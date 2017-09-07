package company.companyDetail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class companyDetailInsert extends companyDetail {
	private ActionListener 	insertAction;
	
	public companyDetailInsert(){
		this.view = new companyView();
		this.dao = new companyDAO();
		addListenerBtn();
		
		clearAction = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				view.clear();
			}
		};
		view.getClearBtn().addActionListener(clearAction);
		
		insertAction = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(view.checkInput()){
					companyData data = new companyData();
					data = setCompanyListData();
					insertData(data);
				}			
			}
		};
		view.getSubmitBtn().addActionListener(insertAction);
	}
	
	public void control(){
		view.setVisible(true);
	}
	
	private void insertData(companyData data){
		try {
			if(dao.canInsert(data.gettName())){
				if(dao.newCompany(data)){
					JOptionPane.showMessageDialog(null,"<html><center>Insert Complete</center></html>"
							,"",JOptionPane.PLAIN_MESSAGE);
					view.clear();
//					view.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null,"Please Insert Again or "
							+ "Check Data before Inserting"
							,"Error", JOptionPane.ERROR_MESSAGE);
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
}
