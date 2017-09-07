package tour.tourListDetail;



import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;









import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

import com.base.Tools;


public class listDetailEdit extends listDetail{
	
	private listDetailData data;
	private boolean checkDisable;
	
	public listDetailEdit(){
		this.view = new listDetailView();
		view.getBtnDisable().setVisible(true);
		view.getDelTourBtn().setVisible(true);
		view.getBtnSavePic().setVisible(true);
//		view.getBtnSubmit().setEnabled(true);
		this.dao = new listDetailDAO();
		addListenerBtn();
		view.getBtnClear().setText("Reset");
		
		ActionListener disableAction = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(checkDisable){
					view.setEnable(false);
					checkDisable = false;
				}else{
					view.setEnable(true);
					checkDisable = true;
				}
			}	
		};
		view.getBtnDisable().addActionListener(disableAction);
		
		clearAction = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setEditGUI(data);
			}
		};
		view.getBtnClear().addActionListener(clearAction);
		
		ActionListener delAction = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int result = JOptionPane.showConfirmDialog
						(null, "Please confirm to delete this data","Warning",dialogButton);
				if(result!=1){
					try {
						if(dao.deleteTourList(data.getTourID())){
							JOptionPane.showMessageDialog(null,"<html><center>Delete complete</center></html>"
									,"",JOptionPane.PLAIN_MESSAGE);
							data = new listDetailData();
							picName 	= null;
							bufferImage = null;
							view.clear();
							view.setVisible(false);
						}else{
							JOptionPane.showMessageDialog(null,"Re-delete again"
									,"Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"Re-delete again"
								,"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		};
		view.getDelTourBtn().addActionListener(delAction);
		
		editAction = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Enable Case
				if(checkDisable){
					if(view.setStringData()){
						if(filePic != null){
							try {
								picName = Tools.genPicName(filePic.getName());
							} catch (Exception e1) {
//								JOptionPane.showMessageDialog(null,"Can't upload file and Insert Data."
//										+ "Please check FTP server");
							}
						}
						data = new listDetailData();
						data = setTourListData(); 
						updateData(data);
					}					
				}else{ //disable case
					JOptionPane.showMessageDialog(null,"Please Enable Screen First");
				}

			}		
		};
		view.getBtnSubmit().addActionListener(editAction);
		
		ActionListener saveAction = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Tools.isNotEmpty(picName)&&Tools.isNotEmpty(bufferImage)){
					String extension = FilenameUtils.getExtension(picName);
					String fullFileName = data.gettFirst()+"_"+data.gettSurName()
							+"."+extension;
					JFileChooser chooser = new JFileChooser();
					chooser.setSelectedFile(new File(fullFileName));
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
						    extension+" Image files", extension);
					chooser.setFileFilter(filter);
					int retrival = chooser.showSaveDialog(null);
				    if (retrival == JFileChooser.APPROVE_OPTION){
				    	fullFileName = FilenameUtils.removeExtension(chooser.getSelectedFile().toString());
				    	try{
				    		FileOutputStream fileOut = new FileOutputStream(fullFileName+"."+extension);
				    		ImageIO.write(bufferImage, extension, fileOut);
							fileOut.flush();
							fileOut.close();
							JOptionPane.showMessageDialog(null,"<html><center>Save Complete.</center></html>"
									,"",JOptionPane.PLAIN_MESSAGE);
						} catch (FileNotFoundException e) {
							System.out.print("Can't save file");
						} catch (IOException e) {
							System.out.print("IO error");
						}
				    }
				}else if(Tools.isEmpty(picName)&&Tools.isEmpty(bufferImage)){
					JOptionPane.showMessageDialog(null,"<html><center>Image has not found.</center></html>"
							,"Error", JOptionPane.ERROR_MESSAGE);
				}else if(Tools.isEmpty(picName)&&Tools.isNotEmpty(bufferImage)){
					JOptionPane.showMessageDialog(null,"<html><center>Please upload Image first."
							+ "</center></html>"
							,"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		};
		view.getBtnSavePic().addActionListener(saveAction);
	}
	
	public void control(String tourID){		
		data = new listDetailData();
		try {
			data = dao.searchTourID(tourID);
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null,"Can't connect Database"
					,"Error", JOptionPane.ERROR_MESSAGE);
		}
		if(!Tools.isEmpty(data.getTourID())){
			view.getBtnSubmit().setEnabled(true);
			view.clear();
			view.setVisible(true);
			setEditGUI(data);
			if(view.setEnable(false)){
				checkDisable = false;
			}else{
				checkDisable = true;
			}
		}else{
			JOptionPane.showMessageDialog(null,"<html><center>Data has not in Database"
					+ "<br>Please re-search agian</center></html>"
					,"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	private void updateData(listDetailData data){
		try {
			if(dao.canEdit(data)){
				if(dao.updateTourList(data,filePic)){
					JOptionPane.showMessageDialog(null,"<html><center>Update Data Complete</center></html>"
							,"",JOptionPane.PLAIN_MESSAGE);
					setEditGUI(data);
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
	
	private void setEditGUI (listDetailData data){
		if(data.getPathPic() != null){
			ftp = new listDetailFTP();
			picName = data.getPathPic();
			bufferImage = ftp.downloadImage(picName);
			data.setIsImage(bufferImage);
			view.setPicLable(bufferImage);
		}else{
			picName 	= null;
			bufferImage = null;
		}
		if (Tools.isEmpty(data.getTourID())){
			view.getBtnSubmit().setEnabled(false);
		}else{
			view.setViewText(data);
			view.getBtnSubmit().setEnabled(true);
		}
		
	}
	
//	private boolean delete (String tourID){
//		boolean result = false;
//		try {
//			dao.deleteTourList(tourID);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}

}
