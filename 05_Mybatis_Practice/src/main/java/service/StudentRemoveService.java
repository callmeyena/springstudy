package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class StudentRemoveService implements IStudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("stuNo"));
		int stuNo = Integer.parseInt(opt.orElse("0"));
		
		int deleteResult = StudentDAO.getInstance().deleteStudent(stuNo);

		PrintWriter out = response.getWriter();
		out.println("<script>");
		if(deleteResult == 1) {
			out.println("alert('학생 정보가 삭제되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/list.do'");
		} else {
			out.println("alert('학생 정보 삭제가 실패했습니다.')");
			out.println("history.back()");
		}
		out.println("</script>");
		out.flush();
		out.close();
		
		return null;
		
	}

}
