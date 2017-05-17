package com.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.core.model.Grid;
import com.mapping.TicketMapper;
import com.pojo.Ticket;
import com.service.TicketServiceI;
@Service
public class TicketServiceImpl implements TicketServiceI {
	@Resource
	private TicketMapper ticketDao;
	@Autowired
	private  HttpServletRequest request ;
	
	@Override
	public List<Ticket> findTicketDicMaps() {
		List<Ticket> results = this.ticketDao.selectAll();
		return results;
	}
	@Override
	public Grid findTicketList(Ticket ticket) {
		String pageIndex =  request.getParameter("page")==null?"0":request.getParameter("page");
		String rowsIndex =  request.getParameter("rows")==null?"0":request.getParameter("rows");
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(rowsIndex));
		Grid grid = new Grid();
		List<Ticket> results = this.ticketDao.selectTicketByWhere(ticket);
		Long total = this.ticketDao.selectTicketCountByWhere(ticket);
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}

	@Override
	public Integer addTicket(Ticket ticket) {
		return this.ticketDao.insert(ticket);
	}

}
