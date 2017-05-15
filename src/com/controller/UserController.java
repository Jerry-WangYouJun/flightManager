package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.model.Grid;
import com.github.pagehelper.PageHelper;
import com.pojo.Department;
import com.pojo.Dictionary;
import com.pojo.User;
import com.service.DepartmentServiceI;
import com.service.DictionaryServiceI;
import com.service.UserServiceI;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource(name="userServiceImpl")
	private UserServiceI userService;
	
	@Resource(name="departmentServiceImpl")
	private DepartmentServiceI deptService;

	@Resource(name="dictionaryServiceImpl")
	private DictionaryServiceI dictionaryService;
	
	@RequestMapping("/checkUser")
	public void checkUser(HttpServletRequest request,HttpServletResponse response) {

		String userNo = request.getParameter("userNo");
		String pwd = request.getParameter("pwd");
		User user = this.userService.findUserForLogin(userNo,pwd);
		RequestDispatcher rd = null;
		if (user == null) {
			request.setAttribute("msg", "用户名或者密码错误!");
			rd = request.getRequestDispatcher("/login.jsp");
			
		}else{
			request.getSession().setAttribute("userName", user.getUsername());
			rd = request.getRequestDispatcher("/index.jsp");
		}
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request){
		request.getSession().removeAttribute("userName");
		return "redirect:/login.jsp";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public Grid queryUsers(HttpServletRequest request,Model model){
		
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String userNo = request.getParameter("userNo");
		String userName = request.getParameter("userName");
		String deptId = request.getParameter("deptId");
		String position = request.getParameter("position");
		
		Map params = new HashMap();
		params.put("userno", userNo);
		params.put("username", userName);
		params.put("deptId", deptId);
		params.put("position", position);
		
		//mysql分页插件
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(rows));
		
		Grid grid = new Grid();
		
		List<User> results = this.userService.findUserWhereSql(params);
		Long total = this.userService.findUserCountByWhere(params);
		grid.setRows(results);
		grid.setTotal(total);
		
		return grid;
		
	}
	
	@RequestMapping("/user_input")
	public String addInput(Map<String, Object> map){
		//初始化 籍贯
		List<Dictionary> provinces = this.dictionaryService.findDicMaps("province");
		//初始化 部门
		List<Department> depts = this.deptService.findDeptSelect();
		//初始化性别
		List<Dictionary> sexs = this.dictionaryService.findDicMaps("sex");
		map.put("user", new User());
		map.put("provinces", provinces);
		map.put("sexs", sexs);
		map.put("depts", depts);
		return "user_add";
	}
	
	@ResponseBody
	@RequestMapping(value="/user_add",method=RequestMethod.POST)
	public Map addUser(User user){
		Map<String,Object> result = new HashMap<String,Object>();
		int rows = this.userService.inserUser(user);
		if (rows > 0) {
			result.put("success", true);
			result.put("msg", "保存成功!");
		}else{
			result.put("success", false);
			result.put("msg", "保存失败，请联系管理员!");
		}
		return result;
	}
	
	@RequestMapping(value="user_update_input/{id}",method=RequestMethod.GET)
	public String updateInput(@PathVariable("id") Integer id,Map<String, Object> map){
		User user = this.userService.getUserById(id);
		
		//初始化 籍贯
		List<Dictionary> provinces = this.dictionaryService.findDicMaps("province");
		//初始化 部门
		List<Department> depts = this.deptService.findDeptSelect();
		//初始化性别
		List<Dictionary> sexs = this.dictionaryService.findDicMaps("sex");
		map.put("user", user);
		map.put("provinces", provinces);
		map.put("sexs", sexs);
		map.put("depts", depts);
		return "user_update";
	}
	
	@ResponseBody   //转成JSON字符串
	@RequestMapping(value="/user_update",method=RequestMethod.PUT)
	public Map updateUser(User user){
		Map map = new HashMap();
		int rows = this.userService.updateUser(user);
		if (rows > 0) {
			map.put("success", true);
			map.put("msg", "修改用户成功！");
		}else{
			map.put("success", false);
			map.put("msg", "修改用户失败！");
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(value="/user_delete/{id}",method=RequestMethod.DELETE)
	public Map deleteUser(@PathVariable("id") String id){
		Map map = new HashMap();
		int rows = this.userService.deleteUser(id);
		if (rows > 0) {
			map.put("success", true);
			map.put("msg", "删除用户成功！");
		}else{
			map.put("success", false);
			map.put("msg", "删除用户失败！");
		}
		return map;
	}
}
