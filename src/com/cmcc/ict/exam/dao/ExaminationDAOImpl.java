package com.cmcc.ict.exam.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cmcc.ict.exam.hibernate.HibernateSessionFactory;
import com.cmcc.ict.exam.po.Examination;
import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;

public class ExaminationDAOImpl implements ExaminationDAO {
	public Examination addExamination(Examination exam){
		Session session = HibernateSessionFactory.getSession();//���Session����
		Transaction  transaction = null;//����һ���������
		try{
			transaction = session.beginTransaction();//��������
			session.save(exam);//�����Ծ���Ϣ
			exam.setUrl(exam.getUrl()+exam.getId());
			session.update(exam);
			transaction.commit();//�ύ����
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//����ع�
		}
		HibernateSessionFactory.closeSession();//�ر�Session����
		return exam;
	}
	public Examination findExaminationByTitle(String title, Integer ecid, String appid) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from Examination as exam where exam.title = ? and exam.ecid = ? and exam.appid = ?");
		query.setString(0, title);
		query.setInteger(1, ecid);
		query.setString(2, appid);
		List list = query.list();					//��ѯ������浽list��
		HibernateSessionFactory.closeSession();		//�ر�Session����
		if(list.size() == 0) {
			return null;							//����null
		}else {
			return (Examination) list.get(0);			//���ص�һ���Ծ�
		}
	}
	public List<Examination> findExaminationByPage(Page page, Integer ecid, String appid) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from Examination as exam where exam.ecid = ? and exam.appid = ? order by createTime desc");
		query.setInteger(0, ecid);
		query.setString(1, appid);
		query.setMaxResults(page.getEveryPage());//���ò�ѯ��¼��
		query.setFirstResult(page.getBeginIndex());//���ò�ѯ��¼��ʼλ��
		List list = query.list();					//��ѯ������浽list��
		HibernateSessionFactory.closeSession();//�ر�Session����
		return list;
	}
	public int findExaminationCount(Integer ecid, String appid) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from Examination as exam where exam.ecid = ? and exam.appid = ?");
		query.setInteger(0, ecid);
		query.setString(1, appid);
		List list = query.list();					//��ѯ������浽list��
		int count = list.size();
		HibernateSessionFactory.closeSession();//�ر�Session����
		return count;
	}
	public void deleteExamination(int id) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Examination examination = (Examination) session.get(Examination.class, id);
		Transaction  transaction = null;//����һ���������
		try{
			transaction = session.beginTransaction();//��������
			session.delete(examination);
			Query query = session.createQuery("delete from Subject as sub where sub.examId = ? ");
			query.setInteger(0,id);
			query.executeUpdate();
			transaction.commit();//�ύ����
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//����ع�
		}
		HibernateSessionFactory.closeSession();//�ر�Session����
		
	}
	public void deleteResultByExaminationId(int id){
		Session session = HibernateSessionFactory.getSession();//���Session����
		Transaction  transaction = null;//����һ���������
		try{
			transaction = session.beginTransaction();//��������
			Query query = session.createQuery("delete from Result as res where res.examId = ? ");
			query.setInteger(0,id);
			query.executeUpdate();
			transaction.commit();//�ύ����
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//����ع�
		}
		HibernateSessionFactory.closeSession();//�ر�Session����
	}
	public void deleteExamresultByExaminationId(int id){
		Session session = HibernateSessionFactory.getSession();//���Session����
		Transaction  transaction = null;//����һ���������
		try{
			transaction = session.beginTransaction();//��������
			Query query = session.createQuery("delete from ExamResult as er where er.examId = ? ");
			query.setInteger(0,id);
			query.executeUpdate();
			transaction.commit();//�ύ����
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//����ع�
		}
		HibernateSessionFactory.closeSession();//�ر�Session����
	}
	@Override
	public Examination findExaminationByID(int id) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Examination exam = (Examination) session.get(Examination.class, id);
		HibernateSessionFactory.closeSession();		//�ر�Session����
		return exam;
	}
	@Override
	public void updateExaminationTitle(int id, String title) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Examination examination = (Examination) session.get(Examination.class, id);
		Transaction  transaction = null;//����һ���������
		try{
			transaction = session.beginTransaction();//��������
			Query query = session.createQuery("update Examination exam set title = ? where id = ?");
			query.setInteger(1,id);
			query.setString(0,title);
			query.executeUpdate();
			transaction.commit();//�ύ����
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//����ع�
		}
		HibernateSessionFactory.closeSession();//�ر�Session����
	}
	public void updateTotalScoreAndSubNum(int id, int totalScore, int subjectNum){
		Session session = HibernateSessionFactory.getSession();//���Session����
		Examination examination = (Examination) session.get(Examination.class, id);
		Transaction  transaction = null;//����һ���������
		try{
			transaction = session.beginTransaction();//��������
			Query query = session.createQuery("update Examination exam set totalscore = ?,subjectnum = ?  where id = ?");
			query.setInteger(0,totalScore);
			query.setInteger(1,subjectNum);
			query.setInteger(2,id);
			query.executeUpdate();
			transaction.commit();//�ύ����
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//����ع�
		}
		HibernateSessionFactory.closeSession();//�ر�Session����
	}
}