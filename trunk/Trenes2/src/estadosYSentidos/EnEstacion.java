package estadosYSentidos;

import trenes.*;

public class EnEstacion extends EstadoTren {

	public EnEstacion(Tren tren) {
		super(tren);
	}

	@Override
	public void run() {
		System.out.println(this.tren.toString() + " saliendo de " + this.tren.estActual.getNombre());
		this.tren.siguienteEstacion();
		//this.tren.liberarPermisoDeIngreso();
	}

	@Override
	public EstadoTren getNext() {
		return this.tren.enMovimiento;
	}

}
