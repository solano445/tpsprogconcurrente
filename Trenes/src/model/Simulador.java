package model;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Clase para simular los recorridos
 * @author Tyno
 *
 */
public class Simulador extends Thread{
	
	private Estacion[] estaciones;
	private List<Tren> enAnden = new LinkedList<Tren>();

	public static void main(String[] args) {		
		
		Estacion[] estacionesT = new Estacion[5];
		estacionesT[0] = new Estacion("Quilmes");
		estacionesT[1] = new Estacion("Bernal");
		estacionesT[2] = new Estacion("Temperley");
		estacionesT[3] = new Estacion("Burzaco");
		estacionesT[4] = new Estacion("Berazategui");
		
		Simulador simuladorRoca = new Simulador();
		simuladorRoca.setEstaciones(estacionesT);
		
		int cantT = 10;

		Tren trenes[] = new Tren[cantT];
		for(int i=0; i<cantT; i++){
			boolean aladerecha = (i%2 == 1);
			trenes[i] = new Tren(String.valueOf(i), estacionesT, true, simuladorRoca);
			trenes[i].run();
		}
		
		while(true){
			simuladorRoca.imprimirRecorrido();
			try {
				Simulador.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
		//System.out.println(" |Quilmes L|  <3= <4=  =1> =2]  |Bernal L| ");

	}

	private void imprimirRecorrido() {
		int totalEstaciones = this.estaciones.length;
		String circuito = "";
		for(int i=0; i<totalEstaciones; i++){
			circuito += this.estaciones[i].toString();
		}
		System.out.println(circuito);
	}

	private void setEstaciones(Estacion[] estacionesT) {
		this.estaciones = estacionesT;		
	}
	
	/*public static void main(String[] args) {
		//Creo las listas de Recorridos y le agrego las estacion creadas.
		List<Estacion> recorridoNormal=new ArrayList<Estacion>();
		List<Estacion> recorridoInverso=new ArrayList<Estacion>();
		Estacion peniarol= new Estacion("Peniarol");
		Estacion berazategui= new Estacion("Berazategui");
		Estacion keleti= new Estacion("Keleti");
		recorridoNormal.add(berazategui);
		recorridoNormal.add(peniarol);
		recorridoNormal.add(keleti);
		recorridoInverso.add(keleti);
		recorridoInverso.add(peniarol);
		recorridoInverso.add(berazategui);
		
		
		//Creo Los trenes e inicio la simulacion.
		Tren belgrano = new Tren("Belgrano", recorridoNormal);
		Tren roca = new Tren("Roca", recorridoInverso);
		belgrano.start();
		roca.start();
	}*/
	
}
