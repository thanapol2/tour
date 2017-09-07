package tourlist.createTourList;

import java.util.ArrayList;
import java.util.Arrays;


import javax.swing.table.AbstractTableModel;


import com.table.rowMember;

public class tableMember extends AbstractTableModel {
	
	/**
	 * 
	 */
	int colIndex=0;
    private ArrayList<String>  colsName;
	private ArrayList<rowMember> listRowsData;
	public tableMember(){
//		colsName = new ArrayList<String>();
		listRowsData = new ArrayList<rowMember>();
		colsName = new ArrayList<String>(
				Arrays.asList("No.","Title","Name","Surname","Title","Name","Surname",
						"NO. PP","DOB","DOI","DOE","Room","Bus",
						"T/L,T/G","Remark"));
		
	}

	
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i =0;i<=10;i++){
        	list.add(i);
        }
    	if(list.contains(columnIndex)){
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

    public void addRow(rowMember data) {
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
        rowMember rowData = getListRowsData().get(rowIndex);
        return rowData.getStringData().get(columnIndex);
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	listRowsData.get(rowIndex).setElementData((String) aValue, columnIndex);
    	fireTableDataChanged();
    }
	public ArrayList<rowMember> getListRowsData() {
		return listRowsData;
	}
	public void setListRowsData(ArrayList<rowMember> listRowsData) {
		this.listRowsData = listRowsData;
		fireTableRowsInserted(listRowsData.size(), listRowsData.size());
	}	
	
	public rowMember getRowsData(int index) {
		return listRowsData.get(index);
	}

}
