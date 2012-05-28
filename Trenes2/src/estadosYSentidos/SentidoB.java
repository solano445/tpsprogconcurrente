package estadosYSentidos;

import trenes.*;
import vista.EstadoTemporal;
import vista.Pantalla;
import vista.VistaEstacion;

public class SentidoB extends Sentido {
	
	@Override
	public EstacionRecorrido primerEstacionRecorrido(Recorrido recorrido) {
		return recorrido.priEstSenB;
	}
	
	@Override
	public EstacionRecorrido siguienteEstacion(EstacionRecorrido estacionP){
		return estacionP.sigEstRecSenB;
	}
	
	@Override
	public void solicitarPermiso(EstacionConcreta estacionActual) {
		estacionActual.pedirPermisoIngresoSentidoB();
	}
	@Override
	public void liberarPermiso(EstacionConcreta estacionActual) {
		estacionActual.liberarPermisoIngresoSentidoB();
		
	}

	@Override
	public void crearVistaTrenMovimiento(VistaEstacion vistaEstacion, Tren tren) {
		vistaEstacion.enMovimientoLlendoHaciaEstacionSentidoB.add(new EstadoTemporal(tren));
	}

	@Override
	public void removeFromEsperando(VistaEstacion vista, Tren tren) {
		vista.esperandoSentidoB.remove(Pantalla.devolverEstado(vista.esperandoSentidoB, tren));
	}

	
	@Override
	public void crearEsperando(VistaEstacion vista, Tren tren) {
		vista.esperandoSentidoB.add(new EstadoTemporal(tren));
		
	}

	@Override
	public void removeFromMovimiento(VistaEstacion vista, Tren tren) {
		EstadoTemporal estadoTemporal = Pantalla.devolverEstado(vista.enMovimientoLlendoHaciaEstacionSentidoB, tren, vista.estacion.nombre);
		vista.enMovimientoLlendoHaciaEstacionSentidoB.remove(estadoTemporal);
	}
	
	@Override
	public EstacionRecorrido estacionAnterior(EstacionRecorrido estacion) {
		return estacion.sigEstRecSenA;
	}	

	@Override
	public String trenToString(Tren tren) {
		return "[<<" + tren.nombre + "]";		
	}
}
