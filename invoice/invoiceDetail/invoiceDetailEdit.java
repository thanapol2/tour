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

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import payment.detail.paymentView;
import payment.detail.paymentData;

import com.base.Tools;
import com.ui.loadingPanel;
import com.selectAddList.listAddSelect;
import com.table.rowMember;


public class invoiceDetailEdit extends invoiceDetail{
	private invoiceData data;
	private paymentView choosePayment;
//	private listAddSelect listAddSelect;
	ArrayList<String> memIDCalList;
	ArrayList<String> memNameCalList;
	
	
	// CASE PAYMENT FROM INVOCIE
	public invoiceDetailEdit(boolean canPayment) {
		super();
//		this.view = new invoiceDetailView();
//		this.dao = new invoiceDetailDAO();
		this.data = new invoiceData();
		this.isNewInvoice = false;
		this.choosePayment = new paymentView(view);
//		this.listAddSelect = new listAddSelect();
		this.memIDCalList = new ArrayList<String>();
		this.memNameCalList = new ArrayList<String>();

		view.editCase();
		setPaymentAction();
		setRevisedAction();
		setPaymentSubmitAction();
		
		actionCancelOnPayment();
		setPrintAction();
		
		CAN_PAYMENT = canPayment;
		choosePayment.setPrintEnable(false);
// Set Calpoint
		setEditListCalPoint();
		setSumbitListCalPointAction();
	}
	
	public void control(String invoiceNo,String rev){
//		view.pack();
		view.setVisible(true);
		view.clearAll();
		if(invoiceNo.contains("RTS")){
			this.invoiceType = "RTS";
		}else{
			this.invoiceType = "RT";
		}
		try {
			this.data = dao.searchInvoiceData(invoiceNo, rev);
			view.setInvoiceAllData(data);
			int isPayment = dao.checkPayment(invoiceNo,rev);
			
			// set Payment Button
			view.setPaymentBtnEnable(CAN_PAYMENT);
			
			// setRevised
			if(isPayment == 0){ // invoice is not Payment
				view.setRevisedBtnEnable(true);
			}else if(isPayment==1){   // Other invoice is Payment
				view.setPaymentEnable(false);
			}else{
				view.setRevisedBtnEnable(false);
			}
			this.dataTable = data.getListData();
			memIDCalList.add(data.getCustomerID());
			memNameCalList.add(data.getCustomerName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void setRevisedAction(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				view.revisionCase();
				view.setIssueDate(Tools.getCurrentDate());
				if(data.getCustomerID().contains("c")){
					view.disableCusEdit(false);
				}else{
					view.disableCusEdit(true);
				}
//				addInsertAction();
			}
		};
		view.setRevisedAction(action);
	}
	

	private void setPaymentAction(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				view.setEnabled(false);
				choosePayment.setVisible(true);
				String salesId = Tools.getConfig("sales_id");
				String salesName = "";
				try {
					salesName = dao.getSalesName(salesId);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				choosePayment.setDetail(data,salesId,salesName);
				// Set Calculation Point
				
			}
		};
		view.setPaymentAction(action);
	}

	
	private void setPaymentSubmitAction(){
		choosePayment.setSubmitAction(new paymentAction());
//		
	}
	
	private void setPrintAction(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int printType = view.getPrintType();
				if(printType<2){
					createXlsInvoice(printType,data);
				}else{
//					view.getInvoice();
//					String printType = getFileName(view.hasVat());
//					String invoiceWithRev = view.getInvoice();
//					try {
//						paymentData payment = dao.getPayment(invoiceWithRev);
//						if(payment.getPaymentType().equals("N")){
//							JOptionPane.showMessageDialog(null,"Invice is not payment!!!!"
//		        					,"Error", JOptionPane.ERROR_MESSAGE);
//						}else{
//							createXlsPayment(printType,data,payment);
//						}
//					} catch (SQLException e1) {
//						JOptionPane.showMessageDialog(null,"Cannot connect to database"
//	        					,"Error", JOptionPane.ERROR_MESSAGE);
//					}
				}
			}
		};
		view.setPrintAction(action);
	}
	
	private void actionCancelOnPayment(){
		WindowAdapter action2 = new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				synchronized (view) {
					choosePayment.setVisible(false);
					view.notify();
					view.setEnabled(true);
	                view.toFront();
	                view.repaint();
				}
			}
		};
//		choosePayment.setCancelAction(action);
		choosePayment.setCloseAction(action2);
	}


	
	protected class paymentAction extends AbstractAction {
//		boolean paymentComplete = false;
		public paymentAction() {
			super();
		}
		   @Override
		public void actionPerformed(ActionEvent evt) {
			   SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
				   @Override
				   protected Void doInBackground() throws Exception {
					   // mimic some long-running process here...
					   view.setEnabled(false);
//					   paymentComplete = false;
					   if(choosePayment.checkCanpayment()&&choosePayment.checkChequeDetail()){
							String invoiceNo = data.getInvoiceNo();
							String invoiceType = data.getInvoiceType();
							String rev = data.getRevised();
							paymentData paymentData = choosePayment.getPaymentData();
//							paymentData.setDetail(createPaymentDetail(invoiceType,invoiceNo,rev));
							try {
								if(dao.updatePayment(invoiceType,invoiceNo, rev,paymentData,memIDCalList)){
									JOptionPane.showMessageDialog(null,"Updating Invoice is Complete");
									paymentData.setPaymentNo(dao.getPaymentNo(invoiceNo,invoiceType,rev));
									String printType = getFileName(view.hasVat());
									createXlsPayment(printType,data,paymentData);
									choosePayment.setVisible(false);
									view.setPaymentEnable(false);
									view.setEnabled(true);
									view.setAlwaysOnTop(true);
								}
							} catch (SQLException e1) {
			        			JOptionPane.showMessageDialog(null,"Updating Invoice is not Complete"
			        					,"Error", JOptionPane.ERROR_MESSAGE);
							}
						}else if(choosePayment.checkChequeDetail()){
							JOptionPane.showMessageDialog(null,"Please select the payment type Or Invaild Payment Date"
		        					,"Error", JOptionPane.ERROR_MESSAGE);
						}else if(choosePayment.checkCanpayment()){
							JOptionPane.showMessageDialog(null,"Invaild Cheque Detail, Please Check again"
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
//	                  view.setVisible(!paymentComplete);
	               }
	            }
	         }
	      });
	      mySwingWorker.execute();
	      dialog.setLocationRelativeTo(win);    
	   }
	}
	private rowMember setMemCalDefault(){
		rowMember data = new rowMember(this.data.getCustomerID(),this.data.getCustomerName());
		return data;
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
	
	private void setEditListCalPoint(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				memIDCalList.add(data.getCustomerID());
				memNameCalList.add(data.getCustomerName());
				ArrayList<rowMember> defaultList = new ArrayList<rowMember>();
				defaultList.add(setMemCalDefault());
				choosePayment.controlListAdd(defaultList);
			}
		};
		choosePayment.setEdiListCalAction(action);
	}
	
	private void setSumbitListCalPointAction(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				memIDCalList = new ArrayList<String>();
				memNameCalList = new ArrayList<String>();
				memIDCalList = choosePayment.getMemIDCalList();
				memNameCalList = choosePayment.getMemNameCalList();
				memIDCalList.add(0,data.getCustomerID());
				memNameCalList.add(0,data.getCustomerName());
				choosePayment.replaceAllCalList(memNameCalList);
				choosePayment.setAddCalListVisible(false);
				
			}
		};
		choosePayment.setSumbitAction(action);
	}
}
