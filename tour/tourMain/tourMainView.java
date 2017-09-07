package tour.tourMain;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;

import com.base.Tools;
import com.base.icon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JLabel;

public class tourMainView extends JFrame {

	private JPanel contentPane;
	private JButton managementBtn;
	private JButton tourListBtn;
	private JButton rtInvoiceBtn;
	private JButton rtsInvoiceBtn;
	private JButton invoiceTourSearchBtn;
	private JButton paymentBtn;
	private JButton invoiceTranSearchBtn;
	private JButton paymentSearchBtn;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public tourMainView(boolean isAccountUser) {
		
		Image searchImg = new ImageIcon(icon.getPicPath("search.png")).getImage();
		searchImg = searchImg.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 473);
		contentPane = new JPanel();

		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		managementBtn = new JButton("<html><center>Tour Member <br>Managment </center></html>");
		managementBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		managementBtn.setIcon(new ImageIcon(icon.getPicPath("Manager.png")));
		managementBtn.setBounds(10, 11, 183, 75);
		contentPane.add(managementBtn);
		
		tourListBtn = new JButton("<html><center>tour List<br>Creation </center></html>");
		tourListBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		tourListBtn.setIcon(new ImageIcon(icon.getPicPath("Invoice.png")));
		tourListBtn.setBounds(227, 11, 183, 75);
		contentPane.add(tourListBtn);
		
		rtInvoiceBtn = new JButton("<html><center>Roongroj Tour<br>Invoice <br>Creation </center></html>");
		rtInvoiceBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		rtInvoiceBtn.setIcon(new ImageIcon(icon.getPicPath("Invoice.png")));
		rtInvoiceBtn.setBounds(10, 114, 183, 75);
		contentPane.add(rtInvoiceBtn);
		
		rtsInvoiceBtn = new JButton("<html><center>Roongroj Tran<br>Invoice<br>Creation </center></html>");
		rtsInvoiceBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		rtsInvoiceBtn.setIcon(new ImageIcon(icon.getPicPath("Invoice.png")));
		rtsInvoiceBtn.setBounds(10, 217, 183, 75);
		contentPane.add(rtsInvoiceBtn);
		
		invoiceTourSearchBtn = new JButton("<html><center>Searching<br>Roongroj Tour Invoice</center></html>");
		invoiceTourSearchBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		invoiceTourSearchBtn.setBounds(227, 114, 183, 75);
		invoiceTourSearchBtn.setIcon(new ImageIcon(searchImg));
		contentPane.add(invoiceTourSearchBtn);
		
		paymentBtn = new JButton("<html><center>Payment<br>Creation</center></html>");
		paymentBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		paymentBtn.setIcon(new ImageIcon(icon.getPicPath("Invoice.png")));
		paymentBtn.setBounds(10, 320, 183, 75);
		contentPane.add(paymentBtn);
		
		invoiceTranSearchBtn = new JButton("<html><center>Searching<br>Roongroj Tran Invoice</center></html>");
		invoiceTranSearchBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		invoiceTranSearchBtn.setBounds(227, 217, 183, 75);
		invoiceTranSearchBtn.setIcon(new ImageIcon(searchImg));
		contentPane.add(invoiceTranSearchBtn);
		
		paymentSearchBtn = new JButton("<html><center>Searching<br>Payment</center></html>");
		paymentSearchBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		paymentSearchBtn.setBounds(227, 320, 183, 75);
		paymentSearchBtn.setIcon(new ImageIcon(searchImg));
		contentPane.add(paymentSearchBtn);
		
		setEnableIsAccount(isAccountUser);
	}

	private void setEnableIsAccount(boolean isAccountUser){
		paymentBtn.setEnabled(isAccountUser);
		paymentSearchBtn.setEnabled(isAccountUser);
	}

	public void setActionManagement(ActionListener action){
		this.managementBtn.addActionListener(action);
	}
	public void setActionTourList(ActionListener action){
		this.tourListBtn.addActionListener(action);
	}
	public void setActionRT(ActionListener action){
		this.rtInvoiceBtn.addActionListener(action);
	}
	public void setActionRTS(ActionListener action){
		this.rtsInvoiceBtn.addActionListener(action);
	}
	public void setActionSearchTourInvoice(ActionListener action){
		this.invoiceTourSearchBtn.addActionListener(action);
	}
	public void setActionSearchTranInvoice(ActionListener action){
		this.invoiceTranSearchBtn.addActionListener(action);
	}
	public void setActionSearchPayment(ActionListener action){
		this.paymentSearchBtn.addActionListener(action);
	}
	public void setActionPaymentInvoice(ActionListener action){
		this.paymentBtn.addActionListener(action);
	}
	public void setTourListBtnEnable(boolean result){
		tourListBtn.setEnabled(result);
	}
	
	public void setManagementBtnEnable(boolean result){
		managementBtn.setEnabled(result);
	}
	
	public void setRtInvoiceBtnEnable(boolean result){
		rtInvoiceBtn.setEnabled(result);
	}
	public void setRtsInvoiceBtnEnable(boolean result){
		rtsInvoiceBtn.setEnabled(result);
	}
	
	public void setInvoiceTourSearchBtnEnable(boolean result){
		invoiceTourSearchBtn.setEnabled(result);
	}
	
	public void setInvoiceTranSearchBtnEnable(boolean result){
		invoiceTranSearchBtn.setEnabled(result);
	}

}
