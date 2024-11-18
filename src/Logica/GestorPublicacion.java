package Logica;

import Persistencia.IntercambioDAO;
import Persistencia.PublicacionDAO;

import java.util.ArrayList;
import java.util.List;

public class GestorPublicacion {
    private final PublicacionDAO dao;
    private List<Publicacion> publicaciones;
    private List<Intercambio> intercambios;

    public GestorPublicacion() {
        this.dao= new PublicacionDAO();
        publicaciones = new ArrayList<>();
        intercambios = new ArrayList<>();

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

    // Metodo para editar una publicación por ID
    public void editarPublicacion(int id, String nuevoTitulo, String nuevaDescripcion, String nuevoTipo) {
        for (Publicacion pub : publicaciones) {
            if (pub.getId() == id) {
                pub.setTitulo(nuevoTitulo);
                pub.setDescripcion(nuevaDescripcion);
                pub.setTipo(nuevoTipo);
                System.out.println("Publicación actualizada: " + pub);
                return;
            }
        }
        System.out.println("Publicación " + id + " no encontrada.");
    }

    // Metodo para eliminar una publicación por ID
    public void eliminarPublicacion(int id) {
        publicaciones.removeIf(pub -> pub.getId() == id);
        System.out.println("Publicación " + id + " eliminada.");
    }

    // Metodo para obtener una publicación por ID
    public Publicacion obtenerPublicacionPorId(int id) {
        for (Publicacion pub : publicaciones) {
            if (pub.getId() == id) {
                return pub;
            }
        }
        return null; // Retorna null si no se encuentra la publicación
    }

    // Metodo para listar todas las publicaciones
    public void listarPublicaciones() {
        if (publicaciones.isEmpty()) {
            System.out.println("No hay publicaciones.");
        } else {
            System.out.println("Lista de publicaciones:");
            for (Publicacion pub : publicaciones) {
                System.out.println(pub);
            }
        }
    }
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


}
