package com.cmcc.ict.exam.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.cmcc.ict.exam.po.Question;
import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;
import com.cmcc.ict.exam.util.PageResult;

public interface QuestionService {
	// 添加试题，首先判断该试题标题是否已经存在，如果已经则不能添加
	public boolean saveQuestion(Question question);
	// 按分页信息查询试题
	public PageResult queryQuestionByPage(Page page, int ecid, String appid);
	// 查看试题详细信息
	public Question showQuestionParticular(int id);
	// 更新试题信息
	public void updateQuestion(Question question);
	// 删除试题信息
	public void deleteQuestion(int id);
	//模糊查询试题信息
	public PageResult likeQueryByTitle(String title, int ecid, String appid, Page page);
	public HashMap importQuestion(String filePath,int ecid, String appid);
}
