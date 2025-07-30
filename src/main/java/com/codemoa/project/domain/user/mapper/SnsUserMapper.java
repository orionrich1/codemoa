//기찬
package com.codemoa.project.domain.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codemoa.project.domain.user.entity.SnsUser;

@Mapper
public interface SnsUserMapper {
	
	public SnsUser isLinkedAccount(String userid);
	
	public void linkSnsAccount(
		    @Param("userId") String userId,
		    @Param("provider") String provider,
		    @Param("providerId") String providerId
		);
	
	public void unlinkSnsAccount(String userId);
}
