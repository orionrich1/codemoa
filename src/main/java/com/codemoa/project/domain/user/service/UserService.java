//기찬
package com.codemoa.project.domain.user.service;

import org.springframework.stereotype.Service;

import com.codemoa.project.domain.user.dto.request.UserLoginRequest;

import com.codemoa.project.domain.user.entity.LocalUser;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.repository.LocalUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final LocalUserRepository localUserRepository;

	public User login(UserLoginRequest requestDto) {

		// 1. LocalUserRepository를 사용하여 아이디로 LocalUser를 조회합니다.
		LocalUser localUser = localUserRepository.findById(requestDto.getUserId()).orElse(null);

		// 2. if-else 문으로 직접 null을 체크합니다.
		// localUser가 null이라는 것은, 아이디가 존재하지 않는다는 뜻입니다.
		if (localUser == null) {
			// 아이디 없음 -> 실패 -> null 반환
			return null;

		} else {
			// 아이디 있음 -> 이제 비밀번호를 비교합니다.
			if (localUser.getPass().equals(requestDto.getPass())) {
				// 비밀번호 일치 -> 성공! -> 연결된 User 객체 반환

				return localUser.getUser();

			} else {
				// 비밀번호 불일치 -> 실패 -> null 반환
				return null;
			}
		}
	}
}
