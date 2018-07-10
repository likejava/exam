<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>考试系统管理中心</title>
	<link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-2.2.3.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
	<div>
		<!-- <ul class="nav nav-pills nav-stacked">
			<li class="active dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#">
					试卷管理 <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="examinationAdd_new.jsp" target="rightFrame">新增试卷</a></li>
					<li><a href="examinationList.action" target="rightFrame">管理试卷</a></li>
					<li class="divider"></li>
					<li><a href="#">分离的链接</a></li>
				</ul>
			</li>
			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#">
					成绩管理 <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#">通过试题查看成绩</a></li>
					<li><a href="#">成绩查询</a></li>
					<li class="divider"></li>
					<li><a href="#">分离的链接</a></li>
				</ul>
			</li>
		</ul> -->
		
		<div class="panel-group" id="accordion2">
            <div class="panel panel-default">
                <div class="dropdown-toggle panel-heading" data-toggle="collapse"
                     data-parent="#accordion2" href="#collapse2015">
                    <a class="accordion-toggle" style="font-size:18px;font-weight: bold" href="#">试卷管理</a>
                </div>
                <div id="collapse2015" class="panel-collapse collapse"
                     style="height: 0px;">
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked">
                            <li><a href="examinationAdd_new.jsp" target="rightFrame">新增试卷</a></li>
							<li><a href="examinationList.action" target="rightFrame">管理试卷</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" data-toggle="collapse"
                     data-parent="#accordion2" href="#collapse2014">
                    <a class="accordion-toggle" style="font-size:18px;font-weight: bold" href="#">成绩管理</a>
                </div>
                <div id="collapse2014" class="panel-collapse collapse"
                     style="height: 0px;">
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked">
                            <li><a href="examinationResultList.action" target="rightFrame">成绩查询</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" data-toggle="collapse"
                     data-parent="#accordion2" href="#collapse2013">
                    <a class="accordion-toggle" style="font-size:18px;font-weight: bold" href="#">题库管理</a>
                </div>
                <div id="collapse2013" class="panel-collapse collapse"
                     style="height: 0px;">
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked">
  							<li><a href="questionAdd.jsp" target="rightFrame">新增试题</a></li>
							<li><a href="questionList.action" target="rightFrame">试题管理</a></li>
						</ul>
                    </div>
                </div>
            </div>
		</div>
	</div>
</body>
</html>
