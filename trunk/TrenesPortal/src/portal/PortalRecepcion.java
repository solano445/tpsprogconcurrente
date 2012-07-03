package portal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import trenes.EstacionConcreta;
import trenes.Pasajero;
import trenes.Recorrido;

public class PortalRecepcion extends Thread {
	//Variables
	public Integer puertoEntrada;
	public Recorrido recorrido;
	//Constructor
	public PortalRecepcion(Recorrido recorrido, Integer puertoEntrada) {
		this.recorrido=recorrido;
		this.puertoEntrada=puertoEntrada;
		
	}
	
	public void run(){
		try {
			ServerSocket sSocket= new ServerSocket(this.puertoEntrada);
			Socket socket= sSocket.accept();
			BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
			while(true){
				String nombre=in.readLine();
				String destino=in.readLine();
				EstacionConcreta estacionDestino = Recorrido.getEstacion(destino);
				new Pasajero(nombre, estacionDestino.getEstacioPortal(), estacionDestino).start();
				System.out.println("#333# A La Cabeza, 50 Centavos a La Matutina.");
				System.out.println("#Rubro 69# Se Recibe Pasajero: " + nombre + " Destino: " + destino);
			}
		} catch (IOException e) {e.printStackTrace();}
		
	}
}