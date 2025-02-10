package Utilites;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExelWrite {
	 private Workbook workbook;
	    private Sheet sheet;

	    public ExelWrite(String sheetName) {
	        workbook = new XSSFWorkbook();
	        sheet = workbook.createSheet(sheetName);
	    }

	    public void writeData(String[] data, int rowIndex, String title) {
	    	Row row = sheet.createRow(rowIndex);
	    	Cell cell = row.createCell(0);
	    	cell.setCellValue(title);
	        for (int i = 0; i < data.length; i++) {
	        	row = sheet.createRow(rowIndex+1+i);
	            cell = row.createCell(0);
	            cell.setCellValue(data[i]);
	        }
	    }

	    public void writeData(String[][] data,int rowIndex,String title) {
	    	Row row = sheet.createRow(rowIndex);
	    	Cell cell = row.createCell(0);
	    	cell.setCellValue(title);
	    	
	    	row = sheet.createRow(rowIndex+1);
	    	cell = row.createCell(0);
			cell.setCellValue("Course Name");
			cell = row.createCell(1);
			cell.setCellValue("Course Duration");
			cell = row.createCell(2);
			cell.setCellValue("Course Rating");
	    	
	    	 for (int i = 0; i < data.length; i++) {
	    		 row = sheet.createRow(rowIndex+i+2);
	    	 for(int j=0;j<data[i].length;j++ ) {
		            cell = row.createCell(j);
		            cell.setCellValue(data[i][j]);
		        }
	    	 }
	        
	    }
	    
	    public void writeLine(String line,int rowIndex,String title) {
	    	Row row = sheet.createRow(rowIndex);
	    	Cell cell = row.createCell(0);
	    	cell.setCellValue(title);
	    	
	    	row = sheet.createRow(rowIndex+1);
	    	cell = row.createCell(0);
	    	cell.setCellValue(line);
			
		}

	    public void saveToFile(String filePath) throws IOException {
	    	
	        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
	            workbook.write(fileOut);
	        }
	        workbook.close();
	    }

}
