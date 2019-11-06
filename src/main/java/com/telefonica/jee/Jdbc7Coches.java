package com.telefonica.jee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * Dentro de la base de datos ted crear la tabla Coche con las columnas: id ---
 * INT Marca --- VARCHAR Modelo --- VARCHAR numero Cilindros --- INT numero de
 * caballos --- DECIMAL(7,2)
 */
public class Jdbc7Coches {

	private static final String DB_URL = "jdbc:mariadb://localhost:3301/ted";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "admin";
	private static final String SELECT_COCHES = "select * from coche;";

	public static void main(String[] args) {

		Connection connection = null;
		Statement statement = null;
		try {

			// 1 - Crear conexion
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// 2 - Crear y ejecutar sentencia
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT_COCHES);

			// 3 - Procesar resultados
			while (rs.next()) {

				// obtenemos los valores en base al nombre de la columna
				int id = rs.getInt("id");
				String modelo = rs.getString("modelo");
				String fabricante = rs.getString("fabricante");
				int numCilindros = rs.getInt("num_cilindros");
				double numCV = rs.getDouble("num_cv");

				// creamos un objeto con los datos obtenidos
				Coche coche = new Coche(id, modelo, fabricante, numCilindros, numCV);
				System.out.println(coche);

			}

			// 4 - cerrar recursos
			rs.close();
		} catch (SQLException se) {
			// Errores de JDBC
			se.printStackTrace();
		} finally {
			// Cerrar recursos

			try {
				if (statement != null)
					statement.close();
			} catch (SQLException se) {
			}

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

}
