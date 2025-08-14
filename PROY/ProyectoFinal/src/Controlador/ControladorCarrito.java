package Controlador;

import Modelo.ListaDobleCarrito;
import Modelo.Libro;
import Modelo.ListaLibros;
import Modelo.NodoLibro;
import Vista.VistaBiblioteca;

public class ControladorCarrito {
    private final ListaDobleCarrito carrito;
    private final ListaLibros listaLibros;
    private final VistaBiblioteca vista;

    public ControladorCarrito(VistaBiblioteca vista, ListaLibros listaLibros) {
        this.vista = vista;
        this.listaLibros = listaLibros;
        this.carrito = new ListaDobleCarrito();
        
        configurarListeners();
    }

    private void configurarListeners() {
        vista.getPanelCarrito().getBtnAgregarCarrito().addActionListener(e -> agregarAlCarrito());
        vista.getPanelCarrito().getBtnEliminarCarrito().addActionListener(e -> eliminarDelCarrito());
        vista.getPanelCarrito().getBtnPagar().addActionListener(e -> pagarCarrito());
        vista.getPanelCarrito().getBtnVerCarrito().addActionListener(e -> verCarrito());
    }

    private void agregarAlCarrito() {
        String titulo = vista.getPanelCarrito().getTitulo();
        int cantidad = vista.getPanelCarrito().getCantidad();
        
        if (titulo.isEmpty()) {
            vista.mostrarMensaje("Ingrese el título del libro");
            return;
        }
        Libro libro = buscarLibro(titulo);
        if (libro != null) {
            carrito.agregarItem(libro, cantidad);
            vista.mostrarMensaje("Libro agregado al carrito: " + titulo);
            vista.getPanelCarrito().limpiarCampos();
        } else {
            vista.mostrarMensaje("Libro no encontrado: " + titulo);
        }
    }

    private void eliminarDelCarrito() {
        String titulo = vista.getPanelCarrito().getTitulo();
        
        if (titulo.isEmpty()) {
            vista.mostrarMensaje("Ingrese el título del libro a eliminar");
            return;
        }
        if (carrito.eliminarItem(titulo)) {
            vista.mostrarMensaje("Libro eliminado del carrito: " + titulo);
            vista.getPanelCarrito().limpiarCampos();
        } else {
            vista.mostrarMensaje("Libro no encontrado en el carrito: " + titulo);
        }
    }

    private void pagarCarrito() {
        if (carrito.procesarPago()) {
            vista.mostrarMensaje("Pago realizado con éxito! Total: $" + String.format("%.2f", carrito.getTotalVentas()));
            vista.getPanelCarrito().mostrarTotalVentas(carrito.getTotalVentas());
        } else {
            vista.mostrarMensaje("El carrito está vacío. No se puede procesar el pago.");
        }
    }

    private void verCarrito() {
        vista.getPanelCarrito().mostrarCarrito(carrito.obtenerItemsFormateados());
    }

    private Libro buscarLibro(String titulo) {
        NodoLibro actual = listaLibros.getCabeza();
        while (actual != null) {
            if (actual.getLibro().getTitulo().equalsIgnoreCase(titulo)) {
                return actual.getLibro();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
}