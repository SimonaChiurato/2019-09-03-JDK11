package it.polito.tdp.food.db;

public class Adiacenza {

	private String p1;
	private String p2;
	private double peso;
	
	
	
	public Adiacenza(String p1, String p2, double peso) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.peso = peso;
	}
	public String getP1() {
		return p1;
	}
	public void setP1(String p1) {
		this.p1 = p1;
	}
	public String getP2() {
		return p2;
	}
	public void setP2(String p2) {
		this.p2 = p2;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	
	
}
