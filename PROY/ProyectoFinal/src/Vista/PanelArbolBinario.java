/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Julio Yanac
 */



public class PanelArbolBinario extends JPanel {
    private final JTextField txtTituloArbol;
    private final JTextField txtAutorArbol;
    private final JTextField txtGeneroArbol;
    private final JTextField txtAñoArbol;
    private final JButton btnAgregarLibroArbol;
    private final JTextField txtBuscarTituloArbol;
    private final JButton btnBuscarLibroArbol;
    private final JButton btnMostrarTodosArbol;
    private final JTextArea areaResultadosArbol;
    private final JScrollPane scrollResultadosArbol;

    public PanelArbolBinario() {
        // Solo agregamos estos colores al inicio
        Color colorFondo = new Color(240, 240, 240);
        Color colorBoton = new Color(70, 130, 180);
        
        setLayout(new BorderLayout(10, 10));
        setBackground(colorFondo); // Fondo gris claro
        setBorder(BorderFactory.createTitledBorder("Gestión con Árbol Binario de Búsqueda"));

        // Panel de entrada para añadir libros (solo cambio de fondo)
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 5, 5));
        panelInput.setBackground(colorFondo);
        panelInput.setBorder(BorderFactory.createTitledBorder("Añadir Libro al Árbol"));
        
        panelInput.add(new JLabel("Título:"));
        txtTituloArbol = new JTextField(20);
        panelInput.add(txtTituloArbol);
        
        panelInput.add(new JLabel("Autor:"));
        txtAutorArbol = new JTextField(20);
        panelInput.add(txtAutorArbol);
        
        panelInput.add(new JLabel("Género:"));
        txtGeneroArbol = new JTextField(20);
        panelInput.add(txtGeneroArbol);
        
        panelInput.add(new JLabel("Año Publicación:"));
        txtAñoArbol = new JTextField(5);
        panelInput.add(txtAñoArbol);
        
        btnAgregarLibroArbol = new JButton("Agregar Libro al Árbol");
        btnAgregarLibroArbol.setBackground(colorBoton); // Color azul
        btnAgregarLibroArbol.setForeground(Color.WHITE); // Texto blanco
        panelInput.add(btnAgregarLibroArbol);
        panelInput.add(new JLabel(""));

        // Panel de búsqueda (solo cambio de fondo)
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelBusqueda.setBackground(colorFondo);
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Buscar Libro en Árbol por Título"));
        
        panelBusqueda.add(new JLabel("Título a buscar:"));
        txtBuscarTituloArbol = new JTextField(20);
        panelBusqueda.add(txtBuscarTituloArbol);
        
        btnBuscarLibroArbol = new JButton("Buscar en Árbol");
        btnBuscarLibroArbol.setBackground(colorBoton); // Color azul
        btnBuscarLibroArbol.setForeground(Color.WHITE); // Texto blanco
        panelBusqueda.add(btnBuscarLibroArbol);

        // Panel de acciones y resultados (solo cambio de fondo)
        JPanel panelAccionesResultados = new JPanel(new BorderLayout(5, 5));
        panelAccionesResultados.setBackground(colorFondo);
        
        btnMostrarTodosArbol = new JButton("Mostrar Todos los Libros (In-Orden)");
        btnMostrarTodosArbol.setBackground(colorBoton); // Color azul
        btnMostrarTodosArbol.setForeground(Color.WHITE); // Texto blanco
        panelAccionesResultados.add(btnMostrarTodosArbol, BorderLayout.NORTH);

        areaResultadosArbol = new JTextArea(10, 40);
        areaResultadosArbol.setEditable(false);
        areaResultadosArbol.setLineWrap(true);
        areaResultadosArbol.setWrapStyleWord(true);
        areaResultadosArbol.setBackground(colorFondo);
        
        scrollResultadosArbol = new JScrollPane(areaResultadosArbol);
        panelAccionesResultados.add(scrollResultadosArbol, BorderLayout.CENTER);

        // Unir los paneles (sin cambios)
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelInput, BorderLayout.NORTH);
        panelSuperior.add(panelBusqueda, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelAccionesResultados, BorderLayout.CENTER);
    }

    // Todos los getters y métodos se mantienen EXACTAMENTE IGUAL
    public JTextField getTxtTituloArbol() { return txtTituloArbol; }
    public JTextField getTxtAutorArbol() { return txtAutorArbol; }
    public JTextField getTxtGeneroArbol() { return txtGeneroArbol; }
    public JTextField getTxtAñoArbol() { return txtAñoArbol; }
    public JButton getBtnAgregarLibroArbol() { return btnAgregarLibroArbol; }
    public JTextField getTxtBuscarTituloArbol() { return txtBuscarTituloArbol; }
    public JButton getBtnBuscarLibroArbol() { return btnBuscarLibroArbol; }
    public JButton getBtnMostrarTodosArbol() { return btnMostrarTodosArbol; }
    public void setAreaResultadosArbol(String texto) { areaResultadosArbol.setText(texto); }
    public void limpiarCampos() {
        txtTituloArbol.setText("");
        txtAutorArbol.setText("");
        txtGeneroArbol.setText("");
        txtAñoArbol.setText("");
        txtBuscarTituloArbol.setText("");
    }
}