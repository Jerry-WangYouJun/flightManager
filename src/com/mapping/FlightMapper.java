package com.mapping;

import java.util.List;

import com.pojo.Flight;

public interface FlightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Flight record);

    int insertSelective(Flight record);

    Flight selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Flight record);

    int updateByPrimaryKey(Flight record);

	List<Flight> selectAll();

	List<Flight> selectFlightByWhere(Flight flight);

	Long selectFlightCountByWhere(Flight flight);
}