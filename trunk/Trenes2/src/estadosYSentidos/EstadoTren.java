package estadosYSentidos;

import trenes.*;

abstract public class EstadoTren {

	public Tren tren;
	public EstadoTren(Tren tren) {
		this.tren = tren;
	}
	abstract public void run();
	
	abstract public EstadoTren getNext();
}
