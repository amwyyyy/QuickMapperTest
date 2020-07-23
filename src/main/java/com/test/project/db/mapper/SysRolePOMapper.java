package com.test.project.db.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Select;

import com.test.project.db.po.SysRolePO;
import com.wen.mapper.common.IBaseMapper;

public interface SysRolePOMapper extends IBaseMapper<SysRolePO, Integer> {
	Set<String> selectByUsername(String username);

	@Select("select r.* from sys_role r left join sys_user_role ur on ur.role_id=r.id where ur.user_id=#{userId}")
	List<SysRolePO> selectRolesByUserId(int userId);
}