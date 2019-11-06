package com.telefonica.jee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.Driver;

/*

CREATE TABLE ted.socio (idsocio INT NOT NULL AUTO_INCREMENT,nombre VARCHAR(45) NULL,edad INT NULL,cuota DECIMAL(5,2) NULL,fecha DATE NULL,PRIMARY KEY (idsocio),UNIQUE INDEX nombre_UNIQUE (nombre ASC),INDEX idx_edad (edad ASC),INDEX idx_cuota (cuota ASC),INDEX idx_fecha (fecha ASC));
CREATE TABLE ted.producto (idproducto INT NOT NULL AUTO_INCREMENT,nombre VARCHAR(45) NULL,precio DECIMAL(7,2) NULL,PRIMARY KEY (idproducto ),UNIQUE INDEX nombre_UNIQUE (nombre ASC),INDEX idx_precio (precio ASC));
CREATE TABLE ted.ventas (idventas INT NOT NULL AUTO_INCREMENT,socio INT NULL,producto INT NULL,cantidad INT NULL,PRIMARY KEY (idventas),INDEX idx_socio (socio ASC),INDEX idx_producto (producto ASC),INDEX idxcantidad (cantidad ASC),CONSTRAINT fk_socio FOREIGN KEY (socio) REFERENCES ted.socio (idsocio) ON DELETE NO ACTION ON UPDATE NO ACTION,CONSTRAINT fk_producto FOREIGN KEY (producto) REFERENCES ted.producto (idproducto) ON DELETE NO ACTION ON UPDATE NO ACTION);


 */
public class JdbcExercise1 {

	private static final String SQL_PRODUCTS_SELECT = "SELECT * FROM producto;";
	private static final String SQL_SOCIOS_SELECT = "SELECT * FROM socio;";
	private static final String SQL_VENTAS_SELECT = "SELECT * FROM ventas;";

	public static void main(String[] args) {

		Connection conexion = null;
		Statement statement = null;

		try {


			registerDriverByClass();
//			registerDriverByMethod();
			
			// 1 - Get Connection
			conexion = getConectionByUrl();
//			conexion = getConectionByUrlParameters();
//			conexion = getConectionByProperties();
			
			// 2 - Create Statement
			System.out.println("COUNT PRODUCTOS: " + count(conexion, SQL_PRODUCTS_SELECT));
			System.out.println("COUNT VENTAS: " + count(conexion, SQL_VENTAS_SELECT));
			System.out.println("COUNT SOCIOS: " + count(conexion, SQL_SOCIOS_SELECT));


			

		} catch (ClassNotFoundException ex) {       
			System.out.println("Error: No se puede cargar driver: " +      ex.getMessage());
			System.exit(1);
		} catch (SQLException ex) {
			System.out.println("Error: Problemas:" + ex.getMessage());
			System.exit(1);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ex) {
					System.out.println("Error: No se puede cerrar statement: " + ex.getMessage());
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


	private static int count(Connection connection, String sql) throws SQLException {
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.last();
	    int size = resultSet.getRow();
	    resultSet.beforeFirst();
		return size;
	}

	private static Connection getConectionByProperties() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", "root");
		connectionProps.put("password", "admin");
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3303/ted",connectionProps);
	}

	private static Connection getConectionByUrlParameters() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3303/ted", "root", "admin");
	}

	private static Connection getConectionByUrl() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3303/ted?user=root&password=admin&"
				+ "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");

	}

	private static void registerDriverByClass() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	
	private static void registerDriverByMethod() throws SQLException {
		Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver( driver );
	}

}
