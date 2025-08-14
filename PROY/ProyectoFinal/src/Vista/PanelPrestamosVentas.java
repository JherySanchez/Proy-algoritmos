/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author jhery
 */

public class PanelPrestamosVentas extends JPanel {
    private final JButton btnBuscar;
    private final JButton btnSolicitarLibro;
    private final JButton btnProcesarCola;
    private final JButton btnVerCola;
    private final JTextField txtBusquedaUniversal;

    public PanelPrestamosVentas() {
        Color colorFondo = new Color(240, 240, 240);
        Color colorBoton = new Color(70, 130, 180);
        
        setLayout(new BorderLayout(10, 10));
        setBackground(colorFondo);
        setBorder(BorderFactory.createTitledBorder("Búsqueda y Préstamos"));

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelBusqueda.setBackground(colorFondo);
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Buscar Libro (Título)"));
        
        txtBusquedaUniversal = new JTextField(25);
        btnBuscar = new JButton("Buscar y Añadir Historial");
        btnBuscar.setBackground(colorBoton);
        btnBuscar.setForeground(Color.WHITE);
        
        panelBusqueda.add(new JLabel("Término de Búsqueda:"));
        panelBusqueda.add(txtBusquedaUniversal);
        panelBusqueda.add(btnBuscar);
        
        add(panelBusqueda, BorderLayout.NORTH);

        JPanel panelSolicitudes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelSolicitudes.setBackground(colorFondo);
        
        btnSolicitarLibro = new JButton("Solicitar Libro (por Título)");
        btnSolicitarLibro.setBackground(colorBoton);
        btnSolicitarLibro.setForeground(Color.WHITE);
        
        panelSolicitudes.add(btnSolicitarLibro);
        add(panelSolicitudes, BorderLayout.CENTER);

        JPanel panelColaAcciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelColaAcciones.setBackground(colorFondo);
        
        btnVerCola = new JButton("Ver Cola de Préstamos");
        btnVerCola.setBackground(colorBoton);
        btnVerCola.setForeground(Color.WHITE);
        
        btnProcesarCola = new JButton("Procesar Siguiente Solicitud");
        btnProcesarCola.setBackground(colorBoton);
        btnProcesarCola.setForeground(Color.WHITE);
        
        panelColaAcciones.add(btnVerCola);
        panelColaAcciones.add(btnProcesarCola);
        add(panelColaAcciones, BorderLayout.SOUTH);
    }

    // Métodos getters se mantienen exactamente igual
    public JTextField getTxtBusquedaGeneral() { return txtBusquedaUniversal; } 
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnSolicitarLibro() { return btnSolicitarLibro; }
    public JButton getBtnProcesarCola() { return btnProcesarCola; }
    public JButton getBtnVerCola() { return btnVerCola; }

    public String getTerminoBusqueda() {
        return txtBusquedaUniversal.getText();
    }
}
