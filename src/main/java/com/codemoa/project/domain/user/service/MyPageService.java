// 도영
package com.codemoa.project.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.codemoa.project.domain.user.dto.request.UserPassUpdateRequest;
import com.codemoa.project.domain.user.dto.response.MyPageResponse;
import com.codemoa.project.domain.user.entity.LocalUser;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.mapper.MyPageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageService {

	private final UserService userService;
	private final MyPageMapper myPageMapper;
	private final PasswordEncoder passwordEncoder;

	public MyPageResponse checkSnsLinked(User user) {
		MyPageResponse myData = new MyPageResponse();

		String snsType = myPageMapper.checkSnsLinked(user.getUserId());

		myData.setUser(user);
		if (snsType != null && !snsType.isBlank()) {
			myData.setSnsLinked(true);

			if (snsType.equals("google"))
				myData.setSnsType("구글");
			else if (snsType.equals("kakao"))
				myData.setSnsType("카카오");
		}

		return myData;
	}

	public String checkPass(UserPassUpdateRequest request) {

		String userId = request.getUserId();
		LocalUser user = userService.getLocalUser(userId);
		
		String pass = request.getPass();
		boolean match = passwordEncoder.matches(pass, user.getPass());

		if (match) {
			return "Matched";
		} else {
			return "UnMatched";
		}
	}
}
