//기찬
package com.codemoa.project.domain.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SnsUserMapper {
	public void linkSnsAccount(
		    @Param("userId") String userId,
		    @Param("provider") String provider,
		    @Param("providerId") String providerId
		);
}
