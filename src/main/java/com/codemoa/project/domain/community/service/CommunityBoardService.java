package com.codemoa.project.domain.community.service;

import com.codemoa.project.domain.community.dto.request.CreateBoardRequest;
import com.codemoa.project.domain.community.dto.request.UpdateBoardRequest;
import com.codemoa.project.domain.community.dto.response.BoardDetailResponse;
import com.codemoa.project.domain.community.dto.response.BoardListResponse;
import com.codemoa.project.domain.community.dto.response.CommentResponse;
import com.codemoa.project.domain.community.entity.CommunityBoard;
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

    /**
     * 게시글 생성
     */
    @Transactional
    public void create(CreateBoardRequest request, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        CommunityBoard board = CommunityBoard.create(
                user,
                request.getTitle(),
                request.getContent(),
                request.getCategory(),
                CommunityBoard.PostType.NORMAL, // TODO: 포인트 기능 구현 시 QUESTION 타입 처리 필요
                0
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
        // TODO: 페이징 기능 추가 필요
        return communityBoardRepository.findAll().stream()
                .map(BoardListResponse::new)
                .collect(Collectors.toList());
    }
    
    /**
     * 게시글 수정
     */
    @Transactional
    public void update(Integer boardNo, UpdateBoardRequest request) {
        CommunityBoard board = communityBoardRepository.findById(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        
        // TODO: 수정 권한 확인 로직 추가 필요 (로그인한 사용자와 게시글 작성자가 동일한지 확인)
        
        // 엔티티 내부의 update 메서드를 호출하여 변경
        // JPA의 'dirty checking' 기능으로 인해 트랜잭션이 끝날 때 변경 사항이 자동으로 DB에 반영됩니다.
        board.update(request.getTitle(), request.getContent(), request.getCategory());
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void delete(Integer boardNo) {
        // TODO: 삭제 권한 확인 로직 추가 필요 (로그인한 사용자와 게시글 작성자가 동일한지 확인)

        // 참고: 연관된 댓글이 있다면 삭제 정책(Cascade)을 고려해야 합니다.
        // 현재는 게시글만 삭제됩니다.
        communityBoardRepository.deleteById(boardNo);
    }
}