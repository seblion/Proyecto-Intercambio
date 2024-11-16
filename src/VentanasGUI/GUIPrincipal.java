package VentanasGUI;

import Logica.Estudiante;
import Logica.GestorIntercambio;
import Logica.GestorPublicacion;

import javax.swing.*;
import java.awt.*;

public class GUIPrincipal extends JFrame {
    private CardLayout cardLayout;
    private  JPanel mainPanel;
    private Estudiante estudianteActual;
    private GestorPublicacion gestorPublicacion;
    private GestorIntercambio gestorIntercambio;

    public GUIPrincipal(){
        setTitle("Poli Trueque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        gestorPublicacion = new GestorPublicacion();
        gestorIntercambio = new GestorIntercambio();
        Inicio inicio = new Inicio (this);
        Registro registro = new Registro(this);
        Interaccion interaccion = new Interaccion(this);
        RealizaPublicacion realizaPublicacion = new RealizaPublicacion(this);
        Publicaciones publicaciones = new Publicaciones(this);

        mainPanel.add(inicio.getPanel(),"Inicio");
        mainPanel.add(registro.getPanel(),"Registro");
        mainPanel.add(interaccion.getPanel(),"Interaccion");
        mainPanel.add(realizaPublicacion.getPanel(),"RealizaPublicacion");
        mainPanel.add(publicaciones.getPanel(),"Publicaciones");
        cardLayout.show(mainPanel,"Inicio");
        //cardLayout.show(mainPanel,"Interaccion");
        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);


    }
    public void setEstudianteActual(Estudiante estudiante){
        this.estudianteActual=estudiante;
    }
    public Estudiante getEstudianteActual(){
        return estudianteActual;
    }
    public GestorPublicacion getGestorPublicacion(){
        return gestorPublicacion;
    }
    public GestorIntercambio getGestorIntercambio(){
        return gestorIntercambio;
    }
    public void cambiarVentana(String nombreVentana){
        cardLayout.show(mainPanel,nombreVentana);
    }
    public static void main(String[] args) {
        new GUIPrincipal();
    }
}
