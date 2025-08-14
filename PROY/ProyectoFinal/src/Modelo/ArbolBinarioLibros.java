/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

//import java.util.ArrayList;
//import java.util.List;
/**
 *
 * @author Julio Yanac
 */



public class ArbolBinarioLibros {
    private NodoArbolBinario raiz;

    public ArbolBinarioLibros() {
        this.raiz = null;
    }

    public void insertar(Libro libro) {
        raiz = insertarRecursivo(raiz, libro);
    }

    private NodoArbolBinario insertarRecursivo(NodoArbolBinario actual, Libro libro) {
        if (actual == null) {
            return new NodoArbolBinario(libro);
        }

        int comparacion = libro.getTitulo().compareToIgnoreCase(actual.getLibro().getTitulo());

        if (comparacion < 0) {
            actual.setHijoIzquierdo(insertarRecursivo(actual.getHijoIzquierdo(), libro));
        } else if (comparacion > 0) {
            actual.setHijoDerecho(insertarRecursivo(actual.getHijoDerecho(), libro));
        } else {
            // Manejo de duplicados
            System.out.println("Advertencia: Libro con el título '" + libro.getTitulo() + "' ya existe. No se añadió.");
            return actual;
        }
        return actual;
    }

    public Libro buscar(String titulo) {
        return buscarRecursivo(raiz, titulo);
    }

    private Libro buscarRecursivo(NodoArbolBinario actual, String titulo) {
        if (actual == null) {
            return null;
        }

        int comparacion = titulo.compareToIgnoreCase(actual.getLibro().getTitulo());

        if (comparacion == 0) {
            return actual.getLibro();
        } else if (comparacion < 0) {
            return buscarRecursivo(actual.getHijoIzquierdo(), titulo);
        } else {
            return buscarRecursivo(actual.getHijoDerecho(), titulo);
        }
    }

    public ListaLibros obtenerLibrosInorden() {
        ListaLibros libros = new ListaLibros();
        inordenRecursivo(raiz, libros);
        return libros;
    }

    private void inordenRecursivo(NodoArbolBinario nodo, ListaLibros lista) {
        if (nodo != null) {
            inordenRecursivo(nodo.getHijoIzquierdo(), lista);
            lista.agregarAlFinal(nodo.getLibro());
            inordenRecursivo(nodo.getHijoDerecho(), lista);
        }
    }

    public boolean estaVacio() {
        return raiz == null;
    }
}
