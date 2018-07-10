package com.cmcc.ict.exam.dao;

import java.util.List;
import java.util.Map;

import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;

public interface SubjectDAO {
	public int addSubject(Subject subject);//���淽����������������
	public Subject findSubjectByTitle(String subjectTitle);//������������������
	public List<Subject> findSubjectByPage(Page page);//��ҳ��ѯ����
	public List<Subject> findSubjectByPageAndExamId(Page page,int examId);//��ҳ��ѯ����
	public int findSubjectCount();//��ѯ��������
	public int findSubjectCountByExamId(int examId);//��ѯ��������
	public Subject findSubjectByID(int subjectID);//��������ID��������
	public void updateSubject(Subject subject);//���·�����������������
	public void deleteSubject(int subjectID);//��������IDɾ������
	public List<Subject> likeQueryByTitle(String subjectTitle,Page page);//�����������ģ����ѯ����
	public List<Subject> likeQueryByTitleAndExamId(String subjectTitle,int examId,Page page);//�����������ģ����ѯ����
	public int findLinkQueryCount(String subjectTitle);//��ѯģ����¼��
	public int findLinkQueryCountByExamId(String subjectTitle,int examId);//��ѯģ����¼��
	public List<Subject> randomFindSubject(int number);//��ʱȡ����¼
	public Subject findSubjectByTitleAndExamId(String subjectTitle, int examId);//�����������������Ծ��������
	public List<Subject> queryAllSubjectsByExamId(int examId);
    public Map<String, Integer> queryTotalScoreAndSubNumByExamId(int examinationId);
}
