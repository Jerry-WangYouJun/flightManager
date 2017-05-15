package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapping.InstockMapper;
import com.pojo.Instock;
import com.pojo.InstockDetail;
import com.pojo.Inventory;
import com.service.InstockDetailServiceI;
import com.service.InstockServiceI;
import com.service.InventoryServiceI;
@Service
public class InstockServiceImpl implements InstockServiceI {
	@Resource
	private InstockMapper instockDao;
	
	@Resource(name="instockDetailServiceImpl")
	private InstockDetailServiceI  instockDetailService;
	
	@Resource(name="inventoryServiceImpl")
	private InventoryServiceI inventoryService;

	@Override
	public List<Map> findInstockInfo(Map map) {
		// TODO Auto-generated method stub
		List<Map> results = this.instockDao.selectInstockInfo(map);
		return results;
	}

	@Override
	public Long findInstockCount(Map results) {
		// TODO Auto-generated method stub
		Long total = this.instockDao.instockCountByMap(results);
		return total;
	}

	@Override
	public int insertInstock(Instock instock) {
		// TODO Auto-generated method stub
		instock.setInstockstate("00");
		int rows = this.instockDao.insert(instock);
		return rows;
	}

	@Override
	public Instock findInstockByPK(Integer id) {
		// TODO Auto-generated method stub
		Instock instock = this.instockDao.selectByPrimaryKey(id);
		return instock;
	}

	@Override
	public int updateInstock(Instock instock) {
		// TODO Auto-generated method stub
		int rows = this.instockDao.updateByPrimaryKeySelective(instock);
		return rows;
	}

	@Override
	public int deleteInstock(Integer id) {
		// TODO Auto-generated method stub
		 int rows = 0;
		
		//删除明细信息
		rows = this.instockDetailService.deleteDetailsByInstockId(id);
		//删除主单信息
		rows  = this.instockDao.deleteByPrimaryKey(id);
		
		return rows;
	}

	@Override
	public Map confirmInstock(Integer id) {
		// TODO Auto-generated method stub
		Map result = new HashMap();
		Instock instock = this.findInstockByPK(id);
		if (instock == null) {
			result.put("success", false);
			result.put("msg", "查询不到id为" + id + "的记录信息，无法进行记账操作!");
			return result;
		}
		if (!"00".equals(instock.getInstockstate())) {
			result.put("success", false);
			result.put("msg", "id为" + id +"的入库单状态不是[创建]，无法进行记账操作!");
			return result;
		}
		//明细
		List<InstockDetail> details = this.instockDetailService.searchDetailsByInstockId(instock.getId().toString());
		if (details.size() <= 0) {
			result.put("success", false);
			result.put("msg", "id为" + id +"的入库单没有任何产品明细记录，无法进行记账操作!");
			return result;
		}
		
		//遍历明细
		for (int i = 0; i < details.size(); i++) {
			InstockDetail temp = details.get(i);
			this.inventoryService.saveOrUpdate(instock.getStockid(),temp);
		}
		
		instock.setInstockstate("02");
		int rows = this.instockDao.updateByPrimaryKeySelective(instock);
		if (rows > 0) {
			result.put("success", true);
			result.put("msg", "记账操作成功!");
		}
		return result;
	}
	
	
}
