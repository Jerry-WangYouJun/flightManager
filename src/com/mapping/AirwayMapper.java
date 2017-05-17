package com.mapping;

import java.util.List;

import com.pojo.Airway;

public interface AirwayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Airway airway);

    int insertSelective(Airway airway);

    Airway selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Airway airway);

    int updateByPrimaryKey(Airway airway);

	List<Airway> selectAll();

	List<Airway> selectAirwayByWhere(Airway airway);

	Long selectAirwayCountByWhere(Airway airway);
}