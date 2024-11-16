import VentanasGUI.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        DAO.conectarBase();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//                JFrame frame = new JFrame("Registro de Usuario");
//                frame.setContentPane(new Registro().panel1);
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.pack();
//                frame.setLocationRelativeTo(null);
//                frame.setVisible(true);
                new GUIPrincipal();
            }
        });
    }
}