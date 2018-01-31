package com.xcc.elec.model;

import java.io.Serializable;

public class ApkVersion implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private int id;
	private double apkversion;
	private String apkurl;
	private int channel;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public double getApkversion() {
		return apkversion;
	}
	public void setApkversion(double apkversion) {
		this.apkversion = apkversion;
	}
	public String getApkurl() {
		return apkurl;
	}
	public void setApkurl(String apkurl) {
		this.apkurl = apkurl;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	
	
 
	
}
