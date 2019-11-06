package com.telefonica.jee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Jdbc4ExecuteInsert {


	
	public static void main(String[] args) {

		Connection conexion = null;
		Statement statement = null;
		boolean result = false;
		
		
		try {       
			// 1 - Get Connection
			conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3303/ted", "root", "admin");
			
			// 2 - Create Statement
			statement = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

			int addedRows = 0;
			result = statement.execute(
					"INSERT INTO ted.producto (nombre, precio ) VALUES ('Inserted"+new Random().nextInt(1000)+"', 18.15); ");
			if (!result) addedRows+=statement.getUpdateCount();
			System.out.println("--Registros añadidos: " + addedRows);
			
		
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
