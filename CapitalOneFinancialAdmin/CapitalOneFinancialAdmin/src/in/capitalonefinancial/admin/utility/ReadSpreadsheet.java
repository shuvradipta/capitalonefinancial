package in.capitalonefinancial.admin.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadSpreadsheet {
	
	private String filePath = "";

	public ReadSpreadsheet(String filePath) {
		super();
		this.filePath = filePath;
	}
	
	public ArrayList<HashMap<String,String>> parseSpreadhseet(){
		ArrayList<HashMap<String,String>> records = new ArrayList<HashMap<String,String>>(0);
		
		try {
			Workbook workbook = Workbook.getWorkbook(new File(this.filePath));
			Sheet sheet = workbook.getSheet(0);// Assuming only 1 sheet
			int columns = sheet.getColumns();
			int rows = sheet.getRows();
			
			System.out.println("ReadSpreadsheet.parseSpreadhseet() columns = " + columns + " , rows = " + rows);
			
			ArrayList<String> dbColumnOrder = new ArrayList<String>(columns); 
			for(int i=0; i<columns; i++)
				dbColumnOrder.add(i, sheet.getCell(i,0).getContents());
			System.out.println("ReadSpreadsheet.parseSpreadhseet() :: dbColumnOrder : " + dbColumnOrder);
			
			HashMap<String, String> recordMap = null;
			for(int i=1; i<rows; i++) {
				recordMap = new HashMap<String, String>();
				for (int j=0; j<columns; j++) {
					String cellContent = sheet.getCell(j, i).getContents();
					cellContent = cellContent.trim();
					if(!cellContent.isEmpty()){
						recordMap.put(dbColumnOrder.get(j), cellContent);
					}
				}
				if(!recordMap.isEmpty()){
					records.add(recordMap);
					System.out.println(recordMap + "added to map");
				}
			}
			
		} catch (BiffException e) {
			System.out.println("ReadSpreadsheet.parseSpreadhseet() - BiffException :: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("ReadSpreadsheet.parseSpreadhseet() - IOException :: " + e.getMessage());
		}
		return records;
	}
	

}
