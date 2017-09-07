package main.export;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class export {
	
	private exportDAO dao;
	public export(){
		this.dao = new exportDAO();
	}
	public boolean createXlsExport (){
		boolean createXls = false;
		createExportXls print = new createExportXls();
			ArrayList<exportData> dataList = new ArrayList<exportData>();
			try {
				dataList = dao.getAllData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(print.printXls(dataList)){
				int result = print.saveFile();
				if(result==1){
					createXls = true;
					JOptionPane.showMessageDialog(null,"Saving Complete");
				}
			}else{
				JOptionPane.showMessageDialog(null,"please check Template file. see at config"
					,"Error", JOptionPane.ERROR_MESSAGE);
			}
		return createXls;
	}
}
