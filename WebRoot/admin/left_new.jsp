<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>����ϵͳ��������</title>
	<link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-2.2.3.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
	<div>
		<!-- <ul class="nav nav-pills nav-stacked">
			<li class="active dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#">
					�Ծ���� <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="examinationAdd_new.jsp" target="rightFrame">�����Ծ�</a></li>
					<li><a href="examinationList.action" target="rightFrame">�����Ծ�</a></li>
					<li class="divider"></li>
					<li><a href="#">���������</a></li>
				</ul>
			</li>
			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#">
					�ɼ����� <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#">ͨ������鿴�ɼ�</a></li>
					<li><a href="#">�ɼ���ѯ</a></li>
					<li class="divider"></li>
					<li><a href="#">���������</a></li>
				</ul>
			</li>
		</ul> -->
		
		<div class="panel-group" id="accordion2">
            <div class="panel panel-default">
                <div class="dropdown-toggle panel-heading" data-toggle="collapse"
                     data-parent="#accordion2" href="#collapse2015">
                    <a class="accordion-toggle" style="font-size:18px;font-weight: bold" href="#">�Ծ����</a>
                </div>
                <div id="collapse2015" class="panel-collapse collapse"
                     style="height: 0px;">
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked">
                            <li><a href="examinationAdd_new.jsp" target="rightFrame">�����Ծ�</a></li>
							<li><a href="examinationList.action" target="rightFrame">�����Ծ�</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" data-toggle="collapse"
                     data-parent="#accordion2" href="#collapse2014">
                    <a class="accordion-toggle" style="font-size:18px;font-weight: bold" href="#">�ɼ�����</a>
                </div>
                <div id="collapse2014" class="panel-collapse collapse"
                     style="height: 0px;">
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked">
                            <li><a href="examinationResultList.action" target="rightFrame">�ɼ���ѯ</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" data-toggle="collapse"
                     data-parent="#accordion2" href="#collapse2013">
                    <a class="accordion-toggle" style="font-size:18px;font-weight: bold" href="#">������</a>
                </div>
                <div id="collapse2013" class="panel-collapse collapse"
                     style="height: 0px;">
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked">
  							<li><a href="questionAdd.jsp" target="rightFrame">��������</a></li>
							<li><a href="questionList.action" target="rightFrame">�������</a></li>
						</ul>
                    </div>
                </div>
            </div>
		</div>
	</div>
</body>
</html>
