package register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.base.ConnectionPool;

import tour.tourMain.tourMain;

public class reg {
	private regView view;
	private regDAO dao;
	public reg(){
		view = new regView();
		dao = new regDAO();
		addChangeAction();
		addUpdateAction();
	}
	public void control(){
		view.setVisible(true);
		try {
			view.setSaleID(dao.getSalesID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void addUpdateAction(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(view.checkData()){
					regData data = view.getData();
					boolean newData = false;
					if(view.getSelectSaleID()==0){
						newData = true;
					}
					try {
						if(dao.update(data,newData,view.isNewUser())){
							JOptionPane.showMessageDialog(view, "Update Complete","complete",JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view, "Cannot Update", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		};
		view.setUpateAction(action);
	}
	

	
	private void addChangeAction(){
		ItemListener action = new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					regData data = new regData();
					if(view.getSelectSaleID()>0){
						try {
							data = dao.getData(view.getSaleID());
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					view.setData(data);
				}
			}
		};
		view.addSalesIDAction(action);
	}
}
