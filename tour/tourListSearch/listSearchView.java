package tour.tourListSearch;

import javax.swing.JFrame;

import javax.swing.JPanel;

import java.awt.Image;


import javax.swing.JButton;


import java.awt.Font;


import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;


import javax.swing.ImageIcon;

import com.base.icon;

import com.tourSearch.tourSearch;
import com.tourSearch.tourSearchView;

public class listSearchView extends JPanel {
	private JButton btnNewTour;
	private JButton btnNewCompany;
	
	public listSearchView() {
//		Icon Image--------------------------
		Image addImg = new ImageIcon(icon.getPicPath("add.png")).getImage();
		addImg = addImg.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		tourSearch tourSearch = new tourSearch();
//		-------------------------------------
		setFont(new Font("Tahoma", Font.PLAIN, 12));		
//	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		setBounds(0, 0, 653, 538);
//		setTitle("Searching");
//		setResizable(false);
		
//		JPanel panel = new JPanel();
//		panel.setBounds(10, 76, 464, 372);
		
		tourSearchView searchList = tourSearch.getView();
		searchList.setBounds(15,76, 631, 370);
		setLayout(null);
		add(searchList);
		searchList.setLayout(null);
		
		btnNewTour = new JButton("<html><center>Add new <br>Tourmember </center></html>");
		btnNewTour.setBounds(20, 11, 126, 39);
		add(btnNewTour);
		btnNewTour.setIcon(new ImageIcon(addImg));
		btnNewTour.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnNewCompany = new JButton("<html><center>Add new <br>Company </center></html>");
		btnNewCompany.setBounds(156, 11, 126, 39);
		add(btnNewCompany);
		btnNewCompany.setIcon(new ImageIcon(addImg));
		btnNewCompany.setFont(new Font("Tahoma", Font.BOLD, 11));

       
       
	}

//	public JButton getSearchButton() {
//		return searchButton;
//	}
//
//	public void setSearchButton(JButton searchButton) {
//		this.searchButton = searchButton;
//	}
//	
//	public String getSearchSurname(){
//		return this.searchSurname.getText();
//	}
//	
//	public void setSearchSurname(String searchSurname){
//		this.searchSurname.setText(searchSurname);
//	}
//
//	public JTable getTable() {
//	}
//
//	public void setTable(tableTour table) {
//		this.table.setModel(table);
//	}
//	
//	public boolean getCheckCom(){
//		return companyBox.isSelected();
//	}
//	public boolean getCheckTour(){
//		return tourBox.isSelected();
//	}

	public JButton getBtnNewTour() {
		return btnNewTour;
	}

	public JButton getBtnNewCompany() {
		return btnNewCompany;
	}
//	public JButton getBtnCancel() {
//		return btnCancel;
//	}
}
