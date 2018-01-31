package com.xcc.elec.model;

import java.io.Serializable;
import java.util.Date;

public class Trip implements Serializable{
	
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String userid;
	private String startaddress;
	private String endaddress;
	private String length;
	private String hastime;
	private String speed;
	private String starttime;
	private String endtime;
	private Date createtime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getStartaddress() {
		return startaddress;
	}
	public void setStartaddress(String startaddress) {
		this.startaddress = startaddress;
	}
	public String getEndaddress() {
		return endaddress;
	}
	public void setEndaddress(String endaddress) {
		this.endaddress = endaddress;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getHastime() {
		return hastime;
	}
	public void setHastime(String hastime) {
		this.hastime = hastime;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
	
}
