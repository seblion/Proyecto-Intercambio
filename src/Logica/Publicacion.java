package Logica;

public class Publicacion {
    private int id;
    private String titulo;
    private String descripcion;
    private String imagen; // Puede ser null si no se agrega una imagen
    private String tipo; // "Producto" o "Servicio"
    private boolean disponibilidad;
    private Estudiante propietario;
    private boolean proceso;

    public Publicacion(String titulo, String descripcion, String tipo,Estudiante propietario) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.tipo = tipo;
        this.disponibilidad = true;
        this.proceso=false;
        this.propietario = propietario;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
                ", imagen= '" + (imagen != null ? imagen : " Sin imagen ") + '\'' +
                ", tipo= '" + tipo + '\'' +
                ", propietario= '"+propietario.getNombre()+ ""+propietario.getApellido()+'\''+
                ' ';
    }

    public boolean estaDisponible() {
        return disponibilidad;
    }

    public void marcarEnProceso() {
        this.proceso = true;
    }

    public boolean perteneceA(Estudiante estudianteReceptor) {
        if(this.propietario == null||estudianteReceptor==null){
            return false;
        }
        return this.propietario.getIdEstudiante().equals(estudianteReceptor.getIdEstudiante());
    }
}

/*
public class Main {
    public static void main(String[] args) {
        GestorPublicacion gestor = new GestorPublicacion();

        // Agregar publicaciones
        gestor.agregarPublicacion("Producto 1", "Descripción del producto 1", "ruta/imagen1.png", "Producto");
        gestor.agregarPublicacion("Servicio 1", "Descripción del servicio 1", null, "Servicio");

        // Listar publicaciones
        gestor.listarPublicaciones();

        // Editar una publicación
        gestor.editarPublicacion(1, "Producto 1 Actualizado", "Descripción actualizada", "ruta/nueva_imagen.png", "Producto");

        // Eliminar una publicación
        gestor.eliminarPublicacion(2);

        // Listar publicaciones nuevamente
        gestor.listarPublicaciones();
    }
}
*/
