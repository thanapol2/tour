package invoice.search;

import com.base.Tools;

public class searchData {
	private String name;
	private String startDate;
	private String endDate;
	private String invoiceNo;
	private String createUser;
	private Boolean paymanet;
	public searchData(){
		this.name = "";
		this.startDate = "";
		this.endDate = "";
		this.invoiceNo = "";
		this.paymanet = false;
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
	public Boolean getPaymanet() {
		return paymanet;
	}
	public void setPaymanet(Boolean paymanet) {
		this.paymanet = paymanet;
	}
	public boolean checkStartEnd(){
		boolean isVaild = true;
		if(Tools.isEmpty(startDate)&&Tools.isNotEmpty(endDate)){
			isVaild = false;
		}
		return isVaild;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
}
