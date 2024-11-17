package Logica;

import Persistencia.EstudianteDAO;

public class GestorEstudiante {

    private final EstudianteDAO dao;
    private Estudiante estudianteActual;

    public GestorEstudiante() {
        this.dao = new EstudianteDAO();
        this.estudianteActual= null;
    }

    public int registrarEstudiante(String nombre, String apellido, String correo, String celular, String usuario, String clave){

        if (!esCorreoEstudiantil(correo))
            return -1;
        Estudiante nuevoEstudiante= new Estudiante(nombre, apellido, correo, celular, usuario, clave);

        //todo:analizar uso de interfaz
        try {
            this.dao.guardarEstudiante(nuevoEstudiante.registro());

//            nuevoEstudiante.guardarEstudiante();
            return 1;
        } catch (Exception e) {
            return -2;
        }
    }

    private static boolean esCorreoEstudiantil(String correo) {
        return correo.contains("@epn.edu.ec");
    }

    public Estudiante obtenerEstudiante(String usuario, String claveIngresada){

        try {
            Estudiante estudiante = this.dao.devolverEstudiante(usuario);
            if(estudiante.claveCorrecta(claveIngresada)){
                estudianteActual = estudiante;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return estudianteActual;
    }
}
