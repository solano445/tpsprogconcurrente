package estadosYSentidos;

import trenes.*;
import vista.CambioEnMovimiento;

public class EnMovimiento extends EstadoTren {

	public EnMovimiento(Tren tren) {
		super(tren);
	}

	@Override
	public void run() {
		//Hacer Cambio en movimiento a siguiente estacion
        EstacionRecorrido estacionAnterior = this.tren.sentido.estacionAnterior(this.tren.estActual);
        this.pantalla.agregarCambio(new CambioEnMovimiento(this.tren));
		        
        //actua sobre el lockAndenTrenesA y la condition accesoAndenTrenA
        this.tren.sentido.liberarPermiso(estacionAnterior.estacionConcreta , this.tren);
		//
        //Aca es donde se deberia actualizar la cantidad de pasajeros de la estacion anterior
		
        //simula el tiempo de circulacion del tren sobre la via
		this.tren.dormir(1000);
	}

	@Override
	public EstadoTren siguienteEstado() {
		return this.tren.esperandoIngreso;
	}


}
