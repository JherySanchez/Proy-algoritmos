package Modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaDobleCarrito {
    private NodoItem cabeza;
    private NodoItem cola;
    private int tamaño;
    private double totalVentas;

    private static class NodoItem {
        Libro libro;
        int cantidad;
        NodoItem anterior;
        NodoItem siguiente;

        NodoItem(Libro libro, int cantidad) {
            this.libro = libro;
            this.cantidad = cantidad;
        }
    }
    public ListaDobleCarrito() {
        cabeza = null;
        cola = null;
        tamaño = 0;
        totalVentas = 0;
    }
    // Agrega un libro al final del carrito
    public void agregarItem(Libro libro, int cantidad) {
        NodoItem existente = buscarNodo(libro.getTitulo());
        if (existente != null) {
            existente.cantidad += cantidad;
            return;
        }
        NodoItem nuevo = new NodoItem(libro, cantidad);
        if (cola == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.anterior = cola;
            cola.siguiente = nuevo;
            cola = nuevo;
        }
        tamaño++;
    }
    // Elimina un libro del carrito
    public boolean eliminarItem(String titulo) {
        NodoItem actual = buscarNodo(titulo);
        if (actual == null) return false;

        if (actual.anterior != null) {
            actual.anterior.siguiente = actual.siguiente;
        } else {
            cabeza = actual.siguiente;
        }
        if (actual.siguiente != null) {
            actual.siguiente.anterior = actual.anterior;
        } else {
            cola = actual.anterior;
        }
        tamaño--;
        return true;
    }
    // Actualiza la cantidad de un libro en el carrito
    public boolean actualizarCantidad(String titulo, int nuevaCantidad) {
        NodoItem actual = buscarNodo(titulo);
        if (actual == null || nuevaCantidad <= 0) return false;
        
        actual.cantidad = nuevaCantidad;
        return true;
    }
    // Busca un nodo por título
    private NodoItem buscarNodo(String titulo) {
        NodoItem actual = cabeza;
        while (actual != null) {
            if (actual.libro.getTitulo().equalsIgnoreCase(titulo)) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }
    // Calcula el total del carrito actual
    public double calcularTotal() {
        double total = 0;
        NodoItem actual = cabeza;
        while (actual != null) {
            total += actual.libro.getPrecio() * actual.cantidad;
            actual = actual.siguiente;
        }
        return total;
    }
    // Procesa el pago y acumula el total
    public boolean procesarPago() {
        if (estaVacia()) return false;
        
        totalVentas += calcularTotal();
        vaciar();
        return true;
    }
    // Obtiene todos los items para mostrar en la vista
    public String obtenerItemsFormateados() {
        StringBuilder sb = new StringBuilder();
        NodoItem actual = cabeza;
        int contador = 1;
        
        while (actual != null) {
            sb.append(contador++)
              .append(". ").append(actual.libro.getTitulo())
              .append(" - Cantidad: ").append(actual.cantidad)
              .append(" - Precio unitario: $").append(String.format("%.2f", actual.libro.getPrecio()))
              .append(" - Subtotal: $").append(String.format("%.2f", actual.libro.getPrecio() * actual.cantidad))
              .append("\n");
            actual = actual.siguiente;
        }
        sb.append("\nTotal actual: $").append(String.format("%.2f", calcularTotal()));
        return sb.toString();
    }
    public boolean estaVacia() {
        return cabeza == null;
    }
    public int getTamaño() {
        return tamaño;
    }
    public void vaciar() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }
    public double getTotalVentas() {
        return totalVentas;
    }
    public List<String> obtenerItemsParaTabla() {
        List<String> items = new ArrayList<>();
        NodoItem actual = cabeza;
        while (actual != null) {
            items.add(actual.libro.getTitulo());
            items.add(String.valueOf(actual.cantidad));
            items.add(String.format("$%.2f", actual.libro.getPrecio()));
            items.add(String.format("$%.2f", actual.libro.getPrecio() * actual.cantidad));
            actual = actual.siguiente;
        }
        return items;
    }
}