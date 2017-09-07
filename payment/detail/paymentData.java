package payment.detail;

import java.util.ArrayList;

public class paymentData {

	protected String paymentNo;
	protected String paymentType;
	protected String chequeBank;
	protected String chequeNo;
	protected String chequeDate;
	protected String paymentDate;
	protected String detail;
	protected String taxID;
	protected String total;
	protected boolean hasVat;
	protected String vat;
	public paymentData(){
		this.paymentNo = "";
		this.paymentType = "";
		this.chequeBank = "";
		this.chequeNo = "";
		this.chequeDate = "";
		this.detail = "";
		this.taxID = "";
		this.total = "";
		this.hasVat = false;
		this.vat = "0.00";
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getChequeBank() {
		return chequeBank;
	}
	public void setChequeBank(String bank) {
		this.chequeBank = bank;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getChequeDate() {
		return chequeDate;
	}
	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}
	public String getTaxID() {
		return taxID;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public boolean getHasVat() {
		return hasVat;
	}
	public void setHasVat(String hasVat) {
		if(hasVat.equals("Y")){
			this.hasVat = true;
		}else{
			this.hasVat = false;
		}
		
	}
}
