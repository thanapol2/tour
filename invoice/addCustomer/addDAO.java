package invoice.addCustomer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.base.BaseDAO;
import com.base.Tools;

public class addDAO extends BaseDAO{

	public invoiceCustomData searchData(String id, String type) throws SQLException{
		invoiceCustomData data = new invoiceCustomData();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();

		// create sql
		StringBuffer sql = new StringBuffer();
		
		///////cusNo;
//		private String cusName;
//		private String attn;
//		private String tax;
//		private String tel;
//		private String email;
//		private String address;
		if(type.equals("tourmember")){
			sql.append("SELECT tour_id		   AS ID,									");
			sql.append("		'คุณ'||thai_name||' '||thai_surname AS name,	");
			sql.append("		''             AS attn,									");
			sql.append("		''             AS tax,									");
			sql.append("		tel			   AS tel,									");
			sql.append("		email	       AS email,								");
			sql.append("		address||' '||province||' '|| POST_NO as address		");
			sql.append("FROM  ").append(Tools.getPREFIX()).append("tour_master												");
			sql.append("Where TOUR_ID = '").append(id).append("'");
		}else{
			sql.append("SELECT  COMPANY_ID	   AS ID,									");
			sql.append("		thai_name      AS name,									");
			sql.append("		''             AS attn,									");
			sql.append("		tax_id         AS tax,									");
			sql.append("		tel_no 		   AS tel,									");
			sql.append("		email		   AS email,								");
			sql.append("		address||' '||province||' '|| POST_NO as address		");
			sql.append("FROM  ").append(Tools.getPREFIX()).append("Company_master											");
			sql.append("Where COMPANY_ID = '").append(id).append("'");
		}
//		System.out.print(sql.toString());
		ResultSet rs = stm.executeQuery(sql.toString());

		while (rs.next()) {
			data.setCusNo(rs.getString("ID"));
			data.setCusName(rs.getString("name"));
			data.setAttn(Tools.nullToEmptyString(rs.getString("attn")));
			data.setTax(Tools.nullToEmptyString(rs.getString("tax")));
			data.setTel(Tools.nullToEmptyString(rs.getString("tel")));
			data.setEmail(Tools.nullToEmptyString(rs.getString("email")));
			data.setAddress(Tools.nullToEmptyString(rs.getString("address")));
			data.setCustomerType(type);
		}
		rs.close();
		stm.close();

		closeConnection(con);
		return data;
	}
}
