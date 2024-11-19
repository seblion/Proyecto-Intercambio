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
        this.gestorIntercambio = new GestorIntercambio();
        inicializarTablaOfertas();
        inicializarTablaContraOfertas();
        CONTRAOFERTAButton.setEnabled(false);
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

        tablaContraOfertas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    CONTRAOFERTAButton.setEnabled(false); // Inicialmente deshabilitar el botón

                    int selectedRow = tablaContraOfertas.getSelectedRow();
                    if (selectedRow >= 0) {
                        // Obtener información de la publicación seleccionada
                        String usuario = (String) tablaContraOfertas.getValueAt(selectedRow, 0);
                        String titulo = (String) tablaContraOfertas.getValueAt(selectedRow, 1);

                        // Buscar la publicación en la lista de publicaciones
                        for (Publicacion publicacion : gestorPublicacion.getPublicaciones()) {
                            if (publicacion.getTitulo().equals(titulo) &&
                                    publicacion.getPropietario().getUsuario().equals(usuario)) {
                                // Asignar la publicación seleccionada
                                publicacionSeleccionada = publicacion;
                                CONTRAOFERTAButton.setEnabled(true); // Habilitar el botón
                                break;
                            }
                        }
                    }
                }
            }
        });


        // Configurar evento para el botón CONTRAOFERTA
        CONTRAOFERTAButton.addActionListener(e -> {
            if (publicacionSeleccionada != null) {
                try {
                    //todo: vincular la contraoferta al intercambio
                    if(!publicacionSeleccionada.estaDisponible()){
                        throw new RuntimeException();
                    }
                    // Simula un proceso para finalizar el intercambio
                    int resultado = gestorIntercambio.agregarContraOferta(intercambioSeleccionado,publicacionSeleccionada);
                    if (resultado == 1){
                    JOptionPane.showMessageDialog(
                            ofertasPanelPrincipal,
                            "La contraoferta se ha registrado exitosamente.",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE
                    );}
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
            if (publicacion.getPropietario().getIdEstudiante().equals(idUsuarioOfertante) && publicacion.estaDisponible()) {
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
                        intercambio.getPublicacionOferente().getPropietario().getUsuario(),
                        intercambio.getPublicacionOferente().getTipo()
                });}
            }
        }
        };

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

    }

    public JPanel getPanel() {
        return ofertasPanelPrincipal;
    }

    public void agregarEstudiante(Estudiante estudianteVerificado) {
        estudianteActual = estudianteVerificado;
    }
    }
