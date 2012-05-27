package trenes;

public class EstacionRecorrido {
	
	public EstacionConcreta estacionConcreta;
	public EstacionRecorrido sigEstRecSenA;
	public EstacionRecorrido sigEstRecSenB;
	
	public EstacionRecorrido(EstacionConcreta estacion){
		this.estacionConcreta = estacion;
	}
	
	public String getNombre(){
		return this.estacionConcreta.nombre;
	}
}
