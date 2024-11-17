package Logica;

import Persistencia.EstudianteDAO;

public class Estudiante {
    private static Persistencia.EstudianteDAO dao;
    private GestorPublicacion gestorPublicacion;
    private String idEstudiante;
    private String nombre;
    private GestorIntercambio gestorIntercambio;

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

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
        this.gestorPublicacion = new GestorPublicacion();
        this.gestorIntercambio = new GestorIntercambio();

    }

    public Estudiante() {
        this.dao = new EstudianteDAO();
        this.gestorPublicacion = new GestorPublicacion();
        this.gestorIntercambio = new GestorIntercambio();

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


    public String getIdEstudiante() {
        return idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public boolean claveCorrecta(String claveIngresada) {
        return this.clave.equals(claveIngresada);
    }

    public String getDatosIdentidad() {
        return "Nombre: " + nombre + " " + apellido +
                "'\n Usuario: " +usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public int crearPublicacion(String titulo, String descripcion, String tipo) {
        return gestorPublicacion.agregarPublicacion(titulo, descripcion, tipo, this);
    }

    public GestorPublicacion getGestorPublicacion() {
        return gestorPublicacion;
    }

    public int iniciarIntercambio(Publicacion publicacionSeleccionada) {
        if (this.getUsuario()==publicacionSeleccionada.getPropietario().getUsuario())
            return -1;
        return gestorIntercambio.iniciarIntercambio(this,publicacionSeleccionada);
    }
}
