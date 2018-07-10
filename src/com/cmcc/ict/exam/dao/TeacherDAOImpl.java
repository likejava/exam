package com.cmcc.ict.exam.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cmcc.ict.exam.hibernate.HibernateSessionFactory;
import com.cmcc.ict.exam.po.Teacher;

public class TeacherDAOImpl implements TeacherDAO{
	public Teacher findByEcid(String ecid) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		
		Query query = session.createQuery("from Teacher where ecid = ?");
		query.setString(0, ecid);
		List list = query.list();
		Teacher teacher = (Teacher)list.get(0);
		
		HibernateSessionFactory.closeSession();//关闭Session对象
		return teacher;
	}
}
