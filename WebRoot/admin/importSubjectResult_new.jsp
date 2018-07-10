<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>考试系统管理中心</title>
	<link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-2.2.3.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    
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
				批量导入结果
			</h3>

			<div style="padding: 50px 100px 10px;">
				<div class="form-horizontal">
					<div class="form-group"> 
						<label class="col-sm-2 control-label">成功：</label> 
						<div class="col-sm-2"> 
							<span class="col-sm-2 control-label">${result.success }</span>
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">失败：</label> 
						<div class="col-sm-2"> 
							<span class="col-sm-2 control-label">${result.failed }</span>
						</div> 
					</div>
					<div class="form-group"> 
						<label class="col-sm-2 control-label">错误信息：</label> 
						<div class="col-sm-8" style="color:#FF0000"> 
							<s:iterator value="#request.result.errorList" var="error">
								<span class="col-sm-2 control-label">${error }</span>
							</s:iterator> 
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