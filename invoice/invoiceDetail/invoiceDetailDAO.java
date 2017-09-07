package invoice.invoiceDetail;

import invoice.search.rowInvoiceHead;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;






import payment.detail.paymentData;

import com.base.BaseDAO;
import com.base.Tools;
import com.table.rowMember;

public class invoiceDetailDAO extends BaseDAO{

		
	public String getPaymentNo(String invoiceNo,String invoiceType,String rev) throws SQLException{
		String paymentNo = "";
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select PAYMENT_NO										 	 	");
		sql.append("FROM  ").append(Tools.getPREFIX()).append("PAYMENT_DETAIL		");
		sql.append(" where invoice_type = '").append(invoiceType).append("'  		");	
		sql.append(" 	and invoice_NO = '").append(invoiceNo).append("'  			");	
		sql.append(" 	and REVISED = '").append(rev).append("'  					");	
		ResultSet rs = stm.executeQuery(sql.toString());
//		System.out.println(sql.toString());
		while (rs.next()){
			paymentNo = rs.getString("PAYMENT_NO");
		}
		rs.close();
		stm.close();
		closeConnection(con);
		return paymentNo;
	}
	public boolean createNewInvoice(invoiceData data)throws SQLException{
		boolean isOkay = false;
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO ").append(Tools.getPREFIX()).append("INVOICE_HEAD 		");
			sql.append("		(INVOICE_TYPE,			");
			sql.append("		 INVOICE_No,			");
			sql.append("		 REVISED,				");
			sql.append("		 CUSTOMER_ID,			");
			sql.append("         CUSTOMER_NAME,			");
//			sql.append(" 	     CUSTOMER_TYPE,			");
			sql.append("		 ATTN,					");
			sql.append("         ADDRESS,				");
			sql.append("	     TAX_ID,				");
			sql.append("         TEL_NO,				");
			sql.append("         EMAIL,					");
			sql.append("		 ISSUE_DATE,			");
			sql.append("	     DUE_DATE,				");
//			sql.append(" 	     TAX_TYPE,				");
			sql.append("	     VAT,					");
			sql.append(" 	     TOTAL_ALL,				");
			sql.append(" 	     CREATE_USER,			");
			sql.append(" 	     UPDATE_USER,			");
			sql.append(" 	     REF,					");
			sql.append(" 	     subject				");
			sql.append("         )						");
			sql.append("			VALUES(				");
			sql.append("'").append(Tools.nullToEmptyString(data.getInvoiceType())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getInvoiceNo())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getRevised())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getCustomerID())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getCustomerName())).append("',");
//			sql.append("'").append(Tools.nullToEmptyString(data.getCustomerType())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getAttn())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getAddress())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getTaxID())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getTel())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getEmail())).append("',");
			sql.append(Tools.genToDateSQL(data.getIssueDate())).append(",");
			sql.append(Tools.genToDateSQL(data.getDueDate())).append(",");
//			sql.append("'").append(Tools.nullToEmptyString(data.getTaxType())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getVat())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getTotalAll())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getCreateUserID())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getCreateUserID())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getRef())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getSubject())).append("'");
			sql.append(")");
//			String b = sql.toString();
			stm.executeUpdate(sql.toString());
			for(int i=0;i<data.getSize();i++){
				sql = new StringBuffer();
				ArrayList<String>temp = data.getListData().get(i).getStringData();
				sql.append("INSERT INTO ").append(Tools.getPREFIX()).append("INVOICE_DETAIL	");
				sql.append("		(INVOICE_NO,		");
				sql.append("		 INVOICE_TYPE,		");
				sql.append("		 REVISED,			");
				sql.append("         SEQ_NO,			");
				sql.append("		 SERVICE_NAME,		");
				sql.append("         SERVICE_TYPE,		");
				sql.append("	     QTY_AD,			");
				sql.append("         PRICE,				");
				sql.append("		 TOTAL				");
				sql.append("         )					");
				sql.append("			VALUES(			");
				sql.append("'").append(Tools.nullToEmptyString(data.getInvoiceNo())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.getInvoiceType())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.getRevised())).append("',");
				sql.append("'").append(i).append("',");
				sql.append("'").append(Tools.nullToEmptyString(temp.get(0))).append("',");
				sql.append("'").append(Tools.nullToEmptyString(temp.get(1))).append("',");
				sql.append("'").append(Tools.nullToEmptyString(temp.get(2))).append("',");
				sql.append("'").append(Tools.nullToEmptyString(temp.get(3))).append("',");
				sql.append("'").append(Tools.nullToEmptyString(temp.get(4))).append("'");
				sql.append(")");
				String a = sql.toString();
				System.out.println(a);
				stm.executeUpdate(sql.toString());
			}
		    con.commit();
            isOkay = true;
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			isOkay = false;
			
		} finally {
			con.setAutoCommit(true);
			if(stm != null)stm.close();
			if(con != null)closeConnection(con);
		}
		
		return isOkay;
	}
	
	
	public boolean updatePayment(String invoiceType, String invoiceNo, String rev,paymentData data,
			ArrayList<String> memIDCalList)throws SQLException{
		boolean isOkay = false;
		String chequeType = Tools.getConfig("cheque_type");
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		String paymentType = data.getPaymentType();
		try {
			StringBuffer sql = new StringBuffer();
			/// update last payment to null
			sql.append("UPDATE ").append(Tools.getPREFIX()).append("PAYMENT_DETAIL 				");
			sql.append("SET REJECT = 'Y'					");
			sql.append("WHERE INVOICE_Type													");
			sql.append("	||INVOICE_NO = '").append(invoiceType+invoiceNo).append("'		");
			sql.append("	AND REVISED    = '").append(rev).append("'						");
			stm.executeUpdate(sql.toString());
					
			sql = new StringBuffer();
			sql.append("UPDATE ").append(Tools.getPREFIX()).append("INVOICE_HEAD 												");
			sql.append("SET PAYMENT_TYPE = '").append(paymentType).append("'				");
			sql.append("WHERE INVOICE_Type													");
			sql.append("	||INVOICE_NO = '").append(invoiceType+invoiceNo).append("'		");
			sql.append("	AND REVISED    = '").append(rev).append("'						");
			stm.executeUpdate(sql.toString());
			
			
//			if(paymentType.equals(chequeType)){
			sql = new StringBuffer();
			sql.append("INSERT INTO ").append(Tools.getPREFIX()).append("Payment_detail	");
			sql.append("		(INVOICE_NO,		");
			sql.append("		 INVOICE_TYPE,		");
			sql.append("		 REVISED,			");
			sql.append("         CHEQUE_BANK,		");
			sql.append("		 CHEQUE_DATE,		");
			sql.append("		 CHEQUE_NO,			");
			sql.append("		 payment_date,		");
			sql.append("		 detail,			");
			sql.append("		 ISVAT				");
			sql.append("         )					");
			sql.append("			VALUES(			");
			sql.append("'").append(invoiceNo).append("',");
			sql.append("'").append(invoiceType).append("',");
			sql.append("'").append(rev).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getChequeBank())).append("',");
			sql.append(Tools.genToDateSQL(data.getChequeDate())).append(",");
			sql.append("'").append(Tools.nullToEmptyString(data.getChequeNo())).append("',");
			sql.append(Tools.genToDateSQL(data.getPaymentDate())).append(",");
			sql.append("'").append(Tools.nullToEmptyString(data.getDetail())).append("',");
			sql.append("(      select case when vat > 0 then 'Y' else 'N' end				");
			sql.append("FROM  ").append(Tools.getPREFIX()).append("INVOICE_HEAD				");
			sql.append("	   WHERE INVOICE_Type											");
			sql.append("	   ||INVOICE_NO = '").append(invoiceType+invoiceNo).append("'	");
			sql.append("	   AND REVISED    = '").append(rev).append("')					");
			sql.append(")");
//			System.out.print(sql.toString());
			stm.executeUpdate(sql.toString());
			for(String temp : memIDCalList){		
				sql = new StringBuffer();
				sql.append("INSERT INTO ").append(Tools.getPREFIX()).append("POINT_ADJUST	");
				sql.append("		(INVOICE_NO,		");
				sql.append("         INVOICE_TYPE,		");
				sql.append("		 REVISED,			");
				sql.append("		 TOUR_ID,			");
				sql.append("		 STATUS				");
				sql.append("         )					");
				sql.append("			VALUES(			");
				sql.append("'").append(invoiceNo).append("',	");
				sql.append("'").append(invoiceType).append("',	");
				sql.append("'").append(rev).append("',			");
				sql.append("'").append(temp).append("',			");
				sql.append("'").append("N' 						");
				sql.append(")");
				System.out.println(sql.toString());
				stm.executeUpdate(sql.toString());
			}
		
		    con.commit();
            isOkay = true;
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			isOkay = false;
			
		} finally {
			con.setAutoCommit(true);
			if(stm != null)stm.close();
			if(con != null)closeConnection(con);
		}
		
		return isOkay;
	}
	
	
	public invoice getNewInoiceNo(String type) throws SQLException{
		invoice invoice = new invoice();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select INVOICE_TYPE,										 	 ");
		sql.append("		to_char(TO_NUMBER(max(invoice_no)+1),'FM00000000009') no ");
		sql.append("from ").append(Tools.getPREFIX()).append("invoice_head											 	 ");
		sql.append(" where invoice_type = '").append(type).append("'				 ");	
		sql.append("group by INVOICE_TYPE											 ");
		ResultSet rs = stm.executeQuery(sql.toString());
		while (rs.next()){
			invoice.setInvoiceType(rs.getString("Invoice_type"));
			invoice.setInvoiceNo(rs.getString("no"));
			invoice.setRevised("0");
		}
		rs.close();
		stm.close();
		closeConnection(con);
		return invoice;
	}
	
	/*
	 *  0 = no PAYMENT
	 *  1 = some invoice has PAYMENT
	 *  2 = this invoice is Payment
	 */
	public int checkPayment(String invoiceWithType,String aRevised) throws SQLException{
		int isPayment = 0;
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select REVISED				 	 		  			  					  ");
		sql.append("FROM  ").append(Tools.getPREFIX()).append("invoice_head			 		  ");
		sql.append("where invoice_type||INVOICE_NO = '").append(invoiceWithType).append("'    ");	
		sql.append(" 	and PAYMENT_TYPE != 'N'												  ");

		ResultSet rs = stm.executeQuery(sql.toString());
		while (rs.next()){
			String revised = rs.getString("revised");
			if(revised.equals(aRevised)){
				isPayment = 2;
			}else{
				isPayment = 1;
			}
		}

		rs.close();
		stm.close();
		closeConnection(con);
		return isPayment;
	}
	
	public invoice getRevisedInvoiceNo(String invoiceWithType) throws SQLException{
		invoice invoice = new invoice();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select INVOICE_TYPE,							 	 		  			  ");
		sql.append("		INVOICE_NO,														  ");
		sql.append("		max(REVISED)+1 as REVISED										  ");
		sql.append("FROM  ").append(Tools.getPREFIX()).append("invoice_head		 	 		  ");
		sql.append(" where invoice_type||INVOICE_NO = '").append(invoiceWithType).append("' ");	
//		sql.append("		||(select to_char(sysdate, 'YYYY') from dual)			 ");
		sql.append("group by INVOICE_TYPE,INVOICE_NO								 		  ");
		ResultSet rs = stm.executeQuery(sql.toString());
		while (rs.next()){
			invoice.setInvoiceType(rs.getString("Invoice_type"));
			invoice.setInvoiceNo(rs.getString("INVOICE_NO"));
			invoice.setRevised(rs.getString("REVISED"));
		}
		rs.close();
		stm.close();
		closeConnection(con);
		return invoice;
	}
	/*
	 * 00 = id
	 * 01 = name + surname
	 */
	public ArrayList<String> getSalesData(String salesName) throws SQLException{
		ArrayList<String> list = new ArrayList<String>();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		StringBuffer sql = new StringBuffer();
		sql.append("select  TH_NAME||' '||TH_SURNAME as NAME,		 		");
		sql.append("		SALES_ID										 ");
		sql.append("FROM  ").append(Tools.getPREFIX()).append("SALES		 ");
		sql.append(" where TH_NAME = '").append(Tools.nullToEmptyString(salesName)).append("' ");	
		ResultSet rs = stm.executeQuery(sql.toString());
		while (rs.next()){
			list.add(Tools.nullToEmptyString(rs.getString("SALES_ID")));
			list.add(Tools.nullToEmptyString(rs.getString("NAME")));
		}
		rs.close();
		stm.close();
		closeConnection(con);
		return list;
	}
	
	public String getSalesName(String salesID) throws SQLException{
		String salesName = "";
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		StringBuffer sql = new StringBuffer();
		sql.append("select  TH_NAME||' '||TH_SURNAME as NAME		 ");
		sql.append("FROM  ").append(Tools.getPREFIX()).append("SALES	");
		sql.append(" where SALES_ID = '").append(Tools.nullToEmptyString(salesID)).append("' ");	
		ResultSet rs = stm.executeQuery(sql.toString());
		while (rs.next()){
			salesName = rs.getString("NAME");
		}
		rs.close();
		stm.close();
		closeConnection(con);
		return salesName;
	}
	
	public paymentData getPayment(String invoiceWithRev) throws SQLException{
		invoiceWithRev = invoiceWithRev.replace("(", "").replace(")", "");
		String rev;
		if(invoiceWithRev.contains("REV")){
			String [] temp = invoiceWithRev.split("REV");
			invoiceWithRev = temp[0];
			rev = temp[1];
		}else{
			invoiceWithRev = invoiceWithRev;
			rev = "0";
		}
		
		paymentData data = new paymentData();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		StringBuffer sql = new StringBuffer();
		sql.append("select i.INVOICE_TYPE,										");
		sql.append("		i.INVOICE_no,										");
		sql.append("		i.REVISED, 											");
		sql.append("		i.payment_type,										");
		sql.append("		c.payment_no,										");
		sql.append("		c.detail,											");
		sql.append("		c.cheque_bank,										");
		sql.append("		to_char(c.cheque_date,'DD/MM/YYYY') CHEQUE_DATE,	");
		sql.append("		c.cheque_no,										");
		sql.append("		to_char(c.PAYMENT_date,'DD/MM/YYYY') PAYMENT_DATE	");
		sql.append("FROM  ").append(Tools.getPREFIX()).append("invoice_head i	");
		sql.append("left join ").append(Tools.getPREFIX()).append("payment_detail c									");
		sql.append("on i.INVOICE_NO = c.INVOICE_NO								");
		sql.append("		and i.INVOICE_TYPE = c.INVOICE_TYPE					");
		sql.append("		and i.REVISED = c.REVISED							");
		sql.append("WHERE i.INVOICE_Type										");
		sql.append("	||i.INVOICE_NO = '").append(invoiceWithRev).append("'	");
		sql.append("	AND i.REVISED    = '").append(rev).append("'			");
		ResultSet rs = stm.executeQuery(sql.toString());
		// set data information to model class form
		while (rs.next()) {
			if(rs.getString("payment_type").equals("N")){
				data.setPaymentType(rs.getString("payment_type"));
			}else{
				data.setChequeBank(Tools.nullToEmptyString(rs.getString("CHEQUE_BANK")));
				data.setChequeNo(Tools.nullToEmptyString(rs.getString("CHEQUE_NO")));
				data.setChequeDate(Tools.nullToEmptyString(rs.getString("CHEQUE_DATE")));
				data.setPaymentType(rs.getString("payment_type"));
				data.setPaymentDate(rs.getString("payment_date"));
				data.setDetail(rs.getString("detail"));
				data.setPaymentNo(rs.getString("payment_no"));
			}
		}
		rs.close();
		stm.close();
		closeConnection(con);
		return data;
		
	}
//////--------------
	public invoiceData searchInvoiceData(String invoiceNo, String rev) throws SQLException{
		invoiceData invoiceData = new invoiceData();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
//		String invoiceType = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT I.INVOICE_TYPE,									");
		sql.append("		I.INVOICE_NO,									");
		sql.append("		I.REVISED, 										");
		sql.append("		I.CREATE_USER, 									");
		sql.append("		S.TH_NAME||' '||S.TH_SURNAME SALES_NAME,		");
		sql.append("		to_char(I.issue_date,'DD/MM/YYYY') issue_date, 	");
		sql.append("		to_char(I.DUE_DATE,'DD/MM/YYYY') DUE_DATE,		");
		sql.append("		I.due_date - I.issue_date DUE_DAY, 				");
		sql.append("		I.CUSTOMER_ID,									");
		sql.append("		I.CUSTOMER_NAME,								");
		sql.append("		I.ATTN,	 										");
		sql.append("		I.TAX_ID,	 									");
		sql.append("		I.ADDRESS,	 									");
		sql.append("		I.TEL_NO,	 									");  
		sql.append("		I.EMAIL,	 									");  
		sql.append("		I.VAT,											");  
		sql.append("		I.TOTAL_ALL,									"); 
		sql.append("		I.PAYMENT_TYPE,									"); 
		sql.append("		I.REF,											"); 
		sql.append("		I.SUBJECT										"); 
		sql.append("FROM  ").append(Tools.getPREFIX()).append("invoice_head	i	");
		sql.append("LEFT JOIN ").append(Tools.getPREFIX()).append("SALES	s										");
		sql.append("ON i.CREATE_USER = s.SALES_ID							");
		sql.append("WHERE I.INVOICE_Type									");
		sql.append("	||I.INVOICE_NO = '").append(invoiceNo).append("'	");
		sql.append("	AND I.REVISED    = '").append(rev).append("'		");
//		System.out.println(sql.toString());
		ResultSet rs = stm.executeQuery(sql.toString());
		// set data information to model class form
		while (rs.next()) {
//			invoiceType = rs.getString("INVOICE_TYPE");
			invoiceData.setInvoiceType(rs.getString("INVOICE_TYPE"));
			invoiceData.setInvoiceNo(rs.getString("INVOICE_NO"));
			invoiceData.setRevised(rs.getString("REVISED"));
			invoiceData.setCreateUserID(rs.getString("CREATE_USER"));
			invoiceData.setCreateName(rs.getString("SALES_NAME"));
			invoiceData.setIssueDate(rs.getString("ISSUE_DATE"));
			invoiceData.setDueDate(rs.getString("DUE_DATE"));
			invoiceData.setDueDay(rs.getString("DUE_DAY"));
			invoiceData.setCustomerID(rs.getString("CUSTOMER_ID"));
			invoiceData.setCustomerName(rs.getString("CUSTOMER_NAME"));
			invoiceData.setAttn(rs.getString("ATTN"));
			invoiceData.setTaxID(rs.getString("TAX_ID"));
			invoiceData.setAddress(rs.getString("ADDRESS"));
			invoiceData.setTel(rs.getString("TEL_NO"));
			invoiceData.setEmail(rs.getString("EMAIL"));
			invoiceData.setVat(rs.getString("VAT"));
			invoiceData.setTotalAll(rs.getString("TOTAL_ALL"));
			invoiceData.setPayment(rs.getString("PAYMENT_TYPE"));
			invoiceData.setRef(rs.getString("REF"));
			invoiceData.setSubject(rs.getString("SUBJECT"));
		}
		
//     Invoice Detail
		ArrayList<rowInvoice> listDetail = new ArrayList<rowInvoice>();
		sql = new StringBuffer();
		sql.append("SELECT  SERVICE_NAME, 									");
		sql.append("		SERVICE_TYPE,								 	");
		sql.append("		QTY_AD,											");
		sql.append("		PRICE,											");	
		sql.append("		TOTAL											");    
		sql.append("FROM  ").append(Tools.getPREFIX()).append("invoice_DETAIL	");
		sql.append("WHERE INVOICE_Type										");
		sql.append("	||INVOICE_NO = '").append(invoiceNo).append("'		");
		sql.append("	AND REVISED    = '").append(rev).append("'			");
		sql.append("ORDER BY SEQ_NO											");
		rs = stm.executeQuery(sql.toString());
		while (rs.next()) {
			ArrayList<String> row = new ArrayList<String>();
			row.add(rs.getString("SERVICE_NAME"));
			row.add(rs.getString("SERVICE_TYPE"));
			row.add(rs.getString("QTY_AD"));
			row.add(rs.getString("PRICE"));
			row.add(rs.getString("TOTAL"));
			listDetail.add(new rowInvoice(row));			
		}
		invoiceData.setListData(listDetail);
		
		rs.close();
		stm.close();
		closeConnection(con);		
		return invoiceData;

	}
	public invoiceData tranThaiToEng(invoiceData data) throws SQLException {
		String cusId = data.getCustomerID();
		String salesId = data.getCreateUserID();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();

		
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT c.en_name AS cus_name,					");
		sql.append("  s.EN_NAME										");
		sql.append("  ||' '											");
		sql.append("  ||s.EN_SURNAME AS sales_NAME					");
		sql.append("FROM  ").append(Tools.getPREFIX()).append("SALES s,	");
		sql.append("  ").append(Tools.getPREFIX()).append("com_tour c	");
		sql.append("WHERE c.ID = '").append(cusId).append("'		");
		sql.append("AND s.SALES_ID = '").append(salesId).append("'	");
//		System.out.println(sql.toString());
		ResultSet rs = stm.executeQuery(sql.toString());
		while (rs.next()){
			data.setCustomerName(rs.getString("cus_name"));
			data.setCreateName(rs.getString("sales_NAME"));
		}
		
		rs.close();
		stm.close();
		closeConnection(con);	
		return data;
	}
}
class invoice{
	private String invoiceType;
	private String invoiceNo;
	private String revised;
	public invoice(){
		invoiceType = "";
		invoiceNo = "";
		revised = "0";
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoice_type) {
		this.invoiceType = invoice_type;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoice_no) {
		this.invoiceNo = invoice_no;
	}
	public String getRevised() {
		return revised;
	}
	public void setRevised(String revised) {
		this.revised = revised;
	}
	
}

