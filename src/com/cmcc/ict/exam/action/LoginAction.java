package com.cmcc.ict.exam.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cmcc.ict.exam.po.Student;
import com.cmcc.ict.exam.service.StudentService;
import com.cmcc.ict.exam.service.StudentServiceImpl;
import com.cmcc.ict.exam.service.TeacherService;
import com.cmcc.ict.exam.service.TeacherServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private String ecid; 			// 接受用户编号
	private String appid;	// 接受用户密码
	private String role;		// 接受用户角色
	private StudentService studentService = 
		new StudentServiceImpl();//学生业务逻辑组件引用
	private TeacherService teacherService = 
		new TeacherServiceImpl();//教师业务逻辑组件引用

	public String getEcid() {
		return ecid;
	}

	public void setEcid(String ecid) {
		this.ecid = ecid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String execute() throws Exception {
		if("student".equals(role)) {//如果以学生身份登录
			if(studentService.allowLogin(ecid, appid)) {
				Student studentInfo = studentService.getStudentInfo(ecid);
				//保存学生记录到session范围
				Map session = ActionContext.getContext().getSession();
				session.put("studentInfo", studentInfo);
				return "studentSuccess";
			}else {
				addActionError("该学生编号不存在，或者密码不正确!");
				return this.INPUT;
			}
		}else {
			if(teacherService.allowLogin(ecid, appid)) {
				Map session = ActionContext.getContext().getSession();
				session.put("ecid", ecid);
				session.put("appid", appid);
				return "teacherSuccess";
			}else {
				addActionError("该教师编号不存在，或者密码不正确!");
				return this.INPUT;
			}
		}
	}
}
