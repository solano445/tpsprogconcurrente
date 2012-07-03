package trenes;

import portal.Portal;

public class EstacionTeleporter extends EstacionConcreta {


	public EstacionTeleporter(String nombreP, Integer cantAndenesP,	Integer esperaEnMilisegundosP) {
		super(nombreP, cantAndenesP, esperaEnMilisegundosP);
	}

	/**
	 * Envia El pasajero a otro teleporter.
	 */
	public void teletransportar(Pasajero pasajero){
		pasajero.estacionDestino=pasajero.estacionDestinoTemporal;
		pasajero.teletransportar=false;
		Portal.getInstance().teletransportar(pasajero);
	}
	/**
	 * Retorna true porque es una estacion Teleporter.
	 */
	public boolean esEstacionPortal(){
		return true;
	}

}
