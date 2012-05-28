package trenes;

import java.util.LinkedList;
import java.util.List;

import vista.EstadoTemporal;
import vista.Pantalla;
import vista.VistaEstacion;

import estadosYSentidos.*;


public class Tren extends Thread{
	
	public EstacionRecorrido estActual;
	public EstadoTren estadoActual;
	public EnMovimiento enMovimiento = new EnMovimiento(this);
	public EsperandoIngreso esperandoIngreso = new EsperandoIngreso(this);
	public EnEstacion enEstacion = new EnEstacion(this);
	public Sentido sentido;
	public String nombre;
	

	public void run(){
		while(!Simulador.terminaSimulacion){
			this.estadoActual.run();
			this.estadoActual = this.estadoActual.siguienteEstado();
		}
	}
	//Constructor
	public Tren(String nombreP, Recorrido recorrido, Sentido sentidoP){
		this.nombre = nombreP;
		this.estadoActual = this.esperandoIngreso;
		this.sentido = sentidoP;
		this.estActual = this.sentido.primerEstacionRecorrido(recorrido);
		Pantalla.getInstance().iniciarEsperandoIngreso(this);		
	}
	
	public String toString(){
		return this.sentido.trenToString(this);
	}
	
	public static List<Tren> getTrenes(Recorrido recorrido){
		List<Tren> trenes = new LinkedList<Tren>();
		for (int i = 1; i < 6; i++) {
			trenes.add(new Tren("Unidad" + i, recorrido , Sentido.getA()));
		}
		for (int i = 6; i < 11; i++) {
			trenes.add(new Tren("Unidad" + i, recorrido , Sentido.getB()));
		}
		return trenes;		
	}
	
	static public void testearGetTrenes(){		
		List<Tren> trenes = getTrenes(Recorrido.getRecorrido());
		for (Tren tren : trenes) {
			System.out.print(tren.toString());
		}
		System.out.print("\n");
	}

	public void dormir(long tiempo) {
		try {sleep(tiempo);} catch (InterruptedException e) {e.printStackTrace();}		
	}

	public void siguienteEstacion() {
		this.estActual = this.sentido.siguienteEstacion(this.estActual);
	}

	public EstacionConcreta getEstacionActual(){
		return this.estActual.estacionConcreta;
	}
}
