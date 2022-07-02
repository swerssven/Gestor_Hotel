package GestorHotel;

import java.io.Serializable;

import javax.swing.*;

public class Habitacion implements Serializable {
	private int numero;
	private int planta;
	private boolean libre;
	private Cliente cli;
	
	public Habitacion(int n, int p) {
		this.numero = n;
		this.planta = p;
		this.libre = true;
		this.cli = null;
	}
	
	public boolean estaLibre() {
		return libre;
	}
	
	public Cliente elCliente() {
		return cli;
	}
	
	public void ocuparHab(Cliente c) {
		if(libre) {
			libre = false;
			cli = c;
		}else {
			JOptionPane.showMessageDialog(null, "La habitación ya esta ocupada");
		}
	}
	
	public void liberarHab() {
		libre = true;
		cli = null;
	}
	
	public String toString() {
		if(libre) {
			return "Planta: " + planta + ", Num: " + numero + ", libre";
		}else {
			return "Planta: " + planta + ", Num: " + numero + ", Cliente: " + cli.toString();
		}
	}
}
