/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.ProductoController;
import DTO.ProductoDTO;
import DTO.UsuarioDTO;
import DTO.VentaDTO;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import java.net.URL;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JETTSUSHD
 */
public class ProductoView extends javax.swing.JFrame {

    /**
     * Creates new form ProductoView
     */
    private UsuarioDTO usuario;
    private ProductoController productoController;
    private int paginaActual = 0;
    private String filtroNombre = null;
    private Double filtroPrecioMaximo = null;
    private Integer filtroStockMaximo = null;
    
    private HelpSet helpSet;
    private HelpBroker helpBroker;

    public ProductoView(UsuarioDTO usuario) {
        this.usuario = usuario;
        this.productoController = new ProductoController();

        initComponents();
        initHelp();
        ejecutarF1();
        
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                dispose();
                new MainView2(usuario).setVisible(true);
            }
        });

        setTitle("Ventana principal");
        inicializarTabla();
        keyBinding();
        
        setResizable(false);
    }

    public void keyBinding() {
        // Acción para aplicar los filtros cuando se presiona ENTER
        Action login = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bAplicarFiltro.doClick(); // Simula un click en aplicar filtro
            }
        };

        // Añadir keybinding para la tecla ENTER para aplicar los filtros
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "APLICARFILTRO");
        rootPane.getActionMap().put("APLICARFILTRO", login);

        requestFocusInWindow();
        setFocusable(true);
    }

   

    private void initHelp() {
        try {
            // Obtener el recurso desde el classpath
            ClassLoader cl = this.getClass().getClassLoader();
            URL hsURL = cl.getResource("help/help_set.hs");

            if (hsURL == null) {
                System.out.println("No se encontró el archivo de ayuda en el classpath.");
                return;
            }

            System.out.println("Archivo de ayuda encontrado en: " + hsURL);

            // Cargar el HelpSet
            helpSet = new HelpSet(cl, hsURL);
            helpBroker = helpSet.createHelpBroker();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ejecutarF1() {
        KeyStroke f1KeyStroke = KeyStroke.getKeyStroke("F1");
        Action helpAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miAyudaActionPerformed(null); // Ejecuta el mismo evento del botón
            }
        };

        // Asignar la acción al panel principal (ventana) para que funcione en cualquier parte
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f1KeyStroke, "help");
        this.getRootPane().getActionMap().put("help", helpAction);
    }

    private void inicializarTabla() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Nombre", "Precio", "Stock"}, 0
        );
        tablaProductos.setModel(model);
        cargarPagina(0);
        bAnterior.setEnabled(false);
    }

    private void cargarPagina(int pagina) {
        try {
            List<ProductoDTO> productos;
            if (filtroNombre != null || filtroPrecioMaximo != null || filtroStockMaximo != null) {
                productos = productoController.filtrarProductos(filtroNombre, filtroPrecioMaximo, filtroStockMaximo, pagina);
            } else {
                productos = productoController.obtenerProductos(pagina);
            }

            DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
            model.setRowCount(0);

            for (ProductoDTO producto : productos) {
                model.addRow(new Object[]{
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getStock()
                });
            }

            lPagina.setText("Página: " + (pagina + 1));
            bAnterior.setEnabled(pagina > 0);
            bSiguiente.setEnabled(productos.size() == 10); // Asume 10 productos por página
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTabla(List<ProductoDTO> productos) {
        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
        model.setRowCount(0); // Limpiar la tabla

        for (ProductoDTO producto : productos) {
            model.addRow(new Object[]{
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock()
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pFiltros = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfPrecio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfStock = new javax.swing.JTextField();
        bAplicarFiltro = new javax.swing.JButton();
        bLimpiar = new javax.swing.JButton();
        bEliminar = new javax.swing.JButton();
        bGestionInforme = new javax.swing.JButton();
        pTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        pPaginado = new javax.swing.JPanel();
        bAnterior = new javax.swing.JButton();
        lPagina = new javax.swing.JLabel();
        bSiguiente = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mArchivo = new javax.swing.JMenu();
        miVolver = new javax.swing.JMenuItem();
        miAnadir = new javax.swing.JMenuItem();
        miEditar = new javax.swing.JMenuItem();
        mAyuda = new javax.swing.JMenu();
        miAyuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pFiltros.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Nombre:");
        pFiltros.add(jLabel1, new java.awt.GridBagConstraints());

        tfNombre.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfNombre.setPreferredSize(new java.awt.Dimension(200, 44));
        tfNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNombreActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 0, 0);
        pFiltros.add(tfNombre, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Precio máximo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 0, 0);
        pFiltros.add(jLabel2, gridBagConstraints);

        tfPrecio.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfPrecio.setPreferredSize(new java.awt.Dimension(200, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 0, 0);
        pFiltros.add(tfPrecio, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Stock máximo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 0, 0);
        pFiltros.add(jLabel3, gridBagConstraints);

        tfStock.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfStock.setPreferredSize(new java.awt.Dimension(200, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 0, 0);
        pFiltros.add(tfStock, gridBagConstraints);

        bAplicarFiltro.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bAplicarFiltro.setText("Aplicar filtros");
        bAplicarFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAplicarFiltroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 0, 0);
        pFiltros.add(bAplicarFiltro, gridBagConstraints);

        bLimpiar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bLimpiar.setText("Limpiar filtros");
        bLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLimpiarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 0, 0);
        pFiltros.add(bLimpiar, gridBagConstraints);

        bEliminar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bEliminar.setText("Eliminar");
        bEliminar.setPreferredSize(new java.awt.Dimension(228, 39));
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 10, 19);
        pFiltros.add(bEliminar, gridBagConstraints);

        bGestionInforme.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bGestionInforme.setText("Informe productos");
        bGestionInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGestionInformeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 10, 0);
        pFiltros.add(bGestionInforme, gridBagConstraints);

        getContentPane().add(pFiltros, java.awt.BorderLayout.PAGE_START);

        pTabla.setLayout(new java.awt.BorderLayout());

        tablaProductos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaProductos.setRowHeight(60);
        jScrollPane1.setViewportView(tablaProductos);

        pTabla.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pPaginado.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 15));

        bAnterior.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bAnterior.setText("Anterior");
        bAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnteriorActionPerformed(evt);
            }
        });
        pPaginado.add(bAnterior);

        lPagina.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lPagina.setText("Pagina: ");
        pPaginado.add(lPagina);

        bSiguiente.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bSiguiente.setText("Siguiente");
        bSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSiguienteActionPerformed(evt);
            }
        });
        pPaginado.add(bSiguiente);

        pTabla.add(pPaginado, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pTabla, java.awt.BorderLayout.CENTER);

        mArchivo.setText("Archivo");
        mArchivo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        miVolver.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        miVolver.setText("Volver");
        miVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miVolverActionPerformed(evt);
            }
        });
        mArchivo.add(miVolver);

        miAnadir.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        miAnadir.setText("Añadir producto");
        miAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAnadirActionPerformed(evt);
            }
        });
        mArchivo.add(miAnadir);

        miEditar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        miEditar.setText("Editar producto");
        miEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEditarActionPerformed(evt);
            }
        });
        mArchivo.add(miEditar);

        jMenuBar1.add(mArchivo);

        mAyuda.setText("Ayuda");
        mAyuda.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        miAyuda.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        miAyuda.setText("Acerca de");
        miAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAyudaActionPerformed(evt);
            }
        });
        mAyuda.add(miAyuda);

        jMenuBar1.add(mAyuda);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNombreActionPerformed

    private void bAplicarFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAplicarFiltroActionPerformed
        try {
            filtroNombre = tfNombre.getText().isEmpty() ? null : tfNombre.getText();
            filtroPrecioMaximo = tfPrecio.getText().isEmpty() ? null : Double.parseDouble(tfPrecio.getText());
            filtroStockMaximo = tfStock.getText().isEmpty() ? null : Integer.parseInt(tfStock.getText());

            paginaActual = 0;
            cargarPagina(paginaActual);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Precio o stock inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bAplicarFiltroActionPerformed

    private void miAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAyudaActionPerformed
        // TODO add your handling code here:
         if (helpBroker != null) {
            helpBroker.setDisplayed(true);
        } else {
            System.out.println("El sistema de ayuda no está disponible.");
        }
    }//GEN-LAST:event_miAyudaActionPerformed

    private void miVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miVolverActionPerformed
        // TODO add your handling code here:
        dispose();
        new MainView2(usuario).setVisible(true);
    }//GEN-LAST:event_miVolverActionPerformed

    private void miAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAnadirActionPerformed
        // TODO add your handling code here:
        AnadirProducto anadirProducto = new AnadirProducto(usuario);
        dispose();
        anadirProducto.setVisible(true);
    }//GEN-LAST:event_miAnadirActionPerformed

    private void miEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEditarActionPerformed
        // Obtener la fila seleccionada en la tabla
        int filaSeleccionada = tablaProductos.getSelectedRow();

        // Verificar si se ha seleccionado una fila
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener los datos del producto seleccionado
        String nombre = (String) tablaProductos.getValueAt(filaSeleccionada, 0); // Nombre en la columna 0
        double precio = (double) tablaProductos.getValueAt(filaSeleccionada, 1); // Precio en la columna 1
        int stock = (int) tablaProductos.getValueAt(filaSeleccionada, 2); // Stock en la columna 2

        // Crear un DTO con los datos del producto seleccionado
        ProductoDTO productoDTO = new ProductoDTO(nombre, precio, stock);

        // Abrir la ventana de edición y pasarle el producto seleccionado
        EditarProducto editarProducto = new EditarProducto(usuario, productoDTO);
        dispose(); // Cerrar la ventana actual
        editarProducto.setVisible(true); // Mostrar la ventana de edición
    }//GEN-LAST:event_miEditarActionPerformed

    private void bGestionInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGestionInformeActionPerformed
        // TODO add your handling code here:
        GestionInformeProducto informe = new GestionInformeProducto(usuario);
        dispose();
        informe.setVisible(true);
    }//GEN-LAST:event_bGestionInformeActionPerformed

    private void bLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLimpiarActionPerformed
        tfNombre.setText("");
        tfPrecio.setText("");
        tfStock.setText("");

        filtroNombre = null;
        filtroPrecioMaximo = null;
        filtroStockMaximo = null;

        paginaActual = 0;
        cargarPagina(paginaActual);

    }//GEN-LAST:event_bLimpiarActionPerformed

    private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
        int selectedRow = tablaProductos.getSelectedRow();
        if (selectedRow >= 0) {
            // Obtener el nombre del producto seleccionado
            String nombre = (String) tablaProductos.getValueAt(selectedRow, 0);

            // Eliminar el producto
            String resultado = productoController.eliminarProducto(nombre);

            // Mostrar el resultado de la eliminación
            JOptionPane.showMessageDialog(this, resultado);

            // Recargar la página actual
            cargarPagina(paginaActual);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bEliminarActionPerformed

    private void bSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSiguienteActionPerformed
        paginaActual++;
        cargarPagina(paginaActual);
    }//GEN-LAST:event_bSiguienteActionPerformed

    private void bAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnteriorActionPerformed
        if (paginaActual > 0) {
            paginaActual--;
            cargarPagina(paginaActual);
        }
    }//GEN-LAST:event_bAnteriorActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAnterior;
    private javax.swing.JButton bAplicarFiltro;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bGestionInforme;
    private javax.swing.JButton bLimpiar;
    private javax.swing.JButton bSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lPagina;
    private javax.swing.JMenu mArchivo;
    private javax.swing.JMenu mAyuda;
    private javax.swing.JMenuItem miAnadir;
    private javax.swing.JMenuItem miAyuda;
    private javax.swing.JMenuItem miEditar;
    private javax.swing.JMenuItem miVolver;
    private javax.swing.JPanel pFiltros;
    private javax.swing.JPanel pPaginado;
    private javax.swing.JPanel pTabla;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTextField tfNombre;
    private javax.swing.JTextField tfPrecio;
    private javax.swing.JTextField tfStock;
    // End of variables declaration//GEN-END:variables
}
