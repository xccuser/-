package com.xcc.elec.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xcc.api.util.SmsDemo;
import com.xcc.api.util.StringUtil;
import com.xcc.api.util.idCardTest;
import com.xcc.elec.model.ApkVersion;
import com.xcc.elec.model.User;
import com.xcc.elec.model.UserSecret;
import com.xcc.elec.service.UserService;


@Controller
public class UserController {
	
	private UserService userService;
	//http://localhost:8080/ElecApi/test
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
/*
 * 版本控制
 * 
 * */
	@RequestMapping(value = "/checkVersion")
	public void checkVersion(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		
		String jsonstring=req.getAttribute("data").toString();
		JSONObject json = null;
		
		try {
			json = new JSONObject(jsonstring);
		} catch (Exception e) {
			
			responseErrorData("JSON格式错误", resp);
		
		}
		String channel=json.optString("channel");
		String apkversion=json.optString("apkversion");
		ApkVersion apkVersion=userService.getApkVersion(channel);
		JSONObject resultObj = new JSONObject();
		Double douapkversion=Double.parseDouble(StringUtil.removeAllPoint(apkversion));	
		if(apkVersion.getApkversion()>douapkversion){
		
			resultObj.put("error_code", 0);
			resultObj.put("error_msg", "版本需要更新");
			resultObj.put("apkurl", apkVersion.getApkurl());
			responseData(resultObj.toString(), resp);
		
		}else{
		
			resultObj.put("error_code", 1);
			resultObj.put("error_msg", "版本不需要更新");
			responseData(resultObj.toString(), resp);
		
		}
		
	}
	
	
	
	/**
	 * 获取手机验证码   检查一
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSecretByMobile")
	public void getSecretByMobile(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		String jsonstring=req.getAttribute("data").toString();
		JSONObject json = null;
		try {
			json = new JSONObject(jsonstring);
		} catch (Exception e) {
			responseErrorData("JSON格式错误", resp);
		}
		// 获取手机号码
		String mobile = json.optString("mobile");
		// 判断手机号码正不正确,因前端判断,后端不判断 		
		String secret = RandomStringUtils.random(4, "1234567890");
		String returncode = SmsDemo.sendSms(mobile, secret);
		//短信平台返回正确 则代表发送成功
		if (returncode.equals("ok")) {
			String id = userService.getMobileSecret(mobile);
			if (StringUtil.isEmpty(id)) {				
			    userService.insertMobileSecret(mobile, secret);
			} else {
				userService.updateMObileSecret(mobile, secret, id);
			}
			JSONObject resultObj = new JSONObject();
			resultObj.put("error_code", 0);
			resultObj.put("error_msg", "发送成功");
			responseData(resultObj.toString(), resp);
		} else {
			responseErrorData("发送失败", resp);
		}
	}
	
	/*
	 * 注册 : 验证码，密码，手机，用户名，昵称，身份证，真实姓名
	 * 
	 * */
	
	@RequestMapping("/register")
	public  void RegisteredUser(HttpServletRequest req, HttpServletResponse resps) throws Exception{
		String jsonstring=req.getAttribute("data").toString();
		JSONObject json = null;
		try {
			json = new JSONObject(jsonstring);
		} catch (Exception e) {
			responseErrorData("JSON格式错误", resps);
		}
		
		String mobile = json.optString("mobile");
		String pwd = json.optString("pwd");
		String username=json.optString("username");
		String identity=json.optString("identity");
		String secret=json.optString("secret");

		User user=new User();
		user.setName(username);
		user.setPwd(pwd);
		user.setIdentity(identity);
		user.setNickname("用户1");
		user.setMobile(mobile);
		if(!idCardTest.isMobileNO(mobile)){
			if(userService.getUserByMobile(mobile)!=null){
				System.out.println(user);
				responseErrorData("用户手机注册过", resps);
				return;
			}
		}else{
			responseErrorData("用户手机号不对", resps);
			return;
		}
/*
 * 用户省份证验证
 * 
 * */
		if(!idCardTest.testID_Card(identity)){
			responseErrorData("用户省份证号不对", resps);
			return;
		}
		
		try{
			userService.insertUser(user);
			responseData("注册成功", resps);
		}catch(Exception e){
			e.printStackTrace();
			responseErrorData("注册失败", resps);
		}
		
		
		
	}
	
	
	
	
	/*
	 * 登录接口   手机和密码
	 * 
	 * */
	@RequestMapping("/login")
	public void test(HttpServletRequest req,
			HttpServletResponse resp) throws Exception{
		String jsonstring=req.getAttribute("data").toString();
		System.out.println(jsonstring);
		JSONObject json = null;
		try {
			
			json = new JSONObject(jsonstring);
			
		} catch (Exception e) {
			
			responseErrorData("JSON格式错误", resp);
			
		}
		// 获取手机号码
		String mobile = json.optString("mobile");
		String pwd = json.optString("pwd");
	    // 获取用户
		User user=userService.loginName(mobile);
		JSONObject resultObj = new JSONObject();
		
		// 判断验证码
		
		if (user != null && user.getPwd().equals(pwd)) {
			resultObj.put("error_code", 0);
			resultObj.put("error_msg", "登录成功");
			resultObj.put("userid", user.getId());
			responseData(resultObj.toString(), resp);
			
		}else{
			
			responseErrorData("用户名密码错误", resp);
		
		}
	}
	
	
	/*
	 * 
	 * 修改密码
	 * 
	 * 
	*/
	
	@RequestMapping(value = "/setPwd")
	public void forgetPwd(HttpServletRequest req,
			HttpServletResponse resp) throws Exception  {
		String jsonstring=req.getAttribute("data").toString();
		JSONObject json = null;
		try {
			json = new JSONObject(jsonstring);
		} catch (Exception e) {
			responseErrorData("JSON格式错误", resp);
		}
		// 获取手机号码
		String mobile = json.optString("mobile");
		// 手机验证码
		String secret = json.optString("secret");
		// 新的密码
		String pwd = json.optString("pwd");
		// 获取验证码
		UserSecret userSecret = userService.getUserSecretByMobile(mobile);
		JSONObject resultObj = new JSONObject();
		// 判断验证码
		if (userSecret != null && userSecret.getSecret().equals(secret)) {
			//判断是否注册过
			User user=userService.getUserByMobile(mobile);
			if(user == null){
				responseErrorData("该手机未被注册,请先注册", resp);
				return;
			}
			//更改密码
			userService.updateUserPwdByUserId(pwd,user.getId());
			resultObj.put("error_code", 0);
			resultObj.put("error_msg", "重置密码成功");
			responseData(resultObj.toString(), resp);
		}else{
			responseErrorData("验证码错误", resp);
		}
			
	}
	
	
	
	

	/**
	 * 修改手机号  检查一
	 * @param req
	 * @param resp
	 * @throws Exception
	 * 用户id,mobile,secret,pwd,newMobile,identity
	 * 
	 */
	@RequestMapping(value = "/setMobileByUserid")
	public void setMobileByUserid(HttpServletRequest req,
			HttpServletResponse resp) throws Exception  {
		String jsonstring=req.getAttribute("data").toString();
		JSONObject json = null;
		try {
			json = new JSONObject(jsonstring);
		} catch (Exception e) {
			responseErrorData("JSON格式错误", resp);
		}
		// 获取手机号码
		String userid = json.optString("identity");
		String secret = json.getString("secret");
		String pwd = json.optString("pwd");
		String newmobile=json.optString("newmobile");
		// 获取验证码
		UserSecret userSecret = userService.getUserSecretByMobile(newmobile);
		JSONObject resultObj = new JSONObject();
		// 判断验证码
		if (userSecret != null && userSecret.getSecret().equals(secret)) {
			//判断新手机是否注册过
			User newuser=userService.getUserByMobile(newmobile);
			if(newuser != null){
				responseErrorData("该手机已被注册,请更换手机号", resp);
				return;
			}
			
			
			User user=userService.getUserByUserId(userid);
			if(user != null && user.getPwd().equals(pwd)){
				//更改手机号
				userService.updateUserMobileByUserId(newmobile,user.getId());
				resultObj.put("error_code", 0);
				resultObj.put("error_msg", "更换手机号成功");
				responseData(resultObj.toString(), resp);
			}else{
				responseErrorData("密码错误", resp);
			}
		}else{
			responseErrorData("验证码错误", resp);
		}
	}
	
	
	
	private void responseErrorData(String string, HttpServletResponse resp)
			throws Exception {

		JSONObject resultObj = new JSONObject();
		resultObj.put("error_code", 1);
		resultObj.put("error_msg", string);
		responseData(resultObj.toString(), resp);
	}
	
	private void responseData(String string, HttpServletResponse resp)
			throws Exception {
		responseDate("application/json;charset=UTF-8", string, resp);
	}

	private void responseDate(String contentType, String value,
			HttpServletResponse resp) throws Exception {
		resp.setContentType(contentType);
		resp.getWriter().write(value);
	}
}
