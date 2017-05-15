package com.service;

import java.util.List;
import java.util.Map;

import com.pojo.Outstock;

public interface OutstockServiceI {

	public List<Map> findOutstockInfo(Map map);

	public Long findOutstockCount(Map map);

	public int insertOutstock(Outstock outstock);

	public Outstock findOutstockByPK(Integer id);

	public int updateOutstock(Outstock outstock);

	public int deleteOutstock(Integer id);

	public Map confirmOutstock(Integer id);

}
