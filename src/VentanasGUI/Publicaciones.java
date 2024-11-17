package VentanasGUI;

import Logica.Estudiante;
import Logica.GestorPublicacion;
import Logica.Publicacion;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Publicaciones {
    private JTable tablaPublicaciones;
    private JTextArea texto;
    private JButton hacerOfertaButton;
    private JPanel panelDescripcion;
    private JLabel publicacionesTitutloLabel;
    private JPanel publicacionesListadoPanel;
    private JPanel publicacionesPanelPrincipal;
    private GUIPrincipal controlador;
    private DefaultTableModel modeloTabla;
    private Publicacion publicacionSeleccionada;
    private Estudiante estudianteActual;
    private GestorPublicacion gestorPublicacion;


    public Publicaciones(GUIPrincipal controlador){
        this.controlador = controlador;
        inicializarTabla();
        configurarEventos();
        cargarPublicaciones();
        tablaPublicaciones.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    int selectedRow = tablaPublicaciones.getSelectedRow();
                    if (selectedRow>=0){
                        String titulo = (String) tablaPublicaciones.getValueAt(selectedRow,0);
                    for (Publicacion pub : gestorPublicacion.getPublicaciones()){
                            if (pub.getTitulo().equals(titulo)){
                                publicacionSeleccionada = pub;
                                mostrarDetallesPublicacion(pub);
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

//    private void cargarPublicaciones() {
//        modeloTabla.setRowCount(0);
//        GestorPublicacion gestorPublicacion = new GestorPublicacion();
//        boolean recuperacion = gestorPublicacion.recopilarPublicaciones();
//        for (Publicacion pub:gestorPublicacion.getPublicaciones()){
//            if (!pub.getPropietario().equals(estudianteActual)){
//                modeloTabla.addRow(new Object[]{
//                        pub.getTitulo(),
//                        pub.getPropietario().getNombre(),
//                        pub.getTipo()
//                });
//            }
//        }
//
//    }

    private void cargarPublicaciones() {
        modeloTabla.setRowCount(0);
        gestorPublicacion = new GestorPublicacion();
        boolean recuperacion = gestorPublicacion.recopilarPublicaciones();
        for (Publicacion pub:gestorPublicacion.getPublicaciones()){
            if (!pub.getPropietario().equals(estudianteActual)){
                modeloTabla.addRow(new Object[]{
                        pub.getTitulo(),
                        pub.getPropietario().getNombre() + " " + pub.getPropietario().getApellido(),
                        pub.getTipo()
                });
            }
        }

    }
    private void configurarEventos() {
        tablaPublicaciones.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    int selectedRow = tablaPublicaciones.getSelectedRow();
                    if (selectedRow>=0){
                        String titulo = (String) tablaPublicaciones.getValueAt(selectedRow,0);
                        for (Publicacion pub : controlador.getGestorPublicacion().getPublicaciones()){
                            if (pub.getTitulo().equals(titulo)){
                                publicacionSeleccionada = pub;
                                mostrarDetallesPublicacion(pub);
                                break;
                            }
                        }
                    }
                }
            }
        });
    hacerOfertaButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (publicacionSeleccionada != null) {
                try {
//                    estudianteActual.iniciarIntercambio(); .iniciarIntercambio(
//                            controlador.getEstudianteActual(),
//                            publicacionSeleccionada.getPropietario(),
//                            publicacionSeleccionada
//                    );
                    JOptionPane.showMessageDialog(publicacionesPanelPrincipal,
                            "Se ha enviado tu interés por la publicación",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(publicacionesPanelPrincipal,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    });
    }
    private void inicializarTabla() {
        modeloTabla = new DefaultTableModel(new String[]{"Titulo","Estudiante","Tipo"},0){
            @Override
            public boolean isCellEditable(int row, int colum){
                return false;
            }
        };
        tablaPublicaciones.setModel(modeloTabla);
        tablaPublicaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPublicaciones.getTableHeader().setReorderingAllowed(false);

        texto.setEditable(false);
        texto.setWrapStyleWord(true);
        texto.setLineWrap(true);
        hacerOfertaButton.setEnabled(false);
    }
    private void mostrarDetallesPublicacion(Publicacion pub) {
        texto.setText(pub.getDescripcion());
        hacerOfertaButton.setEnabled(true);
    }

    public JPanel getPanel() {
        return publicacionesPanelPrincipal;
    }

    public void agregarEstudiante(Estudiante estudianteVerificado) {
        estudianteActual = estudianteVerificado;
    }
}
