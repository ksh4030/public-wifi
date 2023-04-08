package zerobase_mission;

import java.beans.Statement;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class DbBookmarkList {
	public void dbInsert(String name, String wifiId) {

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
		
		
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			
			String sql = " insert into bookmark_list (bookmark_name, wifi_name, reg_date) "
					+ " ( "
					+ " select A.bookmark_name, B.X_SWIFI_MAIN_NM, now() "
					+ " from bookmark_group A JOIN wifi_db B "
					+ " where A.bookmark_name = ? AND B.X_SWIFI_MGR_NO = ? "
					+ " ) "
					;
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, wifiId);
						
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

	public List<BookmarkList> dbSelect() {
		String url = "jdbc:mariadb://localhost:3306/testdb3";
		String dbUserId = "testuser3";
		String dbPassword = "zerobase";
		
		List<BookmarkList> bookmarkListList = new ArrayList<>();
		
		
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
			
			String sql = " select * from bookmark_list ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				BookmarkList bookmarkList = new BookmarkList();
				bookmarkList.setId(rs.getString("id"));
				bookmarkList.setBookmarkName(rs.getString("bookmark_name"));
				bookmarkList.setWifiName(rs.getString("wifi_name"));
				bookmarkList.setRegDate(rs.getString("reg_date"));
				
				
				bookmarkListList.add(bookmarkList);
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
		
		return bookmarkListList;
	}
	
	public void dbUpdate(String id, String name, int seq) {

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
			
			String sql = " update bookmark_group set bookmark_name = ?, bookmark_seq = ?, update_date = now() where id = ? ; " ;
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, seq);
			preparedStatement.setString(3, id);
						
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
	
	public List<BookmarkList> dbSelectName(String id) {
		String url = "jdbc:mariadb://localhost:3306/testdb3";
		String dbUserId = "testuser3";
		String dbPassword = "zerobase";
		
		List<BookmarkList> bookmarkListList = new ArrayList<>();
		
		
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
			
			String sql = " select * from bookmark_list where id=? ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				BookmarkList bookmarkList = new BookmarkList();
				bookmarkList.setId(rs.getString("id"));
				bookmarkList.setBookmarkName(rs.getString("bookmark_name"));
				bookmarkList.setWifiName(rs.getString("wifi_name"));
				bookmarkList.setRegDate(rs.getString("reg_date"));				
				
				bookmarkListList.add(bookmarkList);
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
		
		return bookmarkListList;
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
			
			String sql = " delete from bookmark_list where id = ? ; ";
			
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
