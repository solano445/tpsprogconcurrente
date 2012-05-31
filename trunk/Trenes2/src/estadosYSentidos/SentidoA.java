package estadosYSentidos;

import trenes.*;
import vista.EstadoTemporal;
import vista.Pantalla;
import vista.VistaEstacion;

public class SentidoA extends Sentido  {

	@Override
	public EstacionRecorrido primerEstacionRecorrido(Recorrido recorrido) {
		return recorrido.priEstSenA;
	}

	@Override
	public EstacionRecorrido siguienteEstacion(EstacionRecorrido estacionP){
		return estacionP.sigEstRecSenA;
	}

	@Override
	public void solicitarPermiso(EstacionConcreta estacionActual, Tren tren) {
		estacionActual.pedirPermisoIngresoSentidoA(tren);
	}

	@Override
	public void liberarPermiso(EstacionConcreta estacionActual, Tren tren) {
		estacionActual.liberarPermisoIngresoSentidoA(tren);
		
	}

	@Override
	public void crearVistaTrenMovimiento(VistaEstacion vistaEstacion, Tren tren) {
		vistaEstacion.enMovimientoLlendoHaciaEstacionSentidoA.add(new EstadoTemporal(tren));
	}

	@Override
	public void removeFromEsperando(VistaEstacion vista, Tren tren) {
		vista.esperandoSentidoA.remove(Pantalla.devolverEstado(vista.esperandoSentidoA, tren));
	}


	@Override
	public void crearEsperando(VistaEstacion vista, Tren tren) {
		vista.esperandoSentidoA.add(new EstadoTemporal(tren));		
	}

	@Override
	public void removeFromMovimiento(VistaEstacion vista, Tren tren) {
		vista.enMovimientoLlendoHaciaEstacionSentidoA.remove(Pantalla.devolverEstado(vista.enMovimientoLlendoHaciaEstacionSentidoA, tren, vista.estacion.nombre));
	}

	@Override
	public EstacionRecorrido estacionAnterior(EstacionRecorrido estacion) {
		return estacion.sigEstRecSenB;
	}

	@Override
	public String trenToString(Tren tren) {
		return "[" + tren.nombre + "(#" + tren.cantPasajerosAbordo +"/"+ tren.cantPasajerosMax +")" + ">>]";		
	}


}
