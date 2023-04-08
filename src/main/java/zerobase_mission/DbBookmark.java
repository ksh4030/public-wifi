package zerobase_mission;

import java.beans.Statement;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class DbBookmark {
	public void dbInsert(String name, int seq ) {

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
			
			String sql = " insert into bookmark_group(bookmark_name, bookmark_seq, registered_date) values(?, ?, now()) ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, seq);
						
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

	public List<Bookmark> dbSelect() {
		String url = "jdbc:mariadb://localhost:3306/testdb3";
		String dbUserId = "testuser3";
		String dbPassword = "zerobase";
		
		List<Bookmark> bookmarkList = new ArrayList<>();
		
		
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
			
			String sql = " select * from bookmark_group ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Bookmark bookmark = new Bookmark();
				bookmark.setId(rs.getString("id"));
				bookmark.setName(rs.getString("bookmark_name"));
				bookmark.setSeq(rs.getString("bookmark_seq"));
				bookmark.setRegDate(rs.getString("registered_date"));
				bookmark.setUpdateDate(rs.getString("update_date"));
				
				
				bookmarkList.add(bookmark);
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
		
		return bookmarkList;
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
	
	public List<Bookmark> dbSelectName(String id) {
		String url = "jdbc:mariadb://localhost:3306/testdb3";
		String dbUserId = "testuser3";
		String dbPassword = "zerobase";
		
		List<Bookmark> bookmarkList = new ArrayList<>();
		
		
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
			
			String sql = " select * from bookmark_group where id=? ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Bookmark bookmark = new Bookmark();
				bookmark.setId(rs.getString("id"));
				bookmark.setName(rs.getString("bookmark_name"));
				bookmark.setSeq(rs.getString("bookmark_seq"));
				bookmark.setRegDate(rs.getString("registered_date"));
				bookmark.setUpdateDate(rs.getString("update_date"));
				
				
				bookmarkList.add(bookmark);
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
		
		return bookmarkList;
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
			
			String sql = " delete from bookmark_group where id = ? ; ";
			
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
