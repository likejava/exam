package com.cmcc.ict.exam.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cmcc.ict.exam.hibernate.HibernateSessionFactory;
import com.cmcc.ict.exam.po.Result;

public class ResultDAOImpl implements ResultDAO {
	public void addResults(List<Result> list){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			int i = 0;
			for(Result res : list)
			{
				session.save(res);
				i++;
				
			}
			if(i % 20 == 0){
				session.flush();
				session.clear();
			}
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
	}
	public List<Object> getResultDetailListByExamIdAndMobile(int examId, String mobile, String examType){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = null;
		if(examType != null && "2".equals(examType)){
			query = session.createQuery("from Result res , Question qt where res.subjectId = qt.id "
					+ "and res.examId = ? and res.user = ?");
		}else{
			query = session.createQuery("from Result res , Subject sj where res.subjectId = sj.subjectID "
					+ "and res.examId = ? and res.user = ?");
		}

		query.setInteger(0, examId);
		query.setString(1, mobile);
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();//关闭Session对象
		return list;
	}
	public List<Object[]> queryExamDetailList(String appId, String ecId, String mobile){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("select res, sj from Result res , Subject sj ,Examination e where res.subjectId = sj.subjectID "
				+ "and e.id = res.examId  and e.ecid=? and e.appid=? and res.user=?");
		query.setString(0, ecId);
		query.setString(1, appId);
		query.setString(2, mobile);
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();//关闭Session对象
		return list;
	}
	
	public boolean findResultByUser(String user, Integer examId)
	{
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Result res where res.user = ? and res.examId = ?");
		query.setString(0, user);
		query.setInteger(1, examId);
		List list = query.list();					//查询结果保存到list中
		int count = list.size();
		HibernateSessionFactory.closeSession();//关闭Session对象
		
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}
}