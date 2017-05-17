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
import com.mapping.FlightMapper;
import com.pojo.Flight;
import com.service.FlightServiceI;

@Controller
@RequestMapping("/flight")
public class FlightController {
	
	@Resource(name="flightServiceImpl")
	private FlightServiceI flightService;
	

	@Resource
	private FlightMapper flightDao;
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectFlight(Flight flight ){
		return  this.flightService.findFlightList(flight);
	}
	
	@RequestMapping("/addinit")
	public ModelAndView addInit(){
		ModelAndView mv = new ModelAndView("flight_add");
		List<Flight> list = flightService.findFlightDicMaps();
		mv.addObject("flightlist", list) ;
		return mv ;
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer addFlight(Flight flight ){
		 return this.flightService.addFlight(flight);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deleteFlight(@RequestParam Integer id ){
		return this.flightDao.deleteByPrimaryKey(id);
		 
	}
	
	@RequestMapping("/updateinit")
	public ModelAndView updateFlightInit(@RequestParam int id ){
		Flight flight= this.flightDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("flight",flight);//userlist是个Arraylist之类的  
		List<Flight> list = flightService.findFlightDicMaps();
		model.put("flightlist", list) ;
		return new ModelAndView("flight_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findFlightDetail(@ModelAttribute("flightForm") Flight flight ){
		return this.flightDao.updateByPrimaryKeySelective(flight);
	}
	
}
