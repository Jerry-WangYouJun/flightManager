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
import com.mapping.ImmuneMapper;
import com.pojo.Immune;
import com.service.ImmuneServiceI;

@Controller
@RequestMapping("/immune")
public class ImmuneController {
	
	@Resource(name="immuneServiceImpl")
	private ImmuneServiceI immuneService;

	@Resource
	private ImmuneMapper immuneDao;
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectImmune(Immune immune ){
		return  this.immuneService.findImmuneList(immune);
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer addImmune(Immune immune ){
		 return this.immuneService.addImmune(immune);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deleteImmune(@RequestParam Integer id ){
		return this.immuneDao.deleteByPrimaryKey(id);
		 
	}
	
	@RequestMapping("/updateinit")
	public ModelAndView updateImmuneInit(@RequestParam int id ){
		Immune immune= this.immuneDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("immune",immune);//userlist是个Arraylist之类的  
		return new ModelAndView("immune_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findImmuneDetail(@ModelAttribute("immuneForm") Immune immune ){
		return this.immuneDao.updateByPrimaryKeySelective(immune);
	}
	
}
