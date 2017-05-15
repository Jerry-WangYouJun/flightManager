package com.mapping;

import java.util.List;

import com.pojo.Dictionary;

public interface DictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);

	List<Dictionary> selectDicsByDicNo(String dicNo);

	List<Dictionary> selectDicsBywhere(Dictionary dictionary);

	Long selectDicsCountBywhere(Dictionary dictionary);
	
	
}