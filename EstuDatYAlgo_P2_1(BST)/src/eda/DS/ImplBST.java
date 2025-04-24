package eda.DS;

import java.nio.channels.Pipe;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import eda.ADT.BST.arbol;
import eda.EXCEPTIONS.ArbolVacio;
import eda.EXCEPTIONS.ElementoDuplicado;

public class ImplBST<E extends Comparable<E>> implements arbol<E> {

	private Nodo<E> raiz;
	
	public ImplBST() {
		raiz = null;
	}
	@Override
	public Iterator<Nodo<E>> iterator() {
	    return new BSTiterator();
	}
	
	public class BSTiterator implements Iterator<Nodo<E>>{
		private Nodo<E> current = raiz;
		private Stack<Nodo<E>> pila = new Stack<>();		
		
		public BSTiterator() { //Constructor o inicalizador del iterador.
			while (current != null) {
		        pila.push(current);
		        current = current.izq;
		    }
		}

		public Nodo<E> getCurrent(){
			return current;
		}
		
		

		@Override
		public boolean hasNext() {
			return !pila.isEmpty();//Si la pila está vacia quiere decir que ya no hay más elementos iterables
		}

		@Override
		public Nodo<E> next() {
			if(hasNext()== false){
				throw new NoSuchElementException("NO HAY MÁS ELEMENTOS");
				//return null; Innecesario porque tenemos la excepción
			}
	        Nodo<E> nodo = pila.pop();
	        Nodo<E> temp = nodo.der;
	        
	        while (temp != null) {
	            pila.push(temp);
	            temp = temp.izq;
	        }
	        return nodo;
	    }
		
		
	}

	
	

	@Override
	public void add(E x) throws ElementoDuplicado{
		if (raiz == null) {
			raiz = new Nodo<E>(x);
		}else {
			raiz.add(x);
		}
	}

	@Override
	public Nodo<E> max() {
		return raiz.max();
	}

	@Override
	public Nodo<E> min() {
		return raiz.min();
	}

	@Override
	public void delete(E x)throws ArbolVacio {
		if (raiz == null) throw new ArbolVacio();
		raiz.search(x);//Vemos que ese elemento existe, sino excepción.
		Nodo<E> atc = raiz, padre = null;
		boolean f = false;
		//Lo buscamos ahora que sabemos que existe.
		while (f != true) {
			int com = x.compareTo(atc.getDato()
					);
			if (com == 0) {
				f = true;
			}else if (com > 0) {
				padre = atc;
				atc = atc.der;
			}else {
				padre = atc;
				atc = atc.izq;
			}
		}
		
		if(atc.der == null && atc.izq == null) {
			if( atc == raiz){
				raiz = null;
			}else if (padre.izq == atc){
				padre.izq = null;
			}else {
				padre.der = null;
			}
			
		}else if (atc.der == null || atc.izq == null){
			if( atc == raiz){
				if (raiz.izq != null) {
					raiz = raiz.izq;
				}else {
					raiz = raiz.der;
				}
			}else if(atc.izq != null) {
				atc = atc.izq;
			}else {
				atc = atc.der;
			}
		}else {//Tiene ambas ramas como ihos.Cogemos el minimo del derecho.
			Nodo<E> reemplazoPadre = atc;
		    Nodo<E> reemplazo = atc.der;

		    while (reemplazo.izq != null) {
		        reemplazoPadre = reemplazo;
		        reemplazo = reemplazo.izq;
		    }

		    atc.setDato(reemplazo.getDato()); // Copiamos el dato del reemplazo al nodo actual

		    // Eliminamos el nodo reemplazo ajustando el hijo izquierdo del padre del reemplazo
		    if (reemplazoPadre.izq == reemplazo) {
		        reemplazoPadre.izq  = reemplazo.der; // puede tener hijo derecho
		    } else {
		        reemplazoPadre.der = reemplazo.der;
		    }
		}
		}
		
		 
	

	@Override
	public Nodo<E> search(E x) throws NoSuchElementException {// pereza de cambiarlo por el método de nodos
	    return raiz.search(x);
	}


	
}
