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
import com.mapping.TicketMapper;
import com.pojo.Ticket;
import com.service.TicketServiceI;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	@Resource(name="ticketServiceImpl")
	private TicketServiceI ticketService;
	

	@Resource
	private TicketMapper ticketDao;
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectTicket(Ticket ticket ){
		return  this.ticketService.findTicketList(ticket);
	}
	
	@RequestMapping("/addinit")
	public ModelAndView addInit(){
		ModelAndView mv = new ModelAndView("ticket_add");
		List<Ticket> list = ticketService.findTicketDicMaps();
		mv.addObject("ticketlist", list) ;
		return mv ;
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer addTicket(Ticket ticket ){
		 return this.ticketService.addTicket(ticket);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deleteTicket(@RequestParam Integer id ){
		return this.ticketDao.deleteByPrimaryKey(id);
		 
	}
	
	@RequestMapping("/updateinit")
	public ModelAndView updateTicketInit(@RequestParam int id ){
		Ticket ticket= this.ticketDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("ticket",ticket);//userlist是个Arraylist之类的  
		List<Ticket> list = ticketService.findTicketDicMaps();
		model.put("ticketlist", list) ;
		return new ModelAndView("ticket_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findTicketDetail(@ModelAttribute("ticketForm") Ticket ticket ){
		return this.ticketDao.updateByPrimaryKeySelective(ticket);
	}
	
}
