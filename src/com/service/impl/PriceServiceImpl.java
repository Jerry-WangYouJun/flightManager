package com.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.core.model.Grid;
import com.mapping.PriceMapper;
import com.pojo.Price;
import com.service.PriceServiceI;
@Service
public class PriceServiceImpl implements PriceServiceI {
	@Resource
	private PriceMapper priceDao;
	@Autowired
	private  HttpServletRequest request ;
	
	@Override
	public List<Price> findPriceDicMaps() {
		List<Price> results = this.priceDao.selectAll();
		return results;
	}
	@Override
	public Grid findPriceList(Price price) {
		String pageIndex =  request.getParameter("page")==null?"0":request.getParameter("page");
		String rowsIndex =  request.getParameter("rows")==null?"0":request.getParameter("rows");
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(rowsIndex));
		Grid grid = new Grid();
		List<Price> results = this.priceDao.selectPriceByWhere(price);
		Long total = this.priceDao.selectPriceCountByWhere(price);
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}

	@Override
	public Integer addPrice(Price price) {
		return this.priceDao.insert(price);
	}

}
