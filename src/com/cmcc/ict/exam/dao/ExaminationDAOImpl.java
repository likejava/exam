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
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			session.save(exam);//保存试卷信息
			exam.setUrl(exam.getUrl()+exam.getId());
			session.update(exam);
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
		return exam;
	}
	public Examination findExaminationByTitle(String title, Integer ecid, String appid) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Examination as exam where exam.title = ? and exam.ecid = ? and exam.appid = ?");
		query.setString(0, title);
		query.setInteger(1, ecid);
		query.setString(2, appid);
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();		//关闭Session对象
		if(list.size() == 0) {
			return null;							//返回null
		}else {
			return (Examination) list.get(0);			//返回第一个试卷
		}
	}
	public List<Examination> findExaminationByPage(Page page, Integer ecid, String appid) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Examination as exam where exam.ecid = ? and exam.appid = ? order by createTime desc");
		query.setInteger(0, ecid);
		query.setString(1, appid);
		query.setMaxResults(page.getEveryPage());//设置查询记录数
		query.setFirstResult(page.getBeginIndex());//设置查询记录起始位置
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();//关闭Session对象
		return list;
	}
	public int findExaminationCount(Integer ecid, String appid) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Examination as exam where exam.ecid = ? and exam.appid = ?");
		query.setInteger(0, ecid);
		query.setString(1, appid);
		List list = query.list();					//查询结果保存到list中
		int count = list.size();
		HibernateSessionFactory.closeSession();//关闭Session对象
		return count;
	}
	public void deleteExamination(int id) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Examination examination = (Examination) session.get(Examination.class, id);
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			session.delete(examination);
			Query query = session.createQuery("delete from Subject as sub where sub.examId = ? ");
			query.setInteger(0,id);
			query.executeUpdate();
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
		
	}
	public void deleteResultByExaminationId(int id){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			Query query = session.createQuery("delete from Result as res where res.examId = ? ");
			query.setInteger(0,id);
			query.executeUpdate();
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
	}
	public void deleteExamresultByExaminationId(int id){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			Query query = session.createQuery("delete from ExamResult as er where er.examId = ? ");
			query.setInteger(0,id);
			query.executeUpdate();
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
	}
	@Override
	public Examination findExaminationByID(int id) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Examination exam = (Examination) session.get(Examination.class, id);
		HibernateSessionFactory.closeSession();		//关闭Session对象
		return exam;
	}
	@Override
	public void updateExaminationTitle(int id, String title) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Examination examination = (Examination) session.get(Examination.class, id);
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			Query query = session.createQuery("update Examination exam set title = ? where id = ?");
			query.setInteger(1,id);
			query.setString(0,title);
			query.executeUpdate();
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
	}
	public void updateTotalScoreAndSubNum(int id, int totalScore, int subjectNum){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Examination examination = (Examination) session.get(Examination.class, id);
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			Query query = session.createQuery("update Examination exam set totalscore = ?,subjectnum = ?  where id = ?");
			query.setInteger(0,totalScore);
			query.setInteger(1,subjectNum);
			query.setInteger(2,id);
			query.executeUpdate();
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
	}
}