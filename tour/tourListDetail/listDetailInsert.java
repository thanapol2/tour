package tour.tourListDetail;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;



import javax.swing.JOptionPane;

import com.base.Tools;



public class listDetailInsert extends listDetail{

	public listDetailInsert(){
		this.view = new listDetailView();
		this.dao = new listDetailDAO();
		addListenerBtn();
		
		clearAction = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clearPicData();
//				view.setPicLable(bufferImage);
				view.clear();
			}
		};
		view.getBtnClear().addActionListener(clearAction);
		
		insertAction = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(view.setStringData()){
					if(filePic != null){
						try {
							picName = Tools.genPicName(filePic.getName());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					listDetailData data = new listDetailData();
					data = setTourListData();
					insertData(data);
				}
			}		
		};
		view.getBtnSubmit().addActionListener(insertAction);
	}
	
	public void control(){
//		view.setUndecorated(true);
		view.setVisible(true);
	}
	
	private void insertData(listDetailData data){
		try {
			if(dao.canInsert(data)){
				if(dao.newTourList(data,filePic)){
					JOptionPane.showMessageDialog(null,"<html><center>Insert Complete</center></html>"
							,"",JOptionPane.PLAIN_MESSAGE);
					view.clear();
//					view.setVisible(false);
					clearPicData();
				}else{
					JOptionPane.showMessageDialog(null,"Please Insert Again or "
							+ "Check Data before Inserting"
							,"Error", JOptionPane.ERROR_MESSAGE);
				}
	
			}else{
				JOptionPane.showMessageDialog(null,"Data is already in database"
						,"Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Please Insert Again"
					,"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	

	
//	public Boolean newTourList (tourListData data){
//		
//	}
//	public Boolean updateData (tourListData data){
//		
//	}
//	public Boolean updatePic (tourListData data){
//		
//	}
}
