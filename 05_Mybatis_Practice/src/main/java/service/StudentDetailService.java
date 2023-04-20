package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class StudentDetailService implements IStudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("stuNo"));
		int stuNo = Integer.parseInt(opt.orElse("0"));
		
		request.setAttribute("student", StudentDAO.getInstance().selectStudentByNo(stuNo));
		
		return new ActionForward("student/detail.jsp", false);
		
	}

}
