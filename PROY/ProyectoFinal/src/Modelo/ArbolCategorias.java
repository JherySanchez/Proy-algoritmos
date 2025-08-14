/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

//import java.util.ArrayList;
//import java.util.List;

/**
 *
 * @author jhery
 */
public class ArbolCategorias {
    private final NodoArbolCategorias raiz;

    public ArbolCategorias() {
        this.raiz = new NodoArbolCategorias("Todas las Categorias");
    }
    
    
    public NodoArbolCategorias getRaiz() {
        return raiz;
    }
    
    public void agregarCategoria(String categoriaPadre, String nuevaCategoria) {
        if (nuevaCategoria == null || nuevaCategoria.trim().isEmpty()) {
            return;
        }

        NodoArbolCategorias nuevoNodo = new NodoArbolCategorias(nuevaCategoria);

        if (categoriaPadre == null || categoriaPadre.trim().isEmpty() || categoriaPadre.equals(raiz.getNombreCategoria())) {
            raiz.agregarHijo(nuevoNodo);
        } else {
            NodoArbolCategorias nodoPadre = buscarNodoPorNombre(raiz, categoriaPadre);
            if (nodoPadre != null) {
                nodoPadre.agregarHijo(nuevoNodo);
            } else {
                raiz.agregarHijo(nuevoNodo); 
            }
        }
    }

    public NodoArbolCategorias buscarNodoPorNombre(NodoArbolCategorias nodoActual, String nombreBuscado) {
        if (nodoActual == null) {
            return null;
        }
        if (nodoActual.getNombreCategoria().equalsIgnoreCase(nombreBuscado)) {
            return nodoActual;
        }

        NodoArbolCategorias hijoActual = nodoActual.getPrimerHijo();
        while (hijoActual != null) {
            NodoArbolCategorias encontrado = buscarNodoPorNombre(hijoActual, nombreBuscado);
            if (encontrado != null) {
                return encontrado;
            }
            hijoActual = hijoActual.getSiguienteHermano();
        }
        return null;
    }

    public boolean asignarLibro(Libro libro, String nombreCategoria) {
        NodoArbolCategorias categoria = buscarNodoPorNombre(raiz, nombreCategoria);
        if (categoria != null) {
            categoria.agregarLibro(libro);
            return true;
        }
        return false;
    }

    public void limpiarLibros() {
        limpiarLibrosRec(raiz);
    }

    private void limpiarLibrosRec(NodoArbolCategorias nodo) {
        if (nodo == null) {
            return;
        }
        nodo.getLibrosCategoria().clear();

        NodoArbolCategorias hijoActual = nodo.getPrimerHijo();
        while (hijoActual != null) {
            limpiarLibrosRec(hijoActual);
            hijoActual = hijoActual.getSiguienteHermano();
        }
    }

    public void reconstruirArbol(ListaLibros listaLibros) {
        limpiarLibros();

        NodoLibro actual = listaLibros.getCabeza();
        while (actual != null) {
            Libro libro = actual.getLibro();
            if (!asignarLibro(libro, libro.getGenero())) {
                if (buscarNodoPorNombre(raiz, "Otros Géneros") != null) {
                    asignarLibro(libro, "Otros Géneros");
                }
            }
            actual = actual.getSiguiente();
        }
    }
    
    public ListaLibros obtenerLibrosCategoria(String nombreCategoria) {
        ListaLibros librosEncontrados = new ListaLibros();
        NodoArbolCategorias inicio = buscarNodoPorNombre(raiz, nombreCategoria);

        if (inicio != null) {
            recolectarLibrosRec(inicio, librosEncontrados);
        }
        return librosEncontrados;
    }

 
    private void recolectarLibrosRec(NodoArbolCategorias nodoActual, ListaLibros lista) {
        if (nodoActual == null) {
            return;
        }

        NodoLibro actual = nodoActual.getLibrosCategoria().getCabeza();
        while (actual != null) {
            lista.agregarAlFinal(actual.getLibro());
            actual = actual.getSiguiente();
        }
    }

}
