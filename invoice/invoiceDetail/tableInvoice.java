package invoice.invoiceDetail;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

import com.base.Tools;


public class tableInvoice extends AbstractTableModel {
	
	/**
	 * 
	 */
	int colIndex=0;
    private ArrayList<String>  colsName = new ArrayList<String>();
	private ArrayList<rowInvoice> listRowsData = new ArrayList<rowInvoice>();
	private ArrayList<String> listRTVat = 
			new ArrayList<String>( Arrays.asList(Tools.getConfig("rtvat_type").split(",")));
	private ArrayList<String> listRTSVat = 
			new ArrayList<String>( Arrays.asList(Tools.getConfig("rtsvat_type").split(",")));
	private ArrayList<String> discountVat = 
			new ArrayList<String>( Arrays.asList(Tools.getConfig("discount_type").split(",")));
	private float totalVat;
	private float totalNotVat;
	private float totalIncludeVat;
	private float calRTVat;
	private float calRTSVat;
	private int disableEditColStart = 0;
	public tableInvoice(){
		createHeadTable();
		disableEditColStart = 3;
	}
	
	public tableInvoice(ArrayList<rowInvoice> data){
		createHeadTable();
		setListRowsData(data);
		disableEditColStart = -1;
	}
	public void enableEditCol(int colNum){
		this.disableEditColStart = colNum;
	}
	private void createHeadTable(){
		calRTVat = Float.parseFloat(Tools.getConfig("tour_vat"))/100;
		calRTSVat = Float.parseFloat(Tools.getConfig("bus_vat"))/100;
		totalVat = 0 ;
		totalIncludeVat = 0;
		totalNotVat = 0;
		listRowsData = new ArrayList<rowInvoice>();
		colsName = new ArrayList<String>(
				Arrays.asList("<html><center>Service</center></html>"
						,"Type",
						"<html><center>QTY<br>(Adult)</center></html>",
						"<html><center>Price</center></html>",
						"<html><center>Total(THB)</center></html>"));
	}
	
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	if(columnIndex>disableEditColStart){
    		return false;
    	}else{
    		return true;
    	} 
    }

    @Override
    public String getColumnName(int column) {
        return colsName.get(column).toString();
    }

    @Override
    public int getRowCount() {
        return getListRowsData().size();
    }

    public void addRow(rowInvoice data) {
        getListRowsData().add(data);
        fireTableRowsInserted(getListRowsData().size(), getListRowsData().size());
    }


    public void removeRow(int selectedRow) {
        getListRowsData().remove(selectedRow);
        fireTableRowsDeleted(selectedRow, selectedRow);
    }

    @Override
    public int getColumnCount() {
        return colsName.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        rowInvoice rowData = getListRowsData().get(rowIndex);
        return rowData.getStringData().get(columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//    	int colTotal = 4;
//    	int colServiceType = 1;
    	if((columnIndex>1)&&(Tools.isEmpty(aValue))){
    		aValue = "0";
    	}else{
    		/// add case start o at Adult col
    		if((columnIndex==2)&&(Tools.canConvertToInt(aValue))){
    			aValue = Integer.toString(Integer.parseInt((String) aValue));
    		}else if((columnIndex==3)&&(Tools.canConvertToFloat(aValue))){
    			aValue = String.format("%.2f",Float.parseFloat((String) aValue));
    		}else{
    			aValue = aValue.toString().replace("'", " ");
    			
    		}
    	}
    	listRowsData.get(rowIndex).setElementData((String) aValue, columnIndex);
//    	if(columnIndex>colServiceType){
//    		int qtyAd = Integer.parseInt(listRowsData.get(rowIndex).getElementData(2));
//    		int price = Integer.parseInt(listRowsData.get(rowIndex).getElementData(3));		
//   			int totalAd =  price*qtyAd;
////    		int total = totalAd;
//    		listRowsData.get(rowIndex).setElementData(Integer.toString(totalAd), colTotal);
//    	}
    	fireTableDataChanged();
    }
	public ArrayList<rowInvoice> getListRowsData() {
		return listRowsData;
	}
	public void setListRowsData(ArrayList<rowInvoice> listRowsData) {
		this.listRowsData = listRowsData;
		fireTableRowsInserted(listRowsData.size(), listRowsData.size());
		calculationTable();
		fireTableDataChanged();
	}	
	
	public rowInvoice getRowsData(int index) {
		return listRowsData.get(index);
	}

	public void calculationTable(){
		int colTotal = 4;
		int colType  = 1;
		float total = 0;
		float vat = 0;

		for(rowInvoice temp : listRowsData){
			String type = temp.getElementData(colType);
			float dataTotal = Float.parseFloat(temp.getElementData(colTotal));
			int qtyAd = Integer.parseInt(temp.getElementData(2));
    		float price = Float.parseFloat(temp.getElementData(3));	
			if(discountVat.contains(type)){
				dataTotal =  - price*qtyAd;
//		    		int total = totalAd;
	    		temp.setElementData(String.format("%.2f",dataTotal), colTotal);
			}else{
				dataTotal =  price*qtyAd;
//	    		int total = totalAd;
				temp.setElementData(String.format("%.2f",dataTotal), colTotal);
			}
    		if(listRTVat.contains(type)){
				vat = vat+(dataTotal * calRTVat); 
			}else if(listRTSVat.contains(type)){
				vat = vat+(dataTotal * calRTSVat);
			}

			total = total + dataTotal; 
		}
		totalNotVat = total;
		totalVat = vat;
		totalIncludeVat = total+vat;
	}
	public String getSumTotal(boolean hasVat){
		String total = "0.00";
		if(hasVat){
			total = String.format("%.2f", totalIncludeVat);
				
		}else{
			total = String.format("%.2f",totalNotVat);
		}
		return total;
	}


	
	public String getVat(boolean hasVat){
		String vat = "0.00";
		if(hasVat){
			vat = String.format("%.2f",totalVat);
		}
		return vat;
	}
}
