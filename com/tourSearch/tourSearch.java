package com.tourSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import tour.tourListDetail.listDetailEdit;

import com.base.Tools;
import com.table.rowMember;
import com.table.tableTour;

import company.companyDetail.companyDetailEdit;


public class tourSearch {
	private tourSearchView view;
	private tourSearchDAO dao;
	private tableTour data;
	private ActionListener 	searchAction;
	private MouseAdapter clickTable;
	private listDetailEdit editTourPack;
	private companyDetailEdit editComPack;
	
	public tourSearch(){
		this.view = new tourSearchView();
		view.setVisible(true);
		this.dao = new tourSearchDAO();	
		this.data = new tableTour();
		addAction();
	}
	
//	public void control(){
//		view.setVisible(true);
//	}
	
	public boolean setVisibleOn(){
		view.setVisible(true);
		return true;
	}
	public boolean setVisibleOff(){
		view.setVisible(false);
		return false;
	}
	
	private void addAction(){
		searchAction = new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String searchSurname = view.getSearchSurname();
				try {
					ArrayList<rowMember> dataList = 
							dao.searchThaiSurname(searchSurname,view.getCheckBox());
					data.setListRowsData(dataList);
					data.fireTableDataChanged();
					view.setTable(data);
					view.getTable().setRowHeight(30);
					view.getTable().getColumnModel().getColumn(0).setPreferredWidth(100);
					view.getTable().getColumnModel().getColumn(1).setPreferredWidth(240);;
					view.getTable().getColumnModel().getColumn(2).setPreferredWidth(100);
					view.getTable().getColumnModel().getColumn(3).setPreferredWidth(240);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "research again");
				}
			}
		};
		view.getSearchButton().addActionListener(searchAction);
		
		clickTable = new MouseAdapter(){
			public void mousePressed(MouseEvent event) {
				if(event.getClickCount()==2){
					int row =	view.getTable().getSelectedRow();
					if(row>=0){
						tableTour data = (tableTour) view.getTable().getModel();
						String selectTourID = data.getRowsData(row).getElementData(0);
						String memType = data.getRowsData(row).getGroupType();
						loadEdit(memType,selectTourID);
					}
				}
			}
		};
		view.getTable().addMouseListener(clickTable);
		
		
	}
	
	private void loadEdit(String memType,String selectID){
		if(memType.equals("tourmember")){
			if(Tools.isEmpty(editTourPack)){
				editTourPack = new listDetailEdit();
			}
			editTourPack.control(selectID);
		}else {
			if(Tools.isEmpty(editComPack)){
				editComPack = new companyDetailEdit();
			}
			editComPack.control(selectID);
		}
	}
	
	public rowMember getSelectedRowData(){
		return view.getRowData();
	}
	public void disableComCase(){
		view.setDisable();
		view.setCheckTour();
	}
	public tourSearchView getView(){
		return this.view;
	}
}
