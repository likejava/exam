package com.cmcc.ict.exam.dao;

import java.util.List;

import com.cmcc.ict.exam.po.Question;
import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;

public interface QuestionDAO {
	public int findQuestionCount(int ecid, String appid);//查询试题总量
	public List<Question> findQuestionByPage(int ecid, String appid,Page page);
	public int findLikeQueryCount(String title,int ecid, String appid);
	public List<Question> likeQueryByTitle(String title,int ecid, String appid, Page page);
	public Question findQuestionByTitle(String title);
	public int addQuestion(Question question);
	public Question findQuestionByID(int id);
	public void updateQuestion(Question question);
	public void deleteQuestion(int id);
	public List<Question> randomFindQuestion(int ecid, String appid, int number);//随时取出记录
}
