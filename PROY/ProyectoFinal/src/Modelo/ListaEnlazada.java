/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

/**
 *
 * @author Jhery
 */
public interface ListaEnlazada {
    void agregarAlFinal(Libro libro);
    void agregarAlInicio(Libro libro);
    boolean eliminar(String titulo);
    void ordenarPorTituloBurbuja();
    void ordenarPorAutorMerge();
    void ordenarPorAñoBurbuja();
    int buscarPorTitulo(String titulo);
    void imprimir();
}