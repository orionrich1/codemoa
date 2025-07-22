//기찬
package com.codemoa.project.domain.user.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity
@Getter
@NoArgsConstructor
@Table(name = "user") // 실제 DB 테이블 이름
public class User {

    @Id
    @Column(name = "user_id") // 실제 DB 컬럼 이름
    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "total_points")
    private Integer totalPoints;

    @Column(name = "membership_date")
    private LocalDateTime membershipDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grade_id") // 실제 DB 외래 키 컬럼 이름
    private UserGrade userGrade;

    @PrePersist
    public void prePersist() {
        this.membershipDate = LocalDateTime.now();
    }
}