package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Impresor.EstacionI;
import model.Impresor.TrenEstado;

/**
 * Clase para simular los recorridos
 * @author Tyno
 *
 */
public class Simulador {
	//Variables
	private Recorrido bosquesAConstitucion;
	private Recorrido constitucionBosques;
	private Estacion berazategui;
	private Estacion quilmes;
	private Estacion constitucion;
	private Estacion bosques;
	
	//Constructor
	public Simulador(){
		this.crearEstaciones();
		this.crearRecorridoConstitucionBosques();
		this.crearRecorridoBosquesConstitucion();
	}
	public void crearRecorridoConstitucionBosques(){
		List<Estacion> estaciones= new ArrayList<Estacion>();
		List<EstacionI> estacionesImpresor = Impresor.get().estaciones;
		
		estaciones.add(this.constitucion);
		estacionesImpresor.add(Impresor.get().new EstacionI(this.constitucion));
		estaciones.add(this.quilmes);
		estacionesImpresor.add(Impresor.get().new EstacionI(this.quilmes));
		estaciones.add(this.berazategui);
		estacionesImpresor.add(Impresor.get().new EstacionI(this.berazategui));
		estaciones.add(this.bosques);
		estacionesImpresor.add(Impresor.get().new EstacionI(this.bosques));
		this.constitucionBosques = new Recorrido("Constitucion A Bosques" , estaciones);
	}
	public void crearRecorridoBosquesConstitucion(){
		List<Estacion> estaciones= new ArrayList<Estacion>();
		
		estaciones.add(this.bosques);
		estaciones.add(this.berazategui);
		estaciones.add(this.quilmes);
		estaciones.add(this.constitucion);
		this.constitucionBosques = new Recorrido("Bosques A Constitucion" , estaciones);
	}

	public void crearEstaciones() {
		berazategui = new Estacion("Berazategui" , 1);
		quilmes = new Estacion("Quilmes" , 1);
		constitucion = new Estacion("Constitucion", 1);
		bosques = new Estacion("Bosques", 1);
	}
	public  Recorrido recorrido(){
		return constitucionBosques;
	}
	public  Recorrido recorridoInverso(){
		return bosquesAConstitucion;
	}
	public static void main(String[] args) {
		//Creo El simulador para crear todo los objetos que necesito para probar el funcionamiento.
		Simulador simulador= new Simulador();
		
		List<Tren> trenes = new LinkedList<Tren>();
		
		//Creo Los trenes e inicio la simulacion.
		Tren belgrano = new Tren("Belgrano", simulador.recorrido() , 1);
		Impresor.get().estaciones.get(0).enMovimientoLlendoHaciaEstacionAnden1.add(Impresor.get().new TrenEstado(belgrano, EstadoTrenEnum.Detenido));
		Tren roca = new Tren("Roca", simulador.recorrido() , 0);
		Impresor.get().estaciones.get(Impresor.get().estaciones.size()-1).enMovimientoLlendoHaciaEstacionAnden0.add(Impresor.get().new TrenEstado(roca, EstadoTrenEnum.Detenido));
		Tren sarmiento = new Tren("Sarmiento", simulador.recorrido() , 1);
		Impresor.get().estaciones.get(0).enMovimientoLlendoHaciaEstacionAnden1.add(Impresor.get().new TrenEstado(sarmiento, EstadoTrenEnum.Detenido));
		Tren sanMartin = new Tren("San Martin", simulador.recorrido() , 0);
		Impresor.get().estaciones.get(Impresor.get().estaciones.size()-1).enMovimientoLlendoHaciaEstacionAnden0.add(Impresor.get().new TrenEstado(sanMartin, EstadoTrenEnum.Detenido));
		Tren rosas = new Tren("Rosas", simulador.recorrido() , 0);
		Impresor.get().estaciones.get(Impresor.get().estaciones.size()-1).enMovimientoLlendoHaciaEstacionAnden0.add(Impresor.get().new TrenEstado(rosas, EstadoTrenEnum.Detenido));
		Tren mitre = new Tren("Mitre", simulador.recorrido() , 1);
		Impresor.get().estaciones.get(0).enMovimientoLlendoHaciaEstacionAnden1.add(Impresor.get().new TrenEstado(mitre, EstadoTrenEnum.Detenido));
		Tren jd = new Tren("Juan Domingo", simulador.recorrido() , 1);
		Impresor.get().estaciones.get(0).enMovimientoLlendoHaciaEstacionAnden1.add(Impresor.get().new TrenEstado(jd, EstadoTrenEnum.Detenido));
		
		trenes.add(belgrano);
		trenes.add(roca);
		trenes.add(sarmiento);
		trenes.add(sanMartin);
		trenes.add(rosas);
		trenes.add(mitre);
		trenes.add(jd);
		
		Impresor.get().start();
		for(Tren tren:trenes){
			tren.start();
		}
		
	}
	

}
