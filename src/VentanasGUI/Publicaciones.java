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
    private JButton volverInicioButton;
    private GUIPrincipal controlador;
    private DefaultTableModel modeloTabla;
    private Publicacion publicacionSeleccionada;
    private Estudiante estudianteActual;
    private GestorPublicacion gestorPublicacion;


    public Publicaciones(GUIPrincipal controlador, Estudiante estudianteActual){
        this.controlador = controlador;
        this.estudianteActual= estudianteActual;
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

    private void cargarPublicaciones() {
        modeloTabla.setRowCount(0);
        gestorPublicacion = new GestorPublicacion();
        boolean recuperacion = gestorPublicacion.recopilarPublicaciones();
        for (Publicacion pub:gestorPublicacion.getPublicaciones()){
            if (!pub.getPropietario().getUsuario().equals(estudianteActual.getUsuario())){
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

                    int Resultado = estudianteActual.iniciarIntercambio(publicacionSeleccionada);
                    publicacionSeleccionada.setDisponibilidad(0);
                    if(Resultado==-1)
                        throw new RuntimeException();
                    JOptionPane.showMessageDialog(publicacionesPanelPrincipal,
                            "Se ha enviado tu interés por la publicación",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(publicacionesPanelPrincipal,
                            "No se pudo seleccionar la publicación",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    });
    volverInicioButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.cambiarVentana("Interaccion");
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
