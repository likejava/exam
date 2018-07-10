<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>考试系统管理中心</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="../exam/css/bootstrap.min.css" rel="stylesheet">
    <script src="../exam/js/jquery-2.2.3.min.js"></script>
    <script src="../exam/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="text-info text-center">
				试  卷
			</h3>
			<div>
				<form class="form-horizontal" action="submitExam.action" method="post">	
					<s:iterator value="subjects" var="sub" status="sta">
						<div class="form-group"> 
							<strong><span class="control-label" style="word-break:break-all; word-wrap:break-all;">${sta.index + 1}、（${sub.score }分）&nbsp;${sub.subjectTitle}（）</span></strong>
						</div>
						<div class="form-group">
							A．${sub.subjectOptionA}
						</div>
						<div class="form-group">
							B．${sub.subjectOptionB}
						</div>
						<div class="form-group">
							C．${sub.subjectOptionC}
						</div>
						<div class="form-group">
							D．${sub.subjectOptionD}
						</div>
						<div class="form-group alert alert-success">
							<label class="control-label">请选择：</label> 
							<div> 
							<label class="radio-inline"><input name="resultList[${sta.index}].selectOption" type="radio" value="A">A</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<label class="radio-inline"><input name="resultList[${sta.index}].selectOption" type="radio" value="B">B</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<label class="radio-inline"><input name="resultList[${sta.index}].selectOption" type="radio" value="C">C</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<label class="radio-inline"><input name="resultList[${sta.index}].selectOption" type="radio" value="D">D</label>&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						
						<input type="hidden" name="resultList[${sta.index}].subjectId" value="${sub.subjectID}"/>
		  	  	 		<input type="hidden" name="resultList[${sta.index}].examId" value="${examId}"/>
		  	  	 		<input type="hidden" name="resultList[${sta.index}].user" value="${mobile}"/>
					</s:iterator>
					<br>
					<div class="form-group"> 
						<div class="col-sm-12" align="center">
							<button type="submit" class="btn btn-success">提交</button>
						</div>
					</div>
					<br>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>