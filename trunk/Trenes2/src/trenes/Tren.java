package trenes;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vista.Pantalla;
import estadosYSentidos.EnEstacion;
import estadosYSentidos.EnMovimiento;
import estadosYSentidos.EsperandoIngreso;
import estadosYSentidos.EstadoTren;
import estadosYSentidos.Sentido;


public class Tren extends Thread{
	
	public EstacionRecorrido estActual;
	public EstadoTren estadoActual;
	public EnMovimiento enMovimiento = new EnMovimiento(this);
	public EsperandoIngreso esperandoIngreso = new EsperandoIngreso(this);
	public EnEstacion enEstacion = new EnEstacion(this);
	public Sentido sentido;
	public String nombre;
	public TimerTren timer;
	public Lock lockTrenViaje = new ReentrantLock(true);
	public Condition pasajerosViajando = lockTrenViaje.newCondition();
	public Condition pasajerosAbordando = lockTrenViaje.newCondition();
	public Lock lockTrenEsperandoSalir = new ReentrantLock(true);
	public Condition trenEsperandoSalir = lockTrenEsperandoSalir.newCondition();
	public Integer cantPasajerosMax;
	public Integer cantPasajerosAbordo;
	public List<Pasajero>  pasajerosABordo;
	
	public void run(){
		while(!Simulador.terminaSimulacion){
			this.estadoActual.run();
			this.estadoActual = this.estadoActual.siguienteEstado();
		}
	}
	
	//Constructor
	public Tren(String nombreP, Integer cantMax, Recorrido recorrido, Sentido sentidoP){
		this.nombre = nombreP;
		this.cantPasajerosAbordo=0;
		this.cantPasajerosMax=cantMax;
		this.estadoActual = this.esperandoIngreso;
		this.sentido = sentidoP;
		this.pasajerosABordo= new LinkedList<Pasajero>();
		this.estActual = this.sentido.primerEstacionRecorrido(recorrido);
		Pantalla.getInstance().iniciarEsperandoIngreso(this);		
	}
	
	public String toString(){
		return this.sentido.trenToString(this);
	}
	
	public static List<Tren> getTrenes(Recorrido recorrido){
		List<Tren> trenes = new LinkedList<Tren>();
		for (int i = 1; i < 6; i++) {
			trenes.add(new Tren("Unidad" + i, (i%3*10)+20 ,recorrido , Sentido.getA()));
		}
		for (int i = 6; i < 11; i++) {
			trenes.add(new Tren("Unidad" + i, (i%3*10)+20 ,recorrido , Sentido.getB()));
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

	public String toString(Integer cantPasajerosAbordo) {
		return this.sentido.trenToString(this, cantPasajerosAbordo);
	}
}
