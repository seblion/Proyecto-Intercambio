package Logica;

import Persistencia.EstudianteDAO;

public class Estudiante {
    private final Persistencia.EstudianteDAO dao;
    private String nombre;
    private String celular;
    private String contrasena;
    private String correo;

    public Estudiante(String nombre, String celular, String contrasena, String correo) {
        this.nombre = nombre;
        this.celular = celular;
        this.contrasena = contrasena;
        this.correo = correo;
        this.dao = new EstudianteDAO();
    }

    public static boolean existe(String correo) {
        return false;
    }

    @Override
    public String toString() {
        return nombre +
                "','" + contrasena +
                "','" + celular + "','" + correo ;
    }

    public boolean guardarEstudiante() { //todo: no es muy descriptivo crearPerfil

        try {
            this.dao.guardarEstudiante(this.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
