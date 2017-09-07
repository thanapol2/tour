package payment.detail;

import invoice.invoiceDetail.invoiceData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.base.BaseDAO;
import com.base.Tools;

public class paymentDAO extends BaseDAO{
	
	public String getSalesName(String salesID) throws SQLException{
		String salesName = "";
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		StringBuffer sql = new StringBuffer();
		sql.append("select  TH_NAME||' '||TH_SURNAME as NAME		 ");
		sql.append("from ").append(Tools.getPREFIX()).append("SALES							 	 		  ");
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
	
	public boolean updatePayment(invoiceData invoice,paymentData payment) throws SQLException{
		boolean isOkay = false;
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		try {
			String paymentNo = payment.getPaymentNo();
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE ").append(Tools.getPREFIX()).append("PAYMENT_DETAIL				");
			sql.append("SET CHEQUE_BANK = 					");
			sql.append("'").append(Tools.nullToEmptyString(payment.getChequeBank())).append("',	");
			sql.append("	CHEQUE_DATE =					");
			sql.append(Tools.genToDateSQL(payment.getChequeDate())).append(",					");
			sql.append("	CHEQUE_NO 	=					");
			sql.append("'").append(Tools.nullToEmptyString(payment.getChequeNo())).append("',	");
			sql.append("	payment_date =					");
			sql.append(Tools.genToDateSQL(payment.getPaymentDate())).append(",					");
			sql.append("	detail		=					");
			sql.append("'").append(Tools.nullToEmptyString(payment.getDetail())).append("'		");
			sql.append("WHERE PAYMENT_NO = 					");
			sql.append("'").append(Tools.nullToEmptyString(paymentNo)).append("'		");
			stm.executeUpdate(sql.toString());
			
//			System.out.println(sql.toString());
			
			sql = new StringBuffer();
			sql.append("UPDATE ").append(Tools.getPREFIX()).append("INVOICE_HEAD				");
			sql.append("SET CUSTOMER_ID   =				");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getCustomerID())).append("',		");
			sql.append("    CUSTOMER_NAME =				");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getCustomerName())).append("',	");
			sql.append("    TAX_ID =					");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getTaxID())).append("',	");
			sql.append("    ADDRESS  =					");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getAddress())).append("',		");
			sql.append("	VAT		 =					");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getVat())).append("',			");
			sql.append(" 	TOTAL_ALL =					");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getTotalAll())).append("'		");
			sql.append("WHERE (invoice_type,invoice_no,revised) IN				");
			sql.append("	(SELECT invoice_type,invoice_no,revised				");
			sql.append("	 FROM payment_detail								");
			sql.append("	 WHERE PAYMENT_NO = 								");
			sql.append("'").append(Tools.nullToEmptyString(paymentNo)).append("')		");

//			System.out.println(sql.toString());
			
			stm.executeUpdate(sql.toString());
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
	
	public boolean createNewPaymentNonInvoice(invoiceData invoice,paymentData payment)throws SQLException{
		boolean isOkay = false;
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO ").append(Tools.getPREFIX()).append("INVOICE_HEAD 		");
			sql.append("		(INVOICE_NO,			");
			sql.append("		 INVOICE_TYPE,			");
			sql.append("		 REVISED,				");
			sql.append("         CUSTOMER_ID,			");
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
			sql.append("	     PAYMENT_TYPE,			");
			sql.append(" 	     TOTAL_ALL,				");
			sql.append(" 	     CREATE_USER,			");
			sql.append(" 	     UPDATE_USER,			");
			sql.append(" 	     REF,					");
			sql.append(" 	     subject				");
			sql.append("         )						");
			sql.append("			VALUES(				");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getInvoiceNo())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getInvoiceType())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getRevised())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getCustomerID())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getCustomerName())).append("',");
//			sql.append("'").append(Tools.nullToEmptyString(data.getCustomerType())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getAttn())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getAddress())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getTaxID())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getTel())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getEmail())).append("',");
			sql.append(Tools.genToDateSQL(invoice.getIssueDate())).append(",");
			sql.append(Tools.genToDateSQL(invoice.getDueDate())).append(",");
//			sql.append("'").append(Tools.nullToEmptyString(data.getTaxType())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(payment.getVat())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(payment.getPaymentType())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getTotalAll())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getCreateUserID())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getCreateUserID())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getRef())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getSubject())).append("'");
			sql.append(")");
			stm.executeUpdate(sql.toString());
			
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
			sql.append("'").append(Tools.nullToEmptyString(invoice.getInvoiceNo())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getInvoiceType())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(invoice.getRevised())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(payment.getChequeBank())).append("',");
			sql.append(Tools.genToDateSQL(payment.getChequeDate())).append(",");
			sql.append("'").append(Tools.nullToEmptyString(payment.getChequeNo())).append("',");
			sql.append(Tools.genToDateSQL(payment.getPaymentDate())).append(",");
			sql.append("'").append(Tools.nullToEmptyString(payment.getDetail())).append("',");
			if(payment.getHasVat()){
				sql.append("'Y'		");
			}else{
				sql.append("'N'		");
			}
			sql.append(")");
//			System.out.println(sql.toString());
			stm.executeUpdate(sql.toString());
			
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
	
	public String getNewInoiceNo() throws SQLException{
		String invoiceNo = "";
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select MAX(invoice_no)+1 no ");
//		sql.append("		to_char(TO_NUMBER(max(invoice_no)+1),'FM0000009') no 	 ");
		sql.append("from ").append(Tools.getPREFIX()).append("invoice_head											 	 ");
		sql.append(" where invoice_type = 'Non'										 ");	
//		sql.append("		||(select to_char(sysdate, 'YYYY') from dual)			 ");
		sql.append("group by INVOICE_TYPE											 ");
		ResultSet rs = stm.executeQuery(sql.toString());
		while (rs.next()){
			invoiceNo = rs.getString("no");
		}
		rs.close();
		stm.close();
		closeConnection(con);
		return invoiceNo;
	}

	public String getPaymentNo(String invoiceNo) throws SQLException{
		String paymentNo = "";
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select payment_no 						 ");
//		sql.append("		to_char(TO_NUMBER(max(invoice_no)+1),'FM0000009') no 	 ");
		sql.append("from ").append(Tools.getPREFIX()).append("payment_detail						 ");
		sql.append(" where invoice_no = '").append(invoiceNo).append("'		    ");
		sql.append("   AND invoice_type = 'Non'		    ");
		System.out.print(sql.toString());
		ResultSet rs = stm.executeQuery(sql.toString());
		while (rs.next()){
			paymentNo = rs.getString("payment_no");
		}
		
		rs.close();
		stm.close();
		closeConnection(con);
		return paymentNo;
	}
	
	public String getTaxID(String companyID) throws SQLException{
		String taxID = "";
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT TAX_ID			");
		sql.append("FROM ").append(Tools.getPREFIX()).append("COMPANY_MASTER		");
		sql.append("WHERE COMPANY_ID = '").append(companyID).append("' ");
		ResultSet rs = stm.executeQuery(sql.toString());
		while (rs.next()){
			taxID = Tools.nullToEmptyString(rs.getString("TAX_ID"));
		}
		rs.close();
		stm.close();
		closeConnection(con);
		return taxID;
	}
	
	public searchPaymentData searchPayment(String paymentNo,String isVat,String salesID) throws SQLException{
		searchPaymentData data= new searchPaymentData();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ph.payment_no,									");
		sql.append("  ph.customer_id,										");
		sql.append("  ph.customer_name,										");
		sql.append("  ph.address,											");
		sql.append("  ph.detail ,											");
		sql.append("  ph.TOTAL_ALL,											");
		sql.append("  ph.VAT,												");
		sql.append("  ph.TAX_ID,											");
		sql.append("  ph.ISVAT,												");
		sql.append("  pt.NAME as payment_name,								");
		sql.append("  ph.payment_date,										");
		sql.append("  ph.CHEQUE_BANK,										");
		sql.append("  ph.CHEQUE_DATE,										");
		sql.append("  ph.CHEQUE_NO,											");
		sql.append("  s.SALES_ID,											");
		sql.append("  s.TH_NAME||' '||s.TH_SURNAME as sales_name			");
		sql.append("FROM 													");
		sql.append("  (SELECT p.payment_no,									");
		sql.append("		    h.customer_id,								");
		sql.append("		    h.customer_name, 							");
		sql.append("		    h.address,									");
		sql.append("		    p.detail ,									");
		sql.append("		    TO_CHAR(h.TOTAL_ALL-h.VAT,'FM999999999.00') TOTAL_ALL,	");
		sql.append("		    h.payment_type,								");
		sql.append("		    h.VAT,										");
		sql.append("		    h.TAX_ID,									");
		sql.append("			p.ISVAT,										");
		sql.append("		    TO_CHAR(p.PAYMENT_DATE,'DD/MM/YYYY') payment_date,	");
		sql.append("		    p.CHEQUE_BANK,								");
		sql.append("		    TO_CHAR(p.CHEQUE_DATE,'DD/MM/YYYY') CHEQUE_DATE,	");
		sql.append("		    p.CHEQUE_NO									");
		sql.append("	FROM ").append(Tools.getPREFIX()).append("payment_detail p								");
		sql.append("	LEFT JOIN ").append(Tools.getPREFIX()).append("invoice_head h							");
		sql.append("		  ON p.invoice_no    = h.invoice_no				");
		sql.append("		  AND p.REVISED      = h.REVISED				");
		sql.append("		  AND p.invoice_type = h.invoice_type			");
		sql.append("		  WHERE p.PAYMENT_NO = '").append(Tools.nullToEmptyString(paymentNo)).append("' ");
		sql.append("		  AND   p.ISVAT = '").append(Tools.nullToEmptyString(isVat)).append("' ");
		sql.append("		  ) ph,											");
		sql.append("  ").append(Tools.getPREFIX()).append("PAYMENT_TYPE pt,										");
		sql.append("  ").append(Tools.getPREFIX()).append("SALES s												");
		sql.append("WHERE pt.PAYMENT_TYPE = ph.PAYMENT_TYPE					");
		sql.append("AND S.SALES_ID = '").append(Tools.nullToEmptyString(salesID)).append("' ");	
		System.out.println(sql.toString());
		
		ResultSet rs = stm.executeQuery(sql.toString());
		while (rs.next()){
			data.setPaymentNo(rs.getString("payment_no"));
			data.setSalesID(rs.getString("SALES_ID"));
			data.setSalesName(rs.getString("SALES_NAME"));
			data.setCustomerID(rs.getString("customer_id"));
			data.setCustomerName(rs.getString("customer_NAME"));
			data.setAddress(Tools.nullToEmptyString(rs.getString("address")));
			data.setDetail(Tools.nullToEmptyString(rs.getString("detail")));
			data.setTotal(Tools.nullToEmptyString(rs.getString("total_all")));
			data.setHasVat(Tools.nullToEmptyString(rs.getString("ISVAT")));
			data.setVat(Tools.nullToEmptyString(rs.getString("vat")));
			data.setTaxID(Tools.nullToEmptyString(rs.getString("TAX_ID")));
			data.setPaymentType(rs.getString("payment_NAME"));
			data.setPaymentDate(Tools.nullToEmptyString(rs.getString("PAYMENT_DATE")));
			data.setChequeBank(Tools.nullToEmptyString(rs.getString("CHEQUE_BANK")));
			data.setChequeDate(Tools.nullToEmptyString(rs.getString("CHEQUE_DATE")));
			data.setChequeNo(Tools.nullToEmptyString(rs.getString("CHEQUE_NO")));
		}
		rs.close();
		stm.close();
		closeConnection(con);
		return data;
	}
}

