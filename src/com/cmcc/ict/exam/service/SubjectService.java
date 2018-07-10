package com.cmcc.ict.exam.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;
import com.cmcc.ict.exam.util.PageResult;

public interface SubjectService {
	// ������⣬�����жϸ���������Ƿ��Ѿ����ڣ�����Ѿ��������
	public boolean saveSubject(Subject subject);
	// ����ҳ��Ϣ��ѯ����
	public PageResult querySubjectByPage(Page page);
	public PageResult querySubjectByPageAndExamId(Page page ,int examId);
	// �鿴������ϸ��Ϣ
	public Subject showSubjectParticular(int subjectID);
	// ����������Ϣ
	public void updateSubject(Subject subject);
	// ɾ��������Ϣ
	public void deleteSubject(int subjectID);
	//ģ����ѯ������Ϣ
	public PageResult likeQueryBySubjectTitle(String subjectTitle,Page page);
	public PageResult likeQueryBySubjectTitleAndExamId(String subjectTitle,int examinationId, Page page);
	//�����ѯ�����¼
	public List<Subject> randomFindSubject(int number);
	//����ѧ���÷�
	public int accountResult(List<Integer> subjectIDs,List<String> studentAnswers);
	public HashMap importSubject(String filePath, int examinationId);
	public int addRandomSubject(int examId, int subjectNum);
}
