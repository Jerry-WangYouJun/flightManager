package com.pojo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Report {
	private String stockId;
	private String stockName;
	private String productNo;
	private String productName;
	private String productStandard;
	
	private BigDecimal startNum;
	private BigDecimal startTotal;
	private BigDecimal inNum;
	private BigDecimal inTotal;
	private BigDecimal outNum;
	private BigDecimal outTotal;
	private BigDecimal endNum;
	private BigDecimal endTotal;
	private Date operateDate;
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Report(String stockId, String stockName, String productNo,
			String productName, String productStandard, BigDecimal startNum,
			BigDecimal startTotal, BigDecimal inNum, BigDecimal inTotal,
			BigDecimal outNum, BigDecimal outTotal, BigDecimal endNum,
			BigDecimal endTotal, Date operateDate) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.productNo = productNo;
		this.productName = productName;
		this.productStandard = productStandard;
		this.startNum = startNum;
		this.startTotal = startTotal;
		this.inNum = inNum;
		this.inTotal = inTotal;
		this.outNum = outNum;
		this.outTotal = outTotal;
		this.endNum = endNum;
		this.endTotal = endTotal;
		this.operateDate = operateDate;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductStandard() {
		return productStandard;
	}
	public void setProductStandard(String productStandard) {
		this.productStandard = productStandard;
	}
	public BigDecimal getStartNum() {
		return startNum;
	}
	public void setStartNum(BigDecimal startNum) {
		this.startNum = startNum;
	}
	public BigDecimal getStartTotal() {
		return startTotal;
	}
	public void setStartTotal(BigDecimal startTotal) {
		this.startTotal = startTotal;
	}
	public BigDecimal getInNum() {
		return inNum;
	}
	public void setInNum(BigDecimal inNum) {
		this.inNum = inNum;
	}
	public BigDecimal getInTotal() {
		return inTotal;
	}
	public void setInTotal(BigDecimal inTotal) {
		this.inTotal = inTotal;
	}
	public BigDecimal getOutNum() {
		return outNum;
	}
	public void setOutNum(BigDecimal outNum) {
		this.outNum = outNum;
	}
	public BigDecimal getOutTotal() {
		return outTotal;
	}
	public void setOutTotal(BigDecimal outTotal) {
		this.outTotal = outTotal;
	}
	public BigDecimal getEndNum() {
		return endNum;
	}
	public void setEndNum(BigDecimal endNum) {
		this.endNum = endNum;
	}
	public BigDecimal getEndTotal() {
		return endTotal;
	}
	public void setEndTotal(BigDecimal endTotal) {
		this.endTotal = endTotal;
	}
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
}
