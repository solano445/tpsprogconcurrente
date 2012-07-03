package portal;

import trenes.Recorrido;

public class Portal extends Thread {
	
	
	public Recorrido recorrido;
	public Integer puertoEntrada;
	public Integer puertoSalida;

	public Portal(Recorrido recorrido, Integer puertoEntrada, Integer puertoSalida) {
		// TODO
		this.recorrido = recorrido;
		this.puertoEntrada = puertoEntrada;
		this.puertoSalida = puertoSalida;
		
	}

	public void run(){
		new PortalRecepcion(recorrido, puertoEntrada).start();
		
	}
}
