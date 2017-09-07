
package com.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;




public class BaseDAO {
	
	
	/**
	 * get JDBC Connection 
	 * 
	 * @return connection
	 * @throws Exception 
	 */
	protected Connection getConnection() {
		
		try {
			return ConnectionPool.getInstance().getConnection();
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Cannot connect to Database server"
					,"Error", JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(ex);
		}
		// ConnectionPool.getInstance().getConnection();
	}

	/**
	 * close JDBC Connection
	 * 
	 * @param connection
	 */
	protected void closeConnection(Connection con) {
//		ConnectionPool.getInstance().returnConnection(con);
		ConnectionPool.getInstance().closeConnection(con);
	}

	
}
