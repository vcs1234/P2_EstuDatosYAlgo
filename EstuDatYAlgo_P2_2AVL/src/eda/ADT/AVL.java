package eda.ADT;

import java.util.NoSuchElementException;

import eda.DS.Nodoa;
import eda.EXCEPTIONS.ArbolVacio;
import eda.EXCEPTIONS.ElementoDuplicado;

public class AVL {
	public interface arbol<E extends Comparable<E>> extends Iterable<Nodoa<E>>{
		void add( E x)throws ElementoDuplicado;
		Nodoa<E> max();
		Nodoa<E> min();
		void delete( E x)throws ArbolVacio;
		Nodoa<E> search (E x)throws NoSuchElementException;// Es equivalente a ElementoNoEncontrado
	}
}
