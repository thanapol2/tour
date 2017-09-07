package invoice.search;

import invoice.invoiceDetail.rowInvoice;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

import com.base.Tools;
import com.table.rowMember;



public class tableInvoiceHead extends AbstractTableModel {
	
	/**
	 * 
	 */
	int colIndex=0;
    private ArrayList<String>  colsName = new ArrayList<String>();
	private ArrayList<rowInvoiceHead> listRowsData = new ArrayList<rowInvoiceHead>();
	public tableInvoiceHead(){
//		listRowsData = new ArrayList<rowInvoice>();
		colsName = new ArrayList<String>(
				Arrays.asList("<html><center>Invoice No</center></html>",
//						"<html><center>Revised</center></html>",
						"<html><center>Customer Name</center></html>",
						"<html><center>Payment No</center></html>",
						"<html><center>Issue Date</center></html>",
						"<html><center>Create User</center></html>"));
		
	}
	
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false;
    }
    
    public String getColumnName(int column) {
        return colsName.get(column).toString();
    }
    
	@Override
	public int getColumnCount() {
		return colsName.size();
	}
	@Override
	public int getRowCount() {
		return listRowsData.size();
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
        String data = null;
        if(rowIndex>=0){
        	rowInvoiceHead rowData = listRowsData.get(rowIndex);
        	data = rowData.getStringData().get(columnIndex);
        }
		return data;
	}
	
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	listRowsData.get(rowIndex).setElementData((String) aValue, columnIndex);
    }
    
    public void setListRowData(ArrayList<rowInvoiceHead> data){
    	this.listRowsData = data;
    	fireTableDataChanged();
    }
}
