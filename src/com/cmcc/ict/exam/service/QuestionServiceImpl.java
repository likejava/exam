package com.cmcc.ict.exam.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;






import jxl.Sheet;
import jxl.Workbook;

import com.cmcc.ict.exam.dao.QuestionDAO;
import com.cmcc.ict.exam.dao.QuestionDAOImpl;
import com.cmcc.ict.exam.po.Question;
import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;
import com.cmcc.ict.exam.util.PageResult;
import com.cmcc.ict.exam.util.PageUtil;

public class QuestionServiceImpl implements QuestionService{
	private QuestionDAO questionDAO =  new QuestionDAOImpl();
	
	public boolean saveQuestion(Question question) {
		if(questionDAO.findQuestionByTitle(question.getSubjectTitle()) == null){ //如果该试题标题不存在，允许添加
			questionDAO.addQuestion(question);
			return true;
		}else {
			return false;
		}
	}

	public PageResult queryQuestionByPage(Page page, int ecid, String appid) {
		page = PageUtil.createPage(page.getEveryPage(),
				questionDAO.findQuestionCount(ecid, appid),page.getCurrentPage());//根据总记录数创建分页信息
		List<Question> list = questionDAO.findQuestionByPage(ecid, appid, page);//通过分页信息取得试题
		PageResult result = new PageResult(page,list);//封装分页信息和记录信息，返回给调用处
		return result;
	}
	public PageResult likeQueryByTitle(String title,int ecid, String appid, Page page){
		page = PageUtil.createPage(page.getEveryPage(),
				questionDAO.findLikeQueryCount(title, ecid, appid),page.getCurrentPage());//根据总记录数创建分页信息
		List<Question> list = questionDAO.likeQueryByTitle(title, ecid, appid, page);//通过分页信息模糊查询试题
		PageResult result = new PageResult(page,list);//封装分页信息和记录信息，返回给调用处
		return result;
	}

	public Question showQuestionParticular(int id) {
		return questionDAO.findQuestionByID(id);
	}

	public void updateQuestion(Question question) {
		questionDAO.updateQuestion(question);
	}

	public void deleteQuestion(int id) {
		questionDAO.deleteQuestion(id);
	}

	public HashMap importQuestion(String filePath,int ecid, String appid){
		HashMap result = new HashMap();
		ArrayList errorList = new ArrayList();
		int errorNum = 0;
		int successNum = 0;
		File file = new File(filePath);
		if(file.exists()){
			try {
				Workbook book = Workbook.getWorkbook(file);
				Sheet sheet  =  book.getSheet(0);
				List subjectList = new ArrayList();
				for(int i = 1;i<sheet.getRows();i++){//从第二行（i=1）开始循环，第一行为列名不作处理
					try{
						Question question = new Question();
						String title = sheet.getCell(0,i).getContents().trim();
						String optionA = sheet.getCell(1,i).getContents().trim();
						String optionB = sheet.getCell(2,i).getContents().trim();
						String optionC = sheet.getCell(3,i).getContents().trim();
						String optionD = sheet.getCell(4,i).getContents().trim();
						String answer = sheet.getCell(5,i).getContents().trim();
						String parse = sheet.getCell(6,i).getContents().trim();
						String score = sheet.getCell(7,i).getContents().trim();

						if(null == title || "".equals(title)){
							String error = "第"+i+"行：题目为空！";
							errorList.add(error);
							errorNum++;
							continue;

						}
						if(null == answer || "".equals(answer)){
							String error = "第"+i+"行：答案为空！";
							errorList.add(error);
							errorNum++;
							continue;

						}
						if(null == score || "".equals(score)){
							String error = "第"+i+"行：分值为空！";
							errorList.add(error);
							errorNum++;
							continue;

						}
						if(!score.matches("^\\d+$"))
						{
							String error = "第"+i+"行：分值只能为正整数！";
							errorList.add(error);
							errorNum++;
							continue;

						}
						question.setEcid(ecid);
						question.setAppid(appid);
						question.setSubjectTitle(title);
						question.setSubjectOptionA(optionA);
						question.setSubjectOptionB(optionB);
						question.setSubjectOptionC(optionC);
						question.setSubjectOptionD(optionD);
						question.setSubjectAnswer(answer);
						question.setSubjectParse(parse);
						question.setScore(Integer.parseInt(score));
						if(questionDAO.findQuestionByTitle(title) == null){ //如果该试题标题不存在，允许添加
							int saveResult = questionDAO.addQuestion(question);
								if(saveResult>0){
									successNum++;
								}else{
									String error = "第"+i+"行：数据保存失败！";
									errorList.add(error);
									errorNum++;
									continue;
								}
						}else{
							String error = "第"+i+"行：数据保存失败,该题目已存在！";
							errorList.add(error);
							errorNum++;
							continue;
						}
						
					}catch(Exception e){
						e.printStackTrace();
						String error = "第"+i+"行："+e.getMessage();
						errorList.add(error);
						errorNum++;
						continue;
					}
				}
	            book.close();
	            result.put("success", String.valueOf(successNum));
				result.put("failed", String.valueOf(errorNum));
				result.put("errorList", errorList);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("success", "0");
				result.put("failed", "all");
				String error = "Excel文件转换或sheet页获取失败！";
				errorList.add(error);
				result.put("errorList", errorList);
			}
		}else{
			result.put("success", "0");
			result.put("failed", "all");
			String error = "文件读取失败！";
			errorList.add(error);
			result.put("errorList", errorList);
		}
		return result;
	}
}
