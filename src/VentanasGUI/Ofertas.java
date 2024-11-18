package VentanasGUI;

import Logica.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ofertas {
    private Publicacion publicacionSeleccionada;
    private JPanel ofertasPanelPrincipal;
    private JTable tablaOfertas;
    private JTable tablaContraOfertas;
    private JButton CONTRAOFERTAButton;
    private JButton RECHAZARButton;
    private JButton volverAlInicioButton;
    private GUIPrincipal controlador;
    private GestorPublicacion gestorPublicacion;
    private Estudiante estudianteActual;
    private DefaultTableModel modeloTabla;
    private DefaultTableModel modeloTabla2;

    private GestorIntercambio gestorIntercambio;
    private Intercambio intercambioSeleccionado;


    public Ofertas(GUIPrincipal controlador, Estudiante estudianteActual) {
        this.estudianteActual=estudianteActual;
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
        volverAlInicioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.cambiarVentana("Interaccion");
            }
        });

        // Listener para tablaContraOfertas
        tablaContraOfertas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tablaContraOfertas.getSelectedRow();
                if (selectedRow >= 0) {
                    // Habilitar el botón
                    CONTRAOFERTAButton.setEnabled(true);

                    // Obtener información de la publicación seleccionada
                    String titulo = (String) tablaContraOfertas.getValueAt(selectedRow, 0);
                    String usuario = (String) tablaContraOfertas.getValueAt(selectedRow, 1);

                    // Buscar la publicación y asignarla al intercambio
                    for (Publicacion publicacion : gestorPublicacion.getPublicaciones()) {
                        if (publicacion.getTitulo().equals(titulo) &&
                                publicacion.getPropietario().getUsuario().equals(usuario)) {
                            publicacionSeleccionada = publicacion;
                            break;
                        }
                    }
                } else {
                    CONTRAOFERTAButton.setEnabled(false); // Deshabilitar si no hay selección
                }
            }
        });

        // Configurar evento para el botón CONTRAOFERTA
        CONTRAOFERTAButton.addActionListener(e -> {
            if (publicacionSeleccionada != null) {
                try {
                    // Simula un proceso para finalizar el intercambio
                    gestorIntercambio.agregarContraOferta(intercambioSeleccionado,publicacionSeleccionada);
                    JOptionPane.showMessageDialog(
                            ofertasPanelPrincipal,
                            "La contraoferta se ha registrado exitosamente.",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            ofertasPanelPrincipal,
                            "Error al registrar la contraoferta.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                        ofertasPanelPrincipal,
                        "No se ha seleccionado un producto para la contraoferta.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });
    }

    private void inicializarTablaContraOfertas() {
            modeloTabla2 = new DefaultTableModel(new String[]{"Titulo", "Estudiante", "Tipo"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;

                }
            };
            tablaContraOfertas.setModel(modeloTabla2);
            tablaContraOfertas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tablaContraOfertas.getTableHeader().setReorderingAllowed(false);
        }

    private void cargarContraOfertas() {
        String idUsuarioOfertante = intercambioSeleccionado.getIdOfertante();
        modeloTabla2.setRowCount(0);
        gestorPublicacion.recopilarPublicaciones();
        for (Publicacion publicacion : gestorPublicacion.getPublicaciones()) {
            if (publicacion.getPropietario().getIdEstudiante().equals(idUsuarioOfertante)) {
                modeloTabla2.addRow(new Object[]{
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
                if(!intercambio.getPublicacionOferente().estaDisponible()){
                modeloTabla.addRow(new Object[]{
                        intercambio.getPublicacionOferente().getTitulo(),
                        intercambio.getEstudianteOferente().getUsuario(),
                        intercambio.getPublicacionOferente().getTipo()
                });}
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
