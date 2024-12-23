package Logica;

public class Publicacion {
    private int id;
    private String titulo;
    private String descripcion;
    private String tipo; // "Producto" o "Servicio"
    private int disponible;
    private Estudiante propietario;

    public Publicacion(String titulo, String descripcion, String tipo, Estudiante propietario) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.propietario = propietario;
        this.disponible = 1;
    }

    public Publicacion() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public Estudiante getPropietario(){
        return propietario;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Publicacion " +
                "id= " + id +
                ", titulo= '" + titulo + '\'' +
                ", descripcion= '" + descripcion + '\'' +
                ", tipo= '" + tipo + '\'' +
                ", propietario= '"+propietario.getNombre()+ ""+propietario.getApellido()+'\''+
                ' ';
    }

    public String registro(){
        return propietario.getIdEstudiante() +
                "','" + titulo + "','" + descripcion + "','" +tipo +
                "','" +disponible;
    }

    public boolean estaDisponible() {
        return disponible==1;
    }


    public boolean perteneceA(String usuario) {
        if(this.propietario == null||usuario==null){
            return false;
        }
        String usuarioPropietario = this.propietario.getUsuario();
        boolean x= usuarioPropietario.equals(usuario);
        return x;
    }

    public void setDisponibilidad(int disponibilidad) {
        disponible = disponibilidad;
    }

    public void setPropietario(Estudiante propietario) {
        this.propietario = propietario;
    }
}
