package estadosYSentidos;

import java.util.LinkedList;
import java.util.List;

import trenes.*;
import vista.CambioEsperandoIngreso;

public class EsperandoIngreso extends EstadoTren {

	public EsperandoIngreso(Tren tren) {
		super(tren);
	}

	@Override
	public void run() {
		//Hacer Cambio esperando ingreso
		this.pantalla.agregarCambio(new CambioEsperandoIngreso(this.tren));
				
		this.tren.sentido.solicitarPermiso(this.tren.getEstacionActual() , this.tren);
		
		//nuevo
		this.tren.lockTrenViaje.lock();
		List<Pasajero> pasajerosQueSeBajaron= new LinkedList<Pasajero>();
		for (Pasajero pasajero : this.tren.pasajerosABordo) {
			if(pasajero.estacionDestino.equals(this.tren.getEstacionActual())){
				pasajero.llegueAdestino = true;
				this.tren.cantPasajerosAbordo--;
				pasajerosQueSeBajaron.add(pasajero);
				
			}
			
		}
		this.tren.pasajerosABordo.removeAll(pasajerosQueSeBajaron);
		this.tren.pasajerosViajando.signalAll();
		this.tren.lockTrenViaje.unlock();
		//nuevo
	}

	@Override
	public EstadoTren siguienteEstado() {
		return this.tren.enEstacion;
	}

}
