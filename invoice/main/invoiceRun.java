package invoice.main;

import invoice.invoiceDetail.invoiceDetailInsert;
import invoice.search.invoiceSearch;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import payment.detail.payment;
import payment.detail.paymentView;

import com.base.Tools;
import com.ui.loadingPanel;

public class invoiceRun {

//	private listSearch frame;
//	private listDetailInsert frame;
//	private invoiceDetailInsert frame;
	private payment frame;
//	private  paymentView frame;
//	private companyDetail frame;
//	private listDetailEdit frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					loadingPanel a = new loadingPanel();
					invoiceRun window = new invoiceRun();
//					paymentView window = new paymentView();
					
					window.frame.control();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public invoiceRun() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new listSearch();
//		frame = new listDetailInsert();
//		System.out.print(Tools.isEmpty(null));
		frame = new payment();
//		frame = new companyDetail();
//		frame = new listDetailEdit();
//		frame = new  paymentView();
	}

}

