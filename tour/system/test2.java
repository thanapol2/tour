package tour.system;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JButton;

import org.apache.commons.io.FilenameUtils;



import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import tourlist.createTourList.createTourData;
import tourlist.createTourList.createTourView;
import tourlist.createTourList.createTourXls;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class test2 {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("new sheet");

		XSSFCellStyle style = wb.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
	    XSSFRow row = sheet.createRow((short) 1);
	    XSSFCell cell = row.createCell((short) 1);
	    cell.setCellValue("This is a test of merging");
	    cell.setCellStyle(style);
	    
	    XSSFRow row2 = sheet.createRow((short) 2);
	    XSSFCell cell2 = row2.createCell((short) 1);
	    cell2.setCellValue("This is a test of merging");
	    cell2.setCellStyle(style);
	    
	    sheet.addMergedRegion(new CellRangeAddress(
	            1, //first row (0-based)
	            2, //last row  (0-based)
	            1, //first column (0-based)
	            1  //last column  (0-based)
	    ));

	    // Write the output to a file
	    FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("workbook.xlsx");
		    wb.write(fileOut);
		    fileOut.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
