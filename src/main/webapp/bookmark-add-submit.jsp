<%@page import="zerobase_mission.DbBookmark"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="zerobase_mission.DbBookmarkList"%>
<%@page import="java.util.List"%>
<%@page import="zerobase_mission.Bookmark"%>

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
		DbBookmarkList dbBookmarkList = new DbBookmarkList();
		DbBookmark dbBookmark = new DbBookmark();
		
		String groupId = request.getParameter("groupId");
		List<Bookmark> list = dbBookmark.dbSelectName(groupId);
		String name = "";
		for(Bookmark bookmark : list){
			name = bookmark.getName();
		}
		String wifiId = request.getParameter("wifiId");
		
		dbBookmarkList.dbInsert(name, wifiId);
	%>
	<script type="text/javascript">
	window.onload = function() {
		alert('추가했습니다.');
		window.location.href = "bookmark-group-list.jsp";
	}
	</script>
</body>
</html>