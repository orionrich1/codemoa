// 도영
package com.codemoa.project.domain.user.mapper;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MyPageMapper {
	public String checkSnsLinked(String userId);

}
