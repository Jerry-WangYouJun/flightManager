package com.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.core.model.Grid;
import com.mapping.FlightMapper;
import com.pojo.Flight;
import com.service.FlightServiceI;
@Service
public class FlightServiceImpl implements FlightServiceI {
	@Resource
	private FlightMapper flightDao;
	@Autowired
	private  HttpServletRequest request ;
	
	@Override
	public List<Flight> findFlightDicMaps() {
		List<Flight> results = this.flightDao.selectAll();
		return results;
	}
	@Override
	public Grid findFlightList(Flight flight) {
		String pageIndex =  request.getParameter("page")==null?"0":request.getParameter("page");
		String rowsIndex =  request.getParameter("rows")==null?"0":request.getParameter("rows");
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(rowsIndex));
		Grid grid = new Grid();
		List<Flight> results = this.flightDao.selectFlightByWhere(flight);
		Long total = this.flightDao.selectFlightCountByWhere(flight);
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}

	@Override
	public Integer addFlight(Flight flight) {
		return this.flightDao.insert(flight);
	}

}
