package com.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.core.model.Grid;
import com.mapping.ImmuneMapper;
import com.pojo.Immune;
import com.service.ImmuneServiceI;
@Service
public class ImmuneServiceImpl implements ImmuneServiceI {
	@Resource
	private ImmuneMapper immuneDao;
	@Autowired
	private  HttpServletRequest request ;
	
	@Override
	public List<Immune> findImmuneDicMaps() {
		List<Immune> results = this.immuneDao.selectAll();
		return results;
	}
	@Override
	public Grid findImmuneList(Immune immune) {
		String pageIndex =  request.getParameter("page");
		String rowsIndex =  request.getParameter("rows");
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(rowsIndex));
		Grid grid = new Grid();
		List<Immune> results = this.immuneDao.selectImmuneByWhere(immune);
		Long total = this.immuneDao.selectImmuneCountByWhere(immune);
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}

	@Override
	public Integer addImmune(Immune immune) {
		return this.immuneDao.insert(immune);
	}

}
