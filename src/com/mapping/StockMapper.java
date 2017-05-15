package com.mapping;

import java.util.List;

import com.pojo.Stock;

public interface StockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

	List<Stock> selectAll();
	
	List<Stock>  selectByWhere(Stock stock);

	Long selectCountByWhere(Stock stock);
    
}