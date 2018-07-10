<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>����ϵͳ��������</title>
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
	            	$("#addButton"+id).text("�����");
	            	$("#addButton"+id).removeAttr("onclick");
	            }else if(data == "exists"){
	           		alert("���ʧ�ܣ�����Ŀ�Ѵ���");
	           		$("#addButton"+id).css("background-color","#ddd");
	            	$("#addButton"+id).css("border-color","#ddd");
	            	$("#addButton"+id).text("�Ѵ���");
	            	$("#addButton"+id).removeAttr("onclick");
	            }else {
	            	alert("���ʧ��");
	            }
            },
            //���ó���ִ�еĺ���
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert("���ʧ��");
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
				���	
			</h3>
			<br>
			<form class="form-horizontal" action="questionListSearch.action" method="post" name="form1">
				<div class="form-group" id="search" align="center">
					<input type="text" style="width:260px" class="form-control form-control-xxx" id="title" name="title" value="${title }" placeholder="������������Ŀ����">
	    			<button class="btn btn-success" type="button" onclick="search()">����</button>&nbsp;&nbsp;&nbsp;&nbsp;
    			</div>
    			<input type="hidden" name="examinationId" value="${examinationId }">
			</form>
			<table class="table table-hover table-bordered" style="width:80%;word-break:break-all; word-wrap:break-all;" align="center">
				<thead>
					<tr class="warning">
						<th>
							��ֵ
						</th>
						<th>
							����
						</th>
						<th>
							ѡ��A
						</th>
						<th>
							ѡ��B
						</th>
						<th>
							ѡ��C
						</th>
						<th>
							ѡ��D
						</th>
						<th>
							����
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
	    			          <button class="btn btn-success" type="button" id="addButton${ques.id}" onclick="addToExam(${ques.id})" style="width:120px">��ӵ��Ծ�</button>
							</td>
						</tr>
					</s:iterator>	
				</tbody>
			</table>
			
			<nav class="text-center">
				<ul class="pagination">
				  <s:if test="#request.page.hasPrePage">
				    <li><a href="questionList.action?currentPage=1&title=${title}">��ҳ</a></li>
                	<li><a href="questionList.action?currentPage=${page.currentPage - 1}&title=${title}">&laquo;��һҳ</a></li>
				  </s:if>
               	  <s:else>
               		<li><a href="#">��ҳ</a></li>
				    <li><a href="#">&laquo;��һҳ</a></li> 
               	  </s:else>
               	  <s:if test="#request.page.hasNextPage">
				    <li><a href="questionList.action?currentPage=${page.currentPage + 1}&title=${title}">��һҳ&raquo;</a></li>
				    <li><a href="questionList.action?currentPage=${page.totalPage}&title=${title}">βҳ</a></li>
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