/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

/**
 *
 * @author jhery
 */
public interface Pila {
    void push(Libro libro);
    Libro pop();
    Libro peek();
    boolean isEmpty();
    int size();
}
