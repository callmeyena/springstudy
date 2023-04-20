<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>

	$(function(){
		
		$('#frm_write').on('submit', function(event){
			var kor = $('#kor');
			var eng = $('#eng');
			var math = $('#math');
			if(kor.val() == '' || isNaN(kor.val()) || kor.val() < 0 || kor.val() > 100){
				alert('국어 점수를 확인하세요.');
				kor.focus();
				event.preventDefault();
				return;
			}
			else if(eng.val() == '' || isNaN(eng.val()) || eng.val() < 0 || eng.val() > 100){
				alert('영어 점수를 확인하세요.');
				eng.focus();
				event.preventDefault();
				return;
			}
			else if(math.val() == '' || isNaN(math.val()) || math.val() < 0 || math.val() > 100){
				alert('수학 점수를 확인하세요.');
				math.focus();
				event.preventDefault();
				return;
			}
		});
		
		$('#btn_list').on('click', function(){
			location.href = '${contextPath}/list.do';
		});
		
	});

</script>
</head>
<body>

	<h1>신규학생등록 화면</h1>
	<div>
		<form id="frm_write" method="post" action="${contextPath}/add.do">
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name">
			</div>
			<div>
				<label for="kor">국어</label>
				<input type="text" id="kor" name="kor">
			</div>
			<div>
				<label for="eng">영어</label>
				<input type="text" id="eng" name="eng">
			</div>
			<div>
				<label for="math">수학</label>
				<input type="text" id="math" name="math">
			</div>
			<hr>
			<div>
				<button>작성완료</button>
				<input type="reset" value="다시작성">
				<input type="button" value="목록" id="btn_list">
			</div>
		</form>
	</div>

</body>
</html>