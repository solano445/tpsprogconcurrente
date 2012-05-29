package trenes;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vista.VistaEstacion;

public class EstacionConcreta {
	
	//Variables
	public String nombre;
	public Integer cantAndenes;
	public Integer cantAndenesOcupadosSentidoA;
	public Integer cantAndenesOcupadosSentidoB;
	public Integer esperaEnMilisegundos;
	
	public Lock lockAndenA= new ReentrantLock(true);
	public Lock lockAndenB= new ReentrantLock(true);
    public Condition andenA = lockAndenA.newCondition();
    public Condition andenB = lockAndenB.newCondition();
    
    public VistaEstacion vistaEstacion;
    
	//Constructor
	public EstacionConcreta(String nombreP , Integer cantAndenesP, Integer esperaEnMilisegundosP) {
		this.nombre = nombreP;
		this.cantAndenes=cantAndenesP;
		this.esperaEnMilisegundos=esperaEnMilisegundosP;
		this.cantAndenesOcupadosSentidoA=0;
		this.cantAndenesOcupadosSentidoB=0;
	}
	
	
	
	//Metodos
	
	public void pedirPermisoIngresoSentidoA(){
		lockAndenA.lock();
		if(!(this.cantAndenesOcupadosSentidoA<cantAndenes)){ 
			try {this.andenA.await();} catch (InterruptedException e) {e.printStackTrace();}
		}
		else{
			this.cantAndenesOcupadosSentidoA++;
		}
		lockAndenA.unlock();
	}
	
	public void pedirPermisoIngresoSentidoB(){
		lockAndenB.lock();
		if(!(this.cantAndenesOcupadosSentidoB<cantAndenes)){ 
			try {this.andenB.await();} catch (InterruptedException e) {e.printStackTrace();}
		}
		else{
			this.cantAndenesOcupadosSentidoB++;
		}
		lockAndenB.unlock();
	}
	public void liberarPermisoIngresoSentidoA(){
		lockAndenA.lock();
		this.cantAndenesOcupadosSentidoA--;
		this.andenA.signal();
		lockAndenA.unlock();
	}
	
	public void liberarPermisoIngresoSentidoB(){
		lockAndenB.lock();
		this.cantAndenesOcupadosSentidoB--;
		this.andenB.signal();
		lockAndenB.unlock();
	}
	
	

}
