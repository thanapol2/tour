/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tour.tourListDetail;

import com.base.BaseDAO;
import com.base.Tools;
import com.eatType.eatData;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author Tong
 */
public class listDetailDAO extends BaseDAO {

	
	private listDetailFTP ftp = new listDetailFTP();
	
	public boolean canInsert(listDetailData data)throws SQLException{
		boolean isOkay = false;
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) numb	");
		sql.append("FROM ").append(Tools.getPREFIX()).append("tour_master		");
		sql.append("Where THAI_TITLE = 		");
		sql.append("'").append(Tools.nullToEmptyString(data.gettTittle())).append("' and");
		sql.append("	  THAI_NAME = 		");
		sql.append("'").append(Tools.nullToEmptyString(data.gettFirst())).append("' and");
		sql.append("	  THAI_SURNAME = 	");
		sql.append("'").append(Tools.nullToEmptyString(data.gettSurName())).append("'");
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
	
	public boolean canInsert(ArrayList<String> thaiName)throws SQLException{
		boolean isOkay = false;
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) numb	");
		sql.append("FROM ").append(Tools.getPREFIX()).append("tour_master		");
		sql.append("Where THAI_TITLE = 		");
		sql.append("'").append(Tools.nullToEmptyString(thaiName.get(0))).append("' and");
		sql.append("	  THAI_NAME = 		");
		sql.append("'").append(Tools.nullToEmptyString(thaiName.get(1))).append("' and");
		sql.append("	  THAI_SURNAME = 	");
		sql.append("'").append(Tools.nullToEmptyString(thaiName.get(2))).append("'");
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

	public boolean canEdit(listDetailData data)throws SQLException{
		boolean isOkay = false;
		Connection con = this.getConnection();
		Statement stm = con.createStatement();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) numb	");
		sql.append("FROM ").append(Tools.getPREFIX()).append("tour_master		");
		sql.append("Where THAI_TITLE = 		");
		sql.append("'").append(Tools.nullToEmptyString(data.gettTittle())).append("' and");
		sql.append("	  THAI_NAME = 		");
		sql.append("'").append(Tools.nullToEmptyString(data.gettFirst())).append("' and");
		sql.append("	  THAI_SURNAME = 	");
		sql.append("'").append(Tools.nullToEmptyString(data.gettSurName())).append("' and");
		sql.append("	  TOUR_ID != 	");
		sql.append("'").append(Tools.nullToEmptyString(data.getTourID())).append("'");
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
	
    public boolean newTourList(listDetailData data,File file) throws SQLException{
    	boolean isOkay = false;
    	boolean isUpload = true;
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		if(data.getPathPic() != null){
			isUpload = ftp.uploadImage(file, data.getPathPic());
		}
		try {
			if(isUpload){
				StringBuffer sql = new StringBuffer();
				
				sql.append("INSERT INTO ").append(Tools.getPREFIX()).append("TOUR_MASTER 	");
				sql.append("		(THAI_TITLE,		");
				sql.append("		THAI_NAME,			");
				sql.append("		THAI_SURNAME,		");
				sql.append("		ENG_TITLE,			");
				sql.append("		ENG_NAME,			");
				sql.append("		ENG_SURNAME,		");
				sql.append("		SEX,				");
				sql.append("		PERSON_ID,			");
				sql.append("		PASSPORT_ID,		");
				sql.append("		EMAIL,				");
				sql.append("		COUNTRY,			");
				sql.append("		NATIONALITY,		");
				sql.append("		BIRTH_DAY,			");
				sql.append("		ISSUE_DATE,			");
				sql.append("		EXPIRE_DATE,		");
				
				
				sql.append("		DETAIL,				");
				sql.append("		PATH_PIC,			");
//	 			add 4 column
				sql.append("		TEL,				");
				sql.append("		ADDRESS,			");
				sql.append("		POST_NO,			");
				sql.append("		PROVINCE			");
				sql.append("		)					");
				sql.append("		VALUES (			");
				sql.append("'").append(Tools.nullToEmptyString(data.gettTittle())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.gettFirst())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.gettSurName())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.geteTitle())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.geteFirst())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.geteSurName())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.getSex())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.getPersonID())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.getPassportID())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.getemail())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.getCountry())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.getNationality())).append("',");
				sql.append(Tools.genToDateSQL(data.getBirthDay())).append(",");
				sql.append(Tools.genToDateSQL(data.getIssueDate())).append(",");
				sql.append(Tools.genToDateSQL(data.getExpireDate())).append(",");
				
				
				sql.append("'").append(Tools.nullToEmptyString(data.getDetail())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.getPathPic())).append("',");
				
//				add 4 column	
				sql.append("'").append(Tools.nullToEmptyString(data.getTelNo())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.getAddress())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.getPostNo())).append("',");
				sql.append("'").append(Tools.nullToEmptyString(data.getProvince())).append("'");
				sql.append(")");	
			    stm.executeUpdate(sql.toString());
			   
			    eatData temp = data.getEat();
			    if(temp.isCheck()){
				    sql = new StringBuffer();
				    sql.append("INSERT INTO ").append(Tools.getPREFIX()).append("EAT	( TOUR_ID						");
				    sql.append("	,NO_PIG										");
				    sql.append("	,NO_MEAT									");
				    sql.append("	,NO_CHICKEN									");
				    sql.append("	,HALAL										");
				    sql.append("	,MANGSA										");
				    sql.append("	,VEGETARIAN									");
				    sql.append("	,ISLAM										");
				    sql.append("	,NO_SEAFOOD									");
				    sql.append("	,NO_SHRIMP									");
				    sql.append("	,NO_FISH									");
				    sql.append(") (SELECT tour_id								");
				    sql.append("	,'").append(temp.getNoPig()).append("'		");
				    sql.append("	,'").append(temp.getNoMeat()).append("'		");
				    sql.append("	,'").append(temp.getNoChic()).append("'		");
				    sql.append("	,'").append(temp.getHalan()).append("'		");
				    sql.append("	,'").append(temp.getMangsa()).append("'		");
				    sql.append("	,'").append(temp.getVeg()).append("'		");
				    sql.append("	,'").append(temp.getIslam()).append("'		");
				    sql.append("	,'").append(temp.getNoSeafood()).append("'	");
				    sql.append("	,'").append(temp.getNoShrimp()).append("'	");
				    sql.append("	,'").append(temp.getNoFish()).append("'		");
				    sql.append("	FROM ").append(Tools.getPREFIX()).append("tour_master							");
				    sql.append("	WHERE THAI_NAME||' '||THAI_SURNAME	=		");
				    sql.append("	'").append(data.gettFirst()).append(" ");
				    sql.append(data.gettSurName()).append("')					");
				    System.out.println(sql.toString());
				    stm.executeUpdate(sql.toString());

			    }
			    con.commit();
	            isOkay = true;   
			}
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
    
    public listDetailData searchTourID(String tourID) throws SQLException{
		listDetailData data = new listDetailData();
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
		sql.append("		, SEX 											");
		sql.append("		, PERSON_ID 									");
		sql.append("		, PASSPORT_ID 									");
		sql.append("		, EMAIL 										");
		sql.append("		, COUNTRY 										");
		sql.append("		, NATIONALITY 									");
		sql.append("		,to_char(BIRTH_DAY,'DD/MM/YYYY') BIRTH_DAY		");
		sql.append("		,to_char(ISSUE_DATE,'DD/MM/YYYY') ISSUE_DATE	");
		sql.append("		,to_char(EXPIRE_DATE,'DD/MM/YYYY') EXPIRE_DATE	");
		sql.append("		, DETAIL 										");
		sql.append("		, PATH_PIC 										");
		sql.append("		, ADDRESS 										");
		sql.append("		, PROVINCE 										");
		sql.append("		, POST_NO 										");
		sql.append("		, TEL											");
//		sql.append("		, EAT											");
		sql.append("from ").append(Tools.getPREFIX()).append("tour_Master										");
		sql.append("where tour_id = 										");	
		sql.append("'").append(tourID).append("'");
		ResultSet rs = stm.executeQuery(sql.toString());
		// set data information to model class form
		while (rs.next()) {
			data.setTourID(rs.getString("TOUR_ID"));
			data.settTittle(rs.getString("THAI_TITLE"));
			data.settFirst(rs.getString("THAI_NAME"));
			data.settSurName(rs.getString("THAI_SURNAME"));
			data.seteTitle(rs.getString("ENG_TITLE"));
			data.seteFirst(rs.getString("ENG_NAME"));
			data.seteSurName(rs.getString("ENG_SURNAME"));
			data.setSex(rs.getString("SEX"));
			data.setPersonID(rs.getString("PERSON_ID"));
			data.setPassportID(rs.getString("PASSPORT_ID"));
			data.setemail(rs.getString("EMAIL"));
			data.setCountry(rs.getString("COUNTRY"));
			data.setNationality(rs.getString("NATIONALITY"));		
			data.setBirthDay(rs.getString("BIRTH_DAY"));
			data.setIssueDate(rs.getString("ISSUE_DATE"));
			data.setExpireDate(rs.getString("EXPIRE_DATE"));
			data.setDetail(rs.getString("DETAIL"));
			data.setPathPic(rs.getString("PATH_PIC"));
			data.setAddress(rs.getString("ADDRESS"));
			data.setProvince(rs.getString("PROVINCE"));
			data.setPostNo(rs.getString("POST_NO"));
			data.setTelNo(rs.getString("TEL"));
//			data.setEat(rs.getString("EAT"));
		}
		
		sql = new StringBuffer();
		sql.append("select TOUR_ID										");
		sql.append("		,NO_PIG 									");
		sql.append("		,NO_MEAT									");
		sql.append("		,NO_CHICKEN									");
		sql.append("		,HALAL										");
		sql.append("		,MANGSA 									");
		sql.append("		,VEGETARIAN									");
		sql.append("		,ISLAM										");
		sql.append("		,NO_SEAFOOD									");
		sql.append("		,NO_SHRIMP									");
		sql.append("		,NO_FISH									");
		sql.append("from ").append(Tools.getPREFIX()).append("EAT											");
		sql.append("where tour_id = 									");	
		sql.append("'").append(tourID).append("'");
		rs = stm.executeQuery(sql.toString());
		// set data information to model class form
		while (rs.next()) {
			eatData temp = new eatData();
			temp.setNoPig(rs.getString("NO_PIG"));
			temp.setNoMeat(rs.getString("NO_MEAT"));
			temp.setNoChic(rs.getString("NO_CHICKEN"));
			temp.setHalan(rs.getString("HALAL"));
			temp.setMangsa(rs.getString("MANGSA"));
			temp.setVeg(rs.getString("VEGETARIAN"));
			temp.setIslam(rs.getString("ISLAM"));
			temp.setNoSeafood(rs.getString("NO_SEAFOOD"));
			temp.setNoShrimp(rs.getString("NO_SHRIMP"));
			temp.setNoFish(rs.getString("NO_FISH"));
			data.setEat(temp);
		}
		
		rs.close();
		stm.close();
		closeConnection(con);		
		return data;
    }
    
    public boolean updateTourList(listDetailData data,File file) throws SQLException{
		boolean isOkay = true;
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		Statement stm = con.createStatement();
		boolean isUpload = true;
		if(file != null){
			isUpload = ftp.uploadImage(file, data.getPathPic());
		}
		try {
			if(isUpload){
				StringBuffer sql = new StringBuffer();
				
				sql.append("UPDATE   ").append(Tools.getPREFIX()).append("TOUR_MASTER		 	");
				sql.append("SET		THAI_TITLE    =		");
				sql.append("'").append(Tools.nullToEmptyString(data.gettTittle())).append("',");
				sql.append("		THAI_NAME     =		");
				sql.append("'").append(Tools.nullToEmptyString(data.gettFirst())).append("',");
				sql.append("		THAI_SURNAME  =		");
				sql.append("'").append(Tools.nullToEmptyString(data.gettSurName())).append("',");
				sql.append("		ENG_TITLE	  =		");
				sql.append("'").append(Tools.nullToEmptyString(data.geteTitle())).append("',");
				sql.append("		ENG_NAME	  =		");
				sql.append("'").append(Tools.nullToEmptyString(data.geteFirst())).append("',");
				sql.append("		ENG_SURNAME	  =		");
				sql.append("'").append(Tools.nullToEmptyString(data.geteSurName())).append("',");
				sql.append("		SEX	 		  =		");
				sql.append("'").append(Tools.nullToEmptyString(data.getSex())).append("',");
				sql.append("		PERSON_ID	  =		");
				sql.append("'").append(Tools.nullToEmptyString(data.getPersonID())).append("',");
				sql.append("		PASSPORT_ID	  =		");
				sql.append("'").append(Tools.nullToEmptyString(data.getPassportID())).append("',");
				sql.append("		EMAIL		  =		");
				sql.append("'").append(Tools.nullToEmptyString(data.getemail())).append("',");
				sql.append("		COUNTRY	  	  =		");
				sql.append("'").append(Tools.nullToEmptyString(data.getCountry())).append("',");
				sql.append("		NATIONALITY	  =		");
				sql.append("'").append(Tools.nullToEmptyString(data.getNationality())).append("',");
				sql.append("		BIRTH_DAY	  =		");
				sql.append(Tools.genToDateSQL(data.getBirthDay())).append(",");
				sql.append("		ISSUE_DATE	  =		");
				sql.append(Tools.genToDateSQL(data.getIssueDate())).append(",");
				sql.append("		EXPIRE_DATE	  =		");
				sql.append(Tools.genToDateSQL(data.getExpireDate())).append(",");
				sql.append("		DETAIL	  	  =		");
				sql.append("'").append(Tools.nullToEmptyString(data.getDetail())).append("',");
				sql.append("		PATH_PIC	  =		");
				sql.append("'").append(Tools.nullToEmptyString(data.getPathPic())).append("',");
				sql.append("		TEL	  		  =		");
				sql.append("'").append(Tools.nullToEmptyString(data.getTelNo())).append("',");
				sql.append("		ADDRESS	  	  =		");
				sql.append("'").append(Tools.nullToEmptyString(data.getAddress())).append("',");
				sql.append("		POST_NO	      =		");
				sql.append("'").append(Tools.nullToEmptyString(data.getPostNo())).append("',");
				sql.append("		PROVINCE	  =		");
				sql.append("'").append(Tools.nullToEmptyString(data.getProvince())).append("' ");
//				sql.append("		EAT			  =		");
//				sql.append("'").append(Tools.nullToEmptyString(data.getEat())).append("'");
				sql.append("WHERE TOUR_ID = 			");	
				sql.append("'").append(data.getTourID()).append("'");
				stm.executeUpdate(sql.toString());	
				
				eatData temp = data.getEat();
				sql = new StringBuffer();
				
				
				sql.append("merge INTO ").append(Tools.getPREFIX()).append("EAT a USING						");
				sql.append("(SELECT count(tour_id) cid FROM ").append(Tools.getPREFIX()).append("eat					");
				sql.append(" where TOUR_ID = 							");
				sql.append("'").append(data.getTourID()).append("')		");
				sql.append(" b ON (b.cid > 0)							");
				sql.append("WHEN MATCHED THEN							");				
				sql.append("UPDATE  		 							");
				sql.append("SET		 NO_PIG =							");
				sql.append("'").append(temp.getNoPig()).append("',		");
				sql.append("		NO_MEAT	=							");
				sql.append("'").append(temp.getNoMeat()).append("',		");
				sql.append("		NO_CHICKEN	=						");
				sql.append("'").append(temp.getNoChic()).append("',		");
				sql.append("		HALAL	=							");
				sql.append("'").append(temp.getHalan()).append("',		");
				sql.append("		MANGSA 	=							");
				sql.append("'").append(temp.getMangsa()).append("',		");
				sql.append("		VEGETARIAN	=						");
				sql.append("'").append(temp.getVeg()).append("',		");
				sql.append("		ISLAM		=						");
				sql.append("'").append(temp.getIslam()).append("',		");
				sql.append("		NO_SEAFOOD	=						");
				sql.append("'").append(temp.getNoSeafood()).append("',	");
				sql.append("		NO_SHRIMP	=						");
				sql.append("'").append(temp.getNoShrimp()).append("',	");
				sql.append("		NO_FISH		=						");
				sql.append("'").append(temp.getNoFish()).append("' 		");
				sql.append(" where TOUR_ID = 							");
				sql.append("'").append(data.getTourID()).append("'		");
				sql.append("WHEN NOT MATCHED THEN						");
			    sql.append("INSERT ( TOUR_ID						");
			    sql.append("	,NO_PIG										");
			    sql.append("	,NO_MEAT									");
			    sql.append("	,NO_CHICKEN									");
			    sql.append("	,HALAL										");
			    sql.append("	,MANGSA										");
			    sql.append("	,VEGETARIAN									");
			    sql.append("	,ISLAM										");
			    sql.append("	,NO_SEAFOOD									");
			    sql.append("	,NO_SHRIMP									");
			    sql.append("	,NO_FISH									");
			    sql.append(") values ( '").append(data.getTourID()).append("'");
			    sql.append("	,'").append(temp.getNoPig()).append("'		");
			    sql.append("	,'").append(temp.getNoMeat()).append("'		");
			    sql.append("	,'").append(temp.getNoChic()).append("'		");
			    sql.append("	,'").append(temp.getHalan()).append("'		");
			    sql.append("	,'").append(temp.getMangsa()).append("'		");
			    sql.append("	,'").append(temp.getVeg()).append("'		");
			    sql.append("	,'").append(temp.getIslam()).append("'		");
			    sql.append("	,'").append(temp.getNoSeafood()).append("'	");
			    sql.append("	,'").append(temp.getNoShrimp()).append("'	");
			    sql.append("	,'").append(temp.getNoFish()).append("'		");
			    sql.append(")												");
				
				System.out.println(sql.toString());
				stm.executeUpdate(sql.toString());	
				con.commit();
				isOkay = true;
            }    
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
    
    public boolean deleteTourList(String tourId) throws SQLException{
 		boolean isOkay = true;
 		Connection con = this.getConnection();
 		con.setAutoCommit(false);
 		Statement stm = con.createStatement();
 		try {

			StringBuffer sql = new StringBuffer();
			
			sql.append("DELETE  FROM ").append(Tools.getPREFIX()).append("TOUR_MASTER		");
			sql.append("WHERE	 TOUR_ID    =		");
			sql.append("'").append(tourId).append("'");
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
