package company.meetingLog;

public class meetingData {

	protected String seqNo;
	protected String thaiName;
	protected String companyID;
	protected String contactDay;
	protected String detail;
	protected String contactTo;
	protected String create;
	protected String phone;
	protected String email;
	protected String callDay;
	
	public meetingData(){
		this.seqNo = "";
		this.thaiName = "";
		this.companyID = "";
		this.contactDay = "";
		this.detail = "";
		this.contactTo = "";
		this.create = "";
		this.phone = "";
		this.email = "";
		this.callDay = "";
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getContactDay() {
		return contactDay;
	}
	public void setContactDay(String date) {
		this.contactDay = date;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getContactTo() {
		return contactTo;
	}
	public void setContactTo(String contactTo) {
		this.contactTo = contactTo;
	}
	public String getCreate() {
		return create;
	}
	public void setCreate(String create) {
		this.create = create;
	}
	public String getCompanyThaiName() {
		return thaiName;
	}
	public void setCompanyThaiName(String thaiName) {
		this.thaiName = thaiName;
	}
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCallDay() {
		return callDay;
	}
	public void setCallDay(String callDay) {
		this.callDay = callDay;
	}
}
