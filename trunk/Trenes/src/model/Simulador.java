package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase para simular los recorridos
 * @author Tyno
 *
 */
public class Simulador {
	//Variables
	private Recorrido berazateguiAOnce;
	private Recorrido OnceABerazategui;
	private Recorrido bosquesAConstitucion;
	private Recorrido constitucionBosques;
	private Estacion berazategui;
	private Estacion quilmes;
	private Estacion constitucion;
	private Estacion bosques;
	
	//Constructor
	public Simulador(){
		this.crearEstaciones();
		this.crearRecorridoBerazateguiConstitucion();
		this.crearRecorridoConstitucionBerazategui();
		this.crearRecorridoBosquesConstitucion();
		this.crearRecorridoConstitucionBosques();
	}
	
	public void crearRecorridoConstitucionBerazategui(){
		List<Estacion> estaciones= new ArrayList<Estacion>();
		estaciones.add(this.constitucion);
		estaciones.add(this.quilmes);
		estaciones.add(this.berazategui);
		this.OnceABerazategui = new Recorrido("Constitucion A Berazategui" , estaciones);
	}
	public void crearRecorridoBerazateguiConstitucion(){
		List<Estacion> estaciones= new ArrayList<Estacion>();
		estaciones.add(this.berazategui);
		estaciones.add(this.quilmes);
		estaciones.add(this.constitucion);
		this.berazateguiAOnce = new Recorrido("Berazategui A Constitucion" , estaciones);
	}
	public void crearRecorridoBosquesConstitucion(){
		List<Estacion> estaciones= new ArrayList<Estacion>();
		estaciones.add(this.bosques);
		estaciones.add(this.berazategui);
		estaciones.add(this.quilmes);
		estaciones.add(this.constitucion);
		this.bosquesAConstitucion = new Recorrido("Bosques A Constitucion" , estaciones);
	}
	public void crearRecorridoConstitucionBosques(){
		List<Estacion> estaciones= new ArrayList<Estacion>();
		estaciones.add(this.constitucion);
		estaciones.add(this.quilmes);
		estaciones.add(this.berazategui);
		estaciones.add(this.bosques);
		this.constitucionBosques = new Recorrido("Constitucion A Bosques" , estaciones);
	}

	public void crearEstaciones() {
		berazategui = new Estacion("Berazategui" , 1);
		quilmes = new Estacion("Quilmes" , 1);
		constitucion = new Estacion("Constitucion", 1);
		bosques = new Estacion("Bosques", 1);
	}
	public  Recorrido recorridoTrenRoca(){
		return berazateguiAOnce;
	}
	public  Recorrido recorridoTrenBelgrano(){
		return OnceABerazategui;
	}
	public Recorrido recorridoTrenSarmiento(){
		return bosquesAConstitucion;
	}
	public static void main(String[] args) {
		//Creo El simulador para crear todo los objetos que necesito para probar el funcionamiento.
		Simulador simulador= new Simulador();
		
		List<Tren> trenes = new LinkedList<Tren>();
		
		//Creo Los trenes e inicio la simulacion.
		Tren belgrano = new Tren("Belgrano", simulador.recorridoTrenBelgrano() , 1);
		Tren roca = new Tren("Roca", simulador.recorridoTrenBelgrano() , 0);
		Tren sarmiento = new Tren("Sarmiento", simulador.recorridoTrenBelgrano() , 1);
		Tren sanMartin = new Tren("San Martin", simulador.recorridoTrenBelgrano() , 0);
		Tren rosas = new Tren("Rosas", simulador.recorridoTrenBelgrano() , 0);
		Tren mitre = new Tren("Mitre", simulador.recorridoTrenBelgrano() , 1);
		Tren jd = new Tren("Juan Domingo", simulador.recorridoTrenBelgrano() , 1);
		
		trenes.add(belgrano);
		trenes.add(roca);
		trenes.add(sarmiento);
		trenes.add(sanMartin);
		trenes.add(rosas);
		trenes.add(mitre);
		trenes.add(jd);
		
		
		simulador.iniciarTrenes(trenes);
		
		
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("cagadaaaa");
			
			simulador.congelarTrenes(trenes);

			String pantallazo = "";
			for(Estacion estacionT:simulador.recorridoTrenBelgrano().getEstaciones()){
				pantallazo += " (| " + estacionT + simulador.trenesEnEstacion(estacionT, trenes) + " |)" + simulador.trenesDerechaDeEstacion(estacionT, trenes);
			}
			
			System.out.println("\n" + pantallazo + "\n" );
			
			simulador.desCongelarTrenes(trenes);
			
		}
	}

	private String trenesDerechaDeEstacion(Estacion estacionT, List<Tren> trenes) {
		String res = "";
		for(Tren trenT:trenes){
			res += trenT.imprimirSiDerechaEstacion(estacionT);
		}
		return res;
	}

	private String trenesEnEstacion(Estacion estacionT, List<Tren> trenes) {
		String res = "";
		for(Tren trenT:trenes){
			res += trenT.imprimirSiEstanEnEstacion(estacionT);
		}
		return res;
	}

	private void iniciarTrenes(List<Tren> trenes) {
		for(Tren tren:trenes){
			tren.start();
		}		
	}
	
	private void congelarTrenes(List<Tren> trenes) {
		for(Tren tren:trenes){
			tren.waitP();
		}
	}
	

	
	private void desCongelarTrenes(List<Tren> trenes) {
		for(Tren tren:trenes){
			tren.signal();
		}
	}
	
}
