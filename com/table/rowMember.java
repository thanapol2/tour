package com.table;

import java.util.ArrayList;

import com.data.memberData;

public class rowMember extends memberData{
	
	
	/*
	 *   stringData list
	 *   "No.","Title","Name","Surname","Title","Name","Surname",
						"NO. PP","DOB","DOI","DOE","Room","Air Ticket" 
	 */
	private String eName = "";
	private String picName;
	private String point;
	public rowMember(){
		memID 		= null;
		picName		= null;
		point		= "";
		stringData 	= new ArrayList<String>();
	}

	public rowMember(String aID,String aNameFullName){
	// index 0 == TourID
	
	memID 		= aID;
	tFirst = aNameFullName;
}
	
	public rowMember(ArrayList<String> data,String aPicName){
		// index 0 == TourID
		
		memID 		= data.get(0);
		picName		= aPicName;
		stringData 	= data;
		tTittle     = data.get(1);
	}
	
	
	public void setStringData() {
		this.stringData.add(memID);
		this.stringData.add(tFirst);
		this.stringData.add(groupType);
		this.stringData.add(eName);
		this.stringData.add(point);
	}

	public String getFullName(){
		return this.tFirst;
	}
	public void setFullName(String name){
		this.tFirst = name;
	}
	public boolean getADL(){
		boolean result = true;
		if(tTittle.equals("เด็กหญิง")||tTittle.equals("เด็กชาย")){
			result = false;
		}else{
			result = true;
		}
		return result;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}
}
