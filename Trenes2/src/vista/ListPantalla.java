package vista;

import java.util.LinkedList;
import java.util.List;

public class ListPantalla<E> extends LinkedList<E>{

	private static final long serialVersionUID = 1L;
	
	public List<E> listaComposite;

	public ListPantalla(){
		this.listaComposite = new LinkedList<E>();
	}
	
	public void add(Class<E> object){
		this.listaComposite.add((E) object);
	}
}
