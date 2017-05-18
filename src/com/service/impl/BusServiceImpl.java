package com.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.core.model.Grid;
import com.mapping.BusMapper;
import com.pojo.Bus;
import com.service.BusServiceI;
@Service
public class BusServiceImpl implements BusServiceI {
	@Resource
	private BusMapper busDao;
	@Autowired
	private  HttpServletRequest request ;
	
	@Override
	public List<Bus> findBusDicMaps() {
		List<Bus> results = this.busDao.selectAll();
		return results;
	}
	@Override
	public Grid findBusList(Bus bus) {
		String pageIndex =  request.getParameter("page")==null?"0":request.getParameter("page");
		String rowsIndex =  request.getParameter("rows")==null?"0":request.getParameter("rows");
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(rowsIndex));
		Grid grid = new Grid();
		List<Bus> results = this.busDao.selectBusByWhere(bus);
		Long total = this.busDao.selectBusCountByWhere(bus);
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}

	@Override
	public Integer addBus(Bus bus) {
		return this.busDao.insert(bus);
	}

}
