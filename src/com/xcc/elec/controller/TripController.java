/**
 * 
 */
package com.xcc.elec.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xcc.elec.service.TripService;

/**
 * 
 *  Administrator
 *  
 */


@Controller
public class TripController {

	private TripService tripService;
	
	@Autowired
	public void setTripService(TripService tripService) {
		this.tripService = tripService;
	}
	
	
	
	/*
	 * 轨迹上传
	 * */
	@RequestMapping(value = "/setTripInfo")
	public void setTripInfo(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		String jsonstring=req.getAttribute("data").toString();
		JSONObject inObj = null;
		try {
			inObj = new JSONObject(jsonstring);
		} catch (Exception e) {
			responseErrorData("JSON格式错误", resp);
		}
		String userid=inObj.optString("userid");
		String startaddress=inObj.optString("startaddress");
		String endaddress=inObj.optString("endaddress");
		String length=inObj.optString("length");
		String hastime=inObj.optString("hastime");
		String speed=inObj.optString("speed");
		String starttime=inObj.optString("start_time");
		String endtime=inObj.optString("end_time");
		JSONArray lnglat=inObj.optJSONArray("lnglat");
		//添加新的行程
		/*long tripid=*/tripService.insertNewTripInfo(userid,startaddress,endaddress,length,hastime,speed,starttime,endtime);
		//添加新的行程LNG LAT记录
		/*JSONObject onejson = null;
		for(int i=0;i<lnglat.length();i++){
			onejson = lnglat.getJSONObject(i);
			String lng=onejson.optString("lng");
			String lat=onejson.optString("lat");
			tripService.insertNewTripList(tripid,lng,lat);
		}*/
		JSONObject resultObj = new JSONObject();
		resultObj.put("error_code", 0);
		resultObj.put("error_msg", "上传成功");
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
	
	private void responseErrorData(String string, HttpServletResponse resp)
			throws Exception {
		JSONObject resultObj = new JSONObject();
		resultObj.put("error_code", 1);
		resultObj.put("error_msg", string);
		responseData(resultObj.toString(), resp);
	}
	
}
