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
	 * ����������
	 * */
    public static String getRequestJSONString(HttpServletRequest request){
		
		String resultString = null;
		
		//1.ͨ��ServletActionContext��ȡrequest
		
		StringBuffer buffer = new StringBuffer();
		
		String line = "";
		
		BufferedReader bufferReader;
		
		try {
	    //2.request-->InputStream-->BufferedReader
			bufferReader = new BufferedReader(new InputStreamReader(
					request.getInputStream(),"utf-8"));
	    //3.BufferedReader-->���ж�ȡ��String
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
	 * ����������
	 * */
    public static String getRequestJSONString(){
		
		String resultString = null;
		
		//1.ͨ��ServletActionContext��ȡrequest
		HttpServletRequest request = ServletActionContext.getRequest();
		
		StringBuffer buffer = new StringBuffer();
		
		String line = "";
		
		BufferedReader bufferReader;
		
		try {
	    //2.request-->InputStream-->BufferedReader
			bufferReader = new BufferedReader(new InputStreamReader(
					request.getInputStream(),"utf-8"));
	    //3.BufferedReader-->���ж�ȡ��String
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
	 * ���ؽ��
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
	 * ���ؽ��
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
