package com.cmcc.ict.exam.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.cmcc.ict.exam.po.Examination;
import com.cmcc.ict.exam.po.Question;
import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;
import com.cmcc.ict.exam.util.PageResult;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.cmcc.ict.exam.service.ExaminationService;
import com.cmcc.ict.exam.service.ExaminationServiceImpl;
import com.cmcc.ict.exam.service.QuestionService;
import com.cmcc.ict.exam.service.QuestionServiceImpl;
import com.cmcc.ict.exam.service.SubjectService;
import com.cmcc.ict.exam.service.SubjectServiceImpl;

public class SubjectAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private int currentPage;
	private int examinationId;
	private Examination examination;
	private ExaminationService examService = new ExaminationServiceImpl();
	private List<Subject> subjects = new ArrayList<Subject>();
	private SubjectService service = new SubjectServiceImpl();
	private Subject subject = new Subject();
	private String message = null;
	private String subjectTitle;
	private String mobile;
	private File uploadFile;
	private int totalScore;
	private int questionId;
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	private List<Question> questions = new ArrayList<Question>();
	private QuestionService questionService = new QuestionServiceImpl();
	public String listByExamId(){
		if(null == subjectTitle || "".equals(subjectTitle)){
			Page page = new Page();
			page.setEveryPage(10);//每页显示10条记录
			page.setCurrentPage(currentPage);//设置当前页
			PageResult pageResult = service.querySubjectByPageAndExamId(page,examinationId);
			examination = examService.findExaminationByID(examinationId);
			subjects = pageResult.getList();//获得试题记录
			page = pageResult.getPage();//获得分页信息
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("page", page);
			return SUCCESS;
		}else{
			return this.search();
		}
		
	}
	public String toAdd(){
		return SUCCESS;
	}
	public String add(){
		if(service.saveSubject(subject)) {
			examService.updateTotalScoreAndSubNumByExamId(examinationId);
			return SUCCESS;
		}else {
			this.addActionError("该试题已经添加过了，请不要重复添加!");
			return INPUT;
		}
	}
	public String toUpdate(){
		subject = service.showSubjectParticular(subject.getSubjectID());
		return SUCCESS;
	}
	public String update(){
		service.updateSubject(subject);//更新
		examService.updateTotalScoreAndSubNumByExamId(examinationId);
		this.addActionMessage("更新成功!");
		return SUCCESS;
	}
	public String view(){
		subject = service.showSubjectParticular(subject.getSubjectID());
		return SUCCESS;
	}
	public String delete(){
		service.deleteSubject(subject.getSubjectID());
		examService.updateTotalScoreAndSubNumByExamId(examinationId);
		return SUCCESS;
	}
	public String search(){
		Page page = new Page();
		page.setEveryPage(10);//每页显示10条记录
		page.setCurrentPage(currentPage);//设置当前页
		PageResult pageResult = service.likeQueryBySubjectTitleAndExamId(subjectTitle.trim(),examinationId, page);
		subjects = pageResult.getList();//获得试题记录
		//给关键字标红
		for(Subject subject : subjects) {
			String newTitle = subject.getSubjectTitle().replaceAll(subjectTitle,
						"<font color='red'>" + subjectTitle + "</font>");
			subject.setSubjectTitle(newTitle);
		}
		
		page = pageResult.getPage();//获得分页信息
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		examination = examService.findExaminationByID(examinationId);
		examService.updateTotalScoreAndSubNumByExamId(examinationId);
		return SUCCESS;
	}
	public String toImport(){
		return SUCCESS;
	}
	public String importSubject(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String filePath = request.getSession().getServletContext().getRealPath("/")+"upload/upload.xls";
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
		 result = service.importSubject(filePath, examinationId);
		 examService.updateTotalScoreAndSubNumByExamId(examinationId);
		 request.setAttribute("result", result);
		return SUCCESS;
	}

	public String toQuestionList(){
		Map session = ActionContext.getContext().getSession();
		int ecid = Integer.parseInt((String)session.get("ecid"));
		String appid = session.get("appid").toString();
		Page page = new Page();
		page.setEveryPage(10);//每页显示10条记录
		page.setCurrentPage(currentPage);//设置当前页
		PageResult pageResult = questionService.queryQuestionByPage(page, ecid, appid);
		questions = pageResult.getList();//获得试题记录
		page = pageResult.getPage();//获得分页信息
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		return SUCCESS;
	}
	public void addToExam() throws IOException{
		Question question = questionService.showQuestionParticular(questionId);
		subject.setExamId(examinationId);
		subject.setSubjectTitle(question.getSubjectTitle());
		subject.setSubjectOptionA(question.getSubjectOptionA());
		subject.setSubjectOptionB(question.getSubjectOptionB());
		subject.setSubjectOptionC(question.getSubjectOptionC());
		subject.setSubjectOptionD(question.getSubjectOptionD());
		subject.setSubjectAnswer(question.getSubjectAnswer());
		subject.setSubjectParse(question.getSubjectParse());
		subject.setScore(question.getScore());
		String msg="";
		if(service.saveSubject(subject)){
			examService.updateTotalScoreAndSubNumByExamId(examinationId);
			msg="success";
		}else {
			msg="exists";
		}
		PrintWriter out = ServletActionContext.getResponse().getWriter();  
	    out.print(msg);  
	    out.flush();  
	    out.close();  
	}
	public String addRandomSubject(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String subjectNum = request.getParameter("subjectNum");
		int succCount = service.addRandomSubject(examinationId, Integer.parseInt(subjectNum));
		int existsCount = Integer.parseInt(subjectNum) - succCount;
		message = "成功生成"+succCount+"道试题，"+ existsCount + "道试题已存在";
		examService.updateTotalScoreAndSubNumByExamId(examinationId);
		return SUCCESS;
	}
	public String questionListSearch(){
		Map session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		int ecid = Integer.parseInt((String)session.get("ecid"));
		String appid = session.get("appid").toString();
		String title =  request.getParameter("title");
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
		
		request.setAttribute("page", page);
		request.setAttribute("title", title);
		return SUCCESS;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	public int getExaminationId() {
		return examinationId;
	}
	public void setExaminationId(int examinationId) {
		this.examinationId = examinationId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubjectTitle() {
		return subjectTitle;
	}
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Examination getExamination() {
		return examination;
	}
	public void setExamination(Examination examination) {
		this.examination = examination;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
