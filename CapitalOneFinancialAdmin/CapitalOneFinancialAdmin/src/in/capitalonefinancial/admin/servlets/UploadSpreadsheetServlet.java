package in.capitalonefinancial.admin.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadSpreadsheetServlet
 */
@WebServlet(description = "This servlet will read a spreadsheet and add contents to the database", urlPatterns = { "/uploadSpreadsheetServlet.do" })
public class UploadSpreadsheetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

    /**
     * Default constructor. 
     */
    public UploadSpreadsheetServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		/*InputStream is = getServletContext().getResourceAsStream("/WEB-INF/credentials.txt");		
		UploadJDBCConnectorDAO uploadDAO = new UploadJDBCConnectorDAO(is);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filePath = uploadFileToServer(request, response);
		response.getWriter().append("\n Server File Path : " + filePath);
		/*ReadSpreadsheet readSpreadsheet = new ReadSpreadsheet(filePath);
		
		readSpreadsheet.parseSpreadhseet();*/
	}

	private String uploadFileToServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String serverFilePath = "";
		
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		if(isMultiPart){
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> fileItems = upload.parseRequest(request);
				Iterator<FileItem> iterator = fileItems.iterator();
				
				while(iterator.hasNext()) {
					FileItem fi = iterator.next();
					String fileName = extractFileName(fi);
					serverFilePath = getServletContext().getRealPath("data")+"\\"+fileName;
					File file = new File(serverFilePath);
					try {
						fi.write(file);
						response.getWriter().append("\n " + fileName + " uploaded successfully");
					} catch (Exception e) {
						response.getWriter().append("\n Could not upload file" + e.getMessage());
						serverFilePath = "";
					}
				}
				
			} catch (FileUploadException e) {
				System.out.println("UploadSpreadsheetServlet.doPost() FileUploadException" + e.getMessage());
			}
		}
		return serverFilePath;
	}

	private String extractFileName(FileItem fi) {
		String filePath = fi.getName();
		int lastIndex = filePath.lastIndexOf("\\");
		String fileName = filePath.substring(lastIndex + 1);
		return fileName;
	}

}
