<%@page import="zerobase_mission.DbBookmark"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="zerobase_mission.DbHistory"%>
<%@page import="zerobase_mission.Bookmark"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>public wifi</title>
<style type="text/css">
	header{
		margin-bottom: 10px;
	}
	
	table{
		width: 100%;
		border-collapse: collapse;
	}
	
	table th {
		background-color: #00ad72;
		width: 15%;
		color: white;
		padding: 10px;
		border: 1px solid white;
	}
	
	td {
		padding-left: 10px;
		border: 1px solid #dddddd;
	}
	
	tfoot tr {
		
		width: 100%;
	}
	
	.btn-box{
		width: 100%;
		padding: 10px;
		text-align: center;
	}
</style>
</head>
<body>
	<h1>북마크 그룹 삭제</h1>
	<header>
		<a href="index.jsp">홈</a> |
		<a href="history.jsp">위치 히스토리 목록</a> |
		<a href="wifiInfo.jsp">Open API 와이파이 정보 가져오기</a> |
		<a href="bookmark-group-list.jsp">북마크 보기</a> |
		<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</header>
	<%	
		DbBookmark dbBookmark = new DbBookmark();
		String id = request.getParameter("id");
		List<Bookmark> bookmarkList = dbBookmark.dbSelectName(id);		
	%>
	
	<table>
	<% for(Bookmark bookmark : bookmarkList){%>
		<tr>
			<th>북마크 이름</th>
			<td><input type="text" value="<%=bookmark.getName() %>"></td>
		</tr>
		<tr>
			<th>순서</th>
			<td style="background-color: #f2f2f2"><input type="text" value="<%=bookmark.getSeq() %>"></td>
		</tr>
	<%} %>
	</table>
	<div class="btn-box">
		<a href="bookmark-group.jsp">돌아가기</a> 	|
		<input type="button" value="삭제" onclick="deleteGroup()">
	</div>
	<script type="text/javascript">
		function deleteGroup() {
			const id = <%=id %>
			location.href="bookmark-group-delete-submit.jsp?id=" + id;
		}
	</script>
</body>
</html>