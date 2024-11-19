package Logica;

import java.util.ArrayList;
import java.util.List;

public class Intercambio {
    private String idIntercambio;

    private String idInteresado;
    private Estudiante estudianteOferente;
    private Estudiante estudianteReceptor;
    private Publicacion publicacionOferente;

    private Publicacion publicacionReceptor;

    private String estado;
    private boolean aceptacionOferente;
    private boolean aceptacionReceptor;
    public Intercambio(Estudiante estudianteOferente, Publicacion publicacion){
        this.idIntercambio = null;
        this.estudianteOferente = estudianteOferente;
        this.estudianteReceptor = publicacion.getPropietario();
        this.publicacionOferente = publicacion;

        //TODO: REVISAR CAMBIOS
        this.estado = "EN_PROCESO";
        this.aceptacionOferente = true;  // El oferente acepta por defecto su propia oferta
        this.aceptacionReceptor = false;
    }

    public Intercambio() {

    }


    public String getIdInteresado() {
        return idInteresado;
    }

    public void setIdIntercambio(String idIntercambio) {
        this.idIntercambio = idIntercambio;
    }

    public void setEstudianteOferente(Estudiante estudianteOferente) {
        this.estudianteOferente = estudianteOferente;
    }

    public void setPublicacionOferente(Publicacion publicacionOferente) {
        this.publicacionOferente = publicacionOferente;
    }

    public void setPublicacionReceptor(Publicacion publicacionReceptor) {
        this.publicacionReceptor = publicacionReceptor;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }



    public void establecerPublicacionReceptor(Publicacion publicacionReceptor) {
        this.publicacionReceptor = publicacionReceptor;
        this.estado = "PENDIENTE";
    }
    public void aceptarIntercambio(Estudiante estudiante) {
        if (estudiante.equals(estudianteOferente)) {
            this.aceptacionOferente = true;
        } else if (estudiante.equals(estudianteReceptor)) {
            this.aceptacionReceptor = true;
        }

        if (aceptacionOferente && aceptacionReceptor) {
            this.estado = "EN_PROCESO";
        }
    }
    public void cambiarEstado(String nuevoEstado){
        this.estado = nuevoEstado;
    }
    public void rechazarIntercambio(Estudiante estudiante) {
        this.estado = "CANCELADO";
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
        if (this.estado == "EN_PROCESO") {
            this.estado = "COMPLETADO";
        } else {
            throw new IllegalStateException("El intercambio no está en proceso");
        }
    }

    public Publicacion getPublicacionReceptor() {
        return publicacionReceptor;
    }
    public String getEstado() {
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

    public String getIdIntercambio() {
        return idIntercambio;
    }

    public void setIdInteresado(String trim) {
        idInteresado = trim;
    }

    public String getIdOfertante() {
        return idInteresado;
    }

    public void setEstudianteReceptor(Estudiante propietario) {
        estudianteReceptor=propietario;
    }
}
