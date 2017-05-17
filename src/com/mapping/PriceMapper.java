package com.mapping;

import java.util.List;

import com.pojo.Price;

public interface PriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Price price);

    int insertSelective(Price price);

    Price selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Price price);

    int updateByPrimaryKey(Price price);

	List<Price> selectAll();

	List<Price> selectPriceByWhere(Price price);

	Long selectPriceCountByWhere(Price price);
}