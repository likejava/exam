package com.cmcc.ict.exam.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cmcc.ict.exam.hibernate.HibernateSessionFactory;
import com.cmcc.ict.exam.po.Question;
import com.cmcc.ict.exam.po.Student;
import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;

public class QuestionDAOImpl implements QuestionDAO{
	public int addQuestion(Question question){
		int result = -1;
		Session session = HibernateSessionFactory.getSession();//���Session����
		Transaction  transaction = null;//����һ���������
		try{
			transaction = session.beginTransaction();//��������
			result = (Integer)session.save(question);//����������Ϣ
			transaction.commit();//�ύ����
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//����ع�
		}
		HibernateSessionFactory.closeSession();//�ر�Session����
		return result;
	}
	public List<Question> findQuestionByPage(int ecid, String appid,Page page) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from Question where ecid = ? and appid = ?");
		query.setInteger(0, ecid);
		query.setString(1, appid);
		query.setMaxResults(page.getEveryPage());//���ò�ѯ��¼��
		query.setFirstResult(page.getBeginIndex());//���ò�ѯ��¼��ʼλ��
		List list = query.list();					//��ѯ������浽list��
		HibernateSessionFactory.closeSession();//�ر�Session����
		return list;
	}
	public int findQuestionCount(int ecid, String appid) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from Question where ecid = ? and appid = ?");
		query.setInteger(0, ecid);
		query.setString(1, appid);
		List list = query.list();					//��ѯ������浽list��
		int count = list.size();
		HibernateSessionFactory.closeSession();//�ر�Session����
		return count;
	}

	public Question findQuestionByID(int id) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Question question = (Question) session.get(Question.class, id);
		HibernateSessionFactory.closeSession();		//�ر�Session����
		return question;
	}
	public void updateQuestion(Question question) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Transaction  transaction = null;//����һ���������
		try{
			transaction = session.beginTransaction();//��������
			session.update(question);//����������Ϣ
			transaction.commit();//�ύ����
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//����ع�
		}
		HibernateSessionFactory.closeSession();//�ر�Session����
	}

	public void deleteQuestion(int id) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Question question = (Question) session.get(Question.class, id);
		Transaction  transaction = null;//����һ���������
		try{
			transaction = session.beginTransaction();//��������
			session.delete(question);
			transaction.commit();//�ύ����
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//����ع�
		}
		HibernateSessionFactory.closeSession();//�ر�Session����
	}

	public List<Question> likeQueryByTitle(String title,int ecid, String appid,Page page) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from Question where ecid = ? and appid = ? and subjectTitle like :subjectTitle ");
		query.setString("subjectTitle","%"+title+"%");
		query.setInteger(0, ecid);
		query.setString(1, appid);
		query.setMaxResults(page.getEveryPage());//���ò�ѯ��¼��
		query.setFirstResult(page.getBeginIndex());//���ò�ѯ��¼��ʼλ��
		List list = query.list();					//��ѯ������浽list��
		HibernateSessionFactory.closeSession();//�ر�Session����
		return list;
	}
	public int findLikeQueryCount(String title,int ecid, String appid) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from Question  where ecid = ? and appid = ? and subjectTitle like :subjectTitle ");
		query.setString("subjectTitle","%"+title+"%");
		query.setInteger(0, ecid);
		query.setString(1, appid);
		List list = query.list();					//��ѯ������浽list��
		int count = list.size();
		HibernateSessionFactory.closeSession();//�ر�Session����
		return count;
	}
	public Question findQuestionByTitle(String title) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from Question where subjectTitle = ?");
		query.setString(0, title);
		List list = query.list();					//��ѯ������浽list��
		HibernateSessionFactory.closeSession();		//�ر�Session����
		if(list.size() == 0) {
			return null;							//����null
		}else {
			return (Question) list.get(0);			//���ص�һ������
		}
	}
	public List<Question> randomFindQuestion( int ecid, String appid,int number) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from Question where ecid=? and appid = ? order by rand()");
		query.setInteger(0, ecid);
		query.setString(1, appid);
		query.setMaxResults(number);//���ò�ѯ��¼��
		List list = query.list();					//��ѯ������浽list��
		HibernateSessionFactory.closeSession();//�ر�Session����
		return list;
	}
}
