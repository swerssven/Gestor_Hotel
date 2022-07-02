package GestorHotel;

import java.io.Serializable;

public class Cliente implements Serializable {
	private String nombre;
	private int diaLlegada;
	private int diaSalida;
	
	public Cliente(String nom, int lleg, int sal) {
		this.nombre = nom;
		this.diaLlegada = lleg;
		this.diaSalida = sal;
	}
	
	public int laLlegada() {
		return diaLlegada;
	}
	
	public String toString() {
		return nombre + ", Dia llegada: " + diaLlegada + ", Dia salida: " + diaSalida; 
	}
}
