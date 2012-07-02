package trenes;

public class TimerTren extends Thread {
	public Tren tren;
	public Boolean salioTren;
	
	public TimerTren(Tren tren) {
		this.tren=tren;
		this.salioTren = false;
	}
	
	@Override
	public void run() {
		//duerme
		try {TimerTren.sleep(this.tren.getEstacionActual().esperaEnMilisegundos);} catch (InterruptedException e) {e.printStackTrace();}
		//despierta
			this.tren.lockTrenEsperandoSalir.lock();
			if(!salioTren){				
				this.tren.trenEsperandoSalir.signal();
			}
			this.tren.lockTrenEsperandoSalir.unlock();
	}
}
