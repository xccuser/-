package com.elec.api.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiTest {

	private static String url="http://localhost:8080/ElecApi/";
	
	
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line+"\n";
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}


	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("Accept", " */*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/json");
			//contentType:"application/json"
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Host", "tk.accfun.com");

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line+"\n";

			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！"+e);
			e.printStackTrace();
		}
		//使用finally块来关闭输出流、输入流
		finally{
			try{
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String args[]){
		//test2("checkVersion");
		//test3("setPwd");
		test7("setTripInfo");
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*login*/
	public static void test1(String sts){
		String sr=ApiTest.sendPost(url+sts, "{\"mobile\":\"13812667134\",\"pwd\":\"12345s6271s\"}");
		System.out.println(sr);
	}
	/*版本更新   checkVersion*/
	public static void test2(String sts){
		String sr=ApiTest.sendPost(url+sts, "{\"channel\":\"0\",\"apkversion\":\"-1\"}");
		System.out.println(sr);
	}
	/*   密码修改 
	 * */
	public static void test3(String sts){
		String sr=ApiTest.sendPost(url+sts, "{\"mobile\":\"13812667134\",\"secret\":\"12345s6271s\",\"pwd\":\"12345s6271\"}");
		System.out.println(sr);
	}
	
	/*发送短信验证码测试*/
	public static void test4(String sts){
		String sr=ApiTest.sendPost(url+sts,"{\"mobile\":\"13095653650\"}");
		System.out.println(sr);
	}
	
	/*短信注册测试   */
	public static void test5(String sts){
		String sr=ApiTest.sendPost(url+sts,"{\"mobile\":\"18120040402\",\"pwd\":\"123123\","
				+ "\"identity\":320511199402113756,\"username\":\"test\",\"secret\":\"2345\"}");
		System.out.println(sr);
	}	
	
	/* 修改手机号     secret,pwd,newmobile,identity*/
	public static void test6(String sts){
		String sr=ApiTest.sendPost(url+sts,"{\"newmobile\":\"18120040403\",\"pwd\":\"123123\","
				+ "\"identity\":320511199402113756,\"secret\":\"0423\"}");
		System.out.println(sr);
	
	}

	/*轨迹注册新轨迹     userid,startaddress,endaddress,length,hastime,speed,start_time,end_time,lng,lat*/
	public static void test7(String sts){
		Date date=new Date();
		Date end_date=new Date();
		System.out.println(url+sts);
		String sr=ApiTest.sendPost(url+sts,"{\"userid\":\"3201511199402113756\",\"startaddress\":\"绍兴\","+
		"\"endaddress\":\"苏州\",\"length\":\"30km\",\"speed\":\"30km/h\",\"start_time\":\""+date.toString()+
		"\",\"end_time\":"+"\"10:30\",\"lnglat\":[{\"lng\":\"120.23232\",\"lat\":\"30.123131\"}]"+"}");
		String str1="{\"userid\":\"3201511199402113756\",\"startaddress\":\"绍兴\","+
				"\"endaddress\":\"苏州\",\"length\":\"30km\",\"speed\":\"30km/h\",\"start_time\":"+date.toString()+
				",\"end_time\":"+"\"10:30\",\"lnglat\":[{\"lng\":\"120.23232\",\"lat\":\"30.123131\"}]"+"}";
		System.out.println(sr+","+str1);
	}
	
	
}
