package invoice.invoiceDetail;


import java.awt.Window;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.base.ConnectionPool;
import com.base.Tools;
import com.ui.loadingPanel;


public class invoiceDetailInsert extends invoiceDetail {
	
	public invoiceDetailInsert() {
		super();
//		this.view = new invoiceDetailView();
//		this.dao = new invoiceDetailDAO();
//		this.invoiceType = "tour";
		this.isNewInvoice = true;
//		submitActionAdd();
		view.insertCase();
	}
	
	public void control(String type){
//		view.pack();
		view.setVisible(true);
		view.clearAll();
		this.invoiceType = type;
		this.dataTable = new ArrayList<rowInvoice>();
		view.setIssueDate(Tools.getCurrentDate());
		add();
		view.setSaleID(ConnectionPool.getUserID());
		view.setSaleName(searchSale());
	}
	


	
}


