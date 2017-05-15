package com.mapping;

import java.util.List;
import java.util.Map;

import com.pojo.Inventory;

public interface InventoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Inventory record);

    int insertSelective(Inventory record);

    Inventory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Inventory record);

    int updateByPrimaryKey(Inventory record);

	Inventory selectByStockIdAndProductNo(Map param);

	List<Map> selectByWhere(Map param);

	Long selectCountByWhere(Map param);

	List<Map> findProductByQ(Map param);

}