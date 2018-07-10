package com.cmcc.ict.exam.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cmcc.ict.exam.hibernate.HibernateSessionFactory;
import com.cmcc.ict.exam.po.ExamResult;
import com.cmcc.ict.exam.po.Examination;
import com.cmcc.ict.exam.util.Page;

public class ExamResultDAOImpl implements ExamResultDAO {
	public void addExamResult(ExamResult examResult){
		Session session = HibernateSessionFactory.getSession();//���Session����
		Transaction  transaction = null;//����һ���������
		try{
			transaction = session.beginTransaction();//��������
			session.save(examResult);
			transaction.commit();//�ύ����
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//����ع�
		}
		HibernateSessionFactory.closeSession();//�ر�Session����
	}
	public List<ExamResult> findExamResultsByExamIdByPage(int examId,Page page ){
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from ExamResult where examId = ? order by score desc");
		query.setInteger(0, examId);
		query.setMaxResults(page.getEveryPage());//���ò�ѯ��¼��
		query.setFirstResult(page.getBeginIndex());//���ò�ѯ��¼��ʼλ��
		List list = query.list();					//��ѯ������浽list��
		HibernateSessionFactory.closeSession();	//�ر�Session����
		return list;
	}
	public int findExamResultCount(int examId){
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from ExamResult where examId = ? ");
		query.setInteger(0, examId);
		List list = query.list();					//��ѯ������浽list��
		int count = list.size();
		HibernateSessionFactory.closeSession();//�ر�Session����
		return count;
	}
	public int findExamResultCountByMobile(int examId , String mobile){
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from ExamResult where examId = ? and mobile = ? ");
		query.setInteger(0, examId);
		query.setString(1, mobile);
		List list = query.list();					//��ѯ������浽list��
		int count = list.size();
		HibernateSessionFactory.closeSession();//�ر�Session����
		return count;
	}
	public List<ExamResult> findExamResultsByExamIdAndMobileByPage(int examId, String mobile, Page page){
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from ExamResult where examId = ? and mobile = ? order by createTime desc");
		query.setInteger(0, examId);
		query.setString(1, mobile);
		query.setMaxResults(page.getEveryPage());//���ò�ѯ��¼��
		query.setFirstResult(page.getBeginIndex());//���ò�ѯ��¼��ʼλ��
		List list = query.list();					//��ѯ������浽list��
		HibernateSessionFactory.closeSession();	//�ر�Session����
		return list;
	}
	public List<ExamResult> queryResult(String appId, String ecId, String mobile){
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("select er from ExamResult as er, Examination as e  where er.examId = e.id and e.appid = ? "
				+ "and e.ecid = ? and er.mobile = ?");
		query.setString(0, appId);
		query.setString(1, ecId);
		query.setString(2, mobile);
		List list = query.list();					//��ѯ������浽list��
		HibernateSessionFactory.closeSession();	//�ر�Session����
		return list;
	}
	public List<ExamResult> findExamResultsByExamId(int examId){
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from ExamResult where examId = ? order by score desc");
		query.setInteger(0, examId);
		List list = query.list();					//��ѯ������浽list��
		HibernateSessionFactory.closeSession();	//�ر�Session����
		return list;
	}
}