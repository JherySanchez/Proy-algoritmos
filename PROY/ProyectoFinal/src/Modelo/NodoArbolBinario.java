/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Julio Yanac
 */
public class NodoArbolBinario {
    private Libro libro;
    private NodoArbolBinario hijoIzquierdo;
    private NodoArbolBinario hijoDerecho;

    //Constructor
    public NodoArbolBinario(Libro libro) {
        this.libro = libro;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }
    
    //Getters y Setters
    public Libro getLibro() { return libro; }
    public void setLibro(Libro libro) { this.libro = libro; }
    public NodoArbolBinario getHijoIzquierdo() { return hijoIzquierdo; }
    public void setHijoIzquierdo(NodoArbolBinario hijoIzquierdo) { this.hijoIzquierdo = hijoIzquierdo; }
    public NodoArbolBinario getHijoDerecho() { return hijoDerecho; }
    public void setHijoDerecho(NodoArbolBinario hijoDerecho) { this.hijoDerecho = hijoDerecho; }
}