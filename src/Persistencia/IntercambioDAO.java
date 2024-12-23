package Persistencia;

import Logica.Intercambio;
import Logica.Publicacion;

import java.util.ArrayList;
import java.util.List;

public class IntercambioDAO extends DAO {
    private PublicacionDAO daoPublicacion;

    public IntercambioDAO() {
        this.daoPublicacion = new PublicacionDAO();
    }

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

    public List<Intercambio> recuperarOfertas() throws Exception {
        try {
            String sql = "SELECT IDINTERCAMBIO, ESTADO, IDINTERESADO FROM INTERCAMBIO ";
            consultarBase(sql);

            Intercambio intercambio;
            List<Intercambio> intercambios = new ArrayList();
            while (resultado.next()) {
                intercambio = new Intercambio();
                intercambio.setIdIntercambio(resultado.getString(1).trim());
                intercambio.setEstado(resultado.getString(2).trim());
                intercambio.setIdInteresado(resultado.getString(3).trim());
                try{
                List<Publicacion> pubs = this.daoPublicacion.recuperarOfertas(intercambio.getIdIntercambio());
                if (pubs != null) {
                    intercambio.setPublicacionOferente(pubs.get(0));
                    if (pubs.size() == 2)
                        intercambio.setPublicacionReceptor(pubs.get(1));
                }
                    intercambios.add(intercambio);
            } catch (Exception e) {}
            }
            return intercambios;

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
        }

    public void actualizar(Intercambio intercambio) {
    }


    public void modificarEstado(String idIntercambio, String estado) throws Exception {
        try {
            //Armado de la sentencia nativa query con el String pasado como parámetro
            String sql = "UPDATE INTERCAMBIO SET ESTADO = '" + estado + "' where IDINTERCAMBIO = " + idIntercambio;
            //Ejecuto el método para actualizar la base de datos que no devuelve ningún resultado
            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void eliminarIntercambio(String idIntercambio) throws Exception {
        try {
            //Armado de la sentencia nativa query con el String pasado como parámetro
            String sql = "DELETE FROM INTERCAMBIO WHERE IDINTERCAMBIO = " + idIntercambio;
            //Ejecuto el método para actualizar la base de datos que no devuelve ningún resultado
            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
}
