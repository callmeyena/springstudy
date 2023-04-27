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
		   // 자동 완성 목록 초기화
		   $('#column').on('change', function() {
			   $('auto_complete').empty();
			   $('#query').val('');
		   })
		   // 자동 완성 목록 가져오기
			$('#query').on('keyup', function() {
				$('#auto_complete').empty();
				$.ajax({
					// 요청
					type: 'get',
					url: '${contextPath}/employees/autoComplete.do',
					data: $('#frm1').serialize(),
					// 응답
					dataType: 'json',
					success: function(resData) {		// resData = {"employees": [{"firstName": "xxx", "phoneNumber": "xxx", "deptDTO": "departmentName": "xxx"}, {}, {], ...]}
						let property = '';
						switch($('#column').val()) {
						case "E.FIRST_NAME": property = 'firstName'; break;
						case "E.PHONE_NUMBER": property = 'phoneNumber'; break;
						case "D.DEPARTMENT_NAME": property = 'deptDTO["departmentName"]'; break;
						}
						$.each(resData.employees, function(i, employee) {
							// employee.property   : employee.'firstName' 	(X)
							// employee[property]  : employee['firstName']	(O)
							let str = '<option value="' + employee[property] + '" />';
							$('#auto_complete').append(str);
						
							
						})
						}
					}
				})
			})
	   })
</script>
<style>
   .pagination {
      width: 350px;
      margin: 0 auto;
      text-align: center;
   }
   .pagination span, .pagination a {
      display: inline-block;
      width: 50px;
   }
   .hidden {
      visibility : hidden;
   }
   .strong {
      font-weight: 900;
   }
   .link {
   
   }
   table {
      width: 1500;
   }
   table td:nth-of-type(1) { width: 100px; }
   table td:nth-of-type(2) { width: 150px; }
   table td:nth-of-type(3) { width: 300px; }
</style>
</head>
<body>
   <div>
      <form id="frm1" action='${contextPath}/employees/search.do'>
         <select name="column" id="column">
            <option value="FIRST_NAME">FIRST_NAME</option>
            <option value="PHONE_NUMBER">PHONE_NUMBER</option>
            <option value="D.DEPARTMENT_NAME">D.DEPARTMENT_NAME</option>
         </select>
         <input list="auto_complete" type="text" name="query" id="query">
         <datalist id="auto_complete">
         	
         </datalist>
         <button>조회</button>
      </form>
   </div>
   <div>
   
      <table border="1">
         <thead>
            <tr>
               <td>순번</td>
               <td>사원번호</td>
               <td>사원명</td>
               <td>이메일</td>
               <td>전화번호</td>
               <td>입사일자</td>
               <td>직업</td>
               <td>연봉</td>
               <td>커미션</td>
               <td>매니저</td>
               <td>부서번호</td>
               <td>부서명</td>
            </tr>
         </thead>
         <tbody>
            <c:forEach items="${employees}" var="emp" varStatus="vs">
               <tr>
                  <td>${beginNo - vs.index}</td>
                  <td>${emp.employeeId}</td>
                  <td>${emp.firstName} ${emp.lastName}</td>
                  <td>${emp.email}</td>
                  <td>${emp.phoneNumber}</td>
                  <td>${emp.hireDate}</td>
                  <td>${emp.jobId}</td>
                  <td>${emp.salary}</td>
                  <td>${emp.commissionPct}</td>
                  <td>${emp.managerId}</td>
                  <td>${emp.deptDTO.departmentId}</td>
                  <td>${emp.deptDTO.departmentName}</td>
               </tr>      
            </c:forEach>            
         </tbody>
         <tfoot>
            <tr>
               <td colspan="12">
                  ${pagination}
               </td>
            </tr>            
         </tfoot>
      </table>
   </div>
</body>
</html>