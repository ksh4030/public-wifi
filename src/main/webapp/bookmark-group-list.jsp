<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="zerobase_mission.DbBookmarkList"%>
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
		
	thead {
			background-color: #00ad72;
			width: 100%;
		}
		
	thead tr{
			color: white;
		}
	
	thead th{
		padding: 10px;
		border: 1px solid white;
	}
	
	tbody td{
		border: 1px solid #dddddd;
	}
</style>
</head>
<body>
	<h1>북마크 목록</h1>
	<header>
		<a href="index.jsp">홈</a> |
		<a href="history.jsp">위치 히스토리 목록</a> |
		<a href="wifiInfo.jsp">Open API 와이파이 정보 가져오기</a> |
		<a href="bookmark-group-list.jsp">북마크 보기</a> |
		<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</header>
	
	<%
		DbBookmarkList dbBookmarkList = new DbBookmarkList();
		List<BookmarkList> list = dbBookmarkList.dbSelect();
	%>
	
	<div>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>북마크 이름</th>
					<th>와이파이명</th>
					<th>등록일자</th>
					<th>비고</th>
				</tr>
			</thead>
			
			<tbody>
			<%for(BookmarkList bookmark : list){ %>
				<tr>
					<td><%=bookmark.getId() %></td>
					<td><%=bookmark.getBookmarkName() %></td>
					<td><%=bookmark.getWifiName() %></td>
					<td><%=bookmark.getRegDate() %></td>
					<td style="text-align: center">
						<a href="bookmark-delete.jsp?id=<%=bookmark.getId() %>">삭제</a>
					</td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">

	</script>
</body>
</html>