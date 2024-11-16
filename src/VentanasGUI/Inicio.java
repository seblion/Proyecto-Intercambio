package VentanasGUI;

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
                controlador.cambiarVentana("Interaccion");
            }
        });
    }
    public JPanel getPanel(){
        return inicio;
    }
}
