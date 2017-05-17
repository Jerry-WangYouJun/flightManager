package com.mapping;

import java.util.List;

import com.pojo.Status;

public interface StatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Status status);

    int insertSelective(Status status);

    Status selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Status status);

    int updateByPrimaryKey(Status status);

	List<Status> selectAll();

	List<Status> selectStatusByWhere(Status status);

	Long selectStatusCountByWhere(Status status);
}