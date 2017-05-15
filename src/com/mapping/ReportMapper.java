package com.mapping;

import java.util.List;
import java.util.Map;


public interface ReportMapper {

	List<Map> findInstockMonthInit(Map param);

	List<Map> findInstocksMonth(Map param);

	List<Map> findOutstockMonthInit(Map param);

	List<Map> findOutstocksMonth(Map param);
	Long findMonthReportCount(Map param);

	List<Map> findGoodsInstockReport(Map startMap);

	List<Map> findGoodsOutstockReport(Map startMap);

	Long findGoodsInstockReportCount(Map param);

	List<Map> findGoodsDetailReport(Map param);

}
