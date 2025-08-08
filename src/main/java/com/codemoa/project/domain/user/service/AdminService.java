// 도영
package com.codemoa.project.domain.user.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.codemoa.project.domain.user.dto.request.UserBanRequest;
import com.codemoa.project.domain.user.dto.response.UserDetailResponse;
import com.codemoa.project.domain.user.mapper.AdminMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	private final AdminMapper adminMapper;

	public List<UserDetailResponse> getUserList() {
		return adminMapper.getUserList();
	}
	
	public List<UserDetailResponse> getUserList(String keyword) {
		return adminMapper.searchUserList(keyword);
	}

	public UserDetailResponse getUserDetail(String userId) {
		UserDetailResponse user = adminMapper.getUserDetail(userId);

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime unBanDay = user.getUnbanDate();
		user.setIsBan(now.isBefore(unBanDay));

		return user;
	}
	
	public void banUser(UserBanRequest request) {
		adminMapper.banUserUpdate(request);
		adminMapper.banUserInsert(request);
	}
	
	public void unbanUser(String userId) {
		adminMapper.unbanUser(userId);
	}
	
	public void deleteUser(String userId) {
		adminMapper.deleteUser(userId);
	}
}
