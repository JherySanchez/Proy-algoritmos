package Vista;

import javax.swing.*;
import java.awt.*;

public class PanelCarrito extends JPanel {
    // Colores básicos
    private static final Color COLOR_FONDO = new Color(240, 240, 240);
    private static final Color COLOR_BOTON = new Color(70, 130, 180); 
    
    private final JButton btnAgregarCarrito;
    private final JButton btnEliminarCarrito;
    private final JButton btnPagar;
    private final JButton btnVerCarrito;
    private final JTextField txtTitulo;
    private final JTextField txtCantidad;
    private final JTextArea areaCarrito;
    private final JLabel lblTotalVentas;

    public PanelCarrito() {
        setLayout(new BorderLayout(10, 10));
        setBackground(COLOR_FONDO);
        setBorder(BorderFactory.createTitledBorder("Carrito de Compras"));

        // Panel de entrada de datos
        JPanel panelEntrada = new JPanel(new GridLayout(3, 2, 5, 5));
        panelEntrada.setBackground(COLOR_FONDO);
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Agregar al Carrito"));
        
        panelEntrada.add(new JLabel("Título del libro:"));
        txtTitulo = new JTextField();
        panelEntrada.add(txtTitulo);
        
        panelEntrada.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField("1");
        panelEntrada.add(txtCantidad);
        
        btnAgregarCarrito = new JButton("Agregar al Carrito");
        btnAgregarCarrito.setBackground(COLOR_BOTON);
        btnAgregarCarrito.setForeground(Color.WHITE);
        
        btnEliminarCarrito = new JButton("Eliminar del Carrito");
        btnEliminarCarrito.setBackground(new Color(220, 80, 80)); // Rojo
        btnEliminarCarrito.setForeground(Color.WHITE);
        
        panelEntrada.add(btnAgregarCarrito);
        panelEntrada.add(btnEliminarCarrito);

        // Panel de botones principales
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(COLOR_FONDO);
        
        btnVerCarrito = new JButton("Ver Carrito");
        btnVerCarrito.setBackground(COLOR_BOTON);
        btnVerCarrito.setForeground(Color.WHITE);
        
        btnPagar = new JButton("Pagar Carrito");
        btnPagar.setBackground(COLOR_BOTON);
        btnPagar.setForeground(Color.WHITE);
        
        panelBotones.add(btnVerCarrito);
        panelBotones.add(btnPagar);

        // Panel de información
        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBackground(COLOR_FONDO);
        
        areaCarrito = new JTextArea(10, 30);
        areaCarrito.setEditable(false);
        areaCarrito.setBackground(COLOR_FONDO);
        JScrollPane scrollCarrito = new JScrollPane(areaCarrito);
        
        lblTotalVentas = new JLabel("Total de ventas: $0.00", JLabel.CENTER);
        lblTotalVentas.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotalVentas.setBorder(BorderFactory.createTitledBorder("Historial de Ventas"));
        
        panelInfo.add(scrollCarrito, BorderLayout.CENTER);
        panelInfo.add(lblTotalVentas, BorderLayout.SOUTH);

        // Organización de los paneles
        add(panelEntrada, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(panelInfo, BorderLayout.SOUTH);
    }

    // Todos los métodos originales se mantienen exactamente igual
    public JButton getBtnAgregarCarrito() { return btnAgregarCarrito; }
    public JButton getBtnEliminarCarrito() { return btnEliminarCarrito; }
    public JButton getBtnPagar() { return btnPagar; }
    public JButton getBtnVerCarrito() { return btnVerCarrito; }
    public String getTitulo() { return txtTitulo.getText(); }
    public int getCantidad() { 
        try {
            return Integer.parseInt(txtCantidad.getText());
        } catch (NumberFormatException e) {
            return 1;
        }
    }
    
    public void mostrarCarrito(String contenido) {
        areaCarrito.setText(contenido);
    }
    
    public void mostrarTotalVentas(double total) {
        lblTotalVentas.setText(String.format("Total de ventas: $%.2f", total));
    }
    
    public void limpiarCampos() {
        txtTitulo.setText("");
        txtCantidad.setText("1");
    }
}