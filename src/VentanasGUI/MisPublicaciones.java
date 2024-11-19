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

public class MisPublicaciones {
    private final Estudiante estudianteActual;
    private  GUIPrincipal controlador;
    private JButton ELIMINARButton;
    private JTable tablaMisPublicaciones;
    private JButton volverAlInicioButton;
    private JPanel panelPrincipalM;
    private DefaultTableModel modeloTabla;
    private GestorPublicacion gestorPublicacion;
    private Publicacion publicacionSeleccionada;

    public MisPublicaciones(GUIPrincipal controlador, Estudiante estudianteActual) {
        this.controlador = controlador;
        this.estudianteActual = estudianteActual;
        this.gestorPublicacion = new GestorPublicacion();
        inicializarTabla();
        ELIMINARButton.setEnabled(false);

        tablaMisPublicaciones.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    int selectedRow = tablaMisPublicaciones.getSelectedRow();
                    if (selectedRow>=0){
                        String titulo = (String) tablaMisPublicaciones.getValueAt(selectedRow,0);
                        for (Publicacion pub : gestorPublicacion.getPublicaciones()){
                            if (pub.getTitulo().equals(titulo)){
                                publicacionSeleccionada = pub;
                                ELIMINARButton.setEnabled(true);
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

        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (publicacionSeleccionada != null) {
                    try {
                        if(gestorPublicacion.tieneRelaciones(publicacionSeleccionada))
                            throw new RuntimeException();
                    int Resultado = gestorPublicacion.eliminarPublicacion(publicacionSeleccionada);
                        if(Resultado==-1)
                            throw new RuntimeException();
                        JOptionPane.showMessageDialog(panelPrincipalM,
                                "Se ha eliminado la publicación",
                                "Éxito",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panelPrincipalM,
                                "No se pudo eliminar la publicación. Las publicaciones en proceso no pueden ser eliminadas.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

    }

    protected void cargarMisPublicaciones() {
        modeloTabla.setRowCount(0);
        gestorPublicacion = new GestorPublicacion();
        boolean recuperacion = gestorPublicacion.recopilarPublicaciones();
        for (Publicacion pub:gestorPublicacion.getPublicaciones()){
            if (pub.getPropietario().getUsuario().equals(estudianteActual.getUsuario())){
                modeloTabla.addRow(new Object[]{
                        pub.getTitulo(),
                        pub.getPropietario().getNombre() + " " + pub.getPropietario().getApellido(),
                        pub.getTipo()
                });
            }
        }

    }

    private void inicializarTabla() {
        modeloTabla = new DefaultTableModel(new String[]{"Titulo","Estudiante","Tipo"},0){
            @Override
            public boolean isCellEditable(int row, int colum){
                return false;
            }
        };
        tablaMisPublicaciones.setModel(modeloTabla);
        tablaMisPublicaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaMisPublicaciones.getTableHeader().setReorderingAllowed(false);
    }

    public JPanel getPanel() {
        return panelPrincipalM;
    }
}
