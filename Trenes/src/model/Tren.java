package model;

import java.util.List;

public class Tren extends Thread{

	//Variables
	private String numero;
	private List<Estacion> estaciones;
	
	
	public Tren(String _numero, List<Estacion> _estaciones){
		this.numero=_numero;
		this.estaciones= _estaciones;
	}
	
	
	/**
	 * Hace Iniciar El Recorrido del tren por toda la lista de estaciones.
	 */
	@Override
	public void run() {
		for (Estacion estacion : this.getEstaciones()) {
			this.simularPasoDelTiempo(4000);
			this.simularPasoPorEstacion(estacion);
		}
		System.out.println("Fin Del Recorrido Del Tren:" + this);
	}

	/**
	 * Simula el paso por una estacion, primero pide permiso para entrar y despues simula un demora por carga de pasajeros
	 * que no son mas que sleeps del thread.
	 * @param Estacion estacion
	 */
	private void simularPasoPorEstacion(Estacion estacion) {
		try {
			estacion.pedirPermisoDeIngreso();
			System.out.println("Tren:" + this +" Entrando a Estacion:" + estacion);
			this.simularCargaDePasajeros();
			System.out.println("Tren:" + this + " Saliendo de Estacion:" + estacion);
			estacion.liberarPermisoDeIngreso();
		} catch (InterruptedException e) {
			e.printStackTrace();
			estacion.release();
			System.out.println("Choque En La Estacion:" + estacion);
		}
		
	}

	/**
	 * Duerme 2 segundos al Thread simulando carga de pasajeros al estilo subte japones(?).
	 */
	private void simularCargaDePasajeros() {
		this.simularPasoDelTiempo(2000);
	}


	/**
	 * Tira a dormir n segundos el Thread para simular un paso del tiempo.
	 * Ya sea para simular 
	 */
	private void simularPasoDelTiempo(Integer tiempo) {
		try {
			Tren.sleep(tiempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Exploto Todo");
		}
	}

	/**
	 * toString que imprime el numero del Tren unicamente
	 * @return String numero
	 */
	public String toString(){
		return this.getNumero();
	}
	
	//Getters & Setters
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public List<Estacion> getEstaciones() {
		return estaciones;
	}
	public void setEstaciones(List<Estacion> estaciones) {
		this.estaciones = estaciones;
	}
}
