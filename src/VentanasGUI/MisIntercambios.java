package VentanasGUI;

import Logica.Estudiante;
import Logica.GestorIntercambio;
import Logica.Intercambio;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MisIntercambios {
    private Intercambio intercambioSeleccionado;
    private  Estudiante estudianteActual;
    private  GUIPrincipal controlador;
    private JTable tablaIntercambios;
    private JButton CONFIRMARButton;
    private JButton RECHAZARButton;
    private JButton volverAlInicioButton;
    private JLabel nombreLabel;
    private JLabel celularLabel;
    private JPanel panelPrincipal;
    private DefaultTableModel modeloTabla;
    private GestorIntercambio gestorIntercambio;

    public MisIntercambios(GUIPrincipal controlador, Estudiante estudianteVerificado) {
        this.estudianteActual=estudianteVerificado;
        this.controlador = controlador;
        this.intercambioSeleccionado = new Intercambio();
        inicializarTablaIntercambios();
        CONFIRMARButton.setEnabled(false);
        RECHAZARButton.setEnabled(false);

        tablaIntercambios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tablaIntercambios.getSelectedRow();
                    if (selectedRow >= 0) {
                        String publicacionOferente = (String) tablaIntercambios.getValueAt(selectedRow, 1);
                        for (Intercambio intercambio : gestorIntercambio.getIntercambios()) {
                            if (intercambio.getPublicacionOferente().getTitulo().equals(publicacionOferente)) {
                                intercambioSeleccionado = intercambio;
                                if(intercambioSeleccionado.getEstado().equals("FINALIZADO")){
                                    if(intercambioSeleccionado.getEstudianteReceptor().getUsuario().equals(estudianteActual.getUsuario())) {
                                        nombreLabel.setText(intercambioSeleccionado.getEstudianteOferente().getNombreCompleto());
                                        celularLabel.setText(intercambioSeleccionado.getEstudianteOferente().getCelular());
                                        break;
                                    } else{
                                        nombreLabel.setText(intercambioSeleccionado.getEstudianteReceptor().getNombreCompleto());
                                        celularLabel.setText(intercambioSeleccionado.getEstudianteReceptor().getCelular());
                                        break;
                                    }
                                }
                                //todo: review
                                if(intercambio.getIdInteresado().equals(estudianteActual.getIdEstudiante()) && intercambio.getEstado()!="FINALIZADO") {
                                    CONFIRMARButton.setEnabled(true); // Habilitar el botón
                                }
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

        // Configurar evento para el botón CONTRAOFERTA
        CONFIRMARButton.addActionListener(e -> {
            if (intercambioSeleccionado != null) {
                try {
                    //todo: vincular la contraoferta al intercambio

                    // Simula un proceso para finalizar el intercambio

                    int resultado = gestorIntercambio.finalizarIntercambio(intercambioSeleccionado);
                        JOptionPane.showMessageDialog(
                                panelPrincipal,
                                "Se ha finalizado el proceso de intercambio.",
                                "Éxito",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            panelPrincipal,
                            "Error de seleccion.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                        panelPrincipal,
                        "No se ha seleccionado un producto para la contraoferta.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });
    }

    protected void cargarIntercambios() {
        modeloTabla.setRowCount(0);
        gestorIntercambio = new GestorIntercambio();
        boolean recuperacion = gestorIntercambio.recopilarOfertas();
        for (Intercambio intercambio : gestorIntercambio.getIntercambiosCompletos()) {
            if (estudianteInvolucrado(intercambio)) {
                if (!intercambio.getPublicacionOferente().estaDisponible()) {
                    modeloTabla.addRow(new Object[]{
                            intercambio.getEstudianteOferente().getUsuario(),
                            intercambio.getPublicacionOferente().getTitulo(),
                            intercambio.getEstudianteReceptor().getUsuario(),
                            intercambio.getPublicacionReceptor().getTitulo(),
                    });
                }
            }
        }
    }

    private boolean estudianteInvolucrado(Intercambio intercambio) {
        return intercambio.getPublicacionOferente().getPropietario().getUsuario().equals(estudianteActual.getUsuario())
                ||intercambio.getPublicacionReceptor().getPropietario().getUsuario().equals(estudianteActual.getUsuario())
                ;
    }


    private void inicializarTablaIntercambios() {
        modeloTabla = new DefaultTableModel(new String[]{"Usuario1","Publicacion1","Usuario2", "Publicacion2"},0){
            @Override
            public boolean isCellEditable(int row, int colum){
                return false;
            }
        };
        tablaIntercambios.setModel(modeloTabla);
        tablaIntercambios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaIntercambios.getTableHeader().setReorderingAllowed(false);
    }

    public JPanel getPanel() {
        return panelPrincipal;
    }
}
