package com.telefonica.jee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc3Delete {

	private static final String SQL_SELECT = "SELECT * ";
	private static final String SQL_FROM = "FROM ted.ventas ";
	private static final String SQL_WHERE = "";
	private static final String SQL_ORDER = "ORDER BY idventas ASC";
	private static final String SQL_QUERY = SQL_SELECT + SQL_FROM + SQL_WHERE + SQL_ORDER;
	
	public static void main(String[] args) {

		Connection conexion = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		try {       
			// 1 - Get Connection
			conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3303/ted", "root", "admin");
			
			// 2 - Create Statement
			statement = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			resultSet = statement.executeQuery(SQL_QUERY);
			
			int rowNumber = 1;
			while (resultSet.next ()) {  
					if (rowNumber % 2 == 0) 
						resultSet.deleteRow();
					rowNumber++;
			}
			
		
		} catch (SQLException ex) {       
			System.out.println("Error: Problemas:"+ ex.getMessage());       
			System.exit(1);  
		} finally {       
			if (statement != null){
				 try { 
					 statement.close();
				 } catch (SQLException ex) {   
					 System.out.println("Error: No se puede cerrar statement: "+      ex.getMessage());
				 }
			 }
			if (conexion != null) {   
				try { 
					conexion.close();
				} catch (SQLException ex) {       
					System.out.println("Error: Al cerrar conexión: " + ex.getMessage());
				}    	
			}    
		}
	}

}
