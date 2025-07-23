package com.codemoa.project.domain.user.service;

import com.codemoa.project.domain.user.dto.request.UserLoginRequest;
import com.codemoa.project.domain.user.dto.request.UserSignUpRequest;
import com.codemoa.project.domain.user.dto.response.UserResponse;
import com.codemoa.project.domain.user.entity.LocalUser;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.entity.UserGrade;
import com.codemoa.project.domain.user.repository.LocalUserRepository;
import com.codemoa.project.domain.user.repository.UserGradeRepository;
import com.codemoa.project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final LocalUserRepository localUserRepository;
    private final UserGradeRepository userGradeRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserResponse login(UserLoginRequest request) {
        LocalUser localUser = localUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));

        if (!passwordEncoder.matches(request.getPass(), localUser.getPass())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        return new UserResponse(localUser.getUser());
    }

    @Transactional
    public String signUp(UserSignUpRequest request) {
        if (userRepository.existsById(request.getUserId())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }

        UserGrade defaultGrade = userGradeRepository.findById("BRONZE")
                .orElseThrow(() -> new RuntimeException("DB 확인 필요: 기본 등급(BRONZE) 없음"));

        User newUser = new User(
                request.getUserId(),
                request.getName(),
                request.getNickname(),
                request.getEmail(),
                request.getMobile(),
                0,
                defaultGrade
        );
        // userRepository.save(newUser); // <-- 이 라인을 삭제하거나 주석 처리

        LocalUser newLocalUser = new LocalUser(
                newUser,
                passwordEncoder.encode(request.getPass())
        );
        
        // LocalUser만 저장합니다.
        // 만약 LocalUser에 cascade 옵션이 있다면 User도 함께 저장됩니다.
        // 만약 cascade 옵션이 없다면, 아래의 해결책 2를 시도해야 합니다.
        localUserRepository.save(newLocalUser);

        return newUser.getUserId();
    }
    @Transactional(readOnly = true)
    public UserResponse getUserInfo(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다. id=" + userId));
        return new UserResponse(user);
    }
}