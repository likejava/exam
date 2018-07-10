package com.cmcc.ict.exam.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.service.SubjectService;
import com.cmcc.ict.exam.service.SubjectServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

/*
 * ����������
 */
public class GetRandomSubject extends ActionSupport{
	private SubjectService subjectService = new SubjectServiceImpl();
	public String execute() throws Exception {
		List<Subject> subjects = subjectService.randomFindSubject(20);//��������¼
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("subjects", subjects);
		return SUCCESS;
	}
}
