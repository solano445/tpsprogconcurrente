package vista;

import trenes.Tren;

public class EstadoTemporal {

	public Tren tren;
	
	public EstadoTemporal(Tren tren) {
		this.tren=tren;
	}

	public String toString(){
		return this.tren.toString();				
	}
}
