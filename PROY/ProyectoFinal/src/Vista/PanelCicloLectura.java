/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.Libro;
import Modelo.ListaCircular;
import Modelo.ListaLibros;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author SanchezMRL
 */
public class PanelCicloLectura extends JPanel {
    private static final Color COLOR_FONDO = new Color(240, 240, 240);
    private static final Color COLOR_BOTON = new Color(0, 102, 153); 
    private static final Color COLOR_BORDE = new Color(180, 180, 180);
    
    private JTextField txtTitulo, txtAutor;
    private JButton btnAgregar, btnSiguiente, btnAnterior, btnOrdenar;
    private JTextArea areaCiclo, areaActual;
    private ListaCircular ciclo;
    private ListaLibros listaDisponible;

    public PanelCicloLectura() {
        ciclo = new ListaCircular();
        setLayout(new BorderLayout());
        setBackground(COLOR_FONDO);

        JPanel panelEntrada = new JPanel(new GridLayout(2, 2, 5, 5));
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Datos del libro"));
        panelEntrada.setBackground(COLOR_FONDO);

        panelEntrada.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        panelEntrada.add(txtTitulo);

        panelEntrada.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        panelEntrada.add(txtAutor);

        // Botones con estilo mínimo
        btnAgregar = new JButton("Agregar al Ciclo");
        btnSiguiente = new JButton("Siguiente Libro");
        btnAnterior = new JButton("Libro Anterior");
        btnOrdenar = new JButton("Ordenar por Título");
        
        // Aplicar estilo a botones
        JButton[] botones = {btnAgregar, btnSiguiente, btnAnterior, btnOrdenar};
        for (JButton boton : botones) {
            boton.setBackground(COLOR_BOTON);
            boton.setForeground(Color.WHITE);
            boton.setFocusPainted(false);
        }

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(COLOR_FONDO);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnSiguiente);
        panelBotones.add(btnAnterior);
        panelBotones.add(btnOrdenar);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(COLOR_FONDO);
        panelSuperior.add(panelEntrada, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        areaActual = new JTextArea(3, 30);
        areaActual.setEditable(false);
        areaActual.setBorder(BorderFactory.createTitledBorder("Libro Actual"));
        areaActual.setBackground(COLOR_FONDO);

        areaCiclo = new JTextArea(10, 30);
        areaCiclo.setEditable(false);
        areaCiclo.setBorder(BorderFactory.createTitledBorder("Ciclo de Lectura"));
        areaCiclo.setBackground(COLOR_FONDO);

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(COLOR_FONDO);
        panelCentro.add(areaActual, BorderLayout.NORTH);
        panelCentro.add(new JScrollPane(areaCiclo), BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> agregarLibro());
        btnSiguiente.addActionListener(e -> mostrarSiguiente());
        btnAnterior.addActionListener(e -> mostrarAnterior());
        btnOrdenar.addActionListener(e -> ordenarPorTitulo());
    }

    public void setListaDisponible(ListaLibros listaDisponible) {
        this.listaDisponible = listaDisponible;
    }

    private void agregarLibro() {
        String titulo = txtTitulo.getText().trim();
        String autor = txtAutor.getText().trim();

        if (titulo.isEmpty() || autor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.");
            return;
        }

        if (listaDisponible == null) {
            JOptionPane.showMessageDialog(this, "No se ha proporcionado la lista de libros disponibles.");
            return;
        }

        int posicion = listaDisponible.buscarPorTitulo(titulo);
        if (posicion == -1) {
            JOptionPane.showMessageDialog(this, "El libro no existe en la biblioteca.");
            return;
        }

        Libro libro = new Libro(titulo, autor, "", 0);
        ciclo.agregarLibro(libro);
        txtTitulo.setText("");
        txtAutor.setText("");

        actualizarVista();
    }

    private void mostrarSiguiente() {
        if (ciclo.estaVacia()) {
            JOptionPane.showMessageDialog(this, "El ciclo está vacío.");
            return;
        }

        Libro libro = ciclo.siguienteLibro();
        areaActual.setText("Libro actual: " + libro.getTitulo() + " - " + libro.getAutor());
        actualizarVista();
    }

    private void mostrarAnterior() {
        if (ciclo.estaVacia()) {
            JOptionPane.showMessageDialog(this, "El ciclo está vacío.");
            return;
        }

        Libro libro = ciclo.libroAnterior();
        areaActual.setText("Libro actual: " + libro.getTitulo() + " - " + libro.getAutor());
        actualizarVista();
    }

    private void ordenarPorTitulo() {
        if (ciclo.estaVacia()) {
            JOptionPane.showMessageDialog(this, "No hay libros para ordenar.");
            return;
        }

        ciclo.ordenarPorTituloConInsertionSort();
        actualizarVista();
        JOptionPane.showMessageDialog(this, "Ciclo ordenado por título.");
    }

    private void actualizarVista() {
        areaCiclo.setText(ciclo.mostrarCiclo());

        Libro actual = ciclo.libroActual();
        if (actual != null) {
            areaActual.setText("Libro actual: " + actual.getTitulo() + " - " + actual.getAutor());
        } else {
            areaActual.setText("Ciclo vacío");
        }
    }
}