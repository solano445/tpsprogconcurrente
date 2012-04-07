import org.junit.Assert;
import org.junit.Test;

public class TrenTest {

	@Test
	public void testEstacionSguiente(){
		int cantEst = 100;
		Estacion estaciones[] = new Estacion[cantEst];		
		for(int i=0; i<cantEst; i++){
			estaciones[i] = new Estacion("Test");
		}		
		Tren trenTest = new Tren(true, estaciones);
		for(int i=1; i<cantEst; i++){
			Assert.assertEquals(trenTest.estacionSiguiente(), estaciones[i]);
		}
		for(int i=0; i<cantEst; i++){
			Assert.assertEquals(trenTest.estacionSiguiente(), estaciones[i]);
		}
	}
	
}
