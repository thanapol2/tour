package com.data;

import java.util.ArrayList;

import com.base.Tools;

public class memberData {

	protected String memID;
	protected String tTittle;
	protected String tFirst;
	protected String tSurname;
	protected String groupType;
	protected ArrayList<String> stringData;


	public String getFullName() {
		String fullName;
		if(Tools.isNotEmpty(tSurname)&&Tools.isNotEmpty(tTittle)){
			fullName = tTittle+" "+tFirst+" "+tSurname;
		}else{
			fullName = tFirst;
		}
		return fullName;
	}
	public String gettTittle() {
		return tTittle;
	}
	public void settTittle(String tTittle) {
		this.tTittle = tTittle;
	}
	public String gettFirst() {
		return tFirst;
	}
	public void settFirst(String tFirst) {
		this.tFirst = tFirst;
	}
	public String gettSurName() {
		return tSurname;
	}
	public void settSurName(String tSurName) {
		this.tSurname = tSurName;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getMemID() {
		return memID;
	}
	public void setMemID(String tourID) {
		this.memID = tourID;
	}
	public ArrayList<String> getStringData() {
		return stringData;
	}
	public String getElementData(int index){
		return stringData.get(index);
	}
	public void setElementData(String data,int index){
		stringData.set(index, data);
	}
}
