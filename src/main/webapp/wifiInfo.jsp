<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="zerobase_mission.DbWifi"%>
<%@page import="zerobase_mission.ApiExplorer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>public wifi</title>
	<style>
		body{
			width: 100%;
		}
		
		h1{
			width: 100%;
			text-align: center;
		}
		
		div{
			width: 100%;
			text-align: center;
		}
	</style>
</head>
<body>
	<%
		DbWifi dbWifi = new DbWifi();
		String num = dbWifi.countWifi();
		ApiExplorer apiExplorer = new ApiExplorer();
		
		if(num.equals("0")){
			apiExplorer.getWifiInfo();
		}
		
		num = dbWifi.countWifi();
	%>
	<h1><%=num %>개의 WIFI 정보를 정장적으로 저장하였습니다.</h1>
	<div>
		<a href="index.jsp">홈 으로 가기</a>
	</div>
</body>
</html>