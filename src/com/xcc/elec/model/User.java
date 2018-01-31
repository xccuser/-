/**
 * 
 */
package com.xcc.elec.model;

import java.util.Date;

/**
 *  Administrator
 *	
 */
public class User {
	private static final long serialVersionUID = 1L;
	private int id;
	private String mobile;
	private String pwd;
	private Date createtime;
	private int status;
	private String nickname;
	private String img;
	private String name;
	private String usernumber;
	private String identity;
	public User(){
	}

	
	
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsernumber() {
		return usernumber;
	}
	public void setUsernumber(String usernumber) {
		this.usernumber = usernumber;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", mobile=" + mobile + ", pwd=" + pwd + ", createtime=" + createtime + ", status="
				+ status + ", nickname=" + nickname + ", img=" + img + ", name=" + name + ", usernumber=" + usernumber
				+ "]";
	}
	
	
}
