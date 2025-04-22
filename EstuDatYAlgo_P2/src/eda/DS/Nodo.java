package eda.DS;

public class Nodo<E extends Comparable<E>> {
	E dato;
	Nodo izq, der;
	public Nodo (E x) {
		this.dato = x;
		izq = null;
		der = null;
	}
	
	public boolean equals(Nodo<E> N) {
		return N instanceof Nodo<E> && (N.dato.equals(dato));
	}
}
