package estadosYSentidos;

import trenes.*;
import vista.CambioEnMovimiento;

public class EnMovimiento extends EstadoTren {

	public EnMovimiento(Tren tren) {
		super(tren);
	}

	@Override
	public void run() {
		//Hacer Cambio
		this.pantalla.agregarCambio(new CambioEnMovimiento(this.tren));
        EstacionRecorrido estacionAnterior = this.tren.sentido.estacionAnterior(this.tren.estActual);
		this.tren.sentido.liberarPermiso(estacionAnterior.estacionConcreta);
		//otra cosa
				
		//simula el tiempo de circulacion del tren sobre la via
		//##System.out.println(this.tren.toString() + " circulando hacia " + this.tren.estActual.getNombre());
		this.tren.dormir(300);
	}

	@Override
	public EstadoTren siguienteEstado() {
		return this.tren.esperandoIngreso;
	}


}
