package com.cmcc.ict.exam.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.cmcc.ict.exam.po.Question;
import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;
import com.cmcc.ict.exam.util.PageResult;

public interface QuestionService {
	// ������⣬�����жϸ���������Ƿ��Ѿ����ڣ�����Ѿ��������
	public boolean saveQuestion(Question question);
	// ����ҳ��Ϣ��ѯ����
	public PageResult queryQuestionByPage(Page page, int ecid, String appid);
	// �鿴������ϸ��Ϣ
	public Question showQuestionParticular(int id);
	// ����������Ϣ
	public void updateQuestion(Question question);
	// ɾ��������Ϣ
	public void deleteQuestion(int id);
	//ģ����ѯ������Ϣ
	public PageResult likeQueryByTitle(String title, int ecid, String appid, Page page);
	public HashMap importQuestion(String filePath,int ecid, String appid);
}
