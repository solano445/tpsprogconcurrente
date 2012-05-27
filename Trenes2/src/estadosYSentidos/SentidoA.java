package estadosYSentidos;

import trenes.*;

public class SentidoA extends Sentido  {

	@Override
	public EstacionRecorrido primerEstacionRecorrido(Recorrido recorrido) {
		return recorrido.priEstSenA;
	}

	@Override
	public EstacionRecorrido siguienteEstacion(EstacionRecorrido estacionP){
		return estacionP.sigEstRecSenA;
	}

}
