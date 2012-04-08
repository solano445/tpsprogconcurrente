package model;



public class Tren extends Thread{

	//Variables
	/*True = sentidoDerecha / False = sentidoIzquierda*/
	private boolean aLaDerecha;
	private String numero;
	private Estacion[] estaciones;
	private int ultimaEstacion;
	private Simulador simulador;
	
	
	public Tren(String numeroT, Estacion[] estacionesT, boolean aLaDerechaTemp, Simulador simuladorT){
		this.numero=numeroT;
		this.estaciones= estacionesT;
		this.aLaDerecha = aLaDerechaTemp;
		this.simulador = simuladorT;
		this.ubicarEnPrimerEstacion();
	}
	
	private void ubicarEnPrimerEstacion() {
		try {
			estaciones[0].pedirPermisoDeIngreso();
			estaciones[0].ocuparAnden(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Estacion estacionSiguiente(){
		if(aLaDerecha){
			ultimaEstacion = ((ultimaEstacion + 1) % estaciones.length);
		}
		else{
			ultimaEstacion = ((ultimaEstacion - 1) % estaciones.length);
		}
		return this.estaciones[ultimaEstacion];
	}
	
	
	/**
	 * Hace Iniciar El Recorrido del tren por toda la lista de estaciones.
	 */
	@Override
	public void run() {
		/*
		 * libera la primera estacion que ocupaba y empieza el recorrido;
		 */
		this.estaciones[ultimaEstacion].liberarPermisoDeIngreso();
		while(true) {
			this.simularPasoDelTiempo(1000);
			/**este tiempo deberia ser aleatorio o depender de la estacion*/
			this.simularPasoPorEstacion(this.estacionSiguiente());
		}
		//System.out.println("Fin Del Recorrido Del Tren:" + this);
	}

	/**
	 * Simula el paso por una estacion, primero pide permiso para entrar y despues simula un demora por carga de pasajeros
	 * que no son mas que sleeps del thread.
	 * @param Estacion estacion
	 */	
	private void simularPasoPorEstacion(Estacion estacion) {
		try {
			estacion.pedirPermisoDeIngreso();			
			//System.out.println("Tren:" + this +" Entrando a Estacion:" + estacion);
			this.simularCargaDePasajeros();
			//System.out.println("Tren:" + this + " Saliendo de Estacion:" + estacion);
			estacion.liberarPermisoDeIngreso();
		} catch (InterruptedException e) {
			e.printStackTrace();
			estacion.release();
			//System.out.println("Choque En La Estacion:" + estacion);
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
		if(this.aLaDerecha){
			return "=T" + this.getNumero() + ">";
		}
		return "<T" + this.getNumero() + "=";
	}
	
	//Getters & Setters
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Estacion[] getEstaciones() {
		return estaciones;
	}
	public void setEstaciones(Estacion[] estaciones) {
		this.estaciones = estaciones;
	}
	
}
