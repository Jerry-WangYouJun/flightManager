package com.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sun.swing.StringUIClientPropertyKey;

import com.core.StringUtils;
import com.mapping.InventoryMapper;
import com.pojo.InstockDetail;
import com.pojo.Inventory;
import com.pojo.OutstockDetail;
import com.service.InventoryServiceI;

@Service
public class InventoryServiceImpl  implements InventoryServiceI {
	
	@Resource
	private InventoryMapper inventoryDao;

	@Override
	/**
	 * 根据仓库ID和入库明细 添加或更新库存
	 */
	public void saveOrUpdate(String stockid, InstockDetail detail) {
		// TODO Auto-generated method stub
		Map param = new HashMap();
		param.put("stockId", stockid);
		param.put("productNo", detail.getProductno());
		Inventory inventory  = null;
		inventory = this.inventoryDao.selectByStockIdAndProductNo(param);
		
		if (inventory == null) {
			inventory = new Inventory();
			inventory.setStockid(stockid);
			inventory.setProductno(detail.getProductno());
			inventory.setProductname(detail.getProductname());
			inventory.setProductstandard(detail.getProductstandard());
			inventory.setInventorynum(detail.getProductnum());
			inventory.setInventoryprice(detail.getTotalprice());
			inventory.setMaxnum(detail.getProductnum().multiply(new BigDecimal(3)));
			inventory.setMinnum(detail.getProductnum().multiply(new BigDecimal(0.3)));
			this.inventoryDao.insert(inventory);
		}else {
			inventory.setInventorynum(inventory.getInventorynum().add(detail.getProductnum()));
			inventory.setInventoryprice(inventory.getInventoryprice().add(detail.getTotalprice()));
			this.inventoryDao.updateByPrimaryKey(inventory);
		}
	}

	@Override
	public List<Map> findByWhere(Map param) {
		// TODO Auto-generated method stub
		List<Map> results = this.inventoryDao.selectByWhere(param);
		return results;
	}

	@Override
	public Long findCountByWhere(Map param) {
		// TODO Auto-generated method stub
		return this.inventoryDao.selectCountByWhere(param);
	}

	/**
	 * 根据仓库ID和出库明细更新库存信息
	 */
	@Override
	public void updateOutstock(String stockid, OutstockDetail temp) {
		// TODO Auto-generated method stub
		
		Map param = new HashMap();
		param.put("stockId", stockid);
		param.put("productNo", temp.getProductno());
		Inventory inventory  = null;
		inventory = this.inventoryDao.selectByStockIdAndProductNo(param);
		inventory.setInventorynum(inventory.getInventorynum().subtract(temp.getProductnum()));
		inventory.setInventoryprice(inventory.getInventoryprice().subtract(temp.getTotalprice()));
		this.inventoryDao.updateByPrimaryKey(inventory);
		
	}

	@Override
	public List<Map> findByAjax(String stockId,String q) {
		// TODO Auto-generated method stub
		Map param = new HashMap();
		param.put("stockId",stockId);
		if (StringUtils.isNotEmpty(q)) {
			param.put("q", "%" + q + "%");
		}
		return this.inventoryDao.findProductByQ(param);
	}
}
