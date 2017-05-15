package com.mapping;

import java.util.List;
import java.util.Map;

import com.pojo.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    
    List<Department> selectAllDepts();

	List<Department> selectDeptByWhere(Map<String , Object> param);
    
	List<Department> selectDept(Department department);
	
	Long selectDeptCountByWhere(Department department);
}