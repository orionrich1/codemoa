// com/codemoa/project/domain/user/entity/LocalUser.java

package com.codemoa.project.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "local_user")
public class LocalUser {

    @Id
    @Column(name = "user_id", nullable = false, length = 10, columnDefinition = "VARCHAR(10) COMMENT '사용자 ID (PK, FK)'")
    private String userId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", foreignKey = @ForeignKey(name = "fk_local_user_user"))
    private User user;

    @Column(name = "pass", nullable = false, length = 100, columnDefinition = "VARCHAR(100) COMMENT '비밀번호 (암호화된 값)'")
    private String pass;
    
    public LocalUser(User user, String pass) {
        this.user = user;
        this.pass = pass;
    }
}