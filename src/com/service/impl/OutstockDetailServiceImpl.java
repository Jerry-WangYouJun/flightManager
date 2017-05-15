package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapping.OutstockDetailMapper;
import com.pojo.OutstockDetail;
import com.service.OutstockDetailServiceI;
@Service
public class OutstockDetailServiceImpl implements OutstockDetailServiceI {
	@Resource
	private OutstockDetailMapper outstockDetailDao;
	@Override
	public int deleteDetailsByOutstockId(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OutstockDetail> searchDetailsByOutstockId(String outstockId) {
		// TODO Auto-generated method stub
		
		return this.outstockDetailDao.selectByOutstockId(outstockId);
	}

	@Override
	public int saveOrUpdate(OutstockDetail detail) {
		// TODO Auto-generated method stub
		Integer id =  detail.getId();
		int rows = 0;
		if (id == null) {
			rows = this.outstockDetailDao.insert(detail);
		}else{
			rows = this.outstockDetailDao.updateByPrimaryKeySelective(detail);
		}
		return rows;
	}

	@Override
	public int deleteDetailByPK(String id) {
		// TODO Auto-generated method stub
		return this.outstockDetailDao.deleteByPrimaryKey(Integer.parseInt(id));
	}
}
