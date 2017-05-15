package com.pojo;

public class Remind {
    private Integer id;

    private String immuneid;
    //羊群信息
    private String sheep ;
    //上次接种时间
    private String lastdate;
    //提醒时间
    private String remind;
    //提醒方式
    private String remindtype;

    private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImmuneid() {
		return immuneid;
	}

	public void setImmuneid(String immuneid) {
		this.immuneid = immuneid;
	}

	public String getSheep() {
		return sheep;
	}

	public void setSheep(String sheep) {
		this.sheep = sheep;
	}

	public String getLastdate() {
		return lastdate;
	}

	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}

	public String getRemind() {
		return remind;
	}

	public void setRemind(String remind) {
		this.remind = remind;
	}

	public String getRemindtype() {
		return remindtype;
	}

	public void setRemindtype(String remindtype) {
		this.remindtype = remindtype;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    
}