package com.servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.entity.User;

/**
 * Servlet implementation class ProfileControllerServlet
 */
@WebServlet("/ProfileControllerServlet")
public class ProfileControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/fashion_nc")
	private DataSource dataSource;
	
	private UserDbUtil userDbUtil;
	
	@Override
	public void init() throws ServletException{
		super.init();
		
		try {
			userDbUtil=new UserDbUtil(dataSource);
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command=request.getParameter("command");
		
		if(command.equals("Save changes")) {
			saveChanges(request,response);
			}
		else if(command.equals("Change password")) {
			changePassword(request,response);
		}
	}

	private void saveChanges(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String number=request.getParameter("number");
		
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		
		userDbUtil.changeInfo(user.getEmail(), name, number);
		request.setAttribute("INFO_SAVED", "true");
		
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("/HTML-JSP/Profile.jsp");
		requestDispatcher.forward(request, response);
	}

	private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldPassword=request.getParameter("currentPassword");
		String newPassword=request.getParameter("newPassword");
		
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		
		boolean isSuccess=userDbUtil.changePassword(user.getEmail(), oldPassword, newPassword);
		if(isSuccess) {
			request.setAttribute("CHANGE_PASSWORD_SUCCESS", "true");
		}
		else {
			request.setAttribute("CHANGE_PASSWORD_FAILED", "true");
		}
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("/HTML-JSP/Profile.jsp");
		requestDispatcher.forward(request, response);
	}

}
