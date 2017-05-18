package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Airport;

public interface AirportServiceI {

	List<Airport> findAirportDicMaps();

	Grid findAirportList(Airport airport);

	Integer addAirport(Airport airport);

}
