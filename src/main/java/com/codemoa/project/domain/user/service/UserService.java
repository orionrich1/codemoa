package com.codemoa.project.domain.user.service;

import com.codemoa.project.domain.user.dto.request.UserLoginRequest;
import com.codemoa.project.domain.user.dto.response.UserSessionResponse;
import com.codemoa.project.domain.user.entity.LocalUser;
import com.codemoa.project.domain.user.repository.LocalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
// ✅ [핵심 수정!] jakarta가 아닌 org.springframework.transaction... 로 임포트해야 합니다.
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final LocalUserRepository localUserRepository;

    // ✅ [핵심 수정!] 이 어노테이션이 있어야 DB 연결이 유지됩니다.
    @Transactional(readOnly = true)
    public UserSessionResponse login(UserLoginRequest requestDto) {
        // ID로 LocalUser를 찾습니다. orElse(null)로 없으면 null을 반환합니다.
        LocalUser localUser = localUserRepository.findById(requestDto.getUserId()).orElse(null);

        // localUser가 존재하고, 비밀번호가 일치하는지 확인합니다.
        if (localUser != null && localUser.getPass().equals(requestDto.getPass())) {
            // 성공 시, User Entity를 DTO로 변환해서 반환합니다.
            // 이 시점에는 DB 연결이 살아있어서 getNickname() 호출이 안전합니다.
            return new UserSessionResponse(localUser.getUser());
        } else {
            // 실패 시 null을 반환합니다.
            return null;
        }
    }
}