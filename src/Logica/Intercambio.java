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

    public Intercambio(Estudiante estudianteOferente, Publicacion publicacion){
        this.idIntercambio = null;
        this.estudianteOferente = estudianteOferente;
        this.estudianteReceptor = publicacion.getPropietario();
        this.publicacionOferente = publicacion;

        //TODO: REVISAR CAMBIOS
        this.estado = "EN_PROCESO";
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
