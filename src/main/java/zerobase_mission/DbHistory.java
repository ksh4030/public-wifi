package zerobase_mission;

import java.beans.Statement;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class DbHistory {
	public String countWifi() {
		String url = "jdbc:mariadb://localhost:3306/testdb3";
		String dbUserId = "testuser3";
		String dbPassword = "zerobase";
		
		String count = "";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			
			String sql = " select count(*) as count from name_test ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				count = rs.getString("count");
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if(connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return count;
	}
	
	public void dbInsert(String lat, String lnt) {
		if(lat == null && lnt == null) {
			return;
		}

		String url = "jdbc:mariadb://localhost:3306/testdb3";
		String dbUserId = "testuser3";
		String dbPassword = "zerobase";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		System.out.println("--------------------------");
		
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			
			String sql = " insert into history_list(LAT, LNT, date) "
					+ " values (?, ?, now()) "
					+ " ; ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, lat);
			preparedStatement.setString(2, lnt);
			
			int affected = preparedStatement.executeUpdate();
			
			if(affected > 0) {
				System.out.println(" 성공 ");
			} else {
				System.out.println(" 실패 ");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if(connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public List<History> dbSelect() {
		
		List<History> historyList = new ArrayList<>();
		
		String url = "jdbc:mariadb://localhost:3306/testdb3";
		String dbUserId = "testuser3";
		String dbPassword = "zerobase";
		
		List<String> nameList = new ArrayList<>();
		String count = "";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			
			String sql = " select * from history_list ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String LAT = rs.getString("LAT");
				String LNT = rs.getString("LNT");
				String date = rs.getString("date");
				
				History history = new History();
				history.setId(id);
				history.setLAT(LAT);
				history.setLNT(LNT);
				history.setDate(date);
				
				historyList.add(history);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if(connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return historyList;
	}
	
	public void dbDelete(String id) {

		String url = "jdbc:mariadb://localhost:3306/testdb3";
		String dbUserId = "testuser3";
		String dbPassword = "zerobase";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		System.out.println("--------------------------");
		
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			
			String sql = " delete from history_list where id = ? ; ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			
			int affected = preparedStatement.executeUpdate();
			
			if(affected > 0) {
				System.out.println(" 성공 ");
			} else {
				System.out.println(" 실패 ");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if(connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
