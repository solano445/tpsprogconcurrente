package estadosYSentidos;

import trenes.*;

public class EsperandoIngreso extends EstadoTren {

	public EsperandoIngreso(Tren tren) {
		super(tren);
	}

	@Override
	public void run() {
		System.out.println(this.tren.toString() + " Entrando a " + this.tren.estActual.getNombre());
		//this.tren.obtenerPermisoDeIngreso();
	}

	@Override
	public EstadoTren getNext() {
		return this.tren.enEstacion;
	}

}
