package com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Outstock {
    private Integer id;

    private String outstockno;

    private String stockid;

    private String supplierid;

    private Date outstockdate;

    private String outstockstate;

    private String remark;
    
    private int goodid ;
    
    private Double outstockNum ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutstockno() {
        return outstockno;
    }

    public void setOutstockno(String outstockno) {
        this.outstockno = outstockno == null ? null : outstockno.trim();
    }

    public String getStockid() {
        return stockid;
    }

    public void setStockid(String stockid) {
        this.stockid = stockid == null ? null : stockid.trim();
    }

    public String getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(String supplierid) {
        this.supplierid = supplierid == null ? null : supplierid.trim();
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    public Date getOutstockdate() {
        return outstockdate;
    }

    public void setOutstockdate(Date outstockdate) {
        this.outstockdate = outstockdate;
    }

    public String getOutstockstate() {
        return outstockstate;
    }

    public void setOutstockstate(String outstockstate) {
        this.outstockstate = outstockstate == null ? null : outstockstate.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public int getGoodid() {
		return goodid;
	}

	public void setGoodid(int goodid) {
		this.goodid = goodid;
	}

	public Double getOutstockNum() {
		return outstockNum;
	}

	public void setOutstockNum(Double outstockNum) {
		this.outstockNum = outstockNum;
	}
    
    
}