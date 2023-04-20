package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Student;
import repository.StudentDAO;

public class StudentModifyService implements IStudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int stuNo = Integer.parseInt(request.getParameter("stuNo"));
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		
		Student student = Student.builder()
				.stuNo(stuNo)
				.name(name)
				.kor(kor)
				.eng(eng)
				.math(math)
				.build();
		
		int updateResult = StudentDAO.getInstance().updateStudent(student);
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if(updateResult == 1) {
			out.println("alert('학생 정보가 수정되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/detail.do?stuNo=" + stuNo + "'");
		} else {
			out.println("alert('학생 정보 수정이 실패했습니다.')");
			out.println("history.back()");
		}
		out.println("</script>");
		out.flush();
		out.close();
		
		return null;
		
	}

}
