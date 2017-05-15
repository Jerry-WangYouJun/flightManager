package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.core.model.Grid;
import com.mapping.StockMapper;
import com.pojo.Stock;
import com.service.StockServiceI;

@Controller
@RequestMapping("/stock")
public class StockController {
	@Resource(name="stockServiceImpl")
	private StockServiceI stockService;
	
	@Resource
	private StockMapper stockDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/stockDics")
	@ResponseBody
	public Map getStockDics(){
		Map map = new HashMap();
		map.put("results", this.stockService.findStockDicMaps());
		map.put("success", true);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectStcoks(Stock stock ){
		return  this.stockService.findStockList(stock);
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer insertStock(Stock stock  ){
		 return this.stockService.insertStock(stock);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deleteStock(int id  ){
		 return this.stockService.deleteStock(id);
	}
	
	
	@RequestMapping("/updateinit")
	public ModelAndView updateDeptInit(@RequestParam int id ){
		Stock stock= this.stockDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("stock",stock);
		return new ModelAndView("stock_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findDeptdetail(@ModelAttribute("stockForm") Stock stock ){
		return this.stockDao.updateByPrimaryKeySelective(stock);
	}
}
