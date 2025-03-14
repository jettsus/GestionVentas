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
import java.net.URL;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author JETTSUSHD
 */
public class EditarUsuario extends javax.swing.JFrame {

    /**
     * Creates new form EditarUsuario
     */
    private final UsuarioController usuarioController;
    private final UsuarioDTO usuarioLogueado;
    private final UsuarioDTO usuarioAEditar;
    
 private HelpSet helpSet;
    private HelpBroker helpBroker;

    public EditarUsuario(UsuarioDTO usuarioLogueado, UsuarioDTO usuarioAEditar) {
        this.usuarioLogueado = usuarioLogueado;
        this.usuarioAEditar = usuarioAEditar;
        this.usuarioController = new UsuarioController();
        setTitle("Editar usuario");
        initComponents();

        prefillFields();

        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        setTitle("Ventana editar usuario");
        keyBinding();
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        // Capturar evento al cerrar la ventana con "X"
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                dispose();
                new GestionUsuario2(usuarioLogueado).setVisible(true);
            }
        });
        initHelp();
        ejecutarF1();
        
        setResizable(false);
    }

    public void keyBinding() {
        // Acción para aplicar los filtros cuando se presiona ENTER
        Action aplicaranadir = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bEditar.doClick(); // Simula un click en aplicar filtro
            }
        };

        // Añadir keybinding para la tecla ENTER para aplicar los filtros
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "AÑADIR");
        rootPane.getActionMap().put("AÑADIR", aplicaranadir);

        requestFocusInWindow();
        setFocusable(true);
    }

    private void prefillFields() {
        tfNombre.setText(usuarioAEditar.getNombre());
        tfCorreo.setText(usuarioAEditar.getCorreo());
        tfContra.setText(usuarioAEditar.getContraseña());
        tfConfirmarContra.setText(usuarioAEditar.getContraseña());
        cbTIpo.setSelectedItem(usuarioAEditar.getTipoUsuario());
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

        jLabel1 = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        tfCorreo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        bEditar = new javax.swing.JButton();
        tfContra = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbTIpo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        tfConfirmarContra = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        miAyuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 13);
        getContentPane().add(jLabel1, gridBagConstraints);

        tfNombre.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfNombre.setPreferredSize(new java.awt.Dimension(400, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(tfNombre, gridBagConstraints);

        tfCorreo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfCorreo.setPreferredSize(new java.awt.Dimension(400, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(19, 0, 0, 0);
        getContentPane().add(tfCorreo, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Correo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(19, 0, 0, 13);
        getContentPane().add(jLabel2, gridBagConstraints);

        bEditar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bEditar.setText("Editar");
        bEditar.setPreferredSize(new java.awt.Dimension(200, 44));
        bEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(19, 0, 0, 0);
        getContentPane().add(bEditar, gridBagConstraints);

        tfContra.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfContra.setPreferredSize(new java.awt.Dimension(400, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(19, 0, 0, 0);
        getContentPane().add(tfContra, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Contraseña");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(19, 0, 0, 13);
        getContentPane().add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Tipo usuario:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(19, 0, 0, 13);
        getContentPane().add(jLabel4, gridBagConstraints);

        cbTIpo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbTIpo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "vendedor" }));
        cbTIpo.setPreferredSize(new java.awt.Dimension(400, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(19, 0, 0, 0);
        getContentPane().add(cbTIpo, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Confirmar contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(19, 0, 0, 9);
        getContentPane().add(jLabel5, gridBagConstraints);

        tfConfirmarContra.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfConfirmarContra.setPreferredSize(new java.awt.Dimension(400, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(19, 0, 0, 0);
        getContentPane().add(tfConfirmarContra, gridBagConstraints);

        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jMenuItem1.setText("Volver");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        miAyuda.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        miAyuda.setText("Acerca de ");
        miAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAyudaActionPerformed(evt);
            }
        });
        jMenu2.add(miAyuda);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        dispose();
        new GestionUsuario2(usuarioLogueado).setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void miAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAyudaActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (helpBroker != null) {
            helpBroker.setDisplayed(true);
        } else {
            System.out.println("El sistema de ayuda no está disponible.");
        }
    }//GEN-LAST:event_miAyudaActionPerformed

    private void bEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditarActionPerformed
        // Validate all fields are filled
        String nombre = tfNombre.getText().trim();
        String correo = tfCorreo.getText().trim();
        String contraseña = tfContra.getText().trim();
        String confirmarContraseña = tfConfirmarContra.getText().trim();
        String tipoUsuario = cbTIpo.getSelectedItem().toString();

        if (nombre.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || confirmarContraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate passwords match
        if (!contraseña.equals(confirmarContraseña)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Call the controller's actualizarUsuario method with individual parameters
        String resultado = usuarioController.actualizarUsuario(correo, nombre, contraseña, tipoUsuario);

        // Check the result message to determine success or failure
        if (resultado.equals("Usuario actualizado correctamente.")) {
            JOptionPane.showMessageDialog(this, resultado, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            volverAtras();
        } else {
            JOptionPane.showMessageDialog(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bEditarActionPerformed

    public void volverAtras() {
        GestionUsuario2 gestionUsuario = new GestionUsuario2(usuarioLogueado);
        gestionUsuario.setLocationRelativeTo(null);
        gestionUsuario.setVisible(true);
        dispose();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEditar;
    private javax.swing.JComboBox<String> cbTIpo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem miAyuda;
    private javax.swing.JTextField tfConfirmarContra;
    private javax.swing.JTextField tfContra;
    private javax.swing.JTextField tfCorreo;
    private javax.swing.JTextField tfNombre;
    // End of variables declaration//GEN-END:variables
}
