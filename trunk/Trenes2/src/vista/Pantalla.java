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
		while (Simulador.terminaSimulacion) {
			this.imprimirCambios();
		}
		this.imprimirCambios();
	}

	public synchronized void imprimirCambios() {
		if (cambios.isEmpty()) {
			try {this.wait();} catch (InterruptedException e) {e.printStackTrace();}
		} else {
			this.imprimirVistas();
			for (Cambio cambio : this.cambios) {
				cambio.plasmar(this);
				this.imprimirCambios();
			}
			this.cambios.clear();
		}
	}
	
	

	public void imprimirVistas() {
		for (VistaEstacion vista : this.vistaEstaciones) {
			vista.imprimir();
		}
		System.out.println("");
	}

	public synchronized void agregarCambio(Cambio cambio) {
		this.cambios.add(cambio);
		this.notify();
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
	public static EstadoTemporal devolverEstado(List<EstadoTemporal> esperandoSentidoA,	Tren tren) {
		for (EstadoTemporal estadoTemporal : esperandoSentidoA) {
			if(estadoTemporal.tren.equals(tren)){
				return estadoTemporal;
			}
		}
		throw new RuntimeException("No Existe el estado temporal para el tren: " + tren);
	}
}
