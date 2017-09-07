package temp;

import java.awt.EventQueue;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import payment.detail.paymentDAO;
import payment.detail.paymentView;
import payment.detail.searchPaymentData;
import tour.tourGUI.AccountUserRun;
import tour.tourMain.tourMain;

import com.base.Tools;
import com.login.login;

import invoice.invoiceDetail.invoiceDetailDAO;
import invoice.invoiceXLS.createXls;
import invoice.invoiceXLS.printInvoice;

public class testXls{
	private login frame;

	public static void main(String[] args) {
		System.out.println(Tools.isEmpty(""));
	}


	/**
	 * Create the application.
	 */
	public testXls() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new listSearch();
//		frame = new listDetailInsert();
		frame = new login();
//		frame = new companyDetail();
//		frame = new listDetailEdit();
		
	}
}