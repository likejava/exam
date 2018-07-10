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
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			session.save(examResult);
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
	}
	public List<ExamResult> findExamResultsByExamIdByPage(int examId,Page page ){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from ExamResult where examId = ? order by score desc");
		query.setInteger(0, examId);
		query.setMaxResults(page.getEveryPage());//设置查询记录数
		query.setFirstResult(page.getBeginIndex());//设置查询记录起始位置
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();	//关闭Session对象
		return list;
	}
	public int findExamResultCount(int examId){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from ExamResult where examId = ? ");
		query.setInteger(0, examId);
		List list = query.list();					//查询结果保存到list中
		int count = list.size();
		HibernateSessionFactory.closeSession();//关闭Session对象
		return count;
	}
	public int findExamResultCountByMobile(int examId , String mobile){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from ExamResult where examId = ? and mobile = ? ");
		query.setInteger(0, examId);
		query.setString(1, mobile);
		List list = query.list();					//查询结果保存到list中
		int count = list.size();
		HibernateSessionFactory.closeSession();//关闭Session对象
		return count;
	}
	public List<ExamResult> findExamResultsByExamIdAndMobileByPage(int examId, String mobile, Page page){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from ExamResult where examId = ? and mobile = ? order by createTime desc");
		query.setInteger(0, examId);
		query.setString(1, mobile);
		query.setMaxResults(page.getEveryPage());//设置查询记录数
		query.setFirstResult(page.getBeginIndex());//设置查询记录起始位置
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();	//关闭Session对象
		return list;
	}
	public List<ExamResult> queryResult(String appId, String ecId, String mobile){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("select er from ExamResult as er, Examination as e  where er.examId = e.id and e.appid = ? "
				+ "and e.ecid = ? and er.mobile = ?");
		query.setString(0, appId);
		query.setString(1, ecId);
		query.setString(2, mobile);
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();	//关闭Session对象
		return list;
	}
	public List<ExamResult> findExamResultsByExamId(int examId){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from ExamResult where examId = ? order by score desc");
		query.setInteger(0, examId);
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();	//关闭Session对象
		return list;
	}
}