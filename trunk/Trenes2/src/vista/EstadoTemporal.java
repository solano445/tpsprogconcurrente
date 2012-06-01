package vista;

import trenes.Tren;

public class EstadoTemporal {

	public Tren tren;
	public Integer cantPasajerosAbordo;
	
	public EstadoTemporal(Tren tren, Integer cantPasajerosAbordo) {
		this.tren=tren;
		this.cantPasajerosAbordo= cantPasajerosAbordo;
	}

	public String toString(){
		//TODO
		return this.tren.toString(this.cantPasajerosAbordo);				
	}
}
