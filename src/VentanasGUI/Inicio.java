package VentanasGUI;

import Logica.Estudiante;
import Logica.GestorEstudiante;

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
    private JButton loginButton;
    private JLabel label1;
    private GUIPrincipal controlador;
    private GestorEstudiante gestorEstudiante;


    public Inicio(GUIPrincipal controlador) {
        this.controlador = controlador;
        this.gestorEstudiante = new GestorEstudiante();
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.cambiarVentana("Registro");
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioTextField.getText();
                String clave = new String(contrasenaPasswordField.getPassword());
                Estudiante estudianteVerificado = gestorEstudiante.obtenerEstudiante(usuario,clave);
                if(estudianteVerificado!=null){
                    controlador.interaccion.agregarEstudiante(estudianteVerificado);
                    controlador.realizaPublicacion.agregarEstudiante(estudianteVerificado);
                    controlador.cambiarVentana("Interaccion");
                    //todo: se usa gestorEstudiante como manejador individual y general o solo Estudiante
                   controlador.setGestorEstudiante(gestorEstudiante);
                }else {
                    JOptionPane.showMessageDialog(inicio,
                            "Usuario o contrasena incorrectos","Error de inicio de seesion", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public JPanel getPanel(){
        return inicio;
    }
}
