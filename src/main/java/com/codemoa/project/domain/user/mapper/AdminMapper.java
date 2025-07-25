// 도영
package com.codemoa.project.domain.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codemoa.project.domain.user.dto.response.UserDetailResponse;


@Mapper
public interface AdminMapper {
	public List<UserDetailResponse> getUserList();
	public UserDetailResponse getUserDetail(String userId);
}
