package com.cmcc.ict.exam.dao;

import com.cmcc.ict.exam.po.Teacher;

public interface TeacherDAO {
	public Teacher findByEcid(String ecid);//查询方法，根据教师ID查询
}
