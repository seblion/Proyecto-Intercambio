package Logica;

import Persistencia.IntercambioDAO;

public class GestorIntercambio {
    private IntercambioDAO intercambioDAO;
    public GestorIntercambio(IntercambioDAO intercambioDAO) {
        this.intercambioDAO = intercambioDAO;
    }
    public Intercambio iniciarIntercambio(Estudiante estudianteOferente, Estudiante estudianteReceptor, Publicacion publicacionOferente) {
        // Validar condiciones iniciales
        if (!validarCondicionesIniciales(estudianteOferente, estudianteReceptor, publicacionOferente)) {
            throw new IllegalStateException("No se cumplen las condiciones para iniciar el intercambio");
        }
        Intercambio nuevoIntercambio = new Intercambio(estudianteOferente, estudianteReceptor, publicacionOferente);
        return intercambioDAO.guardar(nuevoIntercambio);
    }
    public void proponerContraoferta(Intercambio intercambio, Publicacion publicacionReceptor) {
    // Validar que la publicación pertenezca al receptor
    if (!publicacionReceptor.perteneceA(intercambio.getEstudianteReceptor())) {
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
    if (intercambio.getEstado() == EstadoIntercambio.EN_PROCESO) {
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
    private boolean validarCondicionesIniciales(Estudiante estudianteOferente, Estudiante estudianteReceptor, Publicacion publicacion) {
        return
//                estudianteOferente.estaActiva() &&
//                estudianteReceptor.estaActiva() &&
                publicacion.estaDisponible() &&
//                publicacion.perteneceA(estudianteOferente) &&
                !intercambioDAO.existenIntercambiosPendientes(estudianteOferente, estudianteReceptor);
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
}
