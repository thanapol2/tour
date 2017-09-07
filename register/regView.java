package register;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import com.base.Tools;
import com.extentclass.upperFilter;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Choice;

public class regView extends JFrame {

	private JPanel contentPane;
	private JComboBox saleText;
	private JTextField THAI_NAME;
	private JTextField THAI_SURNAME;
	private JTextField ENG_NAME;
	private JTextField ENG_SURNAME;
	private JComboBox TtitnameBox;
	private JComboBox EtitnameBox;
	private JComboBox roleBox;
	private JLabel lable;
	private JTextField userName;
	private JLabel lblPassword;
	private JTextField passText;
	private JButton btnUpdateData;

	/**
	 * Create the frame.
	 */
	public regView() {
		String[] Ttitname ={"select","นาย","นาง","นางสาว"};
		String[] Etitname = {"select","Mr.","Mrs.","Ms."};
		String[] role = {"NORMAL","ACCOUNT","SUB"};
		DocumentFilter dfilter = new upperFilter();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("ชื่อ");
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(104, 166, 34, 33);
		contentPane.add(label);
		
		JLabel lblSaleId = new JLabel("Sale ID");
		lblSaleId.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSaleId.setBounds(10, 11, 48, 33);
		contentPane.add(lblSaleId);
		
		saleText = new JComboBox();
		saleText.setFont(new Font("Dialog", Font.PLAIN, 14));
//		saleText.setColumns(10);
		saleText.setBounds(105, 11, 175, 33);
		contentPane.add(saleText);
		
		TtitnameBox = new JComboBox(Ttitname);
		TtitnameBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		TtitnameBox.setBounds(10, 210, 84, 33);
		contentPane.add(TtitnameBox);
		
		THAI_NAME = new JTextField();
		THAI_NAME.setFont(new Font("Dialog", Font.PLAIN, 14));
		THAI_NAME.setColumns(10);
		THAI_NAME.setBounds(104, 210, 197, 33);
		contentPane.add(THAI_NAME);
		
		THAI_SURNAME = new JTextField();
		THAI_SURNAME.setFont(new Font("Dialog", Font.PLAIN, 14));
		THAI_SURNAME.setColumns(10);
		THAI_SURNAME.setBounds(323, 209, 213, 33);
		contentPane.add(THAI_SURNAME);
		
		JLabel label_2 = new JLabel("นามสกุล");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_2.setBounds(323, 166, 84, 33);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Name");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_3.setBounds(104, 242, 53, 33);
		contentPane.add(label_3);
		
		EtitnameBox = new JComboBox(Etitname);
		EtitnameBox.setFont(new Font("Dialog", Font.PLAIN, 14));
		EtitnameBox.setEnabled(false);
		EtitnameBox.setBounds(10, 279, 84, 33);
		contentPane.add(EtitnameBox);
		
		ENG_NAME = new JTextField();
		ENG_NAME.setFont(new Font("Dialog", Font.PLAIN, 14));
		ENG_NAME.setColumns(10);
		ENG_NAME.setBounds(104, 279, 197, 33);
		contentPane.add(ENG_NAME);
		
		ENG_SURNAME = new JTextField();
		ENG_SURNAME.setFont(new Font("Dialog", Font.PLAIN, 14));
		ENG_SURNAME.setColumns(10);
		ENG_SURNAME.setBounds(323, 279, 213, 33);
		contentPane.add(ENG_SURNAME);
		
		JLabel label_4 = new JLabel("Surname");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_4.setBounds(323, 242, 66, 33);
		contentPane.add(label_4);
		
		lable = new JLabel("User name");
		lable.setFont(new Font("Dialog", Font.PLAIN, 14));
		lable.setBounds(10, 70, 84, 33);
		contentPane.add(lable);
		
		userName = new JTextField();
		userName.setFont(new Font("Dialog", Font.PLAIN, 14));
		userName.setColumns(10);
		userName.setBounds(104, 70, 176, 33);
		((AbstractDocument) userName.getDocument()).setDocumentFilter(dfilter);
		contentPane.add(userName);
		
		lblPassword = new JLabel("password");
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPassword.setBounds(10, 122, 84, 33);
		contentPane.add(lblPassword);
		
		passText = new JTextField();
		passText.setFont(new Font("Dialog", Font.PLAIN, 14));
		passText.setColumns(10);
		passText.setBounds(104, 122, 176, 33);
		contentPane.add(passText);
		
		btnUpdateData = new JButton("Update Data");
		btnUpdateData.setBounds(442, 323, 94, 33);
		contentPane.add(btnUpdateData);
		
		roleBox = new JComboBox(role);
		roleBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		roleBox.setBounds(412, 70, 124, 33);
		contentPane.add(roleBox);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblRole.setBounds(323, 70, 84, 33);
		contentPane.add(lblRole);
		
		addAction();
	}
	
	public void addSalesIDAction(ItemListener action){
		saleText.addItemListener(action);
	}
	
	private void addAction(){	
		TtitnameBox.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	if(TtitnameBox.getSelectedIndex()==0){
	        		EtitnameBox.setSelectedIndex(0);
	        	}else if(TtitnameBox.getSelectedIndex()==1){
	        		EtitnameBox.setSelectedIndex(1);
	        	}else if(TtitnameBox.getSelectedIndex()==2){
	        		EtitnameBox.setSelectedIndex(2);
	        	}else if(TtitnameBox.getSelectedIndex()==3){
	        		EtitnameBox.setSelectedIndex(3);
	        	}
	        }
	    });
	}
	public regData getData(){
		regData data = new regData();
		data.setSaleID((String)saleText.getSelectedItem());
		data.settTittle((String)TtitnameBox.getSelectedItem());
		data.settFirst(THAI_NAME.getText());
		data.settSurName(THAI_SURNAME.getText());
		data.seteTitle((String)EtitnameBox.getSelectedItem());
		data.seteFirst(ENG_NAME.getText());
		data.seteSurName(ENG_SURNAME.getText());
		data.setUser(userName.getText());
		data.setPass(passText.getText());
		data.setRole((String)roleBox.getSelectedItem());
		return data;
	}

	public void setData(regData data){
		saleText.setSelectedItem(data.getSaleID());
		TtitnameBox.setSelectedItem(data.gettTittle());
		THAI_NAME.setText(data.gettFirst());
		THAI_SURNAME.setText(data.gettSurName());
		EtitnameBox.setSelectedItem(data.geteTitle());
		ENG_NAME.setText(data.geteFirst());
		ENG_SURNAME.setText(data.geteSurName());
		userName.setEditable(Tools.isEmpty(data.getUser()));
		userName.setText(data.getUser());
		passText.setText(data.getPass());
		roleBox.setSelectedItem(data.getRole());
	}
	public boolean isNewUser(){
		return userName.isEditable();
	}
	
	public void setUpateAction(ActionListener action) {
		btnUpdateData.addActionListener(action);
	}



	public void setSaleID(ArrayList<String> data){
		DefaultComboBoxModel model = new DefaultComboBoxModel(data.toArray(new String[data.size()]));
		saleText.setModel(model);
	}
	public String getSaleID() {
		return (String)saleText.getSelectedItem();
		
	}

	public int getSelectSaleID() {
		return saleText.getSelectedIndex();
		
	}
	public boolean checkData() {
		boolean result= true;
		StringBuilder text = new StringBuilder();
//		if(userName.getText().length()<5){
//			text.append(" - User < 5 char\n");
//			result = false;
//		}
		if(passText.getText().length()<4){
			text.append(" - Password < 4 char\n");
			result = false;
		}
		if(roleBox.getSelectedIndex()<0){
			text.append(" - Check Role Type");
			result = false;
		}
		if(result){
			
		}else{
			JOptionPane.showMessageDialog(this,text.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
}
