<%@page import="zerobase_mission.DbBookmarkList"%>
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
		String id = request.getParameter("id");
		DbBookmarkList dbBookmarkList = new DbBookmarkList();
		dbBookmarkList.dbDelete(id);
	%>
	
	<script type="text/javascript">
	window.onload = function() {
		alert('삭제했습니다.');
		window.location.href = "bookmark-group-list.jsp";
	}
	</script>
</body>
</html>