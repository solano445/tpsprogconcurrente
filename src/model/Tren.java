package model;

import java.util.concurrent.Semaphore;

public class Tren extends Thread {

	// Variables
	public String numero;
	private Recorrido recorrido;
	private Semaphore permiso;
	private Estacion actual;
	private Estacion anterior;
	private String estado;
	private Integer anden; // 0 = izquierda - 1 = derecha

	// Constructor
	public Tren(String numero, Recorrido recorrido, Integer anden) {
		this.numero = numero;
		this.setRecorrido(recorrido);
		this.permiso = new Semaphore(1, true);
		this.estado = "Detenido";
		this.setAnden(anden);
		this.actual= new Estacion("testLoco", 100);
	}

	// Metodos

	/**
	 * Hace Iniciar Todos los Recorrido del tren por toda la lista de
	 * estaciones. Al Terminar Un Recorrido Continua al Siguiente. Se Podria
	 * Considerar que el tren al momento de comenzar sus recorridos ya tiene
	 * todos.
	 */
	@Override
	public void run() {
		while (true) {
					
				for (Estacion estacion : this.getRecorrido().getEstaciones()) {
					this.waitP();
					this.setAnterior(this.getActual());
					this.setActual(estacion);
					this.signal();
					this.simularPasoDelTiempo(400);
					this.simularPasoPorEstacion(estacion);
				}
				System.out.println("Fin Del Recorrido:" + recorrido	+ " Del Tren:" + this);
			}
	}
	/**
	 * Libera el Permiso Del Semaforo
	 */
	public void signal() {
		this.getPermiso().release();
	}
	/**
	 * Pide Permiso Del Semaforo
	 */
	public void waitP() {
		this.getPermiso().acquireUninterruptibly();
	}

	/**
	 * Simula el paso por una estacion, primero pide permiso para entrar y
	 * despues simula un demora por carga de pasajeros que no son mas que sleeps
	 * del thread.
	 * 
	 * @param Estacion
	 *            estacion
	 */
	private void simularPasoPorEstacion(Estacion estacion) {
		try {
			//Paso A Esperar para Ingresar

			this.waitP();
			this.setEstado("Esperando Ingreso");
			System.out.println("Tren:" + this + " Esperando Ingreso A Estacion: " + estacion);
			this.signal();
			
			estacion.pedirPermisoDeIngreso(this.getAnden());

			//Paso a estado DETENIDO Y EN CARGA
			this.waitP();
			this.setEstado("Detenido En Estacion");
			System.out.println("Tren:" + this + " Entrando a Estacion:" + estacion);
			this.simularCargaDePasajeros();
			//PASA A ESTADO EN MOVIMIENTO
			this.signal();
			
			this.waitP();
			this.setEstado("En Movimiento");
			estacion.liberarPermisoDeIngreso(this.getAnden());
			System.out.println("Tren:" + this + " Saliendo de Estacion:"+ estacion);
			this.signal();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			//PASA A ESTADO EN MOVIMIENTO
			this.setEstado("En Movimiento");
			estacion.liberarPermisoDeIngreso(this.getAnden());
			System.out.println("Choque En La Estacion:" + estacion);
		}
	}
	
	public String imprimir(){		
		if(this.getAnden().equals(0)){
			if(this.getEstado().equals("En Movimiento")){
				return " <" + this.getNumero() + "=";
			}
			else{
				return " [" + this.getNumero() + "=";
			}
		}
		else if(this.getEstado().equals("En Movimiento")){
				return " =" + this.getNumero() + ">";
			}
		return " =" + this.getNumero() + "]";
	}

	/**
	 * Duerme 2 segundos al Thread simulando carga de pasajeros .
	 */
	private void simularCargaDePasajeros() {
		this.simularPasoDelTiempo(2000);
	}

	/**
	 * Tira a dormir n segundos el Thread para simular un paso del tiempo. Ya
	 * sea para simular
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
	 * Override de toString que imprime el numero del Tren unicamente
	 * 
	 * @return String numero
	 */
	public String toString() {
		return this.getNumero();
	}

	public String imprimirSiDerechaEstacion(Estacion estacionT) {
		// TODO Auto-generated method stub
		if(!this.getEstado().equals("Detenido En Estacion") && this.estaALaDerechaDeEstacion(estacionT)){
			return this.imprimir();
		}
		return "";
	}

	public boolean estaALaDerechaDeEstacion(Estacion estacionT) {
		if(this.getAnden().equals(0)){
			this.getActual().equals(estacionT);
		}		
		return this.getAnterior().equals(estacionT);
	}

	public String imprimirSiEstanEnEstacion(Estacion estacionT) {
		// TODO Auto-generated method stub
		if(this.getEstado().equals("Detenido En Estacion") && this.estaEnEstacion(estacionT)){
			return this.imprimir();
		}
		return "";
	}

	private boolean estaEnEstacion(Estacion estacionT) {
		return this.getActual().equals(estacionT);
	}

	// Getters & Setters
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Semaphore getPermiso() {
		return permiso;
	}
	public void setPermiso(Semaphore permiso) {
		this.permiso = permiso;
	}
	public Estacion getAnterior() {
		return anterior;
	}
	public void setAnterior(Estacion anterior) {
		this.anterior = anterior;
	}
	public Estacion getActual() {
		return actual;
	}
	public void setActual(Estacion actual) {
		this.actual = actual;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Recorrido getRecorrido() {
		return recorrido;
	}
	public void setRecorrido(Recorrido recorrido) {
		this.recorrido = recorrido;
	}

	public Integer getAnden() {
		return anden;
	}

	public void setAnden(Integer anden) {
		this.anden = anden;
	}
}
