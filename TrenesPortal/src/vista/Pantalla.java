package vista;

import java.util.LinkedList;
import java.util.List;



import trenes.Simulador;
import trenes.Tren;


public class Pantalla extends Thread{	

	public List<Cambio> cambios = new LinkedList<Cambio>();
	public List<VistaEstacion> vistaEstaciones = new LinkedList<VistaEstacion>();

	
	public void run() {
		this.imprimirVistas();
		while (!Simulador.terminaSimulacion) {
			this.imprimirCambios();
		}
		this.imprimirCambios();
		//TODO cerrar la ventana de la interface
		//Simulador.frame.
		System.out.println("Simulacion finalizada");
	}
	
	public synchronized void finalizarSimulacion(){
		notify();
	}

	public synchronized void imprimirCambios() {
		if (cambios.isEmpty()) {
			try {this.wait();} catch (InterruptedException e) {e.printStackTrace();}
		} else {
			for (Cambio cambio : this.cambios) {
				cambio.plasmar(this);
				this.imprimirVistas();
			}
			this.cambios.clear();
		}
	}
	
	public synchronized void iniciarEsperandoIngreso(Tren tren) {
		VistaEstacion vista = tren.estActual.estacionConcreta.vistaEstacion;
		tren.sentido.crearVistaTrenMovimiento(vista, tren, 0);
	}
	
	

	public void imprimirVistas() {
		for (VistaEstacion vista : this.vistaEstaciones) {
			vista.imprimir();
		}
		System.out.println("");
	}

	public synchronized void agregarCambio(Cambio cambio) {
		this.cambios.add(cambio);
		notify();
	}
	
	
	
	
	
	public static Pantalla instancia = null;
	public static Pantalla getInstance(){
		if(instancia != null){
			return instancia;
		}
		else{
			instancia = new Pantalla();
			return instancia;
		}
	}
	
	/**
	 * No deberia llegar una lista que no tenga asociado al tren que llega como parametro
	 * @param List<EstadoTemporal>esperandoSentidoA
	 * @param Tren tren
	 * @return EstadoTemporal
	 */
	public static EstadoTemporal devolverEstado(List<EstadoTemporal> estadoSentido,	Tren tren) {
		for (EstadoTemporal estadoTemporal : estadoSentido) {
			if(estadoTemporal.tren.equals(tren)){
				return estadoTemporal;
			}
		}
		throw new RuntimeException("No Existe el estado temporal para el tren: " + tren + "en lista" + estadoSentido);
	}
	public static EstadoTemporal devolverEstado(List<EstadoTemporal> estadoSentido,	Tren tren, String nombreEstacion) {
		//System.out.println("Listar " + nombreEstacon + " - " + estadoSentido);
		for (EstadoTemporal estadoTemporal : estadoSentido) {
			if(estadoTemporal.tren.equals(tren)){
				return estadoTemporal;
			}
		}
		throw new RuntimeException("No Existe el estado temporal para el tren: " + tren + "en Vista" + nombreEstacion);
	}
}
