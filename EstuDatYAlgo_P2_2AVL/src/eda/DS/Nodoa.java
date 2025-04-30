package eda.DS;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

import eda.EXCEPTIONS.ElementoDuplicado;

public class Nodoa<E extends Comparable<E>> {
	private E dato;
	private Nodoa<E> izq, der;
	
	
	public E getDato() {
		return dato;
	}

	public void setDato(E dato) {
		this.dato = dato;
	}

	
	public Nodoa<E> getIzq() {
		return izq;
	}

	public void setIzq(Nodoa<E> izq) {
		this.izq = izq;
	}

	public Nodoa<E> getDer() {
		return der;
	}

	public void setDer(Nodoa<E> der) {
		this.der = der;
	}

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
	
	//Métodos del AVL
	
	
	public int altura() {
		return calcularAlturaIterativa(this); // No es necesaria tanta selección porque this no puede ser error.
	}

	/*private int calcularAltura(Nodoa<E> nodo) {
	    if (nodo == null) return -1; // Recordatorio -1 no tiene hijos y 0 solo tiene uno.
	    int alturaIzq = calcularAltura(nodo.izq);
	    int alturaDer = calcularAltura(nodo.der);
	    return 1 + Math.max(alturaIzq, alturaDer);
	}*/
	
	public int calcularAlturaIterativa(Nodoa<E> nodo) {
	    if (nodo == null) return -1;

	    Queue<Nodoa<E>> cola = new LinkedList<>();
	    cola.add(nodo);
	    int altura = -1;
	    //En la cola vamos añadiendo los nodos y sacandolos para hacer una busqueda en anchura.
	    while (!cola.isEmpty()) {
	        int nivelSize = cola.size(); // Cantidad de nodos en el nivel actual
	        for (int i = 0; i < nivelSize; i++) {
	            Nodoa<E> actual = cola.poll();
	            if (actual.izq != null) cola.add(actual.izq);
	            if (actual.der != null) cola.add(actual.der);
	        }
	        altura++; // Hemos terminado de procesar un nivel
	    }

	    return altura;
	}
	
	public boolean difalt(Nodoa<E> N) {
		int ai = -1;
		if (N.izq != null) ai = N.izq.altura();
		int ad = -1;
		if (N.der != null) ad = N.der.altura();
		if (Math.abs(ai-ad)>1) {// Le ponemos los if, porque los metodos de altura son para el objeto de nodos.
			return false;
		}else {
			return true;
		}
		
	}
	
	public boolean da() {
		return difalt(this);
	}
	
	public int facBalance() {
		int ai = -1;
		if (this.izq != null) ai = this.izq.altura();
		int ad = -1;
		if (this.der != null) ad = this.der.altura();
		return ai- ad;
	}
	
	public Nodoa<E> balancear(){
	    int balance = this.facBalance();
	    int subbalance;

	    if (balance > 1) { // LL o LR
	        subbalance = this.izq.facBalance();
	        if (subbalance >= 0) {
	            return this.Rightrotate(); // LL
	        } else {
	            this.izq = this.izq.Leftrotate(); // LR
	            return this.Rightrotate();
	        }
	    } else if (balance < -1) { // RR o RL
	        subbalance = this.der.facBalance();
	        if (subbalance <= 0) {
	            return this.Leftrotate(); // RR
	        } else {
	            this.der = this.der.Rightrotate(); // RL
	            return this.Leftrotate();
	        }
	    } else {
	        return this; // ya balanceado
	    }
	}

	
	//Métodos de las rotaciones
	public Nodoa<E> Rightrotate() {// Rotación de la derecha. Hacia la izquierda
		Nodoa<E> child = this.der;
		Nodoa<E> aux = child.izq;
		child.izq = this;
		this.der = aux;
		return child;
	}
	public Nodoa<E> RR(Nodoa<E> N){
		return N.Rightrotate();
	}	
	public Nodoa<E> LR(Nodoa<E> N){
		return N.Leftrotate();
	}
	public Nodoa<E> Leftrotate() {//Rotación de la izquierda. Hacia la derecha.
		Nodoa<E> child = this.izq;
		Nodoa<E> aux = child.der;
		child.der = this;
		this.izq = aux;
		return child;
	}	
}