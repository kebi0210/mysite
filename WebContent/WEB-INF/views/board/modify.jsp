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

			<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		
		<div id="navigation">
				
			<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		
		</div>
		
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="board?a=modify&boardno=${view.boardno}">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글수정</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value="${view.title}"></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="contents" name="contents">
								${view.contents}
								</textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="board?a=list">취소</a>
						<input type="submit" value="수정">
					</div>
				</form>				
			</div>
		</div>

		<div id="footer">
			
			<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
		
		</div> <!-- /footer -->
	</div>
</body>
</html>