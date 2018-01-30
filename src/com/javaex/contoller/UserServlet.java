package com.javaex.contoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		
		if("joinform".equals(actionName)) {
			
			WebUtil.froward(request, response, "/WEB-INF/views/user/joinform.jsp" );
			
		}else if("join".equals(actionName)) {
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo();
			
			userVo.setName(name);
			userVo.setEmail(email);
			userVo.setPassword(password);
			userVo.setGender(gender);
			
			
			
			UserDao userdao = new UserDao();
			userdao.insert(userVo);
			
			WebUtil.froward(request, response, "/WEB-INF/views/user/joinsuccess.jsp");
			
		}else if("loginform".equals(actionName)) {
			
			WebUtil.froward(request, response, "/WEB-INF/views/user/loginform.jsp");
		
		}else if("login".equals(actionName)) {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			System.out.println(email + "/" + password);
			
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(email, password);
			
			if(userVo == null) {
				//로그인 실패
				WebUtil.response(request, response, "/mysite/user?a=loginform&result=fail");
			
			}else {
				//로그인 성공
				HttpSession session = request.getSession(true);
				session.setAttribute("authUser", userVo);
				
				WebUtil.response(request, response, "/mysite/main");
				}
		
		}else if("logout".equals(actionName)) {
			
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			
			WebUtil.response(request, response, "/mysite/main");
		
		}else if("modifyform".equals(actionName)) {
			
			//세션에서 넘버가져옴 getNO()
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo)session.getAttribute("authUser");	
			
			
				
				if(authUser == null) {//비로그인 상태
					//로그인 폼으로 리다이렉트
					WebUtil.response(request, response, "/WEB-INF/views/user/loginform.jsp");
				}else { //로그인 상태 
				    //로그인 회원 no
					
					int no = authUser.getNo();
					//다오에서 가져옴(no)
					UserDao userDao = new UserDao();
					UserVo userVo = userDao.getUser(no);
				   
					//데이터를 담음
					request.setAttribute("userVo", userVo);
					
					//폼으로 이동
					WebUtil.froward(request, response, "/WEB-INF/views/user/modifyform.jsp");
					}
		}else if("modify".equals(actionName)) {
			
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo)session.getAttribute("authUser");	
			
			
				
				if(authUser == null) {//비로그인 상태
					//로그인 폼으로 리다이렉트
					
				}else { //로그인 상태 
				    
					//vo (no,name,password,gender)
					int no = authUser.getNo();
					String name = request.getParameter("name");
					String password = request.getParameter("password");
					String gender = request.getParameter("gender");
					
					UserVo userVo = new UserVo(no, name, "" , password, gender);
					//dao.update(vo)
					UserDao userDao = new UserDao();
					userDao.update(userVo);
					//session name 값변경
					authUser.setName(name);
					
					//main 리다이렉트
					WebUtil.response(request, response, "/mysite/main");
				
		}
	}
}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
