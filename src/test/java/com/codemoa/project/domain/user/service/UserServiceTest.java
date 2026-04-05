package com.codemoa.project.domain.user.service;

import com.codemoa.project.domain.user.dto.request.UserPassUpdateRequest;
import com.codemoa.project.domain.user.dto.request.UserSignUpRequest;
import com.codemoa.project.domain.user.mapper.UserMapper;
import com.codemoa.project.domain.user.repository.LocalUserRepository;
import com.codemoa.project.domain.user.repository.PointLogRepository;
import com.codemoa.project.domain.user.repository.UserRepository;
import com.codemoa.project.support.annotation.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ServiceTest
@DisplayName("UserService 단위 테스트")
class UserServiceTest {

    @InjectMocks
    UserService sut;

    @Mock UserRepository userRepository;
    @Mock LocalUserRepository localUserRepository;
    @Mock PasswordEncoder passwordEncoder;
    @Mock PointLogRepository pointLogRepository;
    @Mock SnsUserService snsUserSerivce;
    @Mock UserMapper userMapper;

    @Test
    @DisplayName("회원가입 시 비밀번호와 확인이 다르면 IllegalArgumentException")
    void signUp_비밀번호_불일치() {
        UserSignUpRequest request = new UserSignUpRequest();
        request.setUserId("user01");
        request.setPass("secret1");
        request.setPassConfirm("secret2");

        assertThatThrownBy(() -> sut.signUp(request, "", ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비밀번호");
    }

    @Test
    @DisplayName("비밀번호 재설정 시 비밀번호와 확인이 다르면 IllegalArgumentException")
    void updatePass_비밀번호_불일치() {
        UserPassUpdateRequest request = new UserPassUpdateRequest();
        request.setUserId("user01");
        request.setPass("a");
        request.setPassConfirm("b");

        assertThatThrownBy(() -> sut.updatePass(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비밀번호");
    }
}
