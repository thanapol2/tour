package com.selectAddList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.JDialog;

import com.base.Tools;
import com.table.rowMember;


public class listAddSelect {
	private listAddView  view;
	private listAddData 	data;
//	private listSelectData 	beforeData;
	private ArrayList<String> currentData;
	private ActionListener 	addAction;
	private ActionListener 	removeAction;
	private ActionListener 	clearAction;
//	private ActionListener 	submitAction;
//	private ActionListener 	cancelAction;
	public listAddSelect(){
//		parent.setEnabled(false);
		this.view = new listAddView();
		this.data = new listAddData();
		addAction();
	}


	public void control(ArrayList<rowMember> dataTable){
//		view.clearList();
//		this.data = new listSelectData();
		this.currentData = tranfer(dataTable);
		view.setVisible(true);
	}
	
	private void addAction(){
		
		addAction = new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				rowMember select = view.getRowData();
				if((Tools.isNotEmpty(select))){
					String memID = select.getMemID();
					if(!currentData.contains(memID)){
						if(data.addrowMember(select)){
							view.addTourList(select.getFullName());
						}
					}
				}
			}
		};
		view.getAddBtn().addActionListener(addAction);
		
		removeAction = new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int index = view.getSelectIndexTourList();
				if(index>=0){
					if(view.removeList()){
						data.deleteSelectIndex(index);
					}
				}
			}			
		};
		view.getRemoveBtn().addActionListener(removeAction);
		
		clearAction = new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(view.clearList()){
					data.clearData();
				}
			}			
		};
		view.getClearBtn().addActionListener(clearAction);
		
	}

	
	public boolean cancelProcess(){
		boolean result = false;
		view.setVisible(false);
		result = true;
		return result;
	}
	public boolean submitProcess(){
		boolean result = false;
//		beforeData = new listSelectData(data);
		view.setVisible(false);
		result = true;
		return result;
	}
	private listAddData getData(){
		return this.data;
	}
	public boolean isNotVisable(){
		return !view.isVisible();
	}
	public boolean isVisable(){
		return view.isVisible();
	}
	
	public void setSumbitAction(ActionListener action){
		view.getSubmitBtn().addActionListener(action);
	}
	public void setCloseAction(WindowAdapter action){
		view.addWindowListener(action);
	}
	public ArrayList<String> getMemID(){
		ArrayList<String> result = new ArrayList<String>();
		if(data.getListTourID().size()>0){
			result = data.getListTourID();
		}
		return result;
	}
	private ArrayList<String> tranfer(ArrayList<rowMember> dataTable){
		ArrayList<String> result = new ArrayList<String>();
		if(dataTable.size()>0){
			for(rowMember temp : dataTable){
				result.add(temp.getMemID());
			}
		}
		return result;
	}
	public void setVisibleView(boolean isVisible){
		view.setVisible(isVisible);
	}


	public ArrayList<String> getMemName() {
		ArrayList<String> result = new ArrayList<String>();
		if(data.getListFullName().size()>0){
			result = data.getListFullName();
		}
		return result;
	}
}
