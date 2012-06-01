package estadosYSentidos;

import java.util.LinkedList;
import java.util.List;

import trenes.*;
import vista.CambioEsperandoIngreso;

public class EsperandoIngreso extends EstadoTren {

	public EsperandoIngreso(Tren tren) {
		super(tren);
	}

	@Override
	public void run() {
		//Hacer Cambio esperando ingreso
		this.pantalla.agregarCambio(new CambioEsperandoIngreso(this.tren));
		this.tren.sentido.solicitarPermiso(this.tren.getEstacionActual() , this.tren);
		
	}

	@Override
	public EstadoTren siguienteEstado() {
		return this.tren.enEstacion;
	}

}
