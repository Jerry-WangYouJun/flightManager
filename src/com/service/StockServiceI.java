package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Stock;

public interface StockServiceI {

	List<Stock> findStockDicMaps();

	Grid findStockList(Stock stock);

	Integer insertStock(Stock stock);

	Integer deleteStock(int  id);


}
