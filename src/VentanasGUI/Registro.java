package VentanasGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Logica.GestorEstudiante;

public class Registro {
    public JPanel panel1;
    private JTextField Nombre;
    private JTextField Contrasena;
    private JButton guardarEstudianteButton;
    private JTextField Correo;
    private JTextField Celular;

    public Registro() {
        guardarEstudianteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });
    }

    private void guardarDatos() {
        String nombre = Nombre.getText();
        String contrasena = Contrasena.getText();
        String celular = Celular.getText();
        String correo = Correo.getText();

        if (nombre.isEmpty() || contrasena.isEmpty()|| celular.isEmpty()|| correo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
           boolean estudianteRegistrado = GestorEstudiante.registrarEstudiante(nombre,celular,contrasena,correo);
            if (estudianteRegistrado) {
                JOptionPane.showMessageDialog(null, "Estudiante registrado satisfactoriamente:" +
                        "\nNombre: " + nombre  + "\nCelular: " + celular + "\nContraseña: " + contrasena + "\nCorreo: " + correo);
            } else {
                JOptionPane.showMessageDialog(null, "El estudiante ya existe / datos incorrectos");

            }
        }
    }



}

