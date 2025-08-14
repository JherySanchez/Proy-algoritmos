/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.NodoArbolCategorias;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhery
 */
public class PanelCategorias extends JPanel {
    // Colores básicos
    private static final Color COLOR_FONDO = new Color(240, 240, 240);
    private static final Color COLOR_BORDE = new Color(200, 200, 200);
    
    private final JTree arbolCategorias;
    private final DefaultTreeModel arbolModelo;
    private DefaultMutableTreeNode raizVisible;
    private final JTable tablaLibrosCategoria;
    private final DefaultTableModel modeloTablaLibrosCategoria;

    public PanelCategorias() {
        setLayout(new BorderLayout());
        setBackground(COLOR_FONDO);
        setBorder(BorderFactory.createTitledBorder("Navegar por Categorías"));

        // Árbol de categorías
        raizVisible = new DefaultMutableTreeNode("Cargando Categorías...");
        arbolModelo = new DefaultTreeModel(raizVisible);
        arbolCategorias = new JTree(arbolModelo);
        arbolCategorias.setBackground(COLOR_FONDO);
        arbolCategorias.setRootVisible(true);
        
        JScrollPane scrollPane = new JScrollPane(arbolCategorias);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));

        // Tabla de libros
        String[] columnas = {"Título", "Autor", "Género", "Año", "Precio"};
        modeloTablaLibrosCategoria = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaLibrosCategoria = new JTable(modeloTablaLibrosCategoria);
        tablaLibrosCategoria.setBackground(COLOR_FONDO);
        tablaLibrosCategoria.setGridColor(COLOR_BORDE);
        
        JScrollPane scrollTabla = new JScrollPane(tablaLibrosCategoria);
        scrollTabla.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));

        // Panel dividido
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, scrollTabla);
        splitPane.setResizeWeight(0.4);
        add(splitPane, BorderLayout.CENTER);
    }

    // Métodos originales se mantienen exactamente igual
    public void actualizarArbol(NodoArbolCategorias nodoRaizDelModelo) {
        raizVisible = construirNodosJTreeRec(nodoRaizDelModelo);
        arbolModelo.setRoot(raizVisible);
        arbolModelo.nodeStructureChanged(raizVisible); 
        arbolCategorias.expandRow(0);
    }

    private DefaultMutableTreeNode construirNodosJTreeRec(Modelo.NodoArbolCategorias nodoModelo) {
        if(nodoModelo == null){
            return null;
        }
        DefaultMutableTreeNode nodoJTree = new DefaultMutableTreeNode(nodoModelo.getNombreCategoria());
        
        NodoArbolCategorias hijoActual = nodoModelo.getPrimerHijo();
        while(hijoActual != null){
            nodoJTree.add(construirNodosJTreeRec(hijoActual));
            hijoActual = hijoActual.getSiguienteHermano();
        }
        return nodoJTree;
    }

    public JTree getArbolCategorias() {
        return arbolCategorias;
    }

    public DefaultTableModel getModeloTablaLibrosCategoria() {
        return modeloTablaLibrosCategoria;
    }

    public void mostrarLibrosEnTablaCategoria(Object[][] datos) {
        modeloTablaLibrosCategoria.setRowCount(0);
        if (datos != null) {
            for (Object[] fila : datos) {
                modeloTablaLibrosCategoria.addRow(fila);
            }
        }
    }
}
