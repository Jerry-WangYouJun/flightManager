package com.pojo;

public class Immune {
    private Integer id;

    private String immunename;

    private String sheeptype;

    private String immunetype;

    //时间间隔
    private String intervals;
    //是否必须
    private String necessary;

    private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImmunename() {
		return immunename;
	}

	public void setImmunename(String immunename) {
		this.immunename = immunename;
	}

	public String getSheeptype() {
		return sheeptype;
	}

	public void setSheeptype(String sheeptype) {
		this.sheeptype = sheeptype;
	}

	public String getImmunetype() {
		return immunetype;
	}

	public void setImmunetype(String immunetype) {
		this.immunetype = immunetype;
	}

	public String getIntervals() {
		return intervals;
	}

	public void setIntervals(String intervals) {
		this.intervals = intervals;
	}

	public String getNecessary() {
		return necessary;
	}

	public void setNecessary(String necessary) {
		this.necessary = necessary;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    
}