package payment.search;

import invoice.invoiceDetail.invoiceDetailDAO;
import invoice.invoiceDetail.invoiceDetailEdit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import payment.detail.payment;

import com.base.Tools;
import com.table.tableTour;

public class paymentSearch {

//	private boolean CAN_PAYMENT = false;
	private paymentSearchView view;
	private paymentSearchDAO dao;
	private payment nonInvoicePack;
//	private invoiceDetailDAO test;
	public paymentSearch(){
		this.view = new paymentSearchView();
		this.dao = new paymentSearchDAO();
		actionSearch();
		actionCancel();
		addClickTable();
		actionReject();
		actionDelete();
//		CAN_PAYMENT = canPayment;
//		this.test = new invoiceDetailDAO();
	}
	public paymentSearch(boolean hasVat){
		this.view = new paymentSearchView();
		this.dao = new paymentSearchDAO();
		actionSearch();
		actionCancel();
		addClickTable();
		actionReject();
		actionDelete();
		view.genCasePaymentVat(hasVat);
//		CAN_PAYMENT = canPayment;
//		this.test = new invoiceDetailDAO();
	}
	public void control(){
		view.setVisible(true);

	}
	
	public void actionSearch(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				searchData();				
			}
		};
		view.setSearchAction(action);
	}
	private void searchData(){
		searchData input = new searchData();
		input = view.getSearchData();
		if(input.checkStartEnd()){
			try {
				ArrayList<rowPaymentHead> listData = dao.searchPaymentList(input);
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
					String temp = view.getPaymentNo();
					boolean hasInvoice = view.getHasInvoice();
					if(temp.equals("")){
						JOptionPane.showMessageDialog(null,"No Payment created."
	        					,"Error", JOptionPane.ERROR_MESSAGE);
					}else{
						view.setEnabled(false);
						String isVat = "N";
						if("ใบกำกับภาษี".equals(view.getIsvat())){
							isVat = "Y";
						}
						nonInvoicePack = new payment(temp,isVat,hasInvoice,view.isNotReject(),view);
//						temp = temp.replace("(", "").replace(")", "");
//						invoiceNo invoice = new invoiceNo(temp);
//						invoiceDetailEdit edit = new invoiceDetailEdit(CAN_PAYMENT);
//						edit.control(invoice.invoiceNo, invoice.rev);
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
	
	public void actionDelete(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String paymentNo = view.getSelectPayment();
				System.out.print(paymentNo);
				if(Tools.isNotEmpty(paymentNo)){
					if(view.canDeletePayment()){
						int dialogResult = JOptionPane.showConfirmDialog(view,
								"Do you want to Delete this payment",
								"Confirm to Reject",
								JOptionPane.YES_NO_OPTION);
						if(dialogResult == JOptionPane.YES_OPTION){
							try {
								if(dao.deletePayment(paymentNo,false)){
									JOptionPane.showMessageDialog(null,"Delete is complete");
									searchData();
								}else{
									JOptionPane.showMessageDialog(null,"Delete is not complete"
				        					,"Error", JOptionPane.ERROR_MESSAGE);
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}else{
							
						}
					}else{
						JOptionPane.showMessageDialog(null,"This payment isnot \"No invoice\" Type. Please contact admin"
	        					,"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		};
		view.setDelAction(action);
	}
	
	public void actionReject(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String paymentNo = view.getSelectPayment();
				System.out.print(paymentNo);
				if(Tools.isNotEmpty(paymentNo)){
					if(view.canRejectPayment()){
						int dialogResult = JOptionPane.showConfirmDialog(view,
								"Do you want to Reject this payment",
								"Confirm to Reject",
								JOptionPane.YES_NO_OPTION);
						if(dialogResult == JOptionPane.YES_OPTION){
							try {
								if(dao.rejectPayment(paymentNo,false)){
									JOptionPane.showMessageDialog(null,"Reject is complete");
									searchData();
								}else{
									JOptionPane.showMessageDialog(null,"Reject is not complete"
				        					,"Error", JOptionPane.ERROR_MESSAGE);
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}else{
							
						}
					}else{
						JOptionPane.showMessageDialog(null,"This payment cannot Reject(Invoice is Tax Type) Please contact admin"
	        					,"Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null,"Please Select Payment"
        					,"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		view.setRejectAction(action);
	}
}

