// com/codemoa/project/domain/user/service/UserDetailsServiceImpl.java

package com.codemoa.project.domain.user.service;

import com.codemoa.project.domain.user.entity.LocalUser;
import com.codemoa.project.domain.user.repository.LocalUserRepository;
// ✅ 새로 만든 CustomUserDetails를 import 합니다.
import com.codemoa.project.domain.user.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final LocalUserRepository localUserRepository;
	// UserRepository는 LocalUser를 통해 User 정보를 가져올 수 있으므로 제거해도 됩니다.

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LocalUser localUser = localUserRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

		// ✅ User 엔티티와 비밀번호를 CustomUserDetails에 담아 반환합니다.
		return new CustomUserDetails(localUser.getUser(), localUser.getPass());
	}
}