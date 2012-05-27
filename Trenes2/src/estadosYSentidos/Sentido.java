package estadosYSentidos;

import trenes.EstacionConcreta;
import trenes.EstacionRecorrido;
import trenes.Recorrido;
import trenes.Tren;
import vista.VistaEstacion;

abstract public class Sentido {

	abstract public EstacionRecorrido primerEstacionRecorrido(Recorrido recorrido);
	abstract public EstacionRecorrido siguienteEstacion(EstacionRecorrido estacionP) ;

	public static Sentido getA() {
		return new SentidoA();
	}
	
	public static Sentido getB() {
		return new SentidoB();
	}
	abstract public void solicitarPermiso(EstacionConcreta estacionActual);
	abstract public void liberarPermiso(EstacionConcreta estacionActual);
	abstract public void crearVistaTrenMovimiento(VistaEstacion vistaEstacion, Tren tren);
	abstract public void removeFromEsperando(VistaEstacion vista, Tren tren);
	abstract public void crearEnMovimiento(VistaEstacion vista, Tren tren);
	abstract public void crearEsperando(VistaEstacion vista, Tren tren);
	abstract public void removeFromMovimiento(VistaEstacion vista, Tren tren);
	abstract public EstacionRecorrido estacionAnterior(EstacionRecorrido estacion);

}
