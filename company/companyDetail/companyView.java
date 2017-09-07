package company.companyDetail;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import com.base.Tools;
import com.extentclass.numberJTextField;
import com.extentclass.selectOneRowModel;
import com.table.rowMember;
import com.table.tableTour;

import company.meetingLog.meetingRow;
import company.meetingLog.tableMeeting;

import javax.swing.border.LineBorder;
import java.awt.Color;

public class companyView extends JFrame{

//	private JFrame frame;
	private JTextField idText;
	private JTextField thaiName;
	private JTextField engName;
	private numberJTextField postNumber;
	private numberJTextField telNo;
	private JTextField email;
	private JEditorPane address;
	private JTextArea comment;
	private JButton submitBtn;
	private JButton clearBtn;
	private JButton cancleBtn;
	private JComboBox provinceBox;
	private JTextField taxID;
	private JButton disBtn;
	private JComboBox typeBox;
	private JTable table;
	private JButton delBtn;
	private JButton insertBtn;
//	private ArrayList<String> provinceData;

	public companyView() {

		ArrayList<String> provinceData = Tools.getProvince();
		String[] province = provinceData.toArray(new String[provinceData.size()]);
		String[] typeData = {"None","Hotel","Restaurant","Show","Company","Other"};

		setBounds(100, 100, 1012, 469);

		getContentPane().setLayout(null);
		setVisible(true);
		setResizable(false);
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new TitledBorder(null, "Company Profile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainPanel.setBounds(10, 11, 479, 374);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel idLabel = new JLabel("Company ID");
		idLabel.setBounds(259, 23, 88, 30);
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mainPanel.add(idLabel);
		
		idText = new JTextField();
		idText.setEditable(false);
		idText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		idText.setBounds(357, 24, 111, 30);
		mainPanel.add(idText);
		idText.setColumns(10);
		
		JLabel thaiLabel = new JLabel("\u0E0A\u0E37\u0E48\u0E2D\u0E20\u0E32\u0E29\u0E32\u0E44\u0E17\u0E22 * : ");
		thaiLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		thaiLabel.setBounds(10, 65, 107, 28);
		mainPanel.add(thaiLabel);
		
		JLabel engLabel = new JLabel("\u0E0A\u0E37\u0E48\u0E2D\u0E20\u0E32\u0E29\u0E32\u0E2D\u0E31\u0E07\u0E01\u0E24\u0E29 : ");
		engLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		engLabel.setBounds(10, 98, 107, 28);
		mainPanel.add(engLabel);
		
		JLabel addLabel = new JLabel("\u0E17\u0E35\u0E48\u0E2D\u0E22\u0E39\u0E48\u0E1A\u0E23\u0E34\u0E29\u0E31\u0E17 : ");
		addLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addLabel.setBounds(10, 166, 75, 28);
		mainPanel.add(addLabel);
		
		JLabel postLabel = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E44\u0E1B\u0E23\u0E13\u0E35\u0E22\u0E4C : ");
		postLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		postLabel.setBounds(10, 228, 107, 28);
		mainPanel.add(postLabel);
		
		JLabel cityLabel = new JLabel("\u0E08\u0E31\u0E07\u0E2B\u0E27\u0E31\u0E14 : ");
		cityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cityLabel.setBounds(232, 228, 55, 28);
		mainPanel.add(cityLabel);
		
		JLabel telLabel = new JLabel("\u0E40\u0E1A\u0E2D\u0E23\u0E4C\u0E42\u0E17\u0E23 : ");
		telLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		telLabel.setBounds(10, 260, 70, 28);
		mainPanel.add(telLabel);
		
		JLabel emailLabel = new JLabel("Email : ");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailLabel.setBounds(232, 260, 55, 28);
		mainPanel.add(emailLabel);
		
		JLabel commentLable = new JLabel("comment : ");
		commentLable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		commentLable.setBounds(10, 295, 95, 28);
		mainPanel.add(commentLable);
		
		thaiName = new JTextField();
		thaiName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		thaiName.setBounds(127, 65, 343, 28);
		mainPanel.add(thaiName);
		thaiName.setColumns(10);
		
		engName = new JTextField();
		engName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		engName.setBounds(127, 98, 343, 28);
		mainPanel.add(engName);
		engName.setColumns(10);
		
		address = new JEditorPane();
		address.setFont(new Font("Tahoma", Font.PLAIN, 14));
		address.setBounds(127, 166, 343, 55);
		mainPanel.add(address);
		
		postNumber = new numberJTextField(5);
		postNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		postNumber.setBounds(127, 228, 95, 28);
		mainPanel.add(postNumber);
		postNumber.setColumns(10);
		
		telNo = new numberJTextField(10);
		telNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		telNo.setColumns(10);
		telNo.setBounds(127, 260, 95, 28);
		mainPanel.add(telNo);
		
		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 14));
		email.setColumns(10);
		email.setBounds(289, 260, 179, 28);
		mainPanel.add(email);
		
		comment = new JTextArea();
		comment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comment.setBounds(127, 295, 343, 68);
		comment.setLineWrap(true);
		comment.setWrapStyleWord(true);
		mainPanel.add(comment);
		
		provinceBox = new JComboBox(province);
		provinceBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		provinceBox.setBounds(289, 228, 179, 28);
		mainPanel.add(provinceBox);
		
		JLabel taxLabel = new JLabel("\u0E40\u0E25\u0E02\u0E17\u0E35\u0E48\u0E1C\u0E39\u0E49\u0E40\u0E2A\u0E35\u0E22\u0E20\u0E32\u0E29\u0E35 : ");
		taxLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taxLabel.setBounds(10, 132, 107, 28);
		mainPanel.add(taxLabel);
		
		taxID = new JTextField();
		taxID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taxID.setColumns(10);
		taxID.setBounds(127, 132, 159, 28);
		mainPanel.add(taxID);
		
		JLabel typeLabel = new JLabel("\u0E1B\u0E23\u0E30\u0E40\u0E20\u0E17 : ");
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		typeLabel.setBounds(293, 132, 66, 28);
		mainPanel.add(typeLabel);
		
		typeBox = new JComboBox(typeData);
		typeBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		typeBox.setBounds(357, 132, 111, 28);
		mainPanel.add(typeBox);
		
		submitBtn = new JButton("Submit");
		submitBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		submitBtn.setBounds(65, 396, 85, 33);
		getContentPane().add(submitBtn);
		
		clearBtn = new JButton("Clear");
		clearBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		clearBtn.setBounds(158, 396, 75, 33);
		getContentPane().add(clearBtn);
		
		cancleBtn = new JButton("Cancel");
		cancleBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		cancleBtn.setBounds(243, 396, 75, 33);
		getContentPane().add(cancleBtn);
		
		disBtn = new JButton("Enable/Disable Edit");
		disBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		disBtn.setBounds(328, 396, 151, 33);
		getContentPane().add(disBtn);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Meeting Log", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(493, 11, 503, 418);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		table = new JTable(new tableMeeting());
		table.setEnabled(false);
		table.setSelectionModel(new selectOneRowModel());
		table.getColumnModel().getColumn(0).setPreferredWidth(160);
		table.getColumnModel().getColumn(1).setPreferredWidth(240);
		table.getColumnModel().getColumn(2).setPreferredWidth(160);
		table.getColumnModel().getColumn(3).setPreferredWidth(160);
		table.getColumnModel().getColumn(4).setPreferredWidth(160);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 22, 483, 341);
		panel.add(scrollPane);
		
		delBtn = new JButton("Delete Log");
		delBtn.setEnabled(false);
		delBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		delBtn.setBounds(279, 374, 114, 33);
		panel.add(delBtn);
		
		insertBtn = new JButton("Create Log");
		insertBtn.setEnabled(false);
		insertBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		insertBtn.setBounds(126, 374, 114, 33);
		panel.add(insertBtn);
		disBtn.setVisible(false);
	}
	public void clear(){
		thaiName.setText("");
		engName.setText("");
		address.setText("");
		email.setText("");
		postNumber.setText("");
		telNo.setText("");
		provinceBox.setSelectedIndex(0);
		comment.setText("");
		taxID.setText("");
	}
	public boolean setDisableText(){
		boolean result = false;
		thaiName.setEditable(false);
		engName.setEditable(false);
		address.setEditable(false);
		email.setEditable(false);
		postNumber.setEditable(false);
		telNo.setEditable(false);
		provinceBox.setEnabled(false);
		comment.setEditable(false);
		taxID.setEditable(false);
		result = true;
		return result;
	}
	public boolean setEnableText(){
		boolean result = false;
		thaiName.setEditable(true);
		engName.setEditable(true);
		address.setEditable(true);
		email.setEditable(true);
		postNumber.setEditable(true);
		telNo.setEditable(true);
		provinceBox.setEnabled(true);
		comment.setEditable(true);
		taxID.setEditable(true);
		result = true;
		return result;
	}
	public JButton getSubmitBtn() {
		return submitBtn;
	}
	public void setSubmitBtn(JButton insertBtn) {
		this.submitBtn = insertBtn;
	}
	public JButton getClearBtn() {
		return clearBtn;
	}
	public void setClearBtn(JButton clearBtn) {
		this.clearBtn = clearBtn;
	}
	public JButton getCancleBtn() {
		return cancleBtn;
	}
	public void setCancleBtn(JButton cancleBtn) {
		this.cancleBtn = cancleBtn;
	}
	public String getProvince() {
		if(provinceBox.getSelectedIndex()==0){
			return "";
		}else{
			return (String) provinceBox.getSelectedItem();
		}
	}
	public void setProvinceIndex(int index) {
		this.provinceBox.setSelectedIndex(index);;
	}
	public String getThaiName() {
		return thaiName.getText();
	}
	public void setThaiName(String thaiName) {
		this.thaiName.setText(thaiName);
	}
	public String getEngName() {
		return engName.getText();
	}
	public void setEngName(String engName) {
		this.engName.setText(engName);
	}
	public String getPostNumber() {
		return postNumber.getText();
	}
	public void setPostNumber(String postNumber) {
		this.postNumber.setText(postNumber);
	}
	public String getTelNo() {
		return telNo.getText();
	}
	public void setTelNo(String telNo) {
		this.telNo.setText(telNo);
	}
	public void setComType(String comType) {
		this.typeBox.setSelectedItem(comType);
	}
	public String getComType() {
		String result = (String) typeBox.getSelectedItem();
		return result;	
	}
	public String getEmail() {
		return email.getText();
	}
	public void setEmail(String email) {
		this.email.setText(email);
	}
	public String getAddress() {
		return address.getText();
	}
	public void setAddress(String address) {
		this.address.setText(address);
	}
	public String getComment() {
		return comment.getText();
	}
	public void setComment(String comment) {
		this.comment.setText(comment);
	}
	public String getTaxID() {
		return taxID.getText();
	}
	public void setTaxID(String taxID) {
		this.taxID.setText(taxID);
	}
	public boolean setViewText(companyData data){
		boolean result = false;
		idText.setText(data.getComID());
		thaiName.setText(data.gettName());
		engName.setText(data.geteName());
		address.setText(data.getAddress());
		email.setText(data.getemail());
		postNumber.setText(data.getPostNum());
		telNo.setText(data.getTelNo());
		provinceBox.setSelectedItem(data.getProvince());
		comment.setText(data.getRemark());
		taxID.setText(data.getTaxID());
		setComType(data.getComType());
		result = true;
		return result;
	}
	public boolean checkInput(){
		boolean result = false;
		if(getThaiName().equals("")){
			JOptionPane.showMessageDialog(null,"Please enter a Thai Name");
		}else{
			if(getProvince().equals("")){
				JOptionPane.showMessageDialog(null,"Please enter a Province");
			}
			else{
				result = true;
			}
		}
		return result;
	}
	public JButton getDisBtn() {
		return disBtn;
	}
	public void setDisBtn(JButton disBtn) {
		this.disBtn = disBtn;
	}
	public String getComID() {
		return idText.getText();
	}
	
	public JTable getTable() {
		return table;
	}

	public void SetEnableLog(boolean enable){
		delBtn.setEnabled(enable);
		insertBtn.setEnabled(enable);
		table.setEnabled(enable);
	}
	public meetingRow getRowData(){
		meetingRow rowData = new meetingRow();
		int rowCount = this.table.getModel().getRowCount();
		if(rowCount>0){
			int index = this.table.getSelectedRow();
			if(index== -1){
//				return new meetingRow();
			}else{
				rowData =  ((tableMeeting) this.table.getModel()).getRowsData(index);
			}
		}
		return rowData;
	}
	public void setTable(tableMeeting aTable) {
		this.table.setModel(aTable);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(160);
		table.getColumnModel().getColumn(1).setPreferredWidth(240);
		table.getColumnModel().getColumn(2).setPreferredWidth(160);
		table.getColumnModel().getColumn(3).setPreferredWidth(160);
		table.getColumnModel().getColumn(4).setPreferredWidth(160);
		
	}
	public void setInsertLogAction(ActionListener action){
		insertBtn.addActionListener(action);
	}
	public void setDelLogAction(ActionListener action){
		delBtn.addActionListener(action);
	}
	public void setClickTableAction(MouseAdapter clickTable){
		table.addMouseListener(clickTable);
	}
}


