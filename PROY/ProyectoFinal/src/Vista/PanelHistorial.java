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

public class PanelHistorial extends JPanel {
    private final JButton btnVerHistorial;
    private final JButton btnVolverHistorial;
    private final JTextArea textAreaHistorial;

    public PanelHistorial() {
        Color colorFondo = new Color(240, 240, 240);
        Color colorBoton = new Color(70, 130, 180);
        
        setLayout(new BorderLayout(10, 10));
        setBackground(colorFondo);
        setBorder(BorderFactory.createTitledBorder("Historial de Vistas"));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(colorFondo);
        
        btnVerHistorial = new JButton("Ver Historial");
        btnVerHistorial.setBackground(colorBoton);
        btnVerHistorial.setForeground(Color.WHITE);
        
        btnVolverHistorial = new JButton("Volver (Historial)");
        btnVolverHistorial.setBackground(colorBoton);
        btnVolverHistorial.setForeground(Color.WHITE);
        
        panelBotones.add(btnVerHistorial);
        panelBotones.add(btnVolverHistorial);
        add(panelBotones, BorderLayout.NORTH);

        textAreaHistorial = new JTextArea(12, 30);
        textAreaHistorial.setEditable(false);
        textAreaHistorial.setLineWrap(true);
        textAreaHistorial.setWrapStyleWord(true);
        textAreaHistorial.setBackground(colorFondo);
        
        JScrollPane scroll = new JScrollPane(textAreaHistorial);
        add(scroll, BorderLayout.CENTER);
    }

    public JButton getBtnVerHistorial() { return btnVerHistorial; }
    public JButton getBtnVolverHistorial() { return btnVolverHistorial; }

    public void setHistorialTexto(String texto) {
        textAreaHistorial.setText(texto);
    }
}
