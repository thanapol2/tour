package com.tourSearch;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

import com.extentclass.selectOneRowModel;
import com.table.rowMember;
import com.table.tableTour;

import java.awt.Component;

public class tourSearchView extends JPanel {
	private JTable table;
	private JTextField searchSurname;
	private JCheckBox tourBox;
	private JButton searchBtn;
	private JCheckBox restBox;
	private JCheckBox hotelBox;
	private JCheckBox showBox;
	private JCheckBox companyBox;
	private JCheckBox otherBox;
	private JCheckBox noneBox;
	private JButton btnClearAll;
	private JButton btnCheckAll;
	
	
	/**
	 * Create the panel.
	 */
	public tourSearchView() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Search Condition", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(3, 0, 737, 167);
		add(panel);
		
		JLabel lblthen = new JLabel("\u0E0A\u0E37\u0E48\u0E2D\u0E1A\u0E23\u0E34\u0E29\u0E31\u0E17/\u0E19\u0E32\u0E21\u0E2A\u0E01\u0E38\u0E25 (TH/EN):");
		lblthen.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblthen.setBounds(10, 25, 201, 23);
		panel.add(lblthen);
		
		searchSurname = new JTextField();
		searchSurname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		searchSurname.setColumns(10);
		searchSurname.setBounds(221, 25, 506, 23);
		panel.add(searchSurname);
		
		searchBtn = new JButton("\u0E04\u0E49\u0E19\u0E2B\u0E32");
		searchBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchBtn.setBounds(407, 126, 108, 30);
		panel.add(searchBtn);
		
		JLabel lblNewLabel = new JLabel("\u0E04\u0E49\u0E19\u0E2B\u0E32\u0E25\u0E39\u0E01\u0E04\u0E49\u0E32\u0E1B\u0E23\u0E30\u0E40\u0E20\u0E17 :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 60, 136, 23);
		panel.add(lblNewLabel);
		
		tourBox = new JCheckBox("\u0E1A\u0E38\u0E04\u0E04\u0E25\u0E17\u0E31\u0E48\u0E27\u0E44\u0E1B");
		tourBox.setSelected(true);
		tourBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tourBox.setBounds(170, 59, 108, 23);
		panel.add(tourBox);
		
		restBox = new JCheckBox("\u0E23\u0E49\u0E32\u0E19\u0E2D\u0E32\u0E2B\u0E32\u0E23");
		restBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		restBox.setBounds(291, 59, 97, 23);
		panel.add(restBox);
		
		hotelBox = new JCheckBox("\u0E42\u0E23\u0E07\u0E41\u0E23\u0E21");
		hotelBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		hotelBox.setBounds(618, 59, 69, 23);
		panel.add(hotelBox);
		
		showBox = new JCheckBox("show");
		showBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		showBox.setBounds(422, 59, 69, 23);
		panel.add(showBox);
		
		companyBox = new JCheckBox("Company");
		companyBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		companyBox.setBounds(500, 59, 93, 23);
		panel.add(companyBox);
		
		otherBox = new JCheckBox("\u0E2D\u0E37\u0E48\u0E19 \u0E46");
		otherBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		otherBox.setBounds(170, 94, 77, 23);
		panel.add(otherBox);
		
		btnCheckAll = new JButton("Check All");
		btnCheckAll.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCheckAll.setBounds(289, 126, 108, 30);
		panel.add(btnCheckAll);
		
		btnClearAll = new JButton("Clear All");
		btnClearAll.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClearAll.setBounds(170, 126, 108, 30);
		panel.add(btnClearAll);
		
		noneBox = new JCheckBox("\u0E44\u0E21\u0E48\u0E44\u0E14\u0E49\u0E40\u0E25\u0E37\u0E2D\u0E01 Type");
		noneBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		noneBox.setBounds(291, 94, 129, 23);
		panel.add(noneBox);
		table = new JTable();
		table.setSelectionModel(new selectOneRowModel());
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(3, 181, 737, 278);
		add(scrollPane);

		addAction();
	}
	public String getSearchSurname(){
		return this.searchSurname.getText();
	}
	
	public void setSearchSurname(String searchSurname){
		this.searchSurname.setText(searchSurname);
	}

	public JTable getTable() {
		return table;
	}

	public rowMember getRowData(){
		rowMember rowData = new rowMember();
		int rowCount = this.table.getModel().getRowCount();
		if(rowCount>0){
			int index = this.table.getSelectedRow();
			if(index== -1){
//				return new rowMember();
			}else{
				rowData =  ((tableTour) this.table.getModel()).getRowsData(index);
			}
		}
		return rowData;
	}
	public void setTable(tableTour table) {
		this.table.setModel(table);
	}

	public void setCheckTour(){
		tourBox.setSelected(true);
	}
	
	/*
	 * 1 Tour
	 * 2 Rest
	 * 3 Hotel
	 * 4 Company
	 * 5 Show
	 * 6 Other
	 */
	
	public ArrayList<Integer> getCheckBox(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(tourBox.isSelected()){
			result.add(1);
		}
		if(restBox.isSelected()){
			result.add(2);
		}
		if(hotelBox.isSelected()){
			result.add(3);
		}
		if(companyBox.isSelected()){
			result.add(4);
		}
		if(showBox.isSelected()){
			result.add(5);
		}
		if(otherBox.isSelected()){
			result.add(6);
		}
		if(otherBox.isSelected()){
			result.add(7);
		}
		return result;
	}
	public void setDisable(){
		restBox.setEnabled(false);
		hotelBox.setEnabled(false);
		showBox.setEnabled(false);
		companyBox.setEnabled(false);
		otherBox.setEnabled(false);
		tourBox.setEnabled(false);
		noneBox.setEnabled(false);
		btnClearAll.setEnabled(false);
		btnCheckAll.setEnabled(false);
	}
	public JButton getSearchButton() {
		return this.searchBtn;
	}
	
	private void addAction(){
		btnClearAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				restBox.setSelected(false);
				hotelBox.setSelected(false);
				showBox.setSelected(false);
				companyBox.setSelected(false);
				otherBox.setSelected(false);
				tourBox.setSelected(false);
				noneBox.setSelected(false);
			}
		});
		btnCheckAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				restBox.setSelected(true);
				hotelBox.setSelected(true);
				showBox.setSelected(true);
				companyBox.setSelected(true);
				otherBox.setSelected(true);
				tourBox.setSelected(true);
				noneBox.setSelected(true);
			}
		});
	}
}
