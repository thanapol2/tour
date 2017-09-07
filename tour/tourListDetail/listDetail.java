package tour.tourListDetail;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.net.ftp.FTPClient;



import com.base.Tools;
import com.base.ftpConnection;

public abstract class listDetail {
	protected listDetailView 	view;
	protected ActionListener 	insertAction;
	protected ActionListener 	editAction;
	protected ActionListener 	clearAction;
	protected ActionListener 	cancelAction;
	protected ActionListener 	checkAction;
	protected ActionListener 	picBrowse;
	protected ActionListener 	picView;
	protected ActionListener 	picDel;
	protected listDetailDAO 	dao;
	protected listDetailFTP		ftp;
	
	/*
	 * File ---> for upload
	 * picName , buffer ---> GUI DB
	 */
	protected String		picName;
	protected File			filePic;
	protected BufferedImage	bufferImage;
	
	protected void addListenerBtn(){
		picBrowse = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
					    "Images Files", "jpg", "gif","jpge");
				view.getChooser().setFileFilter(filter);
				int returnVal = view.getChooser().showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					filePic = view.getChooser().getSelectedFile();
					try {
						bufferImage  = ImageIO.read(filePic);
						view.setPicLable(bufferImage);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		};
		view.getBtnBrowse().addActionListener(picBrowse);
		
		picView = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(Tools.isEmpty(bufferImage)){
					JOptionPane.showMessageDialog(null,"Has not Image");
				}else{
					new viewFrame(new ImageIcon(bufferImage));
				}
			}
		};
		view.getBtnView().addActionListener(picView);
		
		picDel = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				picName = null;
				bufferImage = null;
				view.setPicLable(bufferImage);
			}
		};
		view.getBtnDelPic().addActionListener(picDel);
		
		
		cancelAction = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				picName = null;
				bufferImage = null;
				view.setPicLable(bufferImage);
				view.clear();
				view.setVisible(false);
			}
		};
		view.getBtnCancel().addActionListener(cancelAction);
		
		checkAction = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					Boolean result = dao.canInsert(view.thaiName());
					if(result){
						JOptionPane.showMessageDialog(null,"Data can inserting");
					}else{
						JOptionPane.showMessageDialog(null,"your data is already in a database"
								,"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		view.getCheckBtn().addActionListener(checkAction);
	}
	
	protected listDetailData setTourListData(){
		listDetailData temp = new listDetailData();
		temp.setTourID(view.getTourID());
		temp.settTittle(view.getTTittle());
		temp.settFirst(view.getTFrist());
		temp.settSurName(view.getTSurname());
		temp.seteTitle(view.getETittle());
		temp.seteFirst(view.getEFirst());
		temp.seteSurName(view.getESurname());
		temp.setSex(view.getSex());
		temp.setPassportID(view.getPassport());
		temp.setPersonID(view.getPersonID());
		temp.setNationality(view.getNation());
		temp.setCountry(view.getCounty());
		temp.setBirthDay(view.getBirth());
		temp.setIssueDate(view.getIssue());
		temp.setExpireDate(view.getExpire());
		temp.setDetail(view.getDetail());
		temp.setemail(view.getEmail());
//		add path_pic
		temp.setPathPic(picName);
		temp.setIsImage(bufferImage);
		temp.setTelNo(view.getTelNo());
		temp.setAddress(view.getAddress());
		temp.setPostNo(view.getPostNo());
		temp.setProvince(view.getProvice());
		temp.setEat(view.getEat());
		return temp;
	}

	protected class viewFrame extends JFrame{
	    private viewFrame(ImageIcon image){
	        JPanel panel = new JPanel();
	        float width  = image.getIconWidth();
	        float height = image.getIconHeight();
	        float widthPanel = Integer.parseInt(Tools.getConfig("width"));
	        float heightPanel = Integer.parseInt(Tools.getConfig("height"));
	        float resize = 1;
	        if((widthPanel>width)&&(heightPanel>height)){
	        	
	        }else if((widthPanel/width)<(heightPanel/height)){
	        	resize = (widthPanel/width);
	        }else if((widthPanel/width)>(heightPanel/height)){
	        	resize = (heightPanel/height);
	        }
	        int nWidth = (int) (resize*width);
	        int nHeight = (int) (resize*height);
	        panel.setPreferredSize(new Dimension(nWidth,nHeight));
	        add(panel);
	        JLabel label = new JLabel("",new ImageIcon
	        		(bufferImage.getScaledInstance(nWidth, nHeight, Image.SCALE_SMOOTH)), JLabel.CENTER);
	        panel.add( label, BorderLayout.CENTER );
	        pack();
	        setVisible(true);
	   }
	}
	
	protected void clearPicData(){
		picName = null;
		filePic = null;
		bufferImage = null;
	}
//	
//	protected BufferedImage downloadImage(String fileName) throws Exception{
//		FTPClient ftp = ftpConnection.INSTANCE.getFTP();
//		InputStream inputSteam = ftp.retrieveFileStream(fileName);
//		BufferedImage image = ImageIO.read(inputSteam);
//		ftpConnection.INSTANCE.closeConnection(ftp);
//		return image;
//	}

}
