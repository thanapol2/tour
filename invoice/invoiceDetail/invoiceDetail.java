package invoice.invoiceDetail;


import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import payment.detail.paymentData;
import invoice.addCustomer.addCustomer;
import invoice.addCustomer.invoiceCustomData;
import invoice.invoiceDetail.invoice;
import invoice.invoiceXLS.printInvoice;
import invoice.invoiceXLS.printPayment;

import com.base.Tools;
import com.ui.loadingPanel;



public class invoiceDetail {

	
	protected invoiceDetailView view;
	protected invoiceDetailDAO dao;
	protected ArrayList<rowInvoice> dataTable;
	protected String invoiceType;
	protected addCustomer addCustomer;
	protected loadingPanel loading;
	protected boolean isNewInvoice = true;
	protected boolean CAN_PAYMENT = false;
	private final int EN_TYPE = 1;
	protected invoiceDetail(){
		this.view = new invoiceDetailView();
		this.dao = new invoiceDetailDAO();
		this.addCustomer = new addCustomer(view);

		/*Customer*/
		actionCancelOnAddPack();
		actionSubmitOnAddPack();
		actionFind();
		actionClearCustomer();
		/* Table */
		actionAdd();
		actionDel();
		actionFirst();
		actionLast();
		actionUp();
		actionDown();
		submitActionAdd();
		actionCancel();
//		actionSearchSales();
//		add();
	}
	private void actionClearCustomer(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				view.clearCustomer();
			}
		};
		view.setClearCustomerAction(action);
	}
	
	private void actionCancel(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				view.setVisible(false);
			}
		};
		view.setCancelAction(action);
	}
	
	private void actionCancelOnAddPack(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				synchronized (view) {
					addCustomer.cancelProcess();
					view.notify();
					view.setEnabled(true);
	                view.toFront();
	                view.repaint();
				}
			}
		};
		WindowAdapter action2 = new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				synchronized (view) {
					addCustomer.cancelProcess();
					view.notify();
					view.setEnabled(true);
	                view.toFront();
	                view.repaint();
				}
			}
		};
		addCustomer.setCancelAction(action);
		addCustomer.setCloseAction(action2);
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
	
	private void actionFirst(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int index = view.getSelectRow();
				int size = dataTable.size();
				if((index>=0)&&(size>0)){
					first(index,size);
//					view.setModel(dataTable);
				}
			}
		};
		view.setFirstAction(action);
	}
	private void actionDown(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int index = view.getSelectRow();
				int size = dataTable.size();
				if((index>=0)&&(size>0)){
					down(index,size);
//					view.setModel(dataTable);
				}	
			}
		};
		view.setDownAction(action);
	}
	private void actionUp(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int index = view.getSelectRow();
				int size = dataTable.size();
				if((index>=0)&&(size>0)){
					up(index,size);
//					view.setModel(dataTable);
				}
			}
		};
		view.setUpAction(action);
	}
	private void actionLast(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int index = view.getSelectRow();
				int size = dataTable.size();
				if((index>=0)&&(size>0)){
					last(index,size);
//					view.setModel(dataTable);
				}
			}
		};
		view.setLastAction(action);
	}
	
	private void actionDel(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int index = view.getSelectRow();
				int size = dataTable.size();
				if((index>=0)&&(size>1)&&(index!=size)){
					dataTable.remove(index);	
					view.setModel(dataTable,invoiceType);
					view.getTable().calculationTable();
					view.setTotalVatFromTable();
				}
			}
		};
		view.setDelAction(action);
	}
	private void actionAdd(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				add();
			}
		};
		view.setAddAction(action);
	}	
	private void actionFind(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				view.setEnabled(false);
				addCustomer.control();
		        Thread worker = new Thread() {
		        	public void run() {
		        		synchronized(view){
		                    while (view.isEnabled()){
		                        try {
		                            view.wait();
		                            if(addCustomer.isVisable()){
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
		view.setFindAction(action);
	}
	

	
	protected void add(){
		rowInvoice newRow = new rowInvoice(invoiceType);
		dataTable.add(newRow);
		view.setModel(dataTable,invoiceType);
	}
	private void first(int index,int size){
		if((index>0)&&(index!=size)){
			rowInvoice temp = new rowInvoice(invoiceType);
			temp = dataTable.get(index);
			dataTable.remove(index);
			dataTable.add(0, temp);
			view.setModel(dataTable,invoiceType);
		}
	}
	private void up(int index,int size){
		if((index>0)&&(index!=size)){
			Collections.swap(dataTable, index, index-1);
			view.setModel(dataTable,invoiceType);
		}
	}
	private void down(int index,int size){
		if((index>=0)&&(index!= dataTable.size()-1)&&(index!=size)){
			Collections.swap(dataTable, index, index+1);
			view.setModel(dataTable,invoiceType);
		}
	}
	private void last(int index,int size){
		if((index>=0)&&(index!=size)){
			rowInvoice temp = new rowInvoice(invoiceType);
			temp = dataTable.get(index);
			dataTable.remove(index);
			dataTable.add(temp);
			view.setModel(dataTable,invoiceType);
		}
	}
	protected boolean checkSubmit(){
		boolean canSubmit = false;
		if(Tools.isNotEmpty(view.getCusName())&&
				Tools.isNotEmpty(view.getSaleName())&&
				Tools.isNotEmpty(view.getDueDate())&&
				Tools.isNotEmpty(view.getIssueDate())){
			canSubmit = true;
		}
		return canSubmit;
	}

	
	protected invoiceData getDataFromView(boolean isNewInvoice){
		invoiceData data = new invoiceData();
		try {
			invoice genNo = new invoice();
			if(isNewInvoice){
				genNo = dao.getNewInoiceNo(invoiceType);
			}else{
				String[] invoice = view.getInvoice().split("\\(");
				genNo = dao.getRevisedInvoiceNo(invoice[0]);
			}
			data.setInvoiceNo(genNo.getInvoiceNo());
			data.setInvoiceType(genNo.getInvoiceType());
			data.setRevised(genNo.getRevised());
//---------------	Remove sales Name	
			data.setCreateUserID(view.getSaleID());
			data.setCreateName(view.getSaleName());
			data.setUpdateUser(view.getSaleID());
//---------------
			data.setCustomerID(view.getCusNo());
			data.setCustomerName(view.getCusName());
			data.setAttn(view.getAttn());
			data.setAddress(view.getAddress());
			data.setTaxID(view.getTax());
			data.setTel(view.getTel());
			data.setEmail(view.getEmail());
			data.setListData(view.getTableData());
			data.setTotalAll(view.getTotall());
			data.setVat(view.getVat());
			data.setDueDate(view.getDueDate());
			data.setDueDay(view.getDueDay());
			data.setIssueDate(view.getIssueDate());
			data.setSubject(view.getSubject());
			data.setRef(view.getRef());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
//	protected void actionSearchSales(){
//		ActionListener action = new ActionListener(){
//			public void actionPerformed(ActionEvent e) {
//				String salesID = view.getSaleID();
//				String salesName = searchSale(salesID) ;
//				if(salesName.equals("")&&salesID.equals("")){
//					JOptionPane.showMessageDialog(null,"Sales id is not found"
//        					,"Error", JOptionPane.ERROR_MESSAGE);
//				}else{
//					view.setSaleID(salesID);
//					view.setSaleName(salesName);
//				}
//			}
//		};
//		view.setSearchSalesAction(action);
//	}
	protected class submitAction extends AbstractAction {
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
		        	insertComplete = false;
		        	if(checkView()){
		        		invoiceData data = getDataFromView(isNewInvoice);
//		        		if(isNewInvoice){
		        			insertComplete = dao.createNewInvoice(data);
//		        		}else{
//		        			insertComplete = dao.revInvoice(data);
//		        		}
		        		if(insertComplete){
		        			if(view.getPrintType()<2){
		        				createXlsInvoice(view.getPrintType(),data);
		        			}else{
		        				JOptionPane.showMessageDialog(null,"Please Select Invoice Type only and"
		        						+ "click print again for create Invoice"
			        					,"Error", JOptionPane.ERROR_MESSAGE);
		        			}
		        		}else{
		        			JOptionPane.showMessageDialog(null,"Create Invoice is not Complete"
		        					,"Error", JOptionPane.ERROR_MESSAGE);
		        		}
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
	private void submitActionAdd(){
		view.setSubmitAction(new submitAction());
	}	
	
	protected boolean checkView(){
		boolean isOkay = false;
		if(Tools.isNotEmpty(view.getSaleName())
				&&Tools.isNotEmpty(view.getCusName())){
			if(view.checkIssDue()){
				if(view.checkTable()){
					isOkay = true;
				}else{
					JOptionPane.showMessageDialog(null,"Service Name is not empty"
							,"Error", JOptionPane.ERROR_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null,"Issue Date must before than"
						+ "Due Date.","Error", JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null,"Please enter sales name or"
					+ "customer name","Error", JOptionPane.ERROR_MESSAGE);
		}
		return isOkay;
	}
	protected boolean createXlsInvoice (int printType ,invoiceData data){
		boolean createXls = false;
		String fileName = getTemplateName(printType, data.getInvoiceType());
		printInvoice print = new printInvoice(fileName,printType,!Tools.isEmptyOrZero(data.getVat()));
		if(printType==EN_TYPE){
			try {
				data = dao.tranThaiToEng(data);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Cannot transfer Thai to Eng"
						,"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(print.printXlsVer2(data)){
//		if(print.printXls(data)){
			String invoiceFull = Tools.createFullInvoice(data.getInvoiceType(), 
					data.getInvoiceNo(), data.getRevised());
			int result = print.saveFile(invoiceFull);
//			int result = print.saveFile("invoice",invoiceFull);
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
	protected boolean createXlsPayment (String printType ,invoiceData data,paymentData payment){
		boolean createXls = false;
		printPayment print = new printPayment(printType);
		if(print.printXls(data,payment)){
//			String invoiceFull = Tools.createFullInvoice(data.getInvoiceType(), 
//					data.getInvoiceNo(), data.getRevised());
			int result = print.saveFile("payment_"+payment.getPaymentNo());
//			int result = print.saveFile("payment",invoiceFull);
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
	
	/*
	 *  0 = thai
	 *  1 = eng
	 */
	private String getTemplateName(int langauge,String type){
		StringBuilder name = new StringBuilder();
		name.append("invoice_");
		if(type.equals("RT")){
			name.append("tour");
		}else if(type.equals("RTS")){
			name.append("tran");
		}
		if(langauge==EN_TYPE){
			name.append("EN");
		}else{
			name.append("TH");
		}
		return name.toString();
	}
	protected String searchSale(){
		String salesID = view.getSaleID();
		String salesName = view.getSaleName();
		try {
			if(!salesID.equals("")){
				salesName = dao.getSalesName(salesID);
			}else if(!salesName.equals("")){
				ArrayList<String> temp = dao.getSalesData(salesName);
				salesID = temp.get(0);
				salesName = temp.get(1);
			}
			if(salesName.equals("")&&salesID.equals("")){
				JOptionPane.showMessageDialog(null,"Sales id is not found"
    					,"Error", JOptionPane.ERROR_MESSAGE);
			}else{
				view.setSaleID(salesID);
				view.setSaleName(salesName);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return salesName;
	}
	
	protected String searchSale(String salesID){
		String salesName = view.getSaleName();
		try {
			if(!salesID.equals("")){
				salesName = dao.getSalesName(salesID);
			}else if(!salesName.equals("")){
				ArrayList<String> temp = dao.getSalesData(salesName);
				salesID = temp.get(0);
				salesName = temp.get(1);
			}
			if(salesName.equals("")&&salesID.equals("")){
				JOptionPane.showMessageDialog(null,"Sales id is not found"
    					,"Error", JOptionPane.ERROR_MESSAGE);
			}else{
				view.setSaleID(salesID);
				view.setSaleName(salesName);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return salesName;
	}
}
