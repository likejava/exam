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
    	var file = document.getElementById("importFile").value;
		var fileType = file.substring(file.indexOf(".")+1,file.length);
		if(file == "")
		{
			alert("请选择文件");
			return;
		}
		if(fileType != "xls"){
			alert("只能上传格式为xls的文件");
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
				批量导入试题
			</h3>

			<div style="padding: 50px 100px 10px;">
				<form class="form-horizontal" name="form1" id="form1" method="post" action="questionImport" enctype="multipart/form-data">
					<div class="form-group"> 
						<label class="col-sm-2 control-label">上传文件：</label> 
						<div class="col-sm-4"> 
							<input class="form-control" name="uploadFile" id="importFile" type="file"/>
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">说明：</label> 
						<div class="col-sm-4"> 
							* 请按照模版格式填写数据<a href="../upload/example.xls">模版下载</a><br>
							* 文件名不能含有中文字符
							* 文件大小不超过10M<br>
							* 题目和答案不能为空，其他字段为可选<br>
						</div> 
					</div>
					</br>
					<div class="form-group"> 
						<div class="col-sm-8" align="center"> 
							<button class="btn btn-success" type="button" onclick="formSubmit()">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;
	    					<button class="btn btn-warning" type="button" onclick="javascript:history.go(-1);">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>