package com.cmcc.ict.exam.po;

import java.util.Date;

public class ExamResult {
	private int id;
	private String mobile;
	private int examId;
	private int correctNum;
	private int wrongNum;
	private Date createTime;
	private int score;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public int getCorrectNum() {
		return correctNum;
	}
	public void setCorrectNum(int correctNum) {
		this.correctNum = correctNum;
	}
	public int getWrongNum() {
		return wrongNum;
	}
	public void setWrongNum(int wrongNum) {
		this.wrongNum = wrongNum;
	}
}
