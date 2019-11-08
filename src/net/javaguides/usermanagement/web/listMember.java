package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import net.javaguides.usermanagement.dao.MemberDAO;
import net.javaguides.usermanagement.model.Member;

/**
 * Servlet implementation class listMember
 */
@WebServlet("/listMember")
public class listMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO memberDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listMember() {
        super();
        // TODO Auto-generated constructor stub
        memberDAO = new MemberDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String mname = (String) session.getAttribute("uname");		
		Member listMember = MemberDAO.selectMember(mname);
		request.setAttribute("listMember", listMember);		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = request.getRequestDispatcher("member-list.jsp");
		dispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
