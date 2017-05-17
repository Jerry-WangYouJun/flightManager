package com.pojo;

public class Flight {
	 private int id ; 
	 private String flight;
	 //航班类型
	 private String flightmodel;
	 //机舱类型
	 private String flighttype ;
	 //使用年限
	 private String useage ; 
	 //商务舱规格
	 private String  business ;
	 //经济舱规格
	 private String tourist ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlight() {
		return flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public String getFlightmodel() {
		return flightmodel;
	}

	public void setFlightmodel(String flightmodel) {
		this.flightmodel = flightmodel;
	}

	public String getFlighttype() {
		return flighttype;
	}

	public void setFlighttype(String flighttype) {
		this.flighttype = flighttype;
	}

	public String getUseage() {
		return useage;
	}

	public void setUseage(String useage) {
		this.useage = useage;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getTourist() {
		return tourist;
	}

	public void setTourist(String tourist) {
		this.tourist = tourist;
	}
	 
	 
}
