package model;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

	/*
	 * Es en realidad un Semaphore, se les da refencia desde una
	 * coleccion para ordenarlas en el circuito
	 */

public class Estacion extends Semaphore {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Variables
	 */
	private String nombre;
	/**
	 * Se implementa como lista para evitar que un tren "empuje" al ptro al entrar
	 */
	private List<Tren> enAnden = new LinkedList<Tren>();

	/**
	 * Lo construyo con un solo permiso
	 */
	public Estacion(String nombreT) {
		super(1);
		this.nombre = nombreT;
	}

	/**
	 * Da el permiso del semaforo (WAIT)
	 * @throws InterruptedException
	 */
	public void pedirPermisoDeIngreso() throws InterruptedException{
		this.acquireUninterruptibly();
	}
	
	/**
	 * Retorna el permiso (SIGNAL)
	 */
	public void liberarPermisoDeIngreso(){
		this.release();
	}
	
	/**
	 * Agrega un tren a la lista de trenes en el anden.
	 */
	public void ocuparAnden(Tren trenT){
		this.enAnden.add(trenT);
	}
	/**
	 * Agrega un tren a la lista de trenes en el anden.
	 */
	public void liberarAnden(Tren trenT){
		this.enAnden.remove(trenT);
	}
	
	/**
	 * toString que retorna el nombre de la Estacion
	 */
	public String toString(){
		return ("[ " + this.getNombre() + " " + this.trenesToString() + "]" );
	}
	
	private String trenesToString() {
		String returned = "";
		for(Tren t:this.enAnden){
			returned += t.getName() + " ";
		}
		return returned;
	}

	//Getters & Setters
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
