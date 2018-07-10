package com.cmcc.ict.exam.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cmcc.ict.exam.dao.ExamResultDAO;
import com.cmcc.ict.exam.dao.ExamResultDAOImpl;
import com.cmcc.ict.exam.dao.ExamStartDAO;
import com.cmcc.ict.exam.dao.ExamStartDAOImpl;
import com.cmcc.ict.exam.dao.ExaminationDAO;
import com.cmcc.ict.exam.dao.ExaminationDAOImpl;
import com.cmcc.ict.exam.dao.QuestionDAO;
import com.cmcc.ict.exam.dao.QuestionDAOImpl;
import com.cmcc.ict.exam.dao.ResultDAO;
import com.cmcc.ict.exam.dao.ResultDAOImpl;
import com.cmcc.ict.exam.dao.SubjectDAO;
import com.cmcc.ict.exam.dao.SubjectDAOImpl;
import com.cmcc.ict.exam.po.ExamResult;
import com.cmcc.ict.exam.po.ExamStart;
import com.cmcc.ict.exam.po.Examination;
import com.cmcc.ict.exam.po.Question;
import com.cmcc.ict.exam.po.Result;
import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;
import com.cmcc.ict.exam.util.PageResult;
import com.cmcc.ict.exam.util.PageUtil;

public class ExaminationServiceImpl implements ExaminationService{
	private ExaminationDAO dao =  new ExaminationDAOImpl();
	private ResultDAO resultDao = new ResultDAOImpl();
	private SubjectDAO subjectDao = new SubjectDAOImpl();
	private ExamResultDAO examResultDao = new ExamResultDAOImpl();
	private QuestionDAO questionDao = new QuestionDAOImpl();
	private ExamStartDAO examStartDao = new ExamStartDAOImpl();
	
	public Examination saveExamination(Examination examination) {
		String title = examination.getTitle();
		Integer ecid = examination.getEcid();
		String appid = examination.getAppid();
		if(dao.findExaminationByTitle(title, ecid, appid) == null){ //如果该试卷标题不存在，允许添加
			return dao.addExamination(examination);
		}else {
			return null;
		}
	}
	public PageResult queryExaminationByPage(Page page, Integer ecid, String appid) {
		page = PageUtil.createPage(page.getEveryPage(),
				dao.findExaminationCount(ecid, appid),page.getCurrentPage());
		List<Examination> list = dao.findExaminationByPage(page, ecid, appid);
		PageResult result = new PageResult(page,list);//封装分页信息和记录信息，返回给调用处
		return result;
	}
	public void deleteExamination(int id){
		dao.deleteExamination(id);
	}
	public void deleteResultByExaminationId(int id){
		dao.deleteResultByExaminationId(id);
	}
	public void deleteExamresultByExaminationId(int id){
		dao.deleteExamresultByExaminationId(id);
	}
	@Override
	public Examination findExaminationByID(int id) {
		// TODO Auto-generated method stub
		return dao.findExaminationByID(id);
	}
	@Override
	public void updateExaminationTitle(int id, String title) {
		dao.updateExaminationTitle(id, title);
		
	}
	@Override
	/**
	 * 保存答卷，返回答题正确数
	 */
	public ExamResult saveResult(List<Result> list) {
		int examId = list.get(0).getExamId();
		Examination exam = dao.findExaminationByID(examId);
		int correctNum = 0;
		int totalScore = 0;
		if(exam.getType().equals("1")){
			for(Result res : list)
			{
				Subject subject = subjectDao.findSubjectByID(res.getSubjectId());
				if(subject.getSubjectAnswer().equals(res.getSelectOption()))
				{
					correctNum++;
					res.setScore(subject.getScore());
					totalScore += subject.getScore();
				}else {
					res.setScore(0);
				}
			}
			
		}else {
			int perScore = exam.getTotalScore()/exam.getSubjectNum();
			for(Result res : list){
				Question question = questionDao.findQuestionByID(res.getSubjectId());
				if(question.getSubjectAnswer().equals(res.getSelectOption()))
				{
					correctNum++;
					res.setScore(perScore);
					totalScore += perScore;
				}else {
					res.setScore(0);
				}
				
			}
		}
		resultDao.addResults(list);
		String mobile = list.get(0).getUser();
		int wrongNum = list.size() - correctNum;
		ExamResult examResult = new ExamResult();
		examResult.setExamId(examId);
		examResult.setMobile(mobile);
		examResult.setCorrectNum(correctNum);
		examResult.setWrongNum(wrongNum);
		examResult.setCreateTime(new Date());
		examResult.setScore(totalScore);
		examResultDao.addExamResult(examResult);
		return examResult;
	}
	
	public boolean findResultByUser(String user, Integer examId)
	{
		boolean ret = false;
		ret = resultDao.findResultByUser(user, examId);
		
		return ret;
	}
	public void updateTotalScoreAndSubNumByExamId(int examinationId){
		Map<String, Integer> resMap = subjectDao.queryTotalScoreAndSubNumByExamId(examinationId);
		int totalScore = resMap.get("totalScore");
		int subjectNum = resMap.get("subjectNum");
		dao.updateTotalScoreAndSubNum(examinationId, totalScore, subjectNum);
	}
	@Override
	public List<Subject> queryAllSubjectsByExamId(int examId) {
		return subjectDao.queryAllSubjectsByExamId(examId);
	}
	@Override
	public List<Subject> queryRandomSubjectsByExamId(int examId) {
		Examination exam = dao.findExaminationByID(examId);
		int totalScore = exam.getTotalScore();
		int subjectNum = exam.getSubjectNum();
		int perScore = totalScore/subjectNum;
		List <Question> questions = questionDao.randomFindQuestion(exam.getEcid(),exam.getAppid(), subjectNum);
		List<Subject> subjects = new ArrayList<Subject>();
		for(Question ques : questions){
			Subject sub = new Subject();
			sub.setExamId(examId);
			sub.setScore(perScore);
			sub.setSubjectID(ques.getId());
			sub.setSubjectOptionA(ques.getSubjectOptionA());
			sub.setSubjectOptionB(ques.getSubjectOptionB());
			sub.setSubjectOptionC(ques.getSubjectOptionC());
			sub.setSubjectOptionD(ques.getSubjectOptionD());
			sub.setSubjectTitle(ques.getSubjectTitle());
			subjects.add(sub);
		}
		return subjects;
	}
	@Override
	public void saveExamStart(Integer examId,String mobile,String name){//开始考试记录
		ExamStart examStart = new ExamStart();
		examStart.setExamId(examId);
		examStart.setMobile(mobile);
		examStart.setName(name);
		examStart.setCreateTime(new Date());
		examStartDao.addExamStart(examStart);
	}
	@Override
	public int findExamStartCountByMobile(int examId , String mobile){
		return examStartDao.findExamStartCountByMobile(examId, mobile);
	}
}
