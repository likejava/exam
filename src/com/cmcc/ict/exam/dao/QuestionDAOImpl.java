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
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			result = (Integer)session.save(question);//保存试题信息
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
		return result;
	}
	public List<Question> findQuestionByPage(int ecid, String appid,Page page) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Question where ecid = ? and appid = ?");
		query.setInteger(0, ecid);
		query.setString(1, appid);
		query.setMaxResults(page.getEveryPage());//设置查询记录数
		query.setFirstResult(page.getBeginIndex());//设置查询记录起始位置
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();//关闭Session对象
		return list;
	}
	public int findQuestionCount(int ecid, String appid) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Question where ecid = ? and appid = ?");
		query.setInteger(0, ecid);
		query.setString(1, appid);
		List list = query.list();					//查询结果保存到list中
		int count = list.size();
		HibernateSessionFactory.closeSession();//关闭Session对象
		return count;
	}

	public Question findQuestionByID(int id) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Question question = (Question) session.get(Question.class, id);
		HibernateSessionFactory.closeSession();		//关闭Session对象
		return question;
	}
	public void updateQuestion(Question question) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			session.update(question);//更新试题信息
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
	}

	public void deleteQuestion(int id) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Question question = (Question) session.get(Question.class, id);
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			session.delete(question);
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
	}

	public List<Question> likeQueryByTitle(String title,int ecid, String appid,Page page) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Question where ecid = ? and appid = ? and subjectTitle like :subjectTitle ");
		query.setString("subjectTitle","%"+title+"%");
		query.setInteger(0, ecid);
		query.setString(1, appid);
		query.setMaxResults(page.getEveryPage());//设置查询记录数
		query.setFirstResult(page.getBeginIndex());//设置查询记录起始位置
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();//关闭Session对象
		return list;
	}
	public int findLikeQueryCount(String title,int ecid, String appid) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Question  where ecid = ? and appid = ? and subjectTitle like :subjectTitle ");
		query.setString("subjectTitle","%"+title+"%");
		query.setInteger(0, ecid);
		query.setString(1, appid);
		List list = query.list();					//查询结果保存到list中
		int count = list.size();
		HibernateSessionFactory.closeSession();//关闭Session对象
		return count;
	}
	public Question findQuestionByTitle(String title) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Question where subjectTitle = ?");
		query.setString(0, title);
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();		//关闭Session对象
		if(list.size() == 0) {
			return null;							//返回null
		}else {
			return (Question) list.get(0);			//返回第一个试题
		}
	}
	public List<Question> randomFindQuestion( int ecid, String appid,int number) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Question where ecid=? and appid = ? order by rand()");
		query.setInteger(0, ecid);
		query.setString(1, appid);
		query.setMaxResults(number);//设置查询记录数
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();//关闭Session对象
		return list;
	}
}
