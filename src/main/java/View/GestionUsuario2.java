/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.UsuarioController;
import DTO.UsuarioDTO;
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
public class GestionUsuario2 extends javax.swing.JFrame {

    private final UsuarioController usuarioController;
    private int paginaActual = 0;
    private String filtroNombre = null; // Guardar el filtro de nombre
    private String filtroTipo = null;   // Guardar el filtro de tipo de usuario
    private HelpSet helpSet;
    private HelpBroker helpBroker;
    private final UsuarioDTO usuario; // Almacenar el usuario
    private List<UsuarioDTO> currentUsuarios;

    /**
     * Creates new form GestionUsuario2
     */
    public GestionUsuario2(UsuarioDTO usuario) {
        usuarioController = new UsuarioController();
        this.usuario = usuario; // Guardar el usuario
        this.currentUsuarios = new ArrayList<>();
        initComponents();

        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);

        inicializarTabla();

        cargarTablaa(paginaActual);
        setTitle("Gestión Usuarios");
        // Evitar que la "X" cierre toda la aplicación
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        // Capturar evento al cerrar la ventana con "X"
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                cerrarSesion();
            }
        });

        setTitle("Ventana gestion usuario");
        initHelp();
        ejecutarF1();

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

    // Initialize the table with the correct column names
    private void inicializarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nombre", "Correo", "Tipo de usuario"}
        );
        tablaUsuarios.setModel(modelo);
    }

    private void cargarTablaa(int pagina) {
        List<UsuarioDTO> usuarios;
        if (filtroNombre == null && filtroTipo == null) {
            usuarios = usuarioController.obtenerUsuarios(pagina);
        } else {
            usuarios = usuarioController.obtenerUsuariosFiltrados(pagina, filtroNombre, filtroTipo);
        }

        DefaultTableModel modelo = (DefaultTableModel) tablaUsuarios.getModel();
        modelo.setRowCount(0); // Clear the table

        if (usuarios == null) {
            JOptionPane.showMessageDialog(this, "Error: No se pudieron cargar los usuarios filtrados.", "Error", JOptionPane.ERROR_MESSAGE);
            currentUsuarios.clear();
            return;
        }

        currentUsuarios.clear();
        currentUsuarios.addAll(usuarios);

        usuarios.forEach(u -> modelo.addRow(new Object[]{
            u.getNombre(), u.getCorreo(), u.getTipoUsuario()
        }));

        bAnterior.setEnabled(paginaActual > 0);
        bSiguiente.setEnabled(usuarios.size() >= 10);
        lPagina.setText("Página: " + (paginaActual + 1));
    }

    // Aplicar filtros
    private void aplicarFiltro() {
        filtroNombre = tfNombre.getText().trim().isEmpty() ? null : tfNombre.getText().trim();
        filtroTipo = cbTipo.getSelectedItem().toString().equals("Todos") ? null : cbTipo.getSelectedItem().toString();
        paginaActual = 0;
        cargarTablaa(paginaActual);
    }

    // Limpiar filtros
    private void limpiarFiltros() {
        tfNombre.setText("");
        cbTipo.setSelectedIndex(0);
        filtroNombre = null;
        filtroTipo = null;
        paginaActual = 0;
        cargarTablaa(paginaActual);
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

        pFiltros = new javax.swing.JPanel();
        lNombre = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        lTIpo = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        bAplicarFiltro = new javax.swing.JButton();
        bLimpiarFIltros = new javax.swing.JButton();
        pTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        pPaginacion = new javax.swing.JPanel();
        bAnterior = new javax.swing.JButton();
        lPagina = new javax.swing.JLabel();
        bSiguiente = new javax.swing.JButton();
        mbOpciones = new javax.swing.JMenuBar();
        mArchivo = new javax.swing.JMenu();
        miVolver = new javax.swing.JMenuItem();
        miAgregar = new javax.swing.JMenuItem();
        miEditar = new javax.swing.JMenuItem();
        mAyuda = new javax.swing.JMenu();
        miAyuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pFiltros.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 5));

        lNombre.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lNombre.setText("Nombre:");
        pFiltros.add(lNombre);

        tfNombre.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfNombre.setPreferredSize(new java.awt.Dimension(200, 44));
        pFiltros.add(tfNombre);

        lTIpo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lTIpo.setText("TIpo de usuario:");
        pFiltros.add(lTIpo);

        cbTipo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "admin", "vendedor" }));
        cbTipo.setPreferredSize(new java.awt.Dimension(200, 44));
        pFiltros.add(cbTipo);

        bAplicarFiltro.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bAplicarFiltro.setText("Aplicar filtro");
        bAplicarFiltro.setPreferredSize(new java.awt.Dimension(200, 44));
        bAplicarFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAplicarFiltroActionPerformed(evt);
            }
        });
        pFiltros.add(bAplicarFiltro);

        bLimpiarFIltros.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bLimpiarFIltros.setText("Limpiar filtro");
        bLimpiarFIltros.setPreferredSize(new java.awt.Dimension(200, 44));
        bLimpiarFIltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLimpiarFIltrosActionPerformed(evt);
            }
        });
        pFiltros.add(bLimpiarFIltros);

        getContentPane().add(pFiltros, java.awt.BorderLayout.PAGE_START);

        pTabla.setLayout(new java.awt.BorderLayout());

        tablaUsuarios.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaUsuarios.setRowHeight(59);
        jScrollPane1.setViewportView(tablaUsuarios);

        pTabla.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pTabla, java.awt.BorderLayout.CENTER);

        pPaginacion.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 20));

        bAnterior.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bAnterior.setText("Anterior");
        bAnterior.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bAnterior.setBorderPainted(false);
        bAnterior.setPreferredSize(new java.awt.Dimension(129, 39));
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
        bSiguiente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bSiguiente.setBorderPainted(false);
        bSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSiguienteActionPerformed(evt);
            }
        });
        pPaginacion.add(bSiguiente);

        getContentPane().add(pPaginacion, java.awt.BorderLayout.SOUTH);

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

        miAgregar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        miAgregar.setText("Añadir");
        miAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAgregarActionPerformed(evt);
            }
        });
        mArchivo.add(miAgregar);

        miEditar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        miEditar.setText("Editar");
        miEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEditarActionPerformed(evt);
            }
        });
        mArchivo.add(miEditar);

        mbOpciones.add(mArchivo);

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

        mbOpciones.add(mAyuda);

        setJMenuBar(mbOpciones);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miVolverActionPerformed
        // TODO add your handling code here:
        MainView2 mainview = new MainView2(usuario);
        dispose();
        mainview.setVisible(true);

    }//GEN-LAST:event_miVolverActionPerformed

    private void miAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAyudaActionPerformed
        // TODO add your handling code here:
        if (helpBroker != null) {
            helpBroker.setDisplayed(true);
        } else {
            System.out.println("El sistema de ayuda no está disponible.");
        }
    }//GEN-LAST:event_miAyudaActionPerformed

    private void bAplicarFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAplicarFiltroActionPerformed
        // TODO add your handling code here:
        aplicarFiltro();

    }//GEN-LAST:event_bAplicarFiltroActionPerformed

    private void bLimpiarFIltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLimpiarFIltrosActionPerformed
        // TODO add your handling code here:
        limpiarFiltros();
    }//GEN-LAST:event_bLimpiarFIltrosActionPerformed

    private void miAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAgregarActionPerformed
        // TODO add your handling code here:
        AnadirUsuario anadirusuario = new AnadirUsuario(usuario);
        anadirusuario.setVisible(true);

    }//GEN-LAST:event_miAgregarActionPerformed

    private void miEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEditarActionPerformed
        int fila = tablaUsuarios.getSelectedRow();
        if (fila != -1) {
            if (fila < 0 || fila >= currentUsuarios.size()) {
                JOptionPane.showMessageDialog(this, "Error: El usuario seleccionado no está disponible para editar.", "Error", JOptionPane.ERROR_MESSAGE);
                cargarTablaa(paginaActual);
                return;
            }
            UsuarioDTO usuarioAEditar = currentUsuarios.get(fila);
            EditarUsuario editarUsuario = new EditarUsuario(usuario, usuarioAEditar);
            editarUsuario.setLocationRelativeTo(null); // Center the window
            editarUsuario.setVisible(true);
            dispose(); // Close the current window
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para editar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_miEditarActionPerformed

    private void bSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSiguienteActionPerformed
        // TODO add your handling code here:
        paginaActual++;
        cargarTablaa(paginaActual);
    }//GEN-LAST:event_bSiguienteActionPerformed

    private void bAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnteriorActionPerformed
        if (paginaActual > 0) {
            paginaActual--;
            cargarTablaa(paginaActual);
        }
    }//GEN-LAST:event_bAnteriorActionPerformed

    private void cerrarSesion() {

        dispose(); // Cierra solo esta ventana
        // Asegurar que la nueva ventana se abre correctamente en la EDT
        new MainView2(usuario).setVisible(true);

    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAnterior;
    private javax.swing.JButton bAplicarFiltro;
    private javax.swing.JButton bLimpiarFIltros;
    private javax.swing.JButton bSiguiente;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lNombre;
    private javax.swing.JLabel lPagina;
    private javax.swing.JLabel lTIpo;
    private javax.swing.JMenu mArchivo;
    private javax.swing.JMenu mAyuda;
    private javax.swing.JMenuBar mbOpciones;
    private javax.swing.JMenuItem miAgregar;
    private javax.swing.JMenuItem miAyuda;
    private javax.swing.JMenuItem miEditar;
    private javax.swing.JMenuItem miVolver;
    private javax.swing.JPanel pFiltros;
    private javax.swing.JPanel pPaginacion;
    private javax.swing.JPanel pTabla;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField tfNombre;
    // End of variables declaration//GEN-END:variables
}
