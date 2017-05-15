package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Department;

public interface DepartmentServiceI {
	public Grid findDeptList(Department department);
	
	public List<Department> findDeptSelect();

	public Integer addDept(Department department);
}
