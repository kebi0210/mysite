package com.javaex.contoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		
		if("list".equals(actionName)) {
			//System.out.println("list 진입");
			BoardDao boardDao =new BoardDao();
			
			List<BoardVo> list = boardDao.getList();
			request.setAttribute("list", list);
			
			WebUtil.froward(request, response, "/WEB-INF/views/board/list.jsp");	
			
				
		}else if("writefrom".equals(actionName)) {
			
			//System.out.println("writefrom 진입");
			
			WebUtil.froward(request, response,"/WEB-INF/views/board/write.jsp");
			
		}else if("write".equals(actionName)) {
			
			//System.out.println("write 진입");
			
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo)session.getAttribute("authUser");	
			
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
			BoardVo boardVo = new BoardVo();
		    BoardDao boardDao = new BoardDao();
		   
		    boardVo.setTitle(title);
		    boardVo.setContents(contents);
		   
		    boardDao.insert(boardVo, authUser);
		  
		    WebUtil.response(request, response, "board?a=list");
		
		}else if("view".equals(actionName)) {
			//System.out.println("view 진입");
			
			BoardDao boardDao = new BoardDao();
			BoardVo boardVo = boardDao.view(Integer.parseInt(request.getParameter("boardno")));
			
			request.setAttribute("view", boardVo);
			
			int count = Integer.parseInt(request.getParameter("views"));
			
			boardDao.upViwes(boardVo.getBoardno(), count+1);
			
			WebUtil.froward(request, response, "/WEB-INF/views/board/view.jsp");
		
		}else if("delete".equals(actionName)) {
			//System.out.println("delete 진입");
			
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			System.out.println(authUser);
			
			if(authUser == null) {
				
				WebUtil.froward(request, response, "/WEB-INF/views/user/loginform/jsp");
			
			}else {
				BoardDao boardDao = new BoardDao();
				
				int no = boardDao.getuserNo(Integer.parseInt(request.getParameter("boardno")));
						//System.out.println(no+"안녕하세요");
						//System.out.println(authUser.getNo());
					if(no == authUser.getNo()) {
						//System.out.println(no+"메롱");
						boardDao.delete(Integer.parseInt(request.getParameter("boardno")));
					
						WebUtil.response(request, response, "board?a=list");
							}
						}
		}else if("modifyfrom".equals(actionName)) {
				//System.out.println("modifyfrom 진입");
			
				BoardDao boardDao = new BoardDao();
				BoardVo boardVo = boardDao.view(Integer.parseInt(request.getParameter("boardno")));
			
				request.setAttribute("view", boardVo);
			
				WebUtil.froward(request, response, "/WEB-INF/views/board/modify.jsp");
		
		}else if("modify".equals(actionName)) {
			//System.out.println("modify 진입");
			BoardDao boardDao = new BoardDao();
			BoardVo boardVo = new BoardVo();
			
			boardVo.setTitle(request.getParameter("title"));
			boardVo.setContents(request.getParameter("contents"));
			boardVo.setBoardno(Integer.parseInt(request.getParameter("boardno")));
			
			boardDao.update(boardVo);
			
			WebUtil.response(request, response, "board?a=list");
			}
		}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
