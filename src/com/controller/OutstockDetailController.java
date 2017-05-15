package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.core.model.Grid;
import com.pojo.OutstockDetail;
import com.pojo.OutstockDetail;
import com.service.OutstockDetailServiceI;

@Controller
@RequestMapping("/outstockDetail")
public class OutstockDetailController extends BaseController {
	@Resource(name="outstockDetailServiceImpl")
	private OutstockDetailServiceI outstockDetailService;
	
	@RequestMapping("/search")
	@ResponseBody
	public Grid searchDetails(HttpServletRequest request){
		Grid grid = new Grid();
		String outstockId = request.getParameter("outstockId");
		List<OutstockDetail> results = this.outstockDetailService.searchDetailsByOutstockId(outstockId);
		grid.setRows(results);
		
		return grid;
	}
	
	
	@RequestMapping("/saveorupdate")
	@ResponseBody
	public Map saveOrUpdateDetail(HttpServletRequest request){
		Map map = new HashMap();
		
		String outstockId = request.getParameter("outstockId");
		String goodsList = request.getParameter("goodsList");
		
		JSONArray jsonArray = JSONArray.parseArray(goodsList);
		int rows = 0;
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = (JSONObject) jsonArray.get(i);
			OutstockDetail detail = new OutstockDetail();
			detail.setId(obj.getInteger("id"));
			detail.setOutstockid(outstockId);
			detail.setProductno(obj.getString("productno"));
			detail.setProductname(obj.getString("productname"));
			detail.setProductstandard(obj.getString("productstandard"));
			detail.setProductnum(obj.getBigDecimal("productnum"));
			detail.setUnit(obj.getString("unit"));
			detail.setPrice(obj.getBigDecimal("price"));
			detail.setTotalprice(detail.getPrice().multiply(detail.getProductnum()));
			rows += this.outstockDetailService.saveOrUpdate(detail);
		}
		if (rows > 0) {
			map.put("success", true);
			map.put("msg", "保存明细成功!");
		}else{
			map.put("success", false);
			map.put("msg", "保存明细失败!");
		}
		return map;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map deleteDetail(HttpServletRequest  request){
		Map map = new HashMap();
		String id = request.getParameter("id");
		int rows = this.outstockDetailService.deleteDetailByPK(id);
		if (rows > 0) {
			map.put("success", true);
			map.put("msg", "删除明细成功!");
		}else{
			map.put("success", false);
			map.put("msg", "删除明细失败!");
		}
		return map;
	}
	
	
}
