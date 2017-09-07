package main.export;

public class exportData {
	private String nameSurname;
	private String address;
	private String tel;
	public exportData(){
		this.nameSurname = "";
		this.address = "";
		this.tel = "";
	}
	public exportData(String aName,String aAddress, String aTel){
		this.nameSurname = aName;
		this.address = aAddress;
		this.tel = aTel;
	}
	public String getNameSurname() {
		return nameSurname;
	}
	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}
