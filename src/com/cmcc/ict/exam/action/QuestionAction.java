package com.cmcc.ict.exam.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.cmcc.ict.exam.po.Question;
import com.cmcc.ict.exam.service.QuestionService;
import com.cmcc.ict.exam.service.QuestionServiceImpl;
import com.cmcc.ict.exam.util.Page;
import com.cmcc.ict.exam.util.PageResult;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QuestionAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private int currentPage;
	private List<Question> questions = new ArrayList<Question>();
	private String title;
	private Question question;
	private File uploadFile;
	private int ecid;
	public int getEcid() {
		return ecid;
	}
	public void setEcid(int ecid) {
		this.ecid = ecid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	private String appid;
	private QuestionService questionService = new QuestionServiceImpl();
	public String list(){
		Map session = ActionContext.getContext().getSession();
		ecid = Integer.parseInt((String)session.get("ecid"));
		appid = session.get("appid").toString();
		if(null == title || "".equals(title)){
			Page page = new Page();
			page.setEveryPage(10);//每页显示10条记录
			page.setCurrentPage(currentPage);//设置当前页
			PageResult pageResult = questionService.queryQuestionByPage(page, ecid, appid);
			questions = pageResult.getList();//获得试题记录
			page = pageResult.getPage();//获得分页信息
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("page", page);
			return SUCCESS;
		}else{
			return this.search();
		}
		
	}
	public String search(){
		Map session = ActionContext.getContext().getSession();
		ecid = Integer.parseInt((String)session.get("ecid"));
		appid = session.get("appid").toString();
		Page page = new Page();
		page.setEveryPage(10);//每页显示10条记录
		page.setCurrentPage(currentPage);//设置当前页
		PageResult pageResult = questionService.likeQueryByTitle(title.trim(),ecid,appid, page);
		questions = pageResult.getList();//获得试题记录
		//给关键字标红
		for(Question question : questions) {
			String newTitle = question.getSubjectTitle().replaceAll(title,
						"<font color='red'>" + title + "</font>");
			question.setSubjectTitle(newTitle);
		}
		page = pageResult.getPage();//获得分页信息
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		return SUCCESS;
	}
	public String toAdd(){
		return SUCCESS;
	}
	public String add(){
		if(questionService.saveQuestion(question)) {
			return SUCCESS;
		}else {
			this.addActionError("该试题已经添加过了，请不要重复添加!");
			return INPUT;
		}
	}
	public String toUpdate(){
		question = questionService.showQuestionParticular(question.getId());
		return SUCCESS;
	}
	public String update(){
		questionService.updateQuestion(question);//更新
		this.addActionMessage("更新成功!");
		return SUCCESS;
	}
	public String view(){
		question = questionService.showQuestionParticular(question.getId());
		return SUCCESS;
	}
	public String delete(){
		questionService.deleteQuestion(question.getId());
		return SUCCESS;
	}
	public String toImport(){
		return SUCCESS;
	}
	public String importSubject(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Map session = ActionContext.getContext().getSession();
		ecid = Integer.parseInt((String)session.get("ecid"));
		appid = session.get("appid").toString();
		String filePath = request.getSession().getServletContext().getRealPath("/")+"upload/questionupload.xls";
		 if(uploadFile != null)
         {
             File savefile=new File(filePath);
             if(!savefile.getParentFile().exists())//如果不存在创建
             {
                 savefile.getParentFile().mkdirs();//创建
             }
             try {
				FileUtils.copyFile(uploadFile,savefile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
		 HashMap result = new HashMap();
		 result = questionService.importQuestion(filePath, ecid, appid);
		 request.setAttribute("result", result);
		return SUCCESS;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	
}
