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
	private String ecid; 			// �����û����
	private String appid;	// �����û�����
	private String role;		// �����û���ɫ
	private StudentService studentService = 
		new StudentServiceImpl();//ѧ��ҵ���߼��������
	private TeacherService teacherService = 
		new TeacherServiceImpl();//��ʦҵ���߼��������

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
		if("student".equals(role)) {//�����ѧ����ݵ�¼
			if(studentService.allowLogin(ecid, appid)) {
				Student studentInfo = studentService.getStudentInfo(ecid);
				//����ѧ����¼��session��Χ
				Map session = ActionContext.getContext().getSession();
				session.put("studentInfo", studentInfo);
				return "studentSuccess";
			}else {
				addActionError("��ѧ����Ų����ڣ��������벻��ȷ!");
				return this.INPUT;
			}
		}else {
			if(teacherService.allowLogin(ecid, appid)) {
				Map session = ActionContext.getContext().getSession();
				session.put("ecid", ecid);
				session.put("appid", appid);
				return "teacherSuccess";
			}else {
				addActionError("�ý�ʦ��Ų����ڣ��������벻��ȷ!");
				return this.INPUT;
			}
		}
	}
}
