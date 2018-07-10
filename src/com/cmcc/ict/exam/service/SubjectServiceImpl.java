package com.cmcc.ict.exam.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import com.cmcc.ict.exam.dao.ExaminationDAO;
import com.cmcc.ict.exam.dao.ExaminationDAOImpl;
import com.cmcc.ict.exam.dao.QuestionDAO;
import com.cmcc.ict.exam.dao.QuestionDAOImpl;
import com.cmcc.ict.exam.dao.SubjectDAO;
import com.cmcc.ict.exam.dao.SubjectDAOImpl;
import com.cmcc.ict.exam.po.Examination;
import com.cmcc.ict.exam.po.Question;
import com.cmcc.ict.exam.po.Subject;
import com.cmcc.ict.exam.util.Page;
import com.cmcc.ict.exam.util.PageResult;
import com.cmcc.ict.exam.util.PageUtil;

public class SubjectServiceImpl implements SubjectService{
	private SubjectDAO subjectDAO =  new SubjectDAOImpl();
	private QuestionDAO questionDAO = new QuestionDAOImpl();
	private ExaminationDAO examDAO = new ExaminationDAOImpl();
	
	public boolean saveSubject(Subject subject) {
		if(subjectDAO.findSubjectByTitleAndExamId(subject.getSubjectTitle(),subject.getExamId()) == null){ //�����������ⲻ���ڣ��������
			subjectDAO.addSubject(subject);
			return true;
		}else {
			return false;
		}
	}

	public PageResult querySubjectByPage(Page page) {
		page = PageUtil.createPage(page.getEveryPage(),
				subjectDAO.findSubjectCount(),page.getCurrentPage());//�����ܼ�¼��������ҳ��Ϣ
		List<Subject> list = subjectDAO.findSubjectByPage(page);//ͨ����ҳ��Ϣȡ������
		PageResult result = new PageResult(page,list);//��װ��ҳ��Ϣ�ͼ�¼��Ϣ�����ظ����ô�
		return result;
	}

	public Subject showSubjectParticular(int subjectID) {
		return subjectDAO.findSubjectByID(subjectID);
	}

	public void updateSubject(Subject subject) {
		subjectDAO.updateSubject(subject);
	}

	public void deleteSubject(int subjectID) {
		subjectDAO.deleteSubject(subjectID);
	}

	public PageResult likeQueryBySubjectTitle(String subjectTitle, Page page) {
		page = PageUtil.createPage(page.getEveryPage(),
				subjectDAO.findLinkQueryCount(subjectTitle),page.getCurrentPage());//�����ܼ�¼��������ҳ��Ϣ
		List<Subject> list = subjectDAO.likeQueryByTitle(subjectTitle, page);//ͨ����ҳ��Ϣģ����ѯ����
		PageResult result = new PageResult(page,list);//��װ��ҳ��Ϣ�ͼ�¼��Ϣ�����ظ����ô�
		return result;
	}
	public PageResult likeQueryBySubjectTitleAndExamId(String subjectTitle,int examinationId, Page page){
		page = PageUtil.createPage(page.getEveryPage(),
				subjectDAO.findLinkQueryCountByExamId(subjectTitle,examinationId),page.getCurrentPage());//�����ܼ�¼��������ҳ��Ϣ
		List<Subject> list = subjectDAO.likeQueryByTitleAndExamId(subjectTitle,examinationId, page);//ͨ����ҳ��Ϣģ����ѯ����
		PageResult result = new PageResult(page,list);//��װ��ҳ��Ϣ�ͼ�¼��Ϣ�����ظ����ô�
		return result;	
	}


	public List<Subject> randomFindSubject(int number) {
		return subjectDAO.randomFindSubject(number);
	}

	public int accountResult(List<Integer> subjectIDs,
			List<String> studentAnswers) {
		int GeneralPoint = 0;//�ܷ�
		for(int i = 0; i < subjectIDs.size(); i++) {
			String rightAnswer = subjectDAO.
				findSubjectByID(subjectIDs.get(i)).getSubjectAnswer();//�õ���ȷ�𰸣�ͨ������ID
			if(rightAnswer.equals(studentAnswers.get(i))) {
				GeneralPoint += 5;//��5��
			}
		}
		return GeneralPoint;
	}
	public PageResult querySubjectByPageAndExamId(Page page,int examId) {
		page = PageUtil.createPage(page.getEveryPage(),
				subjectDAO.findSubjectCountByExamId(examId),page.getCurrentPage());//�����ܼ�¼��������ҳ��Ϣ
		List<Subject> list = subjectDAO.findSubjectByPageAndExamId(page,examId);//ͨ����ҳ��Ϣȡ������
		PageResult result = new PageResult(page,list);//��װ��ҳ��Ϣ�ͼ�¼��Ϣ�����ظ����ô�
		return result;
	}

	public HashMap importSubject(String filePath, int examinationId){
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
				for(int i = 1;i<sheet.getRows();i++){//�ӵڶ��У�i=1����ʼѭ������һ��Ϊ������������
					try{
						Subject subject = new Subject();
						String subjectTitle = sheet.getCell(0,i).getContents().trim();
						String subjectOptionA = sheet.getCell(1,i).getContents().trim();
						String subjectOptionB = sheet.getCell(2,i).getContents().trim();
						String subjectOptionC = sheet.getCell(3,i).getContents().trim();
						String subjectOptionD = sheet.getCell(4,i).getContents().trim();
						String subjectAnswer = sheet.getCell(5,i).getContents().trim();
						String subjectParse = sheet.getCell(6,i).getContents().trim();
						String score = sheet.getCell(7,i).getContents().trim();

						if(null == subjectTitle || "".equals(subjectTitle)){
							String error = "��"+i+"�У���ĿΪ�գ�";
							errorList.add(error);
							errorNum++;
							continue;

						}
						if(null == subjectAnswer || "".equals(subjectAnswer)){
							String error = "��"+i+"�У���Ϊ�գ�";
							errorList.add(error);
							errorNum++;
							continue;

						}
						if(null == score || "".equals(score)){
							String error = "��"+i+"�У���ֵΪ�գ�";
							errorList.add(error);
							errorNum++;
							continue;

						}
						if(!score.matches("^\\d+$"))
						{
							String error = "��"+i+"�У���ֵֻ��Ϊ��������";
							errorList.add(error);
							errorNum++;
							continue;

						}
						subject.setExamId(examinationId);
						subject.setSubjectTitle(subjectTitle);
						subject.setSubjectOptionA(subjectOptionA);
						subject.setSubjectOptionB(subjectOptionB);
						subject.setSubjectOptionC(subjectOptionC);
						subject.setSubjectOptionD(subjectOptionD);
						subject.setSubjectAnswer(subjectAnswer);
						subject.setSubjectParse(subjectParse);
						subject.setScore(Integer.parseInt(score));
						if(subjectDAO.findSubjectByTitleAndExamId(subject.getSubjectTitle(),subject.getExamId()) == null){ //�����������ⲻ���ڣ��������
							int saveResult = subjectDAO.addSubject(subject);
								if(saveResult>0){
									successNum++;
								}else{
									String error = "��"+i+"�У����ݱ���ʧ�ܣ�";
									errorList.add(error);
									errorNum++;
									continue;
								}
						}else{
							String error = "��"+i+"�У����ݱ���ʧ��,����Ŀ�Ѵ��ڣ�";
							errorList.add(error);
							errorNum++;
							continue;
						}
						
					}catch(Exception e){
						e.printStackTrace();
						String error = "��"+i+"�У�"+e.getMessage();
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
				String error = "Excel�ļ�ת����sheetҳ��ȡʧ�ܣ�";
				errorList.add(error);
				result.put("errorList", errorList);
			}
		}else{
			result.put("success", "0");
			result.put("failed", "all");
			String error = "�ļ���ȡʧ�ܣ�";
			errorList.add(error);
			result.put("errorList", errorList);
		}
		return result;
	}
	public int addRandomSubject(int examId, int subjectNum){
		int succCount = 0;
		Examination exam = examDAO.findExaminationByID(examId);
		List<Question> questions = questionDAO.randomFindQuestion(exam.getEcid(), exam.getAppid(), subjectNum);
		for(Question ques: questions){
			Subject sub = new Subject();
			sub.setExamId(examId);
			sub.setScore(ques.getScore());
			sub.setSubjectAnswer(ques.getSubjectAnswer());
			sub.setSubjectOptionA(ques.getSubjectOptionA());
			sub.setSubjectOptionB(ques.getSubjectOptionB());
			sub.setSubjectOptionC(ques.getSubjectOptionC());
			sub.setSubjectOptionD(ques.getSubjectOptionD());
			sub.setSubjectParse(ques.getSubjectParse());
			sub.setSubjectTitle(ques.getSubjectTitle());
			if(subjectDAO.findSubjectByTitleAndExamId(sub.getSubjectTitle(),sub.getExamId()) == null){ //�����������ⲻ���ڣ��������
				subjectDAO.addSubject(sub);
				succCount++;
			}
		}
		return succCount;
	}

}
