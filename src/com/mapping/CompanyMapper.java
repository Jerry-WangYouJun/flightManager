package com.mapping;

import java.util.List;

import com.pojo.Company;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company company);

    int insertSelective(Company company);

    Company selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Company company);

    int updateByPrimaryKey(Company company);

	List<Company> selectAll();

	List<Company> selectCompanyByWhere(Company company);

	Long selectCompanyCountByWhere(Company company);
}