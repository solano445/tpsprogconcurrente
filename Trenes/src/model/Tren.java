package model;



public class Tren extends Thread{

	//Variables
	/*True = sentidoDerecha / False = sentidoIzquierda*/
	private boolean aLaDerecha;
	private String identificador;
	private Estacion[] estaciones;
	private int estacionAnterior = 0;
	private Simulador simulador;
	
	
	public Tren(String idT, Estacion[] estacionesT, boolean aLaDerechaTemp, Simulador simuladorT){
		this.identificador = idT;
		this.estaciones = estacionesT;
		this.aLaDerecha = aLaDerechaTemp;
		this.simulador = simuladorT;		
		this.ubicarEnPrimerEstacion();
	}
	
	private void ubicarEnPrimerEstacion() {
		try {
			estaciones[estacionAnterior].pedirPermisoDeIngreso();
			estaciones[estacionAnterior].ocuparAnden(this);
			System.out.println("se ocupa el anden de " + estaciones[estacionAnterior].getNombre());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Estacion estacionSiguiente(){
		if(aLaDerecha){
			estacionAnterior = ((estacionAnterior + 1) % estaciones.length);
		}
		else{
			estacionAnterior = (estaciones.length + ((estacionAnterior - 1) % estaciones.length)) % estaciones.length;
		}
		return this.estaciones[estacionAnterior];
	}
	
	
	/**
	 * Hace Iniciar El Recorrido del tren por toda la lista de estaciones.
	 */
	@Override
	public void run() {
		/*
		 * libera la primera estacion que ocupaba y empieza el recorrido;
		 */
		this.estaciones[estacionAnterior].liberarPermisoDeIngreso();
		System.out.println("Se libera el anden de " + estaciones[estacionAnterior].getNombre());
		
		while(true) {
			this.simularPasoDelTiempo(1000);
			System.out.println("Se simula 1 segundo de tiempo de recorrido");
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
			System.out.println("Se pide permiso para ocupar el anden de " + estaciones[estacionAnterior].getNombre());
			//System.out.println("Tren:" + this +" Entrando a Estacion:" + estacion);
			estacion.ocuparAnden(this);		
			System.out.println("se ocupa anden de " + estaciones[estacionAnterior].getNombre());
			this.simularCargaDePasajeros();			
			System.out.println("se levanta pasajeros en " + estaciones[estacionAnterior].getNombre());
			estacion.liberarAnden(this);	
			System.out.println("se libera anden de " + estaciones[estacionAnterior].getNombre());
			//System.out.println("Tren:" + this + " Saliendo de Estacion:" + estacion);
			estacion.liberarPermisoDeIngreso();
			System.out.println("Se libera el anden de " + estaciones[estacionAnterior].getNombre());
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
		return identificador;
	}
	public void setNumero(String numero) {
		this.identificador = numero;
	}
	public Estacion[] getEstaciones() {
		return estaciones;
	}
	public void setEstaciones(Estacion[] estaciones) {
		this.estaciones = estaciones;
	}
	
}
