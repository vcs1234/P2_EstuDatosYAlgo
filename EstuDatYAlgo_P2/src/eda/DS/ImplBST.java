package eda.DS;

import java.nio.channels.Pipe;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import eda.ADT.BST.arbol;

public class ImplBST<E extends Comparable<E>> implements arbol<E> {

	private Nodo<E> raiz;
	
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
			return next() != null;
		}

		@Override
		public Nodo<E> next() {
	        Nodo<E> nodo = pila.pop();
	        Nodo<E> temp = nodo.der;
	        if(hasNext()== false){
				
				throw new NoSuchElementException("NO HAY MÁS ELEMENTOS");
				//return null; Innecesario porque tenemos la excepción
			}
	        while (temp != null) {
	            pila.push(temp);
	            temp = temp.izq;
	        }
	        return nodo;
	    }
		
		
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
	public Nodo<E> search(E x) throws NoSuchElementException {
	    Nodo<E> aux = raiz;
	    while (aux != null) {
	        int cmp = aux.dato.compareTo(x);
	        if (cmp == 0) {
	            return aux; // encontrado
	        } else if (cmp > 0) {
	            aux = aux.izq; // x es menor → ir a la izquierda
	        } else {
	            aux = aux.der; // x es mayor → ir a la derecha
	        }
	    }
	    throw new NoSuchElementException("Elemento no encontrado");
	}


	
}
