package Logica;

import Persistencia.EstudianteDAO;

public class Estudiante {
    private static Persistencia.EstudianteDAO dao;
    private String idEstudiante;
    private String nombre;
    private String apellido;
    private String correo;
    private String celular;
    private String usuario;
    private String clave;

    public Estudiante( String nombre, String apellido, String correo, String celular, String usuario, String clave) {
        this.idEstudiante = null;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correo = correo;
        this.usuario = usuario;
        this.clave = clave;
        this.dao = new EstudianteDAO();
    }

    public static boolean existe(String correo) {
        return false;
    }

    @Override
    public String toString() {
        return idEstudiante +
                "','" + nombre +
                "','" + apellido + "','" + correo + "','" +celular +
                "','" +usuario + "','" +  clave ;

    }

    public String registro() {
        return nombre +
                "','" + apellido + "','" + correo + "','" +celular +
                "','" +usuario + "','" +  clave ;

    }


    public void guardarEstudiante() throws Exception {
        this.dao.guardarEstudiante(this.registro());
    }
}
