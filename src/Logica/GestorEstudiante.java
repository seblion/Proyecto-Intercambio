package Logica;

public class GestorEstudiante {

    public GestorEstudiante() {
    }

    public static int registrarEstudiante(String nombre, String apellido, String correo, String celular, String usuario, String clave){

        if (!esCorreoEstudiantil(correo))
            return -1;
        Estudiante nuevoEstudiante= new Estudiante(nombre, apellido, correo, celular, usuario, clave);

        //todo:analizar uso de interfaz
        try {
            nuevoEstudiante.guardarEstudiante();
            return 1;
        } catch (Exception e) {
            return -2;
        }
    }
    private static boolean esCorreoEstudiantil(String correo) {
        return correo.contains("@epn.edu.ec");
    }

}
