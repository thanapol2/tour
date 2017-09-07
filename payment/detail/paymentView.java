package payment.detail;

import invoice.addCustomer.invoiceCustomData;
import invoice.invoiceDetail.invoiceData;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import com.base.Tools;
import com.data.payment;
import com.extentclass.doubleDocument;
import com.extentclass.numberJTextField;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListModel;
import com.selectAddList.listAddSelect;
import com.table.rowMember;

public class paymentView extends JDialog {
	private JTextField chequeNo;
	private JComboBox paymentBox;
	private JComboBox bankBox;
	private JButton submitBtn;
	private JButton cancelBtn;
	private JButton searchTourBtn;
	private JButton searchSaleBtn;
	private payment list;
	private JComboBox payDay;
	private JComboBox payMon;
	private JComboBox payYear;	
	private JComboBox chequeDay;
	private JComboBox chequeMon;
	private JComboBox chequeYear;
	private JTextField cusNo;
	private JTextField address;
	private JTextField detail;
	private JLabel label_8;
	private JTextField saleName;
	private JTextField cusName;
	private numberJTextField saleID;
	private JButton clearHeadBtn;
	private JTextField amount;
	private JRadioButton vatBox;
	private JRadioButton nonVatBox;
	private float tax;
	private JList<String> tourList;
	private DefaultListModel<String> model;
	private JButton printBtn;
	private JTextField vat;
	private JCheckBox autoVat;
	private JButton editCustomBtn;
	private listAddSelect listSelect;
	
	/**
	 * @wbp.parser.constructor
	 */
	public paymentView(final JFrame parent) {
		super(parent);
		createview();
		addWindowListener(new WindowAdapter() 
		{
		  public void windowClosing(WindowEvent e)
		  {
			  parent.setEnabled(true);
		  }
		});
		setCancelAction(parent);
		
	}
	
	// Create payment for payment btn invoice page
	public paymentView(final JDialog parent) {
		super(parent);
		createview();
		listSelect = new listAddSelect();
		addWindowListener(new WindowAdapter() 
		{
		  public void windowClosing(WindowEvent e)
		  {
			  listSelect.setVisibleView(false);
			  parent.setEnabled(true);
		  }
		});
		setCancelAction(parent);
		
	}
	private void createview(){
		tax = Float.parseFloat(Tools.getConfig("tour_vat"))/100;
		
		
		String[] bankList = Tools.getConstant("102").split(",");
		list = Tools.getPayment();
		String[] paymentList = list.name.toArray(new String[list.name.size()]);
		String[] Tday={"Day","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		String[] Tmon = {"Mon","1","2","3","4","5","6","7","8","9","10","11","12"};
		String tempYear = Integer.toString(Calendar.getInstance().get(Calendar.YEAR)-1);
		String[] Tyear  = Tools.genYear(10,tempYear);

		
		setBounds(100, 100, 1011, 577);
		getContentPane().setLayout(null);
		
		submitBtn = new JButton("submit");
		submitBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		submitBtn.setBounds(424, 495, 120, 36);
		getContentPane().add(submitBtn);
//		setComboxAction();
		
		JLabel label_5 = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E1C\u0E39\u0E49\u0E08\u0E48\u0E32\u0E22  : ");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_5.setBounds(10, 50, 93, 30);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("\u0E17\u0E35\u0E48\u0E2D\u0E22\u0E39\u0E48        : ");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_6.setBounds(10, 131, 93, 30);
		getContentPane().add(label_6);
		
		cusNo = new JTextField();
		cusNo.setEnabled(false);
		cusNo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cusNo.setText("0");
		cusNo.setColumns(10);
		cusNo.setBounds(130, 52, 212, 30);
		getContentPane().add(cusNo);
		
		address = new JTextField();
		address.setFont(new Font("Tahoma", Font.PLAIN, 13));
		address.setColumns(10);
		address.setBounds(130, 131, 533, 30);
		getContentPane().add(address);
		
		searchTourBtn = new JButton("Search Customer");
		searchTourBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		searchTourBtn.setBounds(352, 52, 171, 30);
		getContentPane().add(searchTourBtn);
		
		label_8 = new JLabel("\u0E1C\u0E39\u0E49\u0E2D\u0E2D\u0E01\u0E43\u0E1A   : ");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_8.setBounds(10, 11, 93, 30);
		getContentPane().add(label_8);
		
		saleName = new JTextField();
		saleName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		saleName.setEnabled(false);
		saleName.setColumns(10);
		saleName.setBounds(252, 11, 281, 30);
		getContentPane().add(saleName);
		
		searchSaleBtn = new JButton("Search Sale");
		searchSaleBtn.setEnabled(false);
		searchSaleBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		searchSaleBtn.setBounds(543, 11, 120, 30);
		getContentPane().add(searchSaleBtn);
		
		clearHeadBtn = new JButton("Clear Head");
		clearHeadBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		clearHeadBtn.setBounds(543, 52, 120, 30);
		getContentPane().add(clearHeadBtn);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Payment Detail", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 172, 658, 312);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_3 = new JLabel("\u0E25\u0E07\u0E27\u0E31\u0E19\u0E17\u0E35\u0E48 : ");
		label_3.setBounds(10, 270, 110, 30);
		panel.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		chequeDay = new JComboBox(Tday);
		chequeDay.setBounds(119, 270, 67, 30);
		panel.add(chequeDay);
		chequeDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chequeMon = new JComboBox(Tmon);
		chequeMon.setBounds(196, 270, 73, 30);
		panel.add(chequeMon);
		chequeMon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chequeYear = new JComboBox(Tyear);
		chequeYear.setBounds(279, 270, 86, 30);
		panel.add(chequeYear);
		chequeYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel label_1 = new JLabel("\u0E40\u0E0A\u0E47\u0E04\u0E18\u0E19\u0E32\u0E04\u0E32\u0E23 : ");
		label_1.setBounds(10, 229, 110, 30);
		panel.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		bankBox = new JComboBox(bankList);
		bankBox.setBounds(119, 229, 189, 30);
		panel.add(bankBox);
		bankBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel label_2 = new JLabel("\u0E40\u0E25\u0E02\u0E17\u0E35\u0E48\u0E40\u0E0A\u0E47\u0E04 : ");
		label_2.setBounds(318, 229, 86, 30);
		panel.add(label_2);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		chequeNo = new JTextField();
		chequeNo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chequeNo.setBounds(414, 231, 189, 30);
		panel.add(chequeNo);
		chequeNo.setColumns(10);
		
		JLabel label_4 = new JLabel("\u0E27\u0E31\u0E19\u0E17\u0E35\u0E48\u0E08\u0E48\u0E32\u0E22\u0E40\u0E07\u0E34\u0E19 : ");
		label_4.setBounds(10, 188, 110, 30);
		panel.add(label_4);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		payDay = new JComboBox(Tday);
		payDay.setBounds(119, 188, 67, 30);
		panel.add(payDay);
		payDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		payMon = new JComboBox(Tmon);
		payMon.setBounds(196, 188, 73, 30);
		panel.add(payMon);
		payMon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		payYear = new JComboBox(Tyear);
		payYear.setBounds(279, 188, 86, 30);
		panel.add(payYear);
		payYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		

		
		JLabel label = new JLabel("\u0E27\u0E34\u0E18\u0E35\u0E01\u0E32\u0E23\u0E08\u0E48\u0E32\u0E22\u0E40\u0E07\u0E34\u0E19 : ");
		label.setBounds(10, 147, 110, 30);
		panel.add(label);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		paymentBox = new JComboBox(paymentList);
		paymentBox.setBounds(119, 147, 529, 30);
		panel.add(paymentBox);
		paymentBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel label_7 = new JLabel("\u0E23\u0E32\u0E22\u0E25\u0E30\u0E40\u0E2D\u0E35\u0E22\u0E14 : ");
		label_7.setBounds(10, 65, 93, 30);
		panel.add(label_7);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		detail = new JTextField();
		detail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		detail.setBounds(119, 66, 529, 30);
		panel.add(detail);
		detail.setColumns(10);
		
		JLabel label_9 = new JLabel("รวมสินค้า : ");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_9.setBounds(10, 106, 110, 30);
		panel.add(label_9);
		
		amount = new JTextField(10);
		amount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		amount.setColumns(10);
		amount.setBounds(119, 106, 267, 30);
		amount.setDocument(new doubleDocument(12));
		panel.add(amount);
		
		vatBox = new JRadioButton("ใบกำกับภาษี");
		vatBox.setSelected(true);
		vatBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		vatBox.setBounds(140, 28, 110, 30);
		panel.add(vatBox);
		
		vat = new JTextField(10);
		vat.setText("0.00");
		vat.setEditable(false);
		vat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		vat.setBounds(506, 106, 142, 30);
		amount.setDocument(new doubleDocument(12));
		panel.add(vat);
		
		nonVatBox = new JRadioButton("ใบเสร็จรับเงิน ");
		nonVatBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		nonVatBox.setBounds(270, 28, 116, 30);
		panel.add(nonVatBox);
		
		ButtonGroup group = new ButtonGroup();
		group.add(vatBox);
		group.add(nonVatBox);
		
		JLabel label_12 = new JLabel("ประเภทใบเสร็จ : ");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_12.setBounds(10, 28, 110, 30);
		panel.add(label_12);
		
		autoVat = new JCheckBox("Auto Vat ");
//		autoVat.setEnabled(false);
		autoVat.setSelected(true);
		autoVat.setFont(new Font("Tahoma", Font.BOLD, 14));
		autoVat.setBounds(390, 106, 99, 30);
		panel.add(autoVat);
		
		
		cancelBtn = new JButton("cancel");
		cancelBtn.setBounds(554, 495, 120, 36);
		getContentPane().add(cancelBtn);
		cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		cusName = new JTextField();
		cusName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cusName.setColumns(10);
		cusName.setBounds(130, 93, 533, 30);
		getContentPane().add(cusName);
		
		JLabel label_10 = new JLabel("\u0E0A\u0E37\u0E48\u0E2D\u0E1C\u0E39\u0E49\u0E08\u0E48\u0E32\u0E22   : ");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_10.setBounds(10, 91, 93, 30);
		getContentPane().add(label_10);
		
		JLabel label_11 = new JLabel("\u0E0A\u0E37\u0E48\u0E2D   : ");
		label_11.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_11.setBounds(191, 11, 51, 30);
		getContentPane().add(label_11);
		
		saleID = new numberJTextField(2);
		saleID.setEnabled(false);
		saleID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		saleID.setColumns(10);
		saleID.setBounds(130, 11, 51, 30);
//		saleID.setText(SALES_ID);
		getContentPane().add(saleID);
		
		printBtn = new JButton("Print");
		printBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		printBtn.setBounds(294, 495, 120, 36);
		getContentPane().add(printBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Add point to member", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(673, 11, 312, 473);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		editCustomBtn = new JButton("<html><center>Edit Point<br>To Member</center></html>");
		editCustomBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		editCustomBtn.setBounds(96, 406, 146, 56);
		panel_1.add(editCustomBtn);
		
		model = new DefaultListModel<String>();
		tourList = new JList(model);
		tourList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tourList.setBounds(10, 23, 292, 376);
		panel_1.add(tourList);
		
		
		setComboxAction();
		actionPaymentVatBox();
		actionPaymentNonVatBox();
		actionAutoBox();
	}
	
	public void setSubmitAction(ActionListener action){
		submitBtn.addActionListener(action);
	}
	public void setPrintAction(ActionListener action){
		printBtn.addActionListener(action);
	}
	private void setCancelAction(final JDialog parent){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				parent.setEnabled(true);
				parent.toFront();
				parent.repaint();
			}
		};
		cancelBtn.addActionListener(action);
	}
	
	private void setCancelAction(final JFrame parent){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				parent.setEnabled(true);
				parent.toFront();
				parent.repaint();
			}
		};
		cancelBtn.addActionListener(action);
	}
	
	public void setCloseAction(WindowListener action){
		addWindowListener(action);
	}
	
	
	public invoiceData getInvoiceData(){
		invoiceData data = new invoiceData();
		data.setCreateUserID(getSaleID());
		data.setCreateName(getSaleName());
		data.setUpdateUser(getSaleID());
		float fVat = Float.parseFloat(vat.getText());
		float total = Float.parseFloat(amount.getText());
		float totalAll = total+fVat;
		data.setTotalAll(Float.toString(totalAll));
		data.setInvoiceType("Non");
		data.setRevised("0");
		data.setCustomerID(getCusNo());
		data.setCustomerName(getCusName());
		data.setAddress(getAddress());

		return data;
	}
	public paymentData getPaymentData(){
		paymentData data = new paymentData();
		int index = paymentBox.getSelectedIndex();
		data.setPaymentType(list.type.get(index));
		if(vatBox.isSelected()){
			
			float fVat = Float.parseFloat(vat.getText());
			data.setVat(Float.toString(fVat));
			float total = Float.parseFloat(amount.getText());
			data.setTotal(Float.toString(total));
			data.setHasVat("Y");
		}else{
			data.setVat("0.00");
			data.setTotal(amount.getText());
		}
		if(index > 0){
			data.setPaymentDate(getPaymentDate());
			data.setDetail(detail.getText());
			if(index==1){
				data.setChequeBank((String)bankBox.getSelectedItem());
				data.setChequeNo(chequeNo.getText());
				data.setChequeDate(getChequeDate());
			}
		}
		return data;
	}
	public boolean checkCanpayment(){
		boolean isPayment = false;
		boolean isOkayDate = Tools.checkDate(payDay,payMon,payYear);
		if((paymentBox.getSelectedIndex()!=0)&&isOkayDate){
			isPayment = true;
		}
		return isPayment;
	}
	
	public boolean checkChequeDetail(){
		boolean isOkay = false;
		int index = paymentBox.getSelectedIndex();
		if(index!=1){
			isOkay = true;
		}else{
			isOkay = Tools.checkDate(chequeDay,chequeMon,chequeYear);
			if(Tools.isEmpty(chequeNo.getText())){
				isOkay = false;
			}
			if(bankBox.getSelectedIndex()==0){
				isOkay = false;
			}
		}
			return isOkay;
	}
	private String getChequeDate(){
		String result = "";
		int day = chequeDay.getSelectedIndex();
		int mon = chequeMon.getSelectedIndex();
		String year = (String) chequeYear.getSelectedItem();
		result = Tools.genTextDate(day, mon, year);
		return result;
	}
	private String getPaymentDate(){
		String result = "";
		int day = payDay.getSelectedIndex();
		int mon = payMon.getSelectedIndex();
		String year = (String) payYear.getSelectedItem();
		result = Tools.genTextDate(day, mon, year);
		return result;
	}
	
	public void setCustomerData(invoiceCustomData data){
		cusNo.setText(data.getCusNo());
		cusName.setText(data.getCusName());
//		attnText.setText(data.getAttn());
//		taxText.setText(data.getTax());
//		telText.setText(data.getTel());
//		emailText.setText(data.getEmail());
		address.setText(data.getAddress());
//		boolean isTour = false;
//		if(data.getCustomerType().equals("tourmember")){
//			isTour = true;
//		}
		cusName.setEditable(false);
//		disableCusEdit(isTour);
	}
	public void setSaleSearchAction(ActionListener action){
		searchSaleBtn.addActionListener(action);
	}
	public void setTourSearchAction(ActionListener action){
		searchTourBtn.addActionListener(action);
	}
	public String getAmount() {
		return amount.getText();
	}
	public void setAmount(String cusName) {
		this.amount.setText(cusName);
	}
	public String getCusName() {
		return cusName.getText();
	}
	public void setCusName(String cusName) {
		this.cusName.setText(cusName);
	}
	public String getAddress() {
		return address.getText();
	}
	public void setAddress(String address) {
		this.address.setText(address);
	}
	public String getCusNo() {
		return cusNo.getText();
	}
	public void setCusNo(String cusID) {
		this.cusNo.setText(cusID);
	}
	public String getSaleID() {
		return saleID.getText();
	}
	public void setSaleID(String cusID) {
		this.saleID.setText(cusID);
	}
	public String getSaleName() {
		return saleName.getText();
	}
	public void setSaleName(String saleName) {
		this.saleName.setText(saleName);
		this.saleID.setEditable(false);
	}
	public void clearHead() {
//		this.saleName.setText("");
//		this.saleID.setText("");
		this.cusNo.setText("0");
		this.cusName.setText("");
		this.address.setText("");
//		this.saleID.setEditable(true);
		this.cusName.setEditable(true);
	}
	
	public void clearDetail(){
		this.detail.setText("");
		this.amount.setText("");
		this.vatBox.setSelected(false);
		this.nonVatBox.setSelected(false);
		this.paymentBox.setSelectedIndex(0);
		this.bankBox.setSelectedIndex(0);
		this.payDay.setSelectedIndex(0);
		this.payMon.setSelectedIndex(0);
		this.payYear.setSelectedIndex(0);
		this.chequeDay.setSelectedIndex(0);
		this.chequeMon.setSelectedIndex(0);
		this.chequeYear.setSelectedIndex(0);
		this.chequeNo.setText("");
	}
	public void setEdiListCalAction(ActionListener action) {
		editCustomBtn.addActionListener(action);
	}
	public void setClearHeadAction(ActionListener action) {
		clearHeadBtn.addActionListener(action);
	}
	public boolean checkAllInput() {
		boolean isOkay = false;
		if(!getSaleName().equals("")){
			if(!getCusName().equals("")){
				if(!getAmount().equals("")){
					if(!getPaymentDate().equals("")){
						isOkay = true;
					}
				}
			}
		}
		return isOkay;
	}
	private void setComboxAction(){
		paymentBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				paymentBox = (JComboBox) e.getSource();
                int index = paymentBox.getSelectedIndex();
				if(index!=1){
					bankBox.setEnabled(false);
					chequeDay.setEnabled(false);
					chequeMon.setEnabled(false);
					chequeYear.setEnabled(false);
					chequeNo.setEnabled(false);
				}else{
					bankBox.setEnabled(true);
					chequeDay.setEnabled(true);
					chequeMon.setEnabled(true);
					chequeYear.setEnabled(true);
					chequeNo.setEnabled(true);
				}
			}
			
		});
	}
//	private void actionVatCheck(){
//	    ActionListener action = new ActionListener() {
//	        public void actionPerformed(ActionEvent actionEvent) {
////	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
////	          boolean selected = abstractButton.getModel().isSelected();
//	          
//	        }
//	      };
//	      vatBox.addActionListener(action);
//	}
	public boolean hasVat() {
		return vatBox.isSelected();
	}
	public void setPrintEnable(boolean enable){
		printBtn.setEnabled(enable);
	};
	public void setAllBtnEnable(boolean enable){
		 searchTourBtn.setEnabled(enable);
//		 searchSaleBtn.setEnabled(enable);
//		 submitBtn.setEnabled(enable);
		 clearHeadBtn.setEnabled(enable);
		
	}

	public void setDetail(invoiceData data,String salesId,String salesName){
		setHasInvoice(true);
		setAllBtnEnable(false);
		saleID.setText(salesId);
		saleName.setText(salesName);
		cusNo.setText(data.getCustomerID());
		cusName.setText(data.getCustomerName());
		address.setText(data.getAddress());
		detail.setText(createPaymentDetail(data.getInvoiceType(), data.getInvoiceNo(), data.getRevised()));
		Float totalAll = Float.parseFloat(data.getTotalAll()) - Float.parseFloat(data.getVat());
		amount.setText(String.format("%.02f",totalAll));
//		amount.setText(data.getTotalAll());
		if(Float.parseFloat(data.getVat())>0){
			vatBox.setSelected(true);
			nonVatBox.setSelected(false);
		}else{
			vatBox.setSelected(false);
			nonVatBox.setSelected(true);
		}
		vat.setText(data.getVat());
		model.clear();
		addMemCalList(data.getCustomerName());
		
	}
	public void setDetail(searchPaymentData data) {
		saleID.setText(data.getSalesID());
		saleName.setText(data.getSalesName());
		cusNo.setText(data.getCustomerID());
		cusName.setText(data.getCustomerName());
		cusNo.setEnabled(false);
		if(!data.getCustomerID().equals("0")){
			cusName.setEnabled(false);
		}else{
			cusName.setEnabled(true);
		}
		
		address.setText(data.getAddress());
		detail.setText(data.getDetail());
		amount.setText(data.getTotal());
		vatBox.setSelected(data.getHasVat());
		nonVatBox.setSelected(!data.getHasVat());
		vat.setText(data.getVat());
		ArrayList<Integer> pay = Tools.spitDate(data.getPaymentDate());
		paymentBox.setSelectedItem(data.getPaymentType());
		payDay.setSelectedIndex(pay.get(0));
		payMon.setSelectedIndex(pay.get(1));
		payYear.setSelectedItem(Integer.toString(pay.get(2)));
		if(Tools.isNotEmpty(data.getChequeNo())){
			chequeNo.setText(data.getChequeNo());
			ArrayList<Integer> cheque = Tools.spitDate(data.getChequeDate());
			chequeDay.setSelectedIndex(pay.get(0));
			chequeMon.setSelectedIndex(pay.get(1));
			chequeYear.setSelectedItem(Integer.toString(pay.get(2)));
			bankBox.setSelectedItem(data.getChequeBank());
		}
	}
	public void setHasInvoice(boolean hasInvoice){
		cusNo.setEnabled(!hasInvoice);
		cusName.setEnabled(!hasInvoice);
		address.setEnabled(!hasInvoice);
//		detail.setEnabled(!hasInvoice);;
		amount.setEnabled(!hasInvoice);;
		vatBox.setEnabled(false);
		nonVatBox.setEnabled(false);
//		paymentBox.setEnabled(hasInvoice);
		payDay.setEnabled(true);
		payMon.setEnabled(true);
		payYear.setEnabled(true);
		if(hasInvoice){
			
		}else{
			textUpdateVatAction();
		}
//		chequeNo.setEnabled(hasInvoice);
//		chequeDay.setEnabled(hasInvoice);
//		chequeMon.setEnabled(hasInvoice);
//		chequeYear.setEnabled(hasInvoice);
//		bankBox.setEnabled(hasInvoice);
	}
	
	public void setEnabledAll(boolean enable){
		cusNo.setEnabled(enable);
		cusName.setEnabled(enable);
		address.setEnabled(enable);
		detail.setEnabled(enable);;
		amount.setEnabled(enable);;
		vatBox.setEnabled(enable);
		nonVatBox.setEnabled(enable);
		paymentBox.setEnabled(enable);
		payDay.setEnabled(enable);
		payMon.setEnabled(enable);
		payYear.setEnabled(enable);
//		chequeNo.setEnabled(enable);
//		chequeDay.setEnabled(enable);
//		chequeMon.setEnabled(enable);
//		chequeYear.setEnabled(enable);
//		bankBox.setEnabled(enable);
	}
	public void replaceAllCalList(ArrayList<String> aData){
		model = new DefaultListModel<String>();
		for(String temp : aData){
			model.addElement(temp);
		}
		tourList.setModel(model);
	}
	
	private String createPaymentDetail(String type,String no,String rev){
		StringBuffer sb = new StringBuffer();
		sb.append("เป็นการชำระเงินสำหรับ Ref. ");
		sb.append(Tools.createFullInvoice(type, no, rev));
		return sb.toString();
	}
	public void setEnableSubmitBtn(boolean enable) {
		submitBtn.setEnabled(enable);
		
	}
	private void actionPaymentVatBox(){
		ItemListener action = new ItemListener()  {
			public void itemStateChanged(ItemEvent actionEvent) {
//	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				String totalAll = amount.getText();
				vat.setText(Tools.calculateVat(totalAll, tax));
	        	autoVat.setSelected(true);
	        	vat.setEditable(false);    
	        	autoVat.setEnabled(true);
	        }
	    };
	    vatBox.addItemListener(action);
	}
	
	private void actionPaymentNonVatBox(){
		ItemListener action = new ItemListener()  {
			public void itemStateChanged(ItemEvent actionEvent) {
	          boolean selected = nonVatBox.isSelected();
	          if(selected){
	        	  vat.setText("0.00");
	        	  vat.setEditable(false);
	        	  autoVat.setSelected(true);
	        	  autoVat.setEnabled(false);
	          }
	        }
	    };
	    nonVatBox.addItemListener(action);
	}
	private void actionAutoBox(){
	    ItemListener action = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
//		          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		          boolean selected = autoVat.isSelected();
//		          boolean hasVat = autoVat.isSelected();
		          if(selected){
		        	  vat.setEditable(false);
		        	  String totalAll = amount.getText();
		        	  vat.setText(Tools.calculateVat(totalAll, tax));
		          }else{
		        	  vat.setEditable(true);
//		        	  vat.setText("0.00");
		          }
			}
	    };
	    autoVat.addItemListener(action);
	}
	
	public boolean isPaymentVatandVatZero(){
		boolean result = false;
		Float vat = Float.parseFloat(this.vat.getText());
		if(vatBox.isSelected()&&(vat<=0)){
			result = true;
		}
		return result;
	}
	public void textUpdateVatAction(){
		DocumentListener updateAction = new DocumentListener(){
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				setVat();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				setVat();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				setVat();
			}
			public void setVat(){
				String totalAll = amount.getText();
				if(hasVat()&&autoVat.isSelected()){
					vat.setText(Tools.calculateVat(totalAll, tax));
				}
			}
		};
		amount.getDocument().addDocumentListener(updateAction);
	}
	public void genCasePaymentVat(boolean paymentHasVat) {
		nonVatBox.setSelected(!paymentHasVat);
		vatBox.setSelected(paymentHasVat);
		nonVatBox.setEnabled(paymentHasVat);
		vatBox.setEnabled(paymentHasVat);
	}
	public void addMemCalList(String aData){
		 model.addElement(aData);
	}
	public void setSumbitAction(ActionListener action){
		listSelect.setSumbitAction(action);
	}
	public void controlListAdd(ArrayList<rowMember> listCalPoint){
		listSelect.control(listCalPoint);
	}

	public ArrayList<String> getMemIDCalList() {
		return listSelect.getMemID();
		
	}

	public ArrayList<String> getMemNameCalList() {
		return listSelect.getMemName();
	}
	public void setAddCalListVisible(boolean isVisible){
		listSelect.setVisibleView(isVisible);
	}
}
