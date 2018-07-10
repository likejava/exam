package com.cmcc.ict.exam.dao;

import java.util.List;
import java.util.Map;

import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;

public interface SubjectDAO {
	public int addSubject(Subject subject);//保存方法，用来保存试题
	public Subject findSubjectByTitle(String subjectTitle);//根据试题标题查找试题
	public List<Subject> findSubjectByPage(Page page);//分页查询试题
	public List<Subject> findSubjectByPageAndExamId(Page page,int examId);//分页查询试题
	public int findSubjectCount();//查询试题总量
	public int findSubjectCountByExamId(int examId);//查询试题总量
	public Subject findSubjectByID(int subjectID);//根据试题ID查找试题
	public void updateSubject(Subject subject);//更新方法，用来更新试题
	public void deleteSubject(int subjectID);//根据试题ID删除试题
	public List<Subject> likeQueryByTitle(String subjectTitle,Page page);//根据试题标题模糊查询试题
	public List<Subject> likeQueryByTitleAndExamId(String subjectTitle,int examId,Page page);//根据试题标题模糊查询试题
	public int findLinkQueryCount(String subjectTitle);//查询模糊记录数
	public int findLinkQueryCountByExamId(String subjectTitle,int examId);//查询模糊记录数
	public List<Subject> randomFindSubject(int number);//随时取出记录
	public Subject findSubjectByTitleAndExamId(String subjectTitle, int examId);//根据试题标题和所属试卷查找试题
	public List<Subject> queryAllSubjectsByExamId(int examId);
    public Map<String, Integer> queryTotalScoreAndSubNumByExamId(int examinationId);
}
