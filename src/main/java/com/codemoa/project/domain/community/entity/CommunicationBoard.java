//윤식
package com.codemoa.project.domain.community.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "CommunicationBoard") // 실제 DB 테이블 이름
public class CommunicationBoard {
	
	@Id
    private int boardNo;  // free_board_id (PK)
    private String title;     // 제목
    private String content; //내용
    private int recommend; //추천수
    private String file1; //첨부파일
    private String type; //게시글언어 타입
    private String userId; // user_id (FK)

    
}