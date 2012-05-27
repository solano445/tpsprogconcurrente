package trenes;

import java.util.LinkedList;
import java.util.List;

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
			this.estadoActual = this.estadoActual.getNext();
		}
		
	}

	public Tren(String nombreP, Recorrido recorrido, Sentido sentidoP){
		this.nombre = nombreP;
		this.estadoActual = this.enMovimiento;
		this.sentido = sentidoP;
		this.estActual = this.sentido.primerEstacionRecorrido(recorrido);
	}
	
	public String toString(){
		return "[" + this.nombre + "]";
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

	public void circular(long tiempo) {
		try {sleep(tiempo);} catch (InterruptedException e) {e.printStackTrace();}		
	}

	public void siguienteEstacion() {
		this.estActual = this.sentido.siguienteEstacion(this.estActual);
	}

}
