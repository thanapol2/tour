package invoice.addCustomer;

import javax.swing.JFrame;

import com.base.Tools;
import com.table.rowMember;
import com.tourSearch.tourSearch;
import com.tourSearch.tourSearchView;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class addView extends JDialog{

	private tourSearch tourSearch;
	private JButton addBtn;
	private JButton cancelBtn;
	/**
	 * @wbp.parser.constructor
	 */

	public addView(JDialog parent){
//		setVisible(true);
		super(parent);
		setBounds(100, 100, 762, 557);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		tourSearch = new tourSearch();
		tourSearchView searchList = tourSearch.getView();
		searchList.setBounds(1,1, 750, 470);
		getContentPane().add(searchList);
		
		addBtn = new JButton("Add");
		addBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		addBtn.setBounds(248, 473, 87, 34);
		getContentPane().add(addBtn);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		cancelBtn.setBounds(350, 473, 87, 34);
		getContentPane().add(cancelBtn);
	}
	public void setAddAction(ActionListener action){
		addBtn.addActionListener(action);
	}
	public void setCancelAction(ActionListener action){
		cancelBtn.addActionListener(action);
	}
	/*
	 *  0 = id
	 *  1 = type tour/companay
	 */
	public ArrayList<String> getSelectedData(){
		rowMember temp = tourSearch.getSelectedRowData();
		ArrayList<String> data = new ArrayList<String>();
		if(Tools.isEmpty(temp.getMemID())){
//			data.add("none");
//			data.add("none");
		}else{
			data.add(temp.getMemID());
			data.add(temp.getGroupType());

		}
		
		return data;
	}
}
