/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

//import java.util.ArrayList;
//import java.util.List;

/**
 *
 * @author Jhery
 */
public class ListaLibros implements ListaEnlazada{
    private NodoLibro cabeza;

    @Override
    public void agregarAlFinal(Libro libro) {
        NodoLibro nuevo = new NodoLibro(libro);
        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }
        NodoLibro actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevo);
    }
    
    @Override
    public void agregarAlInicio(Libro libro) {
        NodoLibro nuevo = new NodoLibro(libro);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
    }
    
    @Override
    public boolean eliminar(String titulo) {
        if(cabeza == null){//Ver.lista vacia
            return false;
        }
        if(cabeza.getLibro().getTitulo().equalsIgnoreCase(titulo)){
            cabeza = cabeza.getSiguiente();
            return true;
            
        }
        
        NodoLibro actual = cabeza;
        while(actual.getSiguiente() != null){
            if(actual.getSiguiente().getLibro().getTitulo().equalsIgnoreCase(titulo)){
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return true;
                
            }
            actual = actual.getSiguiente();

        }
        return false;
    }

    @Override
    public void ordenarPorTituloBurbuja() {
        if(cabeza==null || cabeza.getSiguiente() == null){
            return;            
        }
        
        boolean intercambio;
        do{
            intercambio = false;
            NodoLibro actual = cabeza;
            while(actual.getSiguiente() != null){
                if(actual.getLibro().getTitulo().compareToIgnoreCase(actual.getSiguiente().getLibro().getTitulo()) > 0){
                    Libro temp = actual.getLibro();
                    actual.setLibro(actual.getSiguiente().getLibro());
                    actual.getSiguiente().setLibro(temp);
                    intercambio = true;  
                }
                actual = actual.getSiguiente();  
            }
        }while(intercambio);
    }

    @Override
    public void ordenarPorAutorMerge() {
        cabeza = mergeSortPorAutor(cabeza);
    }

    private NodoLibro mergeSortPorAutor(NodoLibro inicio) {
        if (inicio == null || inicio.getSiguiente() == null) {
            return inicio;
        }
        NodoLibro mitad = obtenerMitad(inicio);
        NodoLibro siguienteDeMitad = mitad.getSiguiente();
        mitad.setSiguiente(null);
        
        NodoLibro izquierda = mergeSortPorAutor(inicio);
        NodoLibro derecha = mergeSortPorAutor(siguienteDeMitad);
        return fusionarPorAutor(izquierda, derecha);
    }

    private NodoLibro obtenerMitad(NodoLibro inicio) {
        if (inicio == null) return null;
        NodoLibro lento = inicio;
        NodoLibro rapido = inicio.getSiguiente();
        
        while (rapido != null && rapido.getSiguiente() != null) {
            lento = lento.getSiguiente();
            rapido = rapido.getSiguiente().getSiguiente();
        }
        return lento;
    }

    private NodoLibro fusionarPorAutor(NodoLibro l1, NodoLibro l2) {
        NodoLibro dummy = new NodoLibro(null);
        NodoLibro actual = dummy;
        while (l1 != null && l2 != null) {
            if (l1.getLibro().getAutor().compareToIgnoreCase(l2.getLibro().getAutor()) <= 0) {
                actual.setSiguiente(l1);
                l1 = l1.getSiguiente();
            } else {
                actual.setSiguiente(l2);
                l2 = l2.getSiguiente();
            }
            actual = actual.getSiguiente();
        }
        
        if (l1 != null) actual.setSiguiente(l1);
        if (l2 != null) actual.setSiguiente(l2);
        return dummy.getSiguiente();
    }
   
    @Override
    public void ordenarPorAñoBurbuja() {
        // Verificar si la lista está vacía o tiene un solo nodo
        if (cabeza == null || cabeza.getSiguiente() == null) {
            return; // No es necesario ordenar
        }

        boolean huboCambios;
        do {
            huboCambios = false;
            NodoLibro actual = cabeza;

            // Recorrer la lista hasta el penúltimo nodo
            while (actual.getSiguiente() != null) {
                Libro libroActual = actual.getLibro();
                Libro libroSiguiente = actual.getSiguiente().getLibro();

                // Comparar el año
                if (libroActual.getAñoPublicacion() > libroSiguiente.getAñoPublicacion()) {
                    // Intercambiar los libros
                    actual.setLibro(libroSiguiente);
                    actual.getSiguiente().setLibro(libroActual);
                    huboCambios = true;
                }
                actual = actual.getSiguiente();
            }
        } while (huboCambios); // Repetir mientras haya cambios
    }
    
    @Override
    public int buscarPorTitulo(String titulo) {
        NodoLibro actual = cabeza;
        int index = 0;
        while (actual != null) {
            if (actual.getLibro().getTitulo().equalsIgnoreCase(titulo)) {
                return index;
            }
            actual = actual.getSiguiente();
            index++;
        }
        return -1;
    }
    
    public ListaLibros buscarLibrosPorCualquierCampo(String termino) {
        ListaLibros resultados = new ListaLibros();
        if (termino == null || termino.trim().isEmpty()) {
            return resultados;
        }
        String termLower = termino.trim().toLowerCase();
        
        NodoLibro actual = cabeza;
        while (actual != null) {
            Libro libro = actual.getLibro();
            // Buscar por titulo, autor, genero y año
            if (libro.getTitulo().toLowerCase().contains(termLower) ||
                libro.getAutor().toLowerCase().contains(termLower) ||
                libro.getGenero().toLowerCase().contains(termLower) ||
                String.valueOf(libro.getAñoPublicacion()).contains(termLower)) { // Convierte el año a String para buscar
                resultados.agregarAlFinal(libro);
            }
            actual = actual.getSiguiente();
        }
        return resultados;
    }
    
    
    @Override
    public void imprimir() {
        NodoLibro actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getLibro());
            actual = actual.getSiguiente();
        }
    }

    public NodoLibro getCabeza() {
        return cabeza;
    }
    
    public void clear(){
        cabeza = null;
    }
    
}
