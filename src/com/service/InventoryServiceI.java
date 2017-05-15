package com.service;

import java.util.List;
import java.util.Map;

import com.pojo.InstockDetail;
import com.pojo.Inventory;
import com.pojo.OutstockDetail;

public interface InventoryServiceI {

	public void saveOrUpdate(String stockid, InstockDetail temp);

	public List<Map> findByWhere(Map param);

	public Long findCountByWhere(Map param);

	public void updateOutstock(String stockid, OutstockDetail temp);

	public List<Map> findByAjax(String stockId,String q);

}
