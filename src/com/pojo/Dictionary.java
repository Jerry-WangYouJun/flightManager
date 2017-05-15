package com.pojo;

public class Dictionary {
    private Integer id;

    private String diccode;

    private String dicno;

    private String dicsort;

    private String dictype;

    private String dicvalue;

    private String remark;

    private String validateflag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiccode() {
        return diccode;
    }

    public void setDiccode(String diccode) {
        this.diccode = diccode == null ? null : diccode.trim();
    }

    public String getDicno() {
        return dicno;
    }

    public void setDicno(String dicno) {
        this.dicno = dicno == null ? null : dicno.trim();
    }

    public String getDicsort() {
        return dicsort;
    }

    public void setDicsort(String dicsort) {
        this.dicsort = dicsort == null ? null : dicsort.trim();
    }

    public String getDictype() {
        return dictype;
    }

    public void setDictype(String dictype) {
        this.dictype = dictype == null ? null : dictype.trim();
    }

    public String getDicvalue() {
        return dicvalue;
    }

    public void setDicvalue(String dicvalue) {
        this.dicvalue = dicvalue == null ? null : dicvalue.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getValidateflag() {
        return validateflag;
    }

    public void setValidateflag(String validateflag) {
        this.validateflag = validateflag == null ? null : validateflag.trim();
    }
}