import java.util.concurrent.Semaphore;

/*
 * Es en realidad un Semaphore, se les da rerencia desde una
 * coleccion para ordenarlas en el circuito
 */
public class Estacion extends Semaphore{

	private static final long serialVersionUID = 1L;
	
	private String idEstacion;
	
	public String getIdEstacion() {
		return idEstacion;
	}

	public Estacion(String idTemp) {
		super(1);  //solo puede haber un tren en cada estacion
		this.idEstacion = idTemp;
	}
	
	
	/*
	 * cambia el nombre de los metodos por los utilizados en clase
	 */
	public void whait(){
		super.acquireUninterruptibly();
	}
	
	/*
	 * cambia el nombre de los metodos por los utilizados en clase
	 */
	public void signal(){
		super.release();
	}
	
	

}
