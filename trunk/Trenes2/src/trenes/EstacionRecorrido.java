package trenes;

public class EstacionRecorrido {
	
	public EstacionConcreta estCncrt;
	public EstacionRecorrido sigEstRecSenA;
	public EstacionRecorrido sigEstRecSenB;
	
	public EstacionRecorrido(EstacionConcreta estacion){
		this.estCncrt = estacion;
	}
	
	public String getNombre(){
		return this.estCncrt.nombre;
	}
}
