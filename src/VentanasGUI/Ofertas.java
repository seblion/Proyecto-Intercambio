package VentanasGUI;

import Logica.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Ofertas {
    private JPanel ofertasPanelPrincipal;
    private JTable tablaOfertas;
    private JTable tablaContraOfertas;
    private JButton CONTRAOFERTAButton;
    private JButton RECHAZARButton;
    private GUIPrincipal controlador;
    private GestorPublicacion gestorPublicacion;
    private Estudiante estudianteActual;
    private DefaultTableModel modeloTabla;
    private GestorIntercambio gestorIntercambio;
    private Intercambio intercambioSeleccionado;


    public Ofertas(GUIPrincipal controlador) {
        this.controlador = controlador;
        this.intercambioSeleccionado = new Intercambio();
        this.gestorPublicacion = new GestorPublicacion();
        inicializarTablaOfertas();
        inicializarTablaContraOfertas();

//        configurarEventos();
        this.gestorIntercambio = new GestorIntercambio();
        tablaOfertas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tablaOfertas.getSelectedRow();
                    if (selectedRow >= 0) {
                        String titulo = (String) tablaOfertas.getValueAt(selectedRow, 0);
                        for (Intercambio intercambio : gestorIntercambio.getIntercambios()) {
                            if (intercambio.getPublicacionOferente().getTitulo().equals(titulo)) {
                                intercambioSeleccionado = intercambio;
                                cargarContraOfertas();
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    private void inicializarTablaContraOfertas() {
            modeloTabla = new DefaultTableModel(new String[]{"Titulo", "Estudiante", "Tipo"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tablaOfertas.setModel(modeloTabla);
            tablaOfertas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tablaOfertas.getTableHeader().setReorderingAllowed(false);
        }

    private void cargarContraOfertas() {
        String idUsuarioOfertante = intercambioSeleccionado.getIdOfertante();
        modeloTabla.setRowCount(0);
        for (Publicacion publicacion : gestorPublicacion.getPublicaciones()) {
            if (publicacion.getPropietario().getIdEstudiante().equals(idUsuarioOfertante)) {
                modeloTabla.addRow(new Object[]{
                        publicacion.getPropietario().getUsuario(),
                        publicacion.getTitulo(),
                        publicacion.getTipo()
                });
            }
        }
    }

    public void cargarOfertas() {
        modeloTabla.setRowCount(0);
        gestorIntercambio = new GestorIntercambio();
        boolean recuperacion = gestorIntercambio.recopilarOfertas();
        for (Intercambio intercambio : gestorIntercambio.getIntercambios()) {
            if (intercambio.getPublicacionOferente().getPropietario().getUsuario().equals(estudianteActual.getUsuario())) {
                modeloTabla.addRow(new Object[]{
                        intercambio.getPublicacionOferente().getTitulo(),
                        intercambio.getEstudianteOferente().getUsuario(),
                        intercambio.getPublicacionOferente().getTipo()
                });
//            }
            }

        }
//    private void configurarEventos() {
//        tablaOfertas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if(!e.getValueIsAdjusting()){
//                    int selectedRow = tablaOfertas.getSelectedRow();
//                    if (selectedRow>=0){
//                        String titulo = (String) tablaOfertas.getValueAt(selectedRow,0);
//                        for (Publicacion pub : controlador.getGestorPublicacion().getPublicaciones()){
//                            if (pub.getTitulo().equals(titulo)){
//                                publicacionSeleccionada = pub;
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
        };
//        CONTRAOFERTAButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (publicacionSeleccionada != null) {
//                    try {
//
//                        int Resultado = estudianteActual.iniciarIntercambio(publicacionSeleccionada);
//                        if(Resultado==-1)
//                            throw new RuntimeException();
//                        JOptionPane.showMessageDialog(ofertasPanelPrincipal,
//                                "Se ha enviado tu interés por la publicación",
//                                "Éxito",
//                                JOptionPane.INFORMATION_MESSAGE);
//                    } catch (Exception ex) {
//                        JOptionPane.showMessageDialog(ofertasPanelPrincipal,
//                                "No se pudo seleccionar la publicación",
//                                "Error",
//                                JOptionPane.ERROR_MESSAGE);
//                    }
//
//                }
//            }
//        });
//    }
//
    private void inicializarTablaOfertas() {
        modeloTabla = new DefaultTableModel(new String[]{"Titulo","Estudiante","Tipo"},0){
            @Override
            public boolean isCellEditable(int row, int colum){
                return false;
            }
        };
        tablaOfertas.setModel(modeloTabla);
        tablaOfertas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaOfertas.getTableHeader().setReorderingAllowed(false);

//        texto.setEditable(false);
//        texto.setWrapStyleWord(true);
//        texto.setLineWrap(true);
//        hacerOfertaButton.setEnabled(false);
    }

    public JPanel getPanel() {
        return ofertasPanelPrincipal;
    }

    public void agregarEstudiante(Estudiante estudianteVerificado) {
        estudianteActual = estudianteVerificado;
    }
    }
