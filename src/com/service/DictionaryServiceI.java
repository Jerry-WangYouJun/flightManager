package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Dictionary;

public interface DictionaryServiceI {
	public List<Dictionary> findDicMaps(String dicNo);

	public Grid findDicList(Dictionary dictionary);

	public Integer addDic(Dictionary dictionary);
}
