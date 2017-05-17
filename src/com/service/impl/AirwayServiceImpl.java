package com.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.core.model.Grid;
import com.mapping.AirwayMapper;
import com.pojo.Airway;
import com.service.AirwayServiceI;
@Service
public class AirwayServiceImpl implements AirwayServiceI {
	@Resource
	private AirwayMapper airwayDao;
	@Autowired
	private  HttpServletRequest request ;
	
	@Override
	public List<Airway> findAirwayDicMaps() {
		List<Airway> results = this.airwayDao.selectAll();
		return results;
	}
	@Override
	public Grid findAirwayList(Airway airway) {
		String pageIndex =  request.getParameter("page")==null?"0":request.getParameter("page");
		String rowsIndex =  request.getParameter("rows")==null?"0":request.getParameter("rows");
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(rowsIndex));
		Grid grid = new Grid();
		List<Airway> results = this.airwayDao.selectAirwayByWhere(airway);
		Long total = this.airwayDao.selectAirwayCountByWhere(airway);
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}

	@Override
	public Integer addAirway(Airway airway) {
		return this.airwayDao.insert(airway);
	}

}
