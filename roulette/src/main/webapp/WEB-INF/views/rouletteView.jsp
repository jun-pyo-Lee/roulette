<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<label>당첨 결과</label>
		<div style="max-width:100px; max-height:300px; text-align:center; background-color: pink; overflow:auto; border: 3px solid black">
			<c:forEach items="${map.list}" var="list">
				  ${list}<br>
			</c:forEach>
		</div>
		<div>
			<hr>
			시행 횟수 : ${map.tryCount} 회<br>
			당첨 횟수 : ${map.winCount} 회<br>
			당첨 확률 : 약 ${map.probability} % <br>
			설정 확률 : ${map.winPercent} %
		</div>
	<form action="home.do" method="POST">
		<input type="submit" value="돌아가기">
	</form>
</body>
</html>