<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/head.css" type="text/css" rel="stylesheet" />
<script src="../js/jquery-2.2.3.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$("div.sec_nav lable span").hover(function(){
				$(this).css("color","#ff0");
			});
	})
	
</script>

<title></title>
</head>

<body>
	<div class="head_container">
	<div class="container">
		<div class="logo">
			<img src="../images/logo_1.png" />
		</div>
    	<div class="re_index" onclick="parent.rightdownFrame.location='index.jsp'" title="返回首页"></div>
    	<div class="sec_nav">
        	<label>欢迎您 </label>
        </div>
    	
   	 	</div>
    	<div style="clear: both;"></div>
	</div>
</body>
</html>
