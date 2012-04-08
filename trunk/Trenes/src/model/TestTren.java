package model;

import org.junit.Assert;
import org.junit.Test;


public class TestTren{
	
	@Test
	public void testTren(){
		Estacion[] estacionesT = new Estacion[5];
		estacionesT[0] = new Estacion("Quilmes");
		estacionesT[1] = new Estacion("Bernal");
		estacionesT[2] = new Estacion("Temperley");
		estacionesT[3] = new Estacion("Burzaco");
		estacionesT[4] = new Estacion("Berazategui");
		
		Simulador simuladorRoca = new Simulador();
		simuladorRoca.setEstaciones(estacionesT);
		
		Tren tren = new Tren("1", estacionesT, false, simuladorRoca);
		tren.run();

		Assert.assertTrue(true);
	}

}
