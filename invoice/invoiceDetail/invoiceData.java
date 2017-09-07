package invoice.invoiceDetail;

import java.util.ArrayList;

public class invoiceData {

	private String invoiceType;
	private String invoiceNo;
	private String revised;
	private String customerID;
	private String customerName;
	private String customerType;
	private String attn;
	private String address;
	private String taxID;
	private String vat;
	private String totalAll;
//	private String taxType;
	private String tel;
	private String email;
	private String issueDate;
	private String dueDate;
	private String payment;
	private String dueDay;
	private String subject;
	private String ref;
	private String createUserID;
	private String createName;
	private String updateUser;
	private ArrayList<rowInvoice> listData;
	public invoiceData(){
		invoiceType = "";
		invoiceNo= "";
		revised= "";
		customerID= "";
		customerName= "";
		attn= "";
		address= "";
		taxID= "";
		vat = "0.00";
		totalAll="0.00";
//		taxType= "";
		tel= "";
		email= "";
		issueDate= "";
		dueDate= "";
		payment= "N";
		createUserID= "";
		createName = "";
		updateUser= "";
		ref = "";
		subject = "";
		listData = new ArrayList<rowInvoice>();
		dueDay = "0";
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getRevised() {
		return revised;
	}
	public void setRevised(String revisied) {
		this.revised = revisied;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerName;
	}
	public String getAttn() {
		return attn;
	}
	public void setAttn(String attn) {
		this.attn = attn;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String addtrss) {
		this.address = addtrss;
	}
//	public String getTaxType() {
//		return taxType;
//	}
//	public void setTaxType(String taxType) {
//		this.taxType = taxType;
//	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getCreateUserID() {
		return createUserID;
	}
	public void setCreateUserID(String createUser) {
		this.createUserID = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public ArrayList<rowInvoice> getListData() {
		return listData;
	}
	public void setListData(ArrayList<rowInvoice> listData) {
		this.listData = listData;
	}
	public int getSize(){
		return this.listData.size();
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = String.format("%.02f",Float.parseFloat(vat));
	}
	public String getTotalAll() {
		return totalAll;
	}
	public void setTotalAll(String totalAll) {
		this.totalAll = String.format("%.02f",Float.parseFloat(totalAll));
	}
	public String getTaxID() {
		return taxID;
	}
	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}
	public String getTotalNoVat(){
		float total = Float.parseFloat(this.totalAll);
		float vat = Float.parseFloat(this.vat);
		String noVat = String.format("%.02f",total-vat);
		return noVat;
	}
	public String getFullInvoice(){
		StringBuilder sb = new StringBuilder();
		sb.append(invoiceType);
		sb.append(invoiceNo);
		if(!revised.equals("0")){
			sb.append("(REV"+revised+")");
		}
		return sb.toString();
	}
	public String getDueDay() {
		return dueDay;
	}
	public void setDueDay(String dueDay) {
		this.dueDay = dueDay;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
}
