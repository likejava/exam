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
		Session session = HibernateSessionFactory.getSession();//���Session����
		Transaction  transaction = null;//����һ���������
		try{
			transaction = session.beginTransaction();//��������
			session.save(ExamStart);
			transaction.commit();//�ύ����
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//����ع�
		}
		HibernateSessionFactory.closeSession();//�ر�Session����
	}
	public int findExamStartCountByMobile(int examId , String mobile){
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from ExamStart where examId = ? and mobile = ? ");
		query.setInteger(0, examId);
		query.setString(1, mobile);
		List list = query.list();					//��ѯ������浽list��
		int count = list.size();
		HibernateSessionFactory.closeSession();//�ر�Session����
		return count;
	}
}