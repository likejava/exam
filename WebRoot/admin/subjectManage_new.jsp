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
  		function toAdd(){
  		    var examinationId = document.getElementById("examinationId").value;
  			window.location.href="subjectToAdd.action?examinationId="+examinationId;
  		}
  		
  		function toImport(){
  		    var examinationId = document.getElementById("examinationId").value;
  			window.location.href="subjectToImport.action?examinationId="+examinationId;
  		}
  		
  		function del(id){
  			var examinationId = document.getElementById("examinationId").value;
  			if(confirm('确定删除该试题所有数据？'))
  				window.location.href="subjectDelete.action?subject.subjectID="+id+"&examinationId="+examinationId;
  		}
  		
  		function search(){
  			var title = document.getElementById("searchsubjectTitle").value;
  			if(title == ""){
	    	//	alert("请输入搜索题目");
	    	//	return;
	    	}
  			document.form1.submit();
  		}
  		
  		function autoimport(){
  			var reNum =/^\d+$/;
  			var strNum = document.getElementById("subjectNumId").value;;
  			if((reNum.test(strNum)) != true){
  				alert("请输入正确的题数！");
  			}else{
  				var id = "${examination.id}";
  				var examinationId;
  				if(id.length != 0){
  					examinationId = id;
  				}else{
  					examinationId = document.getElementById("examinationId").value;
  				}

  				var subjectNum = document.getElementById("subjectNumId").value;
  				window.location.href="addRandomSubject.action?examinationId="+examinationId+"&subjectNum="+subjectNum;
  			}
  		}
  		
  		function load(){
  			var msg = "${message}";
  			if(msg.length != 0){
  				alert(msg);
  				msg = null;;
  			}
  		}
  	</script>
  	<style type="text/css">
  	.form-control-xxx {
	    width:200px;
	    display:inline;
	}
  	</style>
</head>
<body onload="load();">

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="text-info text-center" style="word-break:break-all; word-wrap:break-all;">
				《${examination.title }》试卷编辑	
			</h3>
			
			<h4 class="text-info text-center" style="word-break:break-all; word-wrap:break-all;">
				当前试卷总分：${examination.totalScore }	
			</h4>
			
			<br>
			<form class="form-horizontal" action="subjectSearch.action" method="post" name="form1">
				<div class="form-group" id="search" align="center">
					<input type="text" style="width:260px" class="form-control form-control-xxx" id="searchsubjectTitle" name="subjectTitle" value="${subjectTitle }" placeholder="请输入试题题目搜索">
	    			<button class="btn btn-success" type="button" onclick="search()">搜索</button>&nbsp;&nbsp;&nbsp;&nbsp;
    			</div>
    			<input name="examinationId" type="hidden" value="${examinationId }" id="examinationId">
			</form>
			
			<div class="form-horizontal">
				<div class="form-group" align="center">
					<button class="btn btn-success" type="button" onclick="toAdd()">新增试题</button>
	    			<button class="btn btn-success" type="button" onclick="toImport()">批量导入</button>
	    			<!-- <a href="toQuestionList?examinationId=${examinationId}"><button class="btn btn-success" type="button">从题库添加</button></a> -->
	    			<button class="btn btn-success" data-toggle="modal" data-target="#myModal">从题库添加</button>
				</div>
			</div>
			
			<table class="table table-hover table-bordered" style="width:80%;word-break:break-all; word-wrap:break-all;" align="center">
				<thead>
					<tr class="warning">
						<th>
							试题分值
						</th>
						<th>
							试题标题
						</th>
						<th>
							正确答案
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="subjects" var="sub">
						<tr class="success">
							
							<td width="10%">${sub.score }</td>
							<td width="55%">${sub.subjectTitle}</td>
							<td width="10%">${sub.subjectAnswer}</td>
							<td width="15%">
								<a href="subjectView.action?subject.subjectID=${sub.subjectID}">查看</a>&nbsp;
								<a href="subjectToUpdate.action?subject.subjectID=${sub.subjectID}">更新</a>&nbsp;
								<a href="javascript:del(${sub.subjectID});">删除</a>
							</td>
						</tr>
					</s:iterator>	
				</tbody>
			</table>
			
			<nav class="text-center">
				<ul class="pagination">
				  <s:if test="#request.page.hasPrePage">
				    <li><a href="subjectListByExamId.action?currentPage=1&examinationId=${examinationId}&subjectTitle=${subjectTitle}">首页</a></li>
                	<li><a href="subjectListByExamId.action?currentPage=${page.currentPage - 1}&examinationId=${examinationId}&subjectTitle=${subjectTitle}">&laquo;上一页</a></li>
				  </s:if>
               	  <s:else>
               		<li><a href="#">首页</a></li>
				    <li><a href="#">&laquo;上一页</a></li> 
               	  </s:else>
               	  <s:if test="#request.page.hasNextPage">
				    <li><a href="subjectListByExamId.action?currentPage=${page.currentPage + 1}&examinationId=${examinationId}&subjectTitle=${subjectTitle}">下一页&raquo;</a></li>
				    <li><a href="subjectListByExamId.action?currentPage=${page.totalPage}&examinationId=${examinationId}&subjectTitle=${subjectTitle}">尾页</a></li>
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
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                  &times;
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	              	请选择从题库添加方式
	            </h4>
	         </div>
	         <div class="modal-body">
				<div class="form-horizontal" align="center">
					<a href="toQuestionList?examinationId=${examinationId}"><button class="btn btn-primary" type="button">手动导入</button></a>
				</div>
				<br>
				<div class="form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="autoimport();">自动导入</button>
					<input type="text" style="width:200px" class="form-control form-control-xxx" id="subjectNumId" name="subjectNum" value="${subjectNum }" placeholder="请输入自动生成题目数">
				</div>
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	         </div>
	      </div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>

</body>
</html>