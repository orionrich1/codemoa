package com.codemoa.project.domain.community.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.codemoa.project.domain.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_no")
	private Integer commentNo;

	@Lob
	@Column(nullable = false)
	private String content;

	@Column(name = "is_adopted")
	private boolean isAdopted;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	// --- 연관 관계 ---

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id") // 댓글 작성자
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_no") // 댓글이 달린 게시글
	private CommunityBoard communityBoard;

	@Builder
	public Comment(String content, boolean isAdopted, User user, CommunityBoard communityBoard) {
		this.content = content;
		this.isAdopted = isAdopted;
		this.user = user;
		this.communityBoard = communityBoard;
	}

	public void adopt() {
		this.isAdopted = true;
	}
}