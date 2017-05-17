package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Company;

public interface CompanyServiceI {

	List<Company> findCompanyDicMaps();

	Grid findCompanyList(Company company);

	Integer addCompany(Company company);

}
