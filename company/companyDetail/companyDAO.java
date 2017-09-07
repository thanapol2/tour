/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.companyDetail;

import com.base.BaseDAO;
import com.base.Tools;

import company.meetingLog.meetingRow;
import company.meetingLog.tableMeeting;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import tour.tourListDetail.listDetailData;

/**
 *
 * @author Tong
 */
public class companyDAO extends BaseDAO {

	
	public String genFileName()throws SQLException{
		String genFileName = null;
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		StringBuffer sql = new StringBuffer();
		sql.append("select TO_char(sysdate, 'yyyymmddHH24MISS') gen ");
		sql.append("FROM dual										");
		ResultSet rs = stm.executeQuery(sql.toString());
		while (rs.next()) {
			genFileName = rs.getString("gen");
		}
		rs.close();
		stm.close();
		closeConnection(con);
		return genFileName;
	}
	
		
    public boolean newCompany(companyData data) throws SQLException{
		boolean isOkay = true;
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO ").append(Tools.getPREFIX()).append("COMPANY_MASTER 	");
			sql.append("		(THAI_NAME,			");
			sql.append("		ENG_NAME,			");
			sql.append("		ADDRESS,			");
			sql.append("		POST_NO,			");
			sql.append("		PROVINCE,			");
			sql.append("		TAX_ID,				");
			sql.append("		EMAIL,				");
			sql.append("		TEL_NO,				");
			sql.append("		COMPANY_TYPE,		");
			sql.append("		REMARK)				");
			sql.append("		VALUES (			");
			sql.append("'").append(Tools.nullToEmptyString(data.gettName())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.geteName())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getAddress())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getPostNum())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getProvince())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getTaxID())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getemail())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getTelNo())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getComType())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getRemark())).append("'");
			sql.append(")");	
//			System.out.print(sql.toString());
		    stm.executeUpdate(sql.toString());
		    con.commit();
                    
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
    
    public companyData searchComID(String comId) throws SQLException{
		companyData data = new companyData();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		StringBuffer sql = new StringBuffer();
		sql.append("select COMPANY_ID										");
		sql.append("		, THAI_NAME 									");
		sql.append("		, ENG_NAME	 									");
		sql.append("		, ADDRESS 										");
		sql.append("		, PROVINCE 										");
		sql.append("		, POST_NO 										");
		sql.append("		, TEL_NO										");
		sql.append("		, EMAIL											");
		sql.append("		, TAX_ID										");
		sql.append("		, COMPANY_TYPE									");
		sql.append("		, REMARK										");
		sql.append("FROM  ").append(Tools.getPREFIX()).append("COMPANY_Master		");
		sql.append("where company_id = 										");	
		sql.append("'").append(comId).append("'");
//		System.out.print(sql.toString());
		ResultSet rs = stm.executeQuery(sql.toString());
		// set data information to model class form
		while (rs.next()) {
			data.setComID(rs.getString("COMPANY_ID"));
			data.settName(rs.getString("THAI_NAME"));
			data.seteName(rs.getString("ENG_NAME"));
			data.setAddress(rs.getString("ADDRESS"));
			data.setProvince(rs.getString("PROVINCE"));
			data.setPostNum(rs.getString("POST_NO"));
			data.setTelNo(rs.getString("TEL_NO"));
			data.setemail(rs.getString("EMAIL"));
			data.setTaxID(rs.getString("TAX_ID"));
			data.setComType(rs.getString("COMPANY_TYPE"));
			data.setRemark(rs.getString("REMARK"));
		}
		
		sql = new StringBuffer();
	
		sql.append("select m.SEQ_NO											");
		sql.append("		, m.company_id									");
		sql.append("		, m.Contact_Day									");
		sql.append("		, m.Detail	 									");
		sql.append("		, m.CONTACT_NAME								");
		sql.append("		, m.Phone										");
		sql.append("		, s.TH_NAME || ' ' || s.TH_SURNAME name			");
		sql.append("FROM  ").append(Tools.getPREFIX()).append("meeting_log m	");
		sql.append("inner join ").append(Tools.getPREFIX()).append("sales s		");
		sql.append("on s.SALES_ID = m.SALE_ID									");	
		sql.append("where m.company_id = 										");	
		sql.append("'").append(comId).append("'									");
		sql.append("	  AND m.STATUS = 'Y'									");
		ArrayList<meetingRow> table = new ArrayList<meetingRow>() ;
		rs = stm.executeQuery(sql.toString());
		
		while (rs.next()) {
			meetingRow temp = new meetingRow(rs.getString("SEQ_NO"), 
					rs.getString("company_id"), 
					rs.getString("Contact_Day"), 
					rs.getString("detail"), 
					rs.getString("CONTACT_NAME"),
					rs.getString("Phone"),
					rs.getString("name"));
			table.add(temp);
		}
		data.setTableLog(table);
		rs.close();
		stm.close();
		closeConnection(con);		
		return data;
    }
    
    
	public boolean canInsert(String companyName)throws SQLException{
		boolean isOkay = false;
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) numb	");
		sql.append("FROM  ").append(Tools.getPREFIX()).append("company_master		");
		sql.append("Where THAI_NAME = 		");
		sql.append("'").append(Tools.nullToEmptyString(companyName)).append("'");
		ResultSet rs = stm.executeQuery(sql.toString());
		
		// set data information to model class form
		while (rs.next()) {
			String result = rs.getString("numb");
			if(result.equals("0")){
				isOkay = true;
			}
		}
		rs.close();
		stm.close();
		closeConnection(con);
		return isOkay;
	}
    
	public boolean canEdit(String companyName, String comID)throws SQLException{
		boolean isOkay = false;
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) numb	");
		sql.append("FROM  ").append(Tools.getPREFIX()).append("company_master		");
		sql.append("Where THAI_NAME = 		");
		sql.append("'").append(Tools.nullToEmptyString(companyName)).append("' and");
		sql.append("	  COMPANY_ID != 	");
		sql.append("'").append(Tools.nullToEmptyString(comID)).append("'");
		ResultSet rs = stm.executeQuery(sql.toString());
		
		// set data information to model class form
		while (rs.next()) {
			String result = rs.getString("numb");
			if(result.equals("0")){
				isOkay = true;
			}
		}
		rs.close();
		stm.close();
		closeConnection(con);
		return isOkay;
	}
	public boolean editCompany(companyData data) throws SQLException{
		boolean isOkay = true;
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE  ").append(Tools.getPREFIX()).append("COMPANY_MASTER		");
			sql.append("SET		THAI_NAME    =		");
			sql.append("'").append(Tools.nullToEmptyString(data.gettName())).append("',");
			sql.append("		ENG_NAME	 =		");
			sql.append("'").append(Tools.nullToEmptyString(data.geteName())).append("',");
			sql.append("		ADDRESS		 =		");
			sql.append("'").append(Tools.nullToEmptyString(data.getAddress())).append("',");
			sql.append("		POST_NO		 =		");
			sql.append("'").append(Tools.nullToEmptyString(data.getPostNum())).append("',");
			sql.append("		PROVINCE	 =		");
			sql.append("'").append(Tools.nullToEmptyString(data.getProvince())).append("',");
			sql.append("		TAX_ID		 =		");
			sql.append("'").append(Tools.nullToEmptyString(data.getTaxID())).append("',");
			sql.append("		EMAIL		 =		");
			sql.append("'").append(Tools.nullToEmptyString(data.getemail())).append("',");
			sql.append("		TEL_NO		 =		");
			sql.append("'").append(Tools.nullToEmptyString(data.getTelNo())).append("',");
			sql.append("		COMPANY_TYPE =		");
			sql.append("'").append(Tools.nullToEmptyString(data.getComType())).append("',");
			sql.append("		REMARK		 =		");
			sql.append("'").append(Tools.nullToEmptyString(data.getRemark())).append("'");
			sql.append("WHERE COMPANY_ID	 =		");	
			sql.append("'").append(data.getComID()).append("'");
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
