/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

/**
 *
 * @author jhery
 */
public interface Cola {
    void enqueue(Libro libro); //Añadir un libro a la cola
    Libro dequeue();  //remover libro
    Libro peek(); //devuelve el libro inicio sin removerlo
    boolean isEmpty(); //Verificar si esta vacio 
    int size(); // tamaño de la cola( numero de elementos) 
}
