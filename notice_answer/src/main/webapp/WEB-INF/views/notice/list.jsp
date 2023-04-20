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
	function fnInit(){
		let addResult = '${addResult}';
		if(addResult != '') {
			if(addResult == '1'){
				alert('공지사항이 등록되었습니다.');
			} else {
				alert('공지사항이 등록되지 않았습니다.');
			}
		}
		let removeResult = '${removeResult}';
		if(removeResult != '') {
			if(removeResult == '1'){
				alert('공지사항이 삭제되었습니다.');
			} else {
				alert('공지사항이 삭제되지 않았습니다.');
			}
		}
	}
	$(function(){
		fnInit();
	})
</script>
</head>
<body>

	<div>
		<h3>고양이 상사에 오신 걸 환영합니다</h3>
		<img src="${contextPath}/resources/images/cat.jpg" width="300px">
	</div>
	
	<hr>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>공지번호</td>
					<td>제목</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty noticeList}">
					<tr>
						<td colspan="2">등록된 공지사항이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty noticeList}">
					<c:forEach items="${noticeList}" var="notice">
						<tr>
							<td>${notice.notice_no}</td>
							<td><a href="${contextPath}/notice/detail.do?notice_no=${notice.notice_no}">${notice.title}</a></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	
	<hr>
	
	<div>
		<a href="${contextPath}/notice/write.do">새로운 공지 작성하러 가기</a>
	</div>
	
</body>
</html>