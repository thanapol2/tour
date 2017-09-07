/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.companyDetail;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.base.Tools;
import com.data.memberData;

import company.meetingLog.meetingRow;
import company.meetingLog.tableMeeting;

/**
 *
 * @author Tong
 */
public class companyData extends memberData{
//	private String tourID;
//    private String tTittle;
//    private String tFirst;
//    private String tSurName;
    private String eName;
    private String address;
    private String postNum;
    private String province;
    private String taxID;
    private String taxType;
    private String remark;
    private String email;
    private String telNo;
    private String comType;
    private String contactName;
    private String contactDate;
    private String travelDate;
    private ArrayList<meetingRow>  tableLog;

	public companyData(){
		memID 		= null;
		tTittle 	= null;
		tFirst 		= null;
		tSurname 	= null;
	    eName		= null;
	    address		= null;
	    postNum		= null;
	    province	= null;
	    taxID	 	= null;
	    taxType 	= null;
	    remark		= null;
	    email		= null;
	    telNo 		= null;
	    comType		= null;
	    tableLog = new ArrayList<meetingRow>();
	}
	
	
	public String getComID() {
		return memID;
	}
	public void setComID(String comID) {
		this.memID = comID;
	}
	

	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}


	public String geteName() {
		return eName;
	}


	public void seteName(String eName) {
		this.eName = eName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPostNum() {
		return postNum;
	}


	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String Province) {
		this.province = Province;
	}


	public String getTaxID() {
		return taxID;
	}


	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getTelNo() {
		return telNo;
	}


	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String gettName() {
		return tFirst;
	}

	public void settName(String tName) {
		this.tFirst = tName;
	}


	public String getTaxType() {
		return taxType;
	}


	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}


	public String getComType() {
		return comType;
	}


	public void setComType(String comType) {
		this.comType = comType;
	}


	public ArrayList<meetingRow>  getTableLog() {
		return tableLog;
	}


	public void setTableLog(ArrayList<meetingRow> tableLog) {
		this.tableLog = tableLog;
	}
}
