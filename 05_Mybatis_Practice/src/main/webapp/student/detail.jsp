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
		
		$('#frm_detail').on('submit', function(event){
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

	<h1>학생 상세 조회</h1>
	<div>
		<form id="frm_detail" method="post" action="${contextPath}/modify.do">
			<div>
				<label for="stuNo">학번</label>
				<input type="text" id="stuNo" name="stuNo" value="${student.stuNo}" readonly>
			</div>
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name" value="${student.name}">
			</div>
			<div>
				<label for="kor">국어</label>
				<input type="text" id="kor" name="kor" value="${student.kor}">
			</div>
			<div>
				<label for="eng">영어</label>
				<input type="text" id="eng" name="eng" value="${student.eng}">
			</div>
			<div>
				<label for="math">수학</label>
				<input type="text" id="math" name="math" value="${student.math}">
			</div>
			<div>
				<label for="ave">평균</label>
				<input type="text" id="ave" name="ave" value="${student.ave}" readonly>
			</div>
			<div>
				<label for="grade">학점</label>
				<input type="text" id="grade" name="grade" value="${student.grade}" readonly>
			</div>
			<hr>
			<div>
				<button>수정하기</button>
				<input type="button" value="목록" id="btn_list">
			</div>
		</form>
	</div>

</body>
</html>