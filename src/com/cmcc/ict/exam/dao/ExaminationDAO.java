package com.cmcc.ict.exam.dao;

import java.util.List;

import com.cmcc.ict.exam.po.Examination;
import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;

public interface ExaminationDAO {
	public Examination addExamination(Examination exam);
	public Examination findExaminationByTitle(String title, Integer ecid, String appid);//根据试题标题查找试题
	public List<Examination> findExaminationByPage(Page page, Integer ecid, String appid);
	public int findExaminationCount(Integer ecid, String appid);
	public void deleteExamination(int id);
	public Examination findExaminationByID(int id);
	public void updateExaminationTitle(int id, String title);
	public void updateTotalScoreAndSubNum(int id, int totalScore, int subjectNum);
	public void deleteResultByExaminationId(int id);
	public void deleteExamresultByExaminationId(int id);
}
