package com.mapping;

import java.util.List;
import java.util.Map;

import com.pojo.Outstock;

public interface OutstockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Outstock record);

    int insertSelective(Outstock record);

    Outstock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Outstock record);

    int updateByPrimaryKey(Outstock record);

	List<Map> selectOutstockInfo(Map map);

	Long outstockCountByMap(Map results);
}