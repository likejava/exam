<%@ page language="java" pageEncoding="gbk"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Ä¬ÈÏÒ³Ãæ</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-2.2.3.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>

	<style type="text/css">
		body, p, td, th, input, select, button, dd, dl, dt, ul, ol, li, textarea {
			font-family: "Microsoft YaHei", Helvetica, Verdana, Arial, Tahoma;
			line-height: 1.5;
			font-size: 14px;
		}
		
		div {
			display:block;
		}
		
		.container-wrapper {
			background: transparent url('images/bg.jpg') repeat;
			
		}
		.container {	
			width: 960px;
			margin: 0 auto;
			min-height: 600px;
			padding-top: 20px;
			padding-bottom: 50px;
		}
	</style>

	<script type="text/javascript">
		document.onkeydown = function() {
			if (event.keyCode == 8) {
				var tagName = event.srcElement.tagName;
				if (tagName.toUpperCase() != "INPUT"
						&& tagName.toUpperCase() != "TEXTAREA") {
					event.returnValue = false;
				}
				if (tagName.toUpperCase() == "INPUT"
						&& event.srcElement.type.toUpperCase() != "TEXT") {
					event.returnValue = false;
				}
			}
		}
	</script>
</head>

<body>
	<div class="container-wrapper">
		<div id="main" class="container">
		</div>
	</div>
</body>
</html>