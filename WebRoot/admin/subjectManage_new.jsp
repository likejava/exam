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
  			if(confirm('ȷ��ɾ���������������ݣ�'))
  				window.location.href="subjectDelete.action?subject.subjectID="+id+"&examinationId="+examinationId;
  		}
  		
  		function search(){
  			var title = document.getElementById("searchsubjectTitle").value;
  			if(title == ""){
	    	//	alert("������������Ŀ");
	    	//	return;
	    	}
  			document.form1.submit();
  		}
  		
  		function autoimport(){
  			var reNum =/^\d+$/;
  			var strNum = document.getElementById("subjectNumId").value;;
  			if((reNum.test(strNum)) != true){
  				alert("��������ȷ��������");
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
				��${examination.title }���Ծ�༭	
			</h3>
			
			<h4 class="text-info text-center" style="word-break:break-all; word-wrap:break-all;">
				��ǰ�Ծ��ܷ֣�${examination.totalScore }	
			</h4>
			
			<br>
			<form class="form-horizontal" action="subjectSearch.action" method="post" name="form1">
				<div class="form-group" id="search" align="center">
					<input type="text" style="width:260px" class="form-control form-control-xxx" id="searchsubjectTitle" name="subjectTitle" value="${subjectTitle }" placeholder="������������Ŀ����">
	    			<button class="btn btn-success" type="button" onclick="search()">����</button>&nbsp;&nbsp;&nbsp;&nbsp;
    			</div>
    			<input name="examinationId" type="hidden" value="${examinationId }" id="examinationId">
			</form>
			
			<div class="form-horizontal">
				<div class="form-group" align="center">
					<button class="btn btn-success" type="button" onclick="toAdd()">��������</button>
	    			<button class="btn btn-success" type="button" onclick="toImport()">��������</button>
	    			<!-- <a href="toQuestionList?examinationId=${examinationId}"><button class="btn btn-success" type="button">��������</button></a> -->
	    			<button class="btn btn-success" data-toggle="modal" data-target="#myModal">��������</button>
				</div>
			</div>
			
			<table class="table table-hover table-bordered" style="width:80%;word-break:break-all; word-wrap:break-all;" align="center">
				<thead>
					<tr class="warning">
						<th>
							�����ֵ
						</th>
						<th>
							�������
						</th>
						<th>
							��ȷ��
						</th>
						<th>
							����
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
								<a href="subjectView.action?subject.subjectID=${sub.subjectID}">�鿴</a>&nbsp;
								<a href="subjectToUpdate.action?subject.subjectID=${sub.subjectID}">����</a>&nbsp;
								<a href="javascript:del(${sub.subjectID});">ɾ��</a>
							</td>
						</tr>
					</s:iterator>	
				</tbody>
			</table>
			
			<nav class="text-center">
				<ul class="pagination">
				  <s:if test="#request.page.hasPrePage">
				    <li><a href="subjectListByExamId.action?currentPage=1&examinationId=${examinationId}&subjectTitle=${subjectTitle}">��ҳ</a></li>
                	<li><a href="subjectListByExamId.action?currentPage=${page.currentPage - 1}&examinationId=${examinationId}&subjectTitle=${subjectTitle}">&laquo;��һҳ</a></li>
				  </s:if>
               	  <s:else>
               		<li><a href="#">��ҳ</a></li>
				    <li><a href="#">&laquo;��һҳ</a></li> 
               	  </s:else>
               	  <s:if test="#request.page.hasNextPage">
				    <li><a href="subjectListByExamId.action?currentPage=${page.currentPage + 1}&examinationId=${examinationId}&subjectTitle=${subjectTitle}">��һҳ&raquo;</a></li>
				    <li><a href="subjectListByExamId.action?currentPage=${page.totalPage}&examinationId=${examinationId}&subjectTitle=${subjectTitle}">βҳ</a></li>
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
	
	<!-- ģ̬��Modal�� -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                  &times;
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	              	��ѡ��������ӷ�ʽ
	            </h4>
	         </div>
	         <div class="modal-body">
				<div class="form-horizontal" align="center">
					<a href="toQuestionList?examinationId=${examinationId}"><button class="btn btn-primary" type="button">�ֶ�����</button></a>
				</div>
				<br>
				<div class="form-group" align="center">
					<button type="button" class="btn btn-primary" onclick="autoimport();">�Զ�����</button>
					<input type="text" style="width:200px" class="form-control form-control-xxx" id="subjectNumId" name="subjectNum" value="${subjectNum }" placeholder="�������Զ�������Ŀ��">
				</div>
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">�ر�</button>
	         </div>
	      </div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>

</body>
</html>