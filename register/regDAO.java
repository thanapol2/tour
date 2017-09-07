/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import com.base.BaseDAO;
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
public class regDAO extends BaseDAO {

	
	public regData getData(String salesID)throws SQLException{
		regData data = new regData();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		StringBuffer sql = new StringBuffer();
		sql.append("select SALES_ID								");
	    sql.append("	,TH_TITTLE								");
	    sql.append("	,TH_NAME								");
	    sql.append("	,TH_SURNAME								");
	    sql.append("	,EN_TITTLE								");
	    sql.append("	,EN_NAME								");
	    sql.append("	,EN_SURNAME								");
	    sql.append("	,NVL(USER_NAME,'')	as USER_NAME		");
	    sql.append("	,ROLE_NAME								");
		sql.append("FROM sales									");
		sql.append("where SALES_ID = '").append(salesID).append("'	");
		ResultSet rs = stm.executeQuery(sql.toString());
		while (rs.next()) {
			data.setSaleID(rs.getString("SALES_ID"));
			data.settTittle(rs.getString("TH_TITTLE"));
			data.settFirst(rs.getString("TH_NAME"));
			data.settSurName(rs.getString("TH_SURNAME"));
			data.seteTitle(rs.getString("EN_TITTLE"));
			data.seteFirst(rs.getString("EN_NAME"));
			data.seteSurName(rs.getString("EN_SURNAME"));
			data.setUser(rs.getString("USER_NAME"));
//			data.setPass(rs.getString("SALES_ID"));
			data.setRole(rs.getString("ROLE_NAME"));
		}
		rs.close();
		stm.close();
		closeConnection(con);
		return data;
	}
	
	public ArrayList<String> getSalesID()throws SQLException{
		ArrayList<String> data = new ArrayList<String>();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		StringBuffer sql = new StringBuffer();
		sql.append("select SALES_ID								");
		sql.append("FROM sales									");
		sql.append("WHERE sales_ID != '00'						");
		ResultSet rs = stm.executeQuery(sql.toString());
		data.add("New User");
		while (rs.next()) {
			data.add(rs.getString("SALES_ID"));

		}
		rs.close();
		stm.close();
		closeConnection(con);
		return data;
	}
		
    public boolean update(regData data,boolean newData,boolean newUser) throws SQLException{
		boolean isOkay = true;
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		try {
			StringBuffer sql = new StringBuffer();
			if(newData){
				sql.append("select max(sales_id)+1 as sales_id  ");
				sql.append("from sales							");
				ResultSet rs = stm.executeQuery(sql.toString());
				while (rs.next()) {
					data.setSaleID(rs.getString("SALES_ID"));
				}

			}
			sql = new StringBuffer();
			if(newUser){
				sql = new StringBuffer();
				sql.append("create user 				");
				sql.append(data.getUser()).append(" 	");
				sql.append("IDENTIFIED BY 				");
				sql.append(data.getPass());
				System.out.println(sql.toString());
				stm.executeQuery(sql.toString());
				
				sql = new StringBuffer();
				sql.append("grant dba,account to 		");
				sql.append(data.getUser()).append(" 	");
				System.out.println(sql.toString());
				stm.executeQuery(sql.toString());			 	
			}else{
				sql.append("alter user 					");
				sql.append(data.getUser()).append(" 	");
				sql.append("IDENTIFIED BY 				");
				sql.append(data.getPass());
				stm.executeQuery(sql.toString());
			}
			sql = new StringBuffer();
			sql.append("merge INTO sales a USING					");
			sql.append("(SELECT count(sales_id) cid FROM sales 		");
			sql.append(" WHERE sales_id =							");
			sql.append("'").append(data.getSaleID()).append("')		");
			sql.append(" b ON (b.cid > 0)							");
			sql.append("WHEN MATCHED THEN							");				
			sql.append("UPDATE  		 							");
			sql.append("SET		TH_TITTLE =							");
			sql.append("'").append(data.gettTittle()).append("',	");
			sql.append("		TH_NAME =							");
			sql.append("'").append(data.gettFirst()).append("',		");
			sql.append("		TH_SURNAME =						");
			sql.append("'").append(data.geteSurName()).append("',	");
			sql.append("		EN_TITTLE =							");
			sql.append("'").append(data.geteTitle()).append("',		");
			sql.append("		EN_NAME =							");
			sql.append("'").append(data.geteFirst()).append("',		");
			sql.append("		EN_SURNAME =						");
			sql.append("'").append(data.geteSurName()).append("',	");
			sql.append("		USER_NAME =							");
			sql.append("'").append(data.getUser()).append("',		");
			sql.append("		ROLE_NAME =							");
			sql.append("'").append(data.getRole()).append("'		");
			sql.append(" where sales_id = 							");
			sql.append("'").append(data.getSaleID()).append("'		");
			sql.append("WHEN NOT MATCHED THEN						");
		    sql.append("INSERT ( SALES_ID							");
		    sql.append("	,TH_TITTLE								");
		    sql.append("	,TH_NAME								");
		    sql.append("	,TH_SURNAME								");
		    sql.append("	,EN_TITTLE								");
		    sql.append("	,EN_NAME								");
		    sql.append("	,EN_SURNAME								");
		    sql.append("	,USER_NAME								");
		    sql.append("	,ROLE_NAME								");
		    sql.append(") values ( '").append(data.getSaleID()).append("'	");
		    sql.append("	,'").append(data.gettTittle()).append("'		");
		    sql.append("	,'").append(data.gettFirst()).append("'			");
		    sql.append("	,'").append(data.geteSurName()).append("'		");
		    sql.append("	,'").append(data.geteTitle()).append("'			");
		    sql.append("	,'").append(data.geteFirst()).append("'			");
		    sql.append("	,'").append(data.geteSurName()).append("'		");
		    sql.append("	,'").append(data.getUser()).append("'			");
		    sql.append("	,'").append(data.getRole()).append("'			");
		    sql.append(")													");
		    
//			System.out.println(sql.toString());
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
    
}