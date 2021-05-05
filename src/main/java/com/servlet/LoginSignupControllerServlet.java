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

import com.entity.PasswordEncryption;
import com.entity.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginSignupControllerServlet")
public class LoginSignupControllerServlet extends HttpServlet {
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
		System.out.println(command);
		if(command.equals("login")) {
			try {
				login(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("signup")) {
			try {
				signup(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
	}


	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String encrypt=PasswordEncryption.encrypt(password);
		User user=userDbUtil.getUser(email,encrypt);
		boolean isRegisteredUser=user!=null;
		
		RequestDispatcher requestDispatcher;
		if(isRegisteredUser) {
			HttpSession session = request.getSession();
		    session.setAttribute("user", user);
			requestDispatcher=request.getRequestDispatcher("HomepageControllerServlet");
		}
		else {
			request.setAttribute("Login_Failed", "true");
			requestDispatcher=request.getRequestDispatcher("/HTML-JSP/LoginSignup.jsp");
		}
		requestDispatcher.forward(request, response);
		
	}


	private void signup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String encrypt=PasswordEncryption.encrypt(password);
		String name=request.getParameter("name");
		String contact=request.getParameter("contact");
		boolean isAdded=userDbUtil.addUser(email, name, encrypt, contact);
		
		RequestDispatcher requestDispatcher;
		if(isAdded) {
			request.setAttribute("Signup_Success", "true");
			requestDispatcher=request.getRequestDispatcher("/HTML-JSP/LoginSignup.jsp");
		}
		else {
			request.setAttribute("Signup_Failed", "true");
			requestDispatcher=request.getRequestDispatcher("/HTML-JSP/LoginSignup.jsp");
		}
		requestDispatcher.forward(request, response);
	}

}
