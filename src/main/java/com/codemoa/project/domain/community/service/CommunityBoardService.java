package com.codemoa.project.domain.community.service;

import com.codemoa.project.domain.community.dto.request.CreateBoardRequest;
import com.codemoa.project.domain.community.dto.request.UpdateBoardRequest;
import com.codemoa.project.domain.community.dto.response.BoardDetailResponse;
import com.codemoa.project.domain.community.dto.response.BoardListResponse;
import com.codemoa.project.domain.community.dto.response.CommentResponse;
import com.codemoa.project.domain.community.entity.Comment;
import com.codemoa.project.domain.community.entity.CommunityBoard;
import com.codemoa.project.domain.community.repository.CommentRepository; // import 추가
import com.codemoa.project.domain.community.repository.CommunityBoardRepository;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityBoardService {

    private final CommunityBoardRepository communityBoardRepository;
    private final UserRepository userRepository;
    private final CommentService commentService;
    private final CommentRepository commentRepository; // ▼▼▼ 이 줄을 추가했습니다 ▼▼▼

    /**
     * 게시글 생성
     */
    @Transactional
    public void create(CreateBoardRequest request, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        int pointsToStake = (request.getStakedPoints() == null || request.getStakedPoints() <= 0) ? 0 : request.getStakedPoints();
        CommunityBoard.PostType postType = (pointsToStake > 0) ? CommunityBoard.PostType.QUESTION : CommunityBoard.PostType.NORMAL;

        if (postType == CommunityBoard.PostType.QUESTION) {
            if (user.getPoint() < 1000) {
                throw new IllegalStateException("포인트가 1000점 이상이어야 질문 글을 작성할 수 있습니다.");
            }
            if (pointsToStake > 100) {
                throw new IllegalArgumentException("최대 100 포인트까지 걸 수 있습니다.");
            }
            if (user.getPoint() < pointsToStake) {
                throw new IllegalStateException("보유 포인트가 부족합니다.");
            }
            user.setPoint(user.getPoint() - pointsToStake);
        }
        
        CommunityBoard board = CommunityBoard.create(
                user,
                request.getTitle(),
                request.getContent(),
                request.getCategory(),
                postType,
                pointsToStake
        );
        communityBoardRepository.save(board);
    }

    /**
     * 게시글 단건 조회 (댓글 포함)
     */
    @Transactional(readOnly = true)
    public BoardDetailResponse findById(Integer boardNo) {
        CommunityBoard board = communityBoardRepository.findById(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        List<CommentResponse> comments = commentService.findAll(boardNo);
        return new BoardDetailResponse(board, comments);
    }

    /**
     * 게시글 전체 목록 조회
     */
    @Transactional(readOnly = true)
    public List<BoardListResponse> findAll() {
        return communityBoardRepository.findAll().stream()
                .map(BoardListResponse::new)
                .collect(Collectors.toList());
    }
    
    /**
     * 게시글 수정
     */
    @Transactional
    public void update(Integer boardNo, UpdateBoardRequest request, String currentUserId) { // <-- userId 추가
        CommunityBoard board = communityBoardRepository.findById(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        if (!board.getUser().getUserId().equals(currentUserId)) {
            throw new IllegalStateException("수정 권한이 없습니다.");
        }

        board.update(request.getTitle(), request.getContent(), request.getCategory());
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void delete(Integer boardNo, String currentUserId) { // <-- userId 추가
        CommunityBoard board = communityBoardRepository.findById(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        
        if (!board.getUser().getUserId().equals(currentUserId)) {
            throw new IllegalStateException("삭제 권한이 없습니다.");
        }

        communityBoardRepository.deleteById(boardNo);
    }
    
    /**
     * 댓글 채택
     */
    @Transactional
    public void adoptComment(Integer boardNo, Integer commentNo, String currentUserId) {
        CommunityBoard board = communityBoardRepository.findById(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        
        Comment comment = commentRepository.findById(commentNo)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

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
        // User 엔티티에 setPoint 메서드가 있어야 합니다.
        answerer.setPoint(answerer.getPoint() + points); 
    }
}