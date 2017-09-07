
package com.base;

import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


public class ftpConnection {

	private static String hostName;
	private static String portNumber;
	private static String user;
	private static String pass;
	private static String path;
        
	private Vector ftpList = new Vector();

	public static final ftpConnection INSTANCE = new ftpConnection();
	
	// initial
	static {
                
                
//		String type = Tools.getConfig("jdbc.config.type");
		hostName = Tools.getConfig("ftp_name");
		portNumber = Tools.getConfig("ftp_port");
		user = Tools.getConfig("ftp_user");
		pass = Tools.getConfig("ftp_pass");
		path = Tools.getConfig("ftp_path");	
	}

	private ftpConnection() {}

	public static ftpConnection getInstance() {

		return INSTANCE;
	}


	@SuppressWarnings("unchecked")
	public FTPClient getFTP() throws Exception {
		FTPClient ftp = new FTPClient();
		for (int i = 0; i < ftpList.size(); i++) {
			Pool pool = (Pool) ftpList.get(i);
			if (!pool.isBusy) {
				ftp = pool.getFTP();
				pool.setBusy(true);
				break;
			}
		}
		ftp.connect(hostName, Integer.parseInt(portNumber));
        ftp.login(user, pass);
        ftp.changeWorkingDirectory(path);
        ftp.enterLocalPassiveMode();
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
		Pool pool = new Pool(ftp, true);
		ftpList.add(pool);

		return ftp;
	}

	public void returnConnection(FTPClient ftp) {

		for (int i = 0; i < ftpList.size(); i++) {
			Pool pool = (Pool) ftpList.get(i);
			if (pool.getFTP() == ftp) {
				pool.setBusy(false);
				break;
			}
		}

	}

	public void closeConnection(FTPClient ftp) {

		for (int i = 0; i < ftpList.size(); i++) {
			Pool pool = (Pool) ftpList.get(i);
			if (pool.getFTP() == ftp) {
				try {
					pool.getFTP().logout();
					pool.getFTP().disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ftpList.remove(pool);
				break;
			}
		}
	}

	class Pool {

		FTPClient ftp = null;

		boolean isBusy = false;

		public Pool(FTPClient ftp, boolean isBusy) {
			this.ftp = ftp;
			this.isBusy = isBusy;
		}

		public FTPClient getFTP() {
			return ftp;
		}

		public void setFTP(FTPClient ftp) {
			this.ftp = ftp;
		}

		public boolean isBusy() {
			return isBusy;
		}

		public void setBusy(boolean isBusy) {
			this.isBusy = isBusy;
		}

	}
}
