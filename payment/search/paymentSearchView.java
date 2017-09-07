package payment.search;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.DocumentFilter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import com.base.Tools;
import com.base.icon;
import com.extentclass.upperFilter;

import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.AbstractDocument;

public class paymentSearchView extends JDialog {

	private JTable table;
	private JTextField invoiceNoText;
	private JTextField paymentStatus;
	private JTextField cusNameText;
	private JComboBox startDay;
	private JComboBox startMon;
	private JComboBox startYear;
	private JComboBox endDay;
	private JComboBox endMon;
	private JComboBox endYear;	
	private JButton searchBtn;
	private JButton cancelBtn;
	private JButton deleteBtn;
	private JButton clearBtn;
	private JCheckBox noVatBox;
	private JCheckBox vatBox;
	private JCheckBox rejectBox;
	private JButton rejectBtn;
	
	public paymentSearchView() {
		
		setTitle("Payment Search");
		
		Image searchImg = new ImageIcon(icon.getPicPath("search.png")).getImage();
		searchImg = searchImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		Image delImg = new ImageIcon(icon.getPicPath("delete.png")).getImage();
		delImg = delImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		DocumentFilter dfilter = new upperFilter();
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 993, 640);
		getContentPane().setLayout(null);
		
		String[] Tday={"Day","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		String[] Tmon = {"Mon","1","2","3","4","5","6","7","8","9","10","11","12"};
		String tempYear = Integer.toString(Calendar.getInstance().get(Calendar.YEAR)-2);
		String[] Tyear  = Tools.genYear(10,tempYear);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Search Conditon", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		searchPanel.setBounds(10, 10, 957, 228);
		getContentPane().add(searchPanel);
		searchPanel.setLayout(null);
		
		JLabel cusNameLable = new JLabel("\u0E0A\u0E37\u0E48\u0E2D \u0E2B\u0E23\u0E37\u0E2D \u0E19\u0E32\u0E21\u0E2A\u0E01\u0E38\u0E25 :");
		cusNameLable.setFont(new Font("Tahoma", Font.BOLD, 14));
		cusNameLable.setBounds(10, 114, 123, 30);
		searchPanel.add(cusNameLable);
		
		JLabel paymentLabel = new JLabel("Payment No :");
		paymentLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		paymentLabel.setBounds(10, 26, 97, 30);
		searchPanel.add(paymentLabel);
		
		JLabel lblIssueDate = new JLabel("Start Date :");
		lblIssueDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIssueDate.setBounds(10, 73, 97, 30);
		searchPanel.add(lblIssueDate);
		
		paymentStatus = new JTextField();
		paymentStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
		paymentStatus.setBounds(117, 26, 243, 30);
		searchPanel.add(paymentStatus);
		
		
		invoiceNoText = new JTextField();
		invoiceNoText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		invoiceNoText.setBounds(479, 26, 238, 30);
		searchPanel.add(invoiceNoText);
		invoiceNoText.setColumns(10);
		( (AbstractDocument) invoiceNoText.getDocument()).setDocumentFilter(dfilter);
		
		cusNameText = new JTextField();
		cusNameText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cusNameText.setColumns(10);
		cusNameText.setBounds(140, 115, 577, 30);
		searchPanel.add(cusNameText);

		startDay = new JComboBox(Tday);
		startDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startDay.setBounds(117, 73, 67, 30);
		searchPanel.add(startDay);
		
		startMon = new JComboBox(Tmon);
		startMon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startMon.setBounds(194, 73, 73, 30);
		searchPanel.add(startMon);
		
		startYear = new JComboBox(Tyear);
		startYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startYear.setBounds(274, 73, 86, 30);
		searchPanel.add(startYear);
		
		searchBtn = new JButton("Search");
		searchBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchBtn.setBounds(268, 171, 123, 30);
		searchBtn.setIcon(new ImageIcon(searchImg));
		searchPanel.add(searchBtn);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		cancelBtn.setBounds(534, 171, 123, 30);
		searchPanel.add(cancelBtn);
		
		JLabel lblEndDate = new JLabel("End Date :");
		lblEndDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEndDate.setBounds(386, 73, 97, 30);
		searchPanel.add(lblEndDate);
		
		endDay = new JComboBox(Tday);
		endDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		endDay.setBounds(474, 73, 67, 30);
		searchPanel.add(endDay);
		
		endMon = new JComboBox(Tmon);
		endMon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		endMon.setBounds(551, 73, 73, 30);
		searchPanel.add(endMon);
		
		endYear = new JComboBox(Tyear);
		endYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		endYear.setBounds(631, 73, 86, 30);
		searchPanel.add(endYear);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		deleteBtn.setBounds(10, 171, 123, 30);
		deleteBtn.setIcon(new ImageIcon(delImg));
		searchPanel.add(deleteBtn);
		
		clearBtn = new JButton("Clear");
		clearBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		clearBtn.setBounds(401, 171, 123, 30);
		searchPanel.add(clearBtn);
		
		JLabel label = new JLabel("Invoice No :");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(375, 26, 97, 30);
		searchPanel.add(label);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Search Condition", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(727, 26, 220, 191);
		searchPanel.add(panel);
		panel.setLayout(null);
		
		noVatBox = new JCheckBox("\u0E43\u0E1A\u0E23\u0E31\u0E1A\u0E40\u0E07\u0E34\u0E19");
		noVatBox.setSelected(true);
		noVatBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		noVatBox.setBounds(6, 28, 157, 23);
		panel.add(noVatBox);
		
		vatBox = new JCheckBox("\u0E43\u0E1A\u0E01\u0E33\u0E01\u0E31\u0E1A\u0E20\u0E32\u0E29\u0E35");
		vatBox.setSelected(true);
		vatBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		vatBox.setBounds(6, 64, 157, 23);
		panel.add(vatBox);
		
		rejectBox = new JCheckBox("\u0E40\u0E09\u0E1E\u0E32\u0E30\u0E43\u0E1A\u0E17\u0E35\u0E48\u0E44\u0E21\u0E48\u0E42\u0E14\u0E19 Reject");
		rejectBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		rejectBox.setBounds(6, 102, 208, 23);
		panel.add(rejectBox);
		
		rejectBtn = new JButton("Reject");
		rejectBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		rejectBtn.setBounds(140, 171, 123, 30);
		rejectBtn.setIcon(new ImageIcon(delImg));
		searchPanel.add(rejectBtn);
		clearAction();
		
		table = new JTable();
//		table.setSelectionModel(new selectOneRowModel());
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
//		table.getTableHeader().setPreferredSize(new Dimension(100, 47));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new tablePaymentHead());		
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 249, 957, 341);
		getContentPane().add(scrollPane);
		
		setColwitdh();
		
	}
	
	public String getStartDate(){
		String result = "";
		int day = startDay.getSelectedIndex();
		int mon = startMon.getSelectedIndex();
		String year = (String) startYear.getSelectedItem();
		result = Tools.genTextDate(day, mon, year);
		return result;
	}
	public String getEndDate(){
		String result = "";
		int day = endDay.getSelectedIndex();
		int mon = endMon.getSelectedIndex();
		String year = (String) endYear.getSelectedItem();
		result = Tools.genTextDate(day, mon, year);
		return result;
	}
	public void setStartDate(String date){
		String[] spDate = date.split("/");
		startDay.setSelectedIndex(Integer.parseInt(spDate[0]));
		startMon.setSelectedIndex(Integer.parseInt(spDate[1]));
		startYear.setSelectedItem(spDate[2]);
	}
	public void setEndDate(String date){
		String[] spDate = date.split("/");
		endDay.setSelectedIndex(Integer.parseInt(spDate[0]));
		endMon.setSelectedIndex(Integer.parseInt(spDate[1]));
		endYear.setSelectedItem(spDate[2]);
	}
	public void setSearchAction (ActionListener action){
		searchBtn.addActionListener(action);
	}
	
	public void setClickTableAction (MouseAdapter action){
		table.addMouseListener(action);
	}
	
	public void setCancelAction (ActionListener action){
		cancelBtn.addActionListener(action);
	}

	public void setDelAction (ActionListener action){
		deleteBtn.addActionListener(action);
	}
	
	public void setRejectAction (ActionListener action){
		rejectBtn.addActionListener(action);
	}
	
	public searchData getSearchData (){
		searchData data = new searchData();
		data.setName(cusNameText.getText());
		data.setInvoiceNo(invoiceNoText.getText());
		data.setStartDate(getStartDate());
		data.setEndDate(getEndDate());
		data.setPayment(paymentStatus.getText());
		data.setIsReject(rejectBox.isSelected());
		data.setPaymentNoVat(noVatBox.isSelected());
		data.setPaymentVat(vatBox.isSelected());
		return data;
	}
	public boolean setModel(ArrayList<rowPaymentHead> data){
		boolean result = false;
		tablePaymentHead tableModel= new tablePaymentHead();
		tableModel.setListRowData(data);
		table.setModel(tableModel);
		setColwitdh();
		result = true;
		return result;
	}
	
	public String getPaymentNo (){
		String result = "";
		int rowIndex = table.getSelectedRow();
		final int colInvoice = 1;
		result = (String) ((tablePaymentHead)table.getModel()).getValueAt(rowIndex, colInvoice);
		return result;
	}
	
	private void clearAction(){
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invoiceNoText.setText("");
				cusNameText.setText("");
				endDay.setSelectedIndex(0);
				endMon.setSelectedIndex(0);
				endYear.setSelectedIndex(0);
				startDay.setSelectedIndex(0);
				startMon.setSelectedIndex(0);
				startYear.setSelectedIndex(0);
			}
		});
	}
	
	private void setColwitdh(){
		table.getTableHeader().setPreferredSize(new Dimension(10000, 45));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
//		table.getColumnModel().getColumn(1).setPreferredWidth(75);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(75);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.getColumnModel().getColumn(5).setPreferredWidth(500);
		table.getColumnModel().getColumn(6).setPreferredWidth(110);
		table.setRowHeight(30);
	}
	

	public boolean getHasInvoice() {
		boolean hasInvoice = false;
		int rowNumber = table.getSelectedRow();
		String invoice = "No invoice";
		if(rowNumber>=0){
			invoice = (String) table.getModel().getValueAt(rowNumber, 2);
		}
		if(!invoice.equals("No invoice")){
			hasInvoice  = true;
		}
		return hasInvoice;
	}
	public boolean isNotReject() {
		boolean isReject = false;
		int rowNumber = table.getSelectedRow();
		String rejectType = "N";
		if(rowNumber>=0){
			rejectType = (String) table.getModel().getValueAt(rowNumber, 3);
		}
		if(rejectType.equals("N")){
			isReject  = true;
		}
		return isReject;
	}

	public String getIsvat() {
		String isVat = "";
		int rowIndex = table.getSelectedRow();
		final int colInvoice = 0;
		isVat = (String) ((tablePaymentHead)table.getModel()).getValueAt(rowIndex, colInvoice);
		return isVat;
	}

	public String getSelectInvoice() {
		String invoiceNo = "";
		int rowIndex = table.getSelectedRow();
		final int colInvoice = 2;
		invoiceNo = (String) ((tablePaymentHead)table.getModel()).getValueAt(rowIndex, colInvoice);
		return invoiceNo;
	}
	public boolean canRejectPayment(){
		boolean canDelete = false;
		final int colType = 0;
		int rowIndex = table.getSelectedRow();
		String type = (String) ((tablePaymentHead)table.getModel()).getValueAt(rowIndex, colType);
		if(type.equals("ใบกำกับภาษี")){
			
		}else{
			canDelete = true;
		}
		return canDelete;
	}
	public boolean canDeletePayment(){
		boolean canDelete = false;
		final int colType = 2;
		int rowIndex = table.getSelectedRow();
		String type = (String) ((tablePaymentHead)table.getModel()).getValueAt(rowIndex, colType);
		if(type.equals("No invoice")){
			canDelete = true;
		}else{
			
		}
		return canDelete;
	}

	/*
	 *  paymentNo 
	 */
	public String getSelectPayment() {
		String invoiceNo = "";
		int rowIndex = table.getSelectedRow();
		final int colPayment = 1;
		invoiceNo = (String) ((tablePaymentHead)table.getModel()).getValueAt(rowIndex, colPayment);
		return invoiceNo;
	}

	public void genCasePaymentVat(boolean hasVat) {
		vatBox.setEnabled(hasVat);
		vatBox.setSelected(hasVat);
		
	}
}
