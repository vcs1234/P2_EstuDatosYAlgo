package eda.DS;

import java.nio.channels.Pipe;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

import eda.ADT.AVL.arbol;
import eda.EXCEPTIONS.ArbolVacio;
import eda.EXCEPTIONS.ElementoDuplicado;

public class ImplAVL<E extends Comparable<E>> implements arbol<E> {

	private Nodoa<E> raiz;
	
	public ImplAVL() {
		raiz = null;
	}
	@Override
	public Iterator<Nodoa<E>> iterator() {
	    return new BSTiterator();
	}
	
	public class BSTiterator implements Iterator<Nodoa<E>>{
		private Nodoa<E> current = raiz;
		private Stack<Nodoa<E>> pila = new Stack<>();		
		
		public BSTiterator() { //Constructor o inicalizador del iterador.
			while (current != null) {
		        pila.push(current);
		        current = current.izq;
		    }
		}

		@Override
		public boolean hasNext() {
			return !pila.isEmpty();//Si la pila está vacia quiere decir que ya no hay más elementos iterables
		}

		@Override
		public Nodoa<E> next() {
			if(hasNext()== false){
				throw new NoSuchElementException("NO HAY MÁS ELEMENTOS");
				//return null; Innecesario porque tenemos la excepción
			}
	        Nodoa<E> nodo = pila.pop();
	        Nodoa<E> temp = nodo.der;
	        
	        while (temp != null) {
	            pila.push(temp);
	            temp = temp.izq;
	        }
	        return nodo;
	    }
	}
	@Override
	public void add(E x) throws ElementoDuplicado, ArbolVacio{
		if (raiz == null) {
			raiz = new Nodoa<E>(x);
		}else {
			raiz.add(x);
			Balanceartodo();
		}
	}

	@Override
	public Nodoa<E> max() {
		return raiz.max();
	}

	@Override
	public Nodoa<E> min() {
		return raiz.min();
	}

	@Override
	public void delete(E x)throws ArbolVacio {
		if (raiz == null) throw new ArbolVacio();
		raiz.search(x);//Vemos que ese elemento existe, sino excepción.
		Nodoa<E> atc = raiz, padre = null;
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
			Nodoa<E> reemplazoPadre = atc;
		    Nodoa<E> reemplazo = atc.der;

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
		}Balanceartodo();
	}
	@Override
	public Nodoa<E> search(E x) throws NoSuchElementException {// pereza de cambiarlo por el método de nodos
	    return raiz.search(x);
	}

	public void Balanceartodo() throws ArbolVacio {
	    if (raiz == null) throw new ArbolVacio();
	    //Problema anterior, estaba en que se balanceaba el nodo pero este no se volvía a introducir al arbol.
	    raiz = raiz.balancear(); // ¡Primero asegúrate de reequilibrar y reasignar la raíz!
	    
	    Queue<Nodoa<E>> cola = new LinkedList<>();
	    cola.add(raiz);

	    while (!cola.isEmpty()) {
	        Nodoa<E> actual = cola.poll();

	        // Rebalanceamos hijos y los reasignamos al nodo actual
	        if (actual.izq != null) {
	            actual.izq = actual.izq.balancear();
	            cola.add(actual.izq);
	        }

	        if (actual.der != null) {
	            actual.der = actual.der.balancear();
	            cola.add(actual.der);
	        }
	    }
	}
	
	public void Mostrar() {
		Iterator<Nodoa<E>> iter = iterator();
		while (iter.hasNext()) {
			Nodoa<E> actual = iter.next(); // ¡Avanzamos al siguiente nodo!
			System.out.print(actual.getDato() + " ");
		}
		System.out.println(); // Salto de línea al final
	}


}
