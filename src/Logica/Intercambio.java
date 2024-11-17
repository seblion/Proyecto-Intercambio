package Logica;

import java.util.ArrayList;
import java.util.List;

public class Intercambio {
    private String idIntercambio;
    private Estudiante estudianteOferente;
    private Estudiante estudianteReceptor;
    private Publicacion publicacionOferente;
    private Publicacion publicacionReceptor;
    private EstadoIntercambio estado;
    private boolean aceptacionOferente;
    private boolean aceptacionReceptor;
    public Intercambio(Estudiante estudianteOferente, Publicacion publicacion){
        this.idIntercambio = null;
        this.estudianteOferente = estudianteOferente;
        this.estudianteReceptor = publicacion.getPropietario();
        this.publicacionOferente = publicacion;
        this.estado = EstadoIntercambio.PENDIENTE_CONFIRMACION;
        this.aceptacionOferente = true;  // El oferente acepta por defecto su propia oferta
        this.aceptacionReceptor = false;
    }
    public void establecerPublicacionReceptor(Publicacion publicacionReceptor) {
        this.publicacionReceptor = publicacionReceptor;
        this.estado = EstadoIntercambio.PENDIENTE_CONTRAOFERTA;
    }
    public void aceptarIntercambio(Estudiante estudiante) {
        if (estudiante.equals(estudianteOferente)) {
            this.aceptacionOferente = true;
        } else if (estudiante.equals(estudianteReceptor)) {
            this.aceptacionReceptor = true;
        }

        if (aceptacionOferente && aceptacionReceptor) {
            this.estado = EstadoIntercambio.EN_PROCESO;
        }
    }
    public void cambiarEstado(EstadoIntercambio nuevoEstado){
        this.estado = nuevoEstado;
    }
    public void rechazarIntercambio(Estudiante estudiante) {
        this.estado = EstadoIntercambio.CANCELADO;
        if (estudiante.equals(estudianteOferente)) {
            this.aceptacionOferente = false;
        } else if (estudiante.equals(estudianteReceptor)) {
            this.aceptacionReceptor = false;
        }
    }
    public List<Estudiante> obtenerParticipantes() {
        List<Estudiante> participantes = new ArrayList<>();
        participantes.add(estudianteOferente);
        participantes.add(estudianteReceptor);
        return participantes;
    }
    public void terminarIntercambio(){
        if (this.estado == EstadoIntercambio.EN_PROCESO) {
            this.estado = EstadoIntercambio.COMPLETADO;
        } else {
            throw new IllegalStateException("El intercambio no está en proceso");
        }
    }

    public Publicacion getPublicacionReceptor() {
        return publicacionReceptor;
    }
    public EstadoIntercambio getEstado() {
        return estado;
    }
    public Publicacion getPublicacionOferente() {
        return publicacionOferente;
    }
    public Estudiante getEstudianteOferente() {
        return estudianteOferente;
    }
    public Estudiante getEstudianteReceptor() {
        return estudianteReceptor;
    }

    public String registroInicial() {
        return String.valueOf(estado) + "','"+ estudianteOferente.getIdEstudiante();
    }
}
