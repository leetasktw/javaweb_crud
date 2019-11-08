package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=CST", "root", "12345678");
			
			preparedStatement = (PreparedStatement)con.prepareStatement("select * from members where name=?");
            preparedStatement.setString(1,name);            
            resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) {
				preparedStatement = (PreparedStatement)con.prepareStatement("insert into members(name,password,email)values(?,?,?);");
				preparedStatement.setString(1,name);
	            preparedStatement.setString(2,password);
	            preparedStatement.setString(3,email);
	            preparedStatement.executeUpdate();	            
				out.print("<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\r\n" + 
						"  <strong>Successed!</strong> Sign up successed.\r\n" + 
						"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n" + 
						"    <span aria-hidden=\"true\">&times;</span>\r\n" + 
						"  </button>\r\n" + 
						"</div>");
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response);
			} else {
				out.print("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\r\n" + 
						"  <strong>Warning!</strong> Sign up failed! Name is duplicated.\r\n" + 
						"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n" + 
						"    <span aria-hidden=\"true\">&times;</span>\r\n" + 
						"  </button>\r\n" + 
						"</div>");
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
