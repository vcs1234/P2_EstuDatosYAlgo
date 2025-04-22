package eda.ADT;

import java.util.Iterator;

import eda.DS.*;
import eda.EXCEPTIONS.*;

public class BST{
	public interface arbol<E extends Comparable<E>> extends Iterable<Nodo<E>>{
		void add( E x)throws ElementoDuplicado;
		Nodo<E> max();
		Nodo<E> min();
		void delete( E x)throws ArbolVacio;
		Nodo<E> search (E x)throws ElementoNoEncontrado;
	}
}