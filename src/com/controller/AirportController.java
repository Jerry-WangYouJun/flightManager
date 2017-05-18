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
import com.mapping.AirportMapper;
import com.pojo.Airport;
import com.service.AirportServiceI;

@Controller
@RequestMapping("/airport")
public class AirportController {
	
	@Resource(name="airportServiceImpl")
	private AirportServiceI airportService;
	

	@Resource
	private AirportMapper airportDao;
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectAirport(Airport airport ){
		return  this.airportService.findAirportList(airport);
	}
	
	@RequestMapping("/addinit")
	public ModelAndView addInit(){
		ModelAndView mv = new ModelAndView("airport_add");
		return mv ;
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer addAirport(Airport airport ){
		 return this.airportService.addAirport(airport);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deleteAirport(@RequestParam Integer id ){
		return this.airportDao.deleteByPrimaryKey(id);
		 
	}
	
	@RequestMapping("/updateinit")
	public ModelAndView updateAirportInit(@RequestParam int id ){
		Airport airport= this.airportDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("airport",airport);//userlist是个Arraylist之类的  
		return new ModelAndView("airport_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findAirportDetail(@ModelAttribute("airportForm") Airport airport ){
		return this.airportDao.updateByPrimaryKeySelective(airport);
	}
	
}
