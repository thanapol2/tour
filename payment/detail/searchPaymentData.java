package payment.detail;

public class searchPaymentData extends paymentData{

	private String salesID;
	private String salesName;
	private String customerID;
	private String customerName;
	private String address;

	
	public searchPaymentData(){
		super();
		salesID = "";
		salesName = "";
		customerID ="";
		customerName = "";
		address = "";

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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	public String getSalesID() {
		return salesID;
	}
	public void setSalesID(String salesID) {
		this.salesID = salesID;
	}
	public String getSalesName() {
		return salesName;
	}
	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}


}
