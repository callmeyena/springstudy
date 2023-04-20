package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.StudentAddService;
import service.StudentDetailService;
import service.StudentFindService;
import service.StudentListService;
import service.StudentModifyService;
import service.StudentRemoveService;
import service.IStudentService;

@WebServlet("*.do")

public class StudentController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		IStudentService service = null;
		ActionForward af = null;
		
		switch(urlMapping) {
		case "/list.do":
			service = new StudentListService();
			break;
		case "/add.do":
			service = new StudentAddService();
			break;
		case "/find.do":
			service = new StudentFindService();
			break;
		case "/remove.do":
			service = new StudentRemoveService();
			break;
		case "/detail.do":
			service = new StudentDetailService();
			break;
		case "/modify.do":
			service = new StudentModifyService();
			break;
		case "/write.do":
			af = new ActionForward("student/write.jsp", false);
			break;
		}
		
		if(service != null) {
			try {
				af = service.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getPath());
			} else {
				request.getRequestDispatcher(af.getPath()).forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
