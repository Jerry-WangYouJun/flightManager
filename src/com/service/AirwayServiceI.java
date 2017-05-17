package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Airway;

public interface AirwayServiceI {

	List<Airway> findAirwayDicMaps();

	Grid findAirwayList(Airway airway);

	Integer addAirway(Airway airway);

}
