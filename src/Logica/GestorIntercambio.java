package Logica;

import Persistencia.IntercambioDAO;
import Persistencia.PublicacionDAO;

import java.util.ArrayList;
import java.util.List;

public class GestorIntercambio {
    private IntercambioDAO intercambioDAO;
    private PublicacionDAO publicacionDAO;
    private List<Intercambio> intercambios;

    public GestorIntercambio() {
        intercambioDAO = new IntercambioDAO();
        publicacionDAO = new PublicacionDAO();
        intercambios = new ArrayList<>();
    }

    public int iniciarIntercambio(Estudiante estudianteOferente, Publicacion publicacionOferente) {
        // Validar condiciones iniciales //todo: revisar lo de disponibilidad
        if (!validarCondicionesIniciales(estudianteOferente, publicacionOferente) || !publicacionDAO.hayDisponibilidad(publicacionOferente.getId())) {
            return -1;
        }
        Intercambio nuevoIntercambio = new Intercambio(estudianteOferente, publicacionOferente);

        try {
            int idIntercambio = intercambioDAO.guardar(nuevoIntercambio);
            publicacionDAO.vincularIntercambio(idIntercambio, publicacionOferente.getId());
        } catch (Exception e) {
            return -1;
        }
        return 1;
    }

    public void proponerContraoferta(Intercambio intercambio, Publicacion publicacionReceptor) {
    // Validar que la publicación pertenezca al receptor
    if (!publicacionReceptor.perteneceA(intercambio.getEstudianteReceptor().getUsuario())) {
        throw new IllegalArgumentException("La publicación debe pertenecer al receptor");
    }
    // Validar que la publicación esté disponible
    if (!publicacionReceptor.estaDisponible()) {
        throw new IllegalStateException("La publicación seleccionada no está disponible");
    }
    intercambio.establecerPublicacionReceptor(publicacionReceptor);
    intercambioDAO.actualizar(intercambio);
}
    public void aceptarPropuesta(Intercambio intercambio, Estudiante estudiante) {
    // Validar que la cuenta sea participante del intercambio
    if (!esParticipante(estudiante, intercambio)) {
        throw new IllegalArgumentException("La cuenta no es participante del intercambio");
    }
    // Validar que haya una contraoferta establecida
    if (intercambio.getPublicacionReceptor() == null) {
        throw new IllegalStateException("No hay una contraoferta establecida");
    }

    intercambio.aceptarIntercambio(estudiante);
    intercambioDAO.actualizar(intercambio);

    // Si ambos aceptaron, actualizar el estado de las publicaciones
    if (intercambio.getEstado() == "EN_PROCESO") {
        actualizarEstadoPublicaciones(intercambio);
    }
}
public void rechazarPropuesta(Intercambio intercambio, Estudiante estudiante) {
    if (!esParticipante(estudiante, intercambio)) {
        throw new IllegalArgumentException("La cuenta no es participante del intercambio");
    }
    intercambio.rechazarIntercambio(estudiante);
    intercambioDAO.actualizar(intercambio);
}
    private boolean validarCondicionesIniciales(Estudiante estudianteOferente,  Publicacion publicacion) {
        boolean x = publicacion.estaDisponible() && !publicacion.perteneceA(estudianteOferente.getUsuario()) ;
        return x;
    }
    private boolean esParticipante(Estudiante estudiante, Intercambio intercambio) {
    return estudiante.equals(intercambio.getEstudianteOferente()) ||
            estudiante.equals(intercambio.getEstudianteReceptor());
}

    private void actualizarEstadoPublicaciones(Intercambio intercambio) {
        // Actualizar estado de ambas publicaciones a "en proceso de intercambio"
        intercambio.getPublicacionOferente().marcarEnProceso();
        intercambio.getPublicacionReceptor().marcarEnProceso();
    }

    public boolean recopilarOfertas() {
        try {
            intercambios = this.intercambioDAO.recuperarOfertas();
            for(Intercambio intercambio:intercambios){
                intercambio.setEstudianteOferente(intercambio.getPublicacionOferente().getPropietario());
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Intercambio> getIntercambios() {
        return intercambios;
    }

    public void agregarContraOferta(Intercambio intercambioSeleccionado, Publicacion publicacionSeleccionada) {
//        this.intercambioDAO.guardarContraoferta(interca)
    }
}
