package com.cmcc.ict.exam.service;

import com.cmcc.ict.exam.dao.TeacherDAO;
import com.cmcc.ict.exam.dao.TeacherDAOImpl;
import com.cmcc.ict.exam.po.Teacher;

public class TeacherServiceImpl implements TeacherService{
	private TeacherDAO teacherDAO = new TeacherDAOImpl();
	
	public boolean allowLogin(String ecid, String appid) {
		Teacher teacher = teacherDAO.findByEcid(ecid);
		if(teacher == null) {//�ж��Ƿ���ڸ�ID�Ľ�ʦ
			return false;
		}else {
			if(appid.equals(teacher.getAppid())) {//�ж������Ƿ���ͬ
				return true;
			}else{
				return false;
			}
		}
	}
}
