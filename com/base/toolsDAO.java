/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base;

import com.base.BaseDAO;
import com.base.Tools;
import com.data.payment;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Tong
 */
public class toolsDAO extends BaseDAO {

	public ArrayList<String> getProvince()throws SQLException{
		ArrayList<String> name = new ArrayList<String>();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		name.add("Select");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT province_name				");
		sql.append("FROM  ").append(Tools.getPREFIX()).append("province						");
		sql.append("order by province_id				");
		ResultSet rs = stm.executeQuery(sql.toString());
		
		// set data information to model class form
		while (rs.next()) {
			name.add(rs.getString("province_name"));		
		}
		rs.close();
		stm.close();
		closeConnection(con);
		return name;
	}
	public String getConstant(String id) throws SQLException{
		String result = null;
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT setting						");
		sql.append("FROM  ").append(Tools.getPREFIX()).append("constant						");
		sql.append("Where id = '").append(id).append("'	");
		ResultSet rs = stm.executeQuery(sql.toString());
		while (rs.next()) {
			result = rs.getString("setting");		
		}		
		rs.close();
		stm.close();
		closeConnection(con);
		return result;
	}
	
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
    
	public String getSysdate()throws SQLException{
		String genFileName = null;
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		StringBuffer sql = new StringBuffer();
		sql.append("select TO_char(sysdate, 'dd/mm/yyyy') gen ");
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
	
	public payment getPayment()throws SQLException{
		ArrayList<String> type = new ArrayList<String>();
		ArrayList<String> name = new ArrayList<String>();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		type.add("00");
		name.add("Select");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT PAYMENT_TYPE					");
		sql.append("		,NAME						");
		sql.append("FROM  ").append(Tools.getPREFIX()).append("PAYMENT_TYPE					");
		sql.append("order by PAYMENT_TYPE				");
		ResultSet rs = stm.executeQuery(sql.toString());
		
		// set data information to model class form
		while (rs.next()) {
			type.add(rs.getString("payment_type"));
			name.add(rs.getString("name"));		
		}
		rs.close();
		stm.close();
		closeConnection(con);
		payment data = new payment();
		data.type = type;
		data.name = name;
		return data;
	}
	
}

