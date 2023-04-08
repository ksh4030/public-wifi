<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="zerobase_mission.DbBookmark"%>

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
		DbBookmark dbBookmark = new DbBookmark();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		dbBookmark.dbUpdate(id, name, seq);
	%>
	
	<script type="text/javascript">
	window.onload = function() {
		alert('수정했습니다.');
		window.location.href = "bookmark-group.jsp";
	}
	</script>
</body>
</html>