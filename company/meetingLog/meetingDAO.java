/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.meetingLog;

import com.base.BaseDAO;
import com.base.ConnectionPool;
import com.base.Tools;

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
public class meetingDAO extends BaseDAO {

	
		
    public boolean newMeetingLog(meetingData data) throws SQLException{
		boolean isOkay = true;
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO ").append(Tools.getPREFIX()).append("Meeting_log 	");
			sql.append("		(SALE_ID,			");
			sql.append("		COMPANY_ID,			");
			sql.append("		CONTACT_NAME,			");
			sql.append("		CONTACT_DAY,			");
			sql.append("		CALL_DAY,			");
			sql.append("		DETAIL,				");
			sql.append("		PHONE,				");
			sql.append("		EMAIL				");
			sql.append("         )						");
			sql.append("			VALUES(				");
			sql.append("'").append(ConnectionPool.getUserID()).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getCompanyID())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getContactTo())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getContactDay())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getCallDay())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getDetail())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getPhone())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getEmail())).append("'");
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
    
    public meetingData searchMeetingLog(String seqNo) throws SQLException{
    	meetingData data = new meetingData();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		StringBuffer sql = new StringBuffer();
		sql.append("select ml.SEQ_NO										");
		sql.append("		, ml.COMPANY_ID 								");
		sql.append("		, s.TH_NAME ||' '|| s.TH_SURNAME as name		");
		sql.append("		, c.THAI_NAME									");
		sql.append("		, ml.CONTACT_NAME								");
		sql.append("		, ml.CONTACT_DAY 								");
		sql.append("		, ml.CALL_DAY									");
		sql.append("		, ml.DETAIL										");
		sql.append("		, ml.PHONE										");
		sql.append("		, ml.EMAIL										");
		sql.append("		, REMARK										");
		sql.append("FROM   ").append(Tools.getPREFIX()).append("meeting_log ml		");
		sql.append("inner JOIN ").append(Tools.getPREFIX()).append("sales s		");
		sql.append("on s.SALES_ID = ml.SALE_ID								");
		sql.append("inner JOIN ").append(Tools.getPREFIX()).append("COMPANY_MASTER c	");
		sql.append("ON ml.COMPANY_ID = c.COMPANY_ID							");
		sql.append("where ml.seq_No = 										");	
		sql.append("'").append(seqNo).append("'");
//		System.out.print(sql.toString());
		ResultSet rs = stm.executeQuery(sql.toString());
		// set data information to model class form
		while (rs.next()) {
			data.setSeqNo(rs.getString("SEQ_NO"));
			data.setCompanyID(rs.getString("COMPANY_ID"));
			data.setCreate(rs.getString("NAME"));
			data.setCompanyThaiName(rs.getString("THAI_NAME"));
			data.setContactTo(rs.getString("CONTACT_NAME"));
			data.setContactDay(rs.getString("CONTACT_DAY"));
			data.setCallDay(rs.getString("CALL_DAY"));
			data.setDetail(rs.getString("DETAIL"));		
			data.setPhone(rs.getString("PHONE"));
			data.setEmail(rs.getString("EMAIL"));
			
		}
		rs.close();
		stm.close();
		closeConnection(con);		
		return data;
    }
    
 
	public boolean updateCompany(meetingData data) throws SQLException{
		boolean isOkay = true;
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE  ").append(Tools.getPREFIX()).append("Meeting_log		");
			sql.append("SET		SALE_ID    =		");
			sql.append("'").append(ConnectionPool.getUserID()).append("',");
			sql.append("		COMPANY_ID	 =		");
			sql.append("'").append(Tools.nullToEmptyString(data.getCompanyID())).append("',");
			sql.append("		CONTACT_NAME		 =		");
			sql.append("'").append(Tools.nullToEmptyString(data.getContactTo())).append("',");
			sql.append("		CONTACT_DAY		 =		");
			sql.append("'").append(Tools.nullToEmptyString(data.getContactDay())).append("',");
			sql.append("		CALL_DAY	 =		");
			sql.append("'").append(Tools.nullToEmptyString(data.getCallDay())).append("',");
			sql.append("		DETAIL	 =		");
			sql.append("'").append(Tools.nullToEmptyString(data.getDetail())).append("',");
			sql.append("		PHONE	 =		");
			sql.append("'").append(Tools.nullToEmptyString(data.getPhone())).append("',");
			sql.append("		EMAIL	 =		");
			sql.append("'").append(Tools.nullToEmptyString(data.getEmail())).append("'");
			sql.append("WHERE SEQ_NO	 =		");	
			sql.append("'").append(data.getSeqNo()).append("'");
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

	public boolean delete(String seqNo) throws SQLException{
		boolean isOkay = true;
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE  ").append(Tools.getPREFIX()).append("Meeting_log		");
			sql.append("SET		Status    =		");
			sql.append("'N'						");
			sql.append("WHERE SEQ_NO	 =		");	
			sql.append("'").append(seqNo).append("'");
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
