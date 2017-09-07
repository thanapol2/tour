package invoice.invoiceXLS;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFBorderFormatting;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import com.base.Tools;

import invoice.invoiceDetail.invoiceData;
import invoice.invoiceDetail.rowInvoice;

public class printInvoice extends createXls{

	final private String DISCOUNT = Tools.getConfig("discount_type");
	final private ArrayList<String> consonant = new ArrayList<>(Arrays.asList("่","้","๊","๋","็"
			,"ึ","ึ","ื","ี","ุ","ู"));
	final private int rowEnd = 21;   // Page list
	final private int newLine = Integer.parseInt(Tools.getConfig("new_line"));
	final private int newPage = 10;
//	final private int row
	private int lineInvoice = 0;
	private int langague = 0;
	private boolean isNewPage = false;
//  PARAMETER LIST DETAIL        
    private int seq = 0;
	private int indexData = 0;
	private int indexRow = 0;
	private CellStyle styleMoney;
	private CellStyle styleText;
	
	public printInvoice(String type,int printType,boolean isVat) {
		super(type+addVatName(isVat));
		
//		newLine = Integer.parseInt(Tools.getConfig("new_line"));
		langague = printType;
		// TODO Auto-generated constructor stub
	}
	public boolean printXls(invoiceData aData){
		boolean result = false;
		indexRow = 0;
		indexData = 0;
		
//		final int rowDetailStart = 18; 
		invoiceData data = aData;
		
		styleText = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("RSU");
        font.setColor(HSSFColor.RED.index);
        font.setFontHeightInPoints((short) 9);
        styleText.setFont(font);
        styleText.setBorderLeft(XSSFBorderFormatting.BORDER_THIN);
        styleText.setBorderBottom(XSSFBorderFormatting.BORDER_DOTTED);
        
        styleMoney = workbook.createCellStyle();
        styleMoney.setBorderLeft(XSSFBorderFormatting.BORDER_THIN);
        styleMoney.setAlignment(styleMoney.ALIGN_CENTER);
        styleMoney.setFont(font);


		
		// cal row ////
		for(rowInvoice temp : data.getListData()){
			isNewPage = checkLine(temp.getElementData(0));
		}
		
		if(isNewPage){
			printHead(data);
			while((indexData<data.getListData().size())&&(indexRow<rowEnd)){
				rowInvoice temp = data.getListData().get(indexData);
				printOnlyOnePage(temp);
			}	
			sheet = workbook.getSheetAt(1);
			printHead(data);
			indexRow = 0;
		}else{
			////    Invoice Has 1 page
			workbook.removeSheetAt(0);
			sheet = workbook.getSheetAt(0);
			workbook.setSheetName(workbook.getSheetIndex(sheet), "Page 1");
			printHead(data);
			
			
		}
		
		/////////////
        
        
//		Head invoice  page 1
		
		while(indexData<data.getListData().size()){
			rowInvoice temp = data.getListData().get(indexData);
			printOnlyOnePage(temp);
		}
		indexRow = 0;

//      end head
		
		String salesName = data.getCreateName();
		setValueCell(28,14,Tools.changeFormatNumber(data.getTotalNoVat()));
		setValueCell(29,14,Tools.changeFormatNumber(data.getVat()));
		setValueCell(30,14,Tools.changeFormatNumber(data.getTotalAll()));

		
		
		if(langague == EN_TYPE){
			setValueCell(33,4,Tools.convertEngBahtText(data.getTotalAll()));
		}else{
			setValueCell(33,4,Tools.convertThaiBahtText(data.getTotalAll()));
		}
// end invoice
		setValueCell(41,2,salesName);
		setValueCell(42,2,data.getIssueDate());
//
		result = true;
		
		return result;
	}
	
	public boolean printXlsVer2(invoiceData aData){
		boolean result = false;
		indexRow = 0;
		indexData = 0;
		
//		final int rowDetailStart = 18; 
		invoiceData data = aData;
		
		styleText = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("RSU");
        font.setColor(HSSFColor.RED.index);
        font.setFontHeightInPoints((short) 9);
        styleText.setFont(font);
        styleText.setBorderLeft(XSSFBorderFormatting.BORDER_THIN);
        styleText.setBorderBottom(XSSFBorderFormatting.BORDER_DOTTED);
        
        styleMoney = workbook.createCellStyle();
        styleMoney.setBorderLeft(XSSFBorderFormatting.BORDER_THIN);
        styleMoney.setAlignment(styleMoney.ALIGN_CENTER);
        styleMoney.setFont(font);


        ArrayList<rowInvoice> lineDetail = genLine(data.getListData());
		// cal row ////
		
		if(lineDetail.size()>newPage){
			printHead(data);
			while((indexData<rowEnd)&&(indexData<lineDetail.size())){
				printDetail(lineDetail.get(indexData));
				indexRow = indexRow + 1;
				indexData = indexData + 1;
			}
			sheet = workbook.getSheetAt(1);
			printHead(data);
			indexRow = 0;
			
		}else{
			////    Invoice Has 1 page
			workbook.removeSheetAt(0);
			sheet = workbook.getSheetAt(0);
			workbook.setSheetName(workbook.getSheetIndex(sheet), "Page 1");
			printHead(data);

		}
		
		/////////////         
//		Head invoice  page 1
		
		while(indexData<lineDetail.size()){
			printDetail(lineDetail.get(indexData));
			indexRow = indexRow + 1;
			indexData = indexData + 1;
		}
		indexRow = 0;

//      end head
		
		String salesName = data.getCreateName();
		setValueCell(28,14,Tools.changeFormatNumber(data.getTotalNoVat()));
		setValueCell(29,14,Tools.changeFormatNumber(data.getVat()));
		setValueCell(30,14,Tools.changeFormatNumber(data.getTotalAll()));

		
		
		if(langague == EN_TYPE){
			setValueCell(33,4,Tools.convertEngBahtText(data.getTotalAll()));
		}else{
			setValueCell(33,4,Tools.convertThaiBahtText(data.getTotalAll()));
		}
// end invoice
		setValueCell(41,2,salesName);
		setValueCell(42,2,data.getIssueDate());
//
		result = true;
		
		return result;
	}
	
	public void printXls(int intIn){
		
		setValueCell(0,1,intIn);
	}
	public void printXls(String intIn){
		
		setValueCell(0,1,intIn);
	}
	

	private void printHead(invoiceData data){
		String salesNo = data.getCreateUserID();
		String salesName = data.getCreateName();

		
		setValueCell(5,4,data.getCustomerName());
		setValueCell(6,4,data.getCustomerID());
		setValueCell(7,4,data.getAttn());
		String address = data.getAddress();

		if(address.length()<= 95){
			setValueCell(8,4,address);
		}else{
			CellStyle styleAddress = workbook.createCellStyle();
			Font addFont = workbook.createFont();
			addFont.setFontName("RSU");
			addFont.setColor(HSSFColor.BLACK.index);
			addFont.setFontHeightInPoints((short) 8);
			styleAddress.setFont(addFont);
			styleAddress.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			styleAddress.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		    setValueCell(8,4,address, styleAddress);
		}
		
		
		setValueCell(9,4,salesNo);
		setValueCell(10,4,data.getTaxID());
		setValueCell(11,4,data.getTel());
		setValueCell(12,4,data.getEmail());
		setValueCell(5,14,data.getFullInvoice());
		setValueCell(6,14,data.getSubject());
		setValueCell(7,14,data.getIssueDate());
		setValueCell(9,14,salesName);
		
		
		setValueCell(10,14,data.getDueDay());
		setValueCell(11,14,data.getDueDate());
		setValueCell(12,14,data.getRef());

	}
	private void printDetail(rowInvoice temp){
		if(temp.getElementData(1).equals(DISCOUNT)){
			setStyleCell(18+indexRow, 0,styleMoney);
			setStyleCell(18+indexRow, 1,styleText);
			setStyleCell(18+indexRow, 10,styleMoney);
			setStyleCell(18+indexRow, 11,styleMoney);
			setStyleCell(18+indexRow, 14,styleMoney);
		}
		setValueCell(18+indexRow,0,temp.getLineNo());
		setValueCell(18+indexRow,1,temp.getElementData(0));
		setValueCell(18+indexRow, 10, temp.getElementData(2));
		if(Tools.isNotEmpty(temp.getElementData(3))){
			setValueCell(18+indexRow, 11, Tools.changeFormatNumber(temp.getElementData(3)));
		}
		if(Tools.isNotEmpty(temp.getElementData(4))){
			setValueCell(18+indexRow, 14, Tools.changeFormatNumber(temp.getElementData(4)));
		}
	}
	
	private void printOnlyOnePage(rowInvoice temp){
		if(temp.getElementData(1).equals(DISCOUNT)){
			setStyleCell(18+indexRow, 0,styleMoney);
			setStyleCell(18+indexRow, 1,styleText);
			setStyleCell(18+indexRow, 10,styleMoney);
			setStyleCell(18+indexRow, 11,styleMoney);
			setStyleCell(18+indexRow, 14,styleMoney);
		}
		setValueCell(18+indexRow,0,temp.getLineNo());
		setValueCell(18+indexRow,1,temp.getElementData(0));
		setValueCell(18+indexRow, 10, temp.getElementData(2));
		setValueCell(18+indexRow, 11, Tools.changeFormatNumber(temp.getElementData(3)));
		setValueCell(18+indexRow, 14, Tools.changeFormatNumber(temp.getElementData(4)));
	}
	
	
	public ArrayList<rowInvoice> genLine (ArrayList<rowInvoice> data){
		ArrayList<rowInvoice> result = new ArrayList<rowInvoice>();
		int seqNo = 1;
		for(rowInvoice temp : data){
			String[] listSplitNewLine = temp.getElementData(0).split("  ");
			ArrayList<String> listNewLine = subLine(listSplitNewLine);
			if(listNewLine.size()>1){
				for(int i = 0;i<listNewLine.size();i++){
					rowInvoice printRowData = new rowInvoice();
					if(i == 0){
						printRowData = new rowInvoice(seqNo,listNewLine.get(i),
								temp.getElementData(1),
								temp.getElementData(2),
								temp.getElementData(3),
								temp.getElementData(4));
						seqNo = seqNo + 1;
					}else{
						String type = "";
						if(temp.getElementData(1).equals(DISCOUNT)){
							type = temp.getElementData(1);
						}
						printRowData = new rowInvoice(listNewLine.get(i),
								"",
								type,
								"",
								"");
					}
					result.add(printRowData);
				}
			}else{
				rowInvoice printRowData = new rowInvoice(seqNo,temp);
				seqNo = seqNo + 1;
				result.add(printRowData);
			}
			
		}
		
		return result;
	}
	
	public boolean checkLine(String detail){
		boolean isOver = false;
		int endIndex = 0;
		while(detail.length()>0){
			if(newLine>detail.length()){
				endIndex=detail.length();
				lineInvoice = lineInvoice + 1;
			}else{
				endIndex = newLine;
//				while(Tools.checkUpperVowel(detail, endIndex)){
					if(endIndex==detail.length()){
						
					}else{
						endIndex = endIndex + 1;
					}
					lineInvoice = lineInvoice +1;
//				}
			}
			detail = detail.substring(endIndex);
		}
		if(lineInvoice>newPage){
			isOver = true;
		}
		return isOver;
	}
	private static String addVatName(boolean isVat){
		String vatName = "";
		if(isVat){
			vatName = "_vat";
		}else{
			vatName = "";
		}
		return vatName;
	}
	private ArrayList<String> subLine (String[] listDetail){
		ArrayList<String>  result = new ArrayList<String>() ;
		for(String temp : listDetail){
			if(temp.length()>newLine){
				System.out.println(temp.length());
				while(temp.length()>newLine){
					int index = getSplitindex(temp);
					result.add(temp.substring(0,index));
					temp = temp.substring(index);
				}
				if(temp.length()!=0){
					result.add(temp);
				}
			}else{
				result.add(temp);
			}
		}
		return result;
	}
	
	private int getSplitindex(String data){
		int index = 0;
		if(data.length()<newLine){
			index = data.length();
		}else{
			if(consonant.contains(Character.toString(data.charAt(newLine)))){
				index = newLine;
				while(index<data.length()){
					if(consonant.contains(Character.toString(data.charAt(index)))){
						index = index + 1;
					}else{
						return index;
					}
				}
				
			}else{
				index = newLine - 1;
			}
		}
		return index;
	}
}
