package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;

import com.entity.Banner;
import com.entity.Cart;
import com.entity.User;

import javax.annotation.Resource;

/**
 * Servlet implementation class FetchBanner
 */
@WebServlet("/HomepageControllerServlet")
public class HomepageControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/fashion_nc")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomepageControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		ResultSet resultSet = null;

		try {
			DatabaseConnector dbConnector = new DatabaseConnector(dataSource);
			resultSet = dbConnector.getAll("banners");

			List<Banner> banners = new ArrayList<Banner>();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String type = resultSet.getString("type");
				String path = resultSet.getString("path");
				String heading = resultSet.getString("heading");
				String description = resultSet.getString("description");
				String href = resultSet.getString("href");

				Banner banner = new Banner(id, type, path, heading, description, href);
				banners.add(banner);
				
			}
			
			resultSet=dbConnector.getDistinct("type", "banners");
			List<String> types=new ArrayList<String>();
			while(resultSet.next()) {
				types.add(resultSet.getString("type"));
			}
			dbConnector.closeConnection();
			
			request.setAttribute("banners", banners);
			request.setAttribute("types", types);
			RequestDispatcher rd=request.getRequestDispatcher("/HTML-JSP/Homepage.jsp");
			
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
					if(user!=null) {
						CartDbUtil cartDbUtil=new CartDbUtil(dataSource);
						List<Cart> carts=cartDbUtil.getCartItems(user.getEmail());
						session.setAttribute("CART_COUNT",carts.size());
					}
					rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
