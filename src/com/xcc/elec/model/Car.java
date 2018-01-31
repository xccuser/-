package com.xcc.elec.model;

import java.io.Serializable;
import java.util.Date;

public class Car implements Serializable{
	
	/**
	 * 车的属性
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	//车名称 
	private String name;
	//车型号
	private String type;
	//车图片
	private String img;
	//蓝牙ID
	private String blueid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getBlueid() {
		return blueid;
	}
	public void setBlueid(String blueid) {
		this.blueid = blueid;
	}
	
	
	
	
}
