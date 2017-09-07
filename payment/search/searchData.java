package payment.search;

import com.base.Tools;

public class searchData {
	private String name;
	private String startDate;
	private String endDate;
	private String invoiceNo;
	private String paymanetNo;
	private boolean isReject;
	private boolean isPaymentVat;
	private boolean isPaymentNoVat;
	public searchData(){
		this.name = "";
		this.startDate = "";
		this.endDate = "";
		this.invoiceNo = "";
		this.paymanetNo = "";
		this.isReject = false;
		this.isPaymentVat = false;
		this.isPaymentNoVat = false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String issueDate) {
		this.startDate = Tools.nullToEmptyString(issueDate);
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String issueDate) {
		this.endDate = Tools.nullToEmptyString(issueDate);
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getPayment() {
		return paymanetNo;
	}
	public void setPayment(String paymanet) {
		this.paymanetNo = paymanet;
	}
	public boolean checkStartEnd(){
		boolean isVaild = true;
		if(Tools.isEmpty(startDate)&&Tools.isNotEmpty(endDate)){
			isVaild = false;
		}
		return isVaild;
	}
	public boolean getIsReject() {
		return isReject;
	}
	public void setIsReject(boolean reject) {
		this.isReject = reject;
	}
	public boolean isPaymentVat() {
		return isPaymentVat;
	}
	public void setPaymentVat(boolean isPaymentVat) {
		this.isPaymentVat = isPaymentVat;
	}
	public boolean isPaymentNoVat() {
		return isPaymentNoVat;
	}
	public void setPaymentNoVat(boolean isPaymentNoVat) {
		this.isPaymentNoVat = isPaymentNoVat;
	}
}
