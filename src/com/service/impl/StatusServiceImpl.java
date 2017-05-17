package com.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.core.model.Grid;
import com.mapping.StatusMapper;
import com.pojo.Status;
import com.service.StatusServiceI;
@Service
public class StatusServiceImpl implements StatusServiceI {
	@Resource
	private StatusMapper statusDao;
	@Autowired
	private  HttpServletRequest request ;
	
	@Override
	public List<Status> findStatusDicMaps() {
		List<Status> results = this.statusDao.selectAll();
		return results;
	}
	@Override
	public Grid findStatusList(Status status) {
		String pageIndex =  request.getParameter("page")==null?"0":request.getParameter("page");
		String rowsIndex =  request.getParameter("rows")==null?"0":request.getParameter("rows");
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(rowsIndex));
		Grid grid = new Grid();
		List<Status> results = this.statusDao.selectStatusByWhere(status);
		Long total = this.statusDao.selectStatusCountByWhere(status);
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}

	@Override
	public Integer addStatus(Status status) {
		return this.statusDao.insert(status);
	}

}
