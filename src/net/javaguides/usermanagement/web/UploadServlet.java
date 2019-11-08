package net.javaguides.usermanagement.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet2
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MEMORY_THRESHOLD  = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE   = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE  = 1024 * 1024 * 50; // 50MB   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String profilepicname = (String)session.getAttribute("uname");
	    
	     
	    // �t�m�W�Ǥ޼�
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    // �]�w�O�����{�ɭ� - �W�L��N�����{���ɮרæs�x���{�ɥؿ���
	    factory.setSizeThreshold(MEMORY_THRESHOLD);
	    // �]�w�{���x�s�ؿ�
	    factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	  
	    ServletFileUpload upload = new ServletFileUpload(factory);
	      
	    // �]�w�̤j�ɮפW�ǭ�
	    upload.setFileSizeMax(MAX_FILE_SIZE);
	      
	    // �]�w�̤j�ШD�� (�]�t�ɮשM�����)
	    upload.setSizeMax(MAX_REQUEST_SIZE);
	     
	    // ����B�z
	    upload.setHeaderEncoding("UTF-8");	    
	    String uploadPath = request.getServletContext().getRealPath("/files");
	    File uploadDir = new File(uploadPath);
	    if (!uploadDir.exists()) {
	      uploadDir.mkdir();
	    } 
	    try {
	      // �ѪR�ШD�����e�����ɮ׸��
	      List<FileItem> formItems = upload.parseRequest(request);
	      if (formItems != null && formItems.size() > 0) {
	        // ���N�����
	        for (FileItem item : formItems) {
	          // �B�z���b��椤�����
	          if (!item.isFormField()) {
	        	String filePath = uploadPath+File.separator+profilepicname + ".jpg";
	        	System.out.print(filePath);
	            File storeFile = new File(filePath);
	            // �x�s�ɮר�w��
	            item.write(storeFile);	            
	            request.getRequestDispatcher("listMember").include(request, response);
	            
	          }
	        }
	      }
	    } catch (Exception ex) {
	      System.out.print(ex);
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
