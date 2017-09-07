package company.meetingLog;

import java.util.ArrayList;

public class meetingRow extends meetingData {


	private ArrayList<String> stringData;
	public meetingRow(){
		this.companyID = "";
		this.contactDay = "";
		this.detail = "";
		this.contactTo = "";
		this.create = "";
		this.phone = "";
		stringData = new ArrayList<String>();
	}
	
//	"Contact Day","Detail","Contact To","Phone","Create By"
	public meetingRow(String seqNo,String id,String date,String detail,
			String contactTo ,String phone,String create){
		this.seqNo = seqNo;
		this.companyID = id;
		this.contactDay = date;
		this.detail = detail;
		this.contactTo = contactTo;
		this.phone = phone;
		this.create = create;
		stringData = new ArrayList<String>();
		stringData.add(this.contactDay);
		stringData.add(this.detail);
		stringData.add(this.contactTo);
		stringData.add(this.phone);
		stringData.add(this.create);
	}
	public ArrayList<String> getStringData() {
		return stringData;
	}

}
