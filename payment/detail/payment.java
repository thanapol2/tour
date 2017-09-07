package payment.detail;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.base.ConnectionPool;
import com.ui.loadingPanel;

import invoice.addCustomer.addCustomer;
import invoice.addCustomer.invoiceCustomData;
import invoice.invoiceDetail.invoiceData;
import invoice.invoiceXLS.printPayment;
import com.selectAddList.listAddSelect;

public class payment {
	private paymentView view;
	private addCustomer addCustomer;
	private paymentDAO dao;
//	private String SALES_ID = Tools.getConfig("sales_id");
	private searchPaymentData data;
	private String paymentNo;
//	public nonInvoice(JDialog parent){
//		parent.setEnabled(false);
//		this.view = new nonInvoicePaymentView(parent);
//		this.addCustomer = new addCustomer(view);
//		this.dao = new nonInvoiceDAO();
////		this.listSelect = new listSelect(view);
//		actionCancelOnAddPack();
//		actionTourSearch();
//		actionSubmitOnAddPack();
////		actionSearchSales();
//		actionClearHead();
//		actionSumbit();
//		view.setVisible(true);
//		view.setPrintEnable(false);
//		view.textUpdateVatAction();
//	}
	
	public payment(JFrame parent,boolean paymentHasVat){
		parent.setEnabled(false);
		this.view = new paymentView(parent);
		this.addCustomer = new addCustomer(view);
		this.dao = new paymentDAO();
		actionCancelOnAddPack();
		actionTourSearch();
		actionSubmitOnAddPack();
//		actionSearchSales();
		actionClearHead();
		actionSumbit();
		view.setVisible(true);
		view.setPrintEnable(false);
		view.textUpdateVatAction();
		view.genCasePaymentVat(paymentHasVat);
	}
	
	public payment(String paymentNo,String isVat,boolean hasInvoice,boolean isReject,JDialog parent){
		this.view = new paymentView(parent);
		this.addCustomer = new addCustomer(view);
		this.dao = new paymentDAO();
		this.data = new searchPaymentData();
		this.paymentNo = paymentNo;
		view.setHasInvoice(hasInvoice);
		view.setEnableSubmitBtn(isReject);
		actionPrintBtn();
		actionUpdate();
		view.setVisible(true);
		view.setAllBtnEnable(false);
		try {
			data = dao.searchPayment(paymentNo,isVat, ConnectionPool.getUserID());
			view.setDetail(data);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Cannot Query data from database"
					,"Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	public void control(){
		view.setVisible(true);
		view.clearHead();
		view.clearDetail();
		searchSales(ConnectionPool.getUserID());
		
	}
	private void actionSumbit(){
		view.setSubmitAction(new submitAction());
		
	}
	
	private void actionUpdate(){
		view.setSubmitAction(new updateAction());
		
	}
	
	private void actionClearHead(){
		ActionListener action = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.clearHead();
				
			}
		};
		view.setClearHeadAction(action);
	}
	private void actionCancelOnAddPack(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
//				synchronized (view) {
					addCustomer.cancelProcess();
//					view.notify();
					view.setEnabled(true);
	                view.toFront();
	                view.repaint();
//				}
			}
		};
		WindowAdapter action2 = new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
//				synchronized (view) {
					addCustomer.cancelProcess();
//					view.notify();
					view.setEnabled(true);
	                view.toFront();
	                view.repaint();
//				}
			}
		};
		addCustomer.setCancelAction(action);
		addCustomer.setCloseAction(action2);
	}
	
	private void actionPrintBtn(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(view.checkAllInput()){
	        		invoiceData invoice = view.getInvoiceData();
	        		paymentData payment = view.getPaymentData();
	        		payment.setPaymentNo(paymentNo);
	        		String customerID = invoice.getCustomerID();
	        		if(customerID.startsWith("C")){
	        			try {
							invoice.setTaxID(dao.getTaxID(customerID));
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null,"Cannot found Tax ID process check DB"
		        					,"Error", JOptionPane.ERROR_MESSAGE);
						}
	        		}
        			String printType = getFileName(view.hasVat());
					createXlsPayment(printType,invoice,payment);
					view.setEnabled(true);
					view.toFront();
//					view.setAlwaysOnTop(true);
	        	}else{
	        		JOptionPane.showMessageDialog(null,"Check input data"
        					,"Error", JOptionPane.ERROR_MESSAGE);
	        		view.setEnabled(true);
	        	}
			}
		};
		view.setPrintAction(action);
	}
	
	private void actionTourSearch(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				view.setEnabled(false);
				addCustomer.control();
			}
			
		};
		view.setTourSearchAction(action);
	}
	
	private void actionSubmitOnAddPack(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				synchronized (view) {
					invoiceCustomData temp = addCustomer.getData();
					addCustomer.cancelProcess();
					view.setCustomerData(temp);
					view.notify();
					view.setEnabled(true);
	                view.toFront();
	                view.repaint();
				}
			}
		};
		addCustomer.setAddAction(action);
	}
//	private void actionEditTour(){
//			ActionListener action = new ActionListener(){
//				public void actionPerformed(ActionEvent e) {
////					
//				}
//			};
//			view.setEditCustomAction(action);
//	 }
	
	private class submitAction extends AbstractAction {
		boolean insertComplete = false;
		public submitAction() {
			super();
		}
		   @Override
		   public void actionPerformed(ActionEvent evt) {
		      SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
		         @Override
		         protected Void doInBackground() throws Exception {
		            // mimic some long-running process here...
		        	view.setEnabled(false);
		        	if(view.checkAllInput()){
		        		String invoiceNo = dao.getNewInoiceNo();
		        		invoiceData invoice = view.getInvoiceData();
		        		invoice.setInvoiceNo(invoiceNo);
		        		paymentData payment = view.getPaymentData();
		        		String customerID = invoice.getCustomerID();
		        		if(customerID.startsWith("C")){
		        			try {
								invoice.setTaxID(dao.getTaxID(customerID));
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null,"Cannot found Tax ID process check DB"
			        					,"Error", JOptionPane.ERROR_MESSAGE);
							}
		        		}
		        		if(dao.createNewPaymentNonInvoice(invoice, payment)){
		        			payment.setPaymentNo(dao.getPaymentNo(invoiceNo));
		        			String printType = getFileName(view.hasVat());
							createXlsPayment(printType,invoice,payment);
							view.setEnabled(true);
							view.toFront();
//							view.setAlwaysOnTop(true);
							view.setVisible(false);
		        		}else{
		        			JOptionPane.showMessageDialog(null,"Insert not complete"
		        					,"Error", JOptionPane.ERROR_MESSAGE);
		        		}	
		        	}else{
		        		JOptionPane.showMessageDialog(null,"Check input data"
	        					,"Error", JOptionPane.ERROR_MESSAGE);
		        	}
					return null;

		         }
		      };

		      Window win = SwingUtilities.getWindowAncestor((AbstractButton)evt.getSource());
		      final loadingPanel dialog = new loadingPanel();

		      mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

		         @Override
		         public void propertyChange(PropertyChangeEvent evt) {
		            if (evt.getPropertyName().equals("state")) {
		               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
		                  dialog.dispose();
		                  view.setEnabled(true);
		                  view.toFront();
		                  view.repaint();
		                  view.setVisible(!insertComplete);
		               }
		            }
		         }
		      });
		      mySwingWorker.execute();
		      dialog.setLocationRelativeTo(win);
		   }
	}
	
	
	private class updateAction extends AbstractAction {
		boolean insertComplete = false;
		public updateAction() {
			super();
		}
		   @Override
		   public void actionPerformed(ActionEvent evt) {
		      SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
		         @Override
		         protected Void doInBackground() throws Exception {
		            // mimic some long-running process here...
		        	view.setEnabled(false);
		        	if(view.checkAllInput()){
		        		int dialogButton = JOptionPane.YES_NO_OPTION;
		        		int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to update payment data?","Warning",dialogButton);
		        		if(dialogResult == JOptionPane.YES_OPTION){
		        			invoiceData invoice = view.getInvoiceData();
			        		paymentData payment = view.getPaymentData();
			        		payment.setPaymentNo(paymentNo);
			        		String customerID = invoice.getCustomerID();
			        		if(customerID.startsWith("C")){
			        			try {
									invoice.setTaxID(dao.getTaxID(customerID));
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(null,"Cannot found Tax ID process check DB"
				        					,"Error", JOptionPane.ERROR_MESSAGE);
								}
			        		}
			        		if(dao.updatePayment(invoice, payment)){
			        			String printType = getFileName(view.hasVat());
								createXlsPayment(printType,invoice,payment);
			        		}
		        		
							view.setEnabled(true);
							view.toFront();
//							view.setAlwaysOnTop(true);
							view.setVisible(false);
		        		}else{
		        			JOptionPane.showMessageDialog(null,"Update is not cancel"
		        					,"Error", JOptionPane.ERROR_MESSAGE);
		        		}
		        		
		        	}else{
		        		JOptionPane.showMessageDialog(null,"Check input data"
	        					,"Error", JOptionPane.ERROR_MESSAGE);
		        	}
					return null;

		         }
		      };

		      Window win = SwingUtilities.getWindowAncestor((AbstractButton)evt.getSource());
		      final loadingPanel dialog = new loadingPanel();

		      mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

		         @Override
		         public void propertyChange(PropertyChangeEvent evt) {
		            if (evt.getPropertyName().equals("state")) {
		               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
		                  dialog.dispose();
		                  view.setEnabled(true);
		                  view.toFront();
		                  view.repaint();
		                  view.setVisible(!insertComplete);
		               }
		            }
		         }
		      });
		      mySwingWorker.execute();
		      dialog.setLocationRelativeTo(win);
		   }
	}
	
	
	
	private boolean createXlsPayment (String printType ,invoiceData data,paymentData payment){
		boolean createXls = false;
		printPayment print = new printPayment(printType);
		if(print.printXls(data,payment)){
//			int result = print.saveFile("payment","");
			int result = print.saveFile("payment_"+payment.getPaymentNo());
			if(result==1){
				createXls = true;
				JOptionPane.showMessageDialog(null,"Saving Complete");
			}
		}else{
			JOptionPane.showMessageDialog(null,"please check Template file. see at config"
					,"Error", JOptionPane.ERROR_MESSAGE);
		}
		return createXls;
	}

	private void searchSales(String salesID){
		String salesName = "";
		view.setSaleID(salesID);
		try {
			salesName = dao.getSalesName(salesID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(salesName.equals("")){
			JOptionPane.showMessageDialog(null,"Sales id is not found"
					,"Error", JOptionPane.ERROR_MESSAGE);
		}else{
			view.setSaleName(salesName);
		}
	}
	private String getFileName(boolean hasVat){
		String printType = "payment";
		if(hasVat){
			printType = "paymentVat";
		}else{
			printType = "payment";
		}
		return printType;
	}
	
}
