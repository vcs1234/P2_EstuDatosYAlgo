package eda.SOLUTIONS;

import eda.DS.ImplAVL;
import eda.EXCEPTIONS.ArbolVacio;
import eda.EXCEPTIONS.ElementoDuplicado;

public class testAVL {
    public static void main(String[] args) {
        ImplAVL<Integer> arbol = new ImplAVL<>();

        try {
            // Insertar elementos
            arbol.add(30);
            arbol.add(20);
            arbol.add(40);
            arbol.add(10);
            arbol.add(25);
            arbol.add(35);
            arbol.add(50);

            System.out.println("Árbol después de inserciones:");
            arbol.Mostrar();

            // Buscar
            System.out.println("Buscar 25: " + arbol.search(25).getDato());

            // Mínimo y máximo
            System.out.println("Mínimo: " + arbol.min().getDato());
            System.out.println("Máximo: " + arbol.max().getDato());

            // Eliminar
            arbol.delete(20); // nodo con dos hijos
            arbol.delete(50); // nodo hoja

            System.out.println("Árbol después de eliminaciones:");
            arbol.Mostrar();

        } catch (ElementoDuplicado e) {
            System.out.println("Elemento duplicado: " + e.getMessage());
        } catch (ArbolVacio e) {
            System.out.println("Árbol vacío: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error general: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
