package payment.search;


import invoice.invoiceDetail.invoiceData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.base.BaseDAO;
import com.base.Tools;

public class paymentSearchDAO extends BaseDAO{

	
	
	public ArrayList<rowPaymentHead> searchPaymentList(searchData data) throws SQLException{
		ArrayList<rowPaymentHead> result = new ArrayList<rowPaymentHead>();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select PAYMENT_TYPE,									");
		sql.append("	PAYMENT_NO,											");
		sql.append("	INVOICE_NO, 										");
		sql.append("	to_char(ISSUE_DATE,'DD/MM/YYYY') ISSUE_DATE,		");
		sql.append("	REJECT,												");
		sql.append("	CUSTOMER_NAME,										");
		sql.append("	to_char(PAYMENT_DATE,'DD/MM/YYYY') PAYMENT_DATE		");
		sql.append("from ").append(Tools.getPREFIX()).append("Search_PAYMENT										");
		sql.append("where PAYMENT_no Like '%").append(Tools.nullToEmptyString(data.getPayment())).append("%' ");
		sql.append("		and CUSTOMER_NAME like '%").append(Tools.nullToEmptyString(data.getName())).append("%' ");
		if(Tools.isNotEmpty(data.getStartDate())&&Tools.isNotEmpty(data.getEndDate())){
			sql.append("	and PAYMENT_DATE between ").append(Tools.genToDateSQL(data.getStartDate())).append(" ");
			sql.append("    and ").append(Tools.genToDateSQL(data.getEndDate())).append(" ");
		}else if(Tools.isNotEmpty(data.getStartDate())){
			sql.append("	and PAYMENT_date >= ").append(Tools.genToDateSQL(data.getStartDate())).append(" ");
		}
		if(Tools.isNotEmpty(data.getInvoiceNo())){
			sql.append("	and INVOICE_NO Like '%").append(Tools.nullToEmptyString(data.getInvoiceNo())).append("%' ");
		}
		if(data.isPaymentVat()&&data.isPaymentNoVat()){
			sql.append("    and  isVat in ('N','Y') 			");
		}else if(data.isPaymentNoVat()){
			sql.append("    and  isVat = 'N' 					");
		}else if(data.isPaymentVat()){
			sql.append("    and  isVat = 'Y' 					");
		}else{
			sql.append("    and  isVat in ('')		 			");
		}
		if(data.getIsReject()){
			sql.append("    and  Reject = 'N' 					");
		}else{
			sql.append("    and  Reject in ('N','Y')			");
		}
		sql.append("order by PAYMENT_NO							");
//		System.out.print(sql.toString());
		ResultSet rs = stm.executeQuery(sql.toString());
		// set data information to model class form
		while (rs.next()) {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(rs.getString("PAYMENT_TYPE"));
			temp.add(rs.getString("PAYMENT_NO"));
			temp.add(rs.getString("INVOICE_NO"));
			temp.add(rs.getString("REJECT"));
			temp.add(rs.getString("PAYMENT_DATE"));
			temp.add(rs.getString("CUSTOMER_NAME"));
			temp.add(rs.getString("ISSUE_DATE"));
			rowPaymentHead row = new rowPaymentHead(temp);
			result.add(row);
		}
		rs.close();
		stm.close();
		closeConnection(con);		
		return result;

	}
	
	public boolean rejectPayment(String paymentNo,boolean isVat)throws SQLException{
		boolean isOkay = false;
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		try {
			StringBuffer sql = new StringBuffer();	
			sql.append("update ").append(Tools.getPREFIX()).append("invoice_head							");
			sql.append("set payment_type = 'N'						");
			sql.append("where invoice_type||invoice_no in 			");
			sql.append("		(select invoice_type||invoice_no	");
			sql.append("		from ").append(Tools.getPREFIX()).append("payment_Detail					");
			sql.append("		where payment_no =					");
			sql.append("		'").append(paymentNo).append("'		");
			if(isVat){
				sql.append("      and ISVAT =  'Y'			");
			}else{
				sql.append("      and ISVAT =  'N'			");
			}
			sql.append(")											");
//			System.out.println(sql.toString());
			stm.executeUpdate(sql.toString());
			sql = new StringBuffer();
			sql.append("Update  ").append(Tools.getPREFIX()).append("payment_detail				");
			sql.append("SET REJECT = 'Y'					");
			sql.append("WHERE PAYMENT_NO =	");
			sql.append("'").append(paymentNo).append("'		");
			if(isVat){
				sql.append("      and ISVAT =  'Y'			");
			}else{
				sql.append("      and ISVAT =  'N'			");
			}
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

	public boolean deletePayment(String paymentNo, boolean isVat) throws SQLException {
		boolean isOkay = false;
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		try {
			StringBuffer sql = new StringBuffer();	
			sql.append("Delete FROM ").append(Tools.getPREFIX()).append("PAYMENT_DETAIL			");
			sql.append("where payment_no =					");
			sql.append("'").append(paymentNo).append("'		");
			if(isVat){
				sql.append("      and ISVAT =  'Y'			");
			}else{
				sql.append("      and ISVAT =  'N'			");
			}
//			System.out.println(sql.toString());
			stm.executeUpdate(sql.toString());
		    con.commit();
            isOkay = true;
		}catch (Exception e) {
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

}
