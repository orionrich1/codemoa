package com.codemoa.project.domain.community.service;

import com.codemoa.project.common.util.CommunityHtmlSanitizer;
import com.codemoa.project.domain.community.dto.request.CreateBoardRequest;
import com.codemoa.project.domain.community.dto.request.UpdateBoardRequest;
import com.codemoa.project.domain.community.dto.response.BoardDetailResponse;
import com.codemoa.project.domain.community.dto.response.BoardListResponse;
import com.codemoa.project.domain.community.dto.response.CommentResponse;
import com.codemoa.project.domain.community.entity.Comment;
import com.codemoa.project.domain.community.entity.CommunityBoard;
import com.codemoa.project.domain.community.repository.CommentRepository;
import com.codemoa.project.domain.community.repository.CommunityBoardRepository;
import com.codemoa.project.domain.user.entity.PointEventType;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.repository.UserRepository;
import com.codemoa.project.domain.user.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommunityBoardService {

    private final CommunityBoardRepository communityBoardRepository;
    private final UserRepository userRepository;
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final UserService userService;

    public CommunityBoardService(
            CommunityBoardRepository communityBoardRepository,
            UserRepository userRepository,
            @Lazy CommentService commentService,
            CommentRepository commentRepository,
            UserService userService) {
        this.communityBoardRepository = communityBoardRepository;
        this.userRepository = userRepository;
        this.commentService = commentService;
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    private static final List<String> MAIN_CATEGORIES = Arrays.asList("Java", "Python", "JavaScript", "C#", "Kotlin");

    public Page<BoardListResponse> findAll(String category, String searchType, String keyword, String sort,
            Pageable pageable) {
        Specification<CommunityBoard> spec = boardListSpecification(category, searchType, keyword, sort);
        Pageable withoutSort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return communityBoardRepository.findAll(spec, withoutSort).map(BoardListResponse::new);
    }

    private Specification<CommunityBoard> boardListSpecification(String category, String searchType, String keyword,
            String sort) {
        return (root, query, cb) -> {
            boolean isCountQuery = Long.class.equals(query.getResultType()) || long.class.equals(query.getResultType());
            if (!isCountQuery) {
                query.distinct(true);
                applyBoardListSort(root, query, cb, sort);
            }
            List<Predicate> predicates = buildFilterPredicates(root, query, cb, category, searchType, keyword);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private List<Predicate> buildFilterPredicates(Root<CommunityBoard> root, CriteriaQuery<?> query, CriteriaBuilder cb,
            String category, String searchType, String keyword) {
        List<Predicate> predicates = new ArrayList<>();
        if ("free".equalsIgnoreCase(category)) {
            predicates.add(cb.equal(root.get("category"), "자유"));
        } else if ("etc".equalsIgnoreCase(category)) {
            predicates.add(root.get("category").in(MAIN_CATEGORIES).not());
            predicates.add(cb.notEqual(root.get("category"), "자유"));
        } else if (StringUtils.hasText(category) && !"all".equalsIgnoreCase(category)) {
            predicates.add(cb.equal(root.get("category"), category));
        }
        if (StringUtils.hasText(keyword)) {
            switch (searchType) {
                case "title":
                    predicates.add(cb.like(root.get("title"), "%" + keyword + "%"));
                    break;
                case "content":
                    predicates.add(cb.like(root.get("content"), "%" + keyword + "%"));
                    break;
                case "author":
                    Join<CommunityBoard, User> userJoin = root.join("user", JoinType.INNER);
                    predicates.add(cb.like(userJoin.get("nickname"), "%" + keyword + "%"));
                    break;
                default:
                    predicates.add(cb.or(
                            cb.like(root.get("title"), "%" + keyword + "%"),
                            cb.like(root.get("content"), "%" + keyword + "%")
                    ));
                    break;
            }
        }
        return predicates;
    }

    /**
     * 조회수 컬럼이 없어 {@code recommend} 내림차순을 '추천·관심' 정렬로 제공합니다.
     * {@code comments}는 댓글 수 기준입니다.
     */
    private void applyBoardListSort(Root<CommunityBoard> root, CriteriaQuery<?> query, CriteriaBuilder cb, String sort) {
        String s = (sort == null || sort.isBlank()) ? "board" : sort.trim().toLowerCase();
        switch (s) {
            case "recent":
                query.orderBy(cb.desc(root.get("createdAt")), cb.desc(root.get("boardNo")));
                break;
            case "comments":
                Subquery<Long> sub = query.subquery(Long.class);
                Root<Comment> cr = sub.from(Comment.class);
                sub.select(cb.count(cr));
                sub.where(cb.equal(cr.get("communityBoard"), root));
                query.orderBy(cb.desc(sub), cb.desc(root.get("boardNo")));
                break;
            case "recommend":
            case "popular":
                query.orderBy(cb.desc(cb.coalesce(root.get("recommend"), cb.literal(0))), cb.desc(root.get("boardNo")));
                break;
            default:
                query.orderBy(cb.desc(root.get("boardNo")));
        }
    }

    @Transactional
    public void create(CreateBoardRequest request, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        int pointsToStake = (request.getStakedPoints() == null || request.getStakedPoints() <= 0) ? 0 : request.getStakedPoints();
        CommunityBoard.PostType postType = (pointsToStake > 0) ? CommunityBoard.PostType.QUESTION : CommunityBoard.PostType.NORMAL;

        if (postType == CommunityBoard.PostType.QUESTION) {
            if (user.getTotalPoints() < 1000) {
                throw new IllegalStateException("포인트가 1000점 이상이어야 질문 글을 작성할 수 있습니다.");
            }
            if (pointsToStake > 100) {
                throw new IllegalArgumentException("최대 100 포인트까지 걸 수 있습니다.");
            }
            if (user.getTotalPoints() < pointsToStake) {
                throw new IllegalStateException("보유 포인트가 부족합니다.");
            }
            user.setTotalPoints(user.getTotalPoints() - pointsToStake);
        }

        // [핵심 수정] 'free'로 들어온 카테고리 값을 '자유'로 변환하여 저장합니다.
        String categoryToSave = request.getCategory();
        if ("free".equalsIgnoreCase(categoryToSave)) {
            categoryToSave = "자유";
        }

        CommunityBoard board = CommunityBoard.create(
                user,
                request.getTitle(),
                CommunityHtmlSanitizer.sanitize(request.getContent()),
                categoryToSave, // 변환된 값을 사용
                postType,
                pointsToStake
        );
        communityBoardRepository.save(board);
        userService.addPointsForEvent(userId, PointEventType.CREATE_POST);
    }

    @Transactional(readOnly = true)
    public BoardDetailResponse findById(Integer boardNo) {
        CommunityBoard board = communityBoardRepository.findById(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        List<CommentResponse> comments = commentService.findAll(boardNo);
        return new BoardDetailResponse(board, comments);
    }

    @Transactional
    public void update(Integer boardNo, UpdateBoardRequest request, String currentUserId) {
        CommunityBoard board = communityBoardRepository.findById(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        if (!board.getUser().getUserId().equals(currentUserId)) {
            throw new IllegalStateException("수정 권한이 없습니다.");
        }
        board.update(request.getTitle(), CommunityHtmlSanitizer.sanitize(request.getContent()), request.getCategory());
    }

    @Transactional
    public void delete(Integer boardNo, String currentUserId) {
        CommunityBoard board = communityBoardRepository.findById(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        if (!board.getUser().getUserId().equals(currentUserId)) {
            throw new IllegalStateException("삭제 권한이 없습니다.");
        }
        communityBoardRepository.deleteById(boardNo);
    }

    @Transactional
    public void adoptComment(Integer boardNo, Integer commentNo, String currentUserId) {
        Comment comment = commentRepository.findById(commentNo)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        CommunityBoard board = comment.getCommunityBoard();
        
        User questioner = board.getUser();
        User answerer = comment.getUser();

        if (!questioner.getUserId().equals(currentUserId)) {
            throw new IllegalStateException("게시글 작성자만 답변을 채택할 수 있습니다.");
        }
        if (board.isResolved()) {
            throw new IllegalStateException("이미 해결된 질문입니다.");
        }
        if (questioner.getUserId().equals(answerer.getUserId())) {
            throw new IllegalStateException("자신의 답변은 채택할 수 없습니다.");
        }

        board.resolve();
        comment.adopt();

        int points = board.getStakedPoints();
        answerer.addPoints(points);
    }
    
    @Transactional(readOnly = true)
    public List<CommunityBoard> getLatestPostsByCategory(String category, int size) {
        Specification<CommunityBoard> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category"), category);
        
        Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "boardNo"));
        
        Page<CommunityBoard> boardPage = communityBoardRepository.findAll(spec, pageable);
        
        return boardPage.getContent();
    }

    /**
     * 메인 인기 피드: 댓글 수 우선, 다음으로 추천수·글번호(최신).
     * 게시글 조회수 컬럼이 없어 문서의 ‘인기’ 축을 참여(댓글)·추천으로 구현합니다.
     */
    @Transactional(readOnly = true)
    public List<CommunityBoard> getPopularPosts(int size) {
        return communityBoardRepository.findPopularByEngagement(size);
    }

    /** 메인: 전체 게시판 통합 최신 글 */
    @Transactional(readOnly = true)
    public List<CommunityBoard> getLatestPostsAllCategories(int size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return communityBoardRepository.findAll(pageable).getContent();
    }

    /** 플랫폼 통계: 전체 게시글 수 */
    @Transactional(readOnly = true)
    public long countAllPosts() {
        return communityBoardRepository.count();
    }
}