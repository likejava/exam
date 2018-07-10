package com.cmcc.ict.exam.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.cmcc.ict.exam.po.ExamResult;
import com.cmcc.ict.exam.po.Examination;
import com.cmcc.ict.exam.po.Result;
import com.cmcc.ict.exam.service.ExaminationService;
import com.cmcc.ict.exam.service.ExaminationServiceImpl;
import com.cmcc.ict.exam.service.ResultService;
import com.cmcc.ict.exam.service.ResultServiceImpl;
import com.cmcc.ict.exam.util.Page;
import com.cmcc.ict.exam.util.PageResult;
import com.cmcc.ict.exam.util.RequestResponseJson;
import com.opensymphony.xwork2.ActionSupport;
public class ResultAction  extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ExamResult> examResultList = new ArrayList<ExamResult>();
	private List<Object> resultDetailList = new ArrayList<Object>();
	private Examination examination ;
	private ResultService resultService = new ResultServiceImpl();
	private ExaminationService examService = new ExaminationServiceImpl();
	private String mobile;
	private int currentPage;

	public String listExamResults(){
		if(null == mobile || "".equals(mobile)){
			Page page = new Page();
			page.setEveryPage(10);//每页显示10条记录
			page.setCurrentPage(currentPage);//设置当前页
			examination = examService.findExaminationByID(examination.getId());
			PageResult pageResult = resultService.listExamResultsByExamIdByPage(examination.getId(),page);
			examResultList = pageResult.getList();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("page", pageResult.getPage());
			return SUCCESS;
		}else{
			return this.search();
		}
	}
	public String resultDetail(){
		Examination examTmp = new Examination();
		examTmp = examService.findExaminationByID(examination.getId());
		resultDetailList = resultService.getResultDetailListByExamIdAndMobile(examination.getId(), mobile, examTmp.getType());
		return SUCCESS;
	}
	public String search(){
		Page page = new Page();
		page.setEveryPage(20);//每页显示10条记录
		page.setCurrentPage(1);//设置当前页
		examination = examService.findExaminationByID(examination.getId());
		PageResult pageResult = null;
		if("".equals(mobile)){
			pageResult = resultService.listExamResultsByExamIdByPage(examination.getId(),page);
		}else {
			pageResult = resultService.SearchByMoblieByPage(examination.getId(),mobile,page);
		}
		examResultList = pageResult.getList();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", pageResult.getPage());
		return SUCCESS;
	}
	public void queryResult() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		// 1.接收参数
		String reqStr = RequestResponseJson.getRequestJSONString(request);
		JSONObject json = JSONObject.fromObject(reqStr);
		String appId = json.getString("appid");
		String ecId = json.getString("ecid");
		String mobile = json.getString("userid");
		
//		String appId = request.getParameter("appid");
//		String ecId = request.getParameter("ecid");
//		String mobile = request.getParameter("userid");
		
		List examResuts = resultService.queryResult(appId, ecId, mobile);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		Map<Object, Object> resMap = new HashMap<Object, Object>();
		String resultcode = "0";
		String resultmsg = "success";
		resMap.put("resultcode",resultcode);
		resMap.put("resultmsg", resultmsg);
		resMap.put("resultlist", examResuts);
		JSONObject resultJSON = JSONObject.fromObject(resMap);
		String out = resultJSON.toString();
		response.getWriter().print(out);
	}
	public void queryExamDetail() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String appId = request.getParameter("appid");
		String ecId = request.getParameter("ecid");
		String mobile = request.getParameter("userid");
		List<Map<String,String>> resList = new ArrayList<Map<String, String>> ();
		resList = resultService.queryExamDetailList(appId, ecId, mobile);
		Map<Object, Object> resMap = new HashMap<Object, Object>();
		String resultcode = "0";
		String resultmsg = "成功";
		resMap.put("resultcode",resultcode);
		resMap.put("resultmsg", resultmsg);
		resMap.put("resultlist", resList);
		HttpServletResponse response=ServletActionContext.getResponse();  
		response.setContentType("text/html;charset=UTF-8"); 
		JSONArray resultJSON = JSONArray.fromObject(resMap);
		String out = resultJSON.toString();
		response.getWriter().print(out);
		
	}
	public List<ExamResult> getExamResultList() {
		return examResultList;
	}
	public void setExamResultList(List<ExamResult> examResultList) {
		this.examResultList = examResultList;
	}
	public Examination getExamination() {
		return examination;
	}
	public void setExamination(Examination examination) {
		this.examination = examination;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public List<Object> getResultDetailList() {
		return resultDetailList;
	}
	public void setResultDetailList(List<Object> resultDetailList) {
		this.resultDetailList = resultDetailList;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}	
}
