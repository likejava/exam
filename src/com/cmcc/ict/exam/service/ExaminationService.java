package com.cmcc.ict.exam.service;

import java.util.List;

import com.cmcc.ict.exam.po.ExamResult;
import com.cmcc.ict.exam.po.Examination;
import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;
import com.cmcc.ict.exam.util.PageResult;
import com.cmcc.ict.exam.po.Result;

public interface ExaminationService {
	public Examination saveExamination(Examination examination);
	public PageResult queryExaminationByPage(Page page, Integer ecid, String appid);
	public void deleteExamination(int id);
	public Examination findExaminationByID(int id);
	public void updateExaminationTitle(int id, String title);
	public ExamResult saveResult(List<Result> list);//保存
	public boolean findResultByUser(String user,Integer examId);
	public void updateTotalScoreAndSubNumByExamId(int examinationId);
	public List<Subject> queryAllSubjectsByExamId(int examId);
	public List<Subject> queryRandomSubjectsByExamId(int examId);
	public void deleteResultByExaminationId(int id);
	public void deleteExamresultByExaminationId(int id);
	public void saveExamStart(Integer examId,String mobile,String name);//开始考试记录
	public int findExamStartCountByMobile(int examId , String mobile);
	
}
