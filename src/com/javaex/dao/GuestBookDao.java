package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestBookVo;



public class GuestBookDao {
	public void insert(GuestBookVo vo) {
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
			String auery = " insert into guestbook " + 
					       " values (seq_guestbook_no.nextval, ?, ?, ?,sysdate)";
			pstmt = conn.prepareStatement(auery);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());

			int count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count +"건 저장");

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
	public List<GuestBookVo>getList(){
		// 0. import java.sql.*;

				Connection conn = null;

				PreparedStatement pstmt = null;

				ResultSet rs = null;
				List<GuestBookVo> getList = new ArrayList<GuestBookVo>();
				try {
					// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");
					// 2. Connection 얻어오기
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					conn = DriverManager.getConnection(url, "webdb", "webdb");
					// 3. SQL문 준비 / 바인딩 / 실행
					String query = " select no, " +
						           " name, " +
						           " password," +
						           " content, " +
						           " reg_date " +
						           " from guestbook " +
						           " order by no desc ";

					pstmt = conn.prepareStatement(query);
					rs = pstmt.executeQuery();

					// 4.결과처리
					
					while (rs.next()) {
						GuestBookVo vo = new GuestBookVo();
						
						int no = rs.getInt("no");
						String name = rs.getString("name");
						String password = rs.getString("password");
						String regDate = rs.getString("reg_date");
						String content = rs.getString("content");
						vo.setNo(no);
						vo.setName(name);
						vo.setPassword(password);
						vo.setRegdate(regDate);
						vo.setContent(content);
					   
						
						getList.add(vo);
						
						//System.out.println("작가 넘버:" + authorId + "|" + "작가 이름:" + authorName + "|" + "주 소:" + authorDesc);
					}

				} catch (ClassNotFoundException e) {

					System.out.println("error: 드라이버 로딩 실패 - " + e);

				} catch (SQLException e) {

					System.out.println("error:" + e);

				} finally {

					// 5. 자원정리

					try {

						if (rs != null) {

							rs.close();

						}

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
				return getList;
	}	
	public void delete(int no,String pass) {
		// 0. import java.sql.*;
		
				Connection conn = null;

				PreparedStatement pstmt = null;


				try {

					// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					// 2. Connection 얻어오기
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					conn = DriverManager.getConnection(url, "webdb", "webdb");
					
					// 3. SQL문 준비 / 바인딩 / 실행
					String query =" delete from guestbook " + 
								  " where no = ?" +
								  " and password = ? ";
					
					pstmt = conn.prepareStatement(query);
					
					pstmt.setInt(1, no);
					pstmt.setString(2, pass);
					
					int count = pstmt.executeUpdate();
					
					System.out.println(count +"건 삭제완료");
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
	}
}