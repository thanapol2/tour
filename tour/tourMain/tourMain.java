package tour.tourMain;

import invoice.invoiceDetail.invoiceDetailInsert;
import invoice.search.invoiceSearch;
import main.export.export;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.View;

import payment.detail.payment;
import payment.search.paymentSearch;

import com.base.ConnectionPool;
import com.base.Tools;

import company.companyDetail.companyDetailEdit;
import company.companyDetail.companyDetailInsert;
import tour.tourListDetail.listDetailEdit;
import tour.tourListDetail.listDetailInsert;
import tour.tourListSearch.listSearch;
import tourlist.createTourList.createTour;
import tourlist.createTourList.createTourView;

public class tourMain {

	private View2 mainView;
//	private tourMainView mainView;
//	private listSearch searchPack;
	private createTour listPack;
	private invoiceSearch invoiceSearchPack;
	private invoiceDetailInsert createInvoicePack;
	private paymentSearch paymentSearchPack;
	private payment nonInvoicePack;
	private boolean CAN_PAYMENT = false;
	private boolean MAIN_ACCOUNT = false;
	
	private listDetailInsert newTourPack;
	private companyDetailInsert newComPack;
	private export export;
	
	/*
	 *  false = normal user
	 *  true = Account user
	 */
	public tourMain(boolean isAccountUser){
		CAN_PAYMENT = isAccountUser;
		MAIN_ACCOUNT = !ConnectionPool.isSubAccount(); 
		this.mainView = new View2(CAN_PAYMENT,ConnectionPool.getUserID(),ConnectionPool.getName());
//		this.mainView = new tourMainView(CAN_PAYMENT);
		this.mainView.setVisible(true);	
		setActionTourList();
//		setActionUser();
		setActionInvoiceRTS();
		setActionInvoiceRT();
		setActionSearchTourInvoice();
		setActionSearchTranInvoice();
		setActionSearchPayment();
		setActionExportAddress();
		addcustomerAction();
	}
	private void setActionExportAddress() {
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(Tools.isEmpty(export)){
					export = new export();
				}
				export.createXlsExport();
			}
		};
		mainView.setActionExportAddress(action);
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
//					invoiceSearchPack = new  invoiceSearch(CAN_PAYMENT,ISNOT_ONLY_VAT);
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
//					invoiceSearchPack = new  invoiceSearch(CAN_PAYMENT,ISNOT_ONLY_VAT);
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
//	private void setActionUser(){
//		ActionListener action = new ActionListener(){
//			public void actionPerformed(ActionEvent e) {				
//				if(Tools.isEmpty(searchPack)){
//					searchPack = new listSearch();
//				}
//				searchPack.control();
//			}
//		};
//		mainView.setActionManagement(action);
//	}
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
	
	private void addcustomerAction(){
		
		ActionListener newTourAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Tools.isEmpty(newTourPack)){
					newTourPack = new listDetailInsert();
				}
				newTourPack.control();
			}
		};
		mainView.addActionNewTour(newTourAction);	
		
		ActionListener newComAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Tools.isEmpty(newComPack)){
					newComPack = new companyDetailInsert();
				}
				newComPack.control();
			}
		};
		mainView.addActionNewCompany(newComAction);	
		
//		ActionListener cancelAction = new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				view.setVisible(false);
//			}
//		};
//		view.getBtnCancel().addActionListener(cancelAction);	
	}
}
