<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>考试系统管理中心</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-2.2.3.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    
	<script type="text/javascript">
	function addToExam(id){
		 $.ajax({
            type:"POST",
            url:"addToExam.action",
            data:{questionId:id,examinationId:${examinationId}},
            datatype: "text",
            success:function(data){
	            if(data == "success"){
	            	$("#addButton"+id).css("background-color","#ddd");
	            	$("#addButton"+id).css("border-color","#ddd");
	            	$("#addButton"+id).text("已添加");
	            	$("#addButton"+id).removeAttr("onclick");
	            }else if(data == "exists"){
	           		alert("添加失败，该题目已存在");
	           		$("#addButton"+id).css("background-color","#ddd");
	            	$("#addButton"+id).css("border-color","#ddd");
	            	$("#addButton"+id).text("已存在");
	            	$("#addButton"+id).removeAttr("onclick");
	            }else {
	            	alert("添加失败");
	            }
            },
            //调用出错执行的函数
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert("添加失败");
            }         
         });
        }
  		function search(){
  			var title = document.getElementById("title").value;
  			document.form1.submit();
  		}
  	</script>
  	<style type="text/css">
  	.form-control-xxx {
	    width:200px;
	    display:inline;
	}
  	</style>
</head>
<body>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="text-info text-center" style="word-break:break-all; word-wrap:break-all;">
				题库	
			</h3>
			<br>
			<form class="form-horizontal" action="questionListSearch.action" method="post" name="form1">
				<div class="form-group" id="search" align="center">
					<input type="text" style="width:260px" class="form-control form-control-xxx" id="title" name="title" value="${title }" placeholder="请输入试题题目搜索">
	    			<button class="btn btn-success" type="button" onclick="search()">搜索</button>&nbsp;&nbsp;&nbsp;&nbsp;
    			</div>
    			<input type="hidden" name="examinationId" value="${examinationId }">
			</form>
			<table class="table table-hover table-bordered" style="width:80%;word-break:break-all; word-wrap:break-all;" align="center">
				<thead>
					<tr class="warning">
						<th>
							分值
						</th>
						<th>
							标题
						</th>
						<th>
							选项A
						</th>
						<th>
							选项B
						</th>
						<th>
							选项C
						</th>
						<th>
							选项D
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="questions" var="ques">
						<tr class="success">
							<td width="5%">${ques.score }</td>
							<td width="45%">${ques.subjectTitle}</td>
							<td width="10%">${ques.subjectOptionA}</td>
							<td width="10%">${ques.subjectOptionB}</td>
							<td width="10%">${ques.subjectOptionC}</td>
							<td width="10%">${ques.subjectOptionD}</td>
							<td width="10%">
	    			          <button class="btn btn-success" type="button" id="addButton${ques.id}" onclick="addToExam(${ques.id})" style="width:120px">添加到试卷</button>
							</td>
						</tr>
					</s:iterator>	
				</tbody>
			</table>
			
			<nav class="text-center">
				<ul class="pagination">
				  <s:if test="#request.page.hasPrePage">
				    <li><a href="questionList.action?currentPage=1&title=${title}">首页</a></li>
                	<li><a href="questionList.action?currentPage=${page.currentPage - 1}&title=${title}">&laquo;上一页</a></li>
				  </s:if>
               	  <s:else>
               		<li><a href="#">首页</a></li>
				    <li><a href="#">&laquo;上一页</a></li> 
               	  </s:else>
               	  <s:if test="#request.page.hasNextPage">
				    <li><a href="questionList.action?currentPage=${page.currentPage + 1}&title=${title}">下一页&raquo;</a></li>
				    <li><a href="questionList.action?currentPage=${page.totalPage}&title=${title}">尾页</a></li>
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