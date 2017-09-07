package invoice.invoiceDetail;

import java.util.ArrayList;

import com.base.Tools;

public class rowInvoice {

//	public final String dd = "ขขข"; 
	private String lineNo;
	private String serviceName;
	private String serviceType;
	private String qtyAd;
//	private String qtyChi;
	private String price;
	private String total;
	private ArrayList<String> data;
	
	public rowInvoice(int aLineNo,String aServiceName, String aServiceType,String aQtyAd,String aPrice,String aTotal){
		// index 0 == TourID
		data = new ArrayList<String>();
		lineNo = Integer.toString(aLineNo);
		serviceName = aServiceName;
		serviceType = aServiceType;
		qtyAd = aQtyAd;
		price = aPrice;
		total = aTotal;
		setAllData();
	}
	public rowInvoice(String aServiceName, String aServiceType,String aQtyAd,String aPrice,String aTotal){
		// index 0 == TourID
		data = new ArrayList<String>();
		lineNo = "";
		serviceName = aServiceName;
		serviceType = aServiceType;
		qtyAd = aQtyAd;
		price = aPrice;
		total = aTotal;
		setAllData();
	}
	public rowInvoice(int aLineNo,rowInvoice Data){
		lineNo = Integer.toString(aLineNo);
		data = Data.getStringData();
		serviceName = data.get(0);
		serviceType = data.get(1);
		qtyAd = data.get(2);
		price = data.get(3);
		total = data.get(4);
	}
	
	public rowInvoice(String type){
		serviceName = "";
		if(type.equals("RT")){
			serviceType = "Air Ticket";
		}else if(type.equals("RTS")){
			serviceType = "Bus";
		}
		qtyAd = "0";
//		qtyChi = "0";
		price = "0";
		total = "0";
		data = new ArrayList<String>();
		setAllData();
	}
	public rowInvoice(ArrayList<String> data){
		// index 0 == TourID
		this.data = data;
		serviceName = data.get(0);
		serviceType = data.get(1);
		qtyAd = data.get(2);
		price = data.get(3);
		total = data.get(4);
	}
	public rowInvoice() {
		// TODO Auto-generated constructor stub
	}
	private void setAllData(){
		this.data.add(serviceName);
		this.data.add(serviceType);
		this.data.add(qtyAd);
		this.data.add(price);
		this.data.add(total);
	}
	public ArrayList<String> getStringData() {
		return this.data;
	}
	public String getElementData(int index){
		return this.data.get(index);
	}
	public void setElementData(String data,int index){
		this.data.set(index, data);
		serviceName = this.data.get(0);
		serviceType = this.data.get(1);
		qtyAd = this.data.get(2);
		price = this.data.get(3);
		total = this.data.get(4);
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	
	
}
