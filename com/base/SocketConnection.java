
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


public class SocketConnection {

	private static String hostName;
	private static String portNumber;

        
	private Vector socketList = new Vector();

	public static final SocketConnection INSTANCE = new SocketConnection();
	
	// initial
	static {
                
                
//		String type = Tools.getConfig("jdbc.config.type");
		hostName = Tools.getConfig("host_name");
		portNumber = Tools.getConfig("port");
	}

	private SocketConnection() {}

	public static SocketConnection getInstance() {

		return INSTANCE;
	}


	@SuppressWarnings("unchecked")
	public Socket getSocket() throws Exception {
		Socket soc = null;
		for (int i = 0; i < socketList.size(); i++) {
			Pool pool = (Pool) socketList.get(i);
			if (!pool.isBusy) {
				soc = pool.getSoc();
				pool.setBusy(true);
				break;
			}
		}

		if (soc == null) {
			try {

				soc = new Socket(hostName,Integer.parseInt(portNumber));

				Pool pool = new Pool(soc, true);
				socketList.add(pool);

			} catch (Exception e) {
				throw new Exception(e);
			}
		}

		return soc;
	}

	public void returnConnection(Socket soc) {

		for (int i = 0; i < socketList.size(); i++) {
			Pool pool = (Pool) socketList.get(i);
			if (pool.getSoc() == soc) {
				pool.setBusy(false);
				break;
			}
		}

	}

	public void closeConnection(Socket soc) {

		for (int i = 0; i < socketList.size(); i++) {
			Pool pool = (Pool) socketList.get(i);
			if (pool.getSoc() == soc) {
				try {
					pool.getSoc().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				socketList.remove(pool);
				break;
			}
		}
	}

	class Pool {

		Socket soc = null;

		boolean isBusy = false;

		public Pool(Socket soc, boolean isBusy) {
			this.soc = soc;
			this.isBusy = isBusy;
		}

		public Socket getSoc() {
			return soc;
		}

		public void setSoc(Socket soc) {
			this.soc = soc;
		}

		public boolean isBusy() {
			return isBusy;
		}

		public void setBusy(boolean isBusy) {
			this.isBusy = isBusy;
		}

	}
}
