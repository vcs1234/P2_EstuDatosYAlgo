package eda.DS;

import java.util.NoSuchElementException;

import eda.EXCEPTIONS.ElementoDuplicado;

public class Nodoa<E extends Comparable<E>> {
	private E dato;
	
	
	
	public E getDato() {
		return dato;
	}

	public void setDato(E dato) {
		this.dato = dato;
	}

	public Nodoa<E> izq, der;
	public Nodoa (E x) {
		this.dato = x;
		izq = null;
		der = null;
	}
	
	public boolean equals(Object obj) {
        if (this == obj) return true;                    // misma referencia
        if (!(obj instanceof Nodoa)) return false;        // verifica instancia
        Nodoa<?> o = (Nodoa<?>) obj;                       // casting seguro
        return dato != null && dato.equals(o.dato);      // comparación de datos
    }
	//No ponemos instanceof Nodo<E> Porque java en la comprobación no ve genéricos.
	
	//Implementamos aquí los métodos de los arboles, para poder ejercer las operaciones con más facilidades:
	//Facilidades: Mayor recursividad, Menos acoplameniento y operaciones centradas en los objetos.
	
	public void add(E x)throws ElementoDuplicado {
		Nodoa<E> aux = this;
		while(aux != null) {
			int com = x.compareTo(aux.dato);
			if (com == 0) {
	            throw new ElementoDuplicado(); // Ya existe
			}else if (com >0) {
				if(aux.der == null) {
					aux.der = new Nodoa<E>(x);
					return;
				}
	            aux = aux.der; // x es menor → ir a la izquierda
	        } else {
	        	if(aux.izq == null) {
					aux.izq = new Nodoa<E>(x);
					return;
				}
	            aux = aux.izq; // x es mayor → ir a la derecha
	        }
		}
		
	}

	public Nodoa<E> max() {
		Nodoa<E> actual = this;
	    while (actual.der != null) {
	        actual = actual.der;
	    }
	    return actual;
	}

	public Nodoa<E> min() {
		Nodoa<E> actual = this;
	    while (actual.izq != null) {
	        actual = actual.izq;
	    }
	    return actual;
	}
	
	//El delete va en la propia implementación por si hay que eliminar la raiz.

	public Nodoa<E> search(E x) throws NoSuchElementException {
	    Nodoa<E> aux = this;
	    while (aux != null) {
	    	int com = x.compareTo(aux.dato);
	        if (aux.dato == x) {
	            return aux; // encontrado
	        } else if (com < 0) {
	            aux = aux.izq; // x es menor → ir a la izquierda
	        } else {
	            aux = aux.der; // x es mayor → ir a la derecha
	        }
	    }// Usamos el compareTo porque en genérico el <, > no funciona.
	    throw new NoSuchElementException("Elemento no encontrado");
	}
}