package com.mapping;

import java.util.List;

import com.pojo.Immune;

public interface ImmuneMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Immune record);

    int insertSelective(Immune record);

    Immune selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Immune record);

    int updateByPrimaryKey(Immune record);

	List<Immune> selectAll();

	List<Immune> selectImmuneByWhere(Immune immune);

	Long selectImmuneCountByWhere(Immune immune);
}