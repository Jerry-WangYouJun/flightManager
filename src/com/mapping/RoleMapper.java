package com.mapping;

import java.util.List;

import com.pojo.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	List<Role> selectRoleByWhere(Role role);

	Long selectRoleCountByWhere(Role role);
}