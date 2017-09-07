package tourlist.createTourList;


import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.text.DocumentFilter;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import com.base.Tools;
import com.extentclass.JTableAddFilter;
import com.extentclass.numberJTextField;
import com.extentclass.selectOneRowModel;
import com.extentclass.upperFilter;
import com.table.rowMember;

import javax.swing.text.AbstractDocument;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import com.base.icon;

public class createTourView extends JFrame {
	private JTextField groupName;
	private JTextField tourListName;
	private JTextField hotelName;
	private JTextField departNo;
	private JTextField arriNo;
	private JTextField departName;
	private JTextField arriName;
	private JTextField departTime;
	private JTextField arriTime;
	private JComboBox departDay;
	private JComboBox departMon;
	private JComboBox departYear;
	private JComboBox arriDay;
	private JComboBox arriMon;
	private JComboBox arriYear;
	private JTextField pax;
	private JTable table;
	private JButton firstBtn;
	private JButton upBtn;
	private JButton downBtn;
	private JButton lastBtn;
	private JButton addBtn;
	private JButton delBtn;
	private JButton submitBtn;
	private JButton busAdd;
	private JComboBox destinationBox;
	private JButton cancelBtn;
	

	public createTourView() {
		setTitle("Create Tour List");
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1013, 599);
		getContentPane().setLayout(null);
//		setVisible(true);

/// prepare------------------		
		DocumentFilter filter = new upperFilter();
		
		String[] Tday={"Day","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		String[] Tmount = {"Mon","1","2","3","4","5","6","7","8","9","10","11","12"};
		String tempYear = Integer.toString(Calendar.getInstance().get(Calendar.YEAR)-2);
		String[] Tyear  = Tools.genYear(10,tempYear);
		String[] destiList  = Tools.getConstant("007").split(",");
		ArrayList<Integer> colNum = new ArrayList(Arrays.asList(12));
		Image firstImg = new ImageIcon(icon.getPicPath("first.png")).getImage();
		firstImg = firstImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		Image upImg = new ImageIcon(icon.getPicPath("up.png")).getImage();
		upImg = upImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		Image downImg = new ImageIcon(icon.getPicPath("down.png")).getImage();
		downImg = downImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		Image lastImg = new ImageIcon(icon.getPicPath("last.png")).getImage();
		lastImg = lastImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		Image addImg = new ImageIcon(icon.getPicPath("add.png")).getImage();
		addImg = addImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
		Image delImg = new ImageIcon(icon.getPicPath("remove.png")).getImage();
		delImg = delImg.getScaledInstance(25,25, java.awt.Image.SCALE_SMOOTH);
/// prepare------------------		
		JPanel headPanel = new JPanel();
		headPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Head", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		headPanel.setBounds(10, 11, 977, 197);
		getContentPane().add(headPanel);
		headPanel.setLayout(null);
		
		JLabel groupLabel = new JLabel("Group Name :");
		groupLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		groupLabel.setBounds(10, 23, 86, 23);
		headPanel.add(groupLabel);
		
		groupName = new JTextField();
		groupName.setFont(new Font("Tahoma", Font.BOLD, 12));
		groupName.setBounds(106, 23, 185, 23);
		headPanel.add(groupName);
		groupName.setColumns(10);
		((AbstractDocument) groupName.getDocument()).setDocumentFilter(filter);
		
		JLabel tourNameLabel = new JLabel("TourList Name :");
		tourNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		tourNameLabel.setBounds(301, 23, 107, 23);
		headPanel.add(tourNameLabel);
		
		tourListName = new JTextField();
		tourListName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tourListName.setColumns(10);
		tourListName.setBounds(415, 23, 488, 23);
		headPanel.add(tourListName);
		
		JLabel hotelLabel = new JLabel("Hotel Name :");
		hotelLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		hotelLabel.setBounds(317, 57, 86, 23);
		headPanel.add(hotelLabel);
		
		hotelName = new JTextField();
		hotelName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		hotelName.setColumns(10);
		hotelName.setBounds(415, 57, 487, 23);
		headPanel.add(hotelName);
		
		JLabel lblDepartures = new JLabel("Departing Flight No :");
		lblDepartures.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDepartures.setBounds(10, 91, 139, 23);
		headPanel.add(lblDepartures);
		
		JLabel lblArrivingFlightsno = new JLabel("Arriving Flight No    :");
		lblArrivingFlightsno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblArrivingFlightsno.setBounds(10, 125, 139, 23);
		headPanel.add(lblArrivingFlightsno);
		
		JLabel lblFlightName = new JLabel("Flight Name :");
		lblFlightName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFlightName.setBounds(236, 91, 86, 23);
		headPanel.add(lblFlightName);
		((AbstractDocument) groupName.getDocument()).setDocumentFilter(filter);
		JLabel label = new JLabel("Flight Name :");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(236, 125, 86, 23);
		headPanel.add(label);
		
		JLabel lblTime_1 = new JLabel("Time :");
		lblTime_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTime_1.setBounds(702, 91, 47, 23);
		headPanel.add(lblTime_1);
		
		JLabel lblTime = new JLabel("Time :");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTime.setBounds(702, 125, 47, 23);
		headPanel.add(lblTime);
		
		JLabel lblTarget = new JLabel("Destination :");
		lblTarget.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTarget.setBounds(10, 57, 86, 23);
		headPanel.add(lblTarget);
		
		destinationBox = new JComboBox(destiList);
		destinationBox.setBounds(107, 57, 184, 23);
		headPanel.add(destinationBox);
		
		departNo = new JTextField();
		departNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		departNo.setColumns(10);
		departNo.setBounds(145, 91, 85, 23);
		headPanel.add(departNo);
		((AbstractDocument) departNo.getDocument()).setDocumentFilter(filter);
		
		arriNo = new JTextField();
		arriNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		arriNo.setColumns(10);
		arriNo.setBounds(145, 127, 85, 23);
		headPanel.add(arriNo);
		((AbstractDocument) arriNo.getDocument()).setDocumentFilter(filter);
		
		departName = new JTextField();
		departName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		departName.setColumns(10);
		departName.setBounds(323, 91, 133, 23);
		headPanel.add(departName);
		((AbstractDocument) departName.getDocument()).setDocumentFilter(filter);
		
		arriName = new JTextField();
		arriName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		arriName.setColumns(10);
		arriName.setBounds(323, 125, 133, 23);
		headPanel.add(arriName);
		((AbstractDocument) arriName.getDocument()).setDocumentFilter(filter);
		
		JLabel de = new JLabel("Date :");
		de.setFont(new Font("Tahoma", Font.BOLD, 12));
		de.setBounds(480, 91, 44, 23);
		headPanel.add(de);
		
		JLabel label_1 = new JLabel("Date :");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(480, 125, 44, 23);
		headPanel.add(label_1);
		
		departTime = new JTextField();
		departTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		departTime.setColumns(10);
		departTime.setBounds(750, 91, 153, 23);
		headPanel.add(departTime);
		
		arriTime = new JTextField();
		arriTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		arriTime.setColumns(10);
		arriTime.setBounds(750, 125, 153, 23);
		headPanel.add(arriTime);
		
		departDay = new JComboBox(Tday);
		departDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		departDay.setBounds(522, 91, 48, 23);
		headPanel.add(departDay);
		
		departMon = new JComboBox(Tmount);
		departMon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		departMon.setBounds(574, 91, 48, 23);
		headPanel.add(departMon);
		
		departYear = new JComboBox(Tyear);
		departYear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		departYear.setBounds(627, 91, 67, 23);
		headPanel.add(departYear);
		
		arriDay = new JComboBox(Tday);
		arriDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		arriDay.setBounds(522, 125, 48, 23);
		headPanel.add(arriDay);
		
		arriMon = new JComboBox(Tmount);
		arriMon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		arriMon.setBounds(574, 125, 48, 23);
		headPanel.add(arriMon);
		
		arriYear = new JComboBox(Tyear);
		arriYear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		arriYear.setBounds(627, 125, 67, 23);
		headPanel.add(arriYear);
		
		JLabel lblPassenger = new JLabel("passenger :");
		lblPassenger.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassenger.setBounds(10, 159, 75, 23);
		headPanel.add(lblPassenger);
		
		pax = new JTextField();
		pax.setText("0");
		pax.setEditable(false);
		pax.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pax.setColumns(10);
		pax.setBounds(95, 159, 62, 23);
		headPanel.add(pax);
		
		busAdd = new JButton("All Bus1");
		busAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		busAdd.setBounds(803, 159, 100, 28);
		headPanel.add(busAdd);
		
		
		table = new JTableAddFilter(colNum);
		table.setSelectionModel(new selectOneRowModel());
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new tableMember());
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 219, 937, 293);
		getContentPane().add(scrollPane);
		
		firstBtn = new JButton("");
		firstBtn.setBounds(10, 299, 30, 30);
		firstBtn.setIcon(new ImageIcon(firstImg));
		getContentPane().add(firstBtn);
		
		upBtn = new JButton("");
		upBtn.setBounds(10, 339, 30, 30);
		upBtn.setIcon(new ImageIcon(upImg));
		getContentPane().add(upBtn);
		
		downBtn = new JButton("");
		downBtn.setBounds(10, 379, 30, 30);
		downBtn.setIcon(new ImageIcon(downImg));
		getContentPane().add(downBtn);
		
		lastBtn = new JButton("");
		lastBtn.setBounds(10, 419, 30, 30);
		lastBtn.setIcon(new ImageIcon(lastImg));
		getContentPane().add(lastBtn);
		
		delBtn = new JButton("");
		delBtn.setBounds(10, 259, 30, 30);
		delBtn.setIcon(new ImageIcon(delImg));
		getContentPane().add(delBtn);
		
		addBtn = new JButton("");
		addBtn.setBounds(10, 219, 30, 30);
		addBtn.setIcon(new ImageIcon(addImg));
		getContentPane().add(addBtn);
		
		submitBtn = new JButton("Submit");
		submitBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		submitBtn.setBounds(784, 521, 89, 28);
		getContentPane().add(submitBtn);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		cancelBtn.setBounds(890, 521, 89, 28);
		getContentPane().add(cancelBtn);
		
		setColwitdh();
	}

	public boolean setModel(ArrayList<rowMember> data){
		boolean result = false;
		tableMember tableModel= new tableMember();
		tableModel.setListRowsData(data);
		tableModel.fireTableDataChanged();
		table.setModel(tableModel);
//		addActionTable();
		setColwitdh();
		setTypeColumn();
		result = true;
		return result;
	}
	
//	public void clearSelection(){
//		table.getModel().fireTableDataChanged();
//	}
	
	public int getSelectRow(){
		int result = -1;
		result = table.getSelectedRow();
		return result;
	}
	public int getSelectCol(){
		int result = -1;
		result = table.getSelectedColumn();
		return result;
	}
	public String getValueData(int col,int row){
		String result = null;
		result = (String) table.getModel().getValueAt(row, col);
		return result;
	}
	public void setTableAction (TableModelListener action){
		table.getModel().addTableModelListener(action);
	}
	public void setFirstAction(ActionListener action){
		firstBtn.addActionListener(action);
	}
	public void setLastAction(ActionListener action){
		lastBtn.addActionListener(action);
	}
	public void setUpAction(ActionListener action){
		upBtn.addActionListener(action);
	}
	public void setDownAction(ActionListener action){
		downBtn.addActionListener(action);
	}
	public void setaddAction(ActionListener action){
		addBtn.addActionListener(action);
	}
	public void setdelAction(ActionListener action){
		delBtn.addActionListener(action);
	}

	public void setSubmitAction(ActionListener action){
		submitBtn.addActionListener(action);
	}
	
	public void setBusAction(ActionListener action){
		busAdd.addActionListener(action);
	}
	
	private void setColwitdh(){
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(85);
		table.getColumnModel().getColumn(8).setPreferredWidth(79);
		table.getColumnModel().getColumn(9).setPreferredWidth(79);
		table.getColumnModel().getColumn(10).setPreferredWidth(79);
		table.getColumnModel().getColumn(11).setPreferredWidth(60);
		table.getColumnModel().getColumn(12).setPreferredWidth(60);
		table.getColumnModel().getColumn(13).setPreferredWidth(79);
		table.getColumnModel().getColumn(14).setPreferredWidth(200);
	}

	public String getGroupName() {
		return groupName.getText();
	}

	public void setGroupName(String groupName) {
		this.groupName.setText(groupName);
	}

	public String getTourListName() {
		return tourListName.getText();
	}

	public void setTourListName(String tourListName) {
		this.tourListName.setText(tourListName);
	}

	public String getHotelName() {
		return hotelName.getText();
	}

	public void setHotelName(String hotelName) {
		this.hotelName.setText(hotelName);
	}

	public String getDepartNo() {
		return departNo.getText();
	}

	public void setDepartNo(String departNo) {
		this.departNo.setText(departNo);
	}

	public String getArrNo() {
		return arriNo.getText();
	}

	public void setArrNo(String arriNo) {
		this.arriNo.setText(arriNo);
	}

	public String getDepartName() {
		return departName.getText();
	}

	public void setDepartName(String departName) {
		this.departName.setText(departName);
	}

	public String getArrName() {
		return arriName.getText();
	}

	public void setArrName(String arriName) {
		this.arriName.setText(arriName);
	}

	public String getDepartTime() {
		return departTime.getText();
	}

	public void setDepartTime(String departTime) {
		this.departTime.setText(departTime);
	}

	public String getArrTime() {
		return arriTime.getText();
	}

	public void setArrTime(String arriTime) {
		this.arriTime.setText(arriTime);
	}

	public String getPax() {
		String result = "0";
		if(!pax.getText().isEmpty()){
			result = pax.getText();
		}
		return result;
	}
	public void setPax(int pax){
		this.pax.setText(Integer.toString(pax));
	}

	public String getDestinationBox() {
		return (String) destinationBox.getSelectedItem();
	}

	public void setDestinationBox(String destination) {
		this.destinationBox.setSelectedItem(destination);
	}
	public String getDeparteDate(){
		String result = "";
		int day = departDay.getSelectedIndex();
		int mon = departMon.getSelectedIndex();
		String year = (String) departYear.getSelectedItem();
		result = Tools.genTextDate(day, mon, year);
		return result;
	}
	public String getArrDate(){
		String result = "";
		int day = arriDay.getSelectedIndex();
		int mon = arriMon.getSelectedIndex();
		String year = (String) arriYear.getSelectedItem();
		result = Tools.genTextDate(day, mon, year);
		return result;
	}

	 private void setTypeColumn() {
		TableColumn column = table.getColumnModel().getColumn(13);
		//Set up the editor for the sport cells.
		final JComboBox comboBox = new JComboBox();
		comboBox.addItem("Tour");
		comboBox.addItem("T/L");
		comboBox.addItem("T/G");
		
		column.setCellEditor(new DefaultCellEditor(comboBox));
		
		comboBox.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {
//		        	int col = table.getSelectedColumn();
		        	int tTittle = 1;
		        	int row = table.getSelectedRow();
		            String selectTittle = table.getValueAt(row, tTittle).toString();
		            if(selectTittle.equals("เด็กชาย")||selectTittle.equals("เด็กหญิง")){
		            	comboBox.setSelectedItem("Tour");
		            }
		        }
		    }
		});
		//Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer =
		new DefaultTableCellRenderer();
//		renderer.setToolTipText("Click for combo box");
		column.setCellRenderer(renderer);
	}


	public void clear() {
		groupName.setText("");
		tourListName.setText("");
		hotelName.setText("");
		departNo.setText("");
		arriNo.setText("");
		departName.setText("");
		arriName.setText("");
		departTime.setText("");
		arriTime.setText("");
		departDay.setSelectedIndex(0);
		departMon.setSelectedIndex(0);
		departYear.setSelectedIndex(0);
		arriDay.setSelectedIndex(0);
		arriMon.setSelectedIndex(0);
		arriYear.setSelectedIndex(0);
		destinationBox.setSelectedIndex(0);
		pax.setText("0");
		table.setModel(new tableMember());
	}
	
	public boolean arrAndDep(){
		return Tools.checkDate(arriDay,arriMon,arriYear)&&
				Tools.checkDate(departDay,departMon,departYear); 
	}
}
