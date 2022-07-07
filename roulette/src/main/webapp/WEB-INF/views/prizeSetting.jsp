<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function checkform(f) {
		if($("#tryCount").val()==""){
 		   alert("횟수를 입력해주세요.");
 		   $("#tryCount").focus();
 		   return false;
 	    }
		if($("#tryCount").val()==0){
	 		   alert("0회는 사용 할 수 없습니다.");
	 		   $("#tryCount").focus();
	 		   return false;
 	    }
		var tempTryCount = Number($("#tryCount").val());
		if (isNaN(tempTryCount)){
			alert("숫자만 입력 가능합니다.");
			$("#tryCount").focus();
			return;
		}
		if (tempTryCount>200000) {
			alert("20만번을 넘길 수 없습니다.");
			$("#tryCount").focus();
			return
		}
		$(f).submit();
	}
</script>
</head>
<body>
	<form action="prizeView.do" method="POST">
		<div>
			<label>수행횟수</label>
			<input type="text" name="tryCount" id="tryCount" placeholder="1~200,000"><br>
		</div>
		<input type="button" onClick="checkform(this.form)" value="전송">
		<input type="button" value="홈으로" onClick="location.href='home.do'">
	</form>
</body>
</html>