package invoice.invoiceXLS;

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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFBorderFormatting;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.base.Tools;

public abstract class createXls {

//	protected invoiceData data;
	protected XSSFWorkbook workbook;
	protected XSSFSheet sheet;
	protected XSSFCellStyle numberStyle;
	protected final	DecimalFormat dec = new DecimalFormat("#.00");
	protected final int EN_TYPE = 1;
//	protected int printType = 0;
	protected createXls(String fileNameNoXls){
//		data = aData;
		FileInputStream file;
		try {
			String pathFile = Tools.getConfig("xls_path");
			String tmpFile = getTemplateName(fileNameNoXls);
			Path currentRelativePath = Paths.get("");
			String rootPath = currentRelativePath.toAbsolutePath().toString()
					+pathFile+tmpFile;
			file = new FileInputStream(new File(rootPath));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheetAt(0);
			
			DataFormat format = workbook.createDataFormat();
			numberStyle = workbook.createCellStyle();
		    numberStyle.setDataFormat(format.getFormat("#,##0.00"));
		    workbook.setSheetName(workbook.getSheetIndex(sheet), "Page 1");
			
//			printHead();
//			System.out.print(saveFile());
//			String a = sheet.getRow(7).getCell(0).getStringCellValue();
//			System.out.print(a);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Cannot found template file"
					,"Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	

//	public boolean genXls(String defaultSaveFileName){
//		boolean result = false;
////		if(printXls()){
//			int saveResult = saveFile(defaultSaveFileName);
//			if(saveResult==1){
//				result = true;
//				JOptionPane.showMessageDialog(null,"<html><center>Save File complete.</center></html>"
//						,"",JOptionPane.PLAIN_MESSAGE);
//			}else if (saveResult==0){
//				JOptionPane.showMessageDialog(null,"Please close current file or"
//						+ "check file name."
//						,"Error", JOptionPane.ERROR_MESSAGE);
//			}
////		}else{
////			JOptionPane.showMessageDialog(null,"Please Check Data file."
////					,"Error", JOptionPane.ERROR_MESSAGE);
////		}
//		return result;
//	}
//	private boolean checkData(){
//		boolean result = false;
//		if(!data.getGroupName().equals("")){
//			if(!data.getListName().equals("")){
//				if(data.getSize()>0){
//					result = true;
//				}
//			}
//		}
//		return result;
//	}


	private String getDueDay(){
		StringBuilder result = new StringBuilder();
		result.append(" Days");
		return result.toString();
	}
	/*
	 *    -1 = cancel
	 *    0  = fail
	 *    1  = complte
	 */
	
	
	public int saveFile(String defaultSaveFileName){
		int result = 0;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			    "Microsoft Excel Documents", "xlsx");
		chooser.setFileFilter(filter);
		chooser.setSelectedFile(new File(defaultSaveFileName+".xlsx"));
		int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION){
			String fullFileName = FilenameUtils.removeExtension(chooser.getSelectedFile().toString());
//			String fileName = FilenameUtils.getName(fullFileName);
	    	try {
	//			fileOut = new FileOutputStream("D:\\test.xlsx");
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

	private ArrayList<rowInvoice> sortTypeData(ArrayList<rowInvoice> unSort){
		final int colType = 1;
		ArrayList<rowInvoice> sortData = new ArrayList<rowInvoice>();
		ArrayList<sort> sortIndex = new ArrayList<sort>();
		for(int i=0;i<unSort.size();i++){
			rowInvoice temp = unSort.get(i);
			String type = temp.getElementData(colType);
			if(sortIndex.size()==0){
				sortIndex.add(new sort(type, i));
			}else{
				for(int j=0;j<sortIndex.size();j++){
					
				}
			}
		}
		return sortData;
	}
	
	private class sort{
		public String type;
		public ArrayList<Integer> index;
		public sort(String aType,int aIndex){
			this.type = aType;
			this.index = new ArrayList<Integer>();
			this.index.add(aIndex);
		}
		public boolean checkType(String aType){
			return type.equals(aType);
		}
		public void add(int aIndex){
			index.add(aIndex);
		}
	}
	protected void setValueCell(int row,int cell,String data){
		sheet.getRow(row).getCell(cell).setCellValue(data);
	}
	protected void setValueCell(int row,int cell,int data){
		sheet.getRow(row).getCell(cell).setCellValue(Double.valueOf(dec.format(data)));
		setNumberCell(row,cell);
	}
	protected void setValueCell(int row,int cell,String data,CellStyle style){
		sheet.getRow(row).getCell(cell).setCellValue(data);
		sheet.getRow(row).getCell(cell).setCellStyle(style);
	}
	protected void setStyleCell(int row,int cell,CellStyle style){
		sheet.getRow(row).getCell(cell).setCellStyle(style);
	}
	protected void setNumberCell(int row,int cell){
		sheet.getRow(row).getCell(cell).setCellStyle(numberStyle);
		sheet.getRow(row).getCell(cell).setCellType(Cell.CELL_TYPE_NUMERIC);
	}
	protected void setValueCellWithLine(int row,int cell,String data){
		sheet.getRow(row).getCell(cell).setCellValue(data);
		XSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(XSSFBorderFormatting.BORDER_MEDIUM);
		sheet.getRow(row).getCell(cell).setCellStyle(style);
	}
	
	/*
	 * Type 
	 *  xls_invoice_tourTH
	 * 	xls_invoice_tourEN
	 * 	xls_invoice_tranTH
	 * 	xls_invoice_tranEN
	 * 	xls_paymentVat
	 *  xls_payment
	 */
	public String getTemplateName(String type){
		
		String tmpFile = Tools.getConfig("xls_"+type);
		return tmpFile;
	}
}

	

