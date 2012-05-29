package estadosYSentidos;

import trenes.*;
import vista.Cambio;
import vista.CambioEnEstacion;

public class EnEstacion extends EstadoTren {

	public EnEstacion(Tren tren) {
		super(tren);
	}

	@Override
	public void run() {
		//Hacer Cambio
		this.pantalla.agregarCambio(new CambioEnEstacion(this.tren));
		//otra cosa
		//##System.out.println(this.tren.toString() + " saliendo de " + this.tren.estActual.getNombre());
		this.tren.sentido.liberarPermiso(this.tren.getEstacionActual());
		this.tren.dormir(3000); //Duerme
		this.tren.siguienteEstacion();
	}

	@Override
	public EstadoTren siguienteEstado() {
		return this.tren.enMovimiento;
	}

}
