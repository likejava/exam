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
    	var file = document.getElementById("importFile").value;
		var fileType = file.substring(file.indexOf(".")+1,file.length);
		if(file == "")
		{
			alert("��ѡ���ļ�");
			return;
		}
		if(fileType != "xls"){
			alert("ֻ���ϴ���ʽΪxls���ļ�");
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

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="text-info text-center">
				������������
			</h3>

			<div style="padding: 50px 100px 10px;">
				<form class="form-horizontal" name="form1" id="form1" method="post" action="questionImport" enctype="multipart/form-data">
					<div class="form-group"> 
						<label class="col-sm-2 control-label">�ϴ��ļ���</label> 
						<div class="col-sm-4"> 
							<input class="form-control" name="uploadFile" id="importFile" type="file"/>
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">˵����</label> 
						<div class="col-sm-4"> 
							* �밴��ģ���ʽ��д����<a href="../upload/example.xls">ģ������</a><br>
							* �ļ������ܺ��������ַ�
							* �ļ���С������10M<br>
							* ��Ŀ�ʹ𰸲���Ϊ�գ������ֶ�Ϊ��ѡ<br>
						</div> 
					</div>
					</br>
					<div class="form-group"> 
						<div class="col-sm-8" align="center"> 
							<button class="btn btn-success" type="button" onclick="formSubmit()">�ύ</button>&nbsp;&nbsp;&nbsp;&nbsp;
	    					<button class="btn btn-warning" type="button" onclick="javascript:history.go(-1);">����</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>