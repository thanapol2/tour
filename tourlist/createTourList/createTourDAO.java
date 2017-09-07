package tourlist.createTourList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import java.util.ArrayList;

import com.base.BaseDAO;
import com.base.Tools;
import com.table.rowMember;

public class createTourDAO extends BaseDAO{
   
	public ArrayList<rowMember> searchTourID(ArrayList<String> tourID) throws SQLException{
		ArrayList<rowMember> result = new ArrayList<rowMember>();
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select TOUR_ID											");
		sql.append("		,THAI_TITLE 									");
		sql.append("		, THAI_NAME 									");
		sql.append("		, THAI_SURNAME 									");
		sql.append("		, ENG_TITLE 									");
		sql.append("		, ENG_NAME 										");
		sql.append("		, ENG_SURNAME 									");
		sql.append("		, PASSPORT_ID 									");
		sql.append("		,to_char(BIRTH_DAY,'DD/MM/YYYY') BIRTH_DAY		");
		sql.append("		,to_char(ISSUE_DATE,'DD/MM/YYYY') ISSUE_DATE	");
		sql.append("		,to_char(EXPIRE_DATE,'DD/MM/YYYY') EXPIRE_DATE	");
		sql.append("		, EAT_DETAIL 									");
		sql.append("		, PATH_PIC 										");
		sql.append("from ").append(Tools.getPREFIX()).append("tour_Master										");
		sql.append("where tour_id in (										");	
		for(int i =0;i<tourID.size();i++){
			sql.append("'").append(tourID.get(i)).append("'");
			if(i==tourID.size()-1){
				sql.append(")												");	
			}else{
				sql.append(",												");	
			}
		}
//		System.out.print(sql.toString());
		ResultSet rs = stm.executeQuery(sql.toString());
		// set data information to model class form
		while (rs.next()) {
			ArrayList<String> temp = new ArrayList<String>();
			String picName;
			temp.add(rs.getString("TOUR_ID"));
			temp.add(rs.getString("THAI_TITLE"));
			temp.add(rs.getString("THAI_NAME"));
			temp.add(rs.getString("THAI_SURNAME"));
			temp.add(rs.getString("ENG_TITLE"));
			temp.add(rs.getString("ENG_NAME"));
			temp.add(rs.getString("ENG_SURNAME"));
			temp.add(rs.getString("PASSPORT_ID"));	
			temp.add(rs.getString("BIRTH_DAY"));
			temp.add(rs.getString("ISSUE_DATE"));
			temp.add(rs.getString("EXPIRE_DATE"));
			temp.add(""); 
			temp.add("");
			temp.add("Tour");
//			temp.add("");
//			temp.add("");
//			temp.add("");
			temp.add(rs.getString("EAT_DETAIL"));
			picName = rs.getString("PATH_PIC");
			rowMember data = new rowMember(temp,picName);
			result.add(data);
		}
		rs.close();
		stm.close();
		closeConnection(con);		
		return result;
    }	
	
	public boolean insertRoomlist(createTourData data)throws SQLException{
		boolean isOkay = false;
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO ").append(Tools.getPREFIX()).append("ROOMLIST_HEAD 	");
			sql.append("		(RLIST_NO,			");
			sql.append("         ROOMLIST_NAME,		");
			sql.append("		 GROUP_NAME,		");
			sql.append("         DEP_DATE,			");
			sql.append("	     ARR_DATE,			");
			sql.append("         HOTEL_NAME,		");
			sql.append("         ARR_NO,			");
			sql.append("		 ARR_NAME,			");
			sql.append("	     ARR_TIME,			");
			sql.append(" 	     DEP_NO,			");
			sql.append("		 DEP_NAME,			");
			sql.append("		 DEP_TIME,			");
			sql.append("		 DESTINATION		");
			sql.append("         )					");
			sql.append("			VALUES(			");
			sql.append("'").append(Tools.nullToEmptyString(data.getRoomListNo())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getListName())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getGroupName())).append("',");
			sql.append(Tools.genToDateSQL(data.getDeparteDate())).append(",");
			sql.append(Tools.genToDateSQL(data.getArrDate())).append(",");
			sql.append("'").append(Tools.nullToEmptyString(data.getHotelName())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getArrNo())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getArrFlight())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getArrTime())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getDepartNo())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getDepartFlight())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getDepartTime())).append("',");
			sql.append("'").append(Tools.nullToEmptyString(data.getDestination())).append("'");
			sql.append(")");	
//			String a = sql.toString();
			stm.executeUpdate(sql.toString());
			
			for(int i=0;i<data.getSize();i++){
				sql = new StringBuffer();
				ArrayList<String>temp = data.getRowData(i);
				sql.append("INSERT INTO ").append(Tools.getPREFIX()).append("ROOMLIST_DETAIL	");
				sql.append("		(RLIST_NO,			");
				sql.append("         SEQ_NO,			");
				sql.append("		 TOUR_ID,			");
				sql.append("         ROOM,				");
				sql.append("	     BUS_NO,			");
				sql.append("         MEM_TYPE,			");
				sql.append("         REMARK,			");
				sql.append("		 SHOP,				");
				sql.append("	     SHOP_EXTRA			");
				sql.append("         )					");
				sql.append("			VALUES(			");
				sql.append("'").append(Tools.nullToEmptyString(data.getRoomListNo())).append("',");
				sql.append("'").append(i+1).append("',");
				sql.append("'").append(Tools.nullToEmptyString(temp.get(0))).append("',");
				sql.append("'").append(Tools.nullToEmptyString(temp.get(11))).append("',");
				sql.append("'").append(Tools.nullToEmptyString(temp.get(12))).append("',");
				sql.append("'").append(Tools.nullToEmptyString(temp.get(13))).append("',");
				sql.append("'").append(Tools.nullToEmptyString(temp.get(14))).append("',");
				sql.append("'',");
				sql.append("''");
				sql.append(")");
				stm.executeUpdate(sql.toString());   
			}
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
