package estadosYSentidos;

import trenes.EstacionRecorrido;
import trenes.Recorrido;

abstract public class Sentido {

	abstract public EstacionRecorrido primerEstacionRecorrido(Recorrido recorrido);

	public static Sentido getA() {
		return new SentidoA();
	}
	
	public static Sentido getB() {
		return new SentidoB();
	}

	abstract public EstacionRecorrido siguienteEstacion(EstacionRecorrido estacionP) ;

}
