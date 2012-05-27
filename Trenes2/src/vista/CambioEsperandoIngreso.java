package vista;

import trenes.Tren;

public class CambioEsperandoIngreso extends Cambio {

	public CambioEsperandoIngreso(Tren tren) {
		super(tren);
	}

	@Override
	public void plasmar(Pantalla pantalla) {
		//sacar estado temporal anterior
		//conseguir la VistaEstacion
		VistaEstacion vista = this.estacion.estacionConcreta.vistaEstacion;
		//saber en que coleccion de la vista estacion esta el estado temporal
		//depende del estado y del sentido //estado ingrendo
		this.tren.sentido.removeFromMovimiento(vista, this.tren);
		
		//crear nuevo estado temporal
		this.tren.sentido.crearEsperando(vista, this.tren);
		
	}
	

}
