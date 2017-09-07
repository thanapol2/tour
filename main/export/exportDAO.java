package main.export;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.base.BaseDAO;
import com.base.Tools;
import com.data.memberData;
import com.table.rowMember;


public class exportDAO extends BaseDAO{
	
	
	
	/*
	 *  input Type = 1 com, 2 tour 3 all
	 */
	public ArrayList<exportData> getAllData() throws SQLException {
		ArrayList<exportData> resultList = new ArrayList<exportData>();
		// connect database
		Connection con = this.getConnection();
		Statement stm = con.createStatement();

		// create sql
		StringBuffer sql = new StringBuffer();
		
		
		sql.append("SELECT 'คุณ '						");
		sql.append("  ||THAI_NAME					");
		sql.append("  ||' '							");
		sql.append("  ||THAI_SURNAME AS name,		");
		sql.append("  address						");
		sql.append("  ||' '							");
		sql.append("  ||PROVINCE					");
		sql.append("  ||' '							");
		sql.append("  ||POST_NO AS address,			");
		sql.append("  nvl(tel,'none') as tel		");
		sql.append("FROM ").append(Tools.getPREFIX()).append("tour_master		");
		sql.append("WHERE address IS NOT NULL		");
		sql.append("AND post_no   IS NOT NULL		");
		
		System.out.println(sql.toString());
		ResultSet rs = stm.executeQuery(sql.toString());

		// set data information to model class form
		while (rs.next()) {
			String name = rs.getString("NAME");
			String address = rs.getString("address");
			String tel = rs.getString("tel");
			exportData result = new exportData(name,address,tel);
			resultList.add(result);
		}

		rs.close();
		stm.close();

		closeConnection(con);

		return resultList;
	}
	
}
