package tour.tourMain;

import invoice.invoiceDetail.invoiceDetailInsert;
import invoice.search.invoiceSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.View;

import payment.detail.payment;
import payment.search.paymentSearch;

import com.base.Tools;

import tour.tourListSearch.listSearch;
import tourlist.createTourList.createTour;
import tourlist.createTourList.createTourView;

public class subAccountMain {

	private tourMainView mainView;
	private listSearch searchPack;
	private createTour listPack;
	private invoiceSearch invoiceSearchPack;
	private invoiceDetailInsert createInvoicePack;
	private paymentSearch paymentSearchPack;
	private payment nonInvoicePack;
	private boolean CAN_PAYMENT = true;
	private boolean MAIN_ACCOUNT = false;
	
	/*
	 *  false = normal user
	 *  true = Account user
	 */
	public subAccountMain(boolean isAccountUser){
		CAN_PAYMENT = isAccountUser;
		this.mainView = new tourMainView(CAN_PAYMENT);
		this.mainView.setVisible(true);	
		setActionTourList();
		setActionUser();
		setActionInvoiceRTS();
		setActionInvoiceRT();
		setActionSearchTourInvoice();
		setActionSearchTranInvoice();
		setActionSearchPayment();
	}
	private void setActionSearchPayment() {
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(Tools.isEmpty(paymentSearchPack)){
					paymentSearchPack = new paymentSearch(MAIN_ACCOUNT);
				}
				paymentSearchPack.control();
			}
		};
		mainView.setActionSearchPayment(action);
	}
	private void setActionSearchTourInvoice(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(Tools.isEmpty(listPack)){
					invoiceSearchPack = new  invoiceSearch(CAN_PAYMENT,MAIN_ACCOUNT);
				}
				invoiceSearchPack.control(true);
			}
		};
		mainView.setActionSearchTourInvoice(action);
	}
	
	private void setActionSearchTranInvoice(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(Tools.isEmpty(listPack)){
					invoiceSearchPack = new  invoiceSearch(CAN_PAYMENT,MAIN_ACCOUNT);
				}
				invoiceSearchPack.control(false);
			}
		};
		mainView.setActionSearchTranInvoice(action);
	}
	
	private void setActionTourList(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(Tools.isEmpty(listPack)){
					listPack = new createTour();
				}
				listPack.control();
			}
		};
		mainView.setActionTourList(action);
	}
	private void setActionUser(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {				
				if(Tools.isEmpty(searchPack)){
					searchPack = new listSearch();
				}
				searchPack.control();
			}
		};
		mainView.setActionManagement(action);
	}
	private void setActionInvoiceRT(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(Tools.isEmpty(createInvoicePack)){
					createInvoicePack = new invoiceDetailInsert();
				}
				createInvoicePack.control("RT");				
			}
		};
		mainView.setActionRT(action);
	}
	private void setActionInvoiceRTS(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(Tools.isEmpty(createInvoicePack)){
					createInvoicePack = new invoiceDetailInsert();
				}
				createInvoicePack.control("RTS");				
			}
		};
		mainView.setActionRTS(action);
	}
	public void control(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(Tools.isEmpty(nonInvoicePack)){
					nonInvoicePack = new payment(mainView,MAIN_ACCOUNT);
				}
				nonInvoicePack.control();
			}
		};
		mainView.setActionPaymentInvoice(action);
	}
}
