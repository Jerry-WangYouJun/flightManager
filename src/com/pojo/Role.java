package com.pojo;

public class Role {
    private Integer id;

    private String remark;

    private String rolelevel;

    private String rolename;

    private String roleno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRolelevel() {
        return rolelevel;
    }

    public void setRolelevel(String rolelevel) {
        this.rolelevel = rolelevel == null ? null : rolelevel.trim();
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getRoleno() {
        return roleno;
    }

    public void setRoleno(String roleno) {
        this.roleno = roleno == null ? null : roleno.trim();
    }
}