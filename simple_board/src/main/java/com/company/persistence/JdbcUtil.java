package com.company.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcUtil {

	// 드라이브 로드
		static {
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// connection
		public static Connection getConnection() {
			try {
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "c##java";
				String password = "12345";
				Connection con =  DriverManager.getConnection(url, user, password);
				// autoCommit을 true 에서 false 
				con.setAutoCommit(false);
				return con;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		// commit
		public static void commit(Connection con) {
			try {
				con.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//rollback
		public static void rollback(Connection con) {
			try {
				con.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		

		// 자원 해제
		public static void close(Connection con) {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		public static void close(PreparedStatement pstmt) {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		public static void close(ResultSet rs) {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	
}
