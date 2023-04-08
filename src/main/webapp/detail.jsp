<%@page import="zerobase_mission.DbBookmark"%>
<%@page import="zerobase_mission.DbWifi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="zerobase_mission.DbHistory"%>
<%@page import="zerobase_mission.Wifi"%>
<%@page import="java.util.List"%>
<%@page import="zerobase_mission.Bookmark"%>

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
	
	.bg-color{
		background-color: #f2f2f2;
	}
		
</style>
</head>
<body>
	<%
		String id = request.getParameter("id");
		DbWifi dbWifi = new DbWifi();
		
		List<Wifi> detailList = dbWifi.dbSelectOne(id);
	%>
	<h1>와이파이 정보 구하기</h1>
	<header>
		<a href="index.jsp">홈</a> |
		<a href="history.jsp">위치 히스토리 목록</a> |
		<a href="wifiInfo.jsp">Open API 와이파이 정보 가져오기</a> |
		<a href=bookmark-group-list.jsp>북마크 보기</a> |
		<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</header>
	<div>
		<select onchange="selectValue(this)">
			<option value="">북마크 그룹 이름 선택</option>
			<%
				DbBookmark dbBookmark = new DbBookmark();
				List<Bookmark> bookmarkList = dbBookmark.dbSelect();
				
				for(Bookmark bookmark : bookmarkList){
			%>
				<option value="<%=bookmark.getId() %>"><%=bookmark.getName() %></option>
			<% } %>
		</select>
		<input type="button" value="북마크 추가하기" onclick="addBookmark()">
	</div>
	<div>
		<table>
			<%
				for(Wifi detail : detailList){
			 %>
				<tr>
					<th>거리(KM)</th>
					<td><%=detail.getDistance() %></td>
				</tr>
				<tr>
					<th>관리번호</th>
					<td class="bg-color"><%=detail.getX_SWIFI_MGR_NO() %></td>
				</tr>
				<tr>
					<th>자치구</th>
					<td><%=detail.getX_SWIFI_WRDOFC() %></td>
				</tr>
				<tr>
					<th>와이파이명</th>
					<td class="bg-color"><%=detail.getX_SWIFI_MAIN_NM() %></td>
				</tr>
				<tr>
					<th>도로명주소</th>
					<td><%=detail.getX_SWIFI_ADRES1() %></td>
				</tr>
				<tr>
					<th>상세주소</th>
					<td class="bg-color"><%=detail.getX_SWIFI_ADRES2() %></td>
				</tr>
				<tr>
					<th>설치위치(층)</th>
					<td><%=detail.getX_SWIFI_INSTL_FLOOR() %></td>
				</tr>
				<tr>
					<th>설치유형</th>
					<td class="bg-color"><%=detail.getX_SWIFI_INSTL_TY() %></td>
				</tr>
				<tr>
					<th>설치기관</th>
					<td><%=detail.getX_SWIFI_INSTL_MBY() %></td>
				</tr>
				<tr>
					<th>서비스구분</th>
					<td class="bg-color"><%=detail.getX_SWIFI_SVC_SE() %></td>
				</tr>
				<tr>
					<th>망종류</th>
					<td><%=detail.getX_SWIFI_CMCWR() %></td>
				</tr>
				<tr>
					<th>설치년도</th>
					<td class="bg-color"><%=detail.getX_SWIFI_CNSTC_YEAR() %></td>
				</tr>
				<tr>
					<th>실내외구분</th>
					<td><%=detail.getX_SWIFI_INOUT_DOOR() %></td>
				</tr>
				<tr>
					<th>WIFI접속환경</th>
					<td class="bg-color"><%=detail.getX_SWIFI_REMARS3() %></td>
				</tr>
				<tr>
					<th>X좌표</th>
					<td><%=detail.getLAT() %></td>
				</tr>
				<tr>
					<th>Y좌표</th>
					<td class="bg-color"><%=detail.getLNT() %></td>
				</tr>
				<tr>
					<th>작업일자</th>
					<td><%=detail.getWORK_DTTM() %></td>
				</tr>
			<%
				}
			%>
		</table>
	</div>
	
	<script type="text/javascript">
		let groupId;
		
		const selectValue = (target) => {
			groupId = target.value;
			console.log(groupId);
		}
		
		function addBookmark() {
			const id = new String("<%=id %>");
			location.href="bookmark-add-submit.jsp?groupId=" + groupId + "&wifiId=" + id;
		}
	</script>
</body>
</html>