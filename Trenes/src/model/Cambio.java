package model;


public class Cambio {
	public Tren tren;
	public EstadoTrenEnum nuevoEstado;
	public Estacion estacionSiguiente;
	public Estacion estacionAnterior;
	public Boolean hayCambioDeEstacion;

	public Cambio(Tren tren, EstadoTrenEnum nuevoEstado, Estacion estacionAnterior, Estacion estacionSiguiente, Boolean hayCambioDeEstacion) {
		this.tren = tren;
		this.nuevoEstado = nuevoEstado;
		this.estacionSiguiente = estacionSiguiente;
		this.estacionAnterior = estacionAnterior;
		this.hayCambioDeEstacion=hayCambioDeEstacion;
	}
	

}

