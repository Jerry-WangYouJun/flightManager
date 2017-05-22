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
import com.mapping.PassengerMapper;
import com.pojo.Airport;
import com.pojo.Passenger;
import com.service.AirportServiceI;
import com.service.PassengerServiceI;

@Controller
@RequestMapping("/passenger")
public class PassengerController {
	
	@Resource(name="passengerServiceImpl")
	private PassengerServiceI passengerService;
	
	@Resource(name="airportServiceImpl")
	private AirportServiceI airportService;
	
	@Resource
	private PassengerMapper passengerDao;
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectPassenger(Passenger passenger ){
		return  this.passengerService.findPassengerList(passenger);
	}
	
	@RequestMapping("/addinit")
	public ModelAndView addInit(){
		ModelAndView mv = new ModelAndView("passenger_add");
		List<Passenger> list = passengerService.findPassengerDicMaps();
		mv.addObject("passengerlist", list) ;
		List<Airport> airPortList = airportService.findAirportDicMaps();
		mv.addObject("airPortList", airPortList);
		return mv ;
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer addPassenger(Passenger passenger ){
		 return this.passengerService.addPassenger(passenger);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deletePassenger(@RequestParam Integer id ){
		return this.passengerDao.deleteByPrimaryKey(id);
		 
	}
	
	@RequestMapping("/updateinit")
	public ModelAndView updatePassengerInit(@RequestParam int id ){
		Passenger passenger= this.passengerDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("passenger",passenger);//userlist是个Arraylist之类的  
		List<Passenger> list = passengerService.findPassengerDicMaps();
		model.put("passengerlist", list) ;
		List<Airport> airPortList = airportService.findAirportDicMaps();
		model.put("airPortList", airPortList);
		return new ModelAndView("passenger_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findPassengerDetail(@ModelAttribute("passengerForm") Passenger passenger ){
		return this.passengerDao.updateByPrimaryKeySelective(passenger);
	}
	
}
