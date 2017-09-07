package com.tourSearch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.base.BaseDAO;
import com.base.Tools;
import com.data.memberData;
import com.table.rowMember;


public class tourSearchDAO extends BaseDAO{
	
	
	
	/*
	 *  input Type = 1 com, 2 tour 3 all
	 */
	public ArrayList<rowMember> searchThaiSurname(String thaiSurname,ArrayList<Integer> list) throws SQLException {
		ArrayList<rowMember> resultList = new ArrayList<rowMember>();
		// connect database
		Connection con = this.getConnection();
		Statement stm = con.createStatement();

		// create sql
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT 	ID,				");
		sql.append("		NAME,			");
		sql.append("		TYPE,			");
		sql.append("		EN_NAME			");
		sql.append("FROM  ").append(Tools.getPREFIX()).append("com_tour	");
		sql.append("Where (NAME like '%").append(thaiSurname).append("%' ");
		sql.append(" 		or EN_NAME like UPPER('%").append(thaiSurname).append("%')) ");	
		sql.append(getSearchCondition(list));
//		System.out.println(sql.toString());
		//		sql.append("Order by thai_surname,thai_name");
		ResultSet rs = stm.executeQuery(sql.toString());

		// set data information to model class form
		while (rs.next()) {
			ArrayList<String> tempList = new ArrayList<String>();
			rowMember result = new rowMember();
			result.setMemID((rs.getString("ID")));
			result.setFullName((rs.getString("NAME")));
			result.setGroupType((rs.getString("TYPE")));
			result.seteName((rs.getString("EN_NAME")));
			result.setStringData();
			resultList.add(result);
		}

		rs.close();
		stm.close();

		closeConnection(con);

		return resultList;
	}
	
	/*
	 * 1 Tour
	 * 2 Rest
	 * 3 Hotel
	 * 4 Company
	 * 5 Show
	 * 6 Other
	 * 7 None
	 */
	private String getSearchCondition (ArrayList<Integer> list){
		StringBuilder result = new StringBuilder();
		result.append("		and TYPE in (");
		for(int i=0;i<list.size();i++){
			int condition = list.get(i);
			if(condition==1){
				result.append("'tourmember'");
			}
			if(condition==2){
				result.append("'Restaurant'");
			}
			if(condition==3){
				result.append("'Hotel'");
			}
			if(condition==4){
				result.append("'Company'");
			}
			if(condition==5){
				result.append("'Show'");
			}
			if(condition==6){
				result.append("'Other'");
			}
			if(condition==7){
				result.append("'None'");
			}
			if(i<list.size()-1){
				result.append(",");
			}
		}
		result.append(")					");
		return result.toString();
	}
}
