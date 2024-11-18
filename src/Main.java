import VentanasGUI.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        DAO.conectarBase();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIPrincipal();
            }
        });
    }
}