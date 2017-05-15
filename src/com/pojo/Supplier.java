package com.pojo;

public class Supplier {
    private Integer id;

    private String supplierno;

    private String suppliername;

    private String provincecode;

    private String supplieraddress;

    private String supplieremail;

    private String suppliertel;

    private String suppliertax;

    private String username;

    private String usertel;

    private String usertex;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierno() {
        return supplierno;
    }

    public void setSupplierno(String supplierno) {
        this.supplierno = supplierno == null ? null : supplierno.trim();
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername == null ? null : suppliername.trim();
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode == null ? null : provincecode.trim();
    }

    public String getSupplieraddress() {
        return supplieraddress;
    }

    public void setSupplieraddress(String supplieraddress) {
        this.supplieraddress = supplieraddress == null ? null : supplieraddress.trim();
    }

    public String getSupplieremail() {
        return supplieremail;
    }

    public void setSupplieremail(String supplieremail) {
        this.supplieremail = supplieremail == null ? null : supplieremail.trim();
    }

    public String getSuppliertel() {
        return suppliertel;
    }

    public void setSuppliertel(String suppliertel) {
        this.suppliertel = suppliertel == null ? null : suppliertel.trim();
    }

    public String getSuppliertax() {
        return suppliertax;
    }

    public void setSuppliertax(String suppliertax) {
        this.suppliertax = suppliertax == null ? null : suppliertax.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel == null ? null : usertel.trim();
    }

    public String getUsertex() {
        return usertex;
    }

    public void setUsertex(String usertex) {
        this.usertex = usertex == null ? null : usertex.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}