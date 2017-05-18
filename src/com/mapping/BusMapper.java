package com.mapping;

import java.util.List;

import com.pojo.Bus;

public interface BusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bus bus);

    int insertSelective(Bus bus);

    Bus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bus bus);

    int updateByPrimaryKey(Bus bus);

	List<Bus> selectAll();

	List<Bus> selectBusByWhere(Bus bus);

	Long selectBusCountByWhere(Bus bus);
}