package estadosYSentidos;

import trenes.*;
import vista.Pantalla;

abstract public class EstadoTren {
	public Pantalla pantalla;

	public Tren tren;
	
	public EstadoTren(Tren tren) {
		this.tren = tren;
		this.pantalla= Pantalla.getInstance();
	}
	abstract public void run();
	
	abstract public EstadoTren siguienteEstado();
	
}

