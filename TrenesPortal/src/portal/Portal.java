package portal;

import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import trenes.Pasajero;
import trenes.Recorrido;

public class Portal extends Thread {

	private static Portal portal;
	public Recorrido recorrido;
	public Integer puertoEntrada;
	public Integer puertoSalida;
	public Lock lock = new ReentrantLock(true);
	public Condition condicionSinPajeros = lock.newCondition();
	public List<Pasajero> pasajerosPortal;

	public Portal(Recorrido recorrido, Integer puertoEntrada, Integer puertoSalida) {
		// TODO
		this.recorrido = recorrido;
		this.puertoEntrada = puertoEntrada;
		this.puertoSalida = puertoSalida;
		this.pasajerosPortal = new LinkedList<Pasajero>();
	}

	public void run() {
		try {
			new PortalRecepcion(recorrido, puertoEntrada).start();
			Socket socket;
			while (true) {//MAGIAAAA
				try {
					socket= new Socket("127.0.0.1", this.puertoSalida);
					System.err.println("Cree Conexion en puerto: " + this.puertoSalida);
					break;
				} catch (Exception e) {
					System.err.println("Duermo Hasta que haya conexion");
					Thread.sleep(1000);
				}
			}
			PrintStream out = new PrintStream(socket.getOutputStream());
			while (true) {
				this.lock.lock();
				if (this.pasajerosPortal.isEmpty()) {
					this.condicionSinPajeros.await();
				}
				for (Pasajero pasajero : this.pasajerosPortal) {
					System.out.println("Se Envia El Pasajero: "	+ pasajero.nombre);
					
					out.println(pasajero.nombre);
					out.println(pasajero.estacionDestino.nombre);
				}
				pasajerosPortal.clear();
				this.lock.unlock();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setInstance(Portal portal) {
		Portal.portal = portal;
	}

	public static Portal getInstance() {
		return portal;
	}

	public void teletransportar(Pasajero pasajero) {
		this.lock.lock();
		this.pasajerosPortal.add(pasajero);
		this.condicionSinPajeros.signal();
		this.lock.unlock();
	}
}
