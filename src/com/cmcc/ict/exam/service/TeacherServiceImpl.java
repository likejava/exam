package com.cmcc.ict.exam.service;

import com.cmcc.ict.exam.dao.TeacherDAO;
import com.cmcc.ict.exam.dao.TeacherDAOImpl;
import com.cmcc.ict.exam.po.Teacher;

public class TeacherServiceImpl implements TeacherService{
	private TeacherDAO teacherDAO = new TeacherDAOImpl();
	
	public boolean allowLogin(String ecid, String appid) {
		Teacher teacher = teacherDAO.findByEcid(ecid);
		if(teacher == null) {//判断是否存在该ID的教师
			return false;
		}else {
			if(appid.equals(teacher.getAppid())) {//判断密码是否相同
				return true;
			}else{
				return false;
			}
		}
	}
}
