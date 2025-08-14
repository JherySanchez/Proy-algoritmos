/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jhery
 */
public class PilaLibros implements Pila {
    private NodoLibro cima;
    private int tamaño;

    public PilaLibros() {
        this.cima = null;
        this.tamaño = 0;
    }

    @Override
    public void push(Libro libro) {
        NodoLibro nuevoNodo = new NodoLibro(libro);
        nuevoNodo.setSiguiente(cima); // El nuevo nodo apunta a la antigua cima
        this.cima = nuevoNodo;        // La nueva cima es el nuevo nodo
        tamaño++;
    }

    @Override
    public Libro pop() {
        if (isEmpty()) {
            System.out.println("Error: La pila está vacia. No se puede hacer pop");
            return null;
        }
        Libro libroCima = cima.getLibro(); // Guarda el libro de la cima
        this.cima = cima.getSiguiente();   // La cima se mueve al siguiente nodo
        tamaño--;
        return libroCima;
    }

    @Override
    public Libro peek() {
        if (isEmpty()) {
            return null;
        }
        return cima.getLibro();
    }

    @Override
    public boolean isEmpty() { return cima == null; }

    @Override
    public int size() { return tamaño; }
    
    //Genera una cadena formateada del historial de libros vistos
    public String obtenerHistorialFormateado() {
        if (isEmpty()) {
            return "El historial de vistas está vacío.";
        }
        StringBuilder sb = new StringBuilder();
        NodoLibro actual = cima;
        int contador = 1;
        // Para mostrar del más reciente al más antiguo (orden LIFO)
        // Recorremos la pila hacia abajo
        while (actual != null) {
            sb.append(contador++).append(". ").append(actual.getLibro().getTitulo()).append("\n");
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }
}
