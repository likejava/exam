package com.cmcc.ict.exam.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cmcc.ict.exam.hibernate.HibernateSessionFactory;
import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;

public class SubjectDAOImpl implements SubjectDAO{
	public int addSubject(Subject subject){
		int result = -1;
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			result = (Integer)session.save(subject);//保存试题信息
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
		return result;
	}

	public Subject findSubjectByTitle(String subjectTitle) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Subject as sub where sub.subjectTitle = ?");
		query.setString(0, subjectTitle);
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();		//关闭Session对象
		if(list.size() == 0) {
			return null;							//返回null
		}else {
			return (Subject) list.get(0);			//返回第一个试题
		}
	}

	public List<Subject> findSubjectByPage(Page page) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Subject");
		query.setMaxResults(page.getEveryPage());//设置查询记录数
		query.setFirstResult(page.getBeginIndex());//设置查询记录起始位置
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();//关闭Session对象
		return list;
	}
	public List<Subject> findSubjectByPageAndExamId(Page page,int examId) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Subject sub where sub.examId = ?");
		query.setInteger(0, examId);
		query.setMaxResults(page.getEveryPage());//设置查询记录数
		query.setFirstResult(page.getBeginIndex());//设置查询记录起始位置
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();//关闭Session对象
		return list;
	}
	public int findSubjectCount() {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Subject");
		List list = query.list();					//查询结果保存到list中
		int count = list.size();
		HibernateSessionFactory.closeSession();//关闭Session对象
		return count;
	}
	public int findSubjectCountByExamId(int examId) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Subject sub where sub.examId = ?");
		query.setInteger(0, examId);
		List list = query.list();					//查询结果保存到list中
		int count = list.size();
		HibernateSessionFactory.closeSession();//关闭Session对象
		return count;
	}

	public Subject findSubjectByID(int subjectID) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Subject subject = (Subject) session.get(Subject.class, subjectID);
		HibernateSessionFactory.closeSession();		//关闭Session对象
		return subject;
	}

	public void updateSubject(Subject subject) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			session.update(subject);//更新试题信息
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
	}

	public void deleteSubject(int subjectID) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Subject subject = (Subject) session.get(Subject.class, subjectID);
		Transaction  transaction = null;//声明一个事务对象
		try{
			transaction = session.beginTransaction();//开启事务
			session.delete(subject);
			transaction.commit();//提交事务
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//事务回滚
		}
		HibernateSessionFactory.closeSession();//关闭Session对象
	}

	public List<Subject> likeQueryByTitle(String subjectTitle,Page page) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Subject as sub where sub.subjectTitle like :title ");
		query.setString("title","%"+subjectTitle+"%");
		query.setMaxResults(page.getEveryPage());//设置查询记录数
		query.setFirstResult(page.getBeginIndex());//设置查询记录起始位置
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();//关闭Session对象
		return list;
	}
	public List<Subject> likeQueryByTitleAndExamId(String subjectTitle,int examId,Page page) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Subject as sub where sub.subjectTitle like :title and sub.examId = :examId");
		query.setString("title","%"+subjectTitle+"%");
		query.setInteger("examId",examId);
		query.setMaxResults(page.getEveryPage());//设置查询记录数
		query.setFirstResult(page.getBeginIndex());//设置查询记录起始位置
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();//关闭Session对象
		return list;
	}

	public int findLinkQueryCount(String subjectTitle) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Subject as sub where sub.subjectTitle like :title ");
		query.setString("title","%"+subjectTitle+"%");
		List list = query.list();					//查询结果保存到list中
		int count = list.size();
		HibernateSessionFactory.closeSession();//关闭Session对象
		return count;
	}
	public int findLinkQueryCountByExamId(String subjectTitle, int examId) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Subject as sub where sub.subjectTitle like :title and sub.examId = :examId");
		query.setString("title","%"+subjectTitle+"%");
		query.setInteger("examId",examId);
		List list = query.list();					//查询结果保存到list中
		int count = list.size();
		HibernateSessionFactory.closeSession();//关闭Session对象
		return count;
	}

	public List<Subject> randomFindSubject(int number) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Subject as sub order by rand()");
		query.setMaxResults(number);//设置查询记录数
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();//关闭Session对象
		return list;
	}
	public Subject findSubjectByTitleAndExamId(String subjectTitle, int examId) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Subject as sub where sub.subjectTitle = ? and sub.examId = ?");
		query.setString(0, subjectTitle);
		query.setInteger(1, examId);
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();		//关闭Session对象
		if(list.size() == 0) {
			return null;							//返回null
		}else {
			return (Subject) list.get(0);			//返回第一个试题
		}
	}

	@Override
	public List<Subject> queryAllSubjectsByExamId(int examId) {
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("from Subject sub where sub.examId = ?");
		query.setInteger(0, examId);
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();//关闭Session对象
		return list;
	}
	public Map<String, Integer> queryTotalScoreAndSubNumByExamId(int examinationId){
		Session session = HibernateSessionFactory.getSession();//获得Session对象
		Query query = session.createQuery("select sum(sub.score),count(*) from Subject sub where sub.examId = ?");
		query.setInteger(0, examinationId);
		List list = query.list();					//查询结果保存到list中
		HibernateSessionFactory.closeSession();//关闭Session对象
		Map<String, Integer> resMap = new HashMap<String ,Integer>();
		Object[] res = (Object[]) list.get(0);
		Long totalScore = res[0] == null ? 0 : (Long)res[0];
		Long subjectNum = res[1] == null ? 0 : (Long)res[1];
		resMap.put("totalScore", totalScore.intValue());
		resMap.put("subjectNum", subjectNum.intValue());
		return resMap;
	}
}
