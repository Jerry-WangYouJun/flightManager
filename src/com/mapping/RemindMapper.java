package com.mapping;

import java.util.List;

import com.pojo.Remind;

public interface RemindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Remind record);

    int insertSelective(Remind record);

    Remind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Remind record);

    int updateByPrimaryKey(Remind record);

	List<Remind> selectAll();

	List<Remind> selectRemindByWhere(Remind remind);

	Long selectRemindCountByWhere(Remind remind);
}