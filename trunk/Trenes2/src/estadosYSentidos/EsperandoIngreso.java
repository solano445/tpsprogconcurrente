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
		//Hacer Cambio
		this.pantalla.agregarCambio(new CambioEsperandoIngreso(this.tren));
		//otra cosa
				
		//##System.out.println(this.tren.toString() + " Entrando a " + this.tren.estActual.getNombre());
		this.tren.sentido.solicitarPermiso(this.tren.getEstacionActual() , this.tren);
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
		//##System.out.println(this.tren.toString() + " Entro en " + this.tren.estActual.getNombre());
	}

	@Override
	public EstadoTren siguienteEstado() {
		return this.tren.enEstacion;
	}

}
