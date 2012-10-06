package persistence;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class ConnectionSingleton {
	private static ConnectionSingleton instance = null;
	private Connection conn;
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/";
	
	private ConnectionSingleton() throws Exception {
		String user = "site";
		String pwd = "4321rewq";
		String db = "cooptel";
		try {
	        Class.forName(DRIVER);
		    this.conn = (Connection) DriverManager.getConnection(DBURL+db, user, pwd);      
		} catch (ClassNotFoundException cnfe) {
			throw new Exception("Problema com o driver JDBC"+cnfe.getMessage());
		} catch (SQLException se) {
			throw new Exception("Problemas ao conectar");
		}
	}
	
	public static ConnectionSingleton getInstance(){
		if(instance == null){
			try {
				instance = new ConnectionSingleton();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
		return instance;
	}
	
	public Connection getConnnection(){
		return this.conn;
	}
}
