package com.xcc.elec.service;

import com.xcc.elec.model.ApkVersion;
import com.xcc.elec.model.User;
import com.xcc.elec.model.UserSecret;

public interface UserService {




	void insertUserFeedBack(String userid, String content);
	User loginName(String mobile);

	/*
	 * 版本控制
	 * 
	 */
	ApkVersion getApkVersion(String channel);


	String getMobileSecret(String mobile);


	void insertMobileSecret(String mobile, String secret);


	void updateMObileSecret(String mobile, String secret, String id);



	UserSecret getUserSecretByMobile(String mobile);

	/*
	 * 
	 * 获取用户数据
	 * 
	 * */	
	User getUserByMobile(String mobile);
	/**
	 * @param pwd
	 * @param id
	 */
	void updateUserPwdByUserId(String pwd, int id);

/*   用户数据注册
 * */
	
	
	void  insertUser(User user);
	/**
	 * @param userid
	 * @return
	 */
	User getUserByUserId(String userid);
	/**
	 * @param newmobile
	 * @param id
	 */
	void updateUserMobileByUserId(String newmobile, int id);

}
