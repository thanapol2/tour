package tour.tourListSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;










import tour.tourListDetail.listDetailEdit;
import tour.tourListDetail.listDetailInsert;

import com.base.Tools;
import com.data.memberData;
import com.table.rowMember;
import com.table.tableTour;
import com.tourSearch.tourSearchView;

import company.companyDetail.companyDetailEdit;
import company.companyDetail.companyDetailInsert;

public class listSearch {
	private listSearchView view;
//	private listSearchDAO dao;
	private tableTour data;
	private ActionListener 	newTourAction;
	private ActionListener 	newComAction;

	private listDetailEdit editTourPack;
	private listDetailInsert newTourPack;
	private companyDetailInsert newComPack;
	private companyDetailEdit editComPack;
	
	public listSearch(){
		this.view = new listSearchView();
//		view.setVisible(false);
//		this.dao = new listSearchDAO();	
		this.data = new tableTour();
		addAction();
	}
	
	public void control(){
		view.setVisible(true);
	}
	
	public boolean setVisibleOn(){
		view.setVisible(true);
		return true;
	}
	public boolean setVisibleOff(){
		view.setVisible(false);
		return false;
	}
	
	private void addAction(){
		
		newTourAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Tools.isEmpty(newTourPack)){
					newTourPack = new listDetailInsert();
				}
				newTourPack.control();
			}
		};
		view.getBtnNewTour().addActionListener(newTourAction);	
		
		newComAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Tools.isEmpty(newComPack)){
					newComPack = new companyDetailInsert();
				}
				newComPack.control();
			}
		};
		view.getBtnNewCompany().addActionListener(newComAction);	
		
//		ActionListener cancelAction = new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				view.setVisible(false);
//			}
//		};
//		view.getBtnCancel().addActionListener(cancelAction);	
	}
	
//	private void loadEdit(String memType,String selectID){
//		if(memType.equals("tourmember")){
//			if(Tools.isEmpty(editTourPack)){
//				editTourPack = new listDetailEdit();
//			}
//			editTourPack.control(selectID);
//		}else if (memType.equals("company")){
//			if(Tools.isEmpty(editComPack)){
//				editComPack = new companyDetailEdit();
//			}
//			editComPack.control(selectID);
//		}
//	}
	public listSearchView getView(){
		return this.view;
	}
}
