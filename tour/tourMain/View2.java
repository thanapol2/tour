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
import com.tourSearch.tourSearch;
import com.tourSearch.tourSearchView;

import tour.tourListSearch.listSearch;
import tour.tourListSearch.listSearchView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class View2 extends JFrame {

	private JPanel contentPane;
//	private JButton managementBtn;
	private JButton tourListBtn;
	private JButton rtInvoiceBtn;
	private JButton rtsInvoiceBtn;
	private JButton invoiceTourSearchBtn;
	private JButton paymentBtn;
	private JButton invoiceTranSearchBtn;
	private JButton paymentSearchBtn;
	private JPanel panel_2;
	private JLabel userName;
	private JLabel lblUserId;
	private JLabel userID;
	private JPanel panel_3;
	private JButton btnNewTour;
	private JButton btnNewCompany;
	private JButton exportAllBtn;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public View2(boolean isAccountUser,String id,String aName) {
		
		Image searchImg = new ImageIcon(icon.getPicPath("search.png")).getImage();
		searchImg = searchImg.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH);
		Image addImg = new ImageIcon(icon.getPicPath("add.png")).getImage();
		addImg = addImg.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		Image printImg = new ImageIcon(icon.getPicPath("print.png")).getImage();
		printImg = printImg.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1035, 750);
		contentPane = new JPanel();

		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

//		managementBtn = new JButton("<html><center>Tour Member <br>Managment </center></html>");
//		managementBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
//		managementBtn.setIcon(new ImageIcon(icon.getPicPath("Manager.png")));
//		managementBtn.setBounds(20, 566, 183, 75);
//		contentPane.add(managementBtn);
		
		tourListBtn = new JButton("<html><center>tour List<br>Creation </center></html>");
		tourListBtn.setEnabled(false);
		tourListBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		tourListBtn.setIcon(new ImageIcon(icon.getPicPath("Invoice.png")));
		tourListBtn.setBounds(20, 570, 183, 75);
		contentPane.add(tourListBtn);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Invoice", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 80, 208, 300);
		contentPane.add(panel);
		panel.setLayout(null);
		
		rtInvoiceBtn = new JButton("<html><center>Roongroj Tour<br>Invoice <br>Creation </center></html>");
		rtInvoiceBtn.setBounds(10, 30, 183, 56);
		panel.add(rtInvoiceBtn);
		rtInvoiceBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		rtInvoiceBtn.setIcon(new ImageIcon(icon.getPicPath("Invoice.png")));
		
		rtsInvoiceBtn = new JButton("<html><center>Roongroj Tran<br>Invoice<br>Creation </center></html>");
		rtsInvoiceBtn.setBounds(10, 97, 183, 56);
		panel.add(rtsInvoiceBtn);
		rtsInvoiceBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		rtsInvoiceBtn.setIcon(new ImageIcon(icon.getPicPath("Invoice.png")));
		
		invoiceTourSearchBtn = new JButton("<html><center>Searching<br>Roongroj <br>Tour Invoice</center></html>");
		invoiceTourSearchBtn.setBounds(10, 164, 183, 56);
		panel.add(invoiceTourSearchBtn);
		invoiceTourSearchBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		invoiceTourSearchBtn.setIcon(new ImageIcon(searchImg));
		
		invoiceTranSearchBtn = new JButton("<html><center>Searching<br>Roongroj <br>Tran Invoice</center></html>");
		invoiceTranSearchBtn.setBounds(10, 231, 183, 56);
		panel.add(invoiceTranSearchBtn);
		invoiceTranSearchBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		invoiceTranSearchBtn.setIcon(new ImageIcon(searchImg));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Payment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 388, 208, 171);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		paymentBtn = new JButton("<html><center>Payment<br>Creation</center></html>");
		paymentBtn.setBounds(10, 29, 183, 56);
		panel_1.add(paymentBtn);
		paymentBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		paymentBtn.setIcon(new ImageIcon(icon.getPicPath("Invoice.png")));
		
		paymentSearchBtn = new JButton("<html><center>Searching<br>Payment</center></html>");
		paymentSearchBtn.setBounds(10, 96, 183, 56);
		panel_1.add(paymentSearchBtn);
		paymentSearchBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		paymentSearchBtn.setIcon(new ImageIcon(searchImg));
		
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 21, 1000, 49);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(659, 11, 55, 27);
		panel_2.add(lblNewLabel);
		
		userName = new JLabel(aName);
		userName.setFont(new Font("Tahoma", Font.BOLD, 14));
		userName.setBounds(724, 11, 255, 27);
		panel_2.add(userName);
		
		lblUserId = new JLabel("User ID:");
		lblUserId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUserId.setBounds(465, 11, 68, 27);
		panel_2.add(lblUserId);
		
		userID = new JLabel(id);
		userID.setFont(new Font("Tahoma", Font.BOLD, 14));
		userID.setBounds(543, 11, 106, 27);
		panel_2.add(userID);
		

		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Customer Management", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(228, 80, 784, 580);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		tourSearch tourSearch = new tourSearch();
		tourSearchView searchList = tourSearch.getView();	
		searchList.setBounds(5, 100, 740,470);
		searchList.setLayout(null);
		panel_3.add(searchList);
		
		btnNewTour = new JButton("<html><center>Add new <br>Tourmember </center></html>");
		btnNewTour.setBounds(10, 30, 126, 55);
		panel_3.add(btnNewTour);
		btnNewTour.setIcon(new ImageIcon(addImg));
		btnNewTour.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnNewCompany = new JButton("<html><center>Add new <br>Company </center></html>");
		btnNewCompany.setBounds(151, 30, 126, 55);
		panel_3.add(btnNewCompany);
		btnNewCompany.setIcon(new ImageIcon(addImg));
		btnNewCompany.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		exportAllBtn = new JButton("<html><center>Export Address<br>All Customer</center></html>");
		exportAllBtn.setIcon(new ImageIcon(printImg));
		exportAllBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		exportAllBtn.setBounds(287, 30, 167, 55);
		panel_3.add(exportAllBtn);
		
		setEnableIsAccount(isAccountUser);
	}

	private void setEnableIsAccount(boolean isAccountUser){
		paymentBtn.setEnabled(isAccountUser);
		paymentSearchBtn.setEnabled(isAccountUser);
	}

//	public void setActionManagement(ActionListener action){
//		this.managementBtn.addActionListener(action);
//	}
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
	public void setActionExportAddress(ActionListener action){
		this.exportAllBtn.addActionListener(action);
	}
	public void setActionPaymentInvoice(ActionListener action){
		this.paymentBtn.addActionListener(action);
	}
	public void setTourListBtnEnable(boolean result){
		tourListBtn.setEnabled(result);
	}
	
//	public void setManagementBtnEnable(boolean result){
//		managementBtn.setEnabled(result);
//	}
	
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

	public String getUserName() {
		return userName.getText();
	}

	public void setUserName(String userName) {
		this.userName.setText(userName);
	}
	
	public void addActionNewTour(ActionListener action) {
		btnNewTour.addActionListener(action);
	}

	public void addActionNewCompany(ActionListener action) {
		btnNewCompany.addActionListener(action);
	}
}
