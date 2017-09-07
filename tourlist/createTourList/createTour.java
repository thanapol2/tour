package tourlist.createTourList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.base.Tools;
import com.selectAddList.listAddSelect;
import com.table.rowMember;


public class createTour {
	private createTourView view;
	private createTourDAO  dao;
	private listAddSelect pasPack;
	private ArrayList<rowMember> dataTable;
//	private int chdNum;
	
	
	public createTour(){
		this.view = new createTourView();
		this.pasPack = new listAddSelect();
//		this.dataTable = new ArrayList<rowMember>();
		this.dao = new createTourDAO();
		addAddPasAction();
		addActionClosePas();
		addActionPasCancel();
		addActionPasSubmit();
		addActionFirst();
		addActionUp();
		addActionDown();
		addActionLast();
		addActionDel();
//		addActionTable();
		addActionBus();
		addActionSumbitXls();
//		loading();
	}
	
	private void addAddPasAction(){
		ActionListener addAction = new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				view.setEnabled(false);
				pasPack.control(dataTable);
		        Thread worker = new Thread() {
		        	public void run() {
		        		synchronized(view){
		                    while (view.isEnabled()){
		                        try {
		                            view.wait();
		                            if(pasPack.isVisable()){
		                            	view.notify();
		                            	view.setEnabled(true);
		                            	view.toFront();
		            	                view.repaint();
		                            }
		                        } catch (InterruptedException e) {
		                            e.printStackTrace();
		                        }
		        			}
		        		}
		        	}
		        };
		       	worker.start();
			}
		};
		view.setaddAction(addAction);
	}
	private void addActionClosePas(){
		WindowAdapter action = new WindowAdapter(){
           @Override
            public void windowClosing(WindowEvent e){
               synchronized (view) {
            	   pasPack.cancelProcess();
                   view.setEnabled(true);
                   view.notify();
                   view.toFront();
	               view.repaint();
               }
            }
		};
		pasPack.setCloseAction(action);;
	}
	
	private void addActionPasCancel(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				synchronized (view) {
					pasPack.cancelProcess();
//					genTableData(pasPack.getMemID());
					view.notify();
					view.setEnabled(true);
					view.toFront();
	                view.repaint();
				}
			}
		};
		pasPack.setCancelAction(action);
	}
	
	private void addActionPasSubmit(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				synchronized (view) {
					pasPack.submitProcess();
					genTableData(pasPack.getMemID());
					view.notify();
					view.setEnabled(true);
					view.toFront();
	                view.repaint();
					view.setPax(dataTable.size());
//					view.setADL(dataTable.size()-chdNum);
//					view.setCHD(chdNum);
				}
			}
		};
		pasPack.setSumbitAction(action);
	}
	
	private void addActionFirst(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int index = view.getSelectRow();
				int size = dataTable.size();
				if((index>=0)&&(size>0)){
					first(index);
//					view.setModel(dataTable);
				}
			}
		};
		view.setFirstAction(action);
	}
	private void addActionDown(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int index = view.getSelectRow();
				int size = dataTable.size();
				if((index>=0)&&(size>0)){
					down(index);
//					view.setModel(dataTable);
				}	
			}
		};
		view.setDownAction(action);
	}
	private void addActionUp(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int index = view.getSelectRow();
				int size = dataTable.size();
				if((index>=0)&&(size>0)){
					up(index);
//					view.setModel(dataTable);
				}
			}
		};
		view.setUpAction(action);
	}
	private void addActionLast(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int index = view.getSelectRow();
				int size = dataTable.size();
				if((index>=0)&&(size>0)){
					last(index);
//					view.setModel(dataTable);
				}
			}
		};
		view.setLastAction(action);
	}
	
	private void addActionDel(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int index = view.getSelectRow();
				int size = dataTable.size();
				if((index>=0)&&(size>0)){
//					boolean checkADL = dataTable.get(index).getADL();
					dataTable.remove(index);
//					if(checkADL==false){
//						chdNum = chdNum - 1;
//					}
					view.setPax(dataTable.size());		
//					view.setCHD(chdNum);
					view.setModel(dataTable);
//					view.setADL(dataTable.size()-chdNum);
				}
			}
		};
		view.setdelAction(action);
	}
	
	private void addActionSumbitXls(){
		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				view.setEnabled(false);
		        Thread worker = new Thread(){
		        	public void run() {
			        	createTourData data = new createTourData(dataTable);
						setData(data);
						try {
							if(data.checkData()&&view.arrAndDep()){
								createTourXls xls = new createTourXls(data);
								boolean canCreate = xls.genXls();
								if(canCreate){
									boolean canInsert = dao.insertRoomlist(data);
									if(canInsert){
										clear();
									}
								}
							}else{
								JOptionPane.showMessageDialog(null,"Please Insert Group Name,"
										+ "Room List Name,Check Date ,Choose tour member.");
							}
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} finally{
							view.setEnabled(true);
							view.toFront();
			                view.repaint();
							
						}
		        	}
		        };
		    	worker.start();
			}
		};
		view.setSubmitAction(action);
	}
	
	
	public void control(){
		view.setVisible(true);
		clear();
	}
	private void clear(){
		this.dataTable = new ArrayList<rowMember>();
		view.clear();
	}
	private void genTableData(ArrayList<String> memID){
		try {
			ArrayList<rowMember> listTemp = new ArrayList<rowMember>();
//			chdNum = 0;
			if(memID.size()>0){
				listTemp = dao.searchTourID(memID);	
			}
			for(rowMember temp : listTemp){
				dataTable.add(temp);
//				if(temp.getADL()==false){
//					chdNum = chdNum + 1;
//				}
			}
			view.setModel(dataTable);
//			view.setCHD(chdNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void first(int index){
		if(index>0){
			rowMember temp = new rowMember();
			temp = dataTable.get(index);
			dataTable.remove(index);
			dataTable.add(0, temp);
			view.setModel(dataTable);
		}
	}
	private void up(int index){
		if(index>0){
			Collections.swap(dataTable, index, index-1);
			view.setModel(dataTable);
		}
	}
	private void down(int index){
		if((index>=0)&&(index!= dataTable.size()-1)){
			Collections.swap(dataTable, index, index+1);
			view.setModel(dataTable);
		}
	}
	private void last(int index){
		if(index>=0){
			rowMember temp = new rowMember();
			temp = dataTable.get(index);
			dataTable.remove(index);
			dataTable.add(temp);
			view.setModel(dataTable);
		}
	}
	
	private boolean setData(createTourData data){
		boolean result = false;
		
		// Set Headderg
		data.setGroupName(view.getGroupName());
		data.setListName(view.getTourListName());
		data.setDestination(view.getDestinationBox());
		data.setHotelName(view.getHotelName());
		data.setDepartNo(view.getDepartNo());
		data.setDepartFlight(view.getDepartName());
		data.setDeparteDate(view.getDeparteDate());
		data.setDepartTime(view.getDepartTime());
		data.setArrNo(view.getArrNo());
		data.setArrFlight(view.getArrName());
		data.setArrDate(view.getArrDate());
		data.setArrTime(view.getArrTime());
		result = true;
		return result;
	}
	private void addActionBus(){
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dataTable.size()>0){
					for(int i=0;i<dataTable.size();i++){
						dataTable.get(i).getStringData().set(12, "1");
					}
				}
				view.setModel(dataTable);
			}
		};
		view.setBusAction(action);
	}
	
	private void loading(){
	    JFrame frame = new JFrame("Loading");

	    ImageIcon loading = new ImageIcon("D:\\workspace\\tour\\icon\\loading.gif");
	    frame.add(new JLabel("<html><br>loading... </html>", loading, JLabel.CENTER));

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(400, 300);
	    frame.setVisible(true);
	}

}
