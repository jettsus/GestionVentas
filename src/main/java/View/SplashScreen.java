package View;

import Componente.CuentaAtras;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class SplashScreen extends JFrame {

    private CuentaAtras cuentaAtras;

    public SplashScreen() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Cargando...");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setSize(800, 600);
        setLocationRelativeTo(null);

        cuentaAtras = new CuentaAtras();
        add(cuentaAtras, BorderLayout.CENTER);

        cuentaAtras.addFinalizadoListener(new CuentaAtras.FinalizadoListener() {
            @Override
            public void fincuentaatras() {
                System.out.println("Evento fincuentaatras disparado. Cerrando SplashScreen...");
                dispose();
                System.out.println("Abriendo LoginView...");
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        LoginView loginView = new LoginView();
                        loginView.setVisible(true);
                        System.out.println("LoginView abierto.");
                    }
                });
            }
        });

        setVisible(true);
        setResizable(false);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SplashScreen().setVisible(true);
            }
        });
    }
}