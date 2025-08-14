/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.*;
import Vista.VistaBiblioteca;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class ControladorBiblioteca {
    private final VistaBiblioteca vista;
    private final ListaLibros listaLibros;
    private final PilaLibros historialVistas;
    private final ColaLibros colaPrestamos;
    private final ArbolCategorias arbolCategorias;
    private final TableRowSorter<javax.swing.table.DefaultTableModel> sorter;
    private final ArbolBinarioLibros arbolBinarioLibrosTab;
    private final JTable tablaLibros;
    private final DefaultTableModel modeloTablaLibros;
    

    public ControladorBiblioteca(VistaBiblioteca vista) {
        this.vista = vista;
        this.listaLibros = new ListaLibros();
        this.historialVistas = new PilaLibros();
        this.colaPrestamos = new ColaLibros();
        this.sorter = new TableRowSorter<>((javax.swing.table.DefaultTableModel) vista.getTabla().getModel());
        this.arbolCategorias = new ArbolCategorias();
        this.arbolBinarioLibrosTab = new ArbolBinarioLibros();
        this.tablaLibros = vista.getTabla();
        this.modeloTablaLibros = (DefaultTableModel) tablaLibros.getModel();

        inicializarCategorias();
        cargarLibrosIniciales();
        configurarListeners();

        ControladorCarrito controladorCarrito = new ControladorCarrito(vista, listaLibros);

        vista.getPanelCicloLectura().setListaDisponible(listaLibros);

    }

    private void inicializarCategorias() {
        arbolCategorias.agregarCategoria(null, "Ficción");
        arbolCategorias.agregarCategoria("Ficción", "Ciencia ficción");
        arbolCategorias.agregarCategoria("Ciencia ficción", "Distopías");
        arbolCategorias.agregarCategoria("Ciencia ficción", "Óperas Espaciales");
        arbolCategorias.agregarCategoria("Ficción", "Fantasía");
        arbolCategorias.agregarCategoria("Fantasía", "Fantasía épica");
        arbolCategorias.agregarCategoria("Fantasía", "Fantasía Urbana");
        arbolCategorias.agregarCategoria(null, "No Ficción");
        arbolCategorias.agregarCategoria("No Ficción", "Historia");
        arbolCategorias.agregarCategoria("No Ficción", "Ciencia");
        arbolCategorias.agregarCategoria("No Ficción", "Biografía");
        arbolCategorias.agregarCategoria(null, "Otros Géneros");
    }

    private void cargarLibrosIniciales() {
        listaLibros.agregarAlFinal(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "Novela", 1605, 25.99));
        listaLibros.agregarAlFinal(new Libro("Cien años de soledad", "Gabriel García Márquez", "Realismo mágico", 1967, 19.99));
        listaLibros.agregarAlFinal(new Libro("1984", "George Orwell", "Ciencia ficción", 1949, 15.50));
        listaLibros.agregarAlFinal(new Libro("Orgullo y prejuicio", "Jane Austen", "Romance", 1813, 18.75));
        listaLibros.agregarAlFinal(new Libro("Moby Dick", "Herman Melville", "Aventura", 1851, 22.00));
        listaLibros.agregarAlFinal(new Libro("El nombre del viento", "Patrick Rothfuss", "Fantasía", 2007, 27.50));
        listaLibros.agregarAlFinal(new Libro("Los juegos del hambre", "Suzanne Collins", "Ciencia ficción", 2008, 21.99));
        listaLibros.agregarAlFinal(new Libro("El código Da Vinci", "Dan Brown", "Suspense", 2003, 20.50));
        listaLibros.agregarAlFinal(new Libro("La sombra del viento", "Carlos Ruiz Zafón", "Misterio", 2001, 23.25));
        listaLibros.agregarAlFinal(new Libro("El alquimista", "Paulo Coelho", "Novela filosófica", 1988, 17.99));
        listaLibros.agregarAlFinal(new Libro("Dune", "Frank Herbert", "Ciencia ficción", 1965, 24.99));
        listaLibros.agregarAlFinal(new Libro("El señor de los anillos", "J.R.R. Tolkien", "Fantasía épica", 1954, 29.99));
        listaLibros.agregarAlFinal(new Libro("Fahrenheit 451", "Ray Bradbury", "Ciencia ficción", 1953, 16.75));
        listaLibros.agregarAlFinal(new Libro("Ready Player One", "Ernest Cline", "Ciencia ficción", 2011, 22.50));
        listaLibros.agregarAlFinal(new Libro("Crónicas marcianas", "Ray Bradbury", "Ciencia ficción", 1950, 18.99));
        listaLibros.agregarAlFinal(new Libro("Sapiens: De animales a dioses", "Yuval Noah Harari", "Historia", 2011, 26.50));
        listaLibros.agregarAlFinal(new Libro("El hombre en busca de sentido", "Viktor Frankl", "Psicología", 1946, 15.99));
        listaLibros.agregarAlFinal(new Libro("Steve Jobs", "Walter Isaacson", "Biografía", 2011, 23.75));
        listaLibros.agregarAlFinal(new Libro("Breves respuestas a las grandes preguntas", "Stephen Hawking", "Ciencia", 2018, 20.99));
        listaLibros.agregarAlFinal(new Libro("Los 7 hábitos de la gente altamente efectiva", "Stephen Covey", "Autoayuda", 1989, 19.50));

        asegurarCategoriasIniciales(listaLibros);
        arbolCategorias.reconstruirArbol(listaLibros);
        //Para que se muestren las categorias
        actualizarTabla();
        vista.getPanelCategorias().actualizarArbol(arbolCategorias.getRaiz());
    }

    private void configurarListeners() {
        vista.getBtnAgregarInicio().addActionListener(e -> agregarLibroAlInicio());
        vista.getBtnAgregarFinal().addActionListener(e -> agregarLibroAlFinal());
        vista.getBtnOrdenarTitulo().addActionListener(e -> {
            listaLibros.ordenarPorTituloBurbuja();
            actualizarTabla();
        });
        vista.getBtnOrdenarAutor().addActionListener(e -> {
            listaLibros.ordenarPorAutorMerge();
            actualizarTabla();
        });
        vista.getBtnOrdenarAño().addActionListener(e -> {
            listaLibros.ordenarPorAñoBurbuja();
            actualizarTabla();
        });
        vista.getBtnBuscar().addActionListener(e -> buscarLibro());
        vista.getBtnEliminar().addActionListener(e -> eliminarLibro());
        
        //Listeners PILA
        vista.getBtnVerHistorial().addActionListener(e -> mostrarHistorialVistas());
        vista.getBtnVolverHistorial().addActionListener(e -> volverHistorial());
        
        // Listener para la seleccion de fila en la tabla
        
        vista.getTabla().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = vista.getTabla().getSelectedRow();
                if (selectedRow != -1) { 
                    // Convertir el índice de vista a indice de modelo si se usa un sorter
                    int modelRow = vista.getTabla().convertRowIndexToModel(selectedRow);
                    String tituloSeleccionado = (String) vista.getTabla().getModel().getValueAt(modelRow, 0);
                    Libro libroSeleccionado = obtenerLibroPorTitulo(tituloSeleccionado);
                    if (libroSeleccionado != null) {
                        if (historialVistas.isEmpty() || !historialVistas.peek().equals(libroSeleccionado)) {
                            historialVistas.push(libroSeleccionado);
                            vista.mostrarMensaje("Libro añadido al historial: " + libroSeleccionado.getTitulo());
                            actualizarPanelHistorial();
                        }
                    }
                }
            }
        });
        
        //Listeners COLA
        vista.getBtnBuscar().addActionListener(e -> buscarLibroUniversal());
        vista.getBtnSolicitarLibro().addActionListener(e -> solicitarLibro());
        vista.getBtnProcesarCola().addActionListener(e -> procesarSiguienteSolicitud());
        vista.getBtnVerCola().addActionListener(e -> mostrarColaPrestamos());
        
        //Listeners Jtree
        vista.getPanelCategorias().getArbolCategorias().addTreeSelectionListener((TreeSelectionEvent e) -> {
            JTree arbol = vista.getPanelCategorias().getArbolCategorias();
            DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();
            if (nodoSeleccionado != null) {
                String nombreCategoria = (String) nodoSeleccionado.getUserObject();
                filtrarLibrosPorCategoria(nombreCategoria);
                // Expande la rama seleccionada
                int row = arbol.getSelectionRows() != null && arbol.getSelectionRows().length > 0 ? arbol.getSelectionRows()[0] : -1;
                if (row >= 0) {
                    arbol.expandRow(row);
                }
            }
        });
        
        //Listeners ArbolBinario
        // Botón para agregar libro al árbol
        vista.getPanelArbolBinario().getBtnAgregarLibroArbol().addActionListener(e -> agregarLibroAlArbol()); // <--- NUEVA LÍNEA
        // Botón para buscar libro en el árbol
        vista.getPanelArbolBinario().getBtnBuscarLibroArbol().addActionListener(e -> buscarLibroEnArbol()); // <--- NUEVA LÍNEA
        // Botón para mostrar todos los libros en orden
        vista.getPanelArbolBinario().getBtnMostrarTodosArbol().addActionListener(e -> mostrarTodosLosLibrosDelArbol()); // <--- NUEVA LÍNEA

 
    }
    
 
    private void agregarLibroAlInicio() {
        try {
            String titulo = vista.getTitulo();
            String autor = vista.getAutor();
            String genero = vista.getGenero();
            String añoStr = vista.getAño();

            if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty() || añoStr.isEmpty()) {
                vista.mostrarMensaje("Todos los campos son obligatorios");
                return;
            }
            
            int año = Integer.parseInt(añoStr);
            Libro nuevoLibro = new Libro(titulo, autor, genero, año);
            
            if (arbolCategorias.buscarNodoPorNombre(arbolCategorias.getRaiz(),genero) == null) {
                if (arbolCategorias.buscarNodoPorNombre(arbolCategorias.getRaiz(),"Otros Géneros") != null) {
                    arbolCategorias.agregarCategoria("Otros Géneros", genero);
                } else {
                    arbolCategorias.agregarCategoria(null, genero);
                }
            }

            listaLibros.agregarAlInicio(nuevoLibro);
            arbolCategorias.reconstruirArbol(listaLibros); // Reconstruye el arbol y asigna libros
            vista.getPanelCategorias().actualizarArbol(arbolCategorias.getRaiz()); // Actualiza vista del arbol
            vista.limpiarCampos();
            actualizarTabla();
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("El año debe ser un numero valido");
        }
    }

    private void agregarLibroAlFinal() {
        try {
            String titulo = vista.getTitulo();
            String autor = vista.getAutor();
            String genero = vista.getGenero();
            String añoStr = vista.getAño();

            if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty() || añoStr.isEmpty()) {
                vista.mostrarMensaje("Todos los campos son obligatorios");
                return;
            }

            int año = Integer.parseInt(añoStr);
            Libro nuevoLibro = new Libro(titulo, autor, genero, año);
            
            if (arbolCategorias.buscarNodoPorNombre(arbolCategorias.getRaiz(),genero) == null) {
                if (arbolCategorias.buscarNodoPorNombre(arbolCategorias.getRaiz(),"Otros Géneros") != null) {
                    arbolCategorias.agregarCategoria("Otros Géneros", genero);
                } else {
                    arbolCategorias.agregarCategoria(null, genero);
                }
            }

            listaLibros.agregarAlFinal(nuevoLibro);
            arbolCategorias.reconstruirArbol(listaLibros);
            vista.getPanelCategorias().actualizarArbol(arbolCategorias.getRaiz());
            vista.limpiarCampos();
            actualizarTabla();
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("El año debe ser un numero valido");
        }
    }

    private void buscarLibro() {
        String titulo = vista.getTitulo();

        if (titulo.isEmpty()) {
            vista.mostrarMensaje("Ingrese un titulo para buscar");
            return;
        }

        int indice = listaLibros.buscarPorTitulo(titulo); // Esto devuelve el indice

        if (indice >= 0) {
            vista.getTabla().setRowSelectionInterval(indice, indice);
            vista.mostrarMensaje("Libro encontrado en la posición " + (indice + 1));

            // Aqui se añade el libro a la pila
            Libro libroEncontrado = obtenerLibroPorIndice(indice);
            if (libroEncontrado != null) {
                historialVistas.push(libroEncontrado);
                vista.mostrarMensaje("Libro '" + libroEncontrado.getTitulo() + "' añadido al historial de vistas.");
                actualizarPanelHistorial();
            }
        } else {
            vista.mostrarMensaje("Libro no encontrado");
        }
    }
    
    private void eliminarLibro() {
        String titulo = vista.getTitulo();
        if (titulo.isEmpty()) {
            vista.mostrarMensaje("Ingrese el titulo del libro a eliminar");
            return;
        }

        boolean eliminado = listaLibros.eliminar(titulo);
        if (eliminado) {
            arbolCategorias.reconstruirArbol(listaLibros);
            vista.getPanelCategorias().actualizarArbol(arbolCategorias.getRaiz());
            vista.mostrarMensaje("Libro eliminado correctamente");
            vista.limpiarCampos();
            actualizarTabla();
        } else {
            vista.mostrarMensaje("No se encontro el libro con ese título");
        }
    }
    
    private void buscarLibroUniversal() {
        String termino = vista.getTerminoBusquedaUniversal().trim();
        
        if (termino.isEmpty()) {
            sorter.setRowFilter(null); // Si el campo esta vacio, quita el filtro y muestra todo
            vista.mostrarMensaje("Mostrando todos los libros.");
            return;
        }

        // Crea un RowFilter que compare el termino con cualquier columna
        RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
            @Override
            public boolean include(RowFilter.Entry<?, ?> entry) {
                // Iterar sobre las columnas de la fila actual
                for (int i = 0; i < entry.getValueCount(); i++) {
                    // Convertir el valor a String y a minusculas
                    String cellValue = entry.getStringValue(i).toLowerCase();
                    if (cellValue.contains(termino.toLowerCase())) {
                                    
                        return true;
                    }
                }
                return false; // Si ninguna celda contiene el termino, excluir esta fila
            }
        };
        
        sorter.setRowFilter(rf); // Aplicar el filtro a la tabla
        
        if (vista.getTabla().getRowCount() > 0) { // Si hay filas visibles despues del filtro
            vista.mostrarMensaje("Libros encontrados que coinciden con '" + termino + "'.");
            
            vista.getTabla().setRowSelectionInterval(0, 0); 
                       
        } else {
            vista.mostrarMensaje("No se encontraron libros que coincidan con '" + termino + "'.");
        }
    }
    
    public void actualizarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.getTabla().getModel();
        // Limpiar todas las filas
        modelo.setRowCount(0);
        NodoLibro actual = listaLibros.getCabeza();
        while (actual != null) {
            Libro libro = actual.getLibro();
            Object[] fila = new Object[] {
                libro.getTitulo(),
                libro.getAutor(),
                libro.getGenero(),
                libro.getAñoPublicacion(),
                String.format("$%.2f", libro.getPrecio())
            };
            modelo.addRow(fila);
            actual = actual.getSiguiente();
        }
    }
    
    private Libro obtenerLibroPorIndice(int indice) {
        if (indice < 0) return null;
        NodoLibro actual = listaLibros.getCabeza();
        int i = 0;
        while (actual != null && i < indice) {
            actual = actual.getSiguiente();
            i++;
        }
        return (actual != null) ? actual.getLibro() : null;
    }
    
    private void mostrarHistorialVistas() {
        actualizarPanelHistorial();
    }

    private void volverHistorial() {
        if (historialVistas.isEmpty()) {
            vista.mostrarMensaje("No hay historial previo para volver.");
            vista.limpiarCampos();
            actualizarPanelHistorial();
            return;
        }
        
        if (historialVistas.size() == 1) {
            historialVistas.pop(); // Saca el último libro del historial
            vista.mostrarMensaje("Se ha agotado el historial de vistas.");
            vista.limpiarCampos();
            actualizarTabla();
            actualizarPanelHistorial();
            return;
        }

        // Sacar el libro actual
        historialVistas.pop();
        // El nuevo tope de la pila es el libro anterior
        Libro libroAnterior = historialVistas.peek();

        if (libroAnterior != null) {
            // Rellenar los campos de la vista con los datos del libro anterior
            vista.setTitulo(libroAnterior.getTitulo());
            vista.setAutor(libroAnterior.getAutor());
            vista.setGenero(libroAnterior.getGenero());
            vista.setAño(String.valueOf(libroAnterior.getAñoPublicacion()));
            vista.mostrarMensaje("Volviendo a: " + libroAnterior.getTitulo());

            // Selecciona la fila en la tabla
            int indiceAnterior = listaLibros.buscarPorTitulo(libroAnterior.getTitulo());
            if (indiceAnterior >= 0) {
                vista.getTabla().setRowSelectionInterval(indiceAnterior, indiceAnterior);
            }
        }
        actualizarPanelHistorial();
    }
    
    
    //Metodos de la COLA
    private void solicitarLibro() {
        String tituloSolicitado = vista.getTerminoBusquedaUniversal();
        if (tituloSolicitado.isEmpty()) {
            vista.mostrarMensaje("Ingrese el titulo del libro a solicitar.");
            return;
        }

        Libro libro = obtenerLibroPorTitulo(tituloSolicitado);
        if (libro != null) {
            colaPrestamos.enqueue(libro);
            vista.mostrarMensaje("'" + libro.getTitulo() + "' ha sido añadido a la cola de prestamos");
            vista.getTabla().clearSelection();//Limpia la seleccion
        } else {
            vista.mostrarMensaje("Libro no encontrado en el catalogo para solicitar");
        }
    }
    
    //Metodo auxiliar
    private Libro obtenerLibroPorTitulo(String titulo) {
        NodoLibro actual = listaLibros.getCabeza();
        while (actual != null) {
            if (actual.getLibro().getTitulo().equalsIgnoreCase(titulo)) {
                return actual.getLibro();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    
    private void procesarSiguienteSolicitud() {
        if (!colaPrestamos.isEmpty()) {
            Libro libroAtendido = colaPrestamos.dequeue();
            vista.mostrarMensaje("Se ha procesado la solicitud de: " + libroAtendido.getTitulo() + ". ¡Ya esta disponible!");
            mostrarColaPrestamos(); // Actualiza la vista de la cola
        } else {
            vista.mostrarMensaje("La cola de prestamos esta vacia. No hay solicitudes pendientes.");
        }
    }
    
    private void mostrarColaPrestamos() {
        if (colaPrestamos.isEmpty()) {
            vista.mostrarMensajeCola("La cola de prestamos está vacia.");
            return;
        }

        // Para mostrar la cola sin destruirla, hacemos una copia temporal
        StringBuilder sb = new StringBuilder();
        int contador = 1;
        ColaLibros tempCola = new ColaLibros(); // Cola temporal

        while (!colaPrestamos.isEmpty()) {
            Libro l = colaPrestamos.dequeue(); // Saca de la cola original
            sb.append(contador++).append(". ").append(l.getTitulo()).append("\n");
            tempCola.enqueue(l); // Añade a la cola temporal
        }
        // Restaura la cola original
        while (!tempCola.isEmpty()) {
            colaPrestamos.enqueue(tempCola.dequeue());
        }

        vista.mostrarColaEnPanel(sb.toString());
    }
    
    // Metodo para filtrar la tabla
    private void filtrarLibrosPorCategoria(String nombreCategoria) {
        sorter.setRowFilter(null);
        if (nombreCategoria.equals("Todas las Categorías")) {
            actualizarTabla(); // Si se selecciona la raiz, mostrar todos los libros
            vista.getPanelCategorias().mostrarLibrosEnTablaCategoria(null); // Limpiar tabla de categorías
            vista.mostrarMensaje("Mostrando todos los libros.");
            return;
        }

        ListaLibros librosFiltrados = arbolCategorias.obtenerLibrosCategoria(nombreCategoria);
        vista.getTablaModelo().setRowCount(0);

        NodoLibro actual = librosFiltrados.getCabeza();
        boolean hayLibros = false;
        java.util.List<Object[]> filas = new java.util.ArrayList<>();
        while (actual != null) {
            Libro libro = actual.getLibro();
            Object[] fila = new Object[]{
                libro.getTitulo(),
                libro.getAutor(),
                libro.getGenero(),
                libro.getAñoPublicacion(),
                String.format("$%.2f", libro.getPrecio())
            };
            vista.getTablaModelo().addRow(fila);
            filas.add(fila);
            hayLibros = true;
            actual = actual.getSiguiente();
        }

        // Actualizar la tabla de PanelCategorias
        vista.getPanelCategorias().mostrarLibrosEnTablaCategoria(filas.isEmpty() ? null : filas.toArray(new Object[0][]));

        if (!hayLibros) {
        } else {
            vista.mostrarMensaje("Mostrando libros de la categoría '" + nombreCategoria + "'.");
        }
    }
    
    private void asegurarCategoriasIniciales(ListaLibros libros) {
        
        NodoLibro actual = libros.getCabeza();
        while (actual != null) {
            String genero = actual.getLibro().getGenero();
            if (arbolCategorias.buscarNodoPorNombre(arbolCategorias.getRaiz(), genero) == null) {
                if (arbolCategorias.buscarNodoPorNombre(arbolCategorias.getRaiz(),"Otros Géneros") != null) {
                    arbolCategorias.agregarCategoria("Otros Géneros", genero);
                } else {
                    arbolCategorias.agregarCategoria(null, genero); // Añadirla directamente a la raiz
                }
            }
            actual = actual.getSiguiente();
        }
    }
        private void agregarLibroAlArbol() {
        try {
            String titulo = vista.getPanelArbolBinario().getTxtTituloArbol().getText();
            String autor = vista.getPanelArbolBinario().getTxtAutorArbol().getText();
            String genero = vista.getPanelArbolBinario().getTxtGeneroArbol().getText();
            int año = Integer.parseInt(vista.getPanelArbolBinario().getTxtAñoArbol().getText());

            if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty()) {
                vista.mostrarMensaje("Por favor, rellene todos los campos para agregar un libro al árbol.");
                return;
            }

            Libro nuevoLibro = new Libro(titulo, autor, genero, año);
            arbolBinarioLibrosTab.insertar(nuevoLibro);
            vista.mostrarMensaje("Libro '" + titulo + "' agregado al árbol binario.");
            vista.getPanelArbolBinario().limpiarCampos();
            mostrarTodosLosLibrosDelArbol();
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("El año de publicación debe ser un número válido.");
        }
    }
        
            private void buscarLibroEnArbol() {
        String tituloBusqueda = vista.getPanelArbolBinario().getTxtBuscarTituloArbol().getText();
        if (tituloBusqueda.isEmpty()) {
            vista.mostrarMensaje("Por favor, ingrese un título para buscar en el árbol.");
            return;
        }

        Libro encontrado = arbolBinarioLibrosTab.buscar(tituloBusqueda);
        if (encontrado != null) {
            vista.getPanelArbolBinario().setAreaResultadosArbol(
                """
                Libro encontrado:
                T\u00edtulo: """ + encontrado.getTitulo() + "\n" +
                "Autor: " + encontrado.getAutor() + "\n" +
                "Género: " + encontrado.getGenero() + "\n" +
                "Año: " + encontrado.getAñoPublicacion()
            );
            vista.mostrarMensaje("Libro encontrado en el árbol binario.");
        } else {
            vista.getPanelArbolBinario().setAreaResultadosArbol("Libro con título '" + tituloBusqueda + "' no encontrado en el árbol.");
            vista.mostrarMensaje("Libro no encontrado.");
        }
    }
        private void mostrarTodosLosLibrosDelArbol() {
        ListaLibros librosOrdenados = arbolBinarioLibrosTab.obtenerLibrosInorden();
        StringBuilder sb = new StringBuilder();
        NodoLibro actual = librosOrdenados.getCabeza();
        while (actual != null) {
            Libro libro = actual.getLibro();
            sb.append("Título: ").append(libro.getTitulo())
              .append(", Autor: ").append(libro.getAutor())
              .append(", Género: ").append(libro.getGenero())
              .append(", Año: ").append(libro.getAñoPublicacion())
              .append(", Precio: $").append(String.format("%.2f", libro.getPrecio()))
              .append("\n");
            actual = actual.getSiguiente();
        }
        vista.getPanelArbolBinario().setAreaResultadosArbol(sb.toString());
        vista.mostrarMensaje("Mostrando libros del árbol binario (orden In-Orden).");
    }
        
        //actualizar panel historial
    private void actualizarPanelHistorial() {
        StringBuilder sb = new StringBuilder();
        if (historialVistas.isEmpty()) {
            sb.append("El historial de vistas está vacío\n");
        } else {
            PilaLibros tempPila = new PilaLibros();
            java.util.List<Libro> libros = new java.util.ArrayList<>();
            
            while (!historialVistas.isEmpty()) {
                Libro l = historialVistas.pop();
                libros.add(l);
                tempPila.push(l);
            }
            while (!tempPila.isEmpty()) {
                historialVistas.push(tempPila.pop());
            }
            int contador = 1;
            for (Libro l : libros) {
                sb.append(contador++).append(". ").append(l.getTitulo()).append("\n");
            }
        }
        vista.mostrarHistorialEnPanel(sb.toString());
    }
}
