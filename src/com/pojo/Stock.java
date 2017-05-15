package com.pojo;

public class Stock {
    private Integer id;

    private String stockno;

    private String stockname;

    private String provincecode;

    private String stockaddress;

    private String stocktel;

    private String userid;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStockno() {
        return stockno;
    }

    public void setStockno(String stockno) {
        this.stockno = stockno == null ? null : stockno.trim();
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname == null ? null : stockname.trim();
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode == null ? null : provincecode.trim();
    }

    public String getStockaddress() {
        return stockaddress;
    }

    public void setStockaddress(String stockaddress) {
        this.stockaddress = stockaddress == null ? null : stockaddress.trim();
    }

    public String getStocktel() {
        return stocktel;
    }

    public void setStocktel(String stocktel) {
        this.stocktel = stocktel == null ? null : stocktel.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}