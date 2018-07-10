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
    	var title = document.getElementById("title").value;
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
    	var reg = /^\d+$/;
    	var score = document.getElementById("score").value;
    	if(!score.match(reg)){
    		alert("分值必须为数字");
    		return;
    	}
		document.form1.submit();
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
<s:actionerror/>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="text-info text-center">
				新增试题
			</h3>

			<div style="padding: 50px 100px 10px;">
				<form class="form-horizontal" action="questionAdd.action" method="post" name="form1">
					<div class="form-group"> 
						<label class="col-sm-2 control-label">分值</label> 
						<div class="col-sm-2"> 
							<input class="form-control" type="text" name="question.score" id="score" placeholder="请输入分值"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">题目</label> 
						<div class="col-sm-8"> 
							<input class="form-control" type="text" name="question.subjectTitle" id="title" placeholder="请输入题目"> 
						</div> 
					</div>

					<div class="form-group"> 
						<label class="col-sm-2 control-label">选项A</label> 
						<div class="col-sm-2"> 
							<input class="form-control" type="text" id="optionA" name="question.subjectOptionA" placeholder="请输入选项A"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">选项B</label> 
						<div class="col-sm-2"> 
							<input class="form-control" id="optionB" name="question.subjectOptionB" placeholder="请输入选项B"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">选项C</label> 
						<div class="col-sm-2"> 
							<input class="form-control" id="optionC" name="question.subjectOptionC" placeholder="请输入选项C"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">选项D</label> 
						<div class="col-sm-2"> 
							<input class="form-control" id="optionD" name="question.subjectOptionD" placeholder="请输入选项D"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">答案</label> 
						<div class="col-sm-2" style="width:20%"> 
							<label class="radio-inline"><input name="question.subjectAnswer" type="radio" value="A" checked>A</label>
							<label class="radio-inline"><input name="question.subjectAnswer" type="radio" value="B">B</label>
							<label class="radio-inline"><input name="question.subjectAnswer" type="radio" value="C">C</label>
							<label class="radio-inline"><input name="question.subjectAnswer" type="radio" value="D">D</label>
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">试题解析</label> 
						<div class="col-sm-8"> 
							<textarea class="form-control" name="question.subjectParse" rows="6"></textarea>
						</div> 
					</div>
					<div class="form-group"> 
						<div class="col-sm-8" align="center"> 
							<button class="btn btn-success" type="button" onclick="formSubmit()">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;
	    					<button class="btn btn-warning" type="button" onclick="javascript:history.go(-1);">返回</button>
						</div>
					</div>
					<input name="question.ecid"  value="${sessionScope.ecid}" type="hidden">
					<input name="question.appid"  value="${sessionScope.appid}" type="hidden">
				</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>