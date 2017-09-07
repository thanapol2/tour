package tourlist.createTourList;

import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;

import com.base.Tools;
import com.table.rowMember;

public class createTourData {

	private String roomListNo;
	private String groupName;
	private String listName;
	private String hotelName;
	private String destination;
	private String departNo;
	private String departFlight;
	private String departeDate;
	private String departTime;
	private String arrNo;
	private String arrFlight;
	private String arrDate;
	private String arrTime;
	private String passNum;
	private String adlNum;
	private String chdNum;
	private String tourLeadNum;
	private String tourGroupNum;
	private ArrayList<rowMember> list;
	private final int roomIndex = 11; 
	
	public createTourData(ArrayList<rowMember> aList){
		try {
			this.roomListNo = Tools.genRoomListNo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.groupName = "";
		this.listName = "";
		this.hotelName = "";
		this.destination ="";
		this.departNo = "";
		this.departFlight = "";
		this.departeDate = "";
		this.departTime = "";
		this.arrNo = "";
		this.arrFlight = "";
		this.arrDate = "";
		this.arrTime = "";
		this.passNum = "0";
		this.adlNum = "0";
		this.chdNum = "0";
		this.tourLeadNum = "0";
		this.tourGroupNum = "0";
		this.list = aList;
		setNumberPass(aList);
	}
	private void setNumberPass(ArrayList<rowMember> aList){
		int adl = 0;
		int chd = 0;
		int lead = 0;
		int group = 0;
		for(rowMember temp : aList){
			String tTittle = temp.getElementData(1);
			String memType = temp.getElementData(13);
			if(tTittle.equals("เด็กชาย")||tTittle.equals("เด็กหญิง")){
				chd = chd + 1;
			}else if(memType.equals("T/L")){
				lead = lead+1;
			}else if(memType.equals("T/G")){
				group = group +1;
			}
			adl = aList.size()-chd-lead-group;
		}
		this.passNum = Integer.toString(aList.size());
		this.adlNum = Integer.toString(adl);
		this.chdNum = Integer.toString(chd);
		this.tourLeadNum = Integer.toString(lead);
		this.tourGroupNum = Integer.toString(group);
		
	}
	public ArrayList<String> getRowData(int index){
		ArrayList<String> result = new ArrayList<String>();
		if((index>=0)&&(list.size()>0)){
			rowMember temp = list.get(index);
			for(int i = 0; i<temp.getStringData().size();i++){
				result.add(temp.getStringData().get(i));
			}
		}
		return result;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getDepartNo() {
		return departNo;
	}
	public void setDepartNo(String departName) {
		this.departNo = departName;
	}
	public String getDepartFlight() {
		return departFlight;
	}
	public void setDepartFlight(String departFlight) {
		this.departFlight = departFlight;
	}
	public String getDeparteDate() {
		return departeDate;
	}
	public void setDeparteDate(String departeDate) {
		this.departeDate = departeDate;
	}
	public String getArrNo() {
		return arrNo;
	}
	public void setArrNo(String arrName) {
		this.arrNo = arrName;
	}
	public String getDepartTime() {
		return departTime;
	}
	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}
	public String getArrFlight() {
		return arrFlight;
	}
	public void setArrFlight(String arrFlight) {
		this.arrFlight = arrFlight;
	}
	public String getArrDate() {
		return arrDate;
	}
	public void setArrDate(String arrDate) {
		this.arrDate = arrDate;
	}
	public String getArrTime() {
		return arrTime;
	}
	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
	public String getPassNum() {
		return passNum;
	}
//	public void setPassNum(String passNum) {
//		this.passNum = passNum;
//	}
	public String getTourLeadNum() {
		return tourLeadNum;
	}
//	public void setTourLeadNum(String tourLeadNum) {
//		this.tourLeadNum = tourLeadNum;
//	}
	public String getTourGroupNum() {
		return tourGroupNum;
	}
//	public void setTourGroupNum(String tourGroupNum) {
//		this.tourGroupNum = tourGroupNum;
//	}
	public String getDestination() {
		return destination;
	}
	public String getCHDNum(){
		return chdNum;
	}
	public String getADLNum(){
		return adlNum;
	}
//	public void setCHDNum(String num){
//		this.chdNum = num;
//	}
//	public void setADLNum(String num){
//		this.adlNum = num;
//	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getSize(){
		return list.size();
		
	}
	public String getRoomListNo() {
		return roomListNo;
	}
	public void setRoomListNo(String roomListNo) {
		this.roomListNo = roomListNo;
	}
	public ArrayList<String> getRoomType (){
		ArrayList<String> result = new ArrayList<String>();
		for(rowMember row : list){
			String temp = row.getElementData(roomIndex);
			temp = Normalizer.normalize(temp, Normalizer.Form.NFD);
			result.add(temp.replaceAll("[^a-zA-Z/]", ""));
		}
		return result;
	}
	public String getGenTextRoom(){
		StringBuilder result = new StringBuilder();
		ArrayList<String> roomType = getRoomType();
		ArrayList<Integer> roomNum = getRoomNum();
		ArrayList<String> uniType = new ArrayList<String> ();
		ArrayList<Integer> roomCount = new ArrayList<Integer> ();
		if(roomType.size()>0){
			for(int i=0;i<roomType.size();i++){
				int eqIndex = uniType.indexOf(roomType.get(i));
				if(eqIndex < 0){
					uniType.add(roomType.get(i));
					roomCount.add(roomNum.get(i));
				}else{
					if(roomNum.get(eqIndex)<roomNum.get(i)){
						roomCount.set(eqIndex, roomNum.get(i));
					}
				}
			}
		}
		
		for(int i=0;i<uniType.size();i++){
			result.append(roomCount.get(i)).append(" ");
			result.append(uniType.get(i)).append(" ");
			if(i==uniType.size()-1){
				result.append("= ");
				int sum = 0;
				for(int temp : roomCount){
					sum = sum + temp;
				}
				result.append(sum);
				result.append(" Room");
			}else{
				result.append("+ ");
			}
		}
		return result.toString();
	}
	public ArrayList<Integer> getRoomNum (){
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(rowMember row : list){
			String temp = row.getElementData(roomIndex);
			temp = Normalizer.normalize(temp, Normalizer.Form.NFD);
			temp = temp.replaceAll("[^0-9]", "");
			if(temp.equals("")){
				result.add(0);
			}else{
				result.add(Integer.parseInt(temp));
			}	
		}
		return result;
	}
	
	public boolean checkData(){
		boolean result = false;
		if(!groupName.equals("")&&(!listName.equals(""))){
			if((getSize()>0)){
				result = true;
			}
		}
		return result;
	}
	
}
