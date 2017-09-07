package invoice.invoiceXLS;

import java.text.DecimalFormat;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFBorderFormatting;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellAlignment;

import payment.detail.paymentData;

import com.base.Tools;

import invoice.invoiceDetail.invoiceData;

public class printPayment extends createXls{
	
	private final int NEW_LINE = Integer.parseInt(Tools.getConfig("payment_line"));
	private String paymentType = "N";
	public printPayment(String aType) {
		super(aType);
		if(aType.equals("paymentVat")){
			paymentType = "Y";
		}else{
			paymentType = "N";
		}
		// TODO Auto-generated constructor stub
	}

	public boolean printXls(invoiceData aData,paymentData payment){
		boolean result = false;
		invoiceData data = aData;
		int rowHasVat = 0;
		String hasVat = Tools.getConfig("vat_setting");
//		DecimalFormat formatter = new DecimalFormat("#,###.00");
		double vat = Double.parseDouble(payment.getVat());
//		float vat = Float.parseFloat(data.getVat());
		double total = Double.parseDouble(payment.getTotal());
		
		double totalAll = total + vat;
		
		if(paymentType.equals(hasVat)){
					
			String [] sVat = Tools.changeFormatNumber(Double.toString(vat)).split("\\.");
			String [] sTotal = Tools.changeFormatNumber(Double.toString(total)).split("\\.");
			setValueCell(23,4,sTotal[0]);
			setValueCell(23,11,sTotal[1]+" -");
			setValueCell(26,4,sVat[0]);
			setValueCell(26,11,sVat[1]+" -");
			setValueCell(13,4,data.getTaxID());
			rowHasVat = 2 ; /// add Plus row
		}
		setValueCell(7,19,payment.getPaymentDate());
		setValueCell(8,19,payment.getPaymentNo());
		setValueCell(10,4,data.getCustomerName());
		
		
		String address = data.getAddress();
		if(address.length()>85){
			setValueCell(13+rowHasVat,4,address.substring(0, 85));
			setValueCell(14+rowHasVat,4,address.substring(85));
			
		}else{
			setValueCell(13+rowHasVat,4,address);
		}
		
//		StringBuilder sb = new StringBuilder();
//		sb.append("เป็นการชำระเงินสำหรับ Ref. ").append(data.getFullInvoice()).append(" โดยที่จ่ายเมื่อวันที่ ");
//		sb.append(payment.getPaymentDate());
		String detail = payment.getDetail();
		if(detail.length()>NEW_LINE){
			setValueCell(17+rowHasVat,4,detail.substring(0, NEW_LINE));
//			setValueCell(18+rowHasVat,4,detail);
			setValueCellWithLine(18+rowHasVat,4,detail.substring(NEW_LINE));
		}else{
			setValueCell(17+rowHasVat,4,detail);
		}
		
		String [] sTotalAll = Tools.changeFormatNumber(totalAll).split("\\.");
		
		setValueCell(27+rowHasVat,4,sTotalAll[0]);
		setValueCell(27+rowHasVat,11,sTotalAll[1]+" -");
		setValueCell(30+rowHasVat,0,Tools.convertThaiBahtText(totalAll));
		String engText = Tools.convertEngBahtText(totalAll);
		if(engText.length()<= 85){
			setValueCell(31+rowHasVat,0,engText);
		}else{
			CellStyle styleText = workbook.createCellStyle();
			Font font = workbook.createFont();
		    font.setFontName("RSU");
//		    font.setColor(HSSFColor.RED.index);
		    font.setFontHeightInPoints((short) 8);
		    styleText.setFont(font);
		    styleText.setBorderLeft(XSSFBorderFormatting.BORDER_MEDIUM);
	        styleText.setBorderBottom(XSSFBorderFormatting.BORDER_MEDIUM);
	        styleText.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
	        styleText.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		    setValueCell(31+rowHasVat,0,engText, styleText);
		}
		String chequeType = Tools.getConfig("cheque_type");
		if(chequeType.equals(payment.getPaymentType())){
			setValueCell(23+rowHasVat, 19,payment.getChequeBank());
			setValueCell(25+rowHasVat, 19, payment.getChequeNo());
			setValueCell(27+rowHasVat, 19, payment.getChequeDate());
			setValueCell(23+rowHasVat, 15, "X");
		}else{
			setValueCell(21+rowHasVat, 15, "X");
		}
		
		result = true;
		return result;
	}
}
