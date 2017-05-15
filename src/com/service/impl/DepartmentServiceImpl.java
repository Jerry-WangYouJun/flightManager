package com.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.core.model.Grid;
import com.mapping.DepartmentMapper;
import com.pojo.Department;
import com.service.DepartmentServiceI;
@Service
public class DepartmentServiceImpl implements DepartmentServiceI {
	@Resource
	private DepartmentMapper deptDao;
	@Autowired
	private HttpServletRequest request;
	/**
	 * 部门下拉框
	 * @return
	 */
	@Override
	public Grid findDeptList(Department department) {
		String pageIndex = request.getParameter("page");
		String rowsIndex = request.getParameter("rows");
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(rowsIndex));
		Grid grid = new Grid();
		List<Department> results = this.deptDao.selectDept(department);
		Long total = this.deptDao.selectDeptCountByWhere(department);
		grid.setRows(results);
		grid.setTotal(total);
		return grid ;
	}
	@Override
	public List<Department> findDeptSelect() {
		return this.deptDao.selectAllDepts();
	}
	@Transactional
	@Override
	public Integer addDept(Department department) {
		return this.deptDao.insert(department);
	}
}
