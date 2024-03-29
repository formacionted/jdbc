package com.telefonica.jee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc1Update {

	private static final String SQL_SELECT = "SELECT * ";
	private static final String SQL_FROM = "FROM ted.producto ";
	private static final String SQL_WHERE = "";
	private static final String SQL_ORDER = "ORDER BY idproducto ASC";
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
			System.out.println("TOTAL COLUMN NUMBER: " + resultSet.getMetaData().getColumnCount());
			    
			while (resultSet.next ()) {  
			    String title = resultSet.getString("nombre");
			    title = "(Edited) " + title;
			    resultSet.updateString("nombre", title);
			    resultSet.updateRow();
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
