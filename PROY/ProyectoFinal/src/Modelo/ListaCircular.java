/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author SanchezMRL
 */
public class ListaCircular {
    private NodoLibro cabeza;
    private NodoLibro actual; 
    private int tamaño;

    private static class NodoLibro {
        Libro libro;
        NodoLibro siguiente;
        NodoLibro anterior;

        NodoLibro(Libro libro) {
            this.libro = libro;
        }
    }

    public ListaCircular() {
        cabeza = null;
        actual = null;
        tamaño = 0;
    }

    public void agregarLibro(Libro libro) {
        NodoLibro nuevo = new NodoLibro(libro);
        if (cabeza == null) {
            cabeza = nuevo;
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
            actual = cabeza;
        } else {
            NodoLibro ultimo = cabeza.anterior;

            nuevo.siguiente = cabeza;
            nuevo.anterior = ultimo;
            ultimo.siguiente = nuevo;
            cabeza.anterior = nuevo;
        }
        tamaño++;
    }

    public String mostrarCiclo() {
        if (cabeza == null) return "Ciclo vacío.";
        StringBuilder sb = new StringBuilder();
        NodoLibro temp = cabeza;
        do {

            String marcaActual = (temp == actual) ? " * " : "   ";
            sb.append(marcaActual).append("📖 ").append(temp.libro.getTitulo())
              .append(" - ").append(temp.libro.getAutor()).append("\n");
            temp = temp.siguiente;
        } while (temp != cabeza);
        return sb.toString();
    }

    public Libro libroActual() {
        return actual != null ? actual.libro : null;
    }

    public Libro siguienteLibro() {
        if (actual == null) return null;
        actual = actual.siguiente;
        return actual.libro;
    }

    public Libro libroAnterior() {
        if (actual == null) return null;
        actual = actual.anterior;
        return actual.libro;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void ordenarPorTituloConInsertionSort() {
        if (cabeza == null || cabeza.siguiente == cabeza) return; 

        NodoLibro actual = cabeza.siguiente;
        while (actual != cabeza) {
            NodoLibro siguiente = actual.siguiente;
            NodoLibro insertarAntes = actual.anterior;

            while (insertarAntes != cabeza.anterior &&
                   insertarAntes.libro.getTitulo().compareToIgnoreCase(actual.libro.getTitulo()) > 0) {
                insertarAntes = insertarAntes.anterior;
            }

            if (insertarAntes.siguiente != actual) {
                actual.anterior.siguiente = actual.siguiente;
                actual.siguiente.anterior = actual.anterior;

                actual.siguiente = insertarAntes.siguiente;
                actual.anterior = insertarAntes;
                insertarAntes.siguiente.anterior = actual;
                insertarAntes.siguiente = actual;

                if (actual.libro.getTitulo().compareToIgnoreCase(cabeza.libro.getTitulo()) < 0) {
                    cabeza = actual;
                }
            }

            actual = siguiente;
        }

        if (this.actual != null) {
            Libro libroActual = this.actual.libro;
            NodoLibro temp = cabeza;
            do {
                if (temp.libro.equals(libroActual)) {
                    this.actual = temp;
                    break;
                }
                temp = temp.siguiente;
            } while (temp != cabeza);
        }
    }
}
