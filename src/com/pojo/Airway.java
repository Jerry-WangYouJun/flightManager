package com.pojo;

public class Airway {
	  private int id ; 
	  private String flight ; 
	  //起飞时间
	  private String startdate ;
	  //飞行时间
	  private String flighttime;
	  //直飞转机
	  private String flighttype;
	  //航空公司
	  private String  company;
	  private String fromairport ;
	  private String toairport ;
	  //准点率
	  private String ontime ;
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
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getFlighttime() {
		return flighttime;
	}
	public void setFlighttime(String flighttime) {
		this.flighttime = flighttime;
	}
	public String getFlighttype() {
		return flighttype;
	}
	public void setFlighttype(String flighttype) {
		this.flighttype = flighttype;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getFromairport() {
		return fromairport;
	}
	public void setFromairport(String fromairport) {
		this.fromairport = fromairport;
	}
	public String getToairport() {
		return toairport;
	}
	public void setToairport(String toairport) {
		this.toairport = toairport;
	}
	public String getOntime() {
		return ontime;
	}
	public void setOntime(String ontime) {
		this.ontime = ontime;
	}
	  
}
