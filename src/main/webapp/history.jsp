<%@page import="zerobase_mission.DbHistory"%>
<%@page import="zerobase_mission.History"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>public wifi</title>
	<style type="text/css">
		.table-box{
			width: 100%;
			margin-top: 15px;
		}
		
		table{
			width: 100%;
			border-collapse: collapse;
		}
		
		table thead{
			background-color: #00ad72;
			width: 100%;
		}
		
		table thead tr{
			color: white;
		}
		
		table thead tr th{
			font-size: 13px;
			font-weight: 10px;
			padding: 10px;
			border: 1px solid white;
		}
		
		tbody td{
			font-size: 15px;
			padding: 7px;
			border: 1px solid #dddddd;
		}
		
		tbody tr td:last-child {
			text-align: center;
		}
		
		tbody tr:nth-child(odd){
			background-color: #f2f2f2;
		}
	</style>
</head>
<body>
	<%
		DbHistory dbHistory = new DbHistory();
		
	%>
	<h1>위치 히스토리 목록</h1>
	<div>
		<a href="index.jsp">홈</a> |
		<a href="history.jsp">위치 히스토리 목록</a> |
		<a href="wifiInfo.jsp">Open API 와이파이 정보 가져오기</a> |
		<a href="bookmark-group-list.jsp">북마크 보기</a> |
		<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
	<div class="table-box">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>X좌표</th>
					<th>Y좌표</th>
					<th>조회일자</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<%
						List<History> historyList = dbHistory.dbSelect();
						
						for(History history : historyList) {
					%>
					<tr>
						<td><%=history.getId() %></td>
						<td><%=history.getLAT() %></td>
						<td><%=history.getLNT() %></td>
						<td><%=history.getDate() %></td>
						<td><input type="button" value="삭제" id="<%=history.getId() %>" onclick="deleteHistory(this.id)"></td>
					</tr>
					<%
						}
					%>
				</tr>
			</tbody>
		</table>
	</div>
	
	<script type="text/javascript">
		function deleteHistory(id) {
			location.href="history-delete-submit.jsp?id=" + id;
		}
	</script>
</body>
</html>