<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<body>

	<div id="container">
		
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
				
		<div id="navigation">
			<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		</div> <!-- /navigation -->
		
		<div id="wrapper">
			<div id="content">
				<div id="user">
	
					<form id="join-form" name="joinForm" method="get" action="/mysite/user">

						
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value="${userVo.name }" />
	
						<label class="block-label" for="email">이메일</label>
						<strong>${userVo.email}</strong>
						
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="${userVo.password}" />
						
						<fieldset>
							<legend>성별</legend>
							<c:choose>
							<c:when test="${userVo.gender == male}">
							<label>여</label> <input type="radio" name="gender" value="female" >
							<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							</c:when>
							<c:otherwise>
							<label>여</label> <input type="radio" name="gender" value="male" checked="checked">
							<label>남</label> <input type="radio" name="gender" value="female" >
							</c:otherwise>
							</c:choose>
						</fieldset>
						<input type="hidden"  name="a" value="modify"/>
						<input type="submit" value="수정완료">
						
					</form>
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<div id="footer">
			<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
		</div> <!-- /footer -->
		
	</div> <!-- /container -->

</body>
</html>
