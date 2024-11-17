package VentanasGUI;

import Logica.Estudiante;
import Logica.GestorPublicacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interaccion {
    private JButton verPublicacionesButton;
    private JButton verMisOfertasButton;
    private JButton realizarPublicacionButton;
    private JButton ESTADODEMISINTERCAMBIOSButton;
    private JPanel interaccionPanel;
    JLabel nombre;
    private JLabel usuario;
    private GUIPrincipal controlador;
    private Estudiante estudianteActual;
    private GestorPublicacion gestorPublicacion;

    public Interaccion(GUIPrincipal controlador) {
        this.controlador = controlador;

        realizarPublicacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.cambiarVentana("RealizaPublicacion");
            }
        });
        verPublicacionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.cambiarVentana("Publicaciones");
            }
        });

        verMisOfertasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.cambiarVentana("Oferta");
            }
        });
    }

    public JPanel getPanel() {
        return interaccionPanel;
    }

    public void agregarEstudiante(Estudiante estudianteVerificado) {
        estudianteActual=estudianteVerificado;
        nombre.setText(estudianteActual.getNombre() + " " + estudianteActual.getApellido());
        usuario.setText(estudianteActual.getUsuario());
    }
}
