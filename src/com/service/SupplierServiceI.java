package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Supplier;

public interface SupplierServiceI {

	List<Supplier> findSupplierDicMaps();

	Grid findSupplierList(Supplier supplier);

	Integer addSupplier(Supplier supplier);

}
