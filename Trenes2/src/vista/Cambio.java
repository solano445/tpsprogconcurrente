package vista;

import trenes.EstacionRecorrido;
import trenes.Tren;

public abstract class Cambio {

	public Tren tren;
	public EstacionRecorrido estacion;
	public Integer cantPasajerosEstacion;

	public Cambio(Tren tren){
		this.tren=tren;
		this.estacion=tren.estActual;
		this.cantPasajerosEstacion = estacion.estacionConcreta.cantPasajerosEsperando();
	}
	
	
	
	abstract public String toStringTemplate();



	abstract public void plasmar(Pantalla pantalla);
}
