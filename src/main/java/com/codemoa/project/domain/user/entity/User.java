package com.codemoa.project.domain.user.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
public class User {

	@Id
	@Column(name = "user_id", nullable = false, length = 10)
	private String userId;

	@Column(name = "name", length = 10)
	private String name;

	@Column(name = "nickname", unique = true, length = 10)
	private String nickname;

	@Column(name = "mobile", length = 15)
	private String mobile;

	@Column(name = "email", length = 100)
	private String email;

	@Column(name = "total_points", nullable = false)
	private Integer totalPoints = 0;

	@Column(name = "membership_date", nullable = false)
	private LocalDateTime membershipDate;

	@Column(name = "unban_date", nullable = false)
	private LocalDateTime unbanDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "grade_id", referencedColumnName = "grade_id", foreignKey = @ForeignKey(name = "fk_user_grade"))
	private UserGrade userGrade;

	public User(String userId, String name, String nickname, String email, String mobile, Integer totalPoints,
			UserGrade userGrade) {
		this.userId = userId;
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.mobile = mobile;
		this.totalPoints = totalPoints;
		this.userGrade = userGrade;
	}

	@PrePersist
	public void prePersist() {
		this.membershipDate = LocalDateTime.now();
		this.unbanDate = LocalDateTime.now();
	}
}