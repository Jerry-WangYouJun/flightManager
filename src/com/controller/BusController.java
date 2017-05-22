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
import com.mapping.BusMapper;
import com.pojo.Airport;
import com.pojo.Bus;
import com.service.AirportServiceI;
import com.service.BusServiceI;

@Controller
@RequestMapping("/bus")
public class BusController {
	
	@Resource(name="busServiceImpl")
	private BusServiceI busService;
	
	@Resource(name="airportServiceImpl")
	private AirportServiceI airportService;

	@Resource
	private BusMapper busDao;
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectBus(Bus bus ){
		return  this.busService.findBusList(bus);
	}
	
	@RequestMapping("/addinit")
	public ModelAndView addInit(){
		ModelAndView mv = new ModelAndView("bus_add");
		List<Bus> list = busService.findBusDicMaps();
		mv.addObject("buslist", list) ;
		List<Airport> airPortList = airportService.findAirportDicMaps();
		mv.addObject("airPortList", airPortList);
		return mv ;
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer addBus(Bus bus ){
		 return this.busService.addBus(bus);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deleteBus(@RequestParam Integer id ){
		return this.busDao.deleteByPrimaryKey(id);
		 
	}
	
	@RequestMapping("/updateinit")
	public ModelAndView updateBusInit(@RequestParam int id ){
		Bus bus= this.busDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("bus",bus);//userlist是个Arraylist之类的  
		List<Bus> list = busService.findBusDicMaps();
		model.put("buslist", list) ;
		List<Airport> airPortList = airportService.findAirportDicMaps();
		model.put("airPortList", airPortList);
		return new ModelAndView("bus_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findBusDetail(@ModelAttribute("busForm") Bus bus ){
		return this.busDao.updateByPrimaryKeySelective(bus);
	}
	
}
