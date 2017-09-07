/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tour.tourListDetail;

import java.awt.image.BufferedImage;

import com.base.Tools;
import com.data.memberData;
import com.eatType.eatData;

/**
 *
 * @author Tong
 */
public class listDetailData extends memberData{
//	private String tourID;
//    private String tTittle;
//    private String tFirst;
//    private String tSurName;
    private String eTitle;
    private String eFirst;
    private String eSurName;
    private String personID;
    private String passportID;
    private String email;
    private String country;
    private String nationality;
    private String birthDay;
    private String sex;
    private String issueDate;
    private String expireDate;
    private BufferedImage isImage;
    private String pathPic;
    private String detail;
//    private String eat;
    private String telNo;
    private String address;
    private String postNo;
    private String province;
    private eatData eat;
    
	public listDetailData(){
		memID 		= null;
		tTittle 	= null;
		tFirst 		= null;
		tSurname 	= null;
	    eTitle		= null;
	    eFirst 		= null;
	    eSurName 	= null;
	    personID 	= null;
	    passportID 	= null;
	    email 		= null;
	    country 	= null;
	    nationality = null;
	    birthDay 	= null;
	    sex 		= null;
	    issueDate 	= null;
	    expireDate 	= null;
	    isImage		= null;
	    detail 		= null;
	    setPathPic(null); 
//	    eat			= null;
	    telNo		= null;
	    address		= null;
	    postNo		= null;
	    province	= null;
	    eat			= new eatData();
	}
	
	public String getTourID() {
		return memID;
	}
	public void setTourID(String tourID) {
		this.memID = tourID;
	}
	public String geteTitle() {
		return eTitle;
	}
	public void seteTitle(String eTitle) {
		this.eTitle = eTitle;
	}
	public String geteFirst() {
		return eFirst;
	}
	public void seteFirst(String eName) {
		this.eFirst = eName;
	}
	public String geteSurName() {
		return eSurName;
	}
	public void seteSurName(String eSurName) {
		this.eSurName = eSurName;
	}
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public String getPassportID() {
		return passportID;
	}
	public void setPassportID(String passportID) {
		this.passportID = passportID;
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public BufferedImage getIsImage() {
		return isImage;
	}
	public void setIsImage(BufferedImage isImage) {
		this.isImage = isImage;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String eatDetail) {
		this.detail = eatDetail;
	}

	public String getPathPic() {
		return pathPic;
	}

	public void setPathPic(String pathPic) {
		this.pathPic = pathPic;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public eatData getEat() {
		return eat;
	}

	public void setEat(eatData eat) {
		this.eat = eat;
	}

//	public String getEat() {
//		// TODO Auto-generated method stub
//		return this.eat;
//	}
//	public void	setEat(String eat) {
//		// TODO Auto-generated method stub
//		this.eat = eat;
//	}
}
