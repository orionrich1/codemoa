// 도영
package com.codemoa.project.domain.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codemoa.project.domain.user.dto.request.UserBanRequest;
import com.codemoa.project.domain.user.dto.response.UserDetailResponse;

@Mapper
public interface AdminMapper {
	public List<UserDetailResponse> getUserList();
	
	public List<UserDetailResponse> searchUserList(String keyword);

	public UserDetailResponse getUserDetail(String userId);

	public void banUserUpdate(UserBanRequest request);

	public void banUserInsert(UserBanRequest request);

	public void unbanUser(String userId);

	public void deleteUser(String userId);
}
