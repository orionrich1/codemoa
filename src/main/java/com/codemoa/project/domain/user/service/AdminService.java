// 도영
package com.codemoa.project.domain.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codemoa.project.domain.user.dto.response.UserResponse;
import com.codemoa.project.domain.user.mapper.AdminMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	private final AdminMapper adminMapper;

	public List<UserResponse> getUserList() {
		return adminMapper.getUserList();
	}
	
	public UserResponse getUserDetail(String userId) {
		return adminMapper.getUserDetail(userId);
	}
}
