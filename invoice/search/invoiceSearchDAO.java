package invoice.search;


import invoice.invoiceDetail.invoiceData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.base.BaseDAO;
import com.base.Tools;

public class invoiceSearchDAO extends BaseDAO{

	public boolean isPayment(String invoiceWithType) throws SQLException{
		boolean isPayment = false;
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(PAYMENT_TYPE) as payment			 	 		  			  ");
		sql.append("from ").append(Tools.getPREFIX()).append("invoice_head											 	 		  ");
		sql.append("where invoice_type||INVOICE_NO = '").append(invoiceWithType).append("'    ");	
		sql.append(" 	and PAYMENT_TYPE != 'N'												  ");
		sql.append("group by INVOICE_TYPE,INVOICE_NO								 		  ");
		ResultSet rs = stm.executeQuery(sql.toString());
		while (rs.next()){
			if(Tools.isEmptyOrZero(rs.getString("payment"))){
				isPayment = false;
			}else{
				isPayment = true;
			}
		}

		rs.close();
		stm.close();
		closeConnection(con);
		return isPayment;
	}
	
	
	public ArrayList<rowInvoiceHead> searchInvoiceList(searchData data,boolean isTour,boolean isMainAccount) throws SQLException{
		ArrayList<rowInvoiceHead> result = new ArrayList<rowInvoiceHead>();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select INVOICE_NO										");
//		sql.append("		,REVISED 										");
		sql.append("		, CUSTOMER_NAME									");
		sql.append("		, PAYMENT_TYPE									");
		sql.append("		,to_char(ISSUE_DATE,'DD/MM/YYYY') ISSUE_DATE	");
		sql.append("		,CREATE_USER									");
		sql.append("from ").append(Tools.getPREFIX()).append("search_invoice										");
		sql.append("where invoice_no Like '%").append(Tools.nullToEmptyString(data.getInvoiceNo())).append("%' ");
		sql.append("		and CUSTOMER_NAME like '%").append(Tools.nullToEmptyString(data.getName())).append("%' ");
		if(isTour){
			sql.append("		and INVOICE_NO like 'RT2%' ");
		}else{
			sql.append("		and INVOICE_NO like 'RTS%' ");
		}
		if(Tools.isNotEmpty(data.getStartDate())&&Tools.isNotEmpty(data.getEndDate())){
			sql.append("	and Issue_date between ").append(Tools.genToDateSQL(data.getStartDate())).append(" ");
			sql.append("    and ").append(Tools.genToDateSQL(data.getEndDate())).append(" ");
		}else if(Tools.isNotEmpty(data.getStartDate())){
			sql.append("	and Issue_date >= ").append(Tools.genToDateSQL(data.getStartDate())).append(" ");
		}
		if(data.getPaymanet()){
			sql.append("	and PAYMENT_TYPE != 'N'							");
		}
		if(Tools.isNotEmpty(data.getCreateUser())){
			sql.append("	and CREATE_USER like '%").append(Tools.nullToEmptyString(data.getCreateUser())).append("%' ");
		}
		if(isMainAccount){
			
		}else{
			sql.append("	and HAS_VAT = 'N' ");
		}
		sql.append("order by invoice_no	desc,ISSUE_DATE	desc");
		System.out.print(sql.toString());
		ResultSet rs = stm.executeQuery(sql.toString());
		// set data information to model class form
		while (rs.next()) {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(rs.getString("INVOICE_NO"));
//			temp.add(rs.getString("REVISED"));
			temp.add(rs.getString("CUSTOMER_NAME"));
			temp.add(rs.getString("PAYMENT_TYPE"));
			temp.add(rs.getString("ISSUE_DATE"));
			temp.add(rs.getString("CREATE_USER"));
			rowInvoiceHead row = new rowInvoiceHead(temp);
			result.add(row);
		}
		rs.close();
		stm.close();
		closeConnection(con);		
		return result;

	}
	
	public boolean deleteAllInvoice(String invoiceNoNonRev)throws SQLException{
		boolean isOkay = false;
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM ").append(Tools.getPREFIX()).append("invoice_head 			");
			sql.append("WHERE invoice_type||invoice_no =	");
			sql.append("'").append(invoiceNoNonRev).append("'");
			stm.executeUpdate(sql.toString());
			 sql = new StringBuffer();
			sql.append("DELETE FROM ").append(Tools.getPREFIX()).append("invoice_detail 			");
			sql.append("WHERE invoice_type||invoice_no =	");
			sql.append("'").append(invoiceNoNonRev).append("'");
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

}
