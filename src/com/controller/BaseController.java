package com.controller;

import java.util.List;

import javax.annotation.Resource;

import com.pojo.Department;
import com.pojo.Dictionary;
import com.pojo.Goods;
import com.pojo.Stock;
import com.pojo.Supplier;
import com.service.DepartmentServiceI;
import com.service.DictionaryServiceI;
import com.service.GoodsServiceI;
import com.service.StockServiceI;
import com.service.SupplierServiceI;

public class BaseController {
	@Resource(name="departmentServiceImpl")
	private DepartmentServiceI deptService;

	@Resource(name="dictionaryServiceImpl")
	private DictionaryServiceI dictionaryService;
	
	@Resource(name="stockServiceImpl")
	private StockServiceI stockService;

	@Resource(name="supplierServiceImpl")
	private SupplierServiceI supplierService;
	
	@Resource(name="goodsServiceImpl")
	private GoodsServiceI goodsService;
	
	/**
	 * 部门下拉框
	 * @return
	 */
	public List<Department> getDeptSelects(){
		return this.deptService.findDeptSelect();
	}
	
	/**
	 * 仓库下拉框
	 * @return
	 */
	public List<Stock> getStockSelects(){
		return this.stockService.findStockDicMaps();
	}
	/**
	 * 供应商下拉框
	 * @return
	 */
	public List<Supplier> getSupplierSelects(){
		return this.supplierService.findSupplierDicMaps();
	}
	
	
	public List<Goods> getGoodSelects() {
		return this.goodsService.findByAjax("");
	}
	/**
	 * 根据字典编码查找字典下拉框
	 * @param dicNo 字典编码
	 * @return
	 */
	public List<Dictionary> getDictionarySelects(String dicNo){
		return this.dictionaryService.findDicMaps(dicNo);
	}
	
}
