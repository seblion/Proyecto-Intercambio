package Persistencia;

import Logica.Estudiante;
import Logica.Intercambio;

public class IntercambioDAO {
    public Intercambio guardar(Intercambio nuevoIntercambio) {

        return nuevoIntercambio;
    }

    public void actualizar(Intercambio intercambio) {
    }

    public boolean existenIntercambiosPendientes(Estudiante estudianteOferente, Estudiante estudianteReceptor) {
        return false;
    }
    //no le se, sebas hagale jajjaja
}
