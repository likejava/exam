package com.cmcc.ict.exam.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

public class RequestResponseJson {

	/**
	 * 解析请求报文
	 * */
    public static String getRequestJSONString(HttpServletRequest request){
		
		String resultString = null;
		
		//1.通过ServletActionContext获取request
		
		StringBuffer buffer = new StringBuffer();
		
		String line = "";
		
		BufferedReader bufferReader;
		
		try {
	    //2.request-->InputStream-->BufferedReader
			bufferReader = new BufferedReader(new InputStreamReader(
					request.getInputStream(),"utf-8"));
	    //3.BufferedReader-->逐行读取到String
			while ((line = bufferReader.readLine()) != null)
				buffer.append(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resultString = buffer.toString();
		
		return resultString;
	}
	
	/**
	 * 解析请求报文
	 * */
    public static String getRequestJSONString(){
		
		String resultString = null;
		
		//1.通过ServletActionContext获取request
		HttpServletRequest request = ServletActionContext.getRequest();
		
		StringBuffer buffer = new StringBuffer();
		
		String line = "";
		
		BufferedReader bufferReader;
		
		try {
	    //2.request-->InputStream-->BufferedReader
			bufferReader = new BufferedReader(new InputStreamReader(
					request.getInputStream(),"utf-8"));
	    //3.BufferedReader-->逐行读取到String
			while ((line = bufferReader.readLine()) != null)
				buffer.append(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resultString = buffer.toString();
		
		return resultString;
	}
    
    
    /**
	 * 返回结果
	 * */
    public static void response(Object data) {
		String resultJSONStr = "";
		if (data instanceof List) {
			JSONArray resultJSON = JSONArray.fromObject(data);
			resultJSONStr = resultJSON.toString();
		} else {
			JSONObject resultJSON = JSONObject.fromObject(data);
			resultJSONStr = resultJSON.toString();
		}

		try {

			HttpServletResponse response = ServletActionContext.getResponse();

			response.setContentType("text/html;charset=UTF-8");

			response.getWriter().write(resultJSONStr);

		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
    
    /**
	 * 返回结果
	 * */
    public static void response(HttpServletResponse response , Object data) {
		String resultJSONStr = "";
		if (data instanceof List) {
			JSONArray resultJSON = JSONArray.fromObject(data);
			resultJSONStr = resultJSON.toString();
		} else {
			JSONObject resultJSON = JSONObject.fromObject(data);
			resultJSONStr = resultJSON.toString();
		}

		try {
			
			response.setContentType("text/html;charset=UTF-8");

			response.getWriter().write(resultJSONStr);

		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
}
