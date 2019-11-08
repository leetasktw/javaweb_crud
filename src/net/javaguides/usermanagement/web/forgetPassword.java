package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * Servlet implementation class forgetPassword
 */
@WebServlet("/forgetPassword")
public class forgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String host = "smtp.gmail.com";
	int port = 587;
	final String username = "okya31@gmail.com";
	final String password = "kzaiaktxefgfgmab";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forgetPassword() {
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
		String fname = request.getParameter("fname");		
		String fmail = request.getParameter("fmail");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=CST", "root", "12345678");
			
			preparedStatement = (PreparedStatement)con.prepareStatement("select * from members where name=? and email=?");
            preparedStatement.setString(1,fname);
            preparedStatement.setString(2,fmail); 
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
            	  Properties props = new Properties();
            	  props.put("mail.smtp.host", host);
            	  props.put("mail.smtp.auth", "true");
            	  props.put("mail.smtp.starttls.enable", "true");
            	  props.put("mail.smtp.port", port);
            	  Session session = Session.getInstance(props, new Authenticator() {
            		  protected PasswordAuthentication getPasswordAuthentication() {
            			  return new PasswordAuthentication(username, password);
            		  }
            	  });

            	  try {

            	   Message message = new MimeMessage(session);
            	   message.setFrom(new InternetAddress("okya31@gmail.com"));
            	   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(fmail));
            	   message.setSubject("My Database Password");
            	   message.setText("Your password is "+resultSet.getString(3));

            	   Transport transport = session.getTransport("smtp");
            	   transport.connect(host, port, username, password);

            	   Transport.send(message);

            	  } catch (MessagingException e) {throw new RuntimeException(e);}
            	  out.print("<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\r\n" + 
      					"  <strong>Warning!</strong> Password has been sent to your mailbox.\r\n" + 
      					"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n" + 
      					"    <span aria-hidden=\"true\">&times;</span>\r\n" + 
      					"  </button>\r\n" + 
      					"</div>");
      			  RequestDispatcher rd = request.getRequestDispatcher("index.html");
      			  rd.include(request, response);
            } else {
    				out.print("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\r\n" + 
    					"  <strong>Warning!</strong> Name or Email does not exist.\r\n" + 
    					"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n" + 
    					"    <span aria-hidden=\"true\">&times;</span>\r\n" + 
    					"  </button>\r\n" + 
    					"</div>");
    				RequestDispatcher rd = request.getRequestDispatcher("forget.jsp");
    				rd.include(request, response);
    		}
		} catch (Exception e) {
			// TODO: handle exception
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
