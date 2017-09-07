
package com.base;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;


public class ConnectionPool {

	private static String dbDriver;
	private static String dbUrl;
	private static String dbUser;
	private static String dbPass;
	private static String id;
	private static String userType;
	private static String name;

	
	private Vector conectionList = new Vector();


	public static final ConnectionPool INSTANCE = new ConnectionPool();
	
	// initial
	static {
                
                
//		String type = Tools.getConfig("jdbc.config.type");
		dbDriver = Tools.getConfig("db_driver");
		dbUrl    = Tools.getConfig("db_url");
		dbUser   = Tools.getConfig("db_username");
		dbPass   = Tools.getConfig("db_password");
		
	}

	private ConnectionPool() {}

	/*
	int 0 = ok
	int 1 = connect fail
	int 2 = invaild
	*/
	public static int login(String user, String pass){
		dbUser = user;
		dbPass = pass;
		int result = 2;
		try {
			 result = INSTANCE.test();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static ConnectionPool getInstance() {

		return INSTANCE;
	}

	/*
	int 0 = ok
	int 1 = connect fail
	int 2 = invaild
	*/
	private int test() throws SQLException {
		int result = 2;
		try {
			Connection con = getConnection();
			Statement stm = con.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("select s.SALES_ID,s.ROLE_NAME, EN_TITTLE||' '||EN_NAME||' '||EN_SURNAME as ename			");
			sql.append("FROM  ").append(Tools.getPREFIX()).append("sales s,(select user as name 	");
			sql.append("					from dual) u		");
			sql.append("where s.USER_NAME = u.name				");
//			System.out.println(sql.toString());
			ResultSet re = stm.executeQuery(sql.toString());
			while (re.next()) {
	
				id = re.getString("SALES_ID");
				userType = re.getString("ROLE_NAME");
				name = re.getString("ename");
				result = 0;
			}
			stm.close();
			closeConnection(con);
			return result;
		} catch (SQLException e) {
			return result;
		} catch (Exception e) {
			return 1;
		}
	}

	@SuppressWarnings("unchecked")
	public Connection getConnection() throws Exception {
		Connection con = null;
		for (int i = 0; i < conectionList.size(); i++) {
			Pool pool = (Pool) conectionList.get(i);
			if (!pool.isBusy) {
				con = pool.getCon();
				pool.setBusy(true);
				break;
			}
		}

		if (con == null) {
			try {
				Driver driver = (Driver) (Class.forName(dbDriver).newInstance());
				DriverManager.registerDriver(driver);

				con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

				Pool pool = new Pool(con, true);
				conectionList.add(pool);

			} catch (Exception e) {
				throw new Exception(e);
			}
		}

		return con;
	}

	public void returnConnection(Connection con) {

		for (int i = 0; i < conectionList.size(); i++) {
			Pool pool = (Pool) conectionList.get(i);
			if (pool.getCon() == con) {
				pool.setBusy(false);
				break;
			}
		}

	}

	public void closeConnection(Connection con) {

		for (int i = 0; i < conectionList.size(); i++) {
			Pool pool = (Pool) conectionList.get(i);
			if (pool.getCon() == con) {
				try {
					pool.getCon().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conectionList.remove(pool);
				break;
			}
		}
	}

	class Pool {

		Connection con = null;

		boolean isBusy = false;

		public Pool(Connection con, boolean isBusy) {
			this.con = con;
			this.isBusy = isBusy;
		}

		public Connection getCon() {
			return con;
		}

		public void setCon(Connection con) {
			this.con = con;
		}

		public boolean isBusy() {
			return isBusy;
		}

		public void setBusy(boolean isBusy) {
			this.isBusy = isBusy;
		}

	}
	
	public static String getUserID(){
		return id;
	}
	public static boolean isAccountUser(){
		boolean result = false;
		if(userType.equals("ACCOUNT")||userType.equals("SUB")){
			result = true;
		}
		return result;
	}
	public static boolean isNormal(){
		return !isAccountUser();
	}
	public static boolean isSubAccount(){
		boolean result = false;
		if(userType.equals("SUB")){
			result = true;
		}
		return result;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		ConnectionPool.name = name;
	}
	
}
