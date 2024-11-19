package Logica;

import Persistencia.PublicacionDAO;

import java.util.ArrayList;
import java.util.List;

public class GestorPublicacion {
    private final PublicacionDAO dao;
    private List<Publicacion> publicaciones;

    public GestorPublicacion() {
        this.dao= new PublicacionDAO();
        publicaciones = new ArrayList<>();

    }

    // Metodo para agregar una publicación
    public int agregarPublicacion(String titulo, String descripcion, String tipo, Estudiante estudiante) {
        Publicacion nuevaPublicacion = new Publicacion( titulo, descripcion, tipo, estudiante);

        try {
            this.dao.guardarPublicacion(nuevaPublicacion.registro());
        } catch (Exception e) {
            return -1;
        }
        publicaciones.add(nuevaPublicacion);
        return 1;
    }
//todo:borrar
//    // Metodo para editar una publicación por ID
//    public void editarPublicacion(int id, String nuevoTitulo, String nuevaDescripcion, String nuevoTipo) {
//        for (Publicacion pub : publicaciones) {
//            if (pub.getId() == id) {
//                pub.setTitulo(nuevoTitulo);
//                pub.setDescripcion(nuevaDescripcion);
//                pub.setTipo(nuevoTipo);
//                System.out.println("Publicación actualizada: " + pub);
//                return;
//            }
//        }
//        System.out.println("Publicación " + id + " no encontrada.");
//    }

    // Metodo para eliminar una publicación por ID
    public int eliminarPublicacion(Publicacion publicacion) {
        try {
            this.dao.eliminarPublicacion(publicacion.getId());
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

//    todo:borrar
// Metodo para obtener una publicación por ID
//    public Publicacion obtenerPublicacionPorId(int id) {
//        for (Publicacion pub : publicaciones) {
//            if (pub.getId() == id) {
//                return pub;
//            }
//        }
//        return null; // Retorna null si no se encuentra la publicación
//    }


    // Metodo para obtener la lista de publicaciones
    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public boolean recopilarPublicaciones() {
        try {
            publicaciones = (List<Publicacion>) this.dao.recuperarPublicaciones();
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public boolean tieneRelaciones(Publicacion publicacionSeleccionada) {
        try {
            return this.dao.tieneRelaciones(publicacionSeleccionada.getId());
        } catch (Exception e) {
            return true;
        }
    }
}