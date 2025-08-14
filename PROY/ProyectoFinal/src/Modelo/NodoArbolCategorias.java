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
public class NodoArbolCategorias {
    private final String nombreCategoria;
    private final ListaLibros librosCategoria;
    private NodoArbolCategorias primerHijo;
    private NodoArbolCategorias sigHermano;

    public NodoArbolCategorias(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
        this.primerHijo = null;
        this.sigHermano = null;
        this.librosCategoria = new ListaLibros();
    }
    
    public String getNombreCategoria() { return nombreCategoria; }

    public ListaLibros getLibrosCategoria() { return librosCategoria; }

    public NodoArbolCategorias getPrimerHijo() { return primerHijo; }

    public NodoArbolCategorias getSiguienteHermano() { return sigHermano; }

    public void setSiguienteHermano(NodoArbolCategorias siguienteHermano) { this.sigHermano = siguienteHermano; }

    
    public void agregarHijo(NodoArbolCategorias nuevoHijo) {
        if (this.primerHijo == null) {
            this.primerHijo = nuevoHijo;
        } else {
            NodoArbolCategorias temp = this.primerHijo;
            while (temp.getSiguienteHermano() != null) {
                temp = temp.getSiguienteHermano();
            }
            temp.setSiguienteHermano(nuevoHijo);
        }
    }
    
    public void agregarLibro(Libro libro) { this.librosCategoria.agregarAlFinal(libro); }

    @Override
    public String toString() { return nombreCategoria; }
}
