// 도영
package com.codemoa.project.domain.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codemoa.project.domain.user.dto.response.UserResponse;

@Mapper
public interface AdminMapper {
	public List<UserResponse> getUserList();
	public UserResponse getUserDetail(String userId);
}
