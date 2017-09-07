package company.meetingLog;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.base.Tools;
import com.extentclass.numberJTextField;
import com.extentclass.selectOneRowModel;
import com.table.rowMember;
import com.table.tableTour;

import company.companyDetail.companyView;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;

public class meetingView extends JDialog{

//	private JFrame frame;
	private JTextField idText;
	private JTextField thaiName;
	private JTextField contactName;
	private numberJTextField telNo;
	private JTextField email;
	private JTextArea comment;
	private JButton submitBtn;
	private JButton clearBtn;
	private JButton cancleBtn;
	private JTextArea contactDay;
	private JTextArea callDay;	
//	private ArrayList<String> provinceData;

	public meetingView(final JFrame  parent) {
		super(parent);
		createView();
		addWindowListener(new WindowAdapter() 
		{
		  public void windowClosing(WindowEvent e){
			  parent.setEnabled(true);
		  }
		});
		setCancelAction(parent);
	}
	
	private void createView(){
		setBounds(100, 100, 513, 446);

		getContentPane().setLayout(null);
//		setVisible(true);
		setResizable(false);
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Contact Profile", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		mainPanel.setBounds(10, 11, 479, 351);
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
		
		JLabel thaiLabel = new JLabel("\u0E0A\u0E37\u0E48\u0E2D\u0E20\u0E32\u0E29\u0E32\u0E44\u0E17\u0E22  : ");
		thaiLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		thaiLabel.setBounds(10, 65, 95, 28);
		mainPanel.add(thaiLabel);
		
		JLabel contactLabel = new JLabel("\u0E0A\u0E37\u0E48\u0E2D\u0E1C\u0E39\u0E49\u0E15\u0E34\u0E14\u0E15\u0E48\u0E2D : ");
		contactLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contactLabel.setBounds(10, 98, 107, 28);
		mainPanel.add(contactLabel);
		
		JLabel addLabel = new JLabel("<HTML>\u0E27\u0E31\u0E19\u0E17\u0E35\u0E48\u0E2A\u0E30\u0E14\u0E27\u0E01    : <br>\u0E42\u0E17\u0E23\u0E15\u0E34\u0E14\u0E15\u0E48\u0E2D<HTML>");
		addLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addLabel.setBounds(10, 215, 95, 40);
		mainPanel.add(addLabel);
		
		JLabel dayContactLabel = new JLabel("<HTML>\u0E27\u0E31\u0E19\u0E17\u0E35\u0E48\u0E2A\u0E30\u0E14\u0E27\u0E01    : <br>\u0E40\u0E02\u0E49\u0E32\u0E15\u0E34\u0E14\u0E15\u0E48\u0E2D<HTML>");
		dayContactLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dayContactLabel.setBounds(10, 165, 95, 40);
		mainPanel.add(dayContactLabel);
		
		JLabel telLabel = new JLabel("\u0E40\u0E1A\u0E2D\u0E23\u0E4C\u0E42\u0E17\u0E23 : ");
		telLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		telLabel.setBounds(293, 131, 70, 28);
		mainPanel.add(telLabel);
		
		JLabel emailLabel = new JLabel("Email : ");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailLabel.setBounds(10, 131, 55, 28);
		mainPanel.add(emailLabel);
		
		JLabel commentLable = new JLabel("<HTML>\u0E0A\u0E48\u0E27\u0E07\u0E40\u0E27\u0E25\u0E32\u0E17\u0E35\u0E48    : <br>\u0E44\u0E1B\u0E40\u0E17\u0E35\u0E48\u0E22\u0E27<HTML>");
		commentLable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		commentLable.setBounds(10, 265, 95, 40);
		mainPanel.add(commentLable);
		
		thaiName = new JTextField();
		thaiName.setEnabled(false);
		thaiName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		thaiName.setBounds(115, 65, 355, 28);
		mainPanel.add(thaiName);
		thaiName.setColumns(10);
		
		contactName = new JTextField();
		contactName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contactName.setBounds(115, 98, 355, 28);
		mainPanel.add(contactName);
		contactName.setColumns(10);
		
		telNo = new numberJTextField(10);
		telNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		telNo.setColumns(10);
		telNo.setBounds(373, 131, 95, 28);
		mainPanel.add(telNo);
		
		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 14));
		email.setColumns(10);
		email.setBounds(115, 131, 168, 28);
		mainPanel.add(email);
		
		comment = new JTextArea();
		comment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comment.setBounds(115, 265, 353, 68);
		comment.setLineWrap(true);
		comment.setWrapStyleWord(true);
		mainPanel.add(comment);
		
		contactDay = new JTextArea();
		contactDay.setWrapStyleWord(true);
		contactDay.setLineWrap(true);
		contactDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contactDay.setBounds(115, 165, 353, 45);
		mainPanel.add(contactDay);
		
		callDay = new JTextArea();
		callDay.setWrapStyleWord(true);
		callDay.setLineWrap(true);
		callDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		callDay.setBounds(115, 215, 353, 45);
		mainPanel.add(callDay);
		
		submitBtn = new JButton("Submit");
		submitBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		submitBtn.setBounds(115, 373, 85, 33);
		getContentPane().add(submitBtn);
		
		clearBtn = new JButton("Clear");
		clearBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		clearBtn.setBounds(208, 373, 75, 33);
		getContentPane().add(clearBtn);
		
		cancleBtn = new JButton("Cancel");
		cancleBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		cancleBtn.setBounds(293, 373, 75, 33);
		getContentPane().add(cancleBtn);;
	}
	public void clear(){
		idText.setText("");
		thaiName.setText("");
		contactName.setText("");
		email.setText("");
		telNo.setText("");
		comment.setText("");
		callDay.setText("");
		contactDay.setText("");
	}

	public void setEnable(boolean enable){
		contactName.setEditable(enable);
		email.setEditable(enable);
		telNo.setEditable(enable);
		comment.setEditable(enable);
	}
	
	
	public void setAcctionSubmitBtn(ActionListener action) {
		submitBtn.addActionListener(action);
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
	public String getThaiName() {
		return thaiName.getText();
	}
	public void setThaiName(String thaiName) {
		this.thaiName.setText(thaiName);
	}
	public String getContacName() {
		return contactName.getText();
	}
	public void setContacName(String engName) {
		this.contactName.setText(engName);
	}
	public String getTelNo() {
		return telNo.getText();
	}
	public void setTelNo(String telNo) {
		this.telNo.setText(telNo);
	}
	public String getEmail() {
		return email.getText();
	}
	public void setEmail(String email) {
		this.email.setText(email);
	}
	public String getComment() {
		return comment.getText();
	}
	public void setComment(String comment) {
		this.comment.setText(comment);
	}
	public String getCallDay() {
		return callDay.getText();
	}
	public void setCallDay(String callDay) {
		this.callDay.setText(callDay);
	}
	public String getContactDay() {
		return contactDay.getText();
	}
	public void setContactDay(String contactDay) {
		this.contactDay.setText(contactDay);
	}
//	public boolean setViewText(meetingData data){
//		boolean result = false;
//		idText.setText(data.getComID());
//		thaiName.setText(data.gettName());
//		contactName.setText(data.geteName());
//		address.setText(data.getAddress());
//		email.setText(data.getemail());
//		postNumber.setText(data.getPostNum());
//		telNo.setText(data.getTelNo());
//		provinceBox.setSelectedItem(data.getProvince());
//		comment.setText(data.getRemark());
//		taxID.setText(data.getTaxID());
//		setComType(data.getComType());
//		result = true;
//		return result;
//	}
	public boolean checkInput(){
		boolean result = false;
		if(getThaiName().equals("")){
			JOptionPane.showMessageDialog(null,"Please enter a Thai Name");
		}else{
			result = true;

		}
		return result;
	}
	public void setComID(String comID) {
		idText.setText(comID);
	}
	public String getComID() {
		return idText.getText();
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
		cancleBtn.addActionListener(action);
	}

	public void setData(meetingData data) {
		setComID(data.getCompanyID());
		setThaiName(data.getCompanyThaiName());
		setContactDay(data.getContactDay());
		setContacName(data.getContactTo());
		setCallDay(data.getCallDay());
		setTelNo(data.getPhone());
		setEmail(data.getEmail());
		setComment(data.getDetail());
	}


}


