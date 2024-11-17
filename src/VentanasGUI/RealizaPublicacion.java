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
                guardarPublicacion();
            }
        });
    }

    public void agregarEstudiante(Estudiante estudianteVerificado) {
        estudianteActual = estudianteVerificado;
    }
    private void guardarPublicacion() {
        String titulo = tituloField1.getText();
        String descripcion = descripcionField2.getText();
        String tipo = tipoField3.getText();
        int resultadoValido = estudianteActual.crearPublicacion(titulo, descripcion, tipo);
        if (resultadoValido ==1){
            JOptionPane.showMessageDialog(null, "Registro exitoso" + "\nProducto= " +titulo  );
            controlador.cambiarVentana("Interaccion");
        }else{
            JOptionPane.showMessageDialog(realizaPublicacionPanel,
                    "Datos incorrectos","No se ha realizado la publicación", JOptionPane.ERROR_MESSAGE);
        }
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
