/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.base.Tools;
import com.data.memberData;
import com.eatType.eatData;

/**
 *
 * @author Tong
 */
public class regData{
	private String saleID;
    private String tTittle;
    private String tFirst;
    private String tSurName;
    private String eTitle;
    private String eFirst;
    private String eSurName;
    private String user;
    private String pass;
    private String role;
    
	public regData(){
		saleID		= "";
		tTittle 	= "";
		tFirst 		= "";
		tSurName	= "";
	    eTitle		= "";
	    eFirst 		= "";
	    eSurName 	= "";
	    user		= "";
	    pass		= "";
	    role		= "";
	}
	
	public String getSaleID() {
		return saleID;
	}
	public void setSaleID(String tourID) {
		this.saleID = tourID;
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
	public String gettTittle() {
		return tTittle;
	}
	public void settTittle(String tTittle) {
		this.tTittle = tTittle;
	}
	public String gettFirst() {
		return tFirst;
	}
	public void settFirst(String tFirst) {
		this.tFirst = tFirst;
	}
	public String gettSurName() {
		return tSurName;
	}
	public void settSurName(String tSurName) {
		this.tSurName = tSurName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
