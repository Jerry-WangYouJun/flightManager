package com.service;

import java.util.List;
import java.util.Map;

import com.pojo.Instock;

public interface InstockServiceI {

	public List<Map> findInstockInfo(Map map);

	public Long findInstockCount(Map map);

	public int insertInstock(Instock instock);

	public Instock findInstockByPK(Integer id);

	public int updateInstock(Instock instock);

	public int deleteInstock(Integer id);

	public Map confirmInstock(Integer id);

}
