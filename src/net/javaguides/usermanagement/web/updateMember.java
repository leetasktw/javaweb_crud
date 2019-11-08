package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.javaguides.usermanagement.dao.MemberDAO;
import net.javaguides.usermanagement.model.Member;

/**
 * Servlet implementation class updateMember
 */
@WebServlet("/updateMember")
public class updateMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO memberDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateMember() {
        super();
        // TODO Auto-generated constructor stub
        memberDAO = new MemberDAO();        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();		
		String name = (String) session.getAttribute("uname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");		
		Member book = new Member(id, name, password, email);
		try {
			MemberDAO.updateMember(book);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("listUser");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
