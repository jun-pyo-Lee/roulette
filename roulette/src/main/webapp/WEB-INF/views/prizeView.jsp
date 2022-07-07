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
<div>
<label>당첨 결과</label>
	<div style="max-width:100px; max-height:300px; text-align:center; background-color: pink; overflow:auto; border: 3px solid black">
		<c:forEach items="${map.posList}" var="posList">
			  ${posList} <br>
		</c:forEach>
	</div>
	<div>
		<hr>
		<div>
			시행 횟수 : ${map.tryCount} 회<br>
		</div>
		<div style="float: left;">
			<c:forEach items="${map.prize}" var="prize">
				${prize}<br>
			</c:forEach>
		</div>
		<div style="float: left; padding-left: 15px; text-align: right">
			<c:forEach items="${map.countList}" var="countList">
				 ${countList} 회<br>
			</c:forEach>
		</div>
		<div style="float: left; padding-left: 15px; ">
			<c:forEach items="${map.percentList}" var="percentList" >
				약 : ${percentList} %<br>
			</c:forEach>
		</div>
		<div style="float: left; padding-left: 15px;">
			<c:forEach items="${map.percent}" var="percent" >
				설정 확률 : ${percent} %<br>
			</c:forEach>
		</div>
	</div>
	<div style="clear: left;">
		<input type="button" value="뒤로가기" onClick="location.href='prizeSetting.do'">
		<input type="button" value="홈으로" onClick="location.href='home.do'">
	</div>
</div>

</body>
</html>