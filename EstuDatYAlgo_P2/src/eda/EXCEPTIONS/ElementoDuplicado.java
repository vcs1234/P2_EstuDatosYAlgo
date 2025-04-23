package eda.EXCEPTIONS;

public class ElementoDuplicado extends Exception{
	public ElementoDuplicado() {
		super ("Este elemento ya existe en el arbol.");
	}
	
	public ElementoDuplicado(String message) {
		super(message);
	}

}
