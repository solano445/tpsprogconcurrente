package model;

import java.util.concurrent.Semaphore;

public class Estacion extends Semaphore {
	//Variables
	private String nombre;

	/**
	 * Lo construyo con un solo permiso
	 */
	public Estacion(String _nombre) {
		super(1);
		this.nombre=_nombre;
	}

	
	/**
	 * toString que retorna el nombre de la Estacion
	 */
	public String toString(){
		return this.getNombre();
	}
	
	//Getters & Setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
