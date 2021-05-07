package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.entity.*;

@WebServlet("/CartControllerServlet")
public class CartControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CartDbUtil cartDbUtil;
	
	@Resource(name="jdbc/ferme_bd")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			cartDbUtil = new CartDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			listCartItems(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void listCartItems(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		List<Cart> carts=new ArrayList<Cart>();
		if(user==null) {
			//use cookies for guest
		}
		else {
			carts=cartDbUtil.getCartItems(user.getEmail());
		}
		
		request.setAttribute("CART_ITEMS", carts);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/HTML-JSP/Cart.jsp");
		dispatcher.forward(request, response);
		
	}

}
