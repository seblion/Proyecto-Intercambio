package Logica;

public class GestorEstudiante {
    public static boolean registrarEstudiante(String nombre, String celular, String contrasena, String correo){

//        if (Estudiante.existe(correo))
//            return false; //todo: o modificar para utilizar excepciones
        Estudiante nuevoEstudiante= new Estudiante(nombre, celular,contrasena,correo);

        return nuevoEstudiante.guardarEstudiante();
    }
}
