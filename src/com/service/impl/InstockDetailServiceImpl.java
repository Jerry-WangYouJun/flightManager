package com.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapping.InstockDetailMapper;
import com.pojo.InstockDetail;
import com.service.InstockDetailServiceI;

@Service
public class InstockDetailServiceImpl implements InstockDetailServiceI {
	@Resource
	private InstockDetailMapper instockDetailDao;
	@Override
	public int deleteDetailsByInstockId(Integer instockId) {
		// TODO Auto-generated method stub
		return this.instockDetailDao.deleteByInstockId(instockId);
	}
	@Override
	public int saveOrUpdate(InstockDetail detail) {
		// TODO Auto-generated method stub
		Integer id =  detail.getId();
		int rows = 0;
		if (id == null) {
			rows = this.instockDetailDao.insert(detail);
		}else{
			rows = this.instockDetailDao.updateByPrimaryKeySelective(detail);
		}
		return rows;
	}
	
	@Override
	public List<InstockDetail> searchDetailsByInstockId(String instockId) {
		// TODO Auto-generated method stub
		List<InstockDetail> list = this.instockDetailDao.selectByInstockId(instockId);
		return list;
	}
	
	@Override
	public int deleteDetailByPK(String id) {
		// TODO Auto-generated method stub
		int rows = this.instockDetailDao.deleteByPrimaryKey(Integer.parseInt(id));
		return rows;
	}

}
