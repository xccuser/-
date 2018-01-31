package com.xcc.elec.model;

import java.io.Serializable;
import java.util.Date;

public class UserSecret implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String mobile;
	private String secret;
	private Date createtime;
	private Date endtime;
	
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	
	
	
	
}
