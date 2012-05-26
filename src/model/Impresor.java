package model;

import java.lang.Thread;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Impresor extends Thread {
	
	List<Estacion> estaciones = new LinkedList<Estacion>();	
	
	public class Estacion{
		List<TrenEstado> enMovimientoAntesAnden0 = new LinkedList<TrenEstado>();
		List<TrenEstado> esperandoAnden0 = new LinkedList<TrenEstado>();
		List<TrenEstado> enEstacion = new LinkedList<TrenEstado>();
		List<TrenEstado> esperandoAnden1 = new LinkedList<TrenEstado>();
	}	
	
	/**responde al mismo protocolo que la clase Tren
	con al particularidad que guarda un estado, cantidad pasajeros por ejemplo*/
	public class TrenEstado{
		public Tren tren;
		public TrenEstado(Tren trenT){
			this.tren = trenT;
		}		
		public String numero(){
			return this.tren.numero;
		}
	}
	
	Lock lockImpresor = new ReentrantLock();
	Condition noHayCambios = lockImpresor.newCondition();

	
	
}
