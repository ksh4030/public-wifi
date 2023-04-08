<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="zerobase_mission.DbHistory"%>

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
	<h1>북마크 그룹 추가</h1>
	<header>
		<a href="index.jsp">홈</a> |
		<a href="history.jsp">위치 히스토리 목록</a> |
		<a href="wifiInfo.jsp">Open API 와이파이 정보 가져오기</a> |
		<a href="bookmark-group-list.jsp">북마크 보기</a> |
		<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</header>
	
	<table>
		<tr>
			<th>북마크 이름</th>
			<td><input type="text" id="bookmark-name" value=""></td>
		</tr>
		<tr>
			<th>순서</th>
			<td style="background-color: #f2f2f2"><input type="text" id="bookmark-seq"></td>
		</tr>
	</table>
	<div class="btn-box"><input type="button" value="추가" onclick="addBookmark()"></div>
	
	<script type="text/javascript">
				
		function addBookmark() {
			const name = document.getElementById('bookmark-name').value;
			const seq = document.getElementById('bookmark-seq').value;
			if(name.length !== 0 && seq.length !== 0){
				location.href="bookmark-group-add-submit.jsp?name=" + name + "&seq=" + seq;
			} else {
				alert('값을 입력해 주세요.');
			}
		}
	</script>
</body>
</html>