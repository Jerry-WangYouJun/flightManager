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
import com.mapping.HotelMapper;
import com.pojo.Hotel;
import com.service.HotelServiceI;

@Controller
@RequestMapping("/hotel")
public class HotelController {
	
	@Resource(name="hotelServiceImpl")
	private HotelServiceI hotelService;
	

	@Resource
	private HotelMapper hotelDao;
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectHotel(Hotel hotel ){
		return  this.hotelService.findHotelList(hotel);
	}
	
	@RequestMapping("/addinit")
	public ModelAndView addInit(){
		ModelAndView mv = new ModelAndView("hotel_add");
		return mv ;
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer addHotel(Hotel hotel ){
		 return this.hotelService.addHotel(hotel);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deleteHotel(@RequestParam Integer id ){
		return this.hotelDao.deleteByPrimaryKey(id);
		 
	}
	
	@RequestMapping("/updateinit")
	public ModelAndView updateHotelInit(@RequestParam int id ){
		Hotel hotel= this.hotelDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("hotel",hotel);//userlist是个Arraylist之类的  
		return new ModelAndView("hotel_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findHotelDetail(@ModelAttribute("hotelForm") Hotel hotel ){
		return this.hotelDao.updateByPrimaryKeySelective(hotel);
	}
	
}
