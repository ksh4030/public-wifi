<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="zerobase_mission.DbHistory"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>public wifi</title>
<style type="text/css">
	body{
		background-color: #363538;
	}
</style>
</head>
<body>
	<%
		String id = request.getParameter("id");
		DbHistory dbHistory = new DbHistory();
		dbHistory.dbDelete(id);
	%>
	
	<script type="text/javascript">
		window.onload = function() {
			alert('삭제했습니다.');
			window.location.href = "history.jsp";
		}
	</script>
</body>
</html>