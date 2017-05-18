package com.mapping;

import java.util.List;

import com.pojo.Airport;

public interface AirportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Airport record);

    int insertSelective(Airport record);

    Airport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Airport record);

    int updateByPrimaryKey(Airport record);

	List<Airport> selectAll();

	List<Airport> selectAirportByWhere(Airport airport);

	Long selectAirportCountByWhere(Airport airport);
}