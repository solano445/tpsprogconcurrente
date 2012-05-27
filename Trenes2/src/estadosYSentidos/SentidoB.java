package estadosYSentidos;

import trenes.*;

public class SentidoB extends Sentido {
	
	@Override
	public EstacionRecorrido primerEstacionRecorrido(Recorrido recorrido) {
		return recorrido.priEstSenB;
	}
	
	@Override
	public EstacionRecorrido siguienteEstacion(EstacionRecorrido estacionP){
		return estacionP.sigEstRecSenB;
	}
}
