<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title></title>
</head>
<frameset rows="63,*,26" cols="*" frameborder="0" framespacing="0" name="allFrame">
	<frame src="head.jsp" noresize="noresize" id="headFrame" name="headFrame" bordercolor="#000000" scrolling="no"/>
    <frameset cols="199,*" name="middleFrame">
    	<frame src="left_new.jsp" id="leftFrame" name="leftFrame" noresize="noresize" scrolling="auto" />
		<frame src="right_new.jsp"  noresize="noresize" id="rightFrame" name="rightFrame"/>
    </frameset>
    <frame src="foot.html" noresize="noresize"/>
</frameset>
</html>