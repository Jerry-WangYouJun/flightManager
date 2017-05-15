package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.core.model.Grid;
import com.mapping.DepartmentMapper;
import com.pojo.Department;
import com.service.DepartmentServiceI;

@Controller
@RequestMapping("/dept")
public class DeptController {
	@Resource(name="departmentServiceImpl")
	private DepartmentServiceI deptService;

	@Resource
	private DepartmentMapper deptDao;
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectDepts(Department department  ){
		return  this.deptService.findDeptList(department);
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer addDept(Department department ){
		 return this.deptService.addDept(department);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deleteDept(@RequestParam Integer id ){
		return this.deptDao.deleteByPrimaryKey(id);
		 
	}
	
	@RequestMapping("/updateinit")
	public ModelAndView updateDeptInit(@RequestParam int id ){
		Department dept= this.deptDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("dept",dept);//userlist是个Arraylist之类的  
		return new ModelAndView("dept_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findDeptdetail(@ModelAttribute("deptForm") Department dept ){
		return this.deptDao.updateByPrimaryKeySelective(dept);
	}
	
	@ResponseBody
	@RequestMapping("/deptDics")
	public Map<String , Object> getSelectOptionDetps(){
		List<Department> results = this.deptService.findDeptSelect();
		Map<String , Object > deptOptionMap = new HashMap<String , Object >();
		deptOptionMap.put("success", true);
		deptOptionMap.put("results", results);
		return deptOptionMap;
	}
	
}
