package com.pojo;

import java.math.BigDecimal;

public class Inventory {
    private Integer id;

    private String stockid;

    private String productno;

    private String productname;

    private String productstandard;

    private BigDecimal inventorynum;

    private BigDecimal inventoryprice;

    private BigDecimal maxnum;

    private BigDecimal minnum;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStockid() {
        return stockid;
    }

    public void setStockid(String stockid) {
        this.stockid = stockid == null ? null : stockid.trim();
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

    public BigDecimal getInventorynum() {
        return inventorynum;
    }

    public void setInventorynum(BigDecimal inventorynum) {
        this.inventorynum = inventorynum;
    }

    public BigDecimal getInventoryprice() {
        return inventoryprice;
    }

    public void setInventoryprice(BigDecimal inventoryprice) {
        this.inventoryprice = inventoryprice;
    }

    public BigDecimal getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(BigDecimal maxnum) {
        this.maxnum = maxnum;
    }

    public BigDecimal getMinnum() {
        return minnum;
    }

    public void setMinnum(BigDecimal minnum) {
        this.minnum = minnum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}