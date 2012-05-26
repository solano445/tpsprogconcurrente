package model;

import java.lang.Thread;
import java.util.LinkedList;
import java.util.List;

public class Impresor extends Thread {

	List<EstacionI> estaciones = new LinkedList<EstacionI>();
	List<Cambio> cambios = new LinkedList<Cambio>();
	
	public static Impresor instancia = null;
	
	public static Impresor get(){
		if(instancia != null){
			return instancia;
		}
		else{
			instancia = new Impresor();
			return instancia;
		}
	}

	// Lock lockImpresor = new ReentrantLock();
	// Condition noHayCambios = lockImpresor.newCondition();

	public synchronized void agregarCambio(Cambio cambio) {
		this.cambios.add(cambio);
		this.notify();
	}

	public void run() {
		synchronized(this){
			
			for (EstacionI estacion : estaciones) {
				estacion.imprimirme();// trenes antes anden 0
				System.out.println("");
	
			}
		}
		while (true) {
			this.imprimirCambios();
		}
	}

	public synchronized void imprimirCambios() {
		if (cambios.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			for (Cambio cambio : this.cambios) {
				// aplicarprimercambio
				EstacionI estacionSiguiente = this.estacionIFrom(cambio.estacionSiguiente);
				TrenEstado trenEstado = estacionSiguiente.trenEstadoFrom(cambio.tren);
				trenEstado.estadoTemporal = cambio.nuevoEstado;
				if (cambio.hayCambioDeEstacion) {
					EstacionI estacionAnterior = this.estacionIFrom(cambio.estacionAnterior);
					estacionAnterior.removerTrenEstado(trenEstado);
					estacionSiguiente.agregarTrenEstado(trenEstado);
				} 
				//falta contemplar la diferencia de pasajeros

				for (EstacionI estacion : estaciones) {
					estacion.imprimirme();// trenes antes anden 0
					System.out.println("");

				}
			}
			this.cambios.clear();
		}
	}

	public EstacionI estacionIFrom(Estacion estacion) {
		for (EstacionI estacioni : this.estaciones) {
			if (estacioni.estacion.equals(estacion)) {
				return estacioni;
			}
		}
		throw new RuntimeException("La Estacion No Existe Wacho forro" + estacion);
	}

	public class EstacionI {
		public List<TrenEstado> enMovimientoLlendoHaciaEstacionAnden0 = new LinkedList<TrenEstado>();
		public List<TrenEstado> esperandoAnden0 = new LinkedList<TrenEstado>();
		public List<TrenEstado> enEstacion = new LinkedList<TrenEstado>();
		public List<TrenEstado> esperandoAnden1 = new LinkedList<TrenEstado>();
		public List<TrenEstado> enMovimientoLlendoHaciaEstacionAnden1 = new LinkedList<TrenEstado>();
		public Integer cantPasajeros = 0;
		public Estacion estacion;

		public EstacionI(Estacion estacion) {
			this.estacion = estacion;
		}
		public void agregarTrenEstado(TrenEstado trenEstado) {
			Integer anden= trenEstado.tren.getAnden();
			EstadoTrenEnum estadoTemporal=trenEstado.estadoTemporal;
			if(estadoTemporal.equals(EstadoTrenEnum.DetenidoEnEstacion)){
				this.enEstacion.add(trenEstado);
			}
			else if(estadoTemporal.equals(EstadoTrenEnum.EsperandoIngreso)){
				if(anden==0){
					this.esperandoAnden0.add(trenEstado);
				}
				else{
					this.esperandoAnden1.add(trenEstado);
				}
			}
			else if(estadoTemporal.equals(EstadoTrenEnum.EnMovimiento)){
				if(anden==0){
					this.enMovimientoLlendoHaciaEstacionAnden0.add(trenEstado);
				}
				else{
					this.enMovimientoLlendoHaciaEstacionAnden1.add(trenEstado);
				}
			}
		}
		/**
		 * Siempre va a existir el tren que quiero borrar.
		 * @param TrenEstado trenEstado
		 */
		public void removerTrenEstado(TrenEstado trenEstado) {
			if(this.enMovimientoLlendoHaciaEstacionAnden0.contains(trenEstado)){
				this.enMovimientoLlendoHaciaEstacionAnden0.remove(trenEstado);
			}
			else if(this.esperandoAnden0.contains(trenEstado)){
				this.esperandoAnden0.remove(trenEstado);
			}
			else if(this.enEstacion.contains(trenEstado)){
				this.enEstacion.remove(trenEstado);
			}
			else if(this.esperandoAnden1.contains(trenEstado)){
				this.esperandoAnden1.remove(trenEstado);
			}
			else if(this.enMovimientoLlendoHaciaEstacionAnden1.contains(trenEstado)){
				this.enMovimientoLlendoHaciaEstacionAnden1.remove(trenEstado);
			}
			throw new RuntimeException("No Existe el tren en ninguna coleccion" + trenEstado);//Esto No Va a pasar Nunca
		}
		/**
		 * No Mirar Esto
		 * @author Alejandro
		 * @param Tren tren
		 * @return Tren tren
		 */
		public TrenEstado trenEstadoFrom(Tren tren) {
			if(this.contains(enMovimientoLlendoHaciaEstacionAnden0, tren)){
				return detect(enMovimientoLlendoHaciaEstacionAnden0, tren);
			}
			else if(this.contains(esperandoAnden0, tren)){
				return detect(esperandoAnden0, tren);
			}
			else if(this.contains(enEstacion, tren)){
				return detect(enEstacion, tren);
			}
			else if(this.contains(esperandoAnden1, tren)){
				return detect(esperandoAnden1, tren);
			}
			else if(this.contains(enMovimientoLlendoHaciaEstacionAnden1, tren)){
				return detect(enMovimientoLlendoHaciaEstacionAnden1, tren);
			}
			throw new RuntimeException("No Existe el tren en ninguna coleccion" + tren);//Esto No Va a pasar Nunca
		}

		public Boolean contains(List<TrenEstado> coleccion , Tren tren){
			for (TrenEstado te : coleccion) {
				if (te.tren.equals(tren)) {
					return true;
				}
			}
			return false;
		}
		public TrenEstado detect(List<TrenEstado> coleccion , Tren tren){
			for (TrenEstado te : coleccion) {
				if (te.tren.equals(tren)) {
					return te;
				}
			}
			throw new RuntimeException("Se Pidio un tren estado que no existe");//Esto no va suceder nunca
		}

		/**
		 * Esto Imprime algo diabolico.
		 */
		public void imprimirme() {
			System.out.print(">> ");
			for (TrenEstado te : enMovimientoLlendoHaciaEstacionAnden0) {
				System.out.print(te + " ");
			}
			System.out.print("\n |> ");
			for (TrenEstado te : esperandoAnden0) {
				System.out.print(te + " ");
			}
			System.out.print("\n " + this.estacion.nombre + " <#"
					+ this.estacion.cantAndenes + "> (#" + this.cantPasajeros
					+ ") ");
			for (TrenEstado te : enEstacion) {
				System.out.print(te + " ");
			}
			System.out.print("\n << ");
			for (TrenEstado te : enMovimientoLlendoHaciaEstacionAnden1) {
				System.out.print(te + " ");
			}
			System.out.print("\n |< ");
			for (TrenEstado te : esperandoAnden1) {
				System.out.print(te + " ");
			}
		}
	}

	/**
	 * >> [Tren 1] [Tren 1] [Tren 1] [Tren 1] |> [Tren 1] [Tren 1] [Tren 1]
	 * [Tren 1] <# NombreEstacion [Tren 1] [Tren 1] [Tren 1] [Tren 1]
	 * 
	 * 
	 */

	// enMovimientoAntesAnden1> - <enMovimientoAntesAnden0 - esperandoAnden0> -
	// enEstacion -
	// <esperandoAnden1
	/**
	 * responde al mismo protocolo que la clase Tren con al particularidad que
	 * guarda un estado, cantidad pasajeros por ejemplo
	 */
	public class TrenEstado {
		public EstadoTrenEnum estadoTemporal;
		public Tren tren;
		public Integer pasajerosTemporal;

		public TrenEstado(Tren trenT , EstadoTrenEnum estadoTemporal ) {
			this.tren = trenT;
			this.estadoTemporal=estadoTemporal;
		}

		public String numero() {
			return this.tren.numero;
		}
		public String toString(){
			return this.tren.toString();
		}
	}
	
	

}
