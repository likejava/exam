<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>����ϵͳ��������</title>
	<link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-2.2.3.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script type="text/javascript">
    function del(id){
		if(confirm('ȷ��ɾ�����Ծ��������ݣ�'))
			window.location.href="examinationDelete.action?examination.id="+id;
	}
    </script>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="text-info text-center">
				�����Ծ�
			</h3>
			<table class="table table-hover table-bordered" style="width:90%;word-break:break-all; word-wrap:break-all;" align="center">
				<thead>
					<tr class="warning">
						<th>
							�Ծ����
						</th>
						<th>
							�Ծ�����
						</th>
						<th>
							�ܷ�
						</th>	
						<th>
							������
						</th>					
						<th>
							����
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.examinations" var="exam">
						<tr class="success">
							<td width="31%">${exam.title}</td>
							<td width="44%">${exam.url}</td>
							<td width="5%">${exam.totalScore}</td>
							<td width="8%">${exam.subjectNum}</td>
							<td width="12%">
							<c:if test="${exam.type!='2'}">
								<a href="subjectListByExamId.action?examinationId=${exam.id}">�༭�Ծ�</a>&nbsp;
							</c:if>
								<a href="javascript:del(${exam.id});">ɾ��</a>
							</td>
						</tr>
					</s:iterator>	
				</tbody>
			</table>
			
			<nav class="text-center">
				<ul class="pagination">
				  <s:if test="#request.page.hasPrePage">
				    <li><a href="examinationList.action?currentPage=1">��ҳ</a></li>
				    <li><a href="examinationList.action?currentPage=${page.currentPage - 1}">&laquo;��һҳ</a></li>
				    </s:if>
               	  <s:else>
               		<li><a href="#">��ҳ</a></li>
				    <li><a href="#">&laquo;��һҳ</a></li> 
               	  </s:else>
               	  <s:if test="#request.page.hasNextPage">
				    <li><a href="examinationList.action?currentPage=${page.currentPage + 1}">��һҳ&raquo;</a></li>
				    <li><a href="examinationList.action?currentPage=${page.totalPage}">βҳ</a></li>
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