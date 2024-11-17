package Persistencia;

import Logica.Estudiante;
import Logica.Intercambio;

public class IntercambioDAO extends DAO {
    public int guardar(Intercambio intercambio) throws Exception {
        int idIntercambio = -1;
        try {
            // Armado de la sentencia nativa query con el String pasado como parámetro
            String sql = "INSERT INTO INTERCAMBIO (ESTADO, IDINTERESADO) VALUES ('" + intercambio.registroInicial() + "');";

            // Inserta el registro y recupera el ID generado
            insertarModificarEliminar(sql); // Este método debe ejecutar el SQL

            // Recuperar el ID del registro recién insertado
            String sql2 = "SELECT TOP 1 IDINTERCAMBIO,ESTADO FROM INTERCAMBIO ORDER BY IDINTERCAMBIO DESC;"; // Asegúrate de ordenar por ID o por fecha si es necesario

            consultarBase(sql2); // Ejecuta la consulta para obtener el último registro consultarBase(getIdQuery);
            while (resultado.next()) {
                idIntercambio = resultado.getInt(1); // Obtiene el valor del primer campo
            }

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
        return idIntercambio;
    }

    public void actualizar(Intercambio intercambio) {
    }


    //no le se, sebas hagale jajjaja
}
