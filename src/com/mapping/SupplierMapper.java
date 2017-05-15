package com.mapping;

import java.util.List;

import com.pojo.Supplier;

public interface SupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);

	List<Supplier> selectAll();

	List<Supplier> selectSupplierByWhere(Supplier supplier);

	Long selectSupplierCountByWhere(Supplier supplier);
}