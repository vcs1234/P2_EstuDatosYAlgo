package eda.EXCEPTIONS;

public class ElementoNoEncontrado extends Exception{
	public ElementoNoEncontrado() {
		super("Este elemento no existe en este arbol");
	}
}
