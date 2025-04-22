package eda.DS;

import java.nio.channels.Pipe;
import java.util.Iterator;

import eda.ADT.BST.arbol;;

public class ImplBST implements arbol{
	private Nodo raiz;
	
	public class BSTiterator implements Iterator<Nodo<E>>{
		private Nodo<E> current = raiz;
		private Pile<Nodo<E>> pila;;		
		
		while( current.izq != null) {
			pila.push(current);
			current = current.izq; 
		}
		
		
		

		@Override
		public boolean hasNext() {
			return current.next() != null;
		}

		@Override
		public Nodo<E> next() {
			Nodo<E> aux;
			pila.pop();
			return null;
		}
		
		
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
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
	public Nodo serarch(Comparable x) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
