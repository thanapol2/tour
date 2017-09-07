package invoice.search;

import invoice.invoiceDetail.invoiceDetailDAO;
import invoice.invoiceDetail.invoiceDetailEdit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.base.Tools;
import com.table.tableTour;

public class invoiceSearch {

	private boolean CAN_PAYMENT = false;
	private boolean IS_VAT = false;
	private invoiceSearchView view;
	private invoiceSearchDAO dao;
	
//	private invoiceDetailDAO test;
	public invoiceSearch(boolean canPayment,Boolean isVat){
		this.view = new invoiceSearchView();
		this.dao = new invoiceSearchDAO();
		CAN_PAYMENT = canPayment;
		IS_VAT = isVat;
//		this.test = new invoiceDetailDAO();
	}
	public void control(boolean isTour){
		view.setVisible(true);
		actionSearch(isTour);
		actionCancel();
		addClickTable();
		actionDelete();
	}
	
	public void actionSearch(final boolean isTour){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				searchProcess(isTour);
//				searchData input = new searchData();
//				input = view.getSearchData();
//				if(input.checkStartEnd()){
//					try {
//						ArrayList<rowInvoiceHead> listData = dao.searchInvoiceList(input,isTour);
//						view.setModel(listData);
//					} catch (SQLException e1) {
//						JOptionPane.showMessageDialog(null,"Cannot connect to database"
//	        					,"Error", JOptionPane.ERROR_MESSAGE);
//					}
//				}else{
//					JOptionPane.showMessageDialog(null,"Enter End date"
//        					,"Error", JOptionPane.ERROR_MESSAGE);
//				}
//				
			}
		};
		view.setSearchAction(action);
	}
	
	private void searchProcess(boolean isTour){
		searchData input = new searchData();
		input = view.getSearchData();
		if(input.checkStartEnd()){
			try {
				ArrayList<rowInvoiceHead> listData = dao.searchInvoiceList(input,isTour,IS_VAT);
				view.setModel(listData);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,"Cannot connect to database"
    					,"Error", JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null,"Enter End date"
					,"Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	private void addClickTable(){
		MouseAdapter action = new MouseAdapter(){
			public void mousePressed(MouseEvent event) {
				if(event.getClickCount()==2){
					String temp = view.getInvoiceNoRev();
					if(temp.equals("No invoice")){
						JOptionPane.showMessageDialog(null,"No invoice created."
	        					,"Error", JOptionPane.ERROR_MESSAGE);
					}else{
						temp = temp.replace("(", "").replace(")", "");
						invoiceNo invoice = new invoiceNo(temp);
						invoiceDetailEdit edit = new invoiceDetailEdit(CAN_PAYMENT);
						edit.control(invoice.invoiceNo, invoice.rev);
//						System.out.print(temp.contains("REV"));
					}
				}
			}
		};
		view.setClickTableAction(action);
	}	
	
	private void actionCancel(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				view.setVisible(false);
			}
		};
		view.setCancelAction(action);
	}	
	
	private class invoiceNo{
		// With Type
		public String invoiceNo;
		public String rev;
		public invoiceNo(String invoiceRev){
			if(invoiceRev.contains("REV")){
				String [] temp = invoiceRev.split("REV");
				this.invoiceNo = temp[0];
				this.rev = temp[1];
			}else{
				this.invoiceNo = invoiceRev;
				this.rev = "0";
			}
		}
	}
	public void actionDelete(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String invoiceNum = view.getSelectInvoice();
				System.out.print(invoiceNum);
				if(Tools.isNotEmpty(invoiceNum)){
					try {
						if(dao.isPayment(invoiceNum)){
							JOptionPane.showMessageDialog(null,"Can't delete this invoice"
									+ ", That is already payment"
		        					,"Error", JOptionPane.ERROR_MESSAGE);
						}else{
							int dialogResult = JOptionPane.showConfirmDialog(view,
									"Do you want to delete this invoice",
									"Confirm Delete",
									JOptionPane.YES_NO_OPTION);
							if(dialogResult == JOptionPane.YES_OPTION){
								dao.deleteAllInvoice(invoiceNum);
//								System.out.print("delete complete");
								if(invoiceNum.contains("RTS")){
									searchProcess(false);
								}else{
									searchProcess(true);
								}
								
							}else{
								
							}
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null,"Please Select Invoice"
        					,"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		view.setDelAction(action);
	}
}

