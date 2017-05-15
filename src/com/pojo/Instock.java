package com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Instock {
    private Integer id;

    private Date instockdate;

    private String instockno;

    private String instockstate;

    private String remark;

    private String stockid;

    private String supplierid;
    
    private String goodId;
    private int numbers ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    public Date getInstockdate() {
        return instockdate;
    }

    public void setInstockdate(Date instockdate) {
        this.instockdate = instockdate;
    }

    public String getInstockno() {
        return instockno;
    }

    public void setInstockno(String instockno) {
        this.instockno = instockno == null ? null : instockno.trim();
    }

    public String getInstockstate() {
        return instockstate;
    }

    public void setInstockstate(String instockstate) {
        this.instockstate = instockstate == null ? null : instockstate.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

	public String getGoodId() {
		return goodId;
	}

	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}

	public int getNumbers() {
		return numbers;
	}

	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
    
}