package in.capitalonefinancial.admin.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.capitalonefinancial.admin.jdbc.InsertRecordDAO;
import in.capitalonefinancial.admin.utility.ReadSpreadsheet;

/**
 * Servlet implementation class AddNewClientsServlet
 */
@WebServlet(description = "This servlet will add new clients to database reading from the spreadsheet", urlPatterns = { "/writeNewClientDetailsToDB.do" })
public class AddNewClientsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String fileName = "PAN NO.xls";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewClientsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReadSpreadsheet readSpreadsheet = new ReadSpreadsheet(getServletContext().getRealPath("data")+"\\"+fileName);
		//System.out.println(readSpreadsheet.parseSpreadhseet());
		ArrayList<HashMap<String,String>> recordsToInsert = readSpreadsheet.parseSpreadhseet();
		System.out.println("AddNewClientsServlet.doGet() - recordsToInsert :: " + recordsToInsert);
		ArrayList<String> dbColumnOrder = new ArrayList<String>(0);
		Set<String> keySet = recordsToInsert.get(0).keySet();
		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
			dbColumnOrder.add((String) iterator.next());
		}
		//System.out.println("AddNewClientsServlet.doGet() - dbColumnOrder :: " + dbColumnOrder);
		
		InsertRecordDAO insertRecordDAO = new InsertRecordDAO(getServletContext().getResourceAsStream("/WEB-INF/credentials.txt"));
		
		for (Iterator<HashMap<String, String>> iterator = recordsToInsert.iterator(); iterator.hasNext();) {
			HashMap<String, String> record = iterator.next();
			//System.out.println("AddNewClientsServlet.doGet() - Now inserting record..... :: " + record);
			insertRecordDAO.insertRecord(record, "capitalonedb.co_clientdetails", dbColumnOrder);			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
