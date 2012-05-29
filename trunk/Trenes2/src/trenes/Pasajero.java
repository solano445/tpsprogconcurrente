package trenes;

public class Pasajero extends Thread {

	//Variables
	public String nombre;
	public EstacionConcreta aDondeVa; //Esta es la estacion hacia la que se dirije
	
	public Pasajero(String nombre , EstacionConcreta aDondeVa) {
		this.nombre=nombre;
		this.aDondeVa=aDondeVa;
	}
	
		public void run() {
			//Viajo y Muero
			//Los muchachos peronistas, todos unidos triunfaremos y...
		}
}
