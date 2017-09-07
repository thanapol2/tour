package company.companyDetail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.base.Tools;
import com.table.tableTour;

import company.meetingLog.meeting;
import company.meetingLog.tableMeeting;


public class companyDetailEdit extends companyDetail {
	private ActionListener 	editAction;
	private ActionListener 	disableAction;
	private companyData data;
	private boolean checkDisable;
	private meeting meeting;
	private tableMeeting table;
	
	public companyDetailEdit(){
		this.view = new companyView();
		this.dao = new companyDAO();
		this.meeting = new meeting(this);
		this.table = new tableMeeting();
		view.getDisBtn().setVisible(true);
		view.getSubmitBtn().setEnabled(true);
		addListenerBtn();
		addClickTableAction();
		addDelLogAction();
		clearAction = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (Tools.isEmpty(data.getComID())){
					view.getSubmitBtn().setEnabled(false);
				}else{
					view.setViewText(data);
					view.getSubmitBtn().setEnabled(true);
				}
			}
		};
		view.getClearBtn().addActionListener(clearAction);
		
		editAction = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(checkDisable){
					if(view.checkInput()){
						companyData temp = setCompanyListData(); 
						updateData(temp);
						data = new companyData();
						data = setCompanyListData(); 
					}	
				}else{
					JOptionPane.showMessageDialog(null,"Please Enable Screen First");
				}
		
			}
		};
		view.getSubmitBtn().addActionListener(editAction);
		
		disableAction = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(checkDisable){
					view.setDisableText();
					checkDisable = false;
				}else{
					view.setEnableText();
					checkDisable = true;
				}
			}	
		};
		view.getDisBtn().addActionListener(disableAction);
		addCreateLogAction();
	}
	
	public void control(String companyID){
		view.clear();
		view.setVisible(true);
		view.SetEnableLog(true);
		data = new companyData();
		try {
			data = dao.searchComID(companyID);
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null,"Can't connect Database"
					,"Error", JOptionPane.ERROR_MESSAGE);
		}
		view.setViewText(data);
		table.setListRowsData(data.getTableLog());
		table.fireTableDataChanged();
		view.setTable(table);
		if(view.setDisableText()){
			checkDisable = false;
		}else{
			checkDisable = true;
		}

	}
	
	private void updateData(companyData data){
		try {
			if(dao.canEdit(data.gettName(), data.getComID())){
				if(dao.editCompany(data)){
					JOptionPane.showMessageDialog(null,"<html><center>Insert Complete</center></html>"
							,"",JOptionPane.PLAIN_MESSAGE);
					view.clear();
					view.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null,"Please Insert Again or "
							+ "Check Data before Inserting","Error"
							, JOptionPane.ERROR_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null,"Data is already in database"
						,"Error", JOptionPane.ERROR_MESSAGE);
			}
		}catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Please Re-insert or check data"
					,"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void addDelLogAction(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int row =	view.getTable().getSelectedRow();
				if(row>=0){
					tableMeeting temp = (tableMeeting) view.getTable().getModel();
					String seqNo = temp.getSeqNo(row);
					if(meeting.delete(seqNo)){
						temp.removeRow(row);
						table.setListRowsData(temp.getListRowsData());
						view.setTable(table);
					}
				}else{
					JOptionPane.showMessageDialog(null,"Please select row then click delete button"
							,"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		view.setDelLogAction(action);
	}
	private void addCreateLogAction(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				meeting.control(view.getComID(),view.getThaiName(),true);
				view.setEnabled(false);
			}
		};
		view.setInsertLogAction(action);
	}
	
	private void addClickTableAction(){
		MouseAdapter clickTable = new MouseAdapter(){
			public void mousePressed(MouseEvent event) {
				if(event.getClickCount()==2){
					int row =	view.getTable().getSelectedRow();
					if(row>=0){
						tableMeeting temp = (tableMeeting) view.getTable().getModel();
						String seqNo = temp.getSeqNo(row);
						System.out.println(seqNo);
						meeting.control(view.getComID(),view.getThaiName(), false);
						meeting.setViewData(seqNo);
						view.setEnabled(false);
					}
				}
			}
		};
		view.getTable().addMouseListener(clickTable);
	}

	public JFrame getView() {
		// TODO Auto-generated method stub
		return this.view;
	}
//	public String getComID(){
//		String comID = "";
//		comID = this.data.getComID();
//		return comID;
//	}
}
