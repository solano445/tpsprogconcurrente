package trenes;

import java.util.List;

import vista.Pantalla;
import vista.VistaEstacion;
import estadosYSentidos.Sentido;

public class Recorrido{
	public EstacionRecorrido priEstSenA;
	public EstacionRecorrido priEstSenB; //es la ultima en el sentidoA
	public Integer cantidadEstaciones;
	public static EstacionConcreta[] estaciones;
	
	public Recorrido(EstacionConcreta estacion){
		super();
		EstacionRecorrido nuevaEstacion = new EstacionRecorrido(estacion);
		this.priEstSenA = nuevaEstacion;
		this.priEstSenB = nuevaEstacion;
		this.cantidadEstaciones = 1;
		estacion.agregarEstacionRecorrido(nuevaEstacion);
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
		estaciones = new EstacionConcreta[4];

		estaciones[0] = new EstacionConcreta("Bernal", 2, 400);		
		estaciones[1] = new EstacionConcreta("Quilmes", 2, 700);
		estaciones[2] = new EstacionConcreta("Ezpeleta", 2, 500);
		estaciones[3] = new EstacionConcreta("Berazategui", 2, 600);
		
		
		Recorrido recorrido = new Recorrido(estaciones[0]);		
		recorrido.agregarEstacion(estaciones[1]);
		recorrido.agregarEstacion(estaciones[2]);
		recorrido.agregarEstacion(estaciones[3]);
		crearVistas(recorrido);
		
		return recorrido;
	}
	
	public static void crearVistas(Recorrido recorrido){
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
