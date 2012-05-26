package model;

import java.util.concurrent.Semaphore;

public class Tren extends Thread {

	// Variables
	public String numero;
	private Recorrido recorrido;
	private Estacion actual;
	private Estacion anterior;
	private EstadoTrenEnum estado;
	private Integer anden; // 0 = izquierda - 1 = derecha

	// Constructor
	public Tren(String numero, Recorrido recorrido, Integer anden) {
		this.numero = numero;
		this.setRecorrido(recorrido);
		this.estado = EstadoTrenEnum.Detenido;
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
				this.setActual(this.getRecorrido().getEstaciones().get(0));	
				for (Estacion estacion : this.getRecorrido().getEstaciones()) {
					//this.waitP();	
					this.setAnterior(this.getActual());
					this.setActual(estacion);
					//this.signal();
					this.simularPasoDelTiempo(400);
					this.simularPasoPorEstacion(estacion);
				}
				System.out.println("Fin Del Recorrido:" + recorrido	+ " Del Tren:" + this);
			}
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

			//this.waitP();
			Impresor.get().agregarCambio(new Cambio(this, EstadoTrenEnum.EnMovimiento, this.anterior, this.actual, true));
			this.setEstado(EstadoTrenEnum.EnMovimiento);
			estacion.liberarPermisoDeIngreso(this.getAnden());
			System.out.println("Tren:" + this + " Saliendo de Estacion:"+ estacion);
			//this.signal();
			
			//this.waitP();
			Impresor.get().agregarCambio(new Cambio(this, EstadoTrenEnum.EsperandoIngreso, this.anterior, this.actual, false));
			this.setEstado(EstadoTrenEnum.EsperandoIngreso);
			System.out.println("Tren:" + this + " Esperando Ingreso A Estacion: " + estacion);
			//this.signal();
			
			estacion.pedirPermisoDeIngreso(this.getAnden());

			//Paso a estado DETENIDO Y EN CARGA
			//this.waitP();
			Impresor.get().agregarCambio(new Cambio(this, EstadoTrenEnum.DetenidoEnEstacion, this.anterior, this.actual, false));
			this.setEstado(EstadoTrenEnum.DetenidoEnEstacion);
			System.out.println("Tren:" + this + " Entrando a Estacion:" + estacion);
			this.simularCargaDePasajeros();
			//PASA A ESTADO EN MOVIMIENTO
			//this.signal();
			
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			//PASA A ESTADO EN MOVIMIENTO
			this.setEstado(EstadoTrenEnum.EnMovimiento);
			estacion.liberarPermisoDeIngreso(this.getAnden());
			System.out.println("Choque En La Estacion:" + estacion);
		}
	}
	
	public String imprimir(){		
		if(this.getAnden().equals(0)){
			if(this.getEstado().equals(EstadoTrenEnum.EnMovimiento)){
				return " <" + this.getNumero() + "=";
			}
			else{
				return " [" + this.getNumero() + "=";
			}
		}
		else if(this.getEstado().equals(EstadoTrenEnum.EnMovimiento)){
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
		if(!this.getEstado().equals(EstadoTrenEnum.DetenidoEnEstacion) && this.estaALaDerechaDeEstacion(estacionT)){
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
		if(this.getEstado().equals(EstadoTrenEnum.DetenidoEnEstacion) && this.estaEnEstacion(estacionT)){
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
	public EstadoTrenEnum getEstado() {
		return estado;
	}
	public void setEstado(EstadoTrenEnum estado) {
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
