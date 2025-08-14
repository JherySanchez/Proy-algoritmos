/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jhery
 */
public class ColaLibros implements Cola {
    private NodoLibro cabeza;
    private NodoLibro cola;
    private int tamaño;

    public ColaLibros() {
        this.cabeza = null;
        this.cola = null;
        this.tamaño = 0;
    }
    

    @Override
    public void enqueue(Libro libro) {
        NodoLibro nuevoNodo = new NodoLibro(libro);
        if(isEmpty()){
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        }else{
            cola.setSiguiente(nuevoNodo);
            cola = nuevoNodo;
            
        }
        tamaño++;
    }

    @Override
    public Libro dequeue() {
        if(isEmpty()){
            System.out.println("La cola esta vacia, no se puede desencolar");
            return  null;
        }
        
        Libro libroRemovido = cabeza.getLibro(); //Guarda el libro
        cabeza = cabeza.getSiguiente(); //Pasa al siguiente
        
        if(cabeza == null){
            cola = null;
        }
        tamaño--;
        return libroRemovido;
    }

    @Override
    public Libro peek() {
        if(isEmpty()){
            return null;
        }
        return cabeza.getLibro();
    }
    
    @Override
    public boolean isEmpty() { return cabeza == null; }

    @Override
    public int size() { return tamaño; }
    
    public String obtenerColaFormateada() {
        if (isEmpty()) {
            return "La cola de prestamos está vacia";
        }
        StringBuilder sb = new StringBuilder();
        NodoLibro actual = cabeza;
        int contador = 1;
        while (actual != null) {
            sb.append(contador++).append(". ").append(actual.getLibro().getTitulo()).append("\n");
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }
}
