package trenes;

import estadosYSentidos.Sentido;

public class Recorrido{
	public EstacionRecorrido priEstSenA;
	public EstacionRecorrido priEstSenB; //es la ultima en el sentidoA
	public Integer cantidadEstaciones;
	
	public Recorrido(EstacionConcreta estacion){
		super();
		EstacionRecorrido nuevaEstacion = new EstacionRecorrido(estacion);
		this.priEstSenA = nuevaEstacion;
		this.priEstSenB = nuevaEstacion;
		this.cantidadEstaciones = 1;
	}
	
	public void agregarEstacion(EstacionConcreta estacion){
		EstacionRecorrido nuevaEstacion = new EstacionRecorrido(estacion);
		this.priEstSenB.sigEstRecSenA = nuevaEstacion;
		this.priEstSenA.sigEstRecSenB = nuevaEstacion;
		nuevaEstacion.sigEstRecSenA = this.priEstSenA;
		nuevaEstacion.sigEstRecSenB = this.priEstSenB;
		this.priEstSenB = nuevaEstacion;
		this.cantidadEstaciones++;
	}
	
	
	
	public void printSentido(Sentido sentido){		
		EstacionRecorrido estacionActual = sentido.primerEstacionRecorrido(this);
		Integer contador = this.cantidadEstaciones*2;
		while(contador > 0){
			System.out.print("[" + estacionActual.estCncrt.nombre +  "]");	
			estacionActual = sentido.siguienteEstacion(estacionActual);
			contador--;
		}
		System.out.print("\n");
	}
	
	
	static public Recorrido getRecorrido(){
		Recorrido recorrido = new Recorrido(new EstacionConcreta("Bernal", 2, 400));
		recorrido.agregarEstacion(new EstacionConcreta("Quilmes", 2, 700));
		recorrido.agregarEstacion(new EstacionConcreta("Ezpeleta", 2, 500));
		recorrido.agregarEstacion(new EstacionConcreta("Berazategui", 2, 600));
		return recorrido;
	}
	
	static public void testearGetRecorrido(){
		Recorrido recorrido = getRecorrido();
		recorrido.printSentido(Sentido.getA());
		recorrido.printSentido(Sentido.getB());	
	}

}
