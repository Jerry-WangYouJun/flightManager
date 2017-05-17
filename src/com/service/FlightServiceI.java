package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Flight;

public interface FlightServiceI {

	List<Flight> findFlightDicMaps();

	Grid findFlightList(Flight flight);

	Integer addFlight(Flight flight);

}
