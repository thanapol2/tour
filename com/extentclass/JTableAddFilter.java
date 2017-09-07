package com.extentclass;

import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

public class JTableAddFilter extends JTable{
	private ArrayList<Integer> intColumn = new ArrayList<Integer>();
	private ArrayList<Integer> doubleColumn = new ArrayList<Integer>();
	private TableCellEditor numberEditor;
	private TableCellEditor dobuleEditor;
	public JTableAddFilter(ArrayList<Integer> aIntColumn
//			,ArrayList<Integer> aStringColumn
			){
		this.intColumn = aIntColumn;
//		this.stringColumn = aStringColumn;
		numberEditor = new DefaultCellEditor(new numberJTextField(20));
		
	}
	
	public JTableAddFilter(ArrayList<Integer> aIntColumn
			,ArrayList<Integer> aDobuleColumn
			){
		this.intColumn = aIntColumn;
		this.doubleColumn = aDobuleColumn;
		numberEditor = new DefaultCellEditor(new numberJTextField(20));
		JTextField dobuleText = new JTextField(20);
		dobuleText.setDocument(new doubleDocument(20));
		dobuleEditor = new DefaultCellEditor(dobuleText);
	}
	
	public TableCellEditor getCellEditor(int row, int column) {
		if (intColumn.contains(column)) {
            return numberEditor;
        } else if(doubleColumn.contains(columnModel)){
        	return dobuleEditor;
        } else{
        	 return super.getCellEditor(row, column);
        }
    }
}
