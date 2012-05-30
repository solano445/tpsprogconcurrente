package trenes;

import java.util.List;

import vista.Pantalla;
import vista.VistaEstacion;
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
		estacion.agregarEstacionRecorrido(nuevaEstacion);
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
			System.out.print("[" + estacionActual.estacionConcreta.nombre +  "]");	
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
		crearVistas(recorrido);
		
		return recorrido;
	}
	
	static void crearVistas(Recorrido recorrido){
		List<VistaEstacion> vistas = Pantalla.getInstance().vistaEstaciones;
		
		EstacionRecorrido estacionActual = recorrido.priEstSenA;
		while(estacionActual != recorrido.priEstSenB){
			estacionActual.estacionConcreta.vistaEstacion = new VistaEstacion(estacionActual.estacionConcreta);
			vistas.add(estacionActual.estacionConcreta.vistaEstacion);			
			estacionActual = estacionActual.sigEstRecSenA;
		}
		estacionActual.estacionConcreta.vistaEstacion = new VistaEstacion(estacionActual.estacionConcreta);
		vistas.add(estacionActual.estacionConcreta.vistaEstacion);		
	}
	
	static public void testearGetRecorrido(Recorrido recorrido){
		recorrido.printSentido(Sentido.getA());
		recorrido.printSentido(Sentido.getB());
		List<VistaEstacion> vistas = Pantalla.getInstance().vistaEstaciones;
		for (VistaEstacion vistaEstacion : vistas) {
			System.out.print("[" + vistaEstacion + "] ");
		}
	}

}
