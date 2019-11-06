package com.telefonica.jee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Jdbc6PreparedStatement {


	
	public static void main(String[] args) {

		Connection conexion = null;
		PreparedStatement preparedStatement = null;
		
		
		try {       
			// 1 - Get Connection
			conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3303/ted", "root", "admin");
			
			// 2 - Create Statement
			String sql = "INSERT INTO ted.producto (nombre,precio ) VALUES (?, ?)";

			int insertedRows = 0;
			preparedStatement = conexion.prepareStatement(sql);
			for (int i = 0; i <= new Random().nextInt(5); i++) {
				preparedStatement.setString(1, "Product" + new Random().nextInt());
				preparedStatement.setDouble(2, 24.45);

				insertedRows += preparedStatement.executeUpdate();
			}            
			System.out.println("--Registros añadidos: "  + insertedRows);
		
		} catch (SQLException ex) {       
			System.out.println("Error: Problemas:"+ ex.getMessage());       
			System.exit(1);  
		} finally {       
			if (preparedStatement != null){
				 try { 
					 preparedStatement.close();
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
