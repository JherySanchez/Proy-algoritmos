/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhery
 */
public class PanelGestionLibros extends JPanel {
    private static final Color COLOR_FONDO = new Color(240, 240, 240);
    private static final Color COLOR_BOTON = new Color(70, 130, 180); // Azul acero
    private static final Color COLOR_BOTON_ELIMINAR = new Color(220, 80, 80); // Rojo
    private static final Color COLOR_BORDE = new Color(200, 200, 200);

    private final JTextField txtTitulo, txtAutor, txtGenero, txtAño;
    private final JButton btnAgregarInicio, btnAgregarFinal, btnEliminar;
    private final JButton btnOrdenarTitulo, btnOrdenarAutor, btnOrdenarAño;
    private final JTable tablaLibros;
    private final DefaultTableModel modeloTablaLibros;

    public PanelGestionLibros() {
        setLayout(new BorderLayout(5, 5));
        setBackground(COLOR_FONDO);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtTitulo = new JTextField(20);
        txtAutor = new JTextField(20);
        txtGenero = new JTextField(20);
        txtAño = new JTextField(5);

        JPanel panelInput = new JPanel(new GridLayout(4, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Datos del Libro"));
        panelInput.setBackground(COLOR_FONDO);
        
        panelInput.add(crearEtiqueta("Título:"));
        panelInput.add(txtTitulo);
        panelInput.add(crearEtiqueta("Autor:"));
        panelInput.add(txtAutor);
        panelInput.add(crearEtiqueta("Género:"));
        panelInput.add(txtGenero);
        panelInput.add(crearEtiqueta("Año Publicación:"));
        panelInput.add(txtAño);

        btnAgregarInicio = crearBotonSimple("Agregar al Inicio", COLOR_BOTON);
        btnAgregarFinal = crearBotonSimple("Agregar al Final", COLOR_BOTON);
        btnEliminar = crearBotonSimple("Eliminar Libro", COLOR_BOTON_ELIMINAR);
        btnOrdenarTitulo = crearBotonSimple("Ordenar por Título", COLOR_BOTON);
        btnOrdenarAutor = crearBotonSimple("Ordenar por Autor", COLOR_BOTON);
        btnOrdenarAño = crearBotonSimple("Ordenar por Año", COLOR_BOTON);

        JPanel panelBotonesGestion = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotonesGestion.setBackground(COLOR_FONDO);
        panelBotonesGestion.add(btnAgregarInicio);
        panelBotonesGestion.add(btnAgregarFinal);
        panelBotonesGestion.add(btnEliminar);
        panelBotonesGestion.add(btnOrdenarTitulo);
        panelBotonesGestion.add(btnOrdenarAutor);
        panelBotonesGestion.add(btnOrdenarAño);

        JPanel topContainerPanel = new JPanel();
        topContainerPanel.setLayout(new BoxLayout(topContainerPanel, BoxLayout.Y_AXIS));
        topContainerPanel.setBackground(COLOR_FONDO);
        topContainerPanel.add(panelInput);
        topContainerPanel.add(panelBotonesGestion);

        add(topContainerPanel, BorderLayout.NORTH);

        String[] columnNames = {"Título", "Autor", "Género", "Año", "Precio"};
        modeloTablaLibros = new DefaultTableModel(columnNames, 0);
        tablaLibros = new JTable(modeloTablaLibros);
        
        tablaLibros.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tablaLibros.setRowHeight(25);
        tablaLibros.setGridColor(COLOR_BORDE);
        
        JScrollPane scrollPane = new JScrollPane(tablaLibros);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));
        add(scrollPane, BorderLayout.CENTER);
    }

    // Métodos auxiliares para estilo mínimo
    private JLabel crearEtiqueta(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return label;
    }

    private JButton crearBotonSimple(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return boton;
    }

    // Mantenemos todos los getters igual que en tu versión original
    public JTextField getTxtTitulo() { return txtTitulo; }
    public JTextField getTxtAutor() { return txtAutor; }
    public JTextField getTxtGenero() { return txtGenero; }
    public JTextField getTxtAño() { return txtAño; }
    public JButton getBtnAgregarInicio() { return btnAgregarInicio; }
    public JButton getBtnAgregarFinal() { return btnAgregarFinal; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnOrdenarTitulo() { return btnOrdenarTitulo; }
    public JButton getBtnOrdenarAutor() { return btnOrdenarAutor; }
    public JButton getBtnOrdenarAño() { return btnOrdenarAño; }
    public JTable getTablaLibros() { return tablaLibros; }
    public DefaultTableModel getTablaModelo() { return modeloTablaLibros; }

    public void limpiarCampos() {
        txtTitulo.setText("");
        txtAutor.setText("");
        txtGenero.setText("");
        txtAño.setText("");
    }
}