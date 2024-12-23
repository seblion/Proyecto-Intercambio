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
    private JButton verMisIntercambiosButton;
    private JPanel interaccionPanel;
    JLabel nombre;
    private JLabel usuario;
    private JButton cerrarSesionButton;
    private JButton verMisPublicacionesButton;
    private GUIPrincipal controlador;
    private Estudiante estudianteActual;
    private GestorPublicacion gestorPublicacion;

    public Interaccion(GUIPrincipal controlador, Estudiante estudianteActual) {
        this.controlador = controlador;
        this.estudianteActual = estudianteActual;
        nombre.setText(estudianteActual.getNombre() + " " + estudianteActual.getApellido());
        usuario.setText(estudianteActual.getUsuario());

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
                controlador.ofertas.cargarOfertas();
                controlador.cambiarVentana("Ofertas1");
            }
        });
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.cambiarVentana("Inicio");
            }
        });
        verMisIntercambiosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.misIntercambios.cargarIntercambios();
                controlador.cambiarVentana("MisIntercambios");
            }
        });

        verMisPublicacionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.misPublicaciones.cargarMisPublicaciones();
                controlador.cambiarVentana("MisPublicaciones");
            }
        });
    }


    public JPanel getPanel() {
        return interaccionPanel;
    }

}
