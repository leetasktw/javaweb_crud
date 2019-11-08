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
	    
	     
	    // 配置上傳引數
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    // 設定記憶體臨界值 - 超過後將產生臨時檔案並存儲於臨時目錄中
	    factory.setSizeThreshold(MEMORY_THRESHOLD);
	    // 設定臨時儲存目錄
	    factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	  
	    ServletFileUpload upload = new ServletFileUpload(factory);
	      
	    // 設定最大檔案上傳值
	    upload.setFileSizeMax(MAX_FILE_SIZE);
	      
	    // 設定最大請求值 (包含檔案和表單資料)
	    upload.setSizeMax(MAX_REQUEST_SIZE);
	     
	    // 中文處理
	    upload.setHeaderEncoding("UTF-8");	    
	    String uploadPath = request.getServletContext().getRealPath("/files");
	    File uploadDir = new File(uploadPath);
	    if (!uploadDir.exists()) {
	      uploadDir.mkdir();
	    } 
	    try {
	      // 解析請求的內容提取檔案資料
	      List<FileItem> formItems = upload.parseRequest(request);
	      if (formItems != null && formItems.size() > 0) {
	        // 迭代表單資料
	        for (FileItem item : formItems) {
	          // 處理不在表單中的欄位
	          if (!item.isFormField()) {
	        	String filePath = uploadPath+File.separator+profilepicname + ".jpg";
	        	System.out.print(filePath);
	            File storeFile = new File(filePath);
	            // 儲存檔案到硬碟
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
