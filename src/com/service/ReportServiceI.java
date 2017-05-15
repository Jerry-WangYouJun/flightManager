package com.service;

import java.util.List;
import java.util.Map;

import com.pojo.Report;

public interface ReportServiceI {

	public List<Report> findMonthInfo(Map param);

	public Long findMonthInfoCount(Map param);

	public List<Report> findProductInfo(Map param);

	public Long findProductInfoCount(Map param);

	public List<Map> findGoodsDetail(Map param);

}
