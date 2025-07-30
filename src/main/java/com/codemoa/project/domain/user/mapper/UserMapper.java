//기찬
package com.codemoa.project.domain.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codemoa.project.domain.user.dto.request.UserUpdateRequest;
import com.codemoa.project.domain.user.entity.LocalUser;
import com.codemoa.project.domain.user.entity.User;

@Mapper
public interface UserMapper {	
	public String getBanReason(String userId);

	
	public List<User> findId(@Param("userName") String userName, @Param("userPhone") String userPhone);

	public User findPass(@Param("userId") String userId, @Param("userName") String userName,
			@Param("userPhone") String userPhone);

	public void updatePass(@Param("userId") String userId, @Param("pass") String pass);
	
	
	public void updateUser(UserUpdateRequest request);
	
	public LocalUser getLocalUser(String userId);
		
	
	public void deleteUser(String userId);
	
}
