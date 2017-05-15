package com.mapping;

import java.util.List;
import java.util.Map;

import com.pojo.Instock;

public interface InstockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Instock record);

    int insertSelective(Instock record);

    Instock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Instock record);

    int updateByPrimaryKey(Instock record);
    List<Instock> selectByWhere(Map map);
    List<Map> selectInstockInfo(Map map);

	Long instockCountByMap(Map map);
}