<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>����ϵͳ��������</title>
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
				��${examination.title }���ɼ���ѯ
			</h3>
			<table class="table table-hover table-bordered" style="width:90%;" align="center">
				<thead>
					<tr class="warning">
						<th>
							�û�
						</th>
						<th>
							�����/������
						</th>
						<th>
							�÷�/�ܷ�
						</th>
						<th>
							�ύʱ��
						</th>
						<th>
							����
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
								<a href="resultDetail.action?examination.id=${examination.id}&mobile=${er.mobile}">�鿴����</a>
							</td>
						</tr>
					</s:iterator>	
				</tbody>
			</table>
			
			<nav class="text-center">
				<ul class="pagination">
				  <s:if test="#request.page.hasPrePage">
				    <li><a href="listExamResults.action?currentPage=1&mobile=${mobile}&examination.id=${examination.id}">��ҳ</a></li>
                	<li><a href="listExamResults.action?currentPage=${page.currentPage - 1}&mobile=${mobile}&examination.id=${examination.id}">&laquo;��һҳ</a></li>
				  </s:if>
               	  <s:else>
               		<li><a href="#">��ҳ</a></li>
				    <li><a href="#">&laquo;��һҳ</a></li> 
               	  </s:else>
               	  <s:if test="#request.page.hasNextPage">
				    <li><a href="listExamResults.action?currentPage=${page.currentPage + 1}&mobile=${mobile}&examination.id=${examination.id}">��һҳ&raquo;</a></li>
				    <li><a href="listExamResults.action?currentPage=${page.totalPage}&mobile=${mobile}&examination.id=${examination.id}">βҳ</a></li>
				  </s:if>
				  <s:else>
               		<li><a href="#">��һҳ&raquo;</a></li>
				    <li><a href="#">βҳ</a></li>
               	  </s:else>
				</ul>
				<ul>
					<p class="text-muted">��${page.totalCount}����¼����ǰ��${page.currentPage}/${page.totalPage}ҳ��ÿҳ${page.everyPage}����¼</p>
				</ul>
			</nav>

		</div>
	</div>
</div>

</body>
</html>