package com.cmcc.ict.exam.dao;
import java.util.List;

import com.cmcc.ict.exam.po.ExamStart;
import com.cmcc.ict.exam.util.Page;

public interface ExamStartDAO {
	public void addExamStart(ExamStart ExamStart);
	public int findExamStartCountByMobile(int examId , String mobile);
}
