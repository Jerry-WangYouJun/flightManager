package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.core.model.Grid;
import com.mapping.RoleMapper;
import com.pojo.Role;
import com.service.RoleServiceI;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Resource(name = "roleServiceImpl")
	private RoleServiceI roleService;

	@Resource
	private RoleMapper roleDao;

	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectRole(Role role) {
		return this.roleService.findRoleList(role);
	}

	@ResponseBody
	@RequestMapping("/insert")
	public Integer addRole(Role role) {
		return this.roleService.addRole(role);
	}

	@ResponseBody
	@RequestMapping("/delete")
	public Integer deleteRole(@RequestParam Integer id) {
		return this.roleDao.deleteByPrimaryKey(id);

	}

	@RequestMapping("/updateinit")
	public ModelAndView updateRoleInit(@RequestParam int id) {
		Role role = this.roleDao.selectByPrimaryKey(id);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("role", role);// userlist是个Arraylist之类的
		return new ModelAndView("role_update", model);
	}

	@ResponseBody
	@RequestMapping("/update")
	public int findRoleDetail(@ModelAttribute("roleForm") Role role) {
		return this.roleDao.updateByPrimaryKeySelective(role);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/roleDics")
	@ResponseBody
	public Map getRoleDics() {
		Map map = new HashMap();
		map.put("results", this.roleService.findRoleDicMaps());
		map.put("success", true);
		return map;
	}
}
