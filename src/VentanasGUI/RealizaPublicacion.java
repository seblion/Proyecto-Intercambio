package VentanasGUI;

import Logica.Estudiante;
import Logica.GestorPublicacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RealizaPublicacion {
    private JTextField tituloField1;
    private JTextField descripcionField2;
    private JTextField tipoField3;
    private JButton seleccionarImagenButton;
    private JButton guardarPublicacionButton;
    private JPanel realizaPublicacionPanel;
    private JLabel tituloLabel;
    private GUIPrincipal controlador;
    private GestorPublicacion gestorPublicacion;
    private Estudiante estudianteActual;
    public RealizaPublicacion (GUIPrincipal controlador){
        this.controlador = controlador;
        guardarPublicacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = tituloField1.getText();
            String descripcion = descripcionField2.getText();
            String tipo = tipoField3.getText();
                gestorPublicacion.agregarPublicacion(titulo, descripcion, tipo, estudianteActual);
                controlador.cambiarVentana("Interaccion");
            }
        });
    }
    public JPanel getPanel(){
        return realizaPublicacionPanel;
    }
//    JButton btnGuardar = new JButton("Guardar");
//        btnGuardar.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String titulo = txtTitulo.getText();
//            String descripcion = txtDescripcion.getText();
//            String tipo = txtTipo.getText();
//            String imagen = txtImagen.getText().isEmpty() ? null : txtImagen.getText();
//
//            if (publicacion == null) {
//                gestor.agregarPublicacion(titulo, descripcion, imagen, tipo);
//            } else {
//                gestor.editarPublicacion(publicacion.getId(), titulo, descripcion, imagen, tipo);
//            }
//            actualizarTabla();
//            formulario.dispose();
//        }
//    });
}
