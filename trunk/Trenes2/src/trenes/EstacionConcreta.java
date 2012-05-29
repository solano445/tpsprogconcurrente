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
			System.out.println("Permiso Otorgado En Anden A " + this.nombre);
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
			System.out.println("Permiso Otorgado En Anden B " + this.nombre);
		}
		lockAndenB.unlock();
	}
	public void liberarPermisoIngresoSentidoA(){
		lockAndenA.lock();
		if(cantAndenesOcupadosSentidoA<1){
			throw new RuntimeException("Se Esta Intentando Liberar mas andenes de los que hay. SENTIDO A " + this.nombre);
		}
		this.cantAndenesOcupadosSentidoA--;
		System.out.println("Permiso Liberado En Anden A " + this.nombre);
		this.andenA.signal();
		lockAndenA.unlock();
	}
	
	public void liberarPermisoIngresoSentidoB(){
		lockAndenB.lock();
		if(cantAndenesOcupadosSentidoB<1){
			throw new RuntimeException("Se Esta Intentando Liberar mas andenes de los que hay. SENTIDO B " + this.nombre);
		}
		this.cantAndenesOcupadosSentidoB--;
		System.out.println("Permiso Liberado En Anden B " + this.nombre);
		this.andenB.signal();
		lockAndenB.unlock();
	}
	
	

}
