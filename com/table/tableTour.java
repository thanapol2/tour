package com.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.base.Tools;

public class tableTour extends AbstractTableModel {
	
	/**
	 * 
	 */
	int colIndex=0;
    private ArrayList<String>  colsName = new ArrayList<String>();
	private ArrayList<rowMember> listRowsData = new ArrayList<rowMember>();
	public tableTour(){
		colsName = new ArrayList<String>(
				Arrays.asList("ID","Name-SurName","Type","ENG NAME","POINT"));
//		String[][] aData = {{"","", "", "",""}};
//		this.data = aData;
	}
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
       // Cannot set rowdata
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
