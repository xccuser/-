package com.xcc.api.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.xcc.api.constants.Constants;



public class ServerStartFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		StringBuilder builder = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					req.getInputStream(),"utf-8"));
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		req.setAttribute("data",builder.toString() );     
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		setCharacterEncoding(config);
	}
	
	private void setCharacterEncoding(FilterConfig config){
		String initCharset = config.getInitParameter("charSet");
		if(initCharset == null || initCharset.trim().length() == 0)
			return;
		Constants.CHARSET = initCharset;
	}
	
	
	/**
	 * 数据检验失败
	 * @param value
	 * @param appkey
	 * @param resp
	 * @throws Exception
	 */
	private void errorResponse(String value,String appkey,ServletResponse resp) 
			throws Exception{
		String result = "{\"errorcode\":\"1\""+",\"errormsg\":\""+value+"\"}";
		//result = encryptOrDecryptData(result,true);
		resp.setContentType("application/json; charset=UTF-8");
		resp.getWriter().write(result);
	}

}
