package tour.tourListDetail;
import java.awt.*;

import javax.swing.*;


import com.base.Tools;
import com.base.icon;
import com.eatType.eatData;
import com.eatType.eatView;
import com.extentclass.numberJTextField;
import com.extentclass.upperFilter;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.border.LineBorder;

public class listDetailView extends JFrame{
	private JTextField THAI_NAME;
	private JTextField THAI_SURNAME;
	private JTextField ENG_NAME;
	private JTextField ENG_SURNAME;
	private JTextField EMAIL;
	private numberJTextField PERSON_ID;
	private JTextField PASSPORT_ID;
	private JTextField TOUR_ID;
	private JComboBox countyBox;
	private JComboBox nationBox; 
	private JComboBox TtitnameBox;
	private JComboBox EtitnameBox;
	private JComboBox BirthdayBox;
	private JComboBox BirthmountBox;
	private JComboBox BirthyearBox;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JComboBox IssuedayBox;
	private JComboBox IssuemountBox;
	private JComboBox ExpiredayBox;
	private JComboBox ExpiremountBox;
	private JComboBox IssueyearBox;
	private JComboBox ExpireyearBox;
	private JComboBox proBox;
	private JEditorPane addressText;
	private JLabel lblPic_1;
	private JFileChooser chooser;
	private JEditorPane editorPaneEatDetail;
	private JButton btnDisable;
	private JButton btnInsert;
	private JButton btnView;
	private JButton btnDeletePic;
	private JButton btnClear;
	private JButton btnCancel;
	private ButtonGroup bg1;
	private JButton btnBrowse;
	private JButton checkBtn;
	private JButton delBtn;
	private JButton btnSave;
//	private JComboBox eatBox;
	private eatView eatPanel;
	
	final JLabel lblPic = new JLabel("pic");
	// parameter output-------------------------//
	private String tTittle,tFirst,tSurname,eTittle,eFirst,eSurname,
	email,birth,personID,passportID,sex,county,picPath,nation,issue,expire,tourID,detail;
	private String proName,postID,telNo,address;
	//------------------------------------------//
	private JPanel mainpanel;
	private JPanel insertPanel;
	private numberJTextField TEL_NO;
	private JLabel addLabel;
	private numberJTextField POST_NO;
//	private ArrayList<String> proData;

	
	public listDetailView() {
		setResizable(false);

		setSize(1335, 598);
		ArrayList<String> proData = Tools.getProvince();
		String[] proName = proData.toArray(new String[proData.size()]);
		String[] Ttitname ={"select","นาย","นาง","นางสาว","เด็กหญิง","เด็กชาย"};
		String[] Etitname = {"select","Mr.","Mrs.","Ms.","Mstr."};
		String[] Tday={"Day","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		String[] Tmount = {"Month","January","February","March","April","May","June","July","August","September","October","November","December"};
		String[] Tyear  = Tools.genYear(20,Tools.getConstant("004"));
		String[] nationList = Tools.getConstant("005").split(",");
		String[] countyList = Tools.getConstant("006").split(",");
//		Icon Image--------------------------
		Image delImg = new ImageIcon(icon.getPicPath("delete.png")).getImage();
		delImg = delImg.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		DocumentFilter dfilter = new upperFilter();
		
		chooser = new JFileChooser();
		bg1 = new ButtonGroup( );
			getContentPane().setLayout(null);
			
			mainpanel = new JPanel();
			mainpanel.setBounds(0, 0, 1319, 551);
			getContentPane().add(mainpanel);
			
			JPanel Picpanel = new JPanel();
			Picpanel.setBounds(10, 5, 647, 535);
			Picpanel.setBorder(new TitledBorder(null, "Pic", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			
			lblPic_1 = new JLabel();				
			lblPic_1.setBounds(16, 56, 621, 461);
			btnBrowse = new JButton("Browse");
			btnBrowse.setBounds(314, 22, 67, 23);
			btnDeletePic = new JButton("Delete Pic");
			btnDeletePic.setBounds(558, 22, 79, 23);
			
			btnView = new JButton("View");
			btnView.setBounds(485, 22, 67, 23);
			
			btnSave = new JButton("Download");
			btnSave.setBounds(387, 22, 88, 23);
			btnSave.setVisible(false);
			
			JPanel Profilepanel = new JPanel();
			Profilepanel.setBounds(667, 5, 417, 490);
			Profilepanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Profile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			
			
			JLabel label = new JLabel("\u0E0A\u0E37\u0E48\u0E2D");
			label.setBounds(16, 48, 15, 15);
			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
					
							
		JLabel lblS = new JLabel("Name");
		lblS.setBounds(16, 96, 31, 15);
		lblS.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		TtitnameBox = new JComboBox(Ttitname);
		TtitnameBox.setBounds(16, 69, 60, 21);
		TtitnameBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
	//										TtitnameBox.setModel(new DefaultComboBoxModel(new String[] {"select", "\u0E19\u0E32\u0E22", "\u0E19\u0E32\u0E07", "\u0E19\u0E32\u0E07\u0E2A\u0E32\u0E27", "\u0E40\u0E14\u0E47\u0E01\u0E0A\u0E32\u0E22", "\u0E40\u0E14\u0E47\u0E01\u0E2B\u0E0D\u0E34\u0E07"}));
		
		
		EtitnameBox = new JComboBox(Etitname);
		EtitnameBox.setBounds(16, 117, 60, 21);
		EtitnameBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		EtitnameBox.setEnabled(false);
		
		THAI_NAME = new JTextField();
		THAI_NAME.setBounds(82, 69, 150, 21);
		THAI_NAME.setFont(new Font("Tahoma", Font.PLAIN, 12));
		THAI_NAME.setColumns(10);
		ENG_NAME = new JTextField();
		ENG_NAME.setBounds(82, 117, 150, 21);
		ENG_NAME.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ENG_NAME.setColumns(10);
		((AbstractDocument) ENG_NAME.getDocument()).setDocumentFilter(dfilter);
	
		rdbtnMale = new JRadioButton("ชาย");
		rdbtnMale.setBounds(16, 145, 60, 23);
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bg1.add(rdbtnMale);
		rdbtnFemale = new JRadioButton("หญิง");
		rdbtnFemale.setBounds(74, 145, 60, 23);
		rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bg1.add(rdbtnFemale);
		
		rdbtnMale.setEnabled(false);
		rdbtnFemale.setEnabled(false);
		
		JLabel label_1 = new JLabel("\u0E19\u0E32\u0E21\u0E2A\u0E01\u0E38\u0E25");
		label_1.setBounds(238, 48, 41, 15);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JLabel lblNewLabel = new JLabel("Surname");
		lblNewLabel.setBounds(238, 96, 48, 15);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		THAI_SURNAME = new JTextField();
		THAI_SURNAME.setBounds(238, 69, 161, 21);
		THAI_SURNAME.setFont(new Font("Tahoma", Font.PLAIN, 12));
		THAI_SURNAME.setColumns(10);
		ENG_SURNAME = new JTextField();
		ENG_SURNAME.setBounds(238, 117, 161, 21);
		ENG_SURNAME.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ENG_SURNAME.setColumns(10);
		((AbstractDocument) ENG_SURNAME.getDocument()).setDocumentFilter(dfilter);
		
		JLabel lblEMail = new JLabel("E mail");
		lblEMail.setBounds(238, 175, 35, 21);
		lblEMail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		EMAIL = new JTextField();
		EMAIL.setBounds(277, 175, 122, 21);
		EMAIL.setFont(new Font("Tahoma", Font.PLAIN, 12));
		EMAIL.setColumns(10);
		
		JLabel lblBirth = new JLabel("Birthday");
		lblBirth.setBounds(16, 336, 70, 21);
		lblBirth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		BirthdayBox = new JComboBox(Tday);
		BirthdayBox.setBounds(90, 336, 52, 21);
		BirthdayBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		BirthmountBox = new JComboBox(Tmount);
		BirthmountBox.setBounds(148, 336, 106, 21);
		BirthmountBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		BirthyearBox = new JComboBox(Tyear);
		BirthyearBox.setBounds(260, 336, 139, 21);
		BirthyearBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblPerson = new JLabel("บัตรประชาชน");
		lblPerson.setBounds(16, 175, 80, 21);
		lblPerson.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		PERSON_ID = new numberJTextField(13);
		PERSON_ID.setBounds(106, 175, 126, 21);
		PERSON_ID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		PERSON_ID.setColumns(15);
		
		JLabel lblNewLabel_1 = new JLabel("หนังสือเดินทาง ");
		lblNewLabel_1.setBounds(16, 202, 80, 21);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		PASSPORT_ID = new JTextField();
		PASSPORT_ID.setBounds(106, 202, 126, 21);
		PASSPORT_ID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		PASSPORT_ID.setColumns(15);
		((AbstractDocument) PASSPORT_ID.getDocument()).setDocumentFilter(dfilter);
		
		
		nationBox = new JComboBox(nationList);
		nationBox.setBounds(90, 309, 89, 21);
		nationBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		countyBox = new JComboBox(countyList);
		countyBox.setBounds(237, 309, 162, 21);
		countyBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel label_3 = new JLabel("\u0E2A\u0E31\u0E0D\u0E0A\u0E32\u0E15\u0E34");
		label_3.setBounds(16, 309, 70, 21);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel label_4 = new JLabel("\u0E1B\u0E23\u0E30\u0E40\u0E17\u0E28");
		label_4.setBounds(183, 309, 50, 21);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel label_5 = new JLabel("Issue Date");
		label_5.setBounds(16, 363, 70, 21);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		IssuedayBox = new JComboBox(Tday);
		IssuedayBox.setBounds(90, 363, 52, 21);
		IssuedayBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		IssuemountBox = new JComboBox(Tmount);
		IssuemountBox.setBounds(148, 363, 106, 21);
		IssuemountBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		IssueyearBox = new JComboBox(Tyear);
		IssueyearBox.setBounds(260, 363, 139, 21);
		IssueyearBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		ExpiremountBox = new JComboBox(Tmount);
		ExpiremountBox.setBounds(148, 390, 106, 21);
		ExpiremountBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		ExpiredayBox = new JComboBox(Tday);
		ExpiredayBox.setBounds(90, 390, 52, 21);
		ExpiredayBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ExpireyearBox = new JComboBox(Tyear);
		ExpireyearBox.setBounds(260, 390, 139, 21);
		ExpireyearBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblExpireDate = new JLabel("Expire Date");
		lblExpireDate.setBounds(16, 390, 70, 21);
		lblExpireDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblEatDetail = new JLabel("หมายเหตุ");
		lblEatDetail.setBounds(16, 417, 70, 21);
		lblEatDetail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		editorPaneEatDetail = new JEditorPane();
		editorPaneEatDetail.setBounds(90, 422, 309, 50);
		editorPaneEatDetail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		TOUR_ID = new JTextField();
		TOUR_ID.setBounds(74, 21, 100, 21);
		TOUR_ID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		TOUR_ID.setColumns(10);
		TOUR_ID.setEditable(false);
		
		JLabel lblNewLabel_2 = new JLabel("TOUR ID");
		lblNewLabel_2.setBounds(16, 21, 48, 21);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel telLabel = new JLabel("Tel");
		telLabel.setBounds(238, 202, 35, 21);
		telLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		TEL_NO = new numberJTextField(10);
	//										TEL_NO = new JTextField();
		TEL_NO.setBounds(277, 202, 122, 21);
		TEL_NO.setFont(new Font("Tahoma", Font.PLAIN, 12));
		TEL_NO.setColumns(10);
		
		addLabel = new JLabel("ที่อยู่");
		addLabel.setBounds(16, 229, 47, 21);
		addLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		addressText = new JEditorPane();
		addressText.setBounds(90, 229, 309, 42);
		addressText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel postLable = new JLabel("รหัสไปรณีย์");
		postLable.setBounds(16, 277, 70, 21);
		postLable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		POST_NO = new numberJTextField(5);
		POST_NO.setBounds(90, 277, 89, 21);
		POST_NO.setFont(new Font("Tahoma", Font.PLAIN, 12));
		POST_NO.setColumns(15);
		
		JLabel proLabel = new JLabel("จังหวัด");
		proLabel.setBounds(183, 277, 50, 21);
		proLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		proBox = new JComboBox(proName);
		proBox.setBounds(237, 277, 162, 21);
		proBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		insertPanel = new JPanel();
		insertPanel.setBounds(667, 506, 642, 33);
		Profilepanel.setLayout(null);
		Profilepanel.add(lblEatDetail);
		Profilepanel.add(editorPaneEatDetail);
		Profilepanel.add(lblNewLabel_2);
		Profilepanel.add(TOUR_ID);
		Profilepanel.add(label);
		Profilepanel.add(TtitnameBox);
		Profilepanel.add(THAI_NAME);
		Profilepanel.add(EtitnameBox);
		Profilepanel.add(ENG_NAME);
		Profilepanel.add(lblS);
		Profilepanel.add(rdbtnMale);
		Profilepanel.add(rdbtnFemale);
		Profilepanel.add(lblNewLabel_1);
		Profilepanel.add(lblPerson);
		Profilepanel.add(PASSPORT_ID);
		Profilepanel.add(PERSON_ID);
		Profilepanel.add(lblNewLabel);
		Profilepanel.add(label_1);
		Profilepanel.add(telLabel);
		Profilepanel.add(lblEMail);
		Profilepanel.add(EMAIL);
		Profilepanel.add(TEL_NO);
		Profilepanel.add(THAI_SURNAME);
		Profilepanel.add(ENG_SURNAME);
		Profilepanel.add(label_5);
		Profilepanel.add(IssuedayBox);
		Profilepanel.add(IssuemountBox);
		Profilepanel.add(lblExpireDate);
		Profilepanel.add(ExpiredayBox);
		Profilepanel.add(ExpiremountBox);
		Profilepanel.add(lblBirth);
		Profilepanel.add(BirthdayBox);
		Profilepanel.add(BirthmountBox);
		Profilepanel.add(BirthyearBox);
		Profilepanel.add(IssueyearBox);
		Profilepanel.add(ExpireyearBox);
		Profilepanel.add(label_3);
		Profilepanel.add(nationBox);
		Profilepanel.add(postLable);
		Profilepanel.add(addLabel);
		Profilepanel.add(POST_NO);
		Profilepanel.add(proLabel);
		Profilepanel.add(label_4);
		Profilepanel.add(countyBox);
		Profilepanel.add(proBox);
		Profilepanel.add(addressText);
		
		
		btnInsert = new JButton("OK");
		btnInsert.setFont(new Font("Tahoma", Font.BOLD, 11));
		insertPanel.add(btnInsert);
		btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 11));
		insertPanel.add(btnClear);
		
		btnCancel = new JButton("Cancel");
		insertPanel.add(btnCancel);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
	
		btnDisable = new JButton("Enable/Disable Edit");
		btnDisable.setVisible(false);
		insertPanel.add(btnDisable);
		btnDisable.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		mainpanel.setLayout(null);
		mainpanel.add(Picpanel);
		Picpanel.setLayout(null);
		Picpanel.add(btnSave);
		Picpanel.add(btnBrowse);
		Picpanel.add(btnView);
		Picpanel.add(btnDeletePic);
		Picpanel.add(lblPic_1);
		mainpanel.add(insertPanel);
		mainpanel.add(Profilepanel);
		
		checkBtn = new JButton("Check Name");
		checkBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkBtn.setBounds(198, 21, 100, 23);
		Profilepanel.add(checkBtn);
		
		delBtn = new JButton("delete");
		delBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		delBtn.setBounds(308, 21, 91, 23);
		delBtn.setIcon(new ImageIcon(delImg));
		delBtn.setVisible(false);
		Profilepanel.add(delBtn);
		
		
		eatPanel = new eatView();
		eatPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Eat Detail", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		eatPanel.setBounds(1090, 5, 219, 490);
		mainpanel.add(eatPanel);
		addAction();
	}
	

	public JButton getBtnSubmit() {
		return this.btnInsert;
	}


	public JButton getBtnDisable() {
		return btnDisable;
	}

////// control only UI
	
	public ArrayList<String> thaiName(){
		ArrayList<String> result = new ArrayList<String>();
		result.add(Tools.getStringJBox(TtitnameBox));
		result.add(THAI_NAME.getText());
		result.add(THAI_SURNAME.getText());
		return result;
	}
	
	public boolean setStringData (){
		boolean setComplete = false;
		if(checkInput()){
			tTittle= Tools.getStringJBox(TtitnameBox);
			tFirst=THAI_NAME.getText();
			tSurname=THAI_SURNAME.getText();
			eTittle=Tools.getStringJBox(EtitnameBox);
			eFirst=ENG_NAME.getText();
			eSurname=ENG_SURNAME.getText();
			personID=PERSON_ID.getText();
			passportID=PASSPORT_ID.getText();
			email=EMAIL.getText();
			county= Tools.getStringJBox(countyBox);
			nation= Tools.getStringJBox(nationBox);

			if(rdbtnMale.isSelected()){
				  sex="M";
			}else if(rdbtnFemale.isSelected()){
				  sex="F";
			}
			tourID=TOUR_ID.getText();
			detail=editorPaneEatDetail.getText();
			proName = Tools.getStringJBox(proBox);
			postID = POST_NO.getText();
			telNo = TEL_NO.getText();
			address = addressText.getText();
			setComplete = true;
		}
		return setComplete;

	}
	
	private void addAction(){
		
		TtitnameBox.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	if(TtitnameBox.getSelectedIndex()==0){
	        		EtitnameBox.setSelectedIndex(0);
	        		bg1.clearSelection();
	        	}else if(TtitnameBox.getSelectedIndex()==1){
	        		EtitnameBox.setSelectedIndex(1);
	        		rdbtnMale.setSelected(true);
	        	}else if(TtitnameBox.getSelectedIndex()==2){
	        		EtitnameBox.setSelectedIndex(2);
	        		rdbtnFemale.setSelected(true);
	        	}else if(TtitnameBox.getSelectedIndex()==3){
	        		EtitnameBox.setSelectedIndex(3);
	        		rdbtnFemale.setSelected(true);
	        	}else if(TtitnameBox.getSelectedIndex()==4){
	        		EtitnameBox.setSelectedIndex(3);
	        		rdbtnFemale.setSelected(true);
	        	}else if(TtitnameBox.getSelectedIndex()==5){
	        		EtitnameBox.setSelectedIndex(4);
	        		rdbtnMale.setSelected(true);
	        	}
	        }
	    });
	}
	
	private boolean checkInput(){
		boolean result = false;
		// set day for user not choose date
		this.birth = null;
		this.issue = null;
		this.expire = null;
		
		if(TtitnameBox.getSelectedIndex() ==0
				||THAI_NAME.getText().equals("")||THAI_SURNAME.getText().equals("")){	
			JOptionPane.showMessageDialog(null,"Please enter a valid Thai Name");			
		}else if(!rdbtnMale.isSelected()&&!rdbtnFemale.isSelected()){
			JOptionPane.showMessageDialog(null,"Please enter a gender");
		}else{
			result = true;	
			
			//check birth//
			if(((BirthdayBox.getSelectedIndex()!=0)||
					(BirthmountBox.getSelectedIndex()!=0)||
					(BirthyearBox.getSelectedIndex()!=0))){
				this.birth = Tools.genTextDate(BirthdayBox.getSelectedIndex()
						,BirthmountBox.getSelectedIndex(),
						(String)BirthyearBox.getSelectedItem());
				if(this.birth == null){
					JOptionPane.showMessageDialog(null,"Please enter a valid birthday");
					result = false;
				}
			}
			
			//check issue//
			if(((IssuedayBox.getSelectedIndex()!=0)||
					(IssuemountBox.getSelectedIndex()!=0)||
					(IssueyearBox.getSelectedIndex()!=0))){
				
				this.issue = Tools.genTextDate(IssuedayBox.getSelectedIndex()
						,IssuemountBox.getSelectedIndex(),
						(String)IssueyearBox.getSelectedItem());
				if(this.issue == null){			
					JOptionPane.showMessageDialog(null,"Please enter a valid Issue day");
					result = false;
				}
		
			} 
			
			//check expire//
			if (((ExpiredayBox.getSelectedIndex()!=0)||
					(ExpiremountBox.getSelectedIndex()!=0)||
					(ExpireyearBox.getSelectedIndex()!=0))){
				
				this.expire = Tools.genTextDate(ExpiredayBox.getSelectedIndex()
						,ExpiremountBox.getSelectedIndex(),
						(String)ExpireyearBox.getSelectedItem());
				if(this.expire == null){
					
					JOptionPane.showMessageDialog(null,"Please enter a valid Expire day");
					result = false;
				}
			}
			//Check
			
		}
		return result;
	}

	public JButton getBtnBrowse() {
		return btnBrowse;
	}
	
	public JButton getBtnView() {
		return btnView;
	}

	public JButton getBtnDelPic() {
		return btnDeletePic;
	}
	
	public JButton getBtnSavePic() {
		return btnSave;
	}
	public JButton getBtnClear() {
		return btnClear;
	}
	public void setClear(JButton btnClear) {
		this.btnClear = btnClear;
	}
	
	public JButton getBtnCancel() {
		return btnCancel;
	}

	public JButton getCheckBtn() {
		return checkBtn;
	}
	public JButton getDelTourBtn() {
		return delBtn;
	}
	public JFileChooser getChooser() {
		return chooser;
	}


//---------------- get String data + Picfile------------//	

	public String getTourID(){
		return this.tourID;
	}
	public String getTTittle(){
		return this.tTittle;
	}
	public String getTFrist(){
		return this.tFirst;
	}
	public String getTSurname(){
		return this.tSurname;
	}
	public String getETittle(){
		return this.eTittle;
	}
	public String getEFirst(){
		return this.eFirst;
	}
	public String getESurname(){
		return this.eSurname;
	}
	public String getSex(){
		return this.sex;
	}
	public String getPersonID(){
		return this.personID;
	}
	public String getPassport(){
		return this.passportID;
	}
	public String getEmail(){
		return this.email;
	}
	public String getCounty(){
		return this.county;
	}
	public String getNation(){
		return this.nation;
	}
	public String getBirth(){
		return this.birth;
	}
	public String getIssue(){
		return this.issue;
	}
	public String getExpire(){
		return this.expire;
	}
	public String getDetail(){
		return this.detail;
	}
	public String getPathPic(){
		return this.picPath;
	}
	public String getPostNo(){
		return this.postID;
	}
	public String getAddress(){
		return this.address;
	}
	public String getProvice(){
		return this.proName;
	}
	public String getTelNo(){
		return this.telNo;
	}
	public eatData getEat(){
		return eatPanel.getData();
	}
	
	public void setPicLable(BufferedImage image){
		if(Tools.isEmpty(image)){
			lblPic_1.setIcon(null);
		}else{
			int width = lblPic_1.getWidth();
			int height = lblPic_1.getHeight();
			ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));;
			lblPic_1.setIcon(imageIcon);
		}
	}
	public void clear(){
		THAI_NAME.setText("");
		THAI_SURNAME.setText("");
		ENG_NAME.setText("");
		ENG_SURNAME.setText("");
		EMAIL.setText("");
		PERSON_ID.setText("");
		PASSPORT_ID.setText("");
		nationBox.setSelectedIndex(0);
		TOUR_ID.setText("");
		countyBox.setSelectedIndex(0);
		TtitnameBox.setSelectedIndex(0);
		EtitnameBox.setSelectedIndex(0);
		BirthdayBox.setSelectedIndex(0);
		BirthmountBox.setSelectedIndex(0);
		BirthyearBox.setSelectedIndex(0);
		bg1.clearSelection();
		IssuedayBox.setSelectedIndex(0);
		IssuemountBox.setSelectedIndex(0);
		ExpiredayBox.setSelectedIndex(0);
		ExpiremountBox.setSelectedIndex(0);
		IssueyearBox.setSelectedIndex(0);
		ExpireyearBox.setSelectedIndex(0);
		editorPaneEatDetail.setText("");
		POST_NO.setText("");
		TEL_NO.setText("");
		proBox.setSelectedIndex(0);
		addressText.setText("");
		eatPanel.setSelectAll(false);
		setPicLable(null);
		
	}
	public void setViewText(listDetailData data){
		THAI_NAME.setText(data.gettFirst());
		THAI_SURNAME.setText(data.gettSurName());
		ENG_NAME.setText(data.geteFirst());
		ENG_SURNAME.setText(data.geteSurName());
		EMAIL.setText(data.getemail());
		PERSON_ID.setText(data.getPersonID());
		PASSPORT_ID.setText(data.getPassportID());
		if(data.getNationality()== null){
			nationBox.setSelectedIndex(0);
		}else{
			nationBox.setSelectedItem(data.getNationality());
		}
		TOUR_ID.setText(data.getTourID());
		if(data.getCountry()== null){
			countyBox.setSelectedIndex(0);
		}else{
			countyBox.setSelectedItem(data.getCountry());
		}
		TtitnameBox.setSelectedItem(data.gettTittle());
		if(data.geteTitle()== null){
			EtitnameBox.setSelectedIndex(0);
		}else{
			EtitnameBox.setSelectedItem(data.geteTitle());
		}
		if(data.getBirthDay()==null){
			BirthdayBox.setSelectedIndex(0);
			BirthmountBox.setSelectedIndex(0);
			BirthyearBox.setSelectedIndex(0);	
		}else{
			ArrayList<Integer> tempBirth  = Tools.spitDate(data.getBirthDay());
			BirthdayBox.setSelectedIndex(tempBirth.get(0));
			BirthmountBox.setSelectedIndex(tempBirth.get(1));
			BirthyearBox.setSelectedItem(tempBirth.get(2).toString());			
		}

		if(data.getSex() == null){
			bg1.clearSelection();
		}else if (data.getSex().equals("F")){
			rdbtnFemale.setSelected(true);
		}else{
			rdbtnMale.setSelected(true);
		}
		if(data.getIssueDate()==null){
			IssuedayBox.setSelectedIndex(0);
			IssuemountBox.setSelectedIndex(0);
			IssueyearBox.setSelectedIndex(0);
		}else{
			ArrayList<Integer> tempIssue  = Tools.spitDate(data.getIssueDate());
			IssuedayBox.setSelectedIndex(tempIssue.get(0));
			IssuemountBox.setSelectedIndex(tempIssue.get(1));
			IssueyearBox.setSelectedItem(tempIssue.get(2).toString());
		}
		if(data.getExpireDate()==null){
			ExpiredayBox.setSelectedIndex(0);
			ExpiremountBox.setSelectedIndex(0);
			ExpireyearBox.setSelectedIndex(0);
		}else{
			ArrayList<Integer> tempExpire  = Tools.spitDate(data.getExpireDate());
			ExpiredayBox.setSelectedIndex(tempExpire.get(0));
			ExpiremountBox.setSelectedIndex(tempExpire.get(1));
			ExpireyearBox.setSelectedItem(tempExpire.get(2).toString());
		}
		editorPaneEatDetail.setText(data.getDetail());
		POST_NO.setText(data.getPostNo());
		TEL_NO.setText(data.getTelNo());
		if(data.getProvince()== null){
			proBox.setSelectedIndex(0);
		}else{
			proBox.setSelectedItem(data.getProvince());
		}
		addressText.setText(data.getAddress());
		eatPanel.setData(data.getEat());
		
	}

	public boolean setEnable(boolean enable){
		boolean result = false;
		THAI_NAME.setEditable(enable);
		THAI_SURNAME.setEditable(enable);
		ENG_NAME.setEditable(enable);
		ENG_SURNAME.setEditable(enable);
		EMAIL.setEditable(enable);
		PERSON_ID.setEditable(enable);
		PASSPORT_ID.setEditable(enable);
		nationBox.setEnabled(enable);
		countyBox.setEnabled(enable);
		TtitnameBox.setEnabled(enable);
		BirthdayBox.setEnabled(enable);
		BirthmountBox.setEnabled(enable);
		BirthyearBox.setEnabled(enable);
		IssuedayBox.setEnabled(enable);
		IssuemountBox.setEnabled(enable);
		ExpiredayBox.setEnabled(enable);
		ExpiremountBox.setEnabled(enable);
		IssueyearBox.setEnabled(enable);
		ExpireyearBox.setEnabled(enable);
		editorPaneEatDetail.setEditable(enable);
		POST_NO.setEditable(enable);
		TEL_NO.setEditable(enable);
		proBox.setEnabled(enable);
		addressText.setEditable(enable);
		btnBrowse.setEnabled(enable);
		btnDeletePic.setEnabled(enable);
		eatPanel.setEnableAll(enable);
		result = true;
		return result;
	}
}
