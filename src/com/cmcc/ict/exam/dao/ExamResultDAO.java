package com.cmcc.ict.exam.dao;
import java.util.List;

import com.cmcc.ict.exam.po.ExamResult;
import com.cmcc.ict.exam.util.Page;

public interface ExamResultDAO {
	public void addExamResult(ExamResult examResult);
	public List<ExamResult> findExamResultsByExamIdByPage(int examId,Page page);
	public int findExamResultCount(int examId);
	public int findExamResultCountByMobile(int examId , String mobile);
	public List<ExamResult> findExamResultsByExamIdAndMobileByPage(int examId, String mobile, Page page);
	public List queryResult(String appId, String ecId, String mobile);
	public List<ExamResult> findExamResultsByExamId(int examId);
}
