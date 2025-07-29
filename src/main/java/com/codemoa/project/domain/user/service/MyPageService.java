// 도영
package com.codemoa.project.domain.user.service;

import org.springframework.stereotype.Service;

import com.codemoa.project.domain.user.dto.response.MyPageResponse;
import com.codemoa.project.domain.user.entity.SnsUser;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.mapper.MyPageMapper;
import com.codemoa.project.domain.user.mapper.SnsUserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageService {
	private final MyPageMapper myPageMapper;
	private final SnsUserMapper snsUserMapper;

	public MyPageResponse checkSnsLinked(User user) {
		MyPageResponse myData = new MyPageResponse();

		String snsType = myPageMapper.checkSnsLinked(user.getUserId());

		myData.setUser(user);
		if (snsType != null && !snsType.isBlank()) {
			myData.setSnsLinked(true);
			
			if (snsType.equals("google"))
				myData.setSnsType("구글");
			else if(snsType.equals("kakao"))
				myData.setSnsType("카카오");
		}

		return myData;
	}
}
