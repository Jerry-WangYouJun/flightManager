package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.core.DateUtils;
import com.core.StringUtils;
import com.core.model.Grid;
import com.pojo.Report;
import com.service.ReportServiceI;

@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {
	@Resource
	private ReportServiceI reportService;
	/**
	 * 库存月报界面
	 * @return
	 */
	@RequestMapping("/month_input")
	public String monthInput(){
		return "inventory_report";
	}
	@RequestMapping("/search_month")
	@ResponseBody
	public Grid showMonthInfo(HttpServletRequest request){
		Grid grid = new Grid();
		String page = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String stockId = request.getParameter("stockId");
		String productNo = request.getParameter("productNo");
		
		//默认是查询当天的库存量
		if (StringUtils.isEmpty(startDate)) {
			startDate = DateUtils.formatDate("yyyy-MM-dd", new Date());
		}
		if (StringUtils.isEmpty(endDate)) {
			endDate = DateUtils.formatDate("yyyy-MM-dd", new Date());
		}
		
		Map param = new HashMap();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("stockId",stockId);
		if (StringUtils.isNotEmpty(productNo)) {
			param.put("productNo", "%" + productNo + "%");
		}
		
		PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(pageSize));
		
		List<Report> results = this.reportService.findMonthInfo(param);
		Long total = this.reportService.findMonthInfoCount(param);
		
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}
	
	@RequestMapping("/product_input")
	public String productInput(){
		return "goods_report";	
	}
	@RequestMapping("/search_product")
	@ResponseBody
	public Grid showProductReport(HttpServletRequest request){
		Grid grid = new Grid();
		String page = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String productNo = request.getParameter("productNo");
		//默认是查询当天的库存量
		if (StringUtils.isEmpty(startDate)) {
			startDate = DateUtils.formatDate("yyyy-MM-dd", new Date());
		}
		if (StringUtils.isEmpty(endDate)) {
			endDate = DateUtils.formatDate("yyyy-MM-dd", new Date());
		}
		
		Map param = new HashMap();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		if (StringUtils.isNotEmpty(productNo)) {
			param.put("productNo", "%" + productNo + "%");
		}
		
		PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(pageSize));
		List<Report> results = this.reportService.findProductInfo(param);
		Long total = this.reportService.findProductInfoCount(param);
		
		grid.setRows(results);
		grid.setTotal(total);
		return grid;
	}
	@RequestMapping("/goodsDetail")
	@ResponseBody
	public Grid findGoodsDetails(HttpServletRequest request){
		Grid grid = new Grid();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String productNo = request.getParameter("productNo");
		Map param = new HashMap();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		if (StringUtils.isNotEmpty(productNo)) {
			param.put("productNo", "%" + productNo + "%");
		}
		List<Map> results = this.reportService.findGoodsDetail(param);
		grid.setRows(results);
		return grid;
	}
	

}
