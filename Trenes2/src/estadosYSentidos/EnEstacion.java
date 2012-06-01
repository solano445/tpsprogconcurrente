package estadosYSentidos;

import trenes.Tren;
import vista.CambioEnEstacion;

public class EnEstacion extends EstadoTren {

	public EnEstacion(Tren tren) {
		super(tren);
	}

	@Override
	public void run() {
		//Hacer Cambio enEstacion
		this.pantalla.agregarCambio(new CambioEnEstacion(this.tren));
		
		
		
		//se setea la sigioente estacion
		this.tren.siguienteEstacion();
		this.tren.dormir(3000); //Duerme
		//en el siguiente estado se libera el anden y se guarda el cambio en la coleccion de la pantalla
	}

	@Override
	public EstadoTren siguienteEstado() {
		return this.tren.enMovimiento;
	}

}
