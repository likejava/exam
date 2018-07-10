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
    function del(id){
		if(confirm('确定删除该试卷下所有数据，包括试题？'))
			window.location.href="examinationDelete.action?examination.id="+id;
	}
    </script>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="text-info text-center">
				成绩查询
			</h3>
			<table class="table table-hover table-bordered" style="width:90%;word-break:break-all; word-wrap:break-all;" align="center">
				<thead>
					<tr class="warning">
						<th>
							试卷标题
						</th>
						<th>
							试卷链接
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.examinations" var="exam">
						<tr class="success">
							<td width="52%">${exam.title}</td>
							<td width="40%">${exam.url}</td>
							<td width="8%">
								<a href="listExamResults.action?examination.id=${exam.id}">查看成绩</a>
							</td>
						</tr>
					</s:iterator>	
				</tbody>
			</table>
			
			<nav class="text-center">
				<ul class="pagination">
				  <s:if test="#request.page.hasPrePage">
				    <li><a href="examinationResultList.action?currentPage=1">首页</a></li>
				    <li><a href="examinationResultList.action?currentPage=${page.currentPage - 1}">&laquo;上一页</a></li>
				    </s:if>
               	  <s:else>
               		<li><a href="#">首页</a></li>
				    <li><a href="#">&laquo;上一页</a></li> 
               	  </s:else>
               	  <s:if test="#request.page.hasNextPage">
				    <li><a href="examinationResultList.action?currentPage=${page.currentPage + 1}">下一页&raquo;</a></li>
				    <li><a href="examinationResultList.action?currentPage=${page.totalPage}">尾页</a></li>
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