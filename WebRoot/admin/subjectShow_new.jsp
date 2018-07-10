<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>考试系统管理中心</title>
	<link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-2.2.3.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    function formSubmit(){
    	var title = document.getElementById("subjectTitle").value;
    	var optionA = document.getElementById("optionA").value;
    	var optionB = document.getElementById("optionB").value;
    	var optionC = document.getElementById("optionC").value;
    	var optionD = document.getElementById("optionD").value;
    	if(title == ""){
    		alert("试题题目不能为空");
    		return;
    	}
    	if(optionA=="" && optionB == "" && optionC == "" && optionD == ""){
    		alert("至少给出一个选项!");
    		return;
    	}
		document.form1.submit();
	}
  	function return_back(){
 		var examinationId = ${examinationId};
			window.location.href="subjectListByExamId?examinationId="+examinationId;
  	}
    </script>
    
    <!--<style>  
	 body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background-color: #f7f7f7;
	} 
	</style> -->
</head>
<body>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="text-info text-center">
				查看试题
			</h3>

			<div style="padding: 50px 100px 10px;">
				<div class="form-horizontal">
					<div class="form-group"> 
						<label class="col-sm-2 control-label">分值</label> 
						<div class="col-sm-2"> 
							<input class="form-control" type="text" name="subject.score" id="score" value="${subject.score}" readonly="readonly"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">题目</label> 
						<div class="col-sm-8"> 
							<input class="form-control" type="text" name="subject.subjectTitle" id="subjectTitle" value="${subject.subjectTitle}" readonly="readonly"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">选项A</label> 
						<div class="col-sm-2"> 
							<input class="form-control" type="text" id="optionA" name="subject.subjectOptionA" value="${subject.subjectOptionA}" readonly="readonly"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">选项B</label> 
						<div class="col-sm-2"> 
							<input class="form-control" id="optionB" name="subject.subjectOptionB" value="${subject.subjectOptionB}" readonly="readonly"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">选项C</label> 
						<div class="col-sm-2"> 
							<input class="form-control" id="optionC" name="subject.subjectOptionC" value="${subject.subjectOptionC}" readonly="readonly"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">选项D</label> 
						<div class="col-sm-2"> 
							<input class="form-control" id="optionD" name="subject.subjectOptionD" value="${subject.subjectOptionD}" readonly="readonly"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">答案</label> 
						<div class="col-sm-2"> 
							<input class="form-control" id="answer" name="subject.subjectAnswer" value="${subject.subjectAnswer}" readonly="readonly"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">试题解析</label> 
						<div class="col-sm-8"> 
							<textarea class="form-control" name="subject.subjectParse" rows="6" readonly="readonly">${subject.subjectParse}</textarea>
						</div> 
					</div>
					<div class="form-group"> 
						<div class="col-sm-8" align="center"> 
	    					<button class="btn btn-warning" type="button" onclick="javascript:history.go(-1);">返回</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>