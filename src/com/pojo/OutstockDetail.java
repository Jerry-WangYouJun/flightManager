package com.pojo;

import java.math.BigDecimal;

public class OutstockDetail {
    private Integer id;

    private String outstockid;

    private String productno;

    private String productname;

    private String productstandard;

    private BigDecimal productnum;

    private String unit;

    private BigDecimal price;

    private BigDecimal totalprice;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutstockid() {
        return outstockid;
    }

    public void setOutstockid(String outstockid) {
        this.outstockid = outstockid == null ? null : outstockid.trim();
    }

    public String getProductno() {
        return productno;
    }

    public void setProductno(String productno) {
        this.productno = productno == null ? null : productno.trim();
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getProductstandard() {
        return productstandard;
    }

    public void setProductstandard(String productstandard) {
        this.productstandard = productstandard == null ? null : productstandard.trim();
    }

    public BigDecimal getProductnum() {
        return productnum;
    }

    public void setProductnum(BigDecimal productnum) {
        this.productnum = productnum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}