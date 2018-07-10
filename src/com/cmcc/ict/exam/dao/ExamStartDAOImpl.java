package com.cmcc.ict.exam.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cmcc.ict.exam.hibernate.HibernateSessionFactory;
import com.cmcc.ict.exam.po.ExamStart;
import com.cmcc.ict.exam.po.Examination;
import com.cmcc.ict.exam.util.Page;

public class ExamStartDAOImpl implements ExamStartDAO {
	public void addExamStart(ExamStart ExamStart){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			session.save(ExamStart);
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
	}
	public int findExamStartCountByMobile(int examId , String mobile){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from ExamStart where examId = ? and mobile = ? ");
		query.setInteger(0, examId);
		query.setString(1, mobile);
		List list = query.list();					//查询结果保存到list中
		int count = list.size();
		HibernateSessionFactory.closeSession();//关闭Session对象
		return count;
	}
}