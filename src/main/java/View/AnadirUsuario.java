/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.UsuarioController;
import DTO.UsuarioDTO;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JOptionPane;

import java.net.URL;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author JETTSUSHD
 */
public class AnadirUsuario extends javax.swing.JFrame {

    private UsuarioDTO usuario;
    private final UsuarioController usuarioController; // Controlador para manejar la lógica de usuarios
    private HelpSet helpSet;
    private HelpBroker helpBroker;

    /**
     * Creates new form AnadirUsuario
     */
    public AnadirUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
        this.usuarioController = new UsuarioController(); // Inicializar el controlador
        initComponents();

        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);

        // Evitar que la "X" cierre toda la aplicación
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        agregarJavaHelp();
        setTitle("Ventana añadir usuario");

        // Capturar evento al cerrar la ventana con "X"
        addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent evt) {
                dispose();
                new GestionUsuario2(usuario).setVisible(true);
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

    private void agregarJavaHelp() {
        try {
            URL hsURL = getClass().getClassLoader().getResource("help/help_set.hs");

            HelpSet helpset = new HelpSet(null, hsURL);
            HelpBroker hb = helpset.createHelpBroker();

            hb.enableHelpKey(this.getContentPane(), "ventana_principal", helpset);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cerrarSesion() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Seguro que deseas volver a la gestión usuarios?", "Cerrar Sesión",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // Cierra solo esta ventana
            new GestionUsuario2(usuario).setVisible(true);

            // Asegurar que la nueva ventana se abre correctamente en la EDT
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new GestionUsuario2(usuario).setVisible(true);
                }
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

        lNombre = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        lCorreo = new javax.swing.JLabel();
        lContra = new javax.swing.JLabel();
        tfContrasena = new javax.swing.JTextField();
        tfConfirmarContra = new javax.swing.JTextField();
        lConfirmarContra = new javax.swing.JLabel();
        lTipo = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        tfCorreo = new javax.swing.JTextField();
        bAgregar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mArchivo = new javax.swing.JMenu();
        miVolver = new javax.swing.JMenuItem();
        mAyuda = new javax.swing.JMenu();
        miAyuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lNombre.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lNombre.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lNombre, gridBagConstraints);

        tfNombre.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfNombre.setMinimumSize(new java.awt.Dimension(150, 22));
        tfNombre.setPreferredSize(new java.awt.Dimension(400, 44));
        tfNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNombreActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 13, 0, 0);
        getContentPane().add(tfNombre, gridBagConstraints);

        lCorreo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lCorreo.setText("Correo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 0, 0);
        getContentPane().add(lCorreo, gridBagConstraints);

        lContra.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lContra.setText("Contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 0, 0);
        getContentPane().add(lContra, gridBagConstraints);

        tfContrasena.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfContrasena.setMinimumSize(new java.awt.Dimension(150, 22));
        tfContrasena.setPreferredSize(new java.awt.Dimension(400, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(13, 13, 0, 0);
        getContentPane().add(tfContrasena, gridBagConstraints);

        tfConfirmarContra.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfConfirmarContra.setMinimumSize(new java.awt.Dimension(150, 22));
        tfConfirmarContra.setPreferredSize(new java.awt.Dimension(400, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(13, 13, 0, 0);
        getContentPane().add(tfConfirmarContra, gridBagConstraints);

        lConfirmarContra.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lConfirmarContra.setText("Confirmar contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 0, 0);
        getContentPane().add(lConfirmarContra, gridBagConstraints);

        lTipo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lTipo.setText("Tipo usuario:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 0, 0);
        getContentPane().add(lTipo, gridBagConstraints);

        cbTipo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "vendedor" }));
        cbTipo.setPreferredSize(new java.awt.Dimension(400, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(13, 13, 0, 0);
        getContentPane().add(cbTipo, gridBagConstraints);

        tfCorreo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfCorreo.setMinimumSize(new java.awt.Dimension(150, 22));
        tfCorreo.setPreferredSize(new java.awt.Dimension(400, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(13, 13, 0, 0);
        getContentPane().add(tfCorreo, gridBagConstraints);

        bAgregar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bAgregar.setText("Crear usuario");
        bAgregar.setPreferredSize(new java.awt.Dimension(200, 44));
        bAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 0, 0);
        getContentPane().add(bAgregar, gridBagConstraints);

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

    private void bAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarActionPerformed
        // TODO add your handling code here:
        crearUsuario();
    }//GEN-LAST:event_bAgregarActionPerformed

    private void miAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAyudaActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (helpBroker != null) {
            helpBroker.setDisplayed(true);
        } else {
            System.out.println("El sistema de ayuda no está disponible.");
        }
    }//GEN-LAST:event_miAyudaActionPerformed

    private void miVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miVolverActionPerformed
        // TODO add your handling code here:
        dispose(); // Cierra solo esta ventana
        new GestionUsuario2(usuario).setVisible(true);
    }//GEN-LAST:event_miVolverActionPerformed

    private void crearUsuario() {
        // Obtener los valores de los campos
        String nombre = tfNombre.getText().trim();
        String correo = tfCorreo.getText().trim();
        String contrasena = tfContrasena.getText().trim();
        String confirmarContrasena = tfConfirmarContra.getText().trim();
        String tipoUsuario = cbTipo.getSelectedItem().toString();

        // Validar que todos los campos estén completos
        if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty() || tipoUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que las contraseñas coincidan
        if (!contrasena.equals(confirmarContrasena)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Usar el controlador para registrar el usuario
        String mensaje = usuarioController.registrarUsuario(nombre, correo, contrasena, tipoUsuario);
        JOptionPane.showMessageDialog(this, mensaje, mensaje.contains("Error") ? "Error" : "Éxito",
                mensaje.contains("Error") ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);

        if (mensaje.contains("exitosamente")) {
            // Volver a GestionUsuario2
            dispose();
            java.awt.EventQueue.invokeLater(() -> new GestionUsuario2(usuario).setVisible(true));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAgregar;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lConfirmarContra;
    private javax.swing.JLabel lContra;
    private javax.swing.JLabel lCorreo;
    private javax.swing.JLabel lNombre;
    private javax.swing.JLabel lTipo;
    private javax.swing.JMenu mArchivo;
    private javax.swing.JMenu mAyuda;
    private javax.swing.JMenuItem miAyuda;
    private javax.swing.JMenuItem miVolver;
    private javax.swing.JTextField tfConfirmarContra;
    private javax.swing.JTextField tfContrasena;
    private javax.swing.JTextField tfCorreo;
    private javax.swing.JTextField tfNombre;
    // End of variables declaration//GEN-END:variables
}
