package com.pojo;


public class Department {
    private Integer id;

    private String deptdesc;

    private String deptleader;

    private String deptname;

    private String deptno;

    private String depttel;

    private String remark;

    private Department department ;
    
    private String parentdeptid;
    
	private String parentno;//

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptdesc() {
        return deptdesc;
    }

    public void setDeptdesc(String deptdesc) {
        this.deptdesc = deptdesc == null ? null : deptdesc.trim();
    }

    public String getDeptleader() {
        return deptleader;
    }

    public void setDeptleader(String deptleader) {
        this.deptleader = deptleader == null ? null : deptleader.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno == null ? null : deptno.trim();
    }

    public String getDepttel() {
        return depttel;
    }

    public void setDepttel(String depttel) {
        this.depttel = depttel == null ? null : depttel.trim();
    }

    public String getParentdeptid() {
        return parentdeptid;
    }

    public void setParentdeptid(String parentdeptid) {
        this.parentdeptid = parentdeptid == null ? null : parentdeptid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
		setParentno(department.getDeptno());
	}

	public String getParentno() {
		return parentno;
	}

	public void setParentno(String parentno) {
		this.parentno =parentno;
	}
    
    
}