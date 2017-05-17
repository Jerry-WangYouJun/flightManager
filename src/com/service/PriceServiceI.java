package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Price;

public interface PriceServiceI {

	List<Price> findPriceDicMaps();

	Grid findPriceList(Price price);

	Integer addPrice(Price price);

}
