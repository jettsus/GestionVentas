package Componente;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CuentaAtras extends JPanel implements Serializable {

    private int tiempo = 5; // Valor inicial por defecto (5 segundos)
    private Timer t;
    private FinalizadoListener finlistener;
    private boolean activo;
    private JLabel labelImagen; // Label para la imagen

    public CuentaAtras() {
        // Configurar el diseño del panel
        this.setLayout(null); // Diseño absoluto

        // Inicializar el JLabel para la imagen
        labelImagen = new JLabel();
        labelImagen.setBounds(0, 0, 800, 600); // Ajusta según el tamaño de la imagen
        loadImage(); // Cargar la imagen
        this.add(labelImagen); // Añadir la imagen al JPanel

        // Auto-iniciar el temporizador
        iniciar();
    }

    private void loadImage() {
        try {
            // Cargar la imagen desde los recursos
            java.net.URL imageUrl = getClass().getClassLoader().getResource("imagenes/ventas.png");
            if (imageUrl == null) {
                throw new Exception("Imagen 'ventas.png' no encontrada en los recursos.");
            }
            ImageIcon imageIcon = new ImageIcon(imageUrl);
            Image image = imageIcon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
            labelImagen.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
            e.printStackTrace();
            labelImagen.setText("Imagen no disponible");
        }
    }

    // Getter y Setter para el tiempo
    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
        repaint();
    }

    public interface FinalizadoListener {
        void fincuentaatras();
    }

    private void iniciar() {
        if (!activo) { // Solo iniciar si no está activo
            if (t == null) {
                System.out.println("Iniciada la cuenta atrás. Tiempo: " + tiempo + " segundos");
                t = new Timer(tiempo * 1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Timer finalizado. Deteniendo el timer.");
                        t.stop();
                        activo = false; // Permitir reiniciar
                        if (finlistener != null) {
                            System.out.println("Llamando al FinalizadoListener...");
                            finlistener.fincuentaatras();
                        } else {
                            System.out.println("No hay FinalizadoListener asignado.");
                        }
                    }
                });
                t.setRepeats(false); // Asegurar que el timer solo se ejecute una vez
            }
            t.start();
            activo = true;
        }
    }

    public void addFinalizadoListener(FinalizadoListener finalizadoListener) {
        System.out.println("Asignando FinalizadoListener...");
        this.finlistener = finalizadoListener;
    }

    public void removeFinalizadoListener() {
        System.out.println("Removiendo FinalizadoListener...");
        this.finlistener = null;
    }
}