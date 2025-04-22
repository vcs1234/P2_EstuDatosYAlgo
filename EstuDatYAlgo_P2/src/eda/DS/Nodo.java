package eda.DS;

public class Nodo<E extends Comparable<E>> {
	E dato;
	Nodo<E> izq, der;
	public Nodo (E x) {
		this.dato = x;
		izq = null;
		der = null;
	}
	
	public boolean equals(Object obj) {
        if (this == obj) return true;                    // misma referencia
        if (!(obj instanceof Nodo)) return false;        // verifica instancia
        Nodo<?> o = (Nodo<?>) obj;                       // casting seguro
        return dato != null && dato.equals(o.dato);      // comparación de datos
    }
	//No ponemos instanceof Nodo<E> Porque java en la comprobación no ve genéricos.
}
