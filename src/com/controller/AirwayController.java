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
import com.mapping.AirwayMapper;
import com.pojo.Airway;
import com.service.AirwayServiceI;

@Controller
@RequestMapping("/airway")
public class AirwayController {
	
	@Resource(name="airwayServiceImpl")
	private AirwayServiceI airwayService;
	

	@Resource
	private AirwayMapper airwayDao;
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectAirway(Airway airway ){
		return  this.airwayService.findAirwayList(airway);
	}
	
	@RequestMapping("/addinit")
	public ModelAndView addInit(){
		ModelAndView mv = new ModelAndView("airway_add");
		List<Airway> list = airwayService.findAirwayDicMaps();
		mv.addObject("airwaylist", list) ;
		return mv ;
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer addAirway(Airway airway ){
		 return this.airwayService.addAirway(airway);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deleteAirway(@RequestParam Integer id ){
		return this.airwayDao.deleteByPrimaryKey(id);
		 
	}
	
	@RequestMapping("/updateinit")
	public ModelAndView updateAirwayInit(@RequestParam int id ){
		Airway airway= this.airwayDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("airway",airway);//userlist是个Arraylist之类的  
		List<Airway> list = airwayService.findAirwayDicMaps();
		model.put("airwaylist", list) ;
		return new ModelAndView("airway_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findAirwayDetail(@ModelAttribute("airwayForm") Airway airway ){
		return this.airwayDao.updateByPrimaryKeySelective(airway);
	}
	
}
