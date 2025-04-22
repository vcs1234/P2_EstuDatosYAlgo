package eda.DS;

import java.nio.channels.Pipe;
import java.util.Iterator;
import java.util.Stack;

import eda.ADT.BST.arbol;
import eda.EXCEPTIONS.ElementoNoEncontrado;;

public class ImplBST<E extends Comparable<E>> implements arbol<E> {

	private Nodo raiz;
	
	public class BSTiterator implements Iterator<Nodo<E>>{
		private Nodo<E> current = raiz;
		private Stack<Nodo<E>> pila = new Stack<>();		
		
		public BSTiterator() { //Constructor o inicalizador del iterador.
			while (current != null) {
		        pila.push(current);
		        current = current.izq;
		    }
		}

		
		
		

		@Override
		public boolean hasNext() {
			return next() != null;
		}

		@Override
		public Nodo<E> next() {
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
	public Iterator<Nodo<E>> iterator() {
	    return new BSTiterator();
	}

	@Override
	public void add(Comparable x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Nodo max() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Nodo min() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Comparable x) {
		
	}

	@Override
	public Nodo<E> search(E x) throws ElementoNoEncontrado {
		// TODO Auto-generated method stub
		return null;
	}

	
}
