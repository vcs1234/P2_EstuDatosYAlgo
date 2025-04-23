package eda.EXCEPTIONS;

public class ArbolVacio extends Exception{
	public ArbolVacio (){
		super ("Este arbol está vacio");
	}
	
	public ArbolVacio (String message) {
		super(message);
	}
	

}
