package company.meetingLog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.base.ConnectionPool;

import company.companyDetail.companyDetailEdit;


public class meeting {
	private meetingView view;
	private meetingDAO dao;
	private meetingData data;
	private JFrame parent;
	private boolean isNewData;
	private String comID;
	private companyDetailEdit companyDetail;
	public meeting(companyDetailEdit aParent){
//		this.comID = aParent.getComID();
		this.companyDetail = aParent;
		this.parent = aParent.getView();
		this.view = new meetingView(parent);
		this.dao = new meetingDAO();
		this.data = new meetingData();
		this.isNewData = true;
		addSubmitInsertCaseBtnAction();
		
	}
	
	public void addSubmitInsertCaseBtnAction(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setData();
				try {
					if(isNewData){
						if(dao.newMeetingLog(data)){
							JOptionPane.showMessageDialog(null,"Update Complete"
									,"Message", JOptionPane.INFORMATION_MESSAGE);
							
						}
					}else{
						if(dao.updateCompany(data)){
							JOptionPane.showMessageDialog(null,"Update Complete"
									,"Message", JOptionPane.INFORMATION_MESSAGE);
							
						}
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					companyDetail.control(comID);
					parent.setEnabled(true);
					view.setVisible(false);
				}
				
			}
		};
		view.setAcctionSubmitBtn(action);
	}
	

	
	private void setData(){
		data.setContactDay(view.getContactDay());
		data.setDetail(view.getComment());
		data.setContactTo(view.getContacName());
		data.setCreate(ConnectionPool.getUserID());
		data.setPhone(view.getTelNo());
		data.setEmail(view.getEmail());
		data.setCallDay(view.getCallDay());
		
	}
	public void setViewData(String seqNo){
		try {
			data = dao.searchMeetingLog(seqNo);
			view.setData(data);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Cannot get data"
					,"Error", JOptionPane.ERROR_MESSAGE);
		}finally {
			view.setVisible(true);
		}
		
	}

	public void control(String aComID, String thaiName,boolean isNewData) {
		view.clear();
		this.comID = aComID;
		data.setCompanyID(aComID);
		data.setCompanyThaiName(thaiName);
		view.setComID(aComID);
		view.setThaiName(thaiName);
		this.isNewData = isNewData;
		view.setVisible(true);
	}

	public boolean delete(String seqNo) {
		boolean result = false;
		try {
			result = dao.delete(seqNo);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Cannot delete data"
					,"Error", JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}

}
