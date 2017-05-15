package com.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.core.model.Grid;
import com.mapping.RemindMapper;
import com.pojo.Remind;
import com.service.RemindServiceI;
@Service
public class RemindServiceImpl implements RemindServiceI {
	@Resource
	private RemindMapper remindDao;
	@Autowired
	private  HttpServletRequest request ;
	
	@Override
	public List<Remind> findRemindDicMaps() {
		List<Remind> results = this.remindDao.selectAll();
		return results;
	}
	@Override
	public Grid findRemindList(Remind remind) {
		String pageIndex =  request.getParameter("page");
		String rowsIndex =  request.getParameter("rows");
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(rowsIndex));
		Grid grid = new Grid();
		List<Remind> results = this.remindDao.selectRemindByWhere(remind);
		Long total = this.remindDao.selectRemindCountByWhere(remind);
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}

	@Override
	public Integer addRemind(Remind remind) {
		return this.remindDao.insert(remind);
	}

}
