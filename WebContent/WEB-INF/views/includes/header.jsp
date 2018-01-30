<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.UserVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="header">
			<h1>MySite</h1>
			<ul>
			
				<c:choose>
				<c:when test="${authUser == null}">
				<!-- 로그인 전 -->
				<li><a href="user?a=loginform">로그인</a></li>
				<li><a href="user?a=joinform">회원가입</a></li>
				</c:when>
				<c:when test ="${authUser != null }">
				<!-- 로그인 후 -->
				<li><a href="/mysite/user?a=modifyform">회원정보수정</a></li>
				<li><a href="/mysite/user?a=logout">로그아웃</a></li> 
				<li> ${authUser.name}님 안녕하세요^^;</li>
				</c:when>
				</c:choose>
			</ul>
		</div> <!-- /header -->