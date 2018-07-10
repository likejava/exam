package com.cmcc.ict.exam.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.cmcc.ict.exam.po.ExamResult;
import com.cmcc.ict.exam.po.Examination;
import com.cmcc.ict.exam.po.Result;
import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.service.ExaminationService;
import com.cmcc.ict.exam.service.ExaminationServiceImpl;
import com.cmcc.ict.exam.service.SubjectService;
import com.cmcc.ict.exam.service.SubjectServiceImpl;
import com.cmcc.ict.exam.util.ConfigUtil;
import com.cmcc.ict.exam.util.Page;
import com.cmcc.ict.exam.util.PageResult;
import com.opensymphony.xwork2.ActionSupport;

public class ExaminationAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private Examination examination;
	private String message;
	private int currentPage;
	private String mobile;
	private int examinationId;
	private List<Result> resultList = new ArrayList<Result>();
	private List<Subject> subjects = new ArrayList<Subject>();
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	private List<Examination> examinations = new ArrayList<Examination>();
	private ExaminationService service = new ExaminationServiceImpl();
	private SubjectService subjectService = new SubjectServiceImpl();
	public String list(){
		HttpSession session = ServletActionContext.getRequest().getSession();
        String ecid = (String)session.getAttribute("ecid");
        String appid = (String)session.getAttribute("appid");
		Page page = new Page();
		page.setEveryPage(10);//每页显示10条记录
		page.setCurrentPage(currentPage);//设置当前页
		PageResult pageResult = service.queryExaminationByPage(page, Integer.valueOf(ecid), appid);
		examinations = pageResult.getList();//获得试题记录
		page = pageResult.getPage();//获得分页信息
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		return SUCCESS;
	}
	public String listForResult(){
		HttpSession session = ServletActionContext.getRequest().getSession();
        String ecid = (String)session.getAttribute("ecid");
        String appid = (String)session.getAttribute("appid");
		Page page = new Page();
		page.setEveryPage(10);//每页显示10条记录
		page.setCurrentPage(currentPage);//设置当前页
		PageResult pageResult = service.queryExaminationByPage(page, Integer.valueOf(ecid), appid);
		examinations = pageResult.getList();//获得试题记录
		page = pageResult.getPage();//获得分页信息
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		return SUCCESS;
	}
	public String add(){
		// 从session里取登录信息
		HttpSession session = ServletActionContext.getRequest().getSession();
        String ecid = (String)session.getAttribute("ecid");
        String appid = (String)session.getAttribute("appid");
        examination.setEcid(Integer.valueOf(ecid));
        examination.setAppid(appid);
		examination.setCreateTime(new Date());
		String ip = ConfigUtil.getPublicIp();
		String port = ConfigUtil.getPublicPort();
		String url = "http://"+ ip + ":" + port + "/exam/";
		String resPage = "";
		if(examination.getType().equals("1")){
			url += "generalExamination.action?examId=";
			examination.setUrl(url);
			examination = service.saveExamination(examination);
			if(examination != null) {
				Page page = new Page();
				page.setEveryPage(10);//每页显示10条记录
				page.setCurrentPage(currentPage);//设置当前页
				PageResult pageResult = subjectService.querySubjectByPageAndExamId(page,examination.getId());
				subjects = pageResult.getList();//获得试题记录
				examinationId = examination.getId();
				resPage = "toSubjectList";
			}
		} else {
			url += "randomExamination.action?examId=";
			examination.setUrl(url);
			examination = service.saveExamination(examination);
			if(examination != null) {
				resPage = "toExamList";
			}
		}
		if(examination == null) {
			this.addActionError("该试卷已经添加过了，请不要重复添加!");
			resPage = INPUT;
		}
		return resPage;
	}
	public String delete(){
		service.deleteExamination(examination.getId()); //删除tb_examination表
		service.deleteResultByExaminationId(examination.getId()); //删除tb_result与examination关联的内容
		service.deleteExamresultByExaminationId(examination.getId()); //删除tb_examresult与examination关联的内容
		return SUCCESS;
	}
	public String toUpdate(){
		examination = service.findExaminationByID(examination.getId());
		return SUCCESS;
	}
	public String update(){
		service.updateExaminationTitle(examination.getId(),examination.getTitle());
		return SUCCESS;
	}
	public String submitExam(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(resultList == null || resultList.isEmpty())
		{
			return "empty";  //试题内容为空，请编辑试题
		}
		else
		{
			mobile = resultList.get(0).getUser();
			int examId = resultList.get(0).getExamId();
			Examination exam = service.findExaminationByID(examId);
			if(exam!=null&&!"1".equals(exam.getIsRepeat())&&service.findResultByUser(mobile, examId)){
				return "already";
			}else{
				ExamResult examResult = service.saveResult(resultList); 
				request.setAttribute("score", examResult.getScore());
				return SUCCESS;
			}
		}
	}
	public String getGeneralSubjects(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String examId = request.getParameter("examId");
		subjects = service.queryAllSubjectsByExamId(Integer.parseInt(examId));//获得试题记录
		return SUCCESS;
	}
	public String getRandomSubjects(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int examId = Integer.parseInt(request.getParameter("examId"));
		String mobile = request.getParameter("mobile");
		String name = request.getParameter("name")==null?"":request.getParameter("name");
		try{
			name = URLDecoder.decode(name, "UTF-8");
		}catch(IOException e){
			e.printStackTrace();
		}
		if(mobile!=null&&!"".equals(mobile)){
			int flag = service.findExamStartCountByMobile(examId,mobile);
			if (flag == 0) {
				service.saveExamStart(examId, mobile, name);
			}
		}
		subjects = service.queryRandomSubjectsByExamId(examId);//获得试题记录
		return SUCCESS;
	}
	public void checkExamStatus() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		int examId = Integer.parseInt(request.getParameter("examId"));
		String mobile = request.getParameter("mobile");
		boolean flag = service.findResultByUser(mobile, examId);
		if (flag) {
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String jsonString="{\"result_code\":\"0\",\"result_status\":\"1\"}";
			out.println(jsonString);  
		    out.flush();  
		    out.close();
		} else {
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String jsonString="{\"result_code\":\"0\",\"result_status\":\"0\"}";
			out.println(jsonString);  
		    out.flush();  
		    out.close();
		}
	}
	
	public Examination getExamination() {
		return examination;
	}
	public void setExamination(Examination examination) {
		this.examination = examination;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Examination> getExaminations() {
		return examinations;
	}
	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<Result> getResultList() {
		return resultList;
	}
	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getExaminationId() {
		return examinationId;
	}
	public void setExaminationId(int examinationId) {
		this.examinationId = examinationId;
	}
}
