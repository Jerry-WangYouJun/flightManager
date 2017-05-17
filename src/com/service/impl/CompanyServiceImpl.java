package com.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.core.model.Grid;
import com.mapping.CompanyMapper;
import com.pojo.Company;
import com.service.CompanyServiceI;
@Service
public class CompanyServiceImpl implements CompanyServiceI {
	@Resource
	private CompanyMapper companyDao;
	@Autowired
	private  HttpServletRequest request ;
	
	@Override
	public List<Company> findCompanyDicMaps() {
		List<Company> results = this.companyDao.selectAll();
		return results;
	}
	@Override
	public Grid findCompanyList(Company company) {
		String pageIndex =  request.getParameter("page")==null?"0":request.getParameter("page");
		String rowsIndex =  request.getParameter("rows")==null?"0":request.getParameter("rows");
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(rowsIndex));
		Grid grid = new Grid();
		List<Company> results = this.companyDao.selectCompanyByWhere(company);
		Long total = this.companyDao.selectCompanyCountByWhere(company);
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}

	@Override
	public Integer addCompany(Company company) {
		return this.companyDao.insert(company);
	}

}
