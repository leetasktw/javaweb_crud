package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.ir.GetSplitState;


/**
 * Servlet Filter implementation class MyFilter
 */
@WebFilter("/UserServlet")
public class MyFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MyFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String password = request.getParameter("password");	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=CST", "root", "12345678");
			
			preparedStatement = (PreparedStatement)con.prepareStatement("select * from members where name=? and password=?");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
            	chain.doFilter(request, response);
            } else {
    			out.print("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\r\n" + 
    					"  <strong>Warning!</strong> Name or password error.\r\n" + 
    					"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n" + 
    					"    <span aria-hidden=\"true\">&times;</span>\r\n" + 
    					"  </button>\r\n" + 
    					"</div>");
    			RequestDispatcher rd = request.getRequestDispatcher("index.html");
    			rd.include(request, response);
    		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub		
	}

}
