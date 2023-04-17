<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	
	function fn1() {
		$.ajax({
			// 요청
			type: 'post',						// 서버로 보낼 데이터를 요청 본문(request body)에 저장해서 보낸다. => 파라미터가 없기 때문에 'GET'요청 방식 불가
			url: '${contextPath}/third/ajax1',
			data: JSON.stringify({				// 문자열 형식의 JSON 데이터를 서버로 보낸다. 파라미터 이름이 없음에 주의한다.(서버에서 파라미터로 받을 수 없다.)
				'name': $('#name').val(),
				'tel': $('#tel').val()	
			}),
			// 예시 - data: '{"name":"kim", "tel":"010-2333-3333"}'
			contentType: 'application/json', 	// 서버로 보내는 data의 타입을 서버에 알려준다.
			// 응답
			dataType: 'json',
			success: function(resData) {		// resData = {"name" : "박예나", "tel" : "010-2455-9876"}
				let str = '<ul>';
				str += '<li>' + resData.name + '</li>';
				str += '<li>' + resData.tel + '</li>';
				$('#result').html(str);
			},
			error: function(jqXHR) {
				if(jqXHR.status == 400) {
					alert('이름과 전화번호 입력은 필수 입니다.');
				}
			}
		})
		
	}

	
	function fn2() {
		
	}

</script>
</head>
<body>

	<div>
		<form id="frm">
			<div>
				<label for="name">이름</label>
				<input id="name" name="name">
			</div>
			<div>
				<label for="tel">전화번호</label>
				<input id="tel" name="tel">
			</div>
			<div>
				<input type="button" value="전송1" onclick="fn1()">
				<input type="button" value="전송2" onclick="fn2()">
			</div>
		</form>
	</div>
	
	<div id="result"></div>
	
</body>
</html>