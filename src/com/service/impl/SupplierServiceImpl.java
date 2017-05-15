package com.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.core.model.Grid;
import com.mapping.SupplierMapper;
import com.pojo.Supplier;
import com.service.SupplierServiceI;
@Service
public class SupplierServiceImpl implements SupplierServiceI {
	@Resource
	private SupplierMapper supplierDao;
	@Autowired
	private  HttpServletRequest request ;
	
	@Override
	public List<Supplier> findSupplierDicMaps() {
		List<Supplier> results = this.supplierDao.selectAll();
		return results;
	}
	@Override
	public Grid findSupplierList(Supplier supplier) {
		String pageIndex =  request.getParameter("page");
		String rowsIndex =  request.getParameter("rows");
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(rowsIndex));
		Grid grid = new Grid();
		List<Supplier> results = this.supplierDao.selectSupplierByWhere(supplier);
		Long total = this.supplierDao.selectSupplierCountByWhere(supplier);
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}

	@Override
	public Integer addSupplier(Supplier supplier) {
		return this.supplierDao.insert(supplier);
	}

}
