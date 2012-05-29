package estadosYSentidos;

import trenes.Tren;
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
		this.tren.siguienteEstacion();
		this.tren.dormir(3000); //Duerme
	}

	@Override
	public EstadoTren siguienteEstado() {
		return this.tren.enMovimiento;
	}

}
