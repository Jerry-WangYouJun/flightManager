package com.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.core.DateUtils;
import com.mapping.ReportMapper;
import com.pojo.Report;
import com.service.ReportServiceI;
@Service
public class ReportServiceImpl implements ReportServiceI {
	@Resource
	private ReportMapper reportDao;
	
	@Override
	public List<Report> findMonthInfo(Map param) {
		// TODO Auto-generated method stub
		
		List<Report> results = new ArrayList<Report>();
		
		//期初入库量
		List<Map> initInstock = this.reportDao.findInstockMonthInit(param);
		List<Map> initOutstock = this.reportDao.findOutstockMonthInit(param);
		
		//期间入库量
		List<Map> instocks = this.reportDao.findInstocksMonth(param);
		List<Map> outstocks = this.reportDao.findOutstocksMonth(param);
		
		Report report = null;
		for (int i = 0; i < initInstock.size(); i++) {
			Map startInTemp = initInstock.get(i);
			String stockIdDB = startInTemp.get("STOCKID").toString();
			String stockName = startInTemp.get("STOCKNAME").toString();
			String productNoDB = startInTemp.get("PRODUCTNO").toString();
			String productName = startInTemp.get("PRODUCTNAME").toString();
			String productStandard = startInTemp.get("PRODUCTSTANDARD").toString();
			String startInNum = startInTemp.get("STARTINNUM").toString(); //期初入库量
			String startInTotal = startInTemp.get("STARTINTOTAL").toString();//期初出库量
			report =  new Report();
			report.setStockId(stockIdDB);
			report.setStockName(stockName);
			report.setProductNo(productNoDB);
			report.setProductName(productName);
			report.setProductStandard(productStandard);
			report.setStartNum(new BigDecimal(startInNum)); 
			report.setStartTotal(new BigDecimal(startInTotal));
			report.setInNum(new BigDecimal("0"));
			report.setInTotal(new BigDecimal("0"));
			report.setOutNum(new BigDecimal("0"));
			report.setOutTotal(new BigDecimal("0"));
			
			for (int j = 0; j < initOutstock.size(); j++) {
				Map<String, Object> startOutTemp = initOutstock.get(j);
				if (stockIdDB.equals(startOutTemp.get("STOCKID").toString()) 
						&& productNoDB.equals(startOutTemp.get("PRODUCTNO").toString())) {
					String startOutNum = startOutTemp.get("STARTOUTNUM").toString();
					String startOutTotal = startOutTemp.get("STARTOUTTOTAL").toString();
					//期初结算量  ==  期初入库量-期初出库量
					report.setStartNum(new BigDecimal(startInNum).subtract(new BigDecimal(startOutNum)));
					report.setStartTotal(new BigDecimal(startInTotal).subtract(new BigDecimal(startOutTotal)));
				}
			}
			
			for (int m = 0; m < instocks.size(); m++) {
				Map<String, Object> inTemp = instocks.get(m);
				if (stockIdDB.equals(inTemp.get("STOCKID").toString()) 
					&& productNoDB.equals(inTemp.get("PRODUCTNO").toString())) {
					String inNum = inTemp.get("INNUM").toString();
					String inTotal = inTemp.get("INTOTAL").toString();
					//期中入库量
					report.setInNum(new BigDecimal(inNum));
					report.setInTotal(new BigDecimal(inTotal));
				}
			}
			
			for (int n = 0; n < outstocks.size(); n++) {
				Map<String, Object> outTemp = outstocks.get(n);
				if (stockIdDB.equals(outTemp.get("STOCKID").toString()) 
						&& productNoDB.equals(outTemp.get("PRODUCTNO").toString())) {
					String outNum = outTemp.get("OUTNUM").toString();
					String outTotal = outTemp.get("OUTTOTAL").toString();
					//期中出库量
					report.setOutNum(new BigDecimal(outNum));
					report.setOutTotal(new BigDecimal(outTotal));
				}
			}
			
			

			report.setEndNum(report.getStartNum().add(report.getInNum().subtract(report.getOutNum())));
			report.setEndTotal(report.getStartTotal().add(report.getInTotal().subtract(report.getOutTotal())));
			
			results.add(report);
		}
		return results;
	}

	@Override
	public Long findMonthInfoCount(Map param) {
		// TODO Auto-generated method stub
		Long rows = this.reportDao.findMonthReportCount(param);
		return rows;
	}

	@Override
	/**
	 * 物资台账
	 */
	public List<Report> findProductInfo(Map param) {
		// TODO Auto-generated method stub
		List<Report> results = new ArrayList<Report>();
		Map startMap = new HashMap();
		String startDate = param.get("startDate").toString();
		startMap.put("startDate", param.get("startDate"));
		startMap.put("productNo",param.get("productNo"));
		Map endMap = new HashMap();
		endMap.put("endDate", param.get("endDate"));
		endMap.put("productNo", param.get("productNo"));
		List<Map> instockInit = this.reportDao.findGoodsInstockReport(startMap);
		List<Map> instockEnd = this.reportDao.findGoodsInstockReport(endMap);
		List<Map> outstockInit = this.reportDao.findGoodsOutstockReport(startMap);
		List<Map> outstockEnd = this.reportDao.findGoodsOutstockReport(endMap);
		Map temp  = null;
		for (int i = 0; i < instockInit.size(); i++) {
			temp = instockInit.get(i);
			Report report = new Report();
			report.setOperateDate(DateUtils.toDate("yyyy-MM-dd",startDate));
			String productNo = temp.get("PRODUCTNO").toString();
			String productName = temp.get("PRODUCTNAME").toString();
			String productStandard = temp.get("PRODUCTSTANDARD").toString();
			String startInNum = temp.get("INNUM").toString(); //期初入库量
			String startInTotal = temp.get("INTOTAL").toString();//期初入库金额
			
			report.setProductNo(productNo);
			report.setProductName(productName);
			report.setProductStandard(productStandard);
			report.setStartNum(new BigDecimal(startInNum));
			report.setStartTotal(new BigDecimal(startInTotal));
			report.setEndNum(new BigDecimal("0"));
			report.setEndTotal(new BigDecimal("0"));
			
			for (int j = 0; j < outstockInit.size(); j++) {
				temp = outstockInit.get(i);
				if( productNo.equals(temp.get("PRODUCTNO").toString())){
					report.setStartNum(report.getStartNum().subtract(new BigDecimal(temp.get("OUTNUM").toString())));
					report.setStartTotal(report.getStartTotal().subtract(new BigDecimal(temp.get("OUTTOTAL").toString())));
				}
			}
			
			
			for (int m = 0; m < instockEnd.size(); m++) {
				temp = instockEnd.get(m);
				if( productNo.equals(temp.get("PRODUCTNO").toString())){
					report.setEndNum(report.getEndNum().add(new BigDecimal(temp.get("INNUM").toString())));
					report.setEndTotal(report.getEndTotal().add(new BigDecimal(temp.get("INTOTAL").toString())));
				}
			}
			
			for (int n = 0; n < outstockEnd.size(); n++) {
				temp = outstockEnd.get(n);
				if( productNo.equals(temp.get("PRODUCTNO").toString())){
					report.setEndNum(report.getEndNum().subtract(new BigDecimal(temp.get("OUTNUM").toString())));
					report.setEndTotal(report.getEndTotal().subtract(new BigDecimal(temp.get("OUTTOTAL").toString())));
				}
			}
			
			results.add(report);
		}
		
		return results;
	}

	@Override
	public Long findProductInfoCount(Map param) {
		// TODO Auto-generated method stub
		return this.reportDao.findGoodsInstockReportCount(param);
	}

	@Override
	public List<Map> findGoodsDetail(Map param) {
		// TODO Auto-generated method stub
		return this.reportDao.findGoodsDetailReport(param);
	}

	
}
