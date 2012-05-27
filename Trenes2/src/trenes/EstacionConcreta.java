package trenes;

public class EstacionConcreta {
	
	public String nombre;
	public Integer cantAndenes;
	public Integer esperaEnMilisegundos;
	
	public EstacionConcreta(String nombreP , Integer cantAndenesP, Integer esperaEnMilisegundosP) {
		this.nombre = nombreP;
		this.cantAndenes=cantAndenesP;
		this.esperaEnMilisegundos=esperaEnMilisegundosP;
		
	}

}
