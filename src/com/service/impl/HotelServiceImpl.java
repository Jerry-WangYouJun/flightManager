package com.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.core.model.Grid;
import com.mapping.HotelMapper;
import com.pojo.Hotel;
import com.service.HotelServiceI;
@Service
public class HotelServiceImpl implements HotelServiceI {
	@Resource
	private HotelMapper hotelDao;
	@Autowired
	private  HttpServletRequest request ;
	
	@Override
	public List<Hotel> findHotelDicMaps() {
		List<Hotel> results = this.hotelDao.selectAll();
		return results;
	}
	@Override
	public Grid findHotelList(Hotel hotel) {
		String pageIndex =  request.getParameter("page");
		String rowsIndex =  request.getParameter("rows");
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(rowsIndex));
		Grid grid = new Grid();
		List<Hotel> results = this.hotelDao.selectHotelByWhere(hotel);
		Long total = this.hotelDao.selectHotelCountByWhere(hotel);
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}

	@Override
	public Integer addHotel(Hotel hotel) {
		return this.hotelDao.insert(hotel);
	}

}
