package com.mapping;

import java.util.List;

import com.pojo.Passenger;

public interface PassengerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Passenger passenger);

    int insertSelective(Passenger passenger);

    Passenger selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Passenger passenger);

    int updateByPrimaryKey(Passenger passenger);

	List<Passenger> selectAll();

	List<Passenger> selectPassengerByWhere(Passenger passenger);

	Long selectPassengerCountByWhere(Passenger passenger);
}