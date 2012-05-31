package test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import trenes.EstacionConcreta;
import trenes.EstacionRecorrido;
import trenes.Recorrido;
import estadosYSentidos.Sentido;

	
public class TestEstacionRecorrido {
	    public Recorrido recorrido;
		public Sentido sentidoA;
		public Sentido sentidoB;
		public EstacionConcreta est1;
		public EstacionConcreta est2;
		public EstacionConcreta est3;
		public EstacionConcreta est4;
		public EstacionConcreta estFalsa;
	
	@Before
	public void setUp(){
		this.est1 = new EstacionConcreta("Bernal", 2, 400);		
		this.est2 = new EstacionConcreta("Quilmes", 2, 700);
		this.est3 = new EstacionConcreta("Ezpeleta", 2, 500);
		this.est4 = new EstacionConcreta("Berazategui", 2, 600);	
		this.estFalsa = new EstacionConcreta("FueraDeRecorrido", null, null);
		this.estFalsa.agregarEstacionRecorrido(new EstacionRecorrido(this.estFalsa));
		this.sentidoA = Sentido.getA();
		this.sentidoB = Sentido.getB();
		this.recorrido = new Recorrido(est1);		
		this.recorrido.agregarEstacion(est2);
		this.recorrido.agregarEstacion(est3);
		this.recorrido.agregarEstacion(est4);
		Recorrido.crearVistas(this.recorrido);		
	}
	
	@Test
	public void testCantidadEstaciones(){
		assertTrue(this.recorrido.cantidadEstaciones == 4);
	}
	
	@Test
	public void testDistanciaEstaciones(){
		assertTrue(this.est1.estacionRecorrido.cantidadEstacionesSentido(this.est3, this.sentidoA) == 2);
		assertTrue(this.est1.estacionRecorrido.cantidadEstacionesSentido(this.est3, this.sentidoB) == 2);
		assertTrue(this.est1.estacionRecorrido.cantidadEstacionesSentido(this.est4, this.sentidoB) == 1);
	}
	
	@Test
	public void testDistanciaEstacionesExceptions(){
		boolean esMismaEstacion = false;
		try {
			this.est1.estacionRecorrido.cantidadEstacionesSentido(this.est1, this.sentidoB);
		} catch (RuntimeException e) {
			esMismaEstacion = true;
		}	
		assertTrue(esMismaEstacion);
		
		boolean noExisteEstacion = false;		
		try {
			this.est1.estacionRecorrido.cantidadEstacionesSentido(this.estFalsa, this.sentidoB);
		} catch (RuntimeException e) {
			noExisteEstacion = true;
		}	
		assertTrue(noExisteEstacion);		
	}
}
