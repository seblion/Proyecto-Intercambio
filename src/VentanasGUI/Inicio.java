package VentanasGUI;

import Logica.Estudiante;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio {
    private JPanel inicio;
    private JTextField usuarioTextField;
    private JButton registerButton;
    private JLabel contraseña;
    private JLabel usuario;
    private JLabel iniciarSesionLabel;
    private JPasswordField contrasenaPasswordField;
    private JButton confirmacionButton;
    private JLabel label1;
    private GUIPrincipal controlador;

    public Inicio(GUIPrincipal controlador) {
        this.controlador = controlador;
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.cambiarVentana("Registro");
            }
        });
        confirmacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String usuario = usuarioTextField.getText();
//                String contrasena = new String(contrasenaPasswordField.getPassword());
//                //Estudiante estudianteVerificado = verificarCredenciales(usuario,contrasena);
//                if(estudianteVerificado!= null){
//                   controlador.setEstudianteActual(estudianteVerificado);
                    controlador.cambiarVentana("Interaccion");
//                }else {
//                    JOptionPane.showMessageDialog(inicio,
//                            "Usuario o contrasena incorrectos","Error de inicio de seesion", JOptionPane.ERROR_MESSAGE);
                }
//            }
        });
    }
    public JPanel getPanel(){
        return inicio;
    }
}
