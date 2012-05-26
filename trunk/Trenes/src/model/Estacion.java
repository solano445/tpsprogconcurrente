package model;

import java.util.concurrent.Semaphore;

	
public class Estacion{

	//Variables
	public String nombre;
	private Semaphore[] permisos;
	public Integer cantAndenes;

	//Constructor
	/**
	 * Lo construyo con la cantidad de permisos que recibo por parametro
	 */
	public Estacion(String nombre , Integer cantAndenes) {
		this.permisos= new Semaphore[2];
		this.permisos[0] = new Semaphore(cantAndenes, true);
		this.permisos[1] = new Semaphore(cantAndenes, true);
		this.nombre = nombre;
		this.cantAndenes=cantAndenes;
	}

	//Metodos
	/**
	 * Da el permiso del semaforo
	 * @throws InterruptedException
	 */
	public void pedirPermisoDeIngreso(Integer anden) throws InterruptedException{
		this.permisos[anden].acquireUninterruptibly();
	}
	
	/**
	 * Retorna el permiso
	 */
	public void liberarPermisoDeIngreso(Integer anden){
		this.permisos[anden].release();
	}
	
	/**
	 * toString que retorna el nombre de la Estacion
	 */
	public String toString(){
		return  this.getNombre();
	}
	
	

	//Getters & Setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Semaphore[] getPermisos() {
		return permisos;
	}

	public void setPermisos(Semaphore[] permisos) {
		this.permisos = permisos;
	}
}
