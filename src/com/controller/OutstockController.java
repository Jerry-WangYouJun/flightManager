package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.core.DateUtils;
import com.core.model.Grid;
import com.pojo.Goods;
import com.pojo.Outstock;
import com.pojo.Stock;
import com.pojo.Supplier;
import com.service.OutstockServiceI;

@Controller
@RequestMapping("/outstock")
public class OutstockController extends BaseController {
	@Resource(name="outstockServiceImpl")
	private OutstockServiceI outstockService;
	
	
	@RequestMapping("/list")
	public String outstockList(){
		
		return "outstock_list";
	}
	@ResponseBody
	@RequestMapping("/query")
	public Grid queryOutstock(HttpServletRequest request,Model model){
		String page = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		String outstockNo = request.getParameter("outstockNo");
		String stockId = request.getParameter("stockId");
		String supplierId = request.getParameter("supplierId");
		String outstockState = request.getParameter("outstockState");
		String outstockDateStart = request.getParameter("outstockDateStart");
		String outstockDateEnd = request.getParameter("outstockDateEnd");
		
		Map map = new HashMap();
		map.put("outstockNo", outstockNo);
		map.put("stockId", stockId);
		map.put("supplierId", supplierId);
		map.put("outstockState", outstockState);
		map.put("outstockDateStart", outstockDateStart);
		map.put("outstockDateEnd", outstockDateEnd);
		
		//分页插件
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
		PageHelper.orderBy("outstockNo desc");

		Grid grid = new Grid();
		List<Map> results  = this.outstockService.findOutstockInfo(map);
		
		Long rows = this.outstockService.findOutstockCount(map);
		
		grid.setRows(results);
		grid.setTotal(rows);
		return grid;
	}
	
	@RequestMapping("/save_input")
	public ModelAndView saveInput(){
		ModelAndView modelAndView = new ModelAndView();
		Outstock  outstock = new Outstock();
		outstock.setOutstockno("CK"+ DateUtils.getDate14());
		
		//仓库下拉框
		List<Stock> stocks = this.getStockSelects();
		//供应商下拉框
		List<Supplier> suppliers = this.getSupplierSelects();
		
		List<Goods> goods = this.getGoodSelects();
		
		modelAndView.addObject("goods",goods);
		modelAndView.addObject("outstock",outstock);
		modelAndView.addObject("stocks",stocks);
		modelAndView.addObject("suppliers",suppliers);
		modelAndView.setViewName("/outstock_input");
		return modelAndView;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Map saveOutstock(Outstock outstock){
		Map map = new HashMap();
		int rows = this.outstockService.insertOutstock(outstock);
		if (rows > 0) {
			map.put("success", true);
			map.put("msg", "保存出库单信息成功!");
		}else{
			map.put("success", false);
			map.put("msg", "保存出库单信息失败!");
		}
		return map;
	}
	
	@RequestMapping(value="/save_input/{id}",method=RequestMethod.GET)
	public String updateInput(@PathVariable("id") Integer id,Map map){
		Outstock outstock = this.outstockService.findOutstockByPK(id);
		
		//仓库下拉框
		List<Stock> stocks = this.getStockSelects();
		//供应商下拉框
		List<Supplier> suppliers = this.getSupplierSelects();
		
		List<Goods> goods = this.getGoodSelects();
		
		map.put("goods",goods);
		map.put("outstock", outstock);
		map.put("stocks",stocks);
		map.put("suppliers", suppliers);
		return "outstock_input";
	}
	
	@RequestMapping(value="/save" ,method=RequestMethod.PUT)
	@ResponseBody
	public Map upateOutstock(Outstock outstock){
		Map map = new HashMap();
		int rows = this.outstockService.updateOutstock(outstock);
		if (rows > 0) {
			map.put("success", true);
			map.put("msg", "修改出库单信息成功!");
		}else{
			map.put("success", false);
			map.put("msg", "修改出库单信息失败!");
		}
		return map;
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Map deleteOutstock(@PathVariable("id")Integer id){
		Map map = new HashMap();
		int rows = this.outstockService.deleteOutstock(id);
		if (rows > 0) {
			map.put("success", true);
			map.put("msg", "删除出库单信息成功!");
		}else{
			map.put("success", false);
			map.put("msg", "删除出库单信息失败!");
		}
		return map;
	}
	
	@RequestMapping(value="/hang/{id}")
	public ModelAndView hangMaterial(@PathVariable("id") Integer id){
		Map map = new HashMap();
		map.put("id", id);
		ModelAndView modelAndView = new ModelAndView();
		List<Map> results = this.outstockService.findOutstockInfo(map);
		if (results.size() > 0) {
			modelAndView.addObject("outstock",results.get(0));
		}
		modelAndView.setViewName("outstock_hang");
		return modelAndView;
	}
	
	@RequestMapping("/detail/{id}")
	public ModelAndView showDetails(@PathVariable("id") Integer id){
		Map map = new HashMap();
		map.put("id", id);
		ModelAndView modelAndView = new ModelAndView();
		List<Map> results = this.outstockService.findOutstockInfo(map);
		if (results.size() > 0) {
			modelAndView.addObject("outstock",results.get(0));
		}
		modelAndView.setViewName("outstock_detail");
		return modelAndView;
	}
	@RequestMapping("/confirm/{id}")
	@ResponseBody
	public Map confirmOutstock(@PathVariable("id") Integer id){
		Map map = new HashMap();
		map = this.outstockService.confirmOutstock(id);
		
		return map;
	}
	
}
