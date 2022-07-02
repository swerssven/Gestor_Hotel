package GestorHotel;

import java.io.Serializable;

public class Planta implements Serializable{
	private int numP;
	private Habitacion[] habitaciones;
	private int numHabLibres;
	
	public Planta(int p) {
		this.numP = p;
		this.habitaciones = new Habitacion[10];
		
		for(int i = 0; i < habitaciones.length; i++) {
			habitaciones[i] = new Habitacion(i + 1, numP);
		}
		
		this.numHabLibres = 10;
	}
	
	public boolean hayLibres() {
		if(numHabLibres == 0) {
			return false;
		}else {
			return true;
		}
	}
	

	public Habitacion[] getHabitaciones() {
		return habitaciones;
	}
	
	public int primeraLibre() {
		int numHab = 0;
		
		for(int i = 0; i < habitaciones.length; i++) {
			if(habitaciones[i].estaLibre()) {
				numHab = i + 1;
				i = 10;
			}
		}
		
		return numHab;
	}
	
	public void ocuparHabitPlanta(Cliente c) {
		if(primeraLibre() != 0) {
			habitaciones[primeraLibre() - 1].ocuparHab(c);
			numHabLibres--;
		}
	}
	
	public void liberarHabitPlanta(int i) {
		habitaciones[i - 1].liberarHab();
		numHabLibres++;
	}
	
	public Cliente masDiasEnPlanta() {
		int diaLlegada = 366;
		Cliente cliente = null;
		
		for(Habitacion h: habitaciones) {
			if(h.elCliente() != null && h.elCliente().laLlegada() < diaLlegada) {
				diaLlegada = h.elCliente().laLlegada();
				cliente = h.elCliente();
			}
		}
		
		if(cliente == null) {
			return null;
		}else {
			return cliente;
		}
	}
	
	public String toString() {
		String infoHabs = "";
		
		for(Habitacion h: habitaciones) {
			infoHabs += h.toString() + "\n";
		}
		
		return infoHabs;
	}
}
