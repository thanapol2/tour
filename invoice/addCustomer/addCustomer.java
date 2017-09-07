package invoice.addCustomer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class addCustomer {
	private addView view;
//	private invoiceCustomData data;
	private addDAO dao;
	public addCustomer(JDialog parent){
		this.view = new addView(parent);
		
//		this.data = new invoiceCustomData();
		this.dao = new addDAO();
//		addCancelAction();
	}
//	public addCustomer(JFrame parent){
//		this.view = new addView(parent);
//		
////		this.data = new invoiceCustomData();
//		this.dao = new addDAO();
////		addCancelAction();
//	}
	public boolean isVisable(){
		return view.isVisible();
	}
	public void control(){
		view.setVisible(true);
	}
	public void setCancelAction(ActionListener action){
		view.setCancelAction(action);
	}
	public void setAddAction(ActionListener action){
		view.setAddAction(action);
	}
	public void cancelProcess(){
		view.setVisible(false);
	}
	public void setCloseAction(WindowListener action){
		view.addWindowListener(action);
	}
	public invoiceCustomData getData(){
		invoiceCustomData data = new invoiceCustomData();
		ArrayList<String> row = view.getSelectedData();
		if(row.size()>0){
			String id = row.get(0);
			String type = row.get(1);
			try {
				data = dao.searchData(id, type);
			} catch (SQLException e) {
				System.out.print("cannot query");
			}
		}
		return data;
	}
}
