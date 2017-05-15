package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapping.OutstockMapper;
import com.pojo.Outstock;
import com.pojo.OutstockDetail;
import com.service.OutstockDetailServiceI;
import com.service.InventoryServiceI;
import com.service.OutstockServiceI;
@Service
public class OutstockServiceImpl implements OutstockServiceI {
	@Resource
	private OutstockMapper outstockDao;
	@Resource(name="outstockDetailServiceImpl")
	private OutstockDetailServiceI  outstockDetailService;
	
	@Resource(name="inventoryServiceImpl")
	private InventoryServiceI inventoryService;

	@Override
	public List<Map> findOutstockInfo(Map map) {
		// TODO Auto-generated method stub
		List<Map> results = this.outstockDao.selectOutstockInfo(map);
		return results;
	}

	@Override
	public Long findOutstockCount(Map results) {
		// TODO Auto-generated method stub
		Long total = this.outstockDao.outstockCountByMap(results);
		return total;
	}

	@Override
	public int insertOutstock(Outstock outstock) {
		// TODO Auto-generated method stub
		outstock.setOutstockstate("00");
		int rows = this.outstockDao.insert(outstock);
		return rows;
	}

	@Override
	public Outstock findOutstockByPK(Integer id) {
		// TODO Auto-generated method stub
		Outstock outstock = this.outstockDao.selectByPrimaryKey(id);
		return outstock;
	}

	@Override
	public int updateOutstock(Outstock outstock) {
		// TODO Auto-generated method stub
		int rows = this.outstockDao.updateByPrimaryKeySelective(outstock);
		return rows;
	}

	@Override
	public int deleteOutstock(Integer id) {
		// TODO Auto-generated method stub
		 int rows = 0;
		
		//删除明细信息
		rows = this.outstockDetailService.deleteDetailsByOutstockId(id);
		//删除主单信息
		rows  = this.outstockDao.deleteByPrimaryKey(id);
		
		return rows;
	}

	@Override
	public Map confirmOutstock(Integer id) {
		// TODO Auto-generated method stub
		Map result = new HashMap();
		Outstock outstock = this.findOutstockByPK(id);
		if (outstock == null) {
			result.put("success", false);
			result.put("msg", "查询不到id为" + id + "的记录信息，无法进行记账操作!");
			return result;
		}
		if (!"00".equals(outstock.getOutstockstate())) {
			result.put("success", false);
			result.put("msg", "id为" + id +"的出库单状态不是[创建]，无法进行记账操作!");
			return result;
		}
		//明细
		List<OutstockDetail> details = this.outstockDetailService.searchDetailsByOutstockId(outstock.getId().toString());
		if (details.size() <= 0) {
			result.put("success", false);
			result.put("msg", "id为" + id +"的出库单没有任何产品明细记录，无法进行记账操作!");
			return result;
		}
		
		//遍历明细
		for (int i = 0; i < details.size(); i++) {
			OutstockDetail temp = details.get(i);
			this.inventoryService.updateOutstock(outstock.getStockid(),temp);
		}
		
		outstock.setOutstockstate("02");
		int rows = this.outstockDao.updateByPrimaryKeySelective(outstock);
		if (rows > 0) {
			result.put("success", true);
			result.put("msg", "记账操作成功!");
		}
		return result;
	}
}
