package tourlist.createTourList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.base.Tools;

public class createTourXls {
	private createTourData data;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	final private int colRoom = 11;
	final private int colBus = 12;
	final private int colType = 13;
	final private int rowStart = 13;
	public createTourXls(createTourData aData){
		data = aData;
		FileInputStream file;
		try {
			String pathFile = Tools.getConfig("xls_path");
			String tmpFile  = Tools.getConfig("xls_tmp1");
			Path currentRelativePath = Paths.get("");
			String rootPath = currentRelativePath.toAbsolutePath().toString()
					+pathFile+tmpFile;
			file = new FileInputStream(new File(rootPath));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheetAt(0);
//			printHead();
//			System.out.print(saveFile());
//			String a = sheet.getRow(7).getCell(0).getStringCellValue();
//			System.out.print(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean genXls(){
		boolean result = false;
		if(printXls()){
			int saveResult = saveFile();
			if(saveResult==1){
				result = true;
				JOptionPane.showMessageDialog(null,"<html><center>Save File complete.</center></html>"
						,"",JOptionPane.PLAIN_MESSAGE);
			}else if (saveResult==0){
				JOptionPane.showMessageDialog(null,"Please close current file or"
						+ "check file name."
						,"Error", JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null,"Please Check Data file."
					,"Error", JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
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
	private boolean printXls(){
		boolean result = false;
		
		String listName = "NAMELIST : "+data.getListName();
		String groupName = data.getGroupName();
		String hotel = data.getHotelName();
		String amount = genSumPax(data.getADLNum(),data.getCHDNum()
				,data.getTourLeadNum()
				,data.getTourGroupNum());
		String room = data.getGenTextRoom();;
		String departDate = data.getDeparteDate();
		String departNo = data.getDepartNo();
		String departName = data.getDepartFlight();
		String departTime = data.getDepartTime();
		String arrDate = data.getArrDate();
		String arrNo = data.getArrNo();
		String arrName = data.getArrFlight();
		String arrTime = data.getArrTime();
		String bus1 = getBus("1");
		String bus2 = getBus("2");
		
		XSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		
//			addData to xls
		sheet.getRow(5).getCell(0).setCellValue(listName);
		sheet.getRow(6).getCell(2).setCellValue(groupName);
		sheet.getRow(6).getCell(8).setCellValue(hotel);
		sheet.getRow(7).getCell(2).setCellValue(amount);
		sheet.getRow(8).getCell(2).setCellValue(room);
		sheet.getRow(9).getCell(2).setCellValue(departDate);
		sheet.getRow(9).getCell(3).setCellValue(departNo);
		sheet.getRow(9).getCell(4).setCellValue(departName);
		sheet.getRow(9).getCell(6).setCellValue(departTime);
		sheet.getRow(9).getCell(8).setCellValue(bus1);
		sheet.getRow(10).getCell(2).setCellValue(arrDate);
		sheet.getRow(10).getCell(3).setCellValue(arrNo);
		sheet.getRow(10).getCell(4).setCellValue(arrName);
		sheet.getRow(10).getCell(6).setCellValue(arrTime);
		sheet.getRow(10).getCell(8).setCellValue(bus2);
		
		int rowNum = Integer.parseInt((data.getPassNum()));
		 
		for(int row = 0;row<rowNum;row++){
			XSSFRow xssRow = sheet.createRow((short) row+rowStart);
			XSSFCell xxsCell = xssRow.createCell(0);
			xxsCell.setCellValue(row+1);
			xxsCell.setCellStyle(style);
			ArrayList<String> temp = data.getRowData(row);
			for(int col =1;col<temp.size();col++){
				XSSFCell tempCell = xssRow.createCell(col);
				tempCell.setCellValue(temp.get(col));
				tempCell.setCellStyle(style);
			}
		}
		for(String index : genMergeCell(data.getRoomType(),data.getRoomNum())){
			String [] temp = index.split(",");
			sheet.addMergedRegion(new CellRangeAddress(rowStart+Integer.parseInt(temp[0]),
					rowStart+Integer.parseInt(temp[1]),
					colRoom,
					colRoom));
		}
		result = true;
		
		return result;
	}
	private String genSumPax(String adl,String chd,String tl,String tg){
		int adlNum = Integer.parseInt(adl);
		int chdNum = Integer.parseInt(chd);
		int tlNum = Integer.parseInt(tl);
		int tgNum = Integer.parseInt(tg);
		StringBuffer amount = new StringBuffer();
		
		amount.append("ADL : ");
		amount.append(adlNum);
		if(chdNum>0){
			amount.append(" + CHD : ");
			amount.append(chd);
		}
		if(tlNum>0){
			amount.append(" + T/L : ");
			amount.append(tlNum);
		}
		if(tgNum>0){
			amount.append(" + T/G : ");
			amount.append(tgNum);
		}
		amount.append(" = ");
		amount.append(adlNum+chdNum+tlNum+tgNum);
		amount.append(" Pax");
		return amount.toString();
	}
	/*
	 *    -1 = cancel
	 *    0  = fail
	 *    1  = complte
	 */
	private int saveFile(){
		int result = 0;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			    "Microsoft Excel Documents", "xlsx");
		chooser.setFileFilter(filter);
		
		int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION){
			String fullFileName = FilenameUtils.removeExtension(chooser.getSelectedFile().toString());
			String fileName = FilenameUtils.getName(fullFileName);
	    	try {
	//			fileOut = new FileOutputStream("D:\\test.xlsx");
				FileOutputStream fileOut = new FileOutputStream(fullFileName+".xlsx");
				workbook.write(fileOut);
				fileOut.flush();
				fileOut.close();
				result = 1;
			} catch (FileNotFoundException e) {
				System.out.print("file not found or This file is opened");
			} catch (IOException e) {
				System.out.print("IO error");
			}
	    }else if (retrival == JFileChooser.CANCEL_OPTION){
	    	result = -1;
	    }
		return result;
	}
	private String getBus(String numberBus){
		StringBuilder result = new StringBuilder();
		int tour = 0;
		int lead = 0;
		int group = 0;
		for(int i=0;i<data.getSize();i++){
			ArrayList<String> temp = data.getRowData(i);
			if(temp.get(colBus).equals(numberBus)){
				if(temp.get(colType).equals("Tour")){
					tour = tour + 1;
				}else if(temp.get(colType).equals("T/L")){
					lead = lead + 1;
				}else if(temp.get(colType).equals("T/G")){
					group = group + 1;
				}
			}
		}
		result.append(tour).append(" Pax ");
		if(lead!=0){
			result.append("+ ").append(lead).append(" T/L ");
		}
		if(group!=0){
			result.append("+ ").append(group).append(" T/G ");
		}
		result.append(" = ").append(tour+lead+group).append(" Pax ");
		return result.toString();
	}
	private ArrayList<String> genMergeCell(ArrayList<String> roomType,
			ArrayList<Integer> roomNum){
		ArrayList<String> listMerge = new ArrayList<String>();
		int index = 0;
		int next  = 0;
		while ((index<roomType.size()-1)&&(next<roomType.size())) {
			String mergeIndex = "";
			next = index+1; 
			String before = roomType.get(index)+roomNum.get(index);
			while((index<next)&&(next<roomType.size())){		
				String current = roomType.get(next)+roomNum.get(next);
				if(before.equals(current)){
					mergeIndex = index+","+next;
					next++;
				}else{
					index = next;
				}
			}
			if(!mergeIndex.equals("")){
				listMerge.add(mergeIndex);
			}
		}
		return listMerge;
	}
}
