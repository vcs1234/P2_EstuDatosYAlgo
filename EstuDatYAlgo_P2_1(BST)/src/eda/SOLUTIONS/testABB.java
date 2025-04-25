package eda.SOLUTIONS;

import eda.DS.ImplBST;
import eda.EXCEPTIONS.ArbolVacio;
import eda.EXCEPTIONS.ElementoDuplicado;

//import eda.EXCEPTIONS.WrongIndexException;

public class testABB {
	public static void main(String[] args) {
		try {
			ImplBST<Integer> im = new ImplBST();
			//Anadir elementos
			 im.add(10);
	         im.add(5);
	         im.add(15);
			
			System.out.println("El elemento maximo es "+ im.max().getDato());
			System.out.println("El elemento mínimo es "+ im.min().getDato());
			
			System.out.println("Buscamos el 5" + im.search(5).getDato());
			 im.delete(10);
	         System.out.println("Eliminado 10. Nuevo raíz (mínimo): " + im.min().getDato());
			
		} catch (ElementoDuplicado | ArbolVacio e) {
            System.out.println("Error: " + e.getMessage());
        }
	
	}
}
