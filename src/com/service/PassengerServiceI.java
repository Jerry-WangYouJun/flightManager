package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Passenger;

public interface PassengerServiceI {

	List<Passenger> findPassengerDicMaps();

	Grid findPassengerList(Passenger passenger);

	Integer addPassenger(Passenger passenger);

}
