package VentanasGUI;

import javax.swing.*;
import java.awt.*;

public class GUIPrincipal extends JFrame {
    private CardLayout cardLayout;
    private  JPanel mainPanel;
    public GUIPrincipal(){
        setTitle("Poli Trueque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        Inicio inicio = new Inicio (this);
        Registro registro = new Registro(this);
        Interaccion interaccion = new Interaccion(this);
        RealizaPublicacion realizaPublicacion = new RealizaPublicacion(this);

        mainPanel.add(inicio.getPanel(),"Inicio");
        mainPanel.add(registro.getPanel(),"Registro");
        mainPanel.add(interaccion.getPanel(),"Interaccion");
        mainPanel.add(realizaPublicacion.getPanel(),"RealizaPublicacion");
        cardLayout.show(mainPanel,"Inicio");
        //cardLayout.show(mainPanel,"Interaccion");
        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);


    }
    public void cambiarVentana(String nombreVentana){
        cardLayout.show(mainPanel,nombreVentana);
    }
    public static void main(String[] args) {
        new GUIPrincipal();
    }
}
