package VentanasGUI;

import Logica.Estudiante;
import Logica.GestorEstudiante;
import Logica.GestorIntercambio;
import Logica.GestorPublicacion;

import javax.swing.*;
import java.awt.*;

public class GUIPrincipal extends JFrame {
    final RealizaPublicacion realizaPublicacion;
    public Publicaciones publicaciones;
    public Ofertas ofertas;
    private CardLayout cardLayout;
    private  JPanel mainPanel;
    private GestorPublicacion gestorPublicacion;
    private GestorIntercambio gestorIntercambio;
    private GestorEstudiante gestorEstudiante;
    Interaccion interaccion;
    private Estudiante estudianteActual;
    public MisIntercambios misIntercambios;

    public GUIPrincipal(){
        setTitle("Poli Trueque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,500);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        gestorPublicacion = new GestorPublicacion();
        gestorIntercambio = new GestorIntercambio();
        Inicio inicio = new Inicio (this);
        Registro registro = new Registro(this);
        realizaPublicacion = new RealizaPublicacion(this);


        mainPanel.add(inicio.getPanel(),"Inicio");
        mainPanel.add(registro.getPanel(),"Registro");
        mainPanel.add(realizaPublicacion.getPanel(),"RealizaPublicacion");
        cardLayout.show(mainPanel,"Inicio");
        cardLayout.show(mainPanel,"Interaccion");
        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);


    }
    public GestorPublicacion getGestorPublicacion(){
        return gestorPublicacion;
    }
    public void cambiarVentana(String nombreVentana){
        cardLayout.show(mainPanel,nombreVentana);
    }
    public static void main(String[] args) {
        new GUIPrincipal();
    }

    public void setGestorEstudiante(GestorEstudiante gestorEstudiante) {
        this.gestorEstudiante = gestorEstudiante;
    }

    public void inicializarPaneles(Estudiante estudianteVerificado) {
        estudianteActual=estudianteVerificado;
        publicaciones = new Publicaciones(this,estudianteVerificado);
        mainPanel.add(publicaciones.getPanel(),"Publicaciones");
        ofertas = new Ofertas(this,estudianteVerificado);
        mainPanel.add(ofertas.getPanel(),"Ofertas1");
        interaccion = new Interaccion(this,estudianteVerificado);
        mainPanel.add(interaccion.getPanel(),"Interaccion");
        misIntercambios = new MisIntercambios(this,estudianteVerificado);
        mainPanel.add(misIntercambios.getPanel(),"MisIntercambios");
    }
}
