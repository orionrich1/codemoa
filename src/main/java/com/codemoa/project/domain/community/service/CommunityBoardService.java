package com.codemoa.project.domain.community.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.codemoa.project.domain.community.dto.request.CreateBoardRequest;
import com.codemoa.project.domain.community.dto.request.UpdateBoardRequest;
import com.codemoa.project.domain.community.dto.response.BoardDetailResponse;
import com.codemoa.project.domain.community.dto.response.BoardListResponse;
import com.codemoa.project.domain.community.entity.CommunityBoard;
import com.codemoa.project.domain.community.repository.CommunityBoardRepository;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;






@Service
@RequiredArgsConstructor
public class CommunityBoardService {

    private final CommunityBoardRepository communityBoardRepository;
    private final UserRepository userRepository;

    /**
     * 게시글 생성
     */
    @Transactional
    public void create(CreateBoardRequest request, String userId) {
        // 1. 사용자 정보를 조회합니다.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + userId));

        // 2. 엔티티의 정적 팩토리 메서드를 호출하여 객체를 생성합니다.
        CommunityBoard board = CommunityBoard.create(
                user,
                request.getTitle(),
                request.getContent(),
                request.getCategory(),
                CommunityBoard.PostType.NORMAL, // 질문글이 아닐 경우 기본값
                null      // TODO: CreateBoardRequest에 stakedPoints 필드 추가 후 request.getStakedPoints()로 변경
        );

        // 3. 생성된 엔티티를 저장합니다.
        communityBoardRepository.save(board);
    }

    /**
     * 게시글 단건 조회
     */
    @Transactional(readOnly = true)
    public BoardDetailResponse findById(Integer boardNo) {
        CommunityBoard board = communityBoardRepository.findById(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. ID: " + boardNo));
        return new BoardDetailResponse(board);
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
    public void update(Integer boardNo, UpdateBoardRequest request) {
        // 1. 수정할 게시글을 찾습니다.
        CommunityBoard board = communityBoardRepository.findById(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 2. 권한 확인 로직 (향후 추가)

        // 3. 엔티티의 수정 메서드를 호출하여 데이터를 변경합니다.
        board.update(request.getTitle(), request.getContent(), request.getCategory());
    }
    
    @Transactional
    public void delete(Integer boardNo) {
        // 1. (권한 확인 - 중요!) 현재 로그인한 사용자가 이 글을 삭제할 권한이 있는지 확인하는 로직이 필요합니다.
        //    (지금은 생략하고 나중에 추가합니다.)

        // 2. 리포지토리를 이용해 ID에 해당하는 게시글을 삭제합니다.
        communityBoardRepository.deleteById(boardNo);
    }
    
    @Transactional(readOnly = true)
    public BoardDetailResponse findById(Integer boardNo) {
        CommunityBoard board = communityBoardRepository.findById(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // CommentService를 이용해 댓글 목록을 가져옵니다.
        List<CommentResponse> comments = commentService.findAll(boardNo);

        // 새로운 생성자를 사용하여 BoardDetailResponse를 생성합니다.
        return new BoardDetailResponse(board, comments);
    }
}

