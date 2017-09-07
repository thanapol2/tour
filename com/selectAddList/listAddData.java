package com.selectAddList;



import java.util.ArrayList;

import com.data.memberData;
import com.table.rowMember;

public class listAddData implements Cloneable{



	private ArrayList<rowMember> listData;
	private ArrayList<String>  listMemID;
	public listAddData(){
		this.listData = new ArrayList<rowMember>();
		this.listMemID = new ArrayList<String>();
	}
	
	public listAddData(listAddData temp){
		this.listData = new ArrayList<rowMember>(temp.getListrowMember());
		this.listMemID = new ArrayList<String>(temp.getListTourID());
	}
	
	public boolean deleteSelectIndex(int index){
		boolean result = false;
		if((index>=0)&&(listData.size()>0)){
			this.listData.remove(index);
			this.listMemID.remove(index);
			result = true;
		}
		return result;
	}
	public boolean addrowMember(rowMember aData){
		boolean result = false;
		if(listMemID.size()>0){
			if(!listMemID.contains(aData.getMemID())){
				this.listData.add(aData);
				this.listMemID.add(aData.getMemID());
				result = true;
			}
		}else{
			this.listData.add(aData);
			this.listMemID.add(aData.getMemID());
			result = true;
		}
		return result;
	}
	public rowMember getSelectrowMember(int index){
		rowMember result = null;
		if((index>=0)&&(listData.size()>0)){
			result = this.listData.get(index);
		}
		return result;
	}
	public String getSelectTourID(int index){
		String result = null;
		if((index>=0)&&(listMemID.size()>0)){
			result = listMemID.get(index);
		}
		return result;
	}
	public String getSelectFullName(int index){
		String result = null;
		if((index>=0)&&(listData.size()>0)){
			result = listData.get(index).getFullName();
		}
		return result;
	}
	public ArrayList<String> getListTourID(){
		return listMemID;
	}
	
	public ArrayList<String> getListFullName(){
		ArrayList<String> result = new ArrayList<String>();
		if(this.listData.size()>0){
			for(rowMember temp : listData){
				result.add(temp.getFullName());
			}
		}
		return result;
	}
	public boolean clearData(){
		boolean result = false;
		this.listData = new ArrayList<rowMember>();
		this.listMemID = new ArrayList<String>();
		result = true;
		return result;
	}
    public listAddData clone() throws CloneNotSupportedException {
        return (listAddData) super.clone();
    }
    
    public ArrayList<rowMember> getListrowMember(){
    	return listData;
    }
}
