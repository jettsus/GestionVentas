/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.VentaController;
import DTO.UsuarioDTO;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author JETTSUSHD
 */
public class AnadirVenta extends javax.swing.JFrame {

    private final GestionVentas parent;
    private final VentaController ventaController;
    UsuarioDTO usuario;

    private HelpSet helpSet;
    private HelpBroker helpBroker;

    public AnadirVenta(UsuarioDTO usuario, GestionVentas parent, VentaController ventaController) {
        this.usuario = this.usuario;
        this.parent = parent;
        this.ventaController = ventaController;
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);

        cargarDatos();
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ventana añadir venta");
        // Capturar evento al cerrar la ventana con "X"
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                dispose();
                new GestionVentas(usuario).setVisible(true);
            }
        });
        keyBinding();

        initHelp();
        ejecutarF1();
        
        setResizable(false);
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

    public void keyBinding() {
        // Acción para aplicar los filtros cuando se presiona ENTER
        Action aplicaranadir = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bAgregar.doClick(); // Simula un click en aplicar filtro
            }
        };

        // Añadir keybinding para la tecla ENTER para aplicar los filtros
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "AÑADIR");
        rootPane.getActionMap().put("AÑADIR", aplicaranadir);

        requestFocusInWindow();
        setFocusable(true);
    }

    private void cargarDatos() {
        // Cargar solo vendedores en el JComboBox
        List<String> vendedores = ventaController.obtenerVendedores(); // Ahora solo devuelve vendedores
        cbVendedor.removeAllItems();
        vendedores.forEach(cbVendedor::addItem);

        // Cargar productos en el JComboBox
        List<String> productos = ventaController.obtenerProductos();
        cbProducto.removeAllItems();
        productos.forEach(cbProducto::addItem);

        // Establecer la fecha actual como valor por defecto
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        tfFecha.setText(LocalDate.now().format(formatter));

        // Calcular el total cuando se cambia el producto o la cantidad
        cbProducto.addActionListener(e -> calcularTotal());
        tfCantidad.addActionListener(e -> calcularTotal());
    }

    private void calcularTotal() {
        try {
            String nombreProducto = (String) cbProducto.getSelectedItem();
            double precio = ventaController.getPrecioProductoPorNombre(nombreProducto);
            int cantidad = Integer.parseInt(tfCantidad.getText().trim());
            double total = precio * cantidad;
            tfTotal.setText(String.valueOf(total));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
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

        jLabel1 = new javax.swing.JLabel();
        cbVendedor = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbProducto = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        tfFecha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfCantidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfTotal = new javax.swing.JTextField();
        bAgregar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mVolver = new javax.swing.JMenu();
        miVover = new javax.swing.JMenuItem();
        mAyuda = new javax.swing.JMenu();
        miAyuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Nombre vendedor: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(jLabel1, gridBagConstraints);

        cbVendedor.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbVendedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbVendedor.setPreferredSize(new java.awt.Dimension(250, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        getContentPane().add(cbVendedor, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Nombre producto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);

        cbProducto.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbProducto.setPreferredSize(new java.awt.Dimension(250, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(12, 11, 0, 0);
        getContentPane().add(cbProducto, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Fecha(YYYY-MM-DD): ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        getContentPane().add(jLabel3, gridBagConstraints);

        tfFecha.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfFecha.setPreferredSize(new java.awt.Dimension(250, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(12, 11, 0, 0);
        getContentPane().add(tfFecha, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Cantidad: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        getContentPane().add(jLabel4, gridBagConstraints);

        tfCantidad.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfCantidad.setPreferredSize(new java.awt.Dimension(250, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(12, 11, 0, 0);
        getContentPane().add(tfCantidad, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Total: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        getContentPane().add(jLabel5, gridBagConstraints);

        tfTotal.setEditable(false);
        tfTotal.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfTotal.setPreferredSize(new java.awt.Dimension(250, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(12, 11, 0, 0);
        getContentPane().add(tfTotal, gridBagConstraints);

        bAgregar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bAgregar.setText("Guardar");
        bAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        getContentPane().add(bAgregar, gridBagConstraints);

        mVolver.setText("Archivo");
        mVolver.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        miVover.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        miVover.setText("Volver");
        miVover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miVoverActionPerformed(evt);
            }
        });
        mVolver.add(miVover);

        jMenuBar1.add(mVolver);

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

    private void bAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarActionPerformed
        // TODO add your handling code here:
        try {
            int usuarioId = ventaController.getUsuarioIdPorNombre((String) cbVendedor.getSelectedItem());
            int productoId = ventaController.getProductoIdPorNombre((String) cbProducto.getSelectedItem());
            double total = Double.parseDouble(tfTotal.getText().trim());
            String fecha = tfFecha.getText().trim();
            int cantidad = Integer.parseInt(tfCantidad.getText().trim());

            // Validar fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(fecha, formatter);

            if (usuarioId == -1 || productoId == -1) {
                throw new IllegalArgumentException("Vendedor o producto no encontrado.");
            }

            if (cantidad <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
            }

            ventaController.agregarVenta(usuarioId, productoId, total, fecha, cantidad);
            JOptionPane.showMessageDialog(this, "Venta añadida exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Cerrar la ventana GestionVentas y reabrirla
            parent.dispose();
            new GestionVentas(usuario).setVisible(true);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al añadir la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bAgregarActionPerformed

    private void miVoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miVoverActionPerformed
        // TODO add your handling code here:
        dispose();
        new GestionVentas(usuario).setVisible(true);
    }//GEN-LAST:event_miVoverActionPerformed

    private void miAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAyudaActionPerformed
        // TODO add your handling code here:
        if (helpBroker != null) {
            helpBroker.setDisplayed(true);
        } else {
            System.out.println("El sistema de ayuda no está disponible.");
        }


    }//GEN-LAST:event_miAyudaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAgregar;
    private javax.swing.JComboBox<String> cbProducto;
    private javax.swing.JComboBox<String> cbVendedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu mAyuda;
    private javax.swing.JMenu mVolver;
    private javax.swing.JMenuItem miAyuda;
    private javax.swing.JMenuItem miVover;
    private javax.swing.JTextField tfCantidad;
    private javax.swing.JTextField tfFecha;
    private javax.swing.JTextField tfTotal;
    // End of variables declaration//GEN-END:variables
}
