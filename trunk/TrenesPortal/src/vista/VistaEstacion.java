package vista;

import java.util.LinkedList;
import java.util.List;

import trenes.EstacionConcreta;



public class VistaEstacion {
	
	//Variables
	public List<EstadoTemporal> enMovimientoLlendoHaciaEstacionSentidoA = new LinkedList<EstadoTemporal>();
	public List<EstadoTemporal> esperandoSentidoA = new LinkedList<EstadoTemporal>();
	public List<EstadoTemporal> enEstacion = new LinkedList<EstadoTemporal>();
	public List<EstadoTemporal> esperandoSentidoB = new LinkedList<EstadoTemporal>();
	public List<EstadoTemporal> enMovimientoLlendoHaciaEstacionSentidoB = new LinkedList<EstadoTemporal>();
	public Integer cantPasajeros = 0;
	public EstacionConcreta estacion;
	
	//Constructor
	public VistaEstacion(EstacionConcreta estacion) {
		this.estacion = estacion;
	}
	
	/**
	 * Devuelve el nombre de la estacion concreta que tiene.
	 */
	public String toString(){
		return this.estacion.nombre;
		
	}
	public void imprimir() {
		System.out.print(">> ");
		for (EstadoTemporal et : this.enMovimientoLlendoHaciaEstacionSentidoA) {
			System.out.print(et + " ");
		}
		System.out.print("\n|> ");
		for (EstadoTemporal et : this.esperandoSentidoA) {
			System.out.print(et + " ");
		}
		System.out.print("\n" + this.estacion.nombre +" <#"+ this.estacion.cantAndenes + "> (#" + this.cantPasajeros + ") ");
		//TODO
		if(this.enEstacion.size() > 2){
			System.out.print("#####");
		}
		
		for (EstadoTemporal et : this.enEstacion) {
			System.out.print(et + " ");
		}
		System.out.print("\n|< ");
		for (EstadoTemporal et : this.esperandoSentidoB) {
			System.out.print(et + " ");
		}
		System.out.print("\n<< ");
		for (EstadoTemporal et : this.enMovimientoLlendoHaciaEstacionSentidoB) {
			System.out.print(et + " ");
		}
		System.out.print("\n");
	}
	
	
}
