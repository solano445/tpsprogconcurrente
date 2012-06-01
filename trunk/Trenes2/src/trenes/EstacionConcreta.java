package trenes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import estadosYSentidos.SentidoPasajero;

import vista.VistaEstacion;

public class EstacionConcreta {
	
	//Variables
	public String nombre;
	public Integer cantAndenes;
	public Integer cantAndenesOcupadosSentidoA;
	public Integer cantAndenesOcupadosSentidoB;
	public Integer esperaEnMilisegundos;
	public List<Tren> trenesAndenA;
	public List<Tren> trenesAndenB;
	
	
	public Lock lockAndenPasajerosA= new ReentrantLock(true);
	public Lock lockAndenPasajerosB= new ReentrantLock(true);
	public Lock lockAndenTrenesA= new ReentrantLock(true);
	public Lock lockAndenTrenesB= new ReentrantLock(true);
    public Condition accesoAndenTrenA = lockAndenTrenesA.newCondition();
    public Condition accesoAndenTrenB = lockAndenTrenesB.newCondition();
    public Condition pasajerosEsperandoAndenA = lockAndenPasajerosA.newCondition();
    public Condition pasajerosEsperandoAndenB = lockAndenPasajerosB.newCondition();
    
    public VistaEstacion vistaEstacion;
	public EstacionRecorrido estacionRecorrido;
    
	//Constructor
	public EstacionConcreta(String nombreP , Integer cantAndenesP, Integer esperaEnMilisegundosP) {
		this.nombre = nombreP;
		this.cantAndenes=cantAndenesP;
		this.esperaEnMilisegundos=esperaEnMilisegundosP;
		this.cantAndenesOcupadosSentidoA=0;
		this.cantAndenesOcupadosSentidoB=0;
		this.trenesAndenA = new ArrayList<Tren>();
		this.trenesAndenB = new ArrayList<Tren>();
	}
	
	
	
	//Metodos
	
	public void pedirPermisoIngresoSentidoA(Tren tren){
		lockAndenTrenesA.lock();
		//pedirPermisoIngresoSentido(this.cantAndenesOcupadosSentidoA, this.andenA);
		if(!(this.cantAndenesOcupadosSentidoA<cantAndenes)){
			try {this.accesoAndenTrenA.await();} catch (InterruptedException e) {e.printStackTrace();}
		}
		else{
			this.cantAndenesOcupadosSentidoA++;
			this.trenesAndenA.add(tren);
			this.pasajerosEsperandoAndenA.signalAll();
			
		}
		lockAndenTrenesA.unlock();
	}
	
	public void pedirPermisoIngresoSentidoB(Tren tren){
		lockAndenTrenesB.lock();
		//pedirPermisoIngresoSentido(this.cantAndenesOcupadosSentidoB, this.andenB);
		if(!(this.cantAndenesOcupadosSentidoB<cantAndenes)){
		try {this.accesoAndenTrenB.await();} catch (InterruptedException e) {e.printStackTrace();}
		}
		else{
			this.cantAndenesOcupadosSentidoB++;
			this.trenesAndenB.add(tren);
			this.pasajerosEsperandoAndenB.signalAll();
		}
		lockAndenTrenesB.unlock();
	}
	
	public void liberarPermisoIngresoSentidoA(Tren tren){
		lockAndenTrenesA.lock();
		this.cantAndenesOcupadosSentidoA--;
		this.accesoAndenTrenA.signal();
		this.trenesAndenA.remove(tren);
		lockAndenTrenesA.unlock();
	}
	
	public void liberarPermisoIngresoSentidoB(Tren tren){
		lockAndenTrenesB.lock();
		this.cantAndenesOcupadosSentidoB--;
		this.accesoAndenTrenB.signal();
		this.trenesAndenB.remove(tren);
		lockAndenTrenesB.unlock();
	}


	public void agregarEstacionRecorrido(EstacionRecorrido nuevaEstacion) {
		this.estacionRecorrido = nuevaEstacion;		
	}
	
	public SentidoPasajero sentidoMasCortoHasta(EstacionConcreta estacionDestino){
		return this.estacionRecorrido.sentidoMasCortoHasta(estacionDestino);
	}

}
