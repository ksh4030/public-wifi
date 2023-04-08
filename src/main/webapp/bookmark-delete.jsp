<%@page import="zerobase_mission.DbBookmarkList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="zerobase_mission.DbHistory"%>
<%@page import="java.util.List"%>
<%@page import="zerobase_mission.BookmarkList"%>

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
	<h1>북마크 삭제</h1>
	<header>
		<a href="index.jsp">홈</a> |
		<a href="history.jsp">위치 히스토리 목록</a> |
		<a href="wifiInfo.jsp">Open API 와이파이 정보 가져오기</a> |
		<a href="bookmark-group-list.jsp">북마크 보기</a> |
		<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</header>
	<p>북마크를 삭제하시겠습니까?</p>
	<%
		String id = request.getParameter("id");
		DbBookmarkList dbBookmarkList = new DbBookmarkList();
		List<BookmarkList> bookmarkList = dbBookmarkList.dbSelectName(id);
	%>
	<%for(BookmarkList list : bookmarkList) {%>
	<table>
		<tr>
			<th>북마크 이름</th>
			<td><%=list.getBookmarkName() %></td>
		</tr>
		<tr>
			<th>와이파이명</th>
			<td style="background-color: #f2f2f2"><%=list.getWifiName() %></td>
		</tr>
		<tr>
			<th>등록일자</th>
			<td><%=list.getRegDate() %></td>
		</tr>
	</table>
	<%} %>
	<div class="btn-box">
		<a href="bookmark-group-list.jsp">돌아가기</a> 	|
		<input type="button" value="삭제" onclick="deleteBookmark()">
	</div>
	<script type="text/javascript">
		function deleteBookmark() {
			const id = <%=id %>;
			location.href="bookmark-delete-submit.jsp?id=" + id;
		}
	</script>
</body>
</html>