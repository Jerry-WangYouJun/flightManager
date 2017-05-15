package com.service;

import java.util.List;

import com.pojo.OutstockDetail;

public interface OutstockDetailServiceI {

	int deleteDetailsByOutstockId(Integer id);

	List<OutstockDetail> searchDetailsByOutstockId(String string);

	int saveOrUpdate(OutstockDetail detail);

	int deleteDetailByPK(String id);

}
