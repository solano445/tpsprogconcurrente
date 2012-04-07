

/*
 * Esta clase representa tanto la entidad tren como el 
 * Main ejecutable y la logica visual
 * Posiblemente solo se desprenda la clase "Estacion"
 * 
 */

public class Tren extends Thread{
	
	/*True = sentidoDerecha / False = sentidoIzquierda*/
	private boolean aLaDerecha;
	public Estacion[] estaciones;
	private int ultimaEstacion;
	
	Estacion estacionSiguiente(){
		if(aLaDerecha){
			ultimaEstacion = ((ultimaEstacion + 1) % estaciones.length);
		}
		else{
			ultimaEstacion = ((ultimaEstacion - 1) % estaciones.length);
		}
		return this.estaciones[ultimaEstacion];
	}
	
	public Tren(Boolean aLaDerechaTemp, Estacion[] estacionesTemp){
		super();
		this.aLaDerecha = aLaDerechaTemp;
		this.estaciones = estacionesTemp;
	}
	
	public void run(){
		
	}
	
	public static void main(String[] args) {		
		
		Estacion estaciones[] = new Estacion[5];
		estaciones[0] = new Estacion("Quilmes");
		estaciones[1] = new Estacion("Bernal");
		estaciones[2] = new Estacion("Temperley");
		estaciones[3] = new Estacion("Burzaco");
		estaciones[4] = new Estacion("Berazategui");
		
		int cantT = 10;

		Tren trenes[] = new Tren[cantT];
		for(int i=0; i<cantT; i++){
			boolean aladerecha = (i%2 == 1);
			trenes[i] = new Tren(aladerecha, estaciones);
		}
		
		System.out.println(" |Quilmes L|  <3= <4=  =1> =2]  |Bernal L| ");

	}
}
