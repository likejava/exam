package com.cmcc.ict.exam.dao;

import java.util.List;

import com.cmcc.ict.exam.po.Result;

public interface ResultDAO {
	public void addResults(List<Result> list);
	public List<Object> getResultDetailListByExamIdAndMobile(int examId, String mobile, String examType);
	public List<Object[]> queryExamDetailList(String appId, String ecId, String mobile);
	public boolean findResultByUser(String user, Integer examId);
}
