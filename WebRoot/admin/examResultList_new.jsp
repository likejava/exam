<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			<h3 class="text-info text-center" style="word-break:break-all; word-wrap:break-all;">
				《${examination.title }》成绩查询
			</h3>
			<table class="table table-hover table-bordered" style="width:90%;" align="center">
				<thead>
					<tr class="warning">
						<th>
							用户
						</th>
						<th>
							答对数/总题数
						</th>
						<th>
							得分/总分
						</th>
						<th>
							提交时间
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="examResultList" var="er">
						<tr class="success">
							<td>${er.mobile}</td>
							<td>${er.correctNum}/${er.correctNum + er.wrongNum }</td>
							<td>${er.score }/${examination.totalScore }
							<td>
							    <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${er.createTime }"></fmt:formatDate>
							</td>
							<td>
								<a href="resultDetail.action?examination.id=${examination.id}&mobile=${er.mobile}">查看详情</a>
							</td>
						</tr>
					</s:iterator>	
				</tbody>
			</table>
			
			<nav class="text-center">
				<ul class="pagination">
				  <s:if test="#request.page.hasPrePage">
				    <li><a href="listExamResults.action?currentPage=1&mobile=${mobile}&examination.id=${examination.id}">首页</a></li>
                	<li><a href="listExamResults.action?currentPage=${page.currentPage - 1}&mobile=${mobile}&examination.id=${examination.id}">&laquo;上一页</a></li>
				  </s:if>
               	  <s:else>
               		<li><a href="#">首页</a></li>
				    <li><a href="#">&laquo;上一页</a></li> 
               	  </s:else>
               	  <s:if test="#request.page.hasNextPage">
				    <li><a href="listExamResults.action?currentPage=${page.currentPage + 1}&mobile=${mobile}&examination.id=${examination.id}">下一页&raquo;</a></li>
				    <li><a href="listExamResults.action?currentPage=${page.totalPage}&mobile=${mobile}&examination.id=${examination.id}">尾页</a></li>
				  </s:if>
				  <s:else>
               		<li><a href="#">下一页&raquo;</a></li>
				    <li><a href="#">尾页</a></li>
               	  </s:else>
				</ul>
				<ul>
					<p class="text-muted">共${page.totalCount}条纪录，当前第${page.currentPage}/${page.totalPage}页，每页${page.everyPage}条纪录</p>
				</ul>
			</nav>

		</div>
	</div>
</div>

</body>
</html>