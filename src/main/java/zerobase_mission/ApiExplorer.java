package zerobase_mission;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import com.google.gson.*;
import java.util.List;
import java.util.ArrayList;

public class ApiExplorer {
	public void getWifiInfo() throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/6c666c4349676a61373252784b744b/json/TbPublicWifiInfo/1/1000/"); /*URL*/
		urlBuilder.append("/" +  URLEncoder.encode("인증키","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
		urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
		urlBuilder.append("/" + URLEncoder.encode("CardSubwayStatsNew","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
		urlBuilder.append("/" + URLEncoder.encode("5","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
		
		// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
		urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
		BufferedReader rd;

		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
				sb.append(line);
		}
		
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		
		String[] ary = sb.toString().split("row");
		
		 String jsonStr = ary[1].substring(2, ary[1].length() - 2);

		 System.out.println(jsonStr);
		 
		 JsonParser jsonParser = new JsonParser();
		 JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonStr);
		 
		 
		 
		 Wifi wifi = new Wifi();
		 DbWifi dbWifi = new DbWifi(); 
		 
		 for(int i=0; i<jsonArray.size(); i++) {
			 JsonObject object = (JsonObject) jsonArray.get(i);
			 
			 String a = object.get("X_SWIFI_MGR_NO").getAsString().replaceAll("-", " ");
			 a = a.trim();
			 
			 wifi.setX_SWIFI_MGR_NO(a);
			 wifi.setX_SWIFI_WRDOFC(object.get("X_SWIFI_WRDOFC").getAsString());
			 wifi.setX_SWIFI_MAIN_NM(object.get("X_SWIFI_MAIN_NM").getAsString()); 
			 wifi.setX_SWIFI_ADRES1(object.get("X_SWIFI_ADRES1").getAsString());
			 wifi.setX_SWIFI_ADRES2(object.get("X_SWIFI_ADRES2").getAsString());
			 wifi.setX_SWIFI_INSTL_FLOOR(object.get("X_SWIFI_INSTL_FLOOR").getAsString());
			 wifi.setX_SWIFI_INSTL_TY(object.get("X_SWIFI_INSTL_TY").getAsString());
			 wifi.setX_SWIFI_INSTL_MBY(object.get("X_SWIFI_INSTL_MBY").getAsString());
			 wifi.setX_SWIFI_SVC_SE(object.get("X_SWIFI_SVC_SE").getAsString());
			 wifi.setX_SWIFI_CMCWR(object.get("X_SWIFI_CMCWR").getAsString());
			 wifi.setX_SWIFI_CNSTC_YEAR(object.get("X_SWIFI_CNSTC_YEAR").getAsString());
			 wifi.setX_SWIFI_INOUT_DOOR(object.get("X_SWIFI_INOUT_DOOR").getAsString());
			 wifi.setX_SWIFI_REMARS3(object.get("X_SWIFI_REMARS3").getAsString());
			 wifi.setLAT(object.get("LNT").getAsString());
			 wifi.setLNT(object.get("LAT").getAsString());
			 wifi.setWORK_DTTM(object.get("WORK_DTTM").getAsString());
			 
			 dbWifi.dbInsert(wifi);
		 }
		
	}
}