package com.cmcc.ict.exam.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cmcc.ict.exam.dao.ExamResultDAO;
import com.cmcc.ict.exam.dao.ExamResultDAOImpl;
import com.cmcc.ict.exam.dao.ExaminationDAO;
import com.cmcc.ict.exam.dao.ExaminationDAOImpl;
import com.cmcc.ict.exam.dao.ResultDAO;
import com.cmcc.ict.exam.dao.ResultDAOImpl;
import com.cmcc.ict.exam.po.ExamResult;
import com.cmcc.ict.exam.po.Examination;
import com.cmcc.ict.exam.po.Result;
import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;
import com.cmcc.ict.exam.util.PageResult;
import com.cmcc.ict.exam.util.PageUtil;

public class ResultServiceImpl implements ResultService {
	private ExamResultDAO examResultDao = new ExamResultDAOImpl();
	private ResultDAO resultDao = new ResultDAOImpl();
	private ExaminationDAO examDao = new ExaminationDAOImpl();
	public PageResult listExamResultsByExamIdByPage(int examId, Page page){
		page = PageUtil.createPage(page.getEveryPage(),
				examResultDao.findExamResultCount(examId),page.getCurrentPage());
		List<ExamResult> list = examResultDao.findExamResultsByExamIdByPage(examId,page);;
		PageResult result = new PageResult(page,list);//封装分页信息和记录信息，返回给调用处
		return result;
	}
	public PageResult SearchByMoblieByPage(int examId, String mobile, Page page){
		page = PageUtil.createPage(page.getEveryPage(),
				examResultDao.findExamResultCountByMobile(examId , mobile),page.getCurrentPage());
		List<ExamResult> list = examResultDao.findExamResultsByExamIdAndMobileByPage(examId,mobile,page);
		PageResult result = new PageResult(page,list);//封装分页信息和记录信息，返回给调用处
		return result;
	}
	public List<Object> getResultDetailListByExamIdAndMobile(int examId, String mobile, String examType){
		return resultDao.getResultDetailListByExamIdAndMobile(examId, mobile, examType);
	}
	public List queryResult(String appId, String ecId, String mobile){
		List resList = new ArrayList();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		List<ExamResult> examResultList = examResultDao.queryResult(appId, ecId, mobile);
		for(ExamResult examResult: examResultList){
			Examination exam = examDao.findExaminationByID(examResult.getExamId());
			List<ExamResult> list = examResultDao.findExamResultsByExamId(examResult.getExamId());
			int total = list.size();
			int rank = 0;
			for(int i = 0; i < list.size(); i++){
				if(mobile.equals(list.get(i).getMobile())){
					rank = i + 1;
				}
			}
			Map<String, String > map = new HashMap<String, String>();
			map.put("examId", String.valueOf(examResult.getExamId()));
			map.put("examTitle", exam.getTitle());
			map.put("correctNum", String.valueOf(examResult.getCorrectNum()));
			map.put("wrongNum", String.valueOf(examResult.getWrongNum()));
			map.put("score", String.valueOf(examResult.getScore()));
			map.put("rank", rank+"/"+total);
			map.put("examTime", df.format(examResult.getCreateTime()));
			resList.add(map);
		}
		return resList;
	}
	public List<Map<String, String >> queryExamDetailList(String appId, String ecId, String mobile){
		List<Object[]> list = resultDao.queryExamDetailList(appId, ecId, mobile);
		List<Map<String, String>> resList = new ArrayList<Map<String, String>>();
		for(Object[] obj : list)
		{
			Result res = (Result)obj[0];
			Subject sub = (Subject)obj[1];
			Map<String,String> map = new HashMap<String, String>();
			map.put("subjectId", String.valueOf(sub.getSubjectID()));
			map.put("subjectTitle", sub.getSubjectTitle());
			map.put("subjectOptionA", sub.getSubjectOptionA());
			map.put("subjectOptionB", sub.getSubjectOptionB());
			map.put("subjectOptionC", sub.getSubjectOptionC());
			map.put("subjectOptionD", sub.getSubjectOptionD());
			map.put("subjectAnswer", sub.getSubjectAnswer());
			map.put("subjectParse", sub.getSubjectParse());
			map.put("selectOptoin", res.getSelectOption());
			map.put("myScore", String.valueOf(res.getScore()));
			resList.add(map);
		}
		return resList;
	}

}
