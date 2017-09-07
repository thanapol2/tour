package company.meetingLog;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

//import com.table.rowMember;

public class tableMeeting extends AbstractTableModel {
	
	/**
	 * 
	 */
	int colIndex=0;
    private ArrayList<String>  colsName = new ArrayList<String>();
	private ArrayList<meetingRow> listRowsData = new ArrayList<meetingRow>();
	public tableMeeting(){
		colsName = new ArrayList<String>(
				Arrays.asList("Contact Day","Detail","Contact To","Phone","Create By"));
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

    public void addRow(meetingRow data) {
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
    	meetingRow rowData = getListRowsData().get(rowIndex);
        return rowData.getStringData().get(columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
       // Cannot set rowdata
    }
	public ArrayList<meetingRow> getListRowsData() {
		return listRowsData;
	}
	public void setListRowsData(ArrayList<meetingRow> listRowsData) {
		this.listRowsData = listRowsData;
		fireTableRowsInserted(listRowsData.size(), listRowsData.size());
		
	}	
	
	public meetingRow getRowsData(int index) {
		return listRowsData.get(index);
	}

	public String getSeqNo(int index){
		return listRowsData.get(index).getSeqNo();
	}

}