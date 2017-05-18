package com.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.core.model.Grid;
import com.mapping.PassengerMapper;
import com.pojo.Passenger;
import com.service.PassengerServiceI;
@Service
public class PassengerServiceImpl implements PassengerServiceI {
	@Resource
	private PassengerMapper passengerDao;
	@Autowired
	private  HttpServletRequest request ;
	
	@Override
	public List<Passenger> findPassengerDicMaps() {
		List<Passenger> results = this.passengerDao.selectAll();
		return results;
	}
	@Override
	public Grid findPassengerList(Passenger passenger) {
		String pageIndex =  request.getParameter("page")==null?"0":request.getParameter("page");
		String rowsIndex =  request.getParameter("rows")==null?"0":request.getParameter("rows");
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(rowsIndex));
		Grid grid = new Grid();
		List<Passenger> results = this.passengerDao.selectPassengerByWhere(passenger);
		Long total = this.passengerDao.selectPassengerCountByWhere(passenger);
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}

	@Override
	public Integer addPassenger(Passenger passenger) {
		return this.passengerDao.insert(passenger);
	}

}
