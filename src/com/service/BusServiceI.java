package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Bus;

public interface BusServiceI {

	List<Bus> findBusDicMaps();

	Grid findBusList(Bus bus);

	Integer addBus(Bus bus);

}
