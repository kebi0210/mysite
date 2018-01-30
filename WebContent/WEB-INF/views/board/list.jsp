<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<div id="header">
		
			<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		
		</div>
		
		<div id="navigation">
		
			<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		
		</div>
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				
				<table class="tbl-ex">

					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:forEach items="${list}" var = "vo" >
						<tr>
							<td>${vo.boardno}</td>
							<td><a href="board?a=view&boardno=${vo.boardno}&views=${vo.views}">${vo.title}</a></td>
							<td>${vo.name}</td>
							<td>${vo.views}</td>
							<td>${vo.datecreated}</td>
							<c:if test="${vo.no == authUser.no }">
							<td><a href="board?a=delete&boardno=${vo.boardno}" class="del">삭제</a></td>
							</c:if>
							
						</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li><a href="">2</a></li>
						<li class="selected">3</li>
						<li><a href="">4</a></li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>
				<c:if test="${authUser != null }">	
				<div class="bottom">
					<a href="board?a=writefrom" id="new-book">글쓰기</a>
				</div>	
				</c:if>	
			</div>
		</div>
		
		<div id="footer">
			<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
		</div> <!-- /footer -->
		
	</div>
</body>
</html>