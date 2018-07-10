<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>����ϵͳ��������</title>
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
    		alert("������Ŀ����Ϊ��");
    		return;
    	}
    	if(optionA=="" && optionB == "" && optionC == "" && optionD == ""){
    		alert("���ٸ���һ��ѡ��!");
    		return;
    	}
    	var reg = /^\d+$/;
    	var score = document.getElementById("score").value;
    	if(!score.match(reg)){
    		alert("��ֵ����Ϊ����");
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
<s:actionerror/>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="text-info text-center">
				¼������
			</h3>

			<div style="padding: 50px 100px 10px;">
				<form class="form-horizontal" action="subjectAdd.action" method="post" name="form1">
					<div class="form-group"> 
						<label class="col-sm-2 control-label">��ֵ</label> 
						<div class="col-sm-2"> 
							<input class="form-control" type="text" name="subject.score" id="score" placeholder="�������ֵ"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">��Ŀ</label> 
						<div class="col-sm-8"> 
							<input class="form-control" type="text" name="subject.subjectTitle" id="subjectTitle" placeholder="��������Ŀ"> 
						</div> 
					</div>

					<div class="form-group"> 
						<label class="col-sm-2 control-label">ѡ��A</label> 
						<div class="col-sm-2"> 
							<input class="form-control" type="text" id="optionA" name="subject.subjectOptionA" placeholder="������ѡ��A"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">ѡ��B</label> 
						<div class="col-sm-2"> 
							<input class="form-control" id="optionB" name="subject.subjectOptionB" placeholder="������ѡ��B"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">ѡ��C</label> 
						<div class="col-sm-2"> 
							<input class="form-control" id="optionC" name="subject.subjectOptionC" placeholder="������ѡ��C"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">ѡ��D</label> 
						<div class="col-sm-2"> 
							<input class="form-control" id="optionD" name="subject.subjectOptionD" placeholder="������ѡ��D"> 
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">��</label> 
						<div class="col-sm-2" style="width:20%"> 
							<label class="radio-inline"><input name="subject.subjectAnswer" type="radio" value="A" checked>A</label>
							<label class="radio-inline"><input name="subject.subjectAnswer" type="radio" value="B">B</label>
							<label class="radio-inline"><input name="subject.subjectAnswer" type="radio" value="C">C</label>
							<label class="radio-inline"><input name="subject.subjectAnswer" type="radio" value="D">D</label>
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">�������</label> 
						<div class="col-sm-8"> 
							<textarea class="form-control" name="subject.subjectParse" rows="6"></textarea>
						</div> 
					</div>
					<div class="form-group"> 
						<div class="col-sm-8" align="center"> 
							<button class="btn btn-success" type="button" onclick="formSubmit()">�ύ</button>&nbsp;&nbsp;&nbsp;&nbsp;
	    					<button class="btn btn-warning" type="button" onclick="javascript:history.go(-1);">����</button>
						</div>
					</div>
					
					<input name="subject.examId" id="examinationId" value="${examinationId}" type="hidden">
					<input name="examinationId" value="${examinationId}" type="hidden">
				</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>