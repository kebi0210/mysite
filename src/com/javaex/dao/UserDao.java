package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {

	public void insert(UserVo vo) {
		// 0. import java.sql.*;
		
		
				Connection conn = null;

				PreparedStatement pstmt = null;

				//ResultSet rs = null;

				try {

					// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");

					// 2. Connection 얻어오기
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					conn = DriverManager.getConnection(url, "webdb", "webdb");

					// 3. SQL문 준비 / 바인딩 / 실행
					String auery = " insert into users " +
							       " values (seq_users_no.nextval, ?, ?, ?, ?)"; 
							       
					pstmt = conn.prepareStatement(auery);
					
					pstmt.setString(1, vo.getName());
					pstmt.setString(2, vo.getEmail());
					pstmt.setString(3, vo.getPassword());
					pstmt.setString(4, vo.getGender());
					
					int count = pstmt.executeUpdate();
				
					// 4.결과처리
					
					
				} catch (ClassNotFoundException e) {

					System.out.println("error: 드라이버 로딩 실패 - " + e);

				} catch (SQLException e) {

					System.out.println("error:" + e);

				} finally {

					// 5. 자원정리

					try {

						//if (rs != null) {

							//rs.close();

						//}

						if (pstmt != null) {

							pstmt.close();

						}

						if (conn != null) {

							conn.close();

						}

					} catch (SQLException e) {

						System.out.println("error:" + e);

			}
		}			
	}
	
	public UserVo getUser(String email,String password) {
		// 0. import java.sql.*;
		
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				UserVo userVo = null;


				try {

					// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					// 2. Connection 얻어오기
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					conn = DriverManager.getConnection(url, "webdb", "webdb");
					
					// 3. SQL문 준비 / 바인딩 / 실행
					String query = " select *" + 
							       " from users " + 
							       " where email= ? " + 
							       " and password = ? ";
					pstmt = conn.prepareStatement(query);
					
					pstmt.setString(1, email);
					pstmt.setString(2, password);
					
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						int no = rs.getInt("no");
						String name = rs.getString("name");
						
						userVo = new UserVo();
						userVo.setNo(no);
						userVo.setName(name);
					}
					
					
					// 4.결과처리

				} catch (ClassNotFoundException e) {

					System.out.println("error: 드라이버 로딩 실패 - " + e);

				} catch (SQLException e) {

					System.out.println("error:" + e);

				} finally {

					// 5. 자원정리

					try {

						if (pstmt != null) {

							pstmt.close();

						}

						if (conn != null) {

							conn.close();

						}

					} catch (SQLException e) {

						System.out.println("error:" + e);
					
			}

		}
				return userVo;
	}
	
	public UserVo getUser(int no) {
		// 0. import java.asql.*;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo userVo = null;


		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " select no, name, password, email, gender" + 
					       " from users " + 
					       " where no= ? ";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
			
				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
				userVo.setPassword(password);
				userVo.setEmail(email);
				userVo.setGender(gender);
			}
			
			// 4.결과처리

		} catch (ClassNotFoundException e) {

			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {

			System.out.println("error:" + e);

		} finally {

			// 5. 자원정리

			try {

				if (pstmt != null) {

					pstmt.close();

				}

				if (conn != null) {

					conn.close();

				}

			} catch (SQLException e) {

				System.out.println("error:" + e);
			
			}
		}
		return userVo;
}
	public void update (UserVo userVo) {
		// 0. import java.sql.*;

		Connection conn = null;

		PreparedStatement pstmt = null;

		//ResultSet rs = null;

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String auery = " UPDATE users " +
					       " Set name = ?, password = ?, gender = ? " +
					       " WHERE no = ? ";     
					       
			pstmt = conn.prepareStatement(auery);

			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getPassword());
			pstmt.setString(3, userVo.getGender());
			pstmt.setInt(4, userVo.getNo());
			
			pstmt.executeUpdate();

			// 4.결과처리
			

		} catch (ClassNotFoundException e) {

			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {

			System.out.println("error:" + e);

		} finally {

			// 5. 자원정리

			try {

				//if (rs != null) {

					//rs.close();

				//}

				if (pstmt != null) {

					pstmt.close();

				}

				if (conn != null) {

					conn.close();

				}

			} catch (SQLException e) {

				System.out.println("error:" + e);

			}
		}				
	}
}
