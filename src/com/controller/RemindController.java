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
import com.mapping.RemindMapper;
import com.pojo.Immune;
import com.pojo.Remind;
import com.service.ImmuneServiceI;
import com.service.RemindServiceI;

@Controller
@RequestMapping("/remind")
public class RemindController {
	
	@Resource(name="remindServiceImpl")
	private RemindServiceI remindService;
	
	@Resource(name="immuneServiceImpl")
	private ImmuneServiceI immuneService;

	@Resource
	private RemindMapper remindDao;
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectRemind(Remind remind ){
		return  this.remindService.findRemindList(remind);
	}
	
	@RequestMapping("/addinit")
	public ModelAndView addInit(){
		ModelAndView mv = new ModelAndView("remind_add");
		List<Immune> list = immuneService.findImmuneDicMaps();
		mv.addObject("immunelist", list) ;
		return mv ;
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer addRemind(Remind remind ){
		 return this.remindService.addRemind(remind);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deleteRemind(@RequestParam Integer id ){
		return this.remindDao.deleteByPrimaryKey(id);
		 
	}
	
	@RequestMapping("/updateinit")
	public ModelAndView updateRemindInit(@RequestParam int id ){
		Remind remind= this.remindDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("remind",remind);//userlist是个Arraylist之类的  
		List<Immune> list = immuneService.findImmuneDicMaps();
		model.put("immunelist", list) ;
		return new ModelAndView("remind_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findRemindDetail(@ModelAttribute("remindForm") Remind remind ){
		return this.remindDao.updateByPrimaryKeySelective(remind);
	}
	
}
