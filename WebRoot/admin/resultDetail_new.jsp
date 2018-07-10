<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>考试系统管理中心</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-2.2.3.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="text-info text-center">
				成绩详情
			</h3>
			<!-- <div style="padding: 50px 100px 10px;"> -->
			<div>
				<div class="form-horizontal">	
					<!-- <div style="padding: 50px 100px 10px;"> -->
					<div>
						<s:iterator value="resultDetailList" var="res" status="sta">
							<div class="form-group"> 
								<strong><span class="control-label" style="word-break:break-all; word-wrap:break-all;">${sta.index + 1}、（${res[1].score }分）&nbsp;${res[1].subjectTitle}（）</span></strong>
							</div>
							<div class="form-group">
								A．${res[1].subjectOptionA}
							</div>
							<div class="form-group">
								B．${res[1].subjectOptionB}
							</div>
							<div class="form-group">
								C．${res[1].subjectOptionC}
							</div>
							<div class="form-group">
								D．${res[1].subjectOptionD}
							</div>
							<div class="form-group alert alert-success">
								【正确答案】：${res[1].subjectAnswer}<br/>
				        		【参考解析】：${res[1].subjectParse}<br/>
				        		【考生答案】：${res[0].selectOption}<br/>
				        		【得分】：${res[0].score }
							</div>
						</s:iterator>
						</br>
						<!-- <div class="form-group"> 
							<div class="col-sm-12" align="center"> 
		    					<button class="btn btn-warning" type="button" onclick="javascript:history.go(-1);">返回</button>
							</div>
						</div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>