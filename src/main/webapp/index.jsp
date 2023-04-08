<%@page import="zerobase_mission.GetDistance"%>
<%@page import="zerobase_mission.DbHistory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="zerobase_mission.ApiExplorer"%>
<%@page import="zerobase_mission.DbWifi"%>
<%@page import="zerobase_mission.Wifi"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>public wifi</title>
	<style>
		.flex-box{
			margin-top: 12px;
			display: flex;
		}
		.flex-box div, input {
			margin-right: 7px;
		}
		
		.table-box{
			width: 100%;
			margin-top: 15px;
		}
		
		table{
			width: 100%;
			border-collapse: collapse;
		}
		
		.table-header {
			background-color: #00ad72;
			width: 100%;
		}
		
		.table-header tr{
			color: white;
		}
		
		.table-header th{
			font-size: 13px;
			font-weight: 10px;
			padding: 10px;
			border: 1px solid white;
		}
		
		tbody{
			height: 100px;
			width: 100%;
		}
		
		tbody td{
			font-size: 13px;
			padding: 10px;
			border: 1px solid #dddddd;
		}
		
		tbody tr:nth-child(odd){
			background-color: #f2f2f2;
		}
		
		#command-msg{
			text-align: center;
		}
		
		
	</style>
</head>
<body>
	<%
		String lat = request.getParameter("lat");
		String lnt = request.getParameter("lnt");
		DbHistory dbHistory = new DbHistory();
		dbHistory.dbInsert(lat, lnt);
		DbWifi dbWifi = new DbWifi();
		
		if(lat != null && lnt != null){
			GetDistance getDistance = new GetDistance();
			List<Wifi> keyDistList = dbWifi.dbAllSelect();
			
			for(Wifi item : keyDistList){
				double kilo = getDistance.distance(lat, lnt, item.getLAT(), item.getLNT());
				dbWifi.dbUpdate(item.getX_SWIFI_MGR_NO(), kilo);
			}
			
		}
	%>
	<h1>와이파이 정보 구하기</h1>
	<div>
		<a href="index.jsp">홈</a> |
		<a href="history.jsp">위치 히스토리 목록</a> |
		<a href="wifiInfo.jsp">Open API 와이파이 정보 가져오기</a> |
		<a href="bookmark-group-list.jsp">북마크 보기</a> |
		<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
	<form class = "flex-box" action="index.jsp">
		<div>LAT : </div>
		<input type="text" id="lat" value="0.0" name="lat">
		
		<div> , </div>
		
		<div>LNT : </div>
		<input type="text" id="lnt" value="0.0" name="lnt">
		
		<input type="button" value="내 위치 가져오기" onclick="getLocation()">
		<input type="button" value="근처 WIFI 정보 보기" onclick="wifiInfo()">
	</form>
	<div class = "table-box">
		<table>
			<thead class = "table-header">
				<tr>
					<th>거리(Km)</th>
					<th>관리번호</th>
					<th>자치구</th>
					<th>와이파이명</th>
					<th>도로명주소</th>
					<th>상세주소</th>
					<th>설치위치(층)</th>
					<th>설치유형</th>
					<th>설치기관</th>
					<th>서비스구분</th>
					<th>망종류</th>
					<th>설치년도</th>
					<th>실내외구분</th>
					<th>WIFI접속환경</th>
					<th>X좌표</th>
					<th>Y좌표</th>
					<th>작업일자</th>
				</tr>
			</thead>
			
			<tbody>
			<tr>
				<%if(lat == null && lnt == null){ %>
				
				<tr id="command-msg">
					<td colspan = '17'>위치 정보를 입력한 후에 조회해 주세요.</td>
				</tr>
				<% } %>
				<%				
					int num = 0;
					if(lat != null && lnt != null){
						List<Wifi> wifiList = dbWifi.dbSelect();
						
						for(Wifi wifi : wifiList){
							
				%>
					<tr id="contents">
						<td><%=wifi.getDistance() %></td>
						<td><%=wifi.getX_SWIFI_MGR_NO() %></td>
						<td><%=wifi.getX_SWIFI_WRDOFC() %></td>
						<td>
							<a href="detail.jsp?id=<%=wifi.getX_SWIFI_MGR_NO() %> ">
								<%=wifi.getX_SWIFI_MAIN_NM() %>
							</a>
						</td>
						<td><%=wifi.getX_SWIFI_ADRES1() %></td>
						<td><%=wifi.getX_SWIFI_ADRES2() %></td>
						<td><%=wifi.getX_SWIFI_INSTL_FLOOR() %></td>
						<td><%=wifi.getX_SWIFI_INSTL_TY() %></td>
						<td><%=wifi.getX_SWIFI_INSTL_MBY() %></td>
						<td><%=wifi.getX_SWIFI_SVC_SE() %></td>
						<td><%=wifi.getX_SWIFI_CMCWR() %></td>
						<td><%=wifi.getX_SWIFI_CNSTC_YEAR() %></td>
						<td><%=wifi.getX_SWIFI_INOUT_DOOR() %></td>
						<td><%=wifi.getX_SWIFI_REMARS3() %></td>
						<td><%=wifi.getLAT() %></td>
						<td><%=wifi.getLNT() %></td>
						<td><%=wifi.getWORK_DTTM() %></td>
					</tr>
				<%
					}
					}
				%>
				
			</tr>
			</tbody>
		</table>
	</div>
	
	<script type="text/javascript">
		function getLocation() {
			navigator.geolocation.getCurrentPosition(function(pos) {
				console.log(pos);
			    const latitude = pos.coords.latitude;
			    const longitude = pos.coords.longitude;
			    
			    document.getElementById("lat").value = latitude;
			    document.getElementById("lnt").value = longitude;
			});
		}
		
		function wifiInfo() {
			const latitude = document.getElementById("lat").value;
			const longitude = document.getElementById("lnt").value;
			
			if(latitude !== "0.0" && longitude !== "0.0"){
				const lat = document.getElementById("lat").value;
				const lnt = document.getElementById("lnt").value;
				location.href="index.jsp?lat=" + lat + "&lnt=" + lnt;
			} else {
				alert('내 위치 가져오기를 먼저 실행하세요.');
			}
		}
	</script>
</body>
</html>
