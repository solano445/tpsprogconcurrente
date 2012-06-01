package vista;

import trenes.Tren;

public class CambioEnEstacion extends Cambio {

	public CambioEnEstacion(Tren tren) {
		super(tren);
	}

	@Override
	public void plasmar(Pantalla pantalla) {
		//sacar estado temporal anterior
		//conseguir la VistaEstacion
		VistaEstacion vista = this.estacion.estacionConcreta.vistaEstacion;
		//saber en que coleccion de la vista estacion esta el estado temporal
		//depende del estado y del sentido //estado ingrendo
		this.tren.sentido.removeFromEsperando(vista, this.tren);
		vista.cantPasajeros = this.cantPasajerosEstacion;
		//crear nuevo estado temporal
		vista.enEstacion.add(new EstadoTemporal(tren));
	}

	@Override
	public String toStringTemplate() {
		return "EnEstacion";
	}

}
