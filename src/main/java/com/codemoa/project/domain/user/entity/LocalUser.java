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
    @Column(name = "user_id")
    private String userId;

    // ✅ 이 부분을 수정하세요! (cascade = CascadeType.ALL 추가)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "pass")
    private String pass;
    
    public LocalUser(User user, String pass) {
        this.user = user;
        this.pass = pass;
    }
}