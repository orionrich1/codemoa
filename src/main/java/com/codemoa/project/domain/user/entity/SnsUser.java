package com.codemoa.project.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "sns_user")
public class SnsUser {

	@Id
	@Column(name = "user_id", nullable = false, length = 10)
	private String userId;

	@OneToOne(fetch = FetchType.EAGER)
	@MapsId
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", foreignKey = @ForeignKey(name = "fk_sns_user_user"))
	private User user;

	@Column(name = "sns_type", nullable = false, length = 20)
	private String snsType;

	@Column(name = "sns_id", nullable = false, length = 100)
	private String snsId;

	public SnsUser(User user, String snsType, String snsId) {
		this.user = user;
		this.snsType = snsType;
		this.snsId = snsId;
	}
}