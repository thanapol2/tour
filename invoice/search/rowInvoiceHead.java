package invoice.search;

import java.util.ArrayList;

public class rowInvoiceHead {

	private String invoiceNo;
//	private String revisied;
	private String customerName;
	private String paymanetStatus;
	private String issueDate;
	private String createUser;
	private ArrayList<String> data;
	
	public rowInvoiceHead(){
		invoiceNo = "";
//		revisied = "";
		issueDate = "";
		customerName = "";
		paymanetStatus = "";
		createUser = "";
		data = new ArrayList<String>();
		setAllData();
	}
	public rowInvoiceHead(ArrayList<String> data){
		this.data = new ArrayList<String>();
		this.data = data;
		invoiceNo = data.get(0);
//		revisied = data.get(1);
		issueDate = data.get(1);
		customerName = data.get(2);
		paymanetStatus = data.get(3);
		createUser = data.get(4);
	}
	private void setAllData(){
		this.data.add(invoiceNo);
//		this.data.add(revisied);
		this.data.add(issueDate);
		this.data.add(customerName);
		this.data.add(paymanetStatus);
		this.data.add(createUser);
	}
	public ArrayList<String> getStringData() {
		return this.data;
	}
	public String getElementData(int index){
		return this.data.get(index);
	}
	public void setElementData(String data,int index){
		this.data.set(index, data);
		invoiceNo = this.data.get(0);
//		revisied = this.data.get(1);
		customerName = this.data.get(1);
		paymanetStatus = this.data.get(2);
		issueDate = this.data.get(3);
		createUser = this.data.get(4);
	}
}
