package com.service;

import java.util.List;

import com.pojo.InstockDetail;

public interface InstockDetailServiceI {

	int deleteDetailsByInstockId(Integer instockId);

	int saveOrUpdate(InstockDetail detail);

	List<InstockDetail> searchDetailsByInstockId(String instockId);

	int deleteDetailByPK(String id);

}
