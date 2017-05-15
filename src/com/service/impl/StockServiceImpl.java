package com.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.core.model.Grid;
import com.mapping.StockMapper;
import com.pojo.Stock;
import com.service.StockServiceI;
@Service
public class StockServiceImpl implements StockServiceI {
	@Resource
	private StockMapper stockDao;
	
	@Autowired
	private  HttpServletRequest request ;

	@Override
	public List<Stock> findStockDicMaps() {
		// TODO Auto-generated method stub
		List<Stock> results = this.stockDao.selectAll();
		return results;
	}

	@Override
	public Grid findStockList(Stock stock) {
		String pageIndex = request.getParameter("page");
		String rowsIndex =  request.getParameter("rows");
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(rowsIndex));
		Grid grid = new Grid();
		List<Stock> results = this.stockDao.selectByWhere(stock);
		Long total = this.stockDao.selectCountByWhere(stock);
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}

	@Override
	public Integer insertStock(Stock stock) {
		return stockDao.insert(stock);
	}

	@Override
	public Integer deleteStock(int id) {
		// TODO Auto-generated method stub
		return stockDao.deleteByPrimaryKey(id);
	}
}
