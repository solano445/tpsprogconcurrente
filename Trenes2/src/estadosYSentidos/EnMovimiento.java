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
		this.pantalla.agregarCambio(new CambioEnMovimiento(this.tren));
        EstacionRecorrido estacionAnterior = this.tren.sentido.estacionAnterior(this.tren.estActual);
		
        
        //actua sobre el lockAndenTrenesA y la condition accesoAndenTrenA
        this.tren.sentido.liberarPermiso(estacionAnterior.estacionConcreta , this.tren);
				
		//simula el tiempo de circulacion del tren sobre la via
		this.tren.dormir(300);
	}

	@Override
	public EstadoTren siguienteEstado() {
		return this.tren.esperandoIngreso;
	}


}
