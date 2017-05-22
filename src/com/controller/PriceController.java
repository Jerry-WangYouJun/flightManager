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
import com.mapping.PriceMapper;
import com.pojo.Flight;
import com.pojo.Price;
import com.service.FlightServiceI;
import com.service.PriceServiceI;

@Controller
@RequestMapping("/price")
public class PriceController {
	
	@Resource(name="priceServiceImpl")
	private PriceServiceI priceService;
	
	@Resource(name="flightServiceImpl")
	private FlightServiceI flightService;

	@Resource
	private PriceMapper priceDao;
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectPrice(Price price ){
		return  this.priceService.findPriceList(price);
	}
	
	@RequestMapping("/addinit")
	public ModelAndView addInit(){
		ModelAndView mv = new ModelAndView("price_add");
		List<Price> list = priceService.findPriceDicMaps();
		mv.addObject("pricelist", list) ;
		List<Flight> flightList = flightService.findFlightDicMaps();
		mv.addObject("flightList", flightList);
		return mv ;
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer addPrice(Price price ){
		 return this.priceService.addPrice(price);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deletePrice(@RequestParam Integer id ){
		return this.priceDao.deleteByPrimaryKey(id);
		 
	}
	
	@RequestMapping("/updateinit")
	public ModelAndView updatePriceInit(@RequestParam int id ){
		Price price= this.priceDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("price",price);//userlist是个Arraylist之类的  
		List<Price> list = priceService.findPriceDicMaps();
		model.put("pricelist", list) ;
		List<Flight> flightList = flightService.findFlightDicMaps();
		model.put("flightList", flightList);
		return new ModelAndView("price_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findPriceDetail(@ModelAttribute("priceForm") Price price ){
		return this.priceDao.updateByPrimaryKeySelective(price);
	}
	
}
