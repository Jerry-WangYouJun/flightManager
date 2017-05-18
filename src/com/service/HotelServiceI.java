package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Hotel;

public interface HotelServiceI {

	List<Hotel> findHotelDicMaps();

	Grid findHotelList(Hotel hotel);

	Integer addHotel(Hotel hotel);

}
