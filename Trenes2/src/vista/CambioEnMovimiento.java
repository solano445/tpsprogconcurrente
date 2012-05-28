package vista;

import trenes.EstacionRecorrido;
import trenes.Tren;

public class CambioEnMovimiento extends Cambio {

	public CambioEnMovimiento(Tren tren) {
		super(tren);
	}
	
	@Override
	public void plasmar(Pantalla pantalla) {
		System.out.println("plasmando en movimiento");
		//sacar estado temporal anterior
		//conseguir la VistaEstacion
		VistaEstacion vista = this.estacion.estacionConcreta.vistaEstacion;

		EstacionRecorrido estacionAnterior = this.tren.sentido.estacionAnterior(this.estacion);
		VistaEstacion vistaAnterior = estacionAnterior.estacionConcreta.vistaEstacion;
		//saber en que coleccion de la vista estacion esta el estado temporal
		//depende del estado y del sentido //estado ingrendo
		vistaAnterior.enEstacion.remove(Pantalla.devolverEstado(vistaAnterior.enEstacion, tren));
		
		//crear nuevo estado temporal
		this.tren.sentido.crearVistaTrenMovimiento(vista, this.tren);
		
	}

	@Override
	public String toStringTemplate() {
		return "EnMovimiento";
	}

}
