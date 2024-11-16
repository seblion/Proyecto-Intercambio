package Logica;

import java.util.ArrayList;
import java.util.List;

public class GestorPublicacion {
    private List<Publicacion> publicaciones;
    private int contadorId; // Para asignar IDs únicos

    public GestorPublicacion() {
        publicaciones = new ArrayList<>();
        contadorId = 1;
    }

    // Metodo para agregar una publicación
    public void agregarPublicacion(String titulo, String descripcion, String tipo, Estudiante estudiante) {
        Publicacion nuevaPublicacion = new Publicacion(contadorId++, titulo, descripcion, tipo,estudiante);
        publicaciones.add(nuevaPublicacion);
        System.out.println("Publicación agregada: " + nuevaPublicacion);
    }

    // Metodo para editar una publicación por ID
    public void editarPublicacion(int id, String nuevoTitulo, String nuevaDescripcion, String nuevaImagen, String nuevoTipo) {
        for (Publicacion pub : publicaciones) {
            if (pub.getId() == id) {
                pub.setTitulo(nuevoTitulo);
                pub.setDescripcion(nuevaDescripcion);
                pub.setImagen(nuevaImagen);
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
}
