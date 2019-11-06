package com.telefonica.jee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc5ExecuteDelete {


	
	public static void main(String[] args) {

		Connection conexion = null;
		Statement statement = null;
		
		
		try {       
			// 1 - Get Connection
			conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3303/ted", "root", "admin");
			
			// 2 - Create Statement
			statement = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

			int removedRows = statement.executeUpdate("DELETE FROM ted.producto WHERE nombre like 'Inserted%';");
			System.out.println("--Registros borrados: " + removedRows);
			
		
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
