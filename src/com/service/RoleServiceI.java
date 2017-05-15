package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Role;

public interface RoleServiceI {

	List<Role> findRoleDicMaps();

	Grid findRoleList(Role role);

	Integer addRole(Role role);

}
