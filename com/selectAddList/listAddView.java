package com.selectAddList;


import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;


import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;


import com.table.rowMember;
import com.tourSearch.tourSearch;
import com.tourSearch.tourSearchView;

import javax.swing.JList;

public class listAddView extends JDialog {


	private JList tourList;
	private DefaultListModel<String> model;
	private JButton removeBtn;
	private JButton clearBtn;
	private JButton addBtn;
	private JButton submitBtn;
	private JButton cancelBtn;
	
	private tourSearch tourSearch;
	
	public listAddView() {
//		super(parent);
		createview();
//		addWindowListener(new WindowAdapter() 
//		{
//		  public void windowClosing(WindowEvent e)
//		  {
//			  parent.setEnabled(true);
//		  }
//		});
//		setCancelAction(parent);
		
	}
	public void createview() {

//      prepate tourSearch		
		tourSearch = new tourSearch();
		tourSearch.disableComCase();
//		-------------------------------------
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));		
		getContentPane().setLayout(null);
		setResizable(false);
		
		JPanel selectPanel = new JPanel();
		selectPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "List Member", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		selectPanel.setBounds(10, 11, 233, 526);
		getContentPane().add(selectPanel);
		selectPanel.setLayout(null);
		
		model = new DefaultListModel<String>();
		tourList = new JList(model);
		tourList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tourList.setBounds(10, 21, 213, 427);
		selectPanel.add(tourList);
		
		removeBtn = new JButton("Remove");
		removeBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		removeBtn.setBounds(21, 478, 89, 39);
		selectPanel.add(removeBtn);
		
		clearBtn = new JButton("Clear All");
		clearBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		clearBtn.setBounds(120, 478, 89, 39);
		selectPanel.add(clearBtn);
		
//		JPanel panel = new JPanel();
//		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Select List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		panel.setBounds(253, 11, 706, 486);
//		getContentPane().add(panel);
//		panel.setLayout(null);
		
		
		tourSearchView searchList = tourSearch.getView();
		searchList.setBounds(253,11, 750,470);
		getContentPane().add(searchList);
		
		addBtn = new JButton("Add");
		addBtn.setBounds(253, 485, 77, 39);
		getContentPane().add(addBtn);
		addBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		submitBtn = new JButton("Submit");
		submitBtn.setBounds(340, 485, 85, 39);
		getContentPane().add(submitBtn);
		submitBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(435, 485, 77, 39);
		getContentPane().add(cancelBtn);
		cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		
//	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		setBounds(100, 100, 1020, 569);
		setTitle("Searching");
//		setVisible(true);
       
	}
	
	public rowMember getRowData(){
		return tourSearch.getSelectedRowData();
	}
	public JButton getAddBtn() {
		return addBtn;
	}

	public JButton getRemoveBtn() {
		return removeBtn;
	}

	public JButton getClearBtn() {
		return clearBtn;
	}
	public JButton getSubmitBtn() {
		return submitBtn;
	}
//	public JButton getCancelBtn() {
//		return cancelBtn;
//	}
	public void addTourList(String aData){
		 model.addElement(aData);
	}
	public int getSelectIndexTourList(){
		return tourList.getSelectedIndex();
	}
	public boolean removeList(){
		boolean result = false;
		model.remove(tourList.getSelectedIndex());
		result = true;
		return result;
	}
	
	public boolean clearList(){
		boolean result = false;
		model.removeAllElements();
		result = true;
		return result;
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
}
