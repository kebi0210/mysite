package com.javaex.contoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestBookVo;


@WebServlet("/guest")
public class GuestBookservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		
		if("list".equals(actionName)) {
			//System.out.println("list 진입");
			GuestBookDao dao =new GuestBookDao();
			
			List<GuestBookVo> list = dao.getList();
			request.setAttribute("list", list);
			
			WebUtil.froward(request, response, "/WEB-INF/views/guestbook/list.jsp");
		
		}else if("write".equals(actionName)){
			//System.out.println("write 진입");
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestBookVo vo = new GuestBookVo();
		    GuestBookDao dao = new GuestBookDao();
		   
		    vo.setName(name);
		    vo.setPassword(password);
		    vo.setContent(content);
		 
		    dao.insert(vo);
		    
		    
		    WebUtil.response(request, response, "/mysite/guest?a=list");
		   
		}else if("deleteform".equals(actionName)) {
			//System.out.println("deleteform 진입");
			
			String no = request.getParameter("no");
			request.setAttribute("no", no);
			
			WebUtil.froward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
		
		}else if("delete".equals(actionName)) {
			//System.out.println("delete 진입");
			
			int no = Integer.parseInt(request.getParameter("no"));
			String pass = request.getParameter("password");
		    
		    GuestBookDao dao = new GuestBookDao();
		    
		    dao.delete(no,pass);
			
			WebUtil.response(request, response, "/mysite/guest?a=list");
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	}

}
