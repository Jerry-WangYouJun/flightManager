package com.mapping;

import java.util.List;

import com.pojo.InstockDetail;

public interface InstockDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InstockDetail record);

    int insertSelective(InstockDetail record);

    InstockDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InstockDetail record);

    int updateByPrimaryKey(InstockDetail record);

	int deleteByInstockId(Integer instockId);

	List<InstockDetail> selectByInstockId(String instockId);
}