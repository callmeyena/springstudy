package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Student;
import repository.StudentDAO;

public class StudentAddService implements IStudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		
		Student student = Student.builder()
				.name(name)
				.kor(kor)
				.eng(eng)
				.math(math)
				.build();
		
		int insertResult = StudentDAO.getInstance().insertStudent(student);
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if(insertResult == 1) {
			out.println("alert('학생이 등록되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/list.do'");
		} else {
			out.println("alert('학생 등록이 실패했습니다.')");
			out.println("history.back()");
		}
		out.println("</script>");
		out.flush();
		out.close();
		
		return null;
		
	}

}
