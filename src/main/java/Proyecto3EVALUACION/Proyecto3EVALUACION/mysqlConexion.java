package Proyecto3EVALUACION.Proyecto3EVALUACION;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javax.swing.JOptionPane;

public class mysqlConexion {
    
	private static Connection con = null;
	private String driver;
	private String url;
	private String usuario;
	private String password;
	
	
	public mysqlConexion() {
		String driver="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost/ranking";
		String usuario="root";
		String password="";
		try{
		     Class.forName(driver);
		     con = DriverManager.getConnection(url, usuario, password);
		 }
		 catch(ClassNotFoundException | SQLException e){
		     e.printStackTrace();
		 }
	}
	
	public static Connection getConexion() {
		if(con==null) {
			new mysqlConexion();
			
		}
		return con;
	}
	
       
	
        
}


	
	
    
