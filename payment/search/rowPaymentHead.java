package payment.search;

import java.util.ArrayList;

public class rowPaymentHead {

	private String paymanetType;
	private String paymanetNo;
	private String invoiceNo;
	private String customerName;
	private String reject;
	private String issueDate;
	private String paymentDate;
	private ArrayList<String> data;
	
	public rowPaymentHead(){
		paymanetType = "";
		invoiceNo = "";
		reject = "";
		issueDate = "";
		customerName = "";
		paymanetNo = "";
		paymentDate = "";
		data = new ArrayList<String>();
		setAllData();
	}
	public rowPaymentHead(ArrayList<String> data){
		this.data = new ArrayList<String>();
		this.data = data;
		paymanetType = data.get(0);
		paymanetNo = data.get(1);
		invoiceNo = data.get(2);
		reject = this.data.get(3);
		paymentDate = data.get(4);
		customerName = data.get(5);
		issueDate = data.get(6);
	}
	private void setAllData(){
		this.data.add(paymanetType);
		this.data.add(paymanetNo);
		this.data.add(invoiceNo);
		this.data.add(paymentDate);
		this.data.add(customerName);	
		this.data.add(issueDate);
	}
	public ArrayList<String> getStringData() {
		return this.data;
	}
	public String getElementData(int index){
		return this.data.get(index);
	}
	public void setElementData(String data,int index){
		this.data.set(index, data);
		paymanetType = this.data.get(0);
		paymanetNo = this.data.get(1);
		invoiceNo = this.data.get(2);
		reject = this.data.get(3);
		paymentDate = this.data.get(4);
		customerName = this.data.get(5);
		issueDate = this.data.get(6);
	}
	public String getReject() {
		return reject;
	}
	public void setReject(String reject) {
		this.reject = reject;
	}
}
