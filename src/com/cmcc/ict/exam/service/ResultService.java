package com.cmcc.ict.exam.service;
import java.util.List;
import java.util.Map;

import com.cmcc.ict.exam.po.ExamResult;
import com.cmcc.ict.exam.util.Page;
import com.cmcc.ict.exam.util.PageResult;

public interface ResultService {
	public PageResult listExamResultsByExamIdByPage(int examId, Page page);
	public List<Object> getResultDetailListByExamIdAndMobile(int examId, String mobile, String examType);
	public PageResult SearchByMoblieByPage(int examId, String mobile, Page page);
	public List queryResult(String appId, String ecId, String mobile);
	public List<Map<String, String >> queryExamDetailList(String appId, String ecId, String mobile);
}
