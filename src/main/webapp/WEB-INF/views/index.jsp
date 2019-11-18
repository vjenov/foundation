<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!doctype html>
<html lang="en">
<head>
	<meta charset= "UTF-8">
	<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>
	<script src="<%=application.getContextPath()%>/resources/js/app.js"></script>
	<script src="<%=application.getContextPath()%>/resources/js/router.js"></script>
</head>
<body>
<div id="wrapper"></div>
	<script>
	app.run('<%=application.getContextPath()%>'); 
	</script>
</body>
</html>