package vista;

public class Pantalla extends Thread{	
	public static Pantalla instancia = null;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static Pantalla getPantalla(){
		if(instancia != null){
			return instancia;
		}
		else{
			instancia = new Pantalla();
			return instancia;
		}
	}
	
	public void run(){
		
	}
}
