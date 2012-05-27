package estadosYSentidos;

import trenes.*;
import vista.CambioEsperandoIngreso;

public class EsperandoIngreso extends EstadoTren {

	public EsperandoIngreso(Tren tren) {
		super(tren);
	}

	@Override
	public void run() {
		//Hacer Cambio
		this.pantalla.agregarCambio(new CambioEsperandoIngreso(this.tren));
		//otra cosa
				
		System.out.println(this.tren.toString() + " Entrando a " + this.tren.estActual.getNombre());
		this.tren.sentido.solicitarPermiso(this.tren.getEstacionActual());
		System.out.println(this.tren.toString() + " Entro en " + this.tren.estActual.getNombre());
	}

	@Override
	public EstadoTren siguienteEstado() {
		return this.tren.enEstacion;
	}

}
