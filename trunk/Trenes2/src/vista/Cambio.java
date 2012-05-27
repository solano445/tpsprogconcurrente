package vista;

import trenes.EstacionRecorrido;
import trenes.Tren;

public abstract class Cambio {

	public Tren tren;
	public EstacionRecorrido estacion;

	public Cambio(Tren tren){
		this.tren=tren;
		this.estacion=tren.estActual;
	}
	
	abstract public void plasmar(Pantalla pantalla);
}
