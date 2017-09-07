package invoice.invoiceDetail;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.base.Tools;
import com.base.icon;
import com.extentclass.JTableAddFilter;
import com.extentclass.numberJTextField;
import com.extentclass.selectOneRowModel;

import invoice.addCustomer.invoiceCustomData;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;

public class invoiceDetailView extends JDialog {
	private JTextField invoiceText;
	private JTextField cusNoText;
	private JTextField cusNameText;
	private JTextField attnText;
	private JTextField taxText;
	private JTextField telText;
	private JTextField emailText;
	private JTextArea addressText;
	private numberJTextField saleIDText;
	private JTextField saleNameText;
	private JComboBox issueDay;
	private JComboBox issueMon;
	private JComboBox issueYear;
	private JButton firstBtn;
	private JButton findBtn;
	private JButton clearCustomerBtn;
	private JButton upBtn;
	private JButton downBtn;
	private JButton lastBtn;
	private JButton delBtn;
	private JTable table;
	private JButton addBtn;
	private JTextField vatText;
	private JTextField totalText;
	private JRadioButton vatCalBox;
	private JRadioButton adjustVat;
	private JRadioButton noVat;
	private JButton submitBtn;
	private JButton revisedBtn;
	private JButton paymentBtn;
	private JButton cancelBtn;
	private JButton printBtn;
	private JComboBox printType;
	private numberJTextField dueDay;
	private JTextField refText;
	private JTextField subjectText;
	private String vat;
	private String total;
	
	public invoiceDetailView() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 976, 730);
		String[] Tday={"Day","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		String[] Tmon = {"Mon","1","2","3","4","5","6","7","8","9","10","11","12"};
		String tempYear = Integer.toString(Calendar.getInstance().get(Calendar.YEAR)-2);
		String[] Tyear  = Tools.genYear(10,tempYear);
		String[] print = {"Invoice Thai","Invoice Eng","Payment"};
		// add filter table
		ArrayList<Integer> colNum = new ArrayList(Arrays.asList(2,4));
		ArrayList<Integer> colDobule = new ArrayList(Arrays.asList(3));
		// add 
		
		Image printImg = new ImageIcon(icon.getPicPath("print.png")).getImage();
		printImg = printImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		Image revisedImg = new ImageIcon(icon.getPicPath("invoice.png")).getImage();
		revisedImg = revisedImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		Image paymentImg = new ImageIcon(icon.getPicPath("payment.png")).getImage();
		paymentImg = paymentImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		Image upImg = new ImageIcon(icon.getPicPath("up.png")).getImage();
		upImg = upImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		Image downImg = new ImageIcon(icon.getPicPath("down.png")).getImage();
		downImg = downImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		Image lastImg = new ImageIcon(icon.getPicPath("last.png")).getImage();
		lastImg = lastImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		Image firstImg = new ImageIcon(icon.getPicPath("first.png")).getImage();
		firstImg = firstImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		Image delImg = new ImageIcon(icon.getPicPath("remove.png")).getImage();
		delImg = delImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		Image addImg = new ImageIcon(icon.getPicPath("add.png")).getImage();
		addImg = addImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		
		
		
		setTitle("Invoice");
		setResizable(false);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 72, 950, 87);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel salesIDLable = new JLabel("Sales ID :");
		salesIDLable.setFont(new Font("Tahoma", Font.BOLD, 14));
		salesIDLable.setBounds(363, 5, 67, 33);
		panel.add(salesIDLable);
		
		saleIDText = new numberJTextField(2);
		saleIDText.setEnabled(false);
		saleIDText.setFont(new Font("Tahoma", Font.BOLD, 14));
		saleIDText.setColumns(10);
		saleIDText.setBounds(440, 5, 67, 33);
		panel.add(saleIDText);
		
		JLabel sNamelabel = new JLabel("Sales Name :");
		sNamelabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		sNamelabel.setBounds(517, 5, 99, 33);
		panel.add(sNamelabel);
		
		saleNameText = new JTextField();
		saleNameText.setEnabled(false);
//		saleNameText.setEnabled(false);
		saleNameText.setFont(new Font("Tahoma", Font.BOLD, 14));
		saleNameText.setColumns(10);
		saleNameText.setBounds(626, 5, 314, 33);
		panel.add(saleNameText);
		
		JLabel lblIssueDate = new JLabel("Issue Date :");
		lblIssueDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIssueDate.setBounds(10, 45, 86, 33);
		panel.add(lblIssueDate);
		
		issueDay = new JComboBox(Tday);
		issueDay.setEditable(true);
		issueDay.setEnabled(false);
		issueDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		issueDay.setBounds(106, 45, 67, 33);
		panel.add(issueDay);
		
		issueMon = new JComboBox(Tmon);
		issueMon.setEditable(true);
		issueMon.setEnabled(false);
		issueMon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		issueMon.setBounds(183, 45, 73, 33);
		panel.add(issueMon);
		
		issueYear = new JComboBox(Tyear);
		issueYear.setEditable(true);
		issueYear.setEnabled(false);
		issueYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		issueYear.setBounds(266, 45, 86, 33);
		panel.add(issueYear);
		
		JLabel dueLable = new JLabel("Due Day :");
		dueLable.setFont(new Font("Tahoma", Font.BOLD, 14));
		dueLable.setBounds(362, 45, 86, 33);
		panel.add(dueLable);
		
		JLabel inNoLable = new JLabel("Order No :");
		inNoLable.setBounds(10, 5, 79, 33);
		panel.add(inNoLable);
		inNoLable.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		invoiceText = new JTextField();
		invoiceText.setBounds(107, 5, 180, 33);
		panel.add(invoiceText);
		invoiceText.setEditable(false);
		invoiceText.setFont(new Font("Tahoma", Font.BOLD, 14));
		invoiceText.setColumns(10);
		
		dueDay = new numberJTextField(3);
		dueDay.setFont(new Font("Tahoma", Font.BOLD, 14));
		dueDay.setColumns(10);
		dueDay.setBounds(440, 45, 67, 33);
		panel.add(dueDay);
		
		JPanel cusPanel = new JPanel();
		cusPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Customer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cusPanel.setBounds(10, 160, 950, 203);
		getContentPane().add(cusPanel);
		cusPanel.setLayout(null);
		
		JLabel cusNoLable = new JLabel("Customer No :");
		cusNoLable.setFont(new Font("Tahoma", Font.BOLD, 14));
		cusNoLable.setBounds(10, 20, 108, 30);
		cusPanel.add(cusNoLable);
		
		cusNoText = new JTextField();
		cusNoText.setEnabled(false);
		cusNoText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cusNoText.setColumns(10);
		cusNoText.setBounds(128, 20, 124, 30);
		cusPanel.add(cusNoText);
		
		JLabel cusNameLable = new JLabel("Customer Name :");
		cusNameLable.setFont(new Font("Tahoma", Font.BOLD, 14));
		cusNameLable.setBounds(262, 20, 133, 30);
		cusPanel.add(cusNameLable);
		
		cusNameText = new JTextField();
		cusNameText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cusNameText.setColumns(10);
		cusNameText.setBounds(405, 20, 385, 30);
		cusPanel.add(cusNameText);
		
		JLabel AttnLable = new JLabel("Attention  :");
		AttnLable.setFont(new Font("Tahoma", Font.BOLD, 14));
		AttnLable.setBounds(10, 54, 96, 30);
		cusPanel.add(AttnLable);
		
		attnText = new JTextField();
		attnText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		attnText.setColumns(10);
		attnText.setBounds(128, 54, 185, 30);
		cusPanel.add(attnText);
		
		JLabel addressLable = new JLabel("Address :");
		addressLable.setFont(new Font("Tahoma", Font.BOLD, 14));
		addressLable.setBounds(10, 95, 96, 30);
		cusPanel.add(addressLable);
		
		addressText = new JTextArea();
		addressText.setBounds(128, 95, 494, 55);
		cusPanel.add(addressText);
		
		JLabel taxLable = new JLabel("Tax ID  :");
		taxLable.setFont(new Font("Tahoma", Font.BOLD, 14));
		taxLable.setBounds(323, 54, 72, 30);
		cusPanel.add(taxLable);
		
		taxText = new JTextField();
		taxText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taxText.setColumns(10);
		taxText.setBounds(405, 55, 217, 30);
		cusPanel.add(taxText);
		
		JLabel telLable = new JLabel("Tel/Fax     :");
		telLable.setFont(new Font("Tahoma", Font.BOLD, 14));
		telLable.setBounds(630, 54, 96, 30);
		cusPanel.add(telLable);
		
		telText = new JTextField();
		telText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		telText.setColumns(10);
		telText.setBounds(737, 55, 203, 30);
		cusPanel.add(telText);
		
		JLabel emaillabel = new JLabel("Email     :");
		emaillabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		emaillabel.setBounds(630, 95, 74, 30);
		cusPanel.add(emaillabel);
		
		emailText = new JTextField();
		emailText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailText.setColumns(10);
		emailText.setBounds(737, 93, 203, 30);
		cusPanel.add(emailText);
		
		findBtn = new JButton("Find Customer");
		findBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		findBtn.setBounds(798, 20, 142, 30);
		cusPanel.add(findBtn);
		
		clearCustomerBtn = new JButton("Clear Header");
		clearCustomerBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		clearCustomerBtn.setBounds(798, 161, 142, 30);
		cusPanel.add(clearCustomerBtn);
		
		JLabel lblSubject = new JLabel("REF NO :");
		lblSubject.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSubject.setBounds(10, 161, 96, 30);
		cusPanel.add(lblSubject);
		
		refText = new JTextField();
		refText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		refText.setColumns(10);
		refText.setBounds(128, 161, 185, 30);
		cusPanel.add(refText);
		
		JLabel label = new JLabel("Subject :");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(319, 161, 76, 30);
		cusPanel.add(label);
		
		subjectText = new JTextField();
		subjectText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		subjectText.setColumns(10);
		subjectText.setBounds(405, 161, 217, 30);
		cusPanel.add(subjectText);
		
		
		table = new JTableAddFilter(colNum);
		table.setSelectionModel(new selectOneRowModel());
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setFillsViewportHeight(true);
		table.getTableHeader().setResizingAllowed(false);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new tableInvoice());
		table.getTableHeader().setReorderingAllowed(false);

		
		
		delBtn = new JButton("");
		delBtn.setBounds(930, 408, 30, 30);
		delBtn.setIcon(new ImageIcon(delImg));
		getContentPane().add(delBtn);
		
		firstBtn = new JButton("");
		firstBtn.setBounds(930, 448, 30, 30);
		firstBtn.setIcon(new ImageIcon(firstImg));
		getContentPane().add(firstBtn);
		
		upBtn = new JButton("");
		upBtn.setBounds(930, 488, 30, 30);
		upBtn.setIcon(new ImageIcon(upImg));
		getContentPane().add(upBtn);
		
		downBtn = new JButton("");
		downBtn.setBounds(930, 528, 30, 30);
		downBtn.setIcon(new ImageIcon(downImg));
		getContentPane().add(downBtn);
		
		lastBtn = new JButton("");
		lastBtn.setBounds(930, 568, 30, 30);
		lastBtn.setIcon(new ImageIcon(lastImg));
		getContentPane().add(lastBtn);
		
		addBtn = new JButton("");
		addBtn.setBounds(930, 368, 30, 30);
		addBtn.setIcon(new ImageIcon(addImg));
		getContentPane().add(addBtn);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 368, 910, 190);
		getContentPane().add(scrollPane);
		
		JPanel endPanel = new JPanel();
		endPanel.setBounds(10, 566, 910, 78);
		getContentPane().add(endPanel);
		endPanel.setLayout(null);
		
		JLabel vatLabel = new JLabel("Vat       :");
		vatLabel.setBounds(601, 7, 74, 25);
		endPanel.add(vatLabel);
		vatLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel totalLabel = new JLabel("Total     :");
		totalLabel.setBounds(601, 43, 74, 25);
		endPanel.add(totalLabel);
		totalLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		totalText = new JTextField();
		totalText.setText("0.00");
		totalText.setBounds(688, 43, 192, 25);
		endPanel.add(totalText);
		totalText.setEditable(false);
		totalText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		totalText.setColumns(10);
		
		vatText = new JTextField();
		vatText.setEditable(false);
		vatText.setText("0.00");
		vatText.setBounds(688, 7, 192, 25);
		endPanel.add(vatText);
		vatText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		vatText.setColumns(10);
		
		vatCalBox = new JRadioButton("Vat Calculator");
		vatCalBox.setSelected(true);
		vatCalBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		vatCalBox.setBounds(246, 7, 130, 25);
		endPanel.add(vatCalBox);
		actionVatCheck();
		
		adjustVat = new JRadioButton("Adjust Vat");
		adjustVat.setFont(new Font("Tahoma", Font.BOLD, 14));
		adjustVat.setBounds(378, 7, 106, 25);
		endPanel.add(adjustVat);
		actionVatCheck();
		
		noVat = new JRadioButton("no Vat");
		noVat.setFont(new Font("Tahoma", Font.BOLD, 14));
		noVat.setBounds(496, 7, 85, 25);
		endPanel.add(noVat);
		actionVatCheck();
		
		ButtonGroup vatGroup = new ButtonGroup();
		vatGroup.add(vatCalBox);
		vatGroup.add(adjustVat);
		vatGroup.add(noVat);
		submitBtn = new JButton("Submit");
		submitBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		submitBtn.setBounds(614, 655, 168, 40);
		getContentPane().add(submitBtn);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		cancelBtn.setBounds(798, 655, 162, 40);
		getContentPane().add(cancelBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 13, 950, 48);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		printBtn = new JButton("Print ");
		printBtn.setBounds(434, 4, 162, 38);
		panel_1.add(printBtn);
		printBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		printBtn.setIcon(new ImageIcon(printImg));
		
		revisedBtn = new JButton("Revised");
		revisedBtn.setBounds(606, 4, 162, 38);
		panel_1.add(revisedBtn);
		revisedBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		revisedBtn.setIcon(new ImageIcon(revisedImg));
		
		paymentBtn = new JButton("Payment");
		paymentBtn.setBounds(778, 4, 162, 38);
		panel_1.add(paymentBtn);
		paymentBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		paymentBtn.setIcon(new ImageIcon(paymentImg));
		
		printType = new JComboBox(print);
		printType.setModel(new DefaultComboBoxModel(new String[] {"Invoice Thai", "Invoice Eng"}));
		printType.setFont(new Font("Tahoma", Font.BOLD, 14));
		printType.setBounds(240, 4, 179, 38);
		panel_1.add(printType);
		
		JLabel lblPrintType = new JLabel("Print Type :");
		lblPrintType.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrintType.setBounds(124, 9, 106, 33);
		panel_1.add(lblPrintType);
		
		setColwitdh();
		actionAdjustVat();
		actionNoVat();
		textUpdateVatAction();
//		addJTableAction();

	}
	
	private void setColwitdh(){
		table.getTableHeader().setPreferredSize(new Dimension(100, 45));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
		table.getColumnModel().getColumn(0).setPreferredWidth(440);
		table.getColumnModel().getColumn(1).setPreferredWidth(102);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(144);
		table.getColumnModel().getColumn(4).setPreferredWidth(144);
		table.setRowHeight(40);
		addChangeTable();
	}
	
	public void clearAll() {
		clearCustomer();
		vatText.setText("0.00");
		totalText.setText("0.00");
		table.setModel(new tableInvoice());
	}
	public void clearCustomer(){
		cusNoText.setText("0");
		cusNameText.setText("");
		attnText.setText("");
		taxText.setText("");
		telText.setText("");
		emailText.setText("");
		addressText.setText("");
		refText.setText("");
		subjectText.setText("");
		saleIDText.setText("");
		saleNameText.setText("");
		enableCusEdit();
	}
	private void enableCusEdit(){
//		cusNoText.setEditable(true);
		cusNameText.setEditable(true);
		attnText.setEditable(true);
		taxText.setEditable(true);
		saleIDText.setEditable(true);
		saleNameText.setEditable(true);
//		telText.setEditable(true);
//		emailText.setEditable(true);
//		addressText.setEditable(true);
	}
	
	public void disableCusEdit(boolean isTour){
//		cusNoText.setEditable(false);
		cusNameText.setEditable(false);
		if(isTour){
			attnText.setEditable(false);
		}else{
			attnText.setEditable(true);
		}
		taxText.setEditable(false);
//		telText.setEditable(false);
//		emailText.setEditable(false);
//		addressText.setEditable(false);
	}
	
	public boolean checkIssDue(){
		return (Integer.parseInt(dueDay.getText())>=0)&&
				Tools.checkDate(issueDay,issueMon,issueYear); 
	}
	public int getSelectRow(){
		int result = -1;
		result = table.getSelectedRow();
		return result;
	}
	public tableInvoice getTable(){
		return (tableInvoice) table.getModel();
	}
	
	public boolean setModel(ArrayList<rowInvoice> data,String type){
		boolean result = false;
		tableInvoice tableModel= new tableInvoice();
		tableModel.setListRowsData(data);
//		tableModel.fireTableDataChanged();
		table.setModel(tableModel);

//		addActionTable();
		setColwitdh();
		setTypeColumn(type);
		result = true;
		return result;
	}
	
	/*
	 * type = RT/RTS
	 */
	 private void setTypeColumn(String type) {
		TableColumn column = table.getColumnModel().getColumn(1);
		//Set up the editor for the sport cells.
		JComboBox comboBox = null;
		String[] list = Tools.getConfig(type).split(",");

		comboBox = new JComboBox(list);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		column.setCellEditor(new DefaultCellEditor(comboBox));
		
//		comboBox.addItemListener(new ItemListener() {
//		    @Override
//		    public void itemStateChanged(ItemEvent e) {
//		        if(e.getStateChange() == ItemEvent.SELECTED) {
////		        	int col = table.getSelectedColumn();
//		        	int tTittle = 1;
//		        	int row = table.getSelectedRow();
//
//		        }
//		    }
//		});
		//Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//		renderer.setToolTipText("Click for combo box");
		column.setCellRenderer(renderer);
	}
	 
	public void setClearCustomerAction(ActionListener action){
		clearCustomerBtn.addActionListener(action);
	} 
	public void setSubmitAction(ActionListener action){
		submitBtn.addActionListener(action);
	}
	public void setAddAction(ActionListener action){
		addBtn.addActionListener(action);
	}
	public void setDelAction(ActionListener action){
		delBtn.addActionListener(action);
	}
	public void setUpAction(ActionListener action){
		upBtn.addActionListener(action);
	}
	public void setFirstAction(ActionListener action){
		firstBtn.addActionListener(action);
	}
	public void setDownAction(ActionListener action){
		downBtn.addActionListener(action);
	}
	public void setLastAction(ActionListener action){
		lastBtn.addActionListener(action);
	}
	public void setFindAction(ActionListener action){
		findBtn.addActionListener(action);
	}
	public void setPaymentAction(ActionListener action){
		paymentBtn.addActionListener(action);
	}
	public void setRevisedAction(ActionListener action){
		revisedBtn.addActionListener(action);
	}
	public void setCancelAction(ActionListener action){
		cancelBtn.addActionListener(action);
	}
	public void setPrintAction(ActionListener action){
		printBtn.addActionListener(action);
	}
//	public void setSearchSalesAction(ActionListener action){
//		findSaleBtn.addActionListener(action);
//	}
	public int getDataSize(){
		return ((tableInvoice)table.getModel()).getListRowsData().size();
	}
	public void setCustomerData(invoiceCustomData data){
		cusNoText.setText(data.getCusNo());
		cusNameText.setText(data.getCusName());
		attnText.setText(data.getAttn());
		taxText.setText(data.getTax());
		telText.setText(data.getTel());
		emailText.setText(data.getEmail());
		addressText.setText(data.getAddress());
		boolean isTour = false;
		if(data.getCustomerType().equals("tourmember")){
			isTour = true;
		}
		disableCusEdit(isTour);
	}
	
	public void addChangeTable(){
		 table.getModel().addTableModelListener(new TableModelListener() {
		      public void tableChanged(TableModelEvent e) {
		    	 ((tableInvoice) e.getSource()).calculationTable();
		    	 if(vatCalBox.isSelected()){
		    		 vat = ((tableInvoice) e.getSource())
			    			 .getVat(true);
		    		 vatText.setText(vat);
		    		 total = ((tableInvoice) e.getSource())
			    			 .getSumTotal(true);
		    		 totalText.setText(total);
		    	 }else if(adjustVat.isSelected()){
		    		 float fVat = Float.parseFloat(vatText.getText());
		    		 float fTotal = Float.parseFloat(((tableInvoice) e.getSource())
			    			 .getSumTotal(false));
		    		 total = String.format("%.2f",fTotal+fVat);
		    		 totalText.setText(total);
		    		 
		    		 
		    	 }else if(noVat.isSelected()){
		    		 total = ((tableInvoice) e.getSource())
			    			 .getSumTotal(false);
		    		 vat = ((tableInvoice) e.getSource())
			    			 .getVat(false);
		    		 vatText.setText(vat);
		    		 
		    	 }
		      }
		    });
	}
	private void actionAdjustVat(){
	    ActionListener action = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          if(adjustVat.isSelected()){
	        	  vatText.setEditable(true);
	          }
	        }
	      };
	      adjustVat.addActionListener(action);
	}
	
	private void actionNoVat(){
	    ActionListener action = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          if(noVat.isSelected()){
	        	  vatText.setEditable(false);
	        	  setTotalVatFromTable();
	          }
	        }
	      };
	      noVat.addActionListener(action);
	}
	
	private void actionVatCheck(){
	    ActionListener action = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
//	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
//	          boolean selected = abstractButton.getModel().isSelected();
	          setTotalVatFromTable();
	          vatText.setEditable(false);
	        }
	      };
	      vatCalBox.addActionListener(action);
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
				float fVat = 0;
				float fTotal = 0;
				if(Tools.isEmpty(vatText.getText())){
					
				}else{
					fVat = Float.parseFloat(vatText.getText());
				}
				if(Tools.isEmpty(totalText.getText())){
					
				}else{
					fTotal =  
							Float.parseFloat(((tableInvoice) table.getModel()).getSumTotal(false));
//							Float.parseFloat(totalText.getText());
				} 
	    		 total = String.format("%.2f",fTotal+fVat);
	    		 totalText.setText(total);
	    		 
			}
		};
		vatText.getDocument().addDocumentListener(updateAction);
	}
	
	public boolean hasVat(){
		boolean hasVat = false;
		if(vatCalBox.isSelected()){
			hasVat = true;
		}
		return hasVat;
	}
	
	public void setTotalVatFromTable(){
   	 	totalText.setText(((tableInvoice) table.getModel())
			 .getSumTotal(vatCalBox.isSelected()));
   	 	vatText.setText(((tableInvoice) table.getModel())
			 .getVat(vatCalBox.isSelected()));
	}
	public ArrayList<rowInvoice> getTableData(){
		return ((tableInvoice)this.table.getModel()).getListRowsData();
	}
	public String getInvoice() {
		return invoiceText.getText();
	}

	public void setInvoice(String invoiceText) {
		this.invoiceText.setText(invoiceText);
	}

	public String getCusNo() {
		return cusNoText.getText();
	}

	public void setCusNo(String cusNoText) {
		this.cusNoText.setText(cusNoText);
	}

	public String getCusName() {
		return cusNameText.getText();
	}

	public void setCusName(String cusNameText) {
		this.cusNameText.setText(cusNameText);
	}

	public String getAttn() {
		return attnText.getText();
	}

	public void setAttn(String attnText) {
		this.attnText.setText(attnText);
	}

	public String getTax() {
		return taxText.getText();
	}

	public void setTax(String taxText) {
		this.taxText.setText(taxText);
	}

	public String getTel() {
		return telText.getText();
	}

	public void setTel(String telText) {
		this.telText.setText(telText);
	}

	public String getSubject() {
		return subjectText.getText();
	}

	public void setSubject(String subjectText) {
		this.subjectText.setText(subjectText);
	}

	public String getRef() {
		return refText.getText();
	}

	public void setRef(String refText) {
		this.refText.setText(refText);
	}
	
	public String getEmail() {
		return emailText.getText();
	}

	public void setEmail(String emailText) {
		this.emailText.setText(emailText);
	}

	public String getAddress() {
		return addressText.getText();
	}

	public void setAddress(String addressText) {
		this.addressText.setText(addressText);
	}

	public String getSaleID() {
		return saleIDText.getText();
	}

	public void setSaleID(String saleID) {
		this.saleIDText.setText(saleID);
		saleIDText.setEditable(false);
		saleNameText.setEditable(false);
	}

	public String getSaleName() {
		return saleNameText.getText();
	}

	public void setSaleName(String saleName) {
		this.saleNameText.setText(saleName);
		saleIDText.setEditable(false);
		saleNameText.setEditable(false);
	}

	public String getTotall() {
		String total = "0";
		total = ((tableInvoice)table.getModel()).getSumTotal(vatCalBox.isSelected());
		return total;
	}

	public void setTotal(String totalText) {
		this.totalText.setText(totalText);
	}
	public String getVat() {
		String vat = "0";
		vat = ((tableInvoice)table.getModel()).getVat(vatCalBox.isSelected());
		return vat;
	}

	public void setVat(String vatText) {
		this.vatText.setText(vatText);
	}
	
	public String getDueDate(){
		String result = "";
		int day = Integer.parseInt(dueDay.getText());
		if(Tools.isNotEmpty(getIssueDate())){
			result = Tools.plusDate(getIssueDate(),day);
		}
		return result;
	}
	
	public String getIssueDate(){
		String result = "";
		int day = issueDay.getSelectedIndex();
		int mon = issueMon.getSelectedIndex();
		String year = (String) issueYear.getSelectedItem();
		result = Tools.genTextDate(day, mon, year);
		return result;
	}
	/**
	 *  input dd/mm/yyyy
	 */
	public void setIssueDate(String date){
		String[] spDate = date.split("/");
		issueDay.setSelectedIndex(Integer.parseInt(spDate[0]));
		issueMon.setSelectedIndex(Integer.parseInt(spDate[1]));
		issueYear.setSelectedItem(spDate[2]);
		
	}
	public boolean checkTable(){
		boolean isOkay = true;
		final int serviceNameCol = 0;
		ArrayList<rowInvoice> list = ((tableInvoice)table.getModel()).getListRowsData();
		for(rowInvoice temp : list){
			String name = temp.getElementData(serviceNameCol);
			if(Tools.isEmpty(name)){
				isOkay = false;
			}
		}
		return isOkay;
	}
	public void setInvoiceAllData(invoiceData data){
		StringBuffer invoice = new StringBuffer();
		invoice.append(data.getInvoiceType());
		invoice.append(data.getInvoiceNo());
		if(!data.getRevised().equals("0")){
			invoice.append("(REV"+data.getRevised()+")");
		}
		invoiceText.setText(invoice.toString());
///---------Remove Sales
//		String [] sale = data.getCreateUserID().split("_");
//		saleIDText.setText(data.getCustomerID());
//		saleNameText.setText(sale[1]);
		saleIDText.setText(data.getCreateUserID());
		saleNameText.setText(data.getCreateName());
//---------
		ArrayList<Integer> issue = Tools.spitDate(data.getIssueDate());
		issueDay.setSelectedIndex(issue.get(0));
		issueMon.setSelectedIndex(issue.get(1));
		issueYear.setSelectedItem(Integer.toString(issue.get(2)));
		dueDay.setText(data.getDueDay());
//		ArrayList<Integer> due = Tools.spitDate(data.getDueDate());
//		dueDay.setSelectedIndex(due.get(0));
//		dueMon.setSelectedIndex(due.get(1));
//		dueYear.setSelectedItem(Integer.toString(due.get(2)));
		cusNoText.setText(data.getCustomerID());
		cusNameText.setText(data.getCustomerName());
		attnText.setText(data.getAttn());
		addressText.setText(data.getAddress());
		emailText.setText(data.getEmail());
		telText.setText(data.getTel());
		taxText.setText(data.getTaxID());
		vatText.setText(data.getVat());
		subjectText.setText(data.getSubject());
		refText.setText(data.getRef());
		if(Float.parseFloat(data.getVat())==0){
			vatCalBox.setSelected(false);
		}else{
			vatCalBox.setSelected(true);
		}
		totalText.setText(data.getTotalAll());
//		if(data.getPayment().equals("N")){
//			paymentSetEnable(true);
//		}else{
//			paymentSetEnable(false);
//		}
		setModel(data.getListData(),data.getInvoiceType());
	
		
	}
	
	public void insertCase(){
		printBtn.setVisible(false);
		revisedBtn.setVisible(false);
		paymentBtn.setVisible(false);
		submitBtn.setVisible(true);
		findBtn.setEnabled(true);
		clearCustomerBtn.setEnabled(true);
//		saleIDText.setEnabled(true);
//		saleNameText.setEnabled(true);
		dueDay.setEnabled(true);
		issueDay.setEnabled(true);
		issueMon.setEnabled(true);
		issueYear.setEnabled(true);
//		cusNoText.setEnabled(true);
		cusNameText.setEnabled(true);
		attnText.setEnabled(true);
		addressText.setEnabled(true);
		subjectText.setEnabled(true);
		refText.setEnabled(true);
		telText.setEnabled(true);
		emailText.setEnabled(true);
		taxText.setEnabled(true);
		((tableInvoice)table.getModel()).enableEditCol(3);
		noVat.setSelected(true);
	}
	public void revisionCase(){
		printBtn.setVisible(false);
		revisedBtn.setVisible(false);
		paymentBtn.setVisible(false);
		submitBtn.setVisible(true);
		findBtn.setEnabled(true);
		clearCustomerBtn.setEnabled(true);
//		saleIDText.setEnabled(true);
//		saleNameText.setEnabled(true);
		dueDay.setEnabled(true);
		issueDay.setEnabled(true);
		issueMon.setEnabled(true);
		issueYear.setEnabled(true);
//		cusNoText.setEnabled(true);
		cusNameText.setEnabled(true);
		attnText.setEnabled(true);
		addressText.setEnabled(true);
		subjectText.setEnabled(true);
		refText.setEnabled(true);
		telText.setEnabled(true);
		emailText.setEnabled(true);
		taxText.setEnabled(true);
		((tableInvoice)table.getModel()).enableEditCol(3);
	}
	public void editCase(){
		printBtn.setVisible(true);
		revisedBtn.setVisible(true);
		paymentBtn.setVisible(true);
		submitBtn.setVisible(false);
		findBtn.setEnabled(false);
		clearCustomerBtn.setEnabled(false);
//		saleIDText.setEnabled(false);
//		saleNameText.setEnabled(false);
		dueDay.setEnabled(false);
		issueDay.setEnabled(false);
		issueMon.setEnabled(false);
		issueYear.setEnabled(false);
//		cusNoText.setEnabled(false);
		cusNameText.setEnabled(false);
		attnText.setEnabled(false);
		addressText.setEnabled(false);
		telText.setEnabled(false);
		emailText.setEnabled(false);
		taxText.setEnabled(false);
		subjectText.setEnabled(false);
		refText.setEnabled(false);
		((tableInvoice)table.getModel()).enableEditCol(-1);

	}
	public int getPrintType(){
		return printType.getSelectedIndex();
	}
	
	public void setPaymentEnable(boolean enable){
		paymentBtn.setEnabled(enable);
		revisedBtn.setEnabled(enable);
	}

	public String getDueDay() {
		return dueDay.getText();
	}

	public void setDueDay(String dueDay) {
		this.dueDay.setText(dueDay);
	}
	public void setRevisedBtnEnable(boolean enable) {
		revisedBtn.setEnabled(enable);
	}
	
	public void setPaymentBtnEnable(boolean enable) {
		paymentBtn.setEnabled(enable);
	}
}
