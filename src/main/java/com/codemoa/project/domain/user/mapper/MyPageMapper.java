// 도영
package com.codemoa.project.domain.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codemoa.project.domain.user.entity.LocalUser;

@Mapper
public interface MyPageMapper {
	public String checkSnsLinked(String userId);

}
