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
import com.pojo.InstockDetail;
import com.service.InstockDetailServiceI;

@Controller
@RequestMapping("/instockDetail")
public class InstockDetailController {
	@Resource(name="instockDetailServiceImpl")
	private InstockDetailServiceI instockDetailService;
	@RequestMapping("/search")
	@ResponseBody
	public Grid searchDetails(HttpServletRequest request){
		Grid grid = new Grid();
		String instockId = request.getParameter("instockId");
		List<InstockDetail> results = this.instockDetailService.searchDetailsByInstockId(instockId);
		grid.setRows(results);
		
		return grid;
	}
	
	@RequestMapping("/saveorupdate")
	@ResponseBody
	public Map saveOrUpdateDetail(HttpServletRequest request){
		Map map = new HashMap();
		
		String instockId = request.getParameter("instockId");
		String goodsList = request.getParameter("goodsList");
		
		JSONArray jsonArray = JSONArray.parseArray(goodsList);
		int rows = 0;
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = (JSONObject) jsonArray.get(i);
			InstockDetail detail = new InstockDetail();
			detail.setId(obj.getInteger("id"));
			detail.setInstockid(instockId);
			detail.setProductno(obj.getString("productno"));
			detail.setProductname(obj.getString("productname"));
			detail.setProductstandard(obj.getString("productstandard"));
			detail.setProductnum(obj.getBigDecimal("productnum"));
			detail.setUnit(obj.getString("unit"));
			detail.setPrice(obj.getBigDecimal("price"));
			detail.setTotalprice(detail.getPrice().multiply(detail.getProductnum()));
			rows += this.instockDetailService.saveOrUpdate(detail);
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
		int rows = this.instockDetailService.deleteDetailByPK(id);
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
