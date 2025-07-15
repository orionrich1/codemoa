//기찬
package com.codemoa.project.domain.user.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // 1. 이 클래스가 DB 테이블과 연결되는 'Entity'임을 선언합니다.
@Getter // 2. 각 필드의 값을 외부에서 읽을 수 있도록 Getter를 자동으로 만들어줍니다.
@NoArgsConstructor // 3. JPA는 Entity를 만들 때 기본 생성자를 필요로 하므로 추가합니다.
public class User {

    private String userId;      // user_id (PK)
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String phone;
    private String address;
    private int point;
    private String socialType;
    private String role;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    // --- Getters and Setters ---

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }

    public String getSocialType() { return socialType; }
    public void setSocialType(String socialType) { this.socialType = socialType; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDateTime getRegDate() { return regDate; }
    public void setRegDate(LocalDateTime regDate) { this.regDate = regDate; }

    public LocalDateTime getUpdateDate() { return updateDate; }
    public void setUpdateDate(LocalDateTime updateDate) { this.updateDate = updateDate; }
}