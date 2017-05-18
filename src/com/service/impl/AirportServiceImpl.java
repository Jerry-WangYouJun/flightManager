package com.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.core.model.Grid;
import com.mapping.AirportMapper;
import com.pojo.Airport;
import com.service.AirportServiceI;
@Service
public class AirportServiceImpl implements AirportServiceI {
	@Resource
	private AirportMapper airportDao;
	@Autowired
	private  HttpServletRequest request ;
	
	@Override
	public List<Airport> findAirportDicMaps() {
		List<Airport> results = this.airportDao.selectAll();
		return results;
	}
	@Override
	public Grid findAirportList(Airport airport) {
		String pageIndex =  request.getParameter("page");
		String rowsIndex =  request.getParameter("rows");
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(rowsIndex));
		Grid grid = new Grid();
		List<Airport> results = this.airportDao.selectAirportByWhere(airport);
		Long total = this.airportDao.selectAirportCountByWhere(airport);
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}

	@Override
	public Integer addAirport(Airport airport) {
		return this.airportDao.insert(airport);
	}

}
