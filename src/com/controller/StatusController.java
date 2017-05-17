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
import com.mapping.StatusMapper;
import com.pojo.Status;
import com.service.StatusServiceI;

@Controller
@RequestMapping("/status")
public class StatusController {
	
	@Resource(name="statusServiceImpl")
	private StatusServiceI statusService;
	

	@Resource
	private StatusMapper statusDao;
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectStatus(Status status ){
		return  this.statusService.findStatusList(status);
	}
	
	@RequestMapping("/addinit")
	public ModelAndView addInit(){
		ModelAndView mv = new ModelAndView("status_add");
		List<Status> list = statusService.findStatusDicMaps();
		mv.addObject("statuslist", list) ;
		return mv ;
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer addStatus(Status status ){
		 return this.statusService.addStatus(status);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deleteStatus(@RequestParam Integer id ){
		return this.statusDao.deleteByPrimaryKey(id);
		 
	}
	
	@RequestMapping("/updateinit")
	public ModelAndView updateStatusInit(@RequestParam int id ){
		Status status= this.statusDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("status",status);//userlist是个Arraylist之类的  
		List<Status> list = statusService.findStatusDicMaps();
		model.put("statuslist", list) ;
		return new ModelAndView("status_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findStatusDetail(@ModelAttribute("statusForm") Status status ){
		return this.statusDao.updateByPrimaryKeySelective(status);
	}
	
}
