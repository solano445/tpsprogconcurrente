package vista;

import trenes.EstacionRecorrido;
import trenes.Tren;

public abstract class Cambio {

	public Tren tren;
	public EstacionRecorrido estacion;

	public Cambio(Tren tren){
		this.tren=tren;
		this.estacion=tren.estActual;
		System.out.println("Se crea " + this.toStringTemplate() + " - " + this.tren.nombre + " - " + this.estacion.getNombre());		
	}
	
	
	
	abstract public String toStringTemplate();



	abstract public void plasmar(Pantalla pantalla);
}
