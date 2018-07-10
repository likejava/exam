<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>����ϵͳ��������</title>
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
				�������
			</h3>
			<div>
				<div class="form-horizontal">	
					<div style="padding: 50px 100px 10px;">
						<s:iterator value="#request.subjects" var="subject" status="sta">
							<div class="form-group"> 
								<strong><span class="control-label">${sta.index + 1}����${sub.score }�֣�&nbsp;${subject.subjectTitle}</span></strong>
							</div>
							<div class="form-group">
								A��${subject.subjectOptionA}
							</div>
							<div class="form-group">
								B��${subject.subjectOptionB}
							</div>
							<div class="form-group">
								C��${subject.subjectOptionC}
							</div>
							<div class="form-group">
								D��${subject.subjectOptionD}
							</div>
							<div class="form-group alert alert-success">
								����ȷ�𰸡���${subject.subjectAnswer}<br/>
				        		���ο���������${subject.subjectParse}
							</div>
						</s:iterator>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>