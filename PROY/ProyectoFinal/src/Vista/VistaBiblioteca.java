/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


/**
 *
 * @author gabrielyg2
 */

public class VistaBiblioteca extends JPanel {
 
    private final JLabel lblMensaje;
    private final JTabbedPane tabbedPane;
    
    // Nuevos paneles
    private final PanelGestionLibros panelGestionLibros;
    private final PanelPrestamosVentas panelPrestamosVentas;
    private final PanelHistorial panelHistorial;
    private final PanelCategorias panelCategorias;
    private final PanelCicloLectura panelCicloLectura;
    private final PanelArbolBinario panelArbolBinario;
    private final PanelCarrito panelCarrito;

    // Atributos para los dialogos
    private final JDialog historialDialog;
    private final JTextArea historialTextArea;
    private final JDialog colaDialog;
    private final JTextArea colaTextArea;

    public VistaBiblioteca(JFrame parentFrame) {

        setLayout(new BorderLayout());
        lblMensaje = new JLabel("Mensajes del sistema...");

        // Inicialización de los diálogos
        historialDialog = new JDialog(parentFrame, "Historial de Libros Vistos", false);
        historialDialog.setSize(400, 300);
        historialDialog.setLocationRelativeTo(parentFrame);
        historialDialog.setLayout(new BorderLayout());
        historialTextArea = new JTextArea();
        historialTextArea.setEditable(false);
        JScrollPane historialScrollPane = new JScrollPane(historialTextArea);
        historialDialog.add(historialScrollPane, BorderLayout.CENTER);
        JButton cerrarHistorialBtn = new JButton("Cerrar");
        cerrarHistorialBtn.addActionListener(e -> historialDialog.dispose());
        JPanel historialBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        historialBtnPanel.add(cerrarHistorialBtn);
        historialDialog.add(historialBtnPanel, BorderLayout.SOUTH);

        colaDialog = new JDialog(parentFrame, "Cola de Prestamos Pendientes", false);
        colaDialog.setSize(400, 300);
        historialDialog.setLocationRelativeTo(parentFrame);
        colaDialog.setLayout(new BorderLayout());
        colaTextArea = new JTextArea();
        colaTextArea.setEditable(false);
        JScrollPane colaScrollPane = new JScrollPane(colaTextArea);
        colaDialog.add(colaScrollPane, BorderLayout.CENTER);
        JButton cerrarColaBtn = new JButton("Cerrar");
        cerrarColaBtn.addActionListener(e -> colaDialog.dispose());
        JPanel colaBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        colaBtnPanel.add(cerrarColaBtn);
        colaDialog.add(colaBtnPanel, BorderLayout.SOUTH);

        //Instancia de los paneles para las pesatañas
        panelGestionLibros = new PanelGestionLibros();
        panelPrestamosVentas = new PanelPrestamosVentas();
        panelHistorial = new PanelHistorial();
        panelCategorias = new PanelCategorias();
        panelCicloLectura = new PanelCicloLectura();
        panelArbolBinario = new PanelArbolBinario();
        panelCarrito = new PanelCarrito();
        
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Gestión de Libros", panelGestionLibros);
        tabbedPane.addTab("Préstamos y Ventas", panelPrestamosVentas);
        tabbedPane.addTab("Historial", panelHistorial);
        tabbedPane.addTab("Categorías", panelCategorias);
        tabbedPane.addTab("Ciclo de Lectura", panelCicloLectura);
        tabbedPane.addTab("Árbol Binario", panelArbolBinario);
        tabbedPane.addTab("Carrito", panelCarrito);

        // Organizacion  de la ventana principal
        add(tabbedPane,BorderLayout.CENTER);
        
        JPanel panelMensaje = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelMensaje.add(lblMensaje);
        add(panelMensaje, BorderLayout.SOUTH);
        
        
    }
    // Metodos Getters para los componentes de la tabla y mensajes
    public JTable getTabla() { return panelGestionLibros.getTablaLibros(); }
    public DefaultTableModel getTablaModelo() { return panelGestionLibros.getTablaModelo(); }
    public void mostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
        JOptionPane.showMessageDialog(this, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }
    public void mostrarLibros(String[][] datos) {
        DefaultTableModel model =(DefaultTableModel)getTabla().getModel();
        model.setRowCount(0);
        for (String[] fila : datos) {
            model.addRow(fila);
        }
    }
    
    
    //Getters para acceder al JtabbedPane
    public JTabbedPane getTabbedPane() { return tabbedPane; }
    public PanelGestionLibros getPanelGestionLibros() { return panelGestionLibros; }
    public PanelPrestamosVentas getPanelPrestamosVentas() { return panelPrestamosVentas; }
    public PanelHistorial getPanelHistorial() { return panelHistorial; }
    public PanelCategorias getPanelCategorias() { return panelCategorias; }
    public PanelCicloLectura getPanelCicloLectura() { return panelCicloLectura; }
    public PanelArbolBinario getPanelArbolBinario() { return panelArbolBinario; }
    public PanelCarrito getPanelCarrito() { return panelCarrito; }
    
    
    // Getters para los componentes dentro de cada panel
    public String getTitulo() { return panelGestionLibros.getTxtTitulo().getText(); }
    public void setTitulo(String titulo) { panelGestionLibros.getTxtTitulo().setText(titulo); }
    public String getAutor() { return panelGestionLibros.getTxtAutor().getText(); }
    public void setAutor(String autor) { panelGestionLibros.getTxtAutor().setText(autor); }
    public String getGenero() { return panelGestionLibros.getTxtGenero().getText(); }
    public void setGenero(String genero) { panelGestionLibros.getTxtGenero().setText(genero); }
    public String getAño() { return panelGestionLibros.getTxtAño().getText(); }
    public void setAño(String año) { panelGestionLibros.getTxtAño().setText(año); }
    public void limpiarCampos() { panelGestionLibros.limpiarCampos(); } // Delega al panel

    // Getters de Botones
    public JButton getBtnAgregarInicio() { return panelGestionLibros.getBtnAgregarInicio(); }
    public JButton getBtnAgregarFinal() { return panelGestionLibros.getBtnAgregarFinal(); }
    public JButton getBtnEliminar() { return panelGestionLibros.getBtnEliminar(); }
    public JButton getBtnOrdenarTitulo() { return panelGestionLibros.getBtnOrdenarTitulo(); }
    public JButton getBtnOrdenarAutor() { return panelGestionLibros.getBtnOrdenarAutor(); }
    public JButton getBtnOrdenarAño() { return panelGestionLibros.getBtnOrdenarAño(); }
    
    
    public String getTerminoBusquedaUniversal() { return panelPrestamosVentas.getTerminoBusqueda(); }
    public JButton getBtnBuscar() { return panelPrestamosVentas.getBtnBuscar(); }
    public JButton getBtnSolicitarLibro() { return panelPrestamosVentas.getBtnSolicitarLibro(); }
    public JButton getBtnProcesarCola() { return panelPrestamosVentas.getBtnProcesarCola(); }
    public JButton getBtnVerCola() { return panelPrestamosVentas.getBtnVerCola(); }

    public JButton getBtnVerHistorial() { return panelHistorial.getBtnVerHistorial(); }
    public JButton getBtnVolverHistorial() { return panelHistorial.getBtnVolverHistorial(); }
    
    // Métodos para los diálogos
    public void mostrarHistorialEnPanel(String historialTexto) {
    panelHistorial.setHistorialTexto(historialTexto);
    
    // Busca el índice del panel y cambia si existe
    int index = tabbedPane.indexOfComponent(panelHistorial);
    if (index >= 0) {
        tabbedPane.setSelectedIndex(index);
    }
    }
    public void mostrarColaEnPanel(String colaTexto) {
        colaTextArea.setText(colaTexto);
        colaDialog.setVisible(true);
    }
    
    public void mostrarMensajeCola(String mensaje) {
        mostrarMensaje(mensaje);
        if (colaDialog.isVisible()) {
             colaTextArea.setText(mensaje);
        }
    }
}
