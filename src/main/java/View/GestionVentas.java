/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.VentaController;
import DTO.UsuarioDTO;
import DTO.VentaDTO;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.help.HelpBroker;
import javax.help.HelpSet;
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
public class GestionVentas extends javax.swing.JFrame {

    /**
     * Creates new form GestionVentas
     */
    private final VentaController ventaController;
    private int pagActual = 0;
    private List<VentaDTO> ventasActuales;
    private UsuarioDTO usuario;
    private String filtroVendedor;
    private String filtroProducto;
    private Double filtroPrecioMax;

    private HelpSet helpSet;
    private HelpBroker helpBroker;

    public GestionVentas(UsuarioDTO usuario) {
        this.usuario = usuario;
        ventaController = new VentaController();
        this.ventasActuales = new ArrayList<>();
        initComponents(); 
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("Gestión de Ventas");
        inicializarTabla();
        cargarTablaVentas(pagActual);
        setTitle("Ventana gestion ventas");
        keyBinding();
        
        initHelp();
        ejecutarF1();
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

    // Método para obtener el usuario (necesario para reabrir GestionVentas)
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    private void inicializarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Vendedor", "Producto", "Fecha", "Total", "Cantidad"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        tablaVentas.setModel(modelo);
    }

    public void cargarTablaVentas(int pagina) {
        System.out.println("Cargando tabla - Página: " + pagina + ", Filtro Vendedor: " + filtroVendedor
                + ", Filtro Producto: " + filtroProducto + ", Filtro Precio Max: " + filtroPrecioMax);
        List<VentaDTO> ventas = ventaController.obtenerVentasFiltradas(pagina, filtroVendedor, filtroProducto, filtroPrecioMax);
        DefaultTableModel modelo = (DefaultTableModel) tablaVentas.getModel();
        modelo.setRowCount(0);

        if (ventas == null || ventas.isEmpty()) {
            ventasActuales.clear();
            bAnterior.setEnabled(pagActual > 0);
            bSiguiente.setEnabled(false);
            lPagina.setText("Página: " + (pagActual + 1));
            if (pagina == 0 && (filtroVendedor == null || filtroVendedor.isEmpty())
                    && (filtroProducto == null || filtroProducto.isEmpty()) && filtroPrecioMax == null) {
                JOptionPane.showMessageDialog(this, "No hay ventas registradas en la base de datos.", "Sin Datos", JOptionPane.INFORMATION_MESSAGE);
            }
            return;
        }

        ventasActuales.clear();
        ventasActuales.addAll(ventas);

        ventas.forEach(v -> modelo.addRow(new Object[]{
            v.getId(),
            v.getNombreVendedor(),
            v.getNombreProducto(),
            v.getFecha(),
            v.getTotal(),
            v.getCantidad()
        }));

        bAnterior.setEnabled(pagActual > 0);
        bSiguiente.setEnabled(ventas.size() >= 10);
        lPagina.setText("Página: " + (pagActual + 1));
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
        lVendedor = new javax.swing.JLabel();
        tfVendedor = new javax.swing.JTextField();
        lProducto = new javax.swing.JLabel();
        tfProducto = new javax.swing.JTextField();
        lPrecio = new javax.swing.JLabel();
        tfPrecioMax = new javax.swing.JTextField();
        bAplicarFiltro = new javax.swing.JButton();
        bLimpiar = new javax.swing.JButton();
        bEliminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        pTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        pPaginacion = new javax.swing.JPanel();
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

        lVendedor.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lVendedor.setText("Vendedor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 12);
        pFiltros.add(lVendedor, gridBagConstraints);

        tfVendedor.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfVendedor.setPreferredSize(new java.awt.Dimension(200, 44));
        tfVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfVendedorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 12);
        pFiltros.add(tfVendedor, gridBagConstraints);

        lProducto.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lProducto.setText("Producto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 12);
        pFiltros.add(lProducto, gridBagConstraints);

        tfProducto.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfProducto.setPreferredSize(new java.awt.Dimension(200, 44));
        tfProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfProductoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 12);
        pFiltros.add(tfProducto, gridBagConstraints);

        lPrecio.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lPrecio.setText("Precio maximo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 12);
        pFiltros.add(lPrecio, gridBagConstraints);

        tfPrecioMax.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfPrecioMax.setPreferredSize(new java.awt.Dimension(200, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 12);
        pFiltros.add(tfPrecioMax, gridBagConstraints);

        bAplicarFiltro.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bAplicarFiltro.setText("Aplicar filtros");
        bAplicarFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAplicarFiltroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 3, 12);
        pFiltros.add(bAplicarFiltro, gridBagConstraints);

        bLimpiar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bLimpiar.setText("Limpiar filtros");
        bLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLimpiarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 2);
        pFiltros.add(bLimpiar, gridBagConstraints);

        bEliminar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bEliminar.setText("Eliminar");
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 2, 0);
        pFiltros.add(bEliminar, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton1.setText("Gestión informes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 2, 0);
        pFiltros.add(jButton1, gridBagConstraints);

        getContentPane().add(pFiltros, java.awt.BorderLayout.PAGE_START);

        pTabla.setLayout(new java.awt.BorderLayout());

        tablaVentas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Vendedor", "Producto", "Fecha", "Total", "Cantidad"
            }
        ));
        tablaVentas.setPreferredSize(new java.awt.Dimension(400, 588));
        tablaVentas.setRowHeight(58);
        jScrollPane1.setViewportView(tablaVentas);

        pTabla.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        bAnterior.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bAnterior.setText("Anterior");
        bAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnteriorActionPerformed(evt);
            }
        });
        pPaginacion.add(bAnterior);

        lPagina.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lPagina.setText("Pagina:");
        pPaginacion.add(lPagina);

        bSiguiente.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bSiguiente.setText("Siguiente");
        bSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSiguienteActionPerformed(evt);
            }
        });
        pPaginacion.add(bSiguiente);

        pTabla.add(pPaginacion, java.awt.BorderLayout.SOUTH);

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
        miAnadir.setText("Añadir venta");
        miAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAnadirActionPerformed(evt);
            }
        });
        mArchivo.add(miAnadir);

        miEditar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        miEditar.setText("Editar venta");
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
        miAyuda.setText("Acerca de ");
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
        new MainView2(usuario).setVisible(true);
        dispose();
    }//GEN-LAST:event_miVolverActionPerformed

    private void tfVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfVendedorActionPerformed

    private void tfProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfProductoActionPerformed

    private void bLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLimpiarActionPerformed
        // TODO add your handling code here:
        tfVendedor.setText("");
        tfProducto.setText("");
        tfPrecioMax.setText("");
        filtroVendedor = null;
        filtroProducto = null;
        filtroPrecioMax = null;
        pagActual = 0;
        cargarTablaVentas(pagActual);
    }//GEN-LAST:event_bLimpiarActionPerformed

    private void bAplicarFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAplicarFiltroActionPerformed
        // TODO add your handling code here:
        try {
            filtroVendedor = tfVendedor.getText().trim().isEmpty() ? null : tfVendedor.getText().trim();
            filtroProducto = tfProducto.getText().trim().isEmpty() ? null : tfProducto.getText().trim();
            filtroPrecioMax = tfPrecioMax.getText().trim().isEmpty() ? null : Double.parseDouble(tfPrecioMax.getText().trim());
            if (filtroPrecioMax != null && filtroPrecioMax < 0) {
                throw new NumberFormatException("El precio no puede ser negativo.");
            }
            pagActual = 0;
            cargarTablaVentas(pagActual);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para el precio.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bAplicarFiltroActionPerformed

    private void miAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAnadirActionPerformed
        // TODO add your handling code here:
        AnadirVenta anadirVenta = new AnadirVenta(usuario, this, ventaController);
        dispose();
        anadirVenta.setVisible(true);
    }//GEN-LAST:event_miAnadirActionPerformed

    private void bAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnteriorActionPerformed
        // TODO add your handling code here:
        if (pagActual > 0) {
            pagActual--;
            cargarTablaVentas(pagActual);
        }
    }//GEN-LAST:event_bAnteriorActionPerformed

    private void bSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSiguienteActionPerformed
        // TODO add your handling code here:
        pagActual++;
        cargarTablaVentas(pagActual);
    }//GEN-LAST:event_bSiguienteActionPerformed

    private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
        // TODO add your handling code here:
        int fila = tablaVentas.getSelectedRow();
        if (fila != -1) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que desea eliminar esta venta?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                VentaDTO venta = ventasActuales.get(fila);
                try {
                    ventaController.eliminarVenta(venta.getId());
                    JOptionPane.showMessageDialog(this, "Venta eliminada y stock restaurado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarTablaVentas(pagActual);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una venta para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_bEliminarActionPerformed

    private void miEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEditarActionPerformed
        // TODO add your handling code here:
        int fila = tablaVentas.getSelectedRow();
        if (fila != -1) {
            VentaDTO venta = ventasActuales.get(fila);
            EditarVenta editarVenta = new EditarVenta(this, ventaController, usuario, venta);
            dispose();
            editarVenta.setVisible(true);
        } else {

            JOptionPane.showMessageDialog(this, "Seleccione una venta para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_miEditarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        GestionInformes informes = new GestionInformes(usuario);
        dispose();
        informes.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new GestionVentas(null).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAnterior;
    private javax.swing.JButton bAplicarFiltro;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bLimpiar;
    private javax.swing.JButton bSiguiente;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lPagina;
    private javax.swing.JLabel lPrecio;
    private javax.swing.JLabel lProducto;
    private javax.swing.JLabel lVendedor;
    private javax.swing.JMenu mArchivo;
    private javax.swing.JMenu mAyuda;
    private javax.swing.JMenuItem miAnadir;
    private javax.swing.JMenuItem miAyuda;
    private javax.swing.JMenuItem miEditar;
    private javax.swing.JMenuItem miVolver;
    private javax.swing.JPanel pFiltros;
    private javax.swing.JPanel pPaginacion;
    private javax.swing.JPanel pTabla;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTextField tfPrecioMax;
    private javax.swing.JTextField tfProducto;
    private javax.swing.JTextField tfVendedor;
    // End of variables declaration//GEN-END:variables
}
