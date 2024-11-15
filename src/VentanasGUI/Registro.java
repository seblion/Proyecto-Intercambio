package VentanasGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Logica.GestorEstudiante;

public class Registro {
    public JPanel panel1;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField correo;
    private JTextField celular;
    private JTextField usuario;
    private JTextField clave;
    private JButton guardarEstudianteButton;
    private JPasswordField claveConf;

    public Registro() {
        guardarEstudianteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });
    }

    private void guardarDatos() {
        String nombre = this.nombre.getText();
        String apellido = this.apellido.getText();
        String correo = this.correo.getText();
        String celular = this.celular.getText();
        String usuario = this.usuario.getText();
        String clave = this.clave.getText();
        String claveCof = this.claveConf.getText();

        if (nombre.isEmpty() || apellido.isEmpty()|| correo.isEmpty()||celular.isEmpty()|| usuario.isEmpty()|| clave.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!claveCof.trim().equals(clave.trim())) {
            JOptionPane.showMessageDialog(null, "La clave no coincide");
        } else {
           int estudianteRegistrado = GestorEstudiante.registrarEstudiante(nombre, apellido, correo, celular, usuario, clave);
           switch (estudianteRegistrado){
               case 1: JOptionPane.showMessageDialog(null, "Estudiante registrado satisfactoriamente:" +
                       "\n" + nombre + " " + apellido + "\ncorreo: " + correo + "\ncelular: " + celular  );
               break;
               case -1: JOptionPane.showMessageDialog(null, "El correo no pertenece a la institucion");
                   break;
               case -2: JOptionPane.showMessageDialog(null, "El estudiante ya existe / datos incorrectos");
                   break;
           }
        }
    }



}

