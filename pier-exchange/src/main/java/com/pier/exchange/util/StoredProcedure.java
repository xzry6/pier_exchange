package com.pier.exchange.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;

import com.pier.exchange.BOCcrawler;

public class StoredProcedure {

	private static String db_driverClassName, db_url, db_username, db_password, db_instance;
	static {
		
		db_driverClassName = ConfigUtil.getString(ConfigConst.DB_TEST_DRIVER_CLASS, null);
		db_url = ConfigUtil.getString(ConfigConst.DB_TEST_URL, null);
		db_username = ConfigUtil.getString(ConfigConst.DB_TEST_USERNAME, null);
		db_password = ConfigUtil.getString(ConfigConst.DB_TEST_PASSWORD, null);
		db_instance = ConfigUtil.getString(ConfigConst.DB_TEST_INSTANCE, null);
	}
	
	
	private Connection conn;
	//connect to mysql
	public void connect(String driver, String url, String user, String password) throws ClassNotFoundException, SQLException {
	    Class.forName(driver);
	    conn = DriverManager.getConnection(url, user, password);
	    if(!conn.isClosed()) 
	    	System.out.println("Succeeded connecting to the Database!");
	}
	//close connection
	public void close() throws SQLException {
        conn.close();
    } 
	public boolean insertInstance(String tableName, Map<String, String> map) throws SQLException {
		if(map.size()==0) return false;
		StringBuffer sb1 = new StringBuffer("INSERT INTO "+tableName+"(");
		StringBuffer sb2 = new StringBuffer(") VALUES(");
		Iterator<String> i = map.keySet().iterator();
		Object key = i.next();
		Object value = map.get(key);
		sb1.append(key);
		sb2.append(value);
		while(i.hasNext()) {
			key = i.next();
			value = map.get(key);
			sb1.append(","+key);
			sb2.append(","+value);
		}
		String query = sb1.append(sb2).append(")").toString();
		//System.out.println(query);
		Statement stmt = conn.createStatement(); 
		boolean bool = stmt.execute(query); 
		stmt.close();
		return bool;
	}
	public static void main(String[] args) {
		String url = "http://srh.bankofchina.com/search/whpj/search.jsp";
		try {
			BOCcrawler l = new BOCcrawler();
			l.login(Type.BOC, url);
			l.crawl();
			System.out.println(l.getMap().size());
			StoredProcedure sp = new StoredProcedure();
			sp.connect(db_driverClassName, db_url, db_username, db_password);
			sp.insertInstance(db_instance, l.getMap());
			sp.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
