package main.export;

import invoice.invoiceDetail.invoiceData;
import invoice.invoiceDetail.rowInvoice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFBorderFormatting;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.base.Tools;
import com.table.rowMember;

public class createExportXls {


	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFCellStyle numberStyle;
	private final DecimalFormat dec = new DecimalFormat("#.00");
	private final String defaultFileName = "list_customer";
	public createExportXls(){

		FileInputStream file;
		try {
			String pathFile = Tools.getConfig("xls_path");
			String tmpFile = Tools.getConfig("xls_export_customer");
			Path currentRelativePath = Paths.get("");
			String rootPath = currentRelativePath.toAbsolutePath().toString()
					+pathFile+tmpFile;
			file = new FileInputStream(new File(rootPath));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheetAt(0);
			
			DataFormat format = workbook.createDataFormat();
			numberStyle = workbook.createCellStyle();
		    numberStyle.setDataFormat(format.getFormat("#,##0.00"));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Cannot found template file"
					,"Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	

    /*
	 *    -1 = cancel
	 *    0  = fail
	 *    1  = complte
	 */
	
	
	public int saveFile(){
		int result = 0;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			    "Microsoft Excel Documents", "xlsx");
		chooser.setFileFilter(filter);
		chooser.setSelectedFile(new File(defaultFileName+".xlsx"));
		int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION){
			String fullFileName = FilenameUtils.removeExtension(chooser.getSelectedFile().toString());
	    	try {
				FileOutputStream fileOut = new FileOutputStream(fullFileName+".xlsx");
				workbook.write(fileOut);
				fileOut.flush();
				fileOut.close();
				result = 1;
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"File is not found or Please close this file and print again"
    					,"Error", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Can't saving, please check file."
    					,"Error", JOptionPane.ERROR_MESSAGE);
			}
	    }else if (retrival == JFileChooser.CANCEL_OPTION){
	    	result = -1;
	    }
		return result;
	}

	
	public boolean printXls(ArrayList<exportData> dataList){
		boolean result = false;
		int indexRow = 1;
		for(exportData temp :dataList){
			sheet.createRow(indexRow);
			setValueCell(indexRow,0,Integer.toString(indexRow));
			setValueCell(indexRow,1,temp.getNameSurname());
			setValueCell(indexRow,5,temp.getAddress());
			setValueCell(indexRow,16,temp.getTel());
			indexRow = indexRow + 1;
		}
		result = true;

		return result;

	
	}
	
	
	private void setValueCell(int row,int cell,String data){
		sheet.getRow(row).createCell(cell).setCellValue(data);
	}
	

}

	

