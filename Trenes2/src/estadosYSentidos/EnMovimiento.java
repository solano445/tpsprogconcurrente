package estadosYSentidos;

import trenes.*;

public class EnMovimiento extends EstadoTren {

	public EnMovimiento(Tren tren) {
		super(tren);
	}

	@Override
	public void run() {
		//simula el tiempo de circulacion del tren sobre la via
		System.out.println(this.tren.toString() + " circulando hacia " + this.tren.estActual.getNombre());
		this.tren.circular(300);
	}

	@Override
	public EstadoTren getNext() {
		return this.tren.esperandoIngreso;
	}


}
