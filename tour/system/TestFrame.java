package tour.system;

import invoice.addCustomer.addCustomer;
import invoice.addCustomer.addView;
import invoice.invoiceDetail.invoiceDetailInsert;
import invoice.invoiceDetail.invoiceDetailView;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;

import com.base.Tools;
import com.selectAddList.listAddSelect;
import com.selectAddList.listAddView;

import tour.tourListDetail.listDetailInsert;
import tourlist.createTourList.createTourView;


public class TestFrame extends JFrame{

	public static void main(String[] args) {
//		listSelect frame = new listSelect();
		addCustomer frame = new addCustomer();
	}


}
