package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

public class BoardDao {
	
	public List<BoardVo>getList(){
		// 0. import java.sql.*;

				Connection conn = null;

				PreparedStatement pstmt = null;

				ResultSet rs = null;
				
				List<BoardVo> getList = new ArrayList<BoardVo>();
				
				try {
					// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");
					// 2. Connection 얻어오기
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					conn = DriverManager.getConnection(url, "webdb", "webdb");
					// 3. SQL문 준비 / 바인딩 / 실행
					String query = " select boardno, " + 
							       "        title, " + 
							       "        name, " + 
							       "        views, " + 
							       "        date_created, " +
							       "        bo.no " +
							       " from users us, board bo " + 
							       " where us.no = bo.no " +
							       " order by boardno desc ";
							       

					pstmt = conn.prepareStatement(query);
					rs = pstmt.executeQuery();

					// 4.결과처리
					
					while (rs.next()) {
						
						BoardVo vo = new BoardVo();
						
						int boardno = rs.getInt("boardno");
						String title = rs.getString("title");
						String name = rs.getString("name");
						String views = rs.getString("views");
						String datecreated = rs.getString("date_created");
						int no = rs.getInt("no");
						
						vo.setBoardno(boardno);
						vo.setTitle(title);
						vo.setName(name);
						vo.setViews(views);
						vo.setDatecreated(datecreated);
						vo.setNo(no);
						
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

	public BoardVo getUser(int no) {
		// 0. import java.sql.*;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo boardVo = null;


		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select boardno, " + 
					       "       title, " + 
					       "       contents, " + 
					       "       views, " + 
					       "       date_created, " + 
					       "       no " + 
					       " from board " + 
					       " where no = ? ";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				no = rs.getInt(no);
				int boardno = rs.getInt("boardno");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String views = rs.getString("views");
				String datecreated = rs.getString("date_created");
				
				boardVo = new BoardVo();
				boardVo.setNo(no);
				boardVo.setBoardno(boardno);
				boardVo.setTitle(title);
				boardVo.setContents(contents);
				boardVo.setViews(views);
				boardVo.setDatecreated(datecreated);
				
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
		return boardVo;
}
	public void insert(BoardVo vo, UserVo no) {
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
				String auery = " insert into board " +
						       " values (seq_board_no.nextval, ?, ?, 0, sysdate, ?)"; 
				
				pstmt = conn.prepareStatement(auery);
				
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				pstmt.setInt(3, no.getNo());
				
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

	public BoardVo view(int boardno) {
		// 0. import java.sql.*;

		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;
		
		BoardVo boardVo = null;

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String auery = " select title, " + 
					       "        contents, " + 
					       "        no " + 
					       " from board where boardno= ? "; 
			
			pstmt = conn.prepareStatement(auery);
			
			pstmt.setInt(1, boardno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				boardVo = new BoardVo();
				
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				int no = rs.getInt("no");
				
				boardVo.setTitle(title);
				boardVo.setContents(contents);
				boardVo.setNo(no);
				
				boardVo.setBoardno(boardno);
				
			}
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
		return boardVo;	
		
	}
	public void upViwes(int boardno, int viwes) {
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
			String auery = " update board " + 
					       "        set views = ? " + 
					       " where  boardno = ? "; 
			
			pstmt = conn.prepareStatement(auery);
			
			pstmt.setInt(1, viwes);
			pstmt.setInt(2, boardno);
			
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
	
	public void delete(int boardno) {
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
					String query =" delete from board " + 
							      " where boardno = ? ";
					
					pstmt = conn.prepareStatement(query);
					
					pstmt.setInt(1, boardno);
					
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
	public int getuserNo(int no) {
		// 0. import java.sql.*;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		


		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " select no " + 
					       " from board " + 
					       " where boardno = ? ";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				no =rs.getInt(1);
				
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
		return no;
	}
	
	public void update (BoardVo boardVo) {
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
			String auery = " UPDATE board " + 
				           " Set title = ?, " +
				           " contents = ? " + 
					       " where boardno = ? ";     
					       
			pstmt = conn.prepareStatement(auery);

			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			pstmt.setInt(3, boardVo.getBoardno());
			
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