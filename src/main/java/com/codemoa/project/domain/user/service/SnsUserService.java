package com.codemoa.project.domain.user.service;

import com.codemoa.project.domain.user.entity.SnsUser;
import com.codemoa.project.domain.user.mapper.SnsUserMapper;
import com.codemoa.project.domain.user.repository.SnsUserRepository;
import com.codemoa.project.domain.user.security.OAuth2UserLoginResult;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SnsUserService {

	private final SnsUserRepository snsUserRepository;

	private final SnsUserMapper snsUserMapper;

	// SNS 로그인
	@Transactional
	public OAuth2UserLoginResult processOAuthUser(String provider, Map<String, Object> attributes) {
		OAuth2UserLoginResult result = new OAuth2UserLoginResult();
		String providerId = "";

		if (provider.equals("google")) {
			providerId = attributes.get("sub").toString();
		} else if (provider.equals("kakao")) {
			providerId = attributes.get("id").toString();
		} else {
			result.setStatus(OAuth2UserLoginResult.Status.FAIL);
			return result;
		}

		Optional<SnsUser> user = snsUserRepository.findBySnsId(providerId);
		if (user.isPresent()) {
			result.setStatus(OAuth2UserLoginResult.Status.SUCCESS);
			result.setUser(user.get().getUser()); // 실제 User 객체로 변환
			result.setProviderId(providerId);
		} else {
			result.setStatus(OAuth2UserLoginResult.Status.NEED_SIGN);
			result.setProviderId(providerId);
		}

		return result;
	}

	// SNS 계정과 연동한 상태로 로그인 시 DB에 등록
	@Transactional
	public void linkSnsAccount(String userId, String provider, String providerId) {
		SnsUser user = snsUserMapper.isLinkedAccount(userId);
		if (user == null) {
			snsUserMapper.linkSnsAccount(userId, provider, providerId);
		}
	}

	public void unlinkSnsAccount(String userId) {
		snsUserMapper.unlinkSnsAccount(userId);
	}

}