package trenes;

import java.util.List;

import vista.Pantalla;
import vista.VistaEstacion;
import estadosYSentidos.Sentido;

public class Recorrido{
	public EstacionRecorrido priEstSenA;
	public EstacionRecorrido priEstSenB; //es la ultima en el sentidoA
	public EstacionConcreta estacionPortal;
	public Integer cantidadEstaciones;
	public static EstacionConcreta[] estacionesA;
	public static EstacionConcreta[] estacionesB;
	
	public Recorrido(EstacionConcreta estacion){
		super();
		EstacionRecorrido nuevaEstacion = new EstacionRecorrido(estacion, this);
		this.priEstSenA = nuevaEstacion;
		this.priEstSenB = nuevaEstacion;
		this.cantidadEstaciones = 1;
		estacion.agregarEstacionRecorrido(nuevaEstacion);
	}
	
	public void agregarEstacion(EstacionConcreta estacion){
		EstacionRecorrido nuevaEstacion = new EstacionRecorrido(estacion, this);
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
	
	
	static public Recorrido getRecorridoA(){	
		estacionesA = new EstacionConcreta[4];

		estacionesA[0] = new EstacionConcreta("Bernal", 2, 400);		
		estacionesA[1] = new EstacionTeleporter("Quilmes", 2, 700);
		estacionesA[2] = new EstacionConcreta("Ezpeleta", 2, 500);
		estacionesA[3] = new EstacionConcreta("Berazategui", 2, 600);
		
		
		Recorrido recorrido = new Recorrido(estacionesA[0]);		
		recorrido.agregarEstacion(estacionesA[1]);
		recorrido.agregarEstacion(estacionesA[2]);
		recorrido.agregarEstacion(estacionesA[3]);
		crearVistas(recorrido);
		recorrido.estacionPortal = estacionesA[1];
		
		return recorrido;
	}
	
	static public Recorrido getRecorridoB(){
		estacionesB = new EstacionConcreta[4];

		estacionesB[0] = new EstacionConcreta("Retiro", 2, 1000);
		estacionesB[1] = new EstacionConcreta("3 de Febrero", 2, 700);
		estacionesB[2] = new EstacionConcreta("M. Carranza", 2, 500);
		estacionesB[3] = new EstacionTeleporter("Colegialas", 2, 600);
		
		
		Recorrido recorrido = new Recorrido(estacionesB[0]);		
		recorrido.agregarEstacion(estacionesB[1]);
		recorrido.agregarEstacion(estacionesB[2]);
		recorrido.agregarEstacion(estacionesB[3]);
		crearVistas(recorrido);
		recorrido.estacionPortal = estacionesB[3];
		
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

	/**
	 * Devuelve la estacion que tenga ese nombre
	 * @param nombre
	 * @return
	 */
	public static EstacionConcreta getEstacion(String nombre) {
		for (int i = 0; i < estacionesA.length; i++) {
			if(estacionesA[i].nombre.equals(nombre)){
				return estacionesA[i];
			}
		}
		for (int i = 0; i < estacionesB.length; i++) {
			if(estacionesB[i].nombre.equals(nombre)){
				return estacionesB[i];
			}
		}
		throw new RuntimeException("No Existe la estacion: " + nombre);
	}

}
