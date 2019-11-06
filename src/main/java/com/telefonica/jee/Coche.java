package com.telefonica.jee;

public class Coche {

	private int id;
	private String modelo;
	private String fabricante;
	private int numCilindros;
	private double numCV;

	public Coche() {
	}

	public Coche(int id, String modelo, String fabricante, int numCilindros, double numCV) {
		this.id = id;
		this.modelo = modelo;
		this.fabricante = fabricante;
		this.numCilindros = numCilindros;
		this.numCV = numCV;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public int getNumCilindros() {
		return numCilindros;
	}

	public void setNumCilindros(int numCilindros) {
		this.numCilindros = numCilindros;
	}

	public double getNumCV() {
		return numCV;
	}

	public void setNumCV(double numCV) {
		this.numCV = numCV;
	}

	@Override
	public String toString() {
		return "Coche [modelo=" + modelo + ", fabricante=" + fabricante + ", numCilindros=" + numCilindros + ", numCV="
				+ numCV + "]";
	}
}
