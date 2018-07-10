<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<s:actionerror/>
	<title>考试系统管理中心</title>
	<!-- <meta http-equiv="Content-Type" content="text/html;charset=utf-8"> -->
	<link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-2.2.3.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script type="text/javascript">
    function submitRandomExam(){
	    var score = $("#score").val();
	    var subjectNum = $("#subject-num").val();
	    if(!subjectNum.match(/^[1-9]\d*$/)){
	    	alert("试题个数必须为正整数");
	    	return;
	    }
	    if(score%subjectNum != 0){
	    	alert ("分数除试题不能整除，请重新输入");
	   		return;
	    }
	    $("#form1").submit();      
    }
    function changeExamType(){
    	var type=$("#exam-type").val();
    	if(type=="1"){
    		$("#randow-exam-area").hide();
    		$("#general-area").show();
    	}else{
    		$("#randow-exam-area").show();
    		$("#general-area").hide();
    	}
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
				新增试卷
			</h3>
			<div style="padding: 50px 100px 10px;">
			<form class="form-horizontal" id="form1" action="examinationAdd.action" method="post">	
				<div class="form-group col-center-block">
					<label for="firstname" class="col-sm-2 control-label">试卷标题：</label>
					<div class="col-xs-6">
					   <input type="text" class="form-control" id="title" name="examination.title" placeholder="请输入试卷标题">
					</div>
				</div>
				<div class="form-group col-center-block">
					<label for="firstname" class="col-sm-2 control-label">试卷类型：</label>
					<div class="col-xs-6">
					   <select class="form-control" id="exam-type" name="examination.type" onchange="changeExamType()">
					   <option value="1">一般试卷</option>
					   <option value="2">随机试卷</option>
					   </select>
					   
					</div>
				</div>
				<div id="randow-exam-area" style="display:none">
					<div class="form-group col-center-block">
						<label for="firstname" class="col-sm-2 control-label">总分：</label>
						<div class="col-xs-6">
						   	<input type="text" class="form-control" id="score" name="examination.totalScore" placeholder="请输入试卷总分">
						</div>
					</div>
					<div class="form-group col-center-block">
						<label for="firstname" class="col-sm-2 control-label">试题个数：</label>
						<div class="col-xs-6">
						   	<input type="text" class="form-control" id="subject-num" name="examination.subjectNum" placeholder="请输入试题个数">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-10">
						   <button type="button" class="btn btn-success" onclick="submitRandomExam()">提交</button>
						</div>
					</div>
				</div>
				<div id="general-area">
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-10">
					  	 	<button type="submit" class="btn btn-success" >下一步</button>
						</div>
					</div>
				</div>
			</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>